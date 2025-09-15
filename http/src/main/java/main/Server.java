package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private final int port;
	private final Router router;
	private volatile boolean running = true;

	public Server(int port, Router router) {
		this.port = port;
		this.router = router;
	}

	public Request parseRequest(InputStream in) throws IOException {
		String startLine = readLine(in);
		if (startLine == null || startLine.isEmpty())
			throw new IOException("empty request line");
		String[] parts = startLine.split(" ");
		if (parts.length < 3)
			throw new IOException("malformed request line: " + startLine);
		String method = parts[0];
		String target = parts[1];
		String httpVersion = parts[2];

		String path;
		String query = null;
		int q = target.indexOf('?');
		if (q >= 0) {
			path = target.substring(0, q);
			query = target.substring(q + 1);
		} else {
			path = target;
		}

		// Headers
		Map<String, String> headers = new LinkedHashMap<>();
		String line;
		while ((line = readLine(in)) != null) {
			if (line.isEmpty())
				break; // end of headers
			int idx = line.indexOf(':');
			if (idx <= 0)
				continue; // skip bad header
			String name = line.substring(0, idx).trim().toLowerCase(Locale.ROOT);
			String value = line.substring(idx + 1).trim();
			headers.put(name, value);
		}

		int contentLength = 0;
		if (headers.containsKey("content-length")) {
			try {
				contentLength = Integer.parseInt(headers.get("content-length"));
			} catch (NumberFormatException ignored) {
			}
		}

		byte[] body = new byte[contentLength];
		int read = 0;
		while (read < contentLength) {
			int r = in.read(body, read, contentLength - read);
			if (r == -1)
				break;
			read += r;
		}
		return Request.builder()
				.method(method)
				.url(path)
				.headers(headers)
				.build();
	}

	public String readLine(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(128);
		int prev = -1;
		while (true) {
			int b = in.read();
			if (b == -1) {
				if (baos.size() == 0)
					return null;
				else
					break;
			}
			if (b == '\n' && prev == '\r') {
				baos.write(new byte[] {}); // just to use baos
				break;
			}
			if (b != '\r')
				baos.write(b);
			prev = b;
		}
		return baos.toString(StandardCharsets.UTF_8);
	}

	public void start() throws IOException {
		try (var server = new ServerSocket(port)) {
			server.setReuseAddress(true);
			log("Listening on http://localhost:" + port);

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				running = false;
				try {
					server.close();
				} catch (IOException ignored) {
				}
				log("Shutdown requested. Bye!");
			}));

			try (ExecutorService vexec = Executors.newVirtualThreadPerTaskExecutor()) {
				while (running) {
					Socket socket;
					try {
						socket = server.accept();
					} catch (SocketException se) {
						if (!running)
							break;
						else
							throw se;
					}

					vexec.submit(() -> handleClient(socket));
				}
			}
		}
	}

	public void handleClient(Socket socket) {
		try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {
			Request req;
			try {
				req = parseRequest(in);
			} catch (Exception ex) {
				log("Bad request: " + ex.getMessage());
				new Response(out).status(400, "Bad Request").text("Bad Request\n").send();
				return;
			}

			Response res = new Response(out);
			try {
				Optional<Handler> opt = router.match(req.method, req.url);
				if (opt.isEmpty()) {
					res.status(404, "Not Found").text("Not Found\n").send();
					return;
				}
				opt.get().handle(req, res);
			} catch (UnsupportedMediaType e) {
				try {
					res.status(415, "Unsupported Media Type").text(e.getMessage() + "\n").send();
				} catch (IOException ignored) {
				}
			} catch (Exception ex) {
				log("Handler error: " + ex);
				try {
					res.status(500, "Internal Server Error").text("Internal Server Error\n").send();
				} catch (IOException ignored) {
				}
			}
		} catch (IOException ioe) {
			log("IO: " + ioe.getMessage());
		}
	}

	private static void log(String msg) {
		synchronized (System.out) {
			System.out.println("[" + Thread.currentThread() + "] " + msg);
		}
	}

	public static class UnsupportedMediaType extends RuntimeException {
		public UnsupportedMediaType(String msg) {
			super(msg);
		}
	}
}

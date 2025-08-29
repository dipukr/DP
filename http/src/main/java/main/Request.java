package main;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class Request {

	public final String method;
	public final String path;
	public final String query;
	public final String httpVersion;
	public final Map<String, String> headers;
	public final byte[] body;

	public Request(String method, String path, String query, String httpVersion,
                Map<String, String> headers, byte[] body) {
        this.method = method;
        this.path = path;
        this.query = query;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = body;
    }

	public Optional<String> header(String name) {
		return Optional.ofNullable(headers.get(name.toLowerCase(Locale.ROOT)));
	}

	public String bodyAsString() {
		return new String(body, StandardCharsets.UTF_8);
	}
	
	public static String decode(String s) {
		try {
			return URLDecoder.decode(s, StandardCharsets.UTF_8);
		} catch (Exception e) {
			return s;
		}
	}

	public Map<String, List<String>> queryParams() {
		Map<String, List<String>> params = new LinkedHashMap<>();
		if (query == null || query.isEmpty()) return params;
		for (String pair : query.split("&")) {
			int idx = pair.indexOf('=');
			String k = idx >= 0 ? decode(pair.substring(0, idx)) : decode(pair);
			String v = idx >= 0 ? decode(pair.substring(idx + 1)) : "";
			params.computeIfAbsent(k, kk -> new ArrayList<>()).add(v);
		}
		return params;
	}
}

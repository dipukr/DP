package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender  {

	private LogFormatter formatter;
	private BufferedWriter writer;
	
	public FileAppender(LogFormatter formatter, String fileName) {
		this.formatter = formatter;
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			this.writer = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			throw new RuntimeException("Failed to open log file.");
		}
	}
	
	@Override
	public synchronized void append(LogMessage message) {
		try {
			writer.write(formatter.format(message));
			writer.newLine();
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void close() {
		try {
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

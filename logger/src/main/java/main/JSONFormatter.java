package main;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class JSONFormatter implements LogFormatter {
	private static final DateTimeFormatter FORMATTER = 
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public String format(LogMessage message) {
		String formattedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(message.getTime()), 
				ZoneId.systemDefault()).format(FORMATTER);
		return String.format("{\"level\": %s, \"message\": %s, \"time\": %s}",
				message.getLevel(), message.getMessage(), formattedTime);
	}
}

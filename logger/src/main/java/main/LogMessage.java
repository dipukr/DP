package main;

import java.util.Date;

public class LogMessage {

	private LogLevel level;
	private String message;
	private long time;
	
	public LogMessage(LogLevel level, String message, long time) {
		this.level = level;
		this.message = message;
		this.time = time;
	}
	
	public LogLevel getLevel() {
		return level;
	}
	public void setLevel(LogLevel level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}

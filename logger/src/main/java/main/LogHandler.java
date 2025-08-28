package main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {

	protected LogHandler next;
	protected List<LogAppender> appenders = new CopyOnWriteArrayList();
	
	public void subsribe(LogAppender observer) {
		appenders.add(observer);
	}
	
	public void notifyObservers(LogMessage message) {
		for (LogAppender appender: appenders)
			appender.append(message);
	}
	
	public void handle(LogMessage message) {
		if (canHandle(message.getLevel()))
			notifyObservers(message);
		if (next != null)
			next.handle(message);
	}
	
	public void setNext(LogHandler next) {
		this.next = next;
	}
	
	protected abstract boolean canHandle(LogLevel level);
}

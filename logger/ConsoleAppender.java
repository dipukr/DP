package logger;

public class ConsoleAppender implements LogAppender {
	
	private LogFormatter formatter;
	
	public ConsoleAppender(LogFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public void append(LogMessage message) {
		System.out.println(formatter.format(message));
	}
}

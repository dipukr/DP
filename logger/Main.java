package logger;

public class Main {
	public static void main(String[] args) {
		Logger logger = Logger.getInstance();
		LogHandlerConfiguration.addAppenderForLevel(LogLevel.INFO, 
				new ConsoleAppender(new PlainTextFormatter()));
		LogHandlerConfiguration.addAppenderForLevel(LogLevel.ERROR,
				new ConsoleAppender(new PlainTextFormatter()));
		LogHandlerConfiguration.addAppenderForLevel(LogLevel.ERROR,
				new FileAppender(new PlainTextFormatter(), "/home/dkumar/logger.txt"));
		logger.info("This is some key information.");
		logger.error("There is some error.");
		logger.warn("hello");
	}
}

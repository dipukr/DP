package logger;

public class LogHandlerConfiguration {

	private static final LogHandler info = new InfoHandler();
	private static final LogHandler warn = new WarnHandler();
	private static final LogHandler error = new ErrorHandler();
	private static final LogHandler debug = new DebugHandler();
	private static final LogHandler fatal = new FatalHandler();
	
	public static LogHandler build() {
		debug.setNext(info);
		info.setNext(warn);
		warn.setNext(error);
		error.setNext(fatal);
		return debug;
	}
	
	public static void addAppenderForLevel(LogLevel level, LogAppender appender) {
		switch (level) {
			case INFO -> info.subsribe(appender);
			case WARN -> warn.subsribe(appender);
			case ERROR -> error.subsribe(appender);
			case DEBUG -> debug.subsribe(appender);
			case FATAL -> fatal.subsribe(appender);
		}
	}
}

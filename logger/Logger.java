package logger;

public class Logger {
	
	private static final Logger INSTANCE = new Logger();
	private LogHandler handlerChain;
	
	private Logger() {
		this.handlerChain = LogHandlerConfiguration.build();
	}
	
	public static Logger getInstance() {
		return INSTANCE;
	}
	
	public void log(LogLevel level, String message) {
		LogMessage logMessage = new LogMessage(level, message, System.currentTimeMillis());
		handlerChain.handle(logMessage);
	}
	
	public void info(String message) {log(LogLevel.INFO, message);}
	public void warn(String message) {log(LogLevel.WARN, message);}
	public void error(String message) {log(LogLevel.ERROR, message);}
	public void debug(String message) {log(LogLevel.DEBUG, message);}
	public void fatal(String message) {log(LogLevel.FATAL, message);}
}

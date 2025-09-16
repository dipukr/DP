package logger;

public class InfoHandler extends LogHandler {

	@Override
	protected boolean canHandle(LogLevel level) {
		return level == LogLevel.INFO;
	}
}

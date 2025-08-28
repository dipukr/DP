package main;

public class WarnHandler extends LogHandler {

	@Override
	protected boolean canHandle(LogLevel level) {
		return level == LogLevel.WARN;
	}
}

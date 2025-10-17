package main;

public class NewsObserver implements Observer<News> {

	@Override
	public void update(News data) {
		Console.draw("BREAKING: %s", data.news());
	}
}

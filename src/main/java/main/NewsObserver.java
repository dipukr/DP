package main;

public class NewsObserver implements Observer<News> {

	@Override
	public void update(News news) {
		Console.draw("BREAKING: %s", news.news());
	}
}

package main;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements Subject<News> {

	private List<Observer<News>> observers = new ArrayList<>();
	
	public void addNews(News news) {
		notifyAll(news);
	}
	
	@Override
	public void attach(Observer<News> observer) {
		observers.add(observer);
	}

	@Override
	public void detach(Observer<News> observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyAll(News news) {
		observers.forEach(ob -> ob.update(news));
	}
}

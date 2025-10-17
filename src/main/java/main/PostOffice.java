package main;

import java.util.ArrayList;
import java.util.List;

public class PostOffice implements Subject<Mail> {

	private List<Observer<Mail>> observers = new ArrayList<>();
	private List<Mail> mails = new ArrayList<>();
	
	public void addMail(Mail mail) {
		mails.add(mail);
		notifyAll();
	}
	
	@Override
	public void attach(Observer<Mail> observer) {
		observers.add(observer);
	}

	@Override
	public void detach(Observer<Mail> observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyAll(Mail mail) {
		for (var observer: observers)
			observer.update(mail);
	}	
}

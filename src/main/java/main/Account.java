package main;

import java.util.List;

public class Account {

	private double balance;
	private EventStore store;
	
	public Account(EventStore store) {
		this.store = store;
	}
	
	public void deposit(double amount) {
		Event event = new DepositEvent(amount);
		event.apply(this);
		store.saveEvent(event);
	}
	
	public void withdraw(double amount) {
		Event event = new WithdrawEvent(amount);
		event.apply(this);
		store.saveEvent(event);
	}
	
	public void replay(List<Event> events) {
		for (Event event: events)
			event.apply(this);
	}
	
	public void addBalance(double amount) {
		this.balance += amount;
	}
	
	public void subBalance(double amount) {
		this.balance -= amount;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}
}

package main;

import java.util.Date;

public class DepositEvent implements Event {

	private double amount;
	private Date datetime;
	
	public DepositEvent(double amount) {
		this.amount = amount;
		this.datetime = new Date();
	}
	
	public double amount() {
		return amount;
	}
	
	public Date datetime() {
		return datetime;
	}
	
	@Override
	public void apply(Account account) {
		account.addBalance(amount);
	}
	
	@Override
	public String toString() {
		return String.format("Deposited: %.2f at %s", amount, datetime);
	}
}

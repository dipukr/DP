package main;

import java.util.Date;

public class WithdrawEvent implements Event {

	private double amount;
	private Date datetime;
	
	public WithdrawEvent(double amount) {
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
		account.subBalance(amount);
	}
	
	@Override
	public String toString() {
		return String.format("Withdrawn: %.2f at %s", amount, datetime);
	}
}

package main;

public class CardPayment implements Payment {

	@Override
	public void pay(double amount) {
		Console.draw("Paid Rs. %f using Card", amount);
	}
}

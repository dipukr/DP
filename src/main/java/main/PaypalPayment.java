package main;

public class PaypalPayment implements Payment {

	@Override
	public void pay(double amount) {
		Console.draw("Paid Rs. %f using Paypal", amount);
	}
}

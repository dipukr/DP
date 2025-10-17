package main;

public class UPIPayment implements Payment {
	
	@Override
	public void pay(double amount) {
		Console.draw("Paid Rs. %f using UPI", amount);
	}
}

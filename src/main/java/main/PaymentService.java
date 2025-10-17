package main;

import java.util.Scanner;

public class PaymentService {
	public void pay(Payment payment, double amount) {
		payment.pay(amount);
	}
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Console.write("Enter C for Card, P for Paypal and U for UPI: ");
		String val = scanner.next();
		PaymentService service = new PaymentService();
		switch (val) {
		case "C": service.pay(new CardPayment(), 100); break;
		case "P": service.pay(new PaypalPayment(), 100); break;
		case "U": service.pay(new UPIPayment(), 100); break;
		}
		scanner.close();
	}
}

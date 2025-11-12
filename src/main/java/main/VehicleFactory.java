package main;

public class VehicleFactory {

	public static Vehicle get(String type) {
		return switch (type) {
			case "Car" -> new Car();
			case "Truck" -> new Truck();
			default -> null;
		};
	}
}

package main;

public class FactoryMethod {
	public static void main(String[] args) throws Exception {
		Vehicle vehicle = VehicleFactory.get("Truck");
		vehicle.drive();
	}
}

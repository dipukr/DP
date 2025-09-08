package main;

public class Main {
	public static void main(String[] args) throws Exception {
		AuthServer authServer = new AuthServer(8780);
		authServer.start();
	}
}

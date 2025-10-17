package main;

public class EventSource {
	public static void main(String[] args) throws Exception {
		EventStore store = new EventStore();

		Account account = new Account(store);
		account.deposit(900);
		account.withdraw(300);
		account.deposit(500);

		System.out.println("After transactions: " + account);
		System.out.println("Stored events:");
		store.events().forEach(System.out::println);

		Account restoredAccount = new Account(store);
		restoredAccount.replay(store.events());

		System.out.println("\nAfter replaying events:");
		System.out.println(restoredAccount);
	}
}

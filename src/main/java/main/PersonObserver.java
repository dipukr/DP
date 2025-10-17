package main;

public class PersonObserver implements Observer<Mail> {

	private String name;
	
	@Override
	public void update(Mail mail) {
		if (mail.receiver().name().equals(name)) {
			Sender sender = mail.sender();
			Receiver receiver = mail.receiver();
			System.out.printf("[%s] received mail from [%s]", receiver, sender);
		}
	}
}

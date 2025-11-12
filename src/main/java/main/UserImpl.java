package main;

public class UserImpl extends User {
	
	public UserImpl(ChatMediator mediator, String name) {
		super(mediator, name);
	}

	@Override
	public void send(String message) {
		System.out.printf("%s sends message: %s\n", name(), message);
		mediator().sendMessage(message, this);
	}

	@Override
	public void recv(String message) {
		System.out.printf("%s receives message: %s\n", name(), message);
	}
}

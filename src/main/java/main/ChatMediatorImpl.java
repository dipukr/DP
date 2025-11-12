package main;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {
	
	private List<User> users = new ArrayList<>();

	@Override
	public void addUser(User user) {
		System.out.printf("%s joined the chat.\n", user.name());
		users.add(user);
	}

	@Override
	public void sendMessage(String message, User user) {
		for (User usr: users)
			if (usr != user)
				usr.recv(message);
	}
}

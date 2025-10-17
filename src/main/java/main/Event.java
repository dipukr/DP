package main;

import java.util.Date;

public interface Event {
	Date datetime();
	void apply(Account account);
}

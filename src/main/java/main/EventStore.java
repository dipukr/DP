package main;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

	private List<Event> events = new ArrayList<>();
	
	public void saveEvent(Event event) {
		events.add(event);
	}
	
	public List<Event> events() {
		return List.copyOf(events);
	}
}

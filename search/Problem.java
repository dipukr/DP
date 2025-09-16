package main;

import java.util.List;

public interface Problem {
	State getStartState();
	State getGoalState();
	boolean isGoalState(State state);
	List<State> getSuccessors(State state);
}

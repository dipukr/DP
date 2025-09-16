package main;

import java.util.List;
import java.util.ArrayList;

public class Puzzle implements Problem {

	private State start;
	private State goal;

	public Puzzle(State start, State goal) {
		this.start = start;
		this.goal = goal;
	}

	@Override
	public State getStartState() {
		return start;
	}
	
	public State getGoalState() {
		return goal;
	}

	@Override
	public boolean isGoalState(State state) {
		return state.equals(goal);
	}

	@Override
	public List<State> getSuccessors(State state) {
		List<State> successors = new ArrayList<>();
		for (Action action: state.getActions())
			successors.add(state.result(action));
		return successors;
	}
}
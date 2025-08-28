package main;

import java.util.Map;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		byte[][] data = {{1,6,7},{8,4,0},{5,2,3}};
		State start = new State(data);
		State goal = new State(State.goal);
		Problem problem = new Puzzle(start, goal);
		Map<String, State> prev = Solvers.aStar(problem);
		Stack<State> states = new Stack<>();
		states.add(problem.getGoalState());
		while (!states.peek().equals(start))
			states.add(prev.get(states.peek().toString()));
		System.out.printf("Path size: %d\n", states.size());
		byte[][][] frames = new byte[states.size()][][];
		int count = 0;
		while (!states.isEmpty())
			frames[count++] = states.pop().data;
		Anim.anim(frames);
	}
}
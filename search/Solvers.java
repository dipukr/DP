package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Solvers {
	public static Map<String, State> aStar(Problem problem) {
		long times = 0;
		PriorityQueue<State> queue = new PriorityQueue<>();
		Map<String, Integer> dist = new HashMap<>();
		Map<String, State> prev = new HashMap<>();
		dist.put(problem.getStartState().toString(), 0);
		queue.add(problem.getStartState());
		while (!queue.isEmpty()) {
			times += 1;
			State curr = queue.poll();
			if (curr.isGoal()) break;
			for (State next: problem.getSuccessors(curr)) {
				String nextStr = next.toString();
				if (dist.getOrDefault(nextStr, Integer.MAX_VALUE) > next.getCost()) {
					prev.put(nextStr, curr);
					dist.put(nextStr, next.getCost());
					queue.add(next);
				}
			}
		}
		System.out.println("Times: "+times);
		return prev;
	}

	public static Map<String, State> depthFirst(Problem problem) {
		long count = 0;
		State startState = problem.getStartState();
		Stack<State> stack = new Stack<>();
		Set<String> visited = new HashSet<>();
		Map<String, State> prev = new HashMap<>();
		stack.push(startState);
		visited.add(startState.toString());
		while (!stack.isEmpty()) {
			State curr = stack.pop();
			count += 1;
			if (curr.isGoal())
				break;
			for (State state : problem.getSuccessors(curr)) {
				if (!visited.contains(state.toString())) {
					visited.add(state.toString());
					prev.put(state.toString(), curr);
					stack.push(state);
				}
			}
		}
		System.out.println("Total expanded nodes: " + count);
		return prev;
	}
}
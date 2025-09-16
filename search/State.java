package main;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class State implements Comparable<State> {
	
	public static final byte[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
	
	public byte[][] data;
	public int row, col;
	public int cost;
	public int weight;

	public State(byte[][] maze) {
		this(maze, 0);
	}
	
	public State(byte[][] data, int cost) {
		int r = data.length;
		int c = data[0].length;
		this.data = new byte[r][c];
		this.cost = cost;
		this.weight = cost + heuristic();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				byte val = data[i][j];
				this.data[i][j] = val;
				if (val == 0) {
					row = i;
					col = j;
				}
			}
		}
	}

	public boolean isGoal() {
		for (int i = 0; i < data.length; i++)
			if (!Arrays.equals(data[i], goal[i]))
				return false;
		return true;
	}

	public List<Action> getActions() {
		var actions = new ArrayList<Action>();
		if (row != 0) actions.add(Action.UP);
		if (col != 0) actions.add(Action.LEFT);
		if (row != data.length - 1) actions.add(Action.DOWN);
		if (col != data[0].length - 1) actions.add(Action.RIGHT);
		return actions;
	}

	public State result(Action action) {
		int newrow = 0, newcol = 0;
		switch (action) {
		case UP: newrow = row - 1; newcol = col; break;
		case DOWN: newrow = row + 1; newcol = col; break;
		case LEFT: newrow = row; newcol = col - 1; break;
		case RIGHT: newrow = row; newcol = col + 1; break;
		}
		State state = new State(this.data, this.cost + 1);
		state.data[row][col] = this.data[newrow][newcol];
		state.data[newrow][newcol] = this.data[row][col];
		state.row = newrow;
		state.col = newcol;
		return state;
	}
	
	public int heuristic() {
		int count = 0;
		int r = data.length;
		int c = data[0].length;
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (data[i][j] != goal[i][j])
					count += 1;
		return count;
	}
	
	public int getCost() {
		return cost;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		State other = (State) obj;
		for (int i = 0; i < this.data.length; i++)
			if (!Arrays.equals(this.data[i], other.data[i]))
				return false;
		return true;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder(9);
		for (byte[] row: data)
			for (byte cell: row)
				builder.append(cell);
		return builder.toString();
	}

	@Override
	public int compareTo(State that) {
		return this.weight - that.weight;
	}
}
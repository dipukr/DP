package main;

public class CPU {
	public void freeze() {
		Console.draw("CPU freezing...");
	}

	public void jump(long position) {
		Console.draw("CPU jumping to position %d", position);
	}

	public void execute() {
		Console.draw("CPU executing instructions...");
	}
}

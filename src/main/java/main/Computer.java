package main;

public class Computer {
	
	private Memory memory;
	private CPU cpu;
	private SSD ssd;

	public Computer() {
		this.cpu = new CPU();
		this.ssd = new SSD();
		this.memory = new Memory();
	}

	public void startComputer() {
		Console.draw("Starting computer...");
		cpu.freeze();
		String bootData = ssd.read(0, 1024);
		memory.load(0, bootData);
		cpu.jump(0);
		cpu.execute();
		Console.draw("Computer started successfully");
	}
}

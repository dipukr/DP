package main;

public class Person {

	private String name;
	private int age;
	private int sal;
	
	private Person(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.sal = builder.sal;
	}
	
	public String name() {return name;}
	public int age() {return age;}
	public int sal() {return sal;}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String name;
		private int age;
		private int sal;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		public Builder sal(int sal) {
			this.sal = sal;
			return this;
		}
		
		public Person build() {
			return new Person(this);
		}
	}
}

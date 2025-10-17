package main;

public class Main {
	public static void main(String[] args) throws Exception {
		Person o = Person.builder()
				.name("name")
				.age(10)
				.sal(20000)
				.build();
		System.out.println(o.name());
		System.out.println(o.age());
		System.out.println(o.sal());
	}
}

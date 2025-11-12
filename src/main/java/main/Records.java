package main;

record Content(String data) {}
record Address(String addr, String city) {}
record Sender(String name, Address address) {}
record Receiver(String name, Address address) {}
record Mail(Sender sender, Receiver receiver, Content content) {}
record News(String title, String news) {}
record Message(String msg) {}
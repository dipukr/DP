package main;

public class HyperlinkElement implements Element {

	private String url;
	
	public HyperlinkElement(String url) {
		this.url = url;
	}
	
	public String url() {
		return url;
	}
	
	@Override
	public void accept(Visitor vistor) {
		vistor.visit(this);
	}
}

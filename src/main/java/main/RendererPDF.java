package main;

public class RendererPDF implements Visitor {

	@Override
	public void visit(TextElement element) {
		Console.draw("Rendering text: %s", element.text());
	}

	@Override
	public void visit(ImageElement element) {
		Console.draw("Rendering image: %s", element.imagePath());
	}

	@Override
	public void visit(HyperlinkElement element) {
		Console.draw("Rendering link: %s", element.url());
	}
}

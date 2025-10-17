package main;

public interface Visitor {
	void visit(TextElement element);
	void visit(ImageElement element);
	void visit(HyperlinkElement element);
}

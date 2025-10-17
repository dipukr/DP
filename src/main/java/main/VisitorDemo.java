package main;

import java.util.List;

public class VisitorDemo {
	public static void main(String[] args) throws Exception {
		List<Element> elements = List.of(new TextElement("Hello World"),
				new ImageElement("/images/logo.png"),
				new HyperlinkElement("https://example.com"));

		Visitor renderer = new RendererPDF();
		Visitor exporter = new ExporterPDF();
		
		for (Element element: elements)
			element.accept(renderer);
		for (Element element: elements)
			element.accept(exporter);
	}
}

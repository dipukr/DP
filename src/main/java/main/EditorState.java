package main;

@REM("Memento")
public class EditorState {	
	
	private final String content;

	public EditorState(String content) {
		this.content = content;
	}
	
	public String content() {
		return content;
	}
}

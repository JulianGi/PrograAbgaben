public class File extends Node {

	private String content;

	public File() {
		super();
	}

	public String readContent() {
		return content;
	}

	public void writeContent(String Content) {
		content = Content;
		this.touch();
	}

}
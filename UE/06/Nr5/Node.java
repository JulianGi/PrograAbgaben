import java.lang.*;

public abstract class Node {

	private long lastModified;

	public Node() {
		touch();
	}

	public long getLastModified() {
		return lastModified;
	}

	public void touch() {
		lastModified = System.currentTimeMillis();
	}
}
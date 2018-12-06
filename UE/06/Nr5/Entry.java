
public class Entry {
	private final String name;
	private final Node node;

	public Entry(String Name, Node Node) {
		name = Name;
		node = Node;
	}

	public String getName() {
		return name;
	}

	public Node getNode() {
		return node;
	}

	public File getAsFile() {
		if (node instanceof File)
			return (File) node;
		return null;
	}

	public Directory getAsDirectory() {
		if (node instanceof Directory)
			return (Directory) node;
		return null;
	}

	public Entry createHardlink(String newName) {
		return new Entry(newName, node);
	}
}
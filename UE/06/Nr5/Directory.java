public class Directory extends Node {

	private Entry[] children;

	// Constructor

	public static Directory createEmpty() {
		return new Directory();
	}

	public Directory() {
		super();
		children = new Entry[0];
	}

	// General

	public Entry[] getEntries() {
		//return new Entry[children.length];
		return children;
	}

	public boolean containsEntry(String name) {
		return getEntry(name) != null;
	}

	public Entry getEntry(String name) {
		for (int i = 0; i < children.length; i++) {
			if (children[i].getName() == name)
				return children[i];
		}
		return null;
	}

	// Utility

	public void accept(String name, Visitor visitor) {
		visitor.visitDirectory(name, this);
		for (int i = 0; i < children.length; i++) {
			if (children[i].getNode() instanceof File)
				visitor.visitFile(children[i].getName(), (File) children[i].getNode());
			else if (children[i].getNode() instanceof Directory)
				((Directory) children[i].getNode()).accept(children[i].getName(), visitor);
		}
		visitor.visitedDirectory();
	}

	// Creators

	public Entry createDirectory(String name) {
		if (containsEntry(name)) {
			System.out.println("Error: Es existiert bereits einen Eintrag mit dem Namen'" + name + "'!");
			return null;
		}
		Entry dir = new Entry(name, new Directory());
		addEntry(dir);
		return dir;
	}

	public Entry createFile(String name, String content) {
		if (containsEntry(name)) {
			System.out.println("Error: Es existiert bereits einen Eintrag mit dem Namen'" + name + "'!");
			return null;
		}
		File f = new File();
		f.writeContent(content);
		Entry file = new Entry(name, f);
		addEntry(file);
		return file;
	}

	public Entry createHardlink(String name, Entry entry) {
		if (containsEntry(name)) {
			System.out.println("Error: Es existiert bereits einen Eintrag mit dem Namen'" + name + "'!");
			return null;
		}
		Entry link = new Entry(name, entry.getNode());
		addEntry(link);
		return link;
	}

	// Helpers

	private void addEntry(Entry entry) {
		Entry[] newChildren = new Entry[children.length + 1];
		for (int i = 0; i < children.length; i++)
			newChildren[i] = children[i];
		newChildren[children.length] = entry;
		children = newChildren;
	}
}
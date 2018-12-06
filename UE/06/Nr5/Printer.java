import java.lang.*;

public class Printer implements Visitor {
	public String curPath = "";

	public void visitFile(String name, File file) {
		System.out.println(file.getLastModified() + " " + curPath + name);
		System.out.println("> " + file.readContent());
	}

	public void visitDirectory(String name, Directory directory) {
		curPath = curPath + name + "/";
		System.out.println(directory.getLastModified() + " " + curPath);
	}

	public void visitedDirectory() {
		curPath = curPath.substring(0, curPath.lastIndexOf("/")) + "/";
	}
}
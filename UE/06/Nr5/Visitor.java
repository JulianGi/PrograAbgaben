public interface Visitor 
{
	void visitFile(String name, File file);
	void visitDirectory (String name, Directory directory);
	void visitedDirectory();
}
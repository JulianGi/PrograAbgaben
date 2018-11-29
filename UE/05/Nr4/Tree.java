public class Tree
{
  private int label;
  
  private TreeList children;
  
  public Tree(int inputLabel, TreeList inputChildren){
    this.label = inputLabel;
    this.children = inputChildren;
  }
  
  //a)
  public int getLabel(){
    return label;
  }

  public void setLabel(int label){
    this.label = label;
  }

  public TreeList getChildren(){
    return children;
  }

  public void setChildren(TreeList children){
    this.children = children;
  }
  //b)

  /**
  * Method for outputting the Tree as a String.
  */
  public String toString(){
	  String tree = this.label + "->[";
	  
	  if (this.children != null) {
		  tree = tree + this.children.toString();
	  }
	  
	  tree = tree + "]";
	  return tree;
  }

  //c)


  /**
  * Method for getting the Trees hight.
  */
  public int branchingDegree(){
		if (children == null) return 0;
		return children.branchingDegree();
	}
	

  


  //d)
  
  /**
  * Method for looking up if a value is contained in a Tree.
  * @param toSearch The value to search the Tree for.
  */
  public boolean contains(int toSearch){
	  if (this.label == toSearch){
      return true;
    } 
	  if (this.children != null) {
		  return this.children.contains(toSearch); 
	  }
	  return false;
  }
  

  
  //e)
  


  /**
  * Method for building the Tree.
  * @param value The value of the Treenode.
  * @param children The children of the Treenode.
  */


  public static Tree buildTree(final int value, final Tree... children){
		return new Tree(value,new TreeList(children));
	}

  
  /**
  * Method for trying out some of the implemented commands.
  * @param args input strings from the console
  */


  public static void main(String[] args){
    Tree[] trees = {buildTree(1,buildTree(2),buildTree(3),buildTree(4)), buildTree(-1) , buildTree(4,buildTree(1,buildTree(1,buildTree(1,buildTree(1),buildTree(1),buildTree(1)),buildTree(1),buildTree(1))),buildTree(2),buildTree(2,buildTree(2))),
    buildTree(72, buildTree(27), buildTree(11), buildTree(54,buildTree(89,buildTree(10),buildTree(20),buildTree(42))), buildTree(23)),
    buildTree(54,buildTree(89,buildTree(10),buildTree(20),buildTree(42)))};
    


    for(Tree tree:trees){
      if(tree != null){
        String test = "";
        test = test + tree.toString() + "\n";
        test = test + "Branching Degree: " + tree.branchingDegree() + "\n";
        test = test + "2 contained: " + tree.contains(2) + "\n";
        test = test + "42 contained: " + tree.contains(42) + "\n";
        test = test + "1 contained: " + tree.contains(1) + "\n";
        System.out.print(test);
      }
    }
    
  }
  
}

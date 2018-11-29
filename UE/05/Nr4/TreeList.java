public class TreeList
{
  private TreeListElement head;
  
  public TreeList(){
    this.head = null;
  }
  
  //a)
  public TreeListElement getHead(){
    return head;
  } 

  public void setHead(TreeListElement head){
    this.head = head;
  }
  //b)
   public String toString() {
	  String treeList = "";
	  
	  if (this.head != null) {
		  treeList = treeList + (this.head).toString();
	  }
	  return treeList;
  }
  
  
  //c)
  public int branchingDegree(){
		if (head == null) return 0;
		return head.branchingDegree(0,0);
	}

  
  //d)
  public boolean contains(int toSearch){
    if(this.head != null)
    {
      return this.head.contains(toSearch);
    }
    return false;
  }

  //e)
  public TreeList(final Tree... elements){
		this.head = null;
		if (elements.length > 0) this.head = new TreeListElement(0,elements);
	}
	

  
}

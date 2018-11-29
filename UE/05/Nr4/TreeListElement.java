import java.util.Arrays;

public class TreeListElement{
  private Tree value;
    
  private TreeListElement next;

  /**
  * @param inputValue The value of the new TreeListElement
  * @param inputNext The next elements of the TreeListElement
  */
  public TreeListElement(Tree inputValue, TreeListElement inputNext){
    this.value = inputValue;
    this.next = inputNext;
  }
  
  //a)
  public Tree getValue(){
    return value;
  }

  public void setValue(Tree value){
    this.value = value;
  }

  public TreeListElement getNext(){
    return next;
  }

  public void setNext(TreeListElement next){
    this.next = next;
  }
  
  //b)
    public String toString() {
	  String treeListElement = "";
	  if (this.value != null) {
		  treeListElement = treeListElement + (this.value).toString();
	  }
	  if (this.next != null) {
		  treeListElement = treeListElement + "," + (this.next).toString();
	  }
	  return treeListElement;
  }
  
  
  //c)
  public int branchingDegree(int max, int now){
		final int maxPT = value.branchingDegree();
		if (maxPT > max) max=maxPT;
		now++;
		if (now > max) max = now;
		if (next == null) return max;
		final int maxN = next.branchingDegree(max, now);
		if (maxN > max) max = maxN;
		return max;
	}

  
  
  //d)
  public boolean contains(int toSearch){
	  if (this.value != null) {
		  if ((this.value).contains(toSearch)){
        return true;
      } 
	  }
	  if (this.next != null) {
		  if ((this.next).contains(toSearch)){
        return true;
      }
	  }
	  return false;
  }
  //e)
  public TreeListElement(final int i, final Tree... trees){
		this.value = trees[i];
		this.next = null;
		if (i+1<trees.length) this.next = new TreeListElement(i+1,trees);
	}

  
}

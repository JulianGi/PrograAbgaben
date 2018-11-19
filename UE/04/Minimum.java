import java.util.Arrays;
public class Minimum{
	public static int arrayMin(int[] inputArray){
		  //Ihr Code hier:
		  return helper(inputArray, 0, Integer.MAX_VALUE);
	}

	private static int helper(int[] inputArray, int counter, int min){
		if(inputArray == null || counter == inputArray.length){
			return min;
		}
		if(inputArray[counter] < min){
			min = inputArray[counter];
		}
		return helper(inputArray, counter+1, min);
	}
}

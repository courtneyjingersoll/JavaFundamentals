package algorithmproject;

import java.util.*;

public class BinarySearch {
	/** Binary Search
	 *  A binary search can only be performed on a sorted array.
	 *  Binary search compares the target value to the middle element of the array.
	 *  If they are not equal, the half in which the target cannot be contained is
	 *  removed, and the target is again compared to the middle element of the
	 *  remaining half.  This process is repeated until the element is found or
	 *  the target is not in the array.
	 *  
	 *  In the worst case, a binary search has a performance of O(log(n)).
	 */

	public static void main(String[] args) {
		
		int array[] = {10, 14, 8, 7, 2, 9};
		libArraysBinarySearch(array,10);
		libArraysBinarySearch(array,14);
		libArraysBinarySearch(array,8);
		libArraysBinarySearch(array,1);


	}
	
	public static void output(int target, int index) {
		if (index >= 0) {
	        System.out.println(" value " + target + " found at index " + index + "."); 
	    } else {
	    	System.out.println(" value " + target + " was not found."); 
	    }
	}
	
	public static void customBinarySearch(int[] array, int target) {
		int index = 0;
		boolean checkedZero = false;
		
		Arrays.sort(array);
		
		// TODO fix me -- not every position is checked before exiting.
		for(index = array.length/2; index >= 0 && index < array.length; ) {
			if (array[index] == target) {
				break;
			} else if (array[index] > target) {
				index /= 2;
				if (index == 0 && !checkedZero) { 
					checkedZero = true;
				}else {
					index = -1;
					break;
				}
			} else {
				index *= 1.5;
			}
		}
		
		output(target,index);

	}
	
	public static void libArraysBinarySearch(int[] array, int target) {
		int index = 0;
	    Arrays.sort(array); 
	  
	    index = Arrays.binarySearch(array, target); 
	    output(target,index);

	}
	
	public static void libCollectionsBinarySearch(List<Integer> list, int target) {
		int index = 0;
		Collections.sort(list);
		
		index = Collections.binarySearch(list, target);
		output(target,index);
		
	}

}

package algorithmproject;

public class GreedyAlgorithm {

	public static void main(String[] args) {
		
		int array[] = {10, 14, 8, 7, 2, 9};
		greedySearch(array,10);
		greedySearch(array,14);
		greedySearch(array,8);
		greedySearch(array,1);

	}
	
	
	public static void greedySearch(int[] array, int target) {
		int index;
		
		for(index = 0; index < array.length; index++) {
			if (array[index] == target) {
				break;
			}
		}
		
		if (index == array.length) { 
			index = -1; 
		}

		if (index >= 0) {
			System.out.println(" value " + target + " found at index " + index + "."); 
		} else {
			System.out.println(" value " + target + " was not found."); 
		}
	
	}

}

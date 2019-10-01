package mcdowell;

public class SortAndSearchQuestions {
	
	public static void main ( String[] args ) {
		
	}
	
	/* Question 1
	 *  You are given two sorted arrays, A and B, where A has a large 
	 *  enough buffer at the end to hold B.  Write a method to merge
	 *  B into A in sorted order. 
	 */
	
	/* Solution 1.A - hi
	 *  Array A can be filled by iterating backwards over its length.
	 *  Two separate indexes are set to track which element of A and B
	 *  was last compared -- the larger value is set into the finalized
	 *  Array A, and its index is updated to the next element.
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(1)
	 */
	private static void SortTogether( int aryA[], int aryB[], int lengthA, int lengthB ) {
		int idxA = lengthA-1;
		int idxB = lengthB-1;
		
		for ( int i = aryA.length - 1; i >= 0 ; i-- ) {
			if ( idxB < 0 ) { 			// B is out of elements
				break; 					// -- don't need to copy rest of array A into array A
			} else if ( idxA < 0 ) { 	// A is out of elements
				aryA[i] = aryB[idxB];	// -- finish copying array B into array A
				idxB--;
			} else if ( aryA[idxA] >= aryB[idxB] ) {
				aryA[i] = aryA[idxA];
				idxA--;
			} else {
				aryA[i] = aryB[idxB];
				idxB--;
			}
		}
	}
	
}

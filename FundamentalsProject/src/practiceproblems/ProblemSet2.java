package practiceproblems;

import java.util.*;

public class ProblemSet2 {

	public static void main(String[] args) {
		
		testActiveCellCompetition();
		testFindGCD();
		
	}
	
	
	
	//----------------------------------------------------------------------------------
	
	/* Question 1
	 *  A series of cells are active if set to 1 and inactive if set to 0, and their status
	 *  changes daily.  A cell becomes inactive on the following day if both adjacent cells
	 *  are either active or inactive.  Otherwise, the cell becomes active. The cells on
	 *  the each end of the series are assumed to have a permanently inactive neighbor on
	 *  the unoccupied side.
	 */
	public static List<Integer> activeCellCompetition(int[] cells, int days)
	{
		List<Integer> finalState = new ArrayList<Integer>();
		int savedState = 0;
		int lastIndex = 0;
		
		while ( days > 0 ){
			for ( int i=0; i < cells.length; i++ ){
				//save the original state of the current cell for its neighbor
				lastIndex = savedState;
				savedState = cells[i];
				
				if ( i == 0 ) {
					cells[i] = neighborCompare(0, cells[i+1]);
				} else if ( i == (cells.length-1) ) {
					cells[i] = neighborCompare(lastIndex, 0);
				} else {
					cells[i] = neighborCompare(lastIndex, cells[i+1]);
				}
			}
			days--;
		}
		
		for (int x : cells){
		    finalState.add(x);
		}
		
		return finalState;
	}
    
	//Neighbor Comparison -- performs the comparison
	private static int neighborCompare( int neighbor1, int neighbor2 )	{	
		if ( neighbor1 == neighbor2 ){
			return 0; //inactive
		} else {
			return 1; //active
		}
	}
	
	public static void testActiveCellCompetition() {
		int test[][] = { { 1, 0, 0, 0, 0, 1, 0, 0 }, { 1 },
						 { 1, 0, 0, 0, 0, 1, 0, 0 }, { 2 },
						 { 1, 0, 0, 0, 0, 1, 0, 0 }, { 3 },
						 { 1, 1, 1, 0, 1, 1, 1, 1 }, { 2 },};
		
		System.out.println("\nQuestion 1 :: Active Cell Competition");
		
		for ( int i = 0; i < test.length; i += 2 ) {
			System.out.println("  Cells " + arrToString(test[i]) + " after day " + test[i+1][0] + " becomes " + activeCellCompetition(test[i],test[i+1][0]).toString() );
		}
	}
	
	//----------------------------------------------------------------------------------
	
	/* Question 2
	 * 	Given a list of numbers, find the greatest common divisor (GCD) between them.
	 *  @param num - the size of the list
	 *  @param arr - the list of numbers
	 *  @return - the GCD of the numbers in the list
	 */
	public static int findGCD(int num, int[] arr){
		int gcd = 1;
		int smallest = 0;
		boolean divisible;
		
		if ( num < 1 ) return 0;  //error - array is empty
		if ( num == 1 ) return arr[0]; //array only has one element
		
		//find the smallest integer contained in the list
		smallest = arr[0];
		for ( int x = 1; x < num; x++ ) {
			if (arr[x] < 0) return 0; //error - array contains negative numbers
			if (arr[x] < smallest) smallest = arr[x];
		}
		if ( smallest == 1 ) return 1; //smallest gcd can only be 1
		
		//the highest gcd possible would be equal to the smallest number in the list
		gcd = smallest;
		while( gcd > 1 ){
			divisible = true;
			for ( int x : arr ){
				if ( ( x % gcd ) != 0 ){
					divisible = false;
					break;
				}
			}
			if ( !divisible ){
				gcd -= 1; 	//decrement the gcd and try again
			} else {
				return gcd; //gcd has been found
			}
		}
		
		return 1; //no better gcd could be found
	}
	
	public static void testFindGCD() {
		int test[][] = { { 4, 6, 8, 10 },
						 { 2, 3,4, 5, 6 },
						 { 18, 36, 72, 192} };
		
		System.out.println("\nQuestion 2 :: Find the GCD");
		for ( int i = 0; i < test.length; i++ ) {
			System.out.println("  The GCD of " + arrToString(test[i]) + " is " + findGCD(test[i].length,test[i]));
		}
		
	}
	
	public static String arrToString(int[] arr) {
		String outStr = "[";
		
		for ( int i = 0; i < arr.length - 1; i++ ) {
			outStr += " " + arr[i] + ",";
		}
		outStr += " " + arr[arr.length-1] + " ]";
		
		return outStr;
	}
	
}

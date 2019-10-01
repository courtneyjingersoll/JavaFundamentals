package mcdowell;

import java.util.*;

public class ArrayAndStringQuestions {

	public static void main ( String[] args ) {
		
		String testStr1[] = {"special","tacoCAT","welcome"};
		Boolean testValidate1[][] = { { true, true }, { true, false }, { false, false } };
		String testStr2[][] = { { "abcdefg","gfedcba" }, { "taco","cato" }, { "God","dog" }, 
							    { "hello","greetings" }, { "Beeb","Boop" }, { "aabbcc","aaabcc" } };
		Boolean testValidate2[] = { true, true, false, false, false, false };
		String testStr3[][] = { { "hello world  ","11" }, { "oh what a wonderful world         ","25" } };
		String testAnswers3[] = { "hello%20world", "oh%20what%20a%20wonderful%20world" };
		
		Question1Test(testStr1,testValidate1,false);
		Question2Test(testStr2,testValidate2,false);
		Question3Test(testStr3,testAnswers3,false);
		
	}
	
	/* Question 1
	 *  Implement an algorithm to determine if a string has all unique characters.
	 *  > Assumptions: 
	 *  	[1] The strings are based on ASCII characters (no Unicode)
	 *  	[2] The strings only contain letters [A-Z,a-z]
	 */
	
	/* Solution 1.A - Brute Force
	 *  Iterates over the string twice, comparing every letter to the rest of the letters
	 *   - Time Complexity: O(n^2) 
	 *   - Space Complexity: O(1)
	 *    > Notes: 
	 *        [1] Could argue a Time Complexity of O(1) for every solution, since our character set is 
	 *            limited to a constant max of n=52.
	 *        [2] Or if you didn't want to assume the cap of 52, you could say [with c = length of char set,
	 *     		  n = length of string] Time Complexity is O(min(c^2,n^2), and Space Complexity is O(c).
	 */
	private static boolean IsUnique ( String str, boolean ignoreCase ) {
		if ( str.length() > 52 ) {
			return false;
		}
		
		if ( ignoreCase ) {
			str = str.toUpperCase();
		}
		for ( int i = 0; i < str.length()-1; i++ ) {
			for ( int j = i + 1; j < str.length(); j++ ) {
				if ( str.charAt(i) == str.charAt(j) ) {
					return false;
				}
			}
		}
		return true;
	}
	private static boolean IsUnique(String str) {
		return IsUnique(str,false);
	}
	
	/* Solution 1.B - Sorted Array 
	 *   Creates a sorted character array, then compares each letter.  Immediately fail if a 
	 *   letter is equal to its adjacent neighbor.
	 *    - Time Complexity: O(n*log(n)) -- sorting: log(n), iteration: n
	 *    - Space Complexity: O(1)
	 */
	private static boolean IsUniqueSortedArray ( String str ) {
		char chars[] = new char[52];
		
		if ( str.length() > 52 ) {
			return false;
		}
		
		chars = str.toCharArray();
		Arrays.sort(chars);
		for ( int i = 0; i < chars.length-1; i++ ) {
			if ( chars[i] == chars[i+1] ) {
				return false;
			}
		}
		return true;
	}
	
	/* Solution 1.C - Hash Table
	 *  Creates a hash table with the characters as the keys.  Set a value for the key
	 *  when a new letter is encountered.  Immediately fail if the table already has a key set.
	 *  already contains a key.
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(1)
	 */
	 private static boolean IsUniqueHash ( String str ) {
		Hashtable<Character, Boolean> hashtable = new Hashtable<Character, Boolean>();
		 
		if ( str.length() > 52 ) {
			return false;
		}
		 
		for ( int i = 0; i < str.length(); i++ ) {
			if ( hashtable.containsKey(str.charAt(i)) ) {
				return false;
			}
			hashtable.put(str.charAt(i), true);
		 }
		 return true;
	}
	
	 /* Solution 1.D - Bit Vector
	 *  Creates a bit vector/bit array, where each bit represents a letter (A-Z,a-z). (*very similar to the boolean array)
	 *  Set the bit to 1 when a new letter is encountered.  Immediately fail if a bit is already set.
	 *  Each index represents a letter (A-Z,a-z).
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(1)
	 */
	private static boolean IsUniqueBitVector ( String str ) {
		long bitarray = 0; 						//set a result bit array to an initial value of zero
		int val;
		 
		if ( str.length() > 52 ) {
			return false;
		}
		 
		for ( int i = 0; i < str.length(); i++ ) {
			if ( str.charAt(i) < 'a' ) {
				val = str.charAt(i) - 'A';			//get current character's distance away from 'A' (a number between 0-50)
			} else {
				val = (str.charAt(i) - 'a') + 25; 	//get current character's distance away from 'a' (adjusted to be a number between 26-51)
			}
			val = 1 << val; 						//left-shift a bit according to the "distance" calculated (0-51)
			if ( (bitarray & val) > 0 ) { 			//check if this bit is already set in the result
				return false;
			} 
			bitarray |= val;              			//set the bit in the result bit array
		 }
		 return true;
	}
	
	/* Solution 1.E - Boolean Array 
	 *   Creates a flag array, where each index represents a letter (A-Z,a-z).  Set to true when 
	 *   a new letter is encountered.  Immediately fail if a letter is already flagged as true.
	 *      > Note: Array if size 58 because there are some special characters between our range of 
	 *              ASCII z (155) and ASCII A (65).  This could be reduced to 52 if space was important.
	 *   Time Complexity: O(n)
	 *   Space Complexity: O(1)
	 */
	private static boolean IsUniqueBooleanArray ( String str ) {
		boolean flag[] = new boolean[58];
		int val;
		
		if ( str.length() > 52 ) {
			return false;
		}
		
		for ( char c : str.toCharArray() ) {
			val = c - 'A';
			if ( flag[val] ) {
				return false;
			} else {
				flag[val] = true;
			}
		}
		return true;
	}
	
	/* Question 1 Test */
	private static void Question1Test ( String tests[], Boolean answers[][], Boolean debug ) {
		boolean results[] = new boolean[6];
		String word = "";
		
		System.out.println();
		System.out.println(" Question 1: IsUnique");
		for ( int i = 0; i<tests.length; i++ ) {
			word = tests[i];
			System.out.println(" > Test: \"" + word + "\", expected: " + answers[i][0] + " (or if case is ignored: " + answers[i][1] + ")");
			results[0] = IsUnique(word);
			results[1] = IsUnique(word,true); 
			results[2] = IsUniqueBooleanArray(word);
			results[3] = IsUniqueSortedArray(word);
			results[4] = IsUniqueHash(word);
			results[5] = IsUniqueBitVector(word);
			
			if ( debug ) {
				System.out.println("      Using a for-loop: " + results[0] + ", Expected: " + answers[i][0]);
				System.out.println("           Ignore case: " + results[1] + ", Expected: " + answers[i][1]);
				System.out.println("  Using a sorted array: " + results[2] + ", Expected: " + answers[i][0]);
				System.out.println("     Using a hashtable: " + results[3] + ", Expected: " + answers[i][0]);
				System.out.println("    Using a bit vector: " + results[4] + ", Expected: " + answers[i][0]);
				System.out.println(" Using a boolean array: " + results[5] + ", Expected: " + answers[i][0]);
				
			}
			
			if ( results[0] == answers[i][0] &&
				 results[1] == answers[i][1] &&
				 results[2] == answers[i][0] &&
				 results[3] == answers[i][0] &&
				 results[4] == answers[i][0] &&
				 results[5] == answers[i][0]) {
				System.out.println("   - Test passed!");
			} else {
				System.out.println("   - FAILED");
			}
		}
	}
	 
	/* Question 2
	 *  Given two strings, write a method to decide if one is a permutation of the other.
	 *  Permutation: A string that contains the same characters as another string, but possibly in a different order.
	 *  > Assumptions: 
	 *  	[1] The permutation is case sensitive (ie, "God" is not a permutation of "dog")
	 *  	[2] The strings only contain letters [A-Z,a-z] (no whitespace)
	 */
	
	/* Solution 2.A - Sorted Arrays
	 *  Sort the strings into two separate arrays, then compare the final arrays for any differences.
	 *   - Time Complexity: O(n*log(n)) -- sorting: log(n), iteration: n
	 *   - Space Complexity: O(n)
	 */
	private static boolean IsPermutationArray ( String str1, String str2 ) {
		char chars1[],chars2[];
		
		if ( str1.length() != str2.length() ) {
			 return false;
		}
		
		chars1 = str1.toCharArray();
		chars2 = str2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		for ( int i = 0; i < chars1.length; i++ ) {
			if ( chars1[i] != chars2[i] ) {
				return false;
			}
		}
		return true;
	}
	
	/* Solution 2.B - Tally Arrays 
	 *  Creates two int arrays, where each index represents a letter (A-Z,a-z).  Increment the tally
	 *  at that index whenever its letter is encountered, then compare the final tallies.
	 *      > Note: Array if size 58 because there are some special characters between our range of 
	 *              ASCII z (155) and ASCII A (65).  This could be reduced to 52 if space was important.
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(1)
	 */
	private static boolean IsPermutationTally ( String str1, String str2 ) {
		int[] tally1 = new int[58];
		int[] tally2 = new int[58];
		
		if ( str1.length() != str2.length() ) {
			 return false;
		}
		
		for ( int i = 0; i < str1.length(); i++ ) {
			tally1[str1.charAt(i)-'A'] += 1;
			tally2[str2.charAt(i)-'A'] += 1;
		}
		for ( int i = 0; i < tally1.length; i++ ) {
			if ( tally1[i] != tally2[i] ) {
				return false;
			}
		}
		
		return true;
	}
	
	/* Question 2 Test */
	private static void Question2Test ( String tests[][], Boolean answers[], Boolean debug ) {
		boolean results[] = new boolean[2];
		
		System.out.println();
		System.out.println(" Question 2: IsPermutation");
		for ( int i = 0; i < tests.length; i++ ) {
			System.out.println(" > Test: \"" + tests[i][0] + "\", \"" + tests[i][1] + "\", expected: " + answers[i]);
			
			results[0] = IsPermutationArray(tests[i][0],tests[i][1]);
			results[1] = IsPermutationTally(tests[i][0],tests[i][1]);
			
			if ( debug ) {
				System.out.print("    Using a sorted array: " + results[0]);
				System.out.print("     Using a tally array: " + results[1]);
			}
			
			if ( results[0] == answers[i] &&
				 results[1] == answers[i] ) {
					System.out.println("   - Test passed!");
				} else {
					System.out.println("   - FAILED");
				} 
		 }
	 }
	
	/* Question 3
	 *  Write a method to replace all spaces in a string with '%20'.  You may assume 
	 *  that the string has sufficient space at the end to hold the additional characters, 
	 *  and that you are given the "true" length of the string.
	 *   > For Java, use a character array so that the operation can be performed in place.
	 */
	
	/* Solution 3.A - Copy Array
	 *  Create a copy array that is large enough to replace an input array entirely
	 *  composed of spaces (ie, 3*InputSize).  Iterate over the input array up to the
	 *  given size, copying each character to the copy array. When a space is encountered, 
	 *  add "%20" to the copy instead. Finally, overwrite the input array with the copy 
	 *  array up to size (avoids changing the original size of the array).
	 *   - Time Complexity: O(n^2)
	 *   - Space Complexity: O(n)
	 */
	private static void URLifyString ( char[] input, int size ) {
		char[] copy = new char[size*3];
		int numSpaces = 0;
		int copyIdx = 0;
		
		// build copy with replacements
		for ( int i = 0; i < size; i++ ) {
			if ( input[i] == ' ') {
				copy[copyIdx] = '%';
				copy[copyIdx+1] = '2';
				copy[copyIdx+2] = '0';
				copyIdx += 3;
				numSpaces++;
			} else {
				copy[copyIdx] = input[i];
				copyIdx += 1;
			}
		}
		
		// adjust size
		size = size + numSpaces*2;
		
		// re-copy into input string
		for ( int i = 0; i < size; i++ ) {
			input[i] = copy[i];
		}
	}
	
	/* Solution 3.A - Backwards Pass
	 *  First iterates through the input array to count the number of spaces
	 *  and calculate the new array size for the replacements. Then traverses
	 *  the input array again, but this time backwards, shifting each character
	 *  to its new location in the updated array.
	 *   - Time Complexity: O(n^2)
	 *   - Space Complexity: O(1)
	 */
	private static void URLifyStringBackwards ( char[] input, int size ) {
		int numSpaces = 0;
		int newIndex = 0;
		
		// get the number of spaces
		for ( int i = 0; i < size; i++ ) {
			if ( input[i] == ' ') {
				numSpaces++;
			}
		}
		
		// add 2 extra characters to the size for each space,
		// then shift by one to be in index range (zero-based array)
		newIndex = size + numSpaces*2 - 1;
		
		// going backwards, shift the characters to their new positions
		for ( int i = size-1; i >= 0; i-- ) {
			if ( input[i] == ' ') {
				input[newIndex-2] = '%';
				input[newIndex-1] = '2';
				input[newIndex] = '0';
				newIndex -= 3;
			} else {
				input[newIndex] = input[i];
				newIndex -= 1;
			}
		}
	}
		
	/* Question 3 Test */
	private static void Question3Test ( String tests[][], String answers[], Boolean debug ) {
		char results[][] = new char[10][];
		int size;
		
		System.out.println();
		System.out.println(" Question 3: URL-ify");
		for ( int i = 0; i < tests.length; i++ ) {
			System.out.println(" > Test: \"" + tests[i][0] + "\", expected: " + answers[i]);
			size = Integer.parseInt(tests[i][1]);
			results[0] = tests[i][0].toCharArray();
			results[1]= tests[i][0].toCharArray();
			URLifyString(results[0], size);
			URLifyStringBackwards(results[1], size);
			
			if ( debug ) {
				System.out.print("    Using a copy array: " + String.valueOf(results[0]) + "\n"); 
				System.out.print("    Backwards shifting: " + String.valueOf(results[1]) + "\n");
			}
			
			if ( answers[i].equals(String.valueOf(results[0]).trim()) &&
				 answers[i].equals(String.valueOf(results[1]).trim()) ) {
					System.out.println("   - Test passed!");
				} else {
					System.out.println("   - FAILED");
				} 
		 }
	 }
}










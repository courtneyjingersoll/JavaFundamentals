package practiceproblems;

import java.util.*;

public class ProblemSet1 {
	
	public static void main(String[] args) {
		testTwoSum();
		testAddTwoNumbers();
		testLongestUniqueSubstring();
		testFindMediantOfSortedArrays();
	}
	
	/* Question 1
	 *  Given an array of integers, return indices of the two numbers such that they 
	 *  add up to a specific target.
	 *  
	 *  You may assume that each input would have exactly one solution, and you 
	 *  may not use the same element twice.
	 */
	public static int[] twoSum(int[] nums, int target) {
		int indicies[] = new int[2];
		for ( int i = 0; i < nums.length-1; i++ ) {
			for ( int j = i+1; j < nums.length; j++ ) { 
				if ( (nums[i] + nums[j]) == target ) {
					indicies[0] = i;
					indicies[1] = j;
					return indicies;
				}
			}
		}
		return null;
	}
	
	public static void testTwoSum() {
		int numbers[] = {1, 1, 2, 3, 5, 7, 9, 11};
		int targets[] = {2, 3, 7, 8, 16, 20};
		int results[];
		
		System.out.println("\nQuestion 1 :: Two Sum");
		for ( int x: targets ) {
			results = twoSum(numbers,x);
			System.out.println("  " + numbers[results[0]] + " + " + numbers[results[1]] + " = " + x );
		}
	}
	
	//----------------------------------------------------------------------------------
	
	/* Question 2
	 * You are given two non-empty linked lists representing two non-negative integers. 
	 * The digits are stored in reverse order and each of their nodes contain a single 
	 * digit. Add the two numbers and return it as a linked list.
	 * 
	 * You may assume the two numbers do not contain any leading zero, 
	 * except the number 0 itself.
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int val1, val2, sum, carry;
		ListNode result = new ListNode(0);
		ListNode p1, p2, pr; // pointers
		
		carry = 0;
		p1 = l1; 
		p2 = l2; 
		pr = result;
		
		while ( p1 != null || p2 != null || carry != 0) {
			
			val1 = p1 != null ? p1.val : 0;
			val2 = p2 != null ? p2.val : 0;
			
			sum = val1 + val2 + carry;
			pr.next = new ListNode(sum % 10);
			
			if ( sum >= 10 ) carry = 1;
			else carry = 0;
			
			p1 = p1 != null ? p1.next : null; 
			p2 = p2 != null ? p2.next : null; 
			pr = pr.next;
		}
		
		return result.next;
	}
		
	public static void testAddTwoNumbers() {
		int data[][] = { { 3, 4, 2 },  {9, 6, 7 },
						 { 7, 4 }, { 1, 2, 1},
						 { 1, 0, 0, 0, 0 }, { 3, 4, 5 } };
		ListNode nodes[] = new ListNode[6];
		ListNode out[] = new ListNode[3];
		int outIdx = 0;
		
		for ( int i = 0; i < data.length; i++ ) {
			nodes[i] = buildListNodeNumber(data[i]);
		}
		
		System.out.println("\nQuestion 2 :: Add Two Numbers");
		
		for ( int i = 0; i < nodes.length; i += 2 ) {
			out[outIdx] = addTwoNumbers(nodes[i], nodes[i+1]);
			System.out.println("  " + nodes[i].toString() + " + " + nodes[i+1].toString() 
							 + " = " + out[outIdx].toString());
			outIdx++;
		}
		
		
	}
	
	public static ListNode buildListNodeNumber( int[] num) {
		ListNode list = new ListNode(0);
		ListNode pointer;
		
		pointer = list;
		for ( int i = num.length-1; i >= 0; i-- ) {
			pointer.next = new ListNode(num[i]);
			pointer = pointer.next;
		}
		return list.next;
	}
	
	//----------------------------------------------------------------------------------
	
	/* Question 3
	 *  Given a string, find the length of the longest substring without repeating characters.
	 *  Example: input of "pwwkew" would return 3 for "wke"
	 */
	public static int LongestUniqueSubstring(String input) {
		int longest = 0, startIdx = 0, endIdx = 0, checkIdx = 0;
		Hashtable<Character,Boolean> characters = new Hashtable<Character,Boolean>();
		
		while ( startIdx < input.length() ) {
			//Begin building the substring
			while ( endIdx < input.length() ) {
				if ( !characters.containsKey(input.charAt(endIdx)) ) {
					characters.put(input.charAt(endIdx), true);
					endIdx++;
				} else {
					break;
				}
			}
			
			//Check new substring against size
			if ( characters.size() > longest ) {
				longest = characters.size();
			}
			
			//Check if end of input string
			if ( endIdx >= input.length() ) return longest;
			
			//Move start of substring up to following unique character
			// -- Remove characters from hashtable that will no longer be in substring
			for ( checkIdx = startIdx; checkIdx < endIdx; checkIdx++ ) {
				if ( input.charAt(checkIdx) != input.charAt(endIdx) ) {
					characters.remove(input.charAt(checkIdx));
				} else {
					break;
				}
			}
			startIdx = checkIdx + 1;
			endIdx++;
		}
		
		return longest;
	}
	
	public static void testLongestUniqueSubstring() {
		String tests[] = { "abcabcbb" , "x", "bbbbb", "pwwkew" , "ccchilled", "xyzabc", "bbarks" };  
		
		System.out.println("\nQuestion 3 :: Longest Unique Substring");		
		for ( int i = 0; i < tests.length; i++ ) {
			System.out.println("  " + tests[i] + " --> " + LongestUniqueSubstring(tests[i]));
		}
	}
	
	//----------------------------------------------------------------------------------
	
	/* Question 4
	 *  There are two sorted arrays, nums1 and nums2, of size m and n respectively.
	 *  Find the median of the two sorted arrays.
	 */
	public static double findMedianOfSortedArrays( int nums1[], int nums2[] ) {
		int idx1 = 0, idx2 = 0;
		int total = nums1.length + nums2.length;
		int medianIdx = (total) / 2;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for ( int i = 0; i <= medianIdx; i++ ) {
			if ( idx1 >= nums1.length ) { 			// nums1 is out of elements
				numbers.add(nums2[idx2]);
				idx2++;
			} else if ( idx2 >= nums2.length ) {	// nums2 is out of elements
				numbers.add(nums1[idx1]);
				idx1++;
			} else if ( nums1[idx1] <= nums2[idx2] ) {
				numbers.add(nums1[idx1]);
				idx1++;
			} else {
				numbers.add(nums2[idx2]);
				idx2++;
			}
		}
		
		if ( total % 2 == 0 ) {
			return ( numbers.get(medianIdx) + numbers.get(medianIdx-1) ) / 2.0;
		} else {
			return numbers.get(medianIdx);
		}
	}
	
	public static void testFindMediantOfSortedArrays() {
		int tests[][] = { { 1, 3 } , { 2 } ,		// median 2
						  { 1, 2 } , { 3, 4 } ,		// median 2.5
						  { 1, 3, 5 } , { 2, 4 } ,	// median 3
						  { } , { 1, 2, 3} ,		// median 2
						  { } , { 2, 3 },			// median 2.5
						  { 3, 4 } , { },			// median 3.5
						  { 5 } , { 1, 2, 3, 4 } }; // median 3
		
		System.out.println("\nQuestion 4 :: Find Meidan of Sorted Arrays");
		int count = 0;
		for ( int i = 0; i < tests.length; i+=2 ) {
			System.out.println("  Test "  + ++count + " --> " + findMedianOfSortedArrays(tests[i],tests[i+1]));
		}
	}
}

// --------------------------------------------------------------------------------------

class ListNode {
	int val;
	ListNode next;
	
	ListNode ( int x ) {
		val = x;
	}
	
	public String toString() {
		if ( next != null ) {
			return next.toString() + val;
		}else {
			return String.valueOf(val);
		}
	}
	
	
}
package mcdowell;

import java.util.*;

public class LinkedListQuestions {
	
	public static void main ( String[] args ) {
		LinkedList<String> testList1 = new LinkedList<String>();
		testList1.add("hello");	 testList1.add("greetings");	testList1.add("hiya");	testList1.add("hey");
		testList1.add("hello");	 testList1.add("hey");			testList1.add("hey");	testList1.add("welcome");
		int test1Answer = 5;
		int test2Kths[] = { -1, 0, 2, 5, 8, 13 };
		int test2Answers[] = { -1, 7, 5, 2, -1, -1 };
		int test3Indexes[] = { 2, 4, 2 };
		LinkedList<String> test3Answers = CopyLinkedList(testList1);
		for ( int i : test3Indexes ) { test3Answers.remove(i); }
		 
		
		Question1Test(testList1, test1Answer, true);
		Question2Test(testList1, test2Kths, test2Answers, true);
		Question3Test(testList1, test3Indexes, test3Answers, true);
	}
	
	/* Copy Linked List
	 *  Creates an independent copy of a linked list.
	 */
	private static LinkedList<String> CopyLinkedList ( LinkedList<String> list ) {
		LinkedList<String> copy = new LinkedList<String>();
		ListIterator<String> listItr = list.listIterator();
		
		while ( listItr.hasNext() ) {
			copy.add(listItr.next().toString());
		}
		
		return copy;
	}
	
	/* Question 1
	 *  Write code to remove duplicates from an unsorted linked list.
	 */
	
	/* Solution 1.A - Temporary Buffer
	 *  Store every value as a key in a hashtable.  If a key is already set within the
	 *  hashtable, we know it's a duplicate. Allows duplicates to be removed in one pass.
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(n)
	 */
	private static void RemoveDuplicates ( LinkedList<String> list ) {
		Hashtable<String,Boolean> values = new Hashtable<String,Boolean>();
		
		for ( int i = 0; i < list.size(); i++ ) {
			if ( list.get(i) == null ) {
				break;
			} else {
				String temp = list.get(i);
				if ( !values.containsKey(temp) ) {
					values.put(temp, true);
				} else {
					list.remove(i);
					i--;
				}
			}
		}
		
	}
	
	/* Solution 1.B - No Buffer
	 *  Sort the list, then compare against neighboring nodes for duplicates.
	 *  without using a temporary buffer.
	 *   - Time Complexity: O(n*log(n))
	 *   - Space Complexity: O(1)
	 */
	private static void RemoveDuplicatesNoBuffer ( LinkedList<String> list ) {
		list.sort(null);
		
		for ( int i = 1; i < list.size(); i++ ) {
			if ( list.get(i) == null ) { break; }		//since nodes are being removed as we iterate, we may need to end the loop early.
			if ( list.get(i).equals(list.get(i-1)) ) {
				list.remove(i);		// removing a node updates the indexes of the following nodes immediately
				i--;				// so we need to similarly update our loop-index to avoid skipping a node
			}
		}		
	}
	
	/* Solution 1.C - No Buffer and No Sorting
	 *  Compare each node to the nodes that link after it to check for duplicates.
	 *   - Time Complexity: O(n^2)
	 *   - Space Complexity: O(1)
	 */
	private static void RemoveDuplicatesNoSort ( LinkedList<String> list ) {
		String value = "";
		
		for ( int i = 0; i < list.size()-1; i++ ) {
			if ( list.get(i) == null ) { break; }    //since nodes are being removed as we iterate, we may need to end the loop early.	
			value = list.get(i);
			for ( int j = i + 1; j < list.size(); j++ ) {
				if ( list.get(j) == null ) { break; }
				if ( value.equals(list.get(j)) ) {
					list.remove(j);		// removing a node updates the indexes of the following nodes immediately
					j--;				// so we need to similarly update our loop-index to avoid skipping a node
				}
			}
		}
	}
	
	/* Question 1 Test */
	private static void Question1Test ( LinkedList<String> test, int answer, Boolean debug ) {
		LinkedList<String> test1 = CopyLinkedList(test);
		LinkedList<String> test2 = CopyLinkedList(test);
		LinkedList<String> test3 = CopyLinkedList(test);
		
		System.out.println();
		System.out.println(" Question 1: RemoveDuplicates");
		System.out.println(" > Test: " + test.toString() + " > size of " + test.size());
		RemoveDuplicates(test1);
		RemoveDuplicatesNoBuffer(test2);
		RemoveDuplicatesNoSort(test3);
		
		if ( debug ) {
			System.out.println("      Using a buffer: " + test1.toString() + " > size of " + test1.size());
			System.out.println("   Using a no buffer: " + test2.toString() + " > size of " + test2.size());
			System.out.println("   No buffer or sort: " + test3.toString() + " > size of " + test3.size());
		}
		
		if ( test1.size() == answer &&
			 test2.size() == answer &&
			 test3.size() == answer ) {
			System.out.println("   - Test passed!");
		} else {
			System.out.println("   - FAILED");
		}
	}
	
	/* Question 2
	 *  Find the k'th to last element of a singly linked list.
	 *  > Assumptions: 
	 *  	[1] Returning the 0th to last element is equivalent to returning the last element.  This means that,
	 *  		for a linked list of size n, attempting to return the nth to last element fails (it's out-of-bounds).
	 *   > Notes:
	 *     [1] Java does not have direct pointers, like in C, so using list iterators instead.
	 *     [2] List iterators cannot be duplicated, so each "pointer" needs to be its own iterator object.
	 *       	>> passing in the LinkedList object as a parameter to make the iterators, but then not allowed to use the LinkedList object!
	 */
	
	/* Solution 2.A - Two Pointers
	 *  Iterate through the list with two pointers, keeping one k nodes ahead of the other.
	 *  Returns the index of kth node, or -1 if kth node could not be found.
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(1) 	
	 */
	private static int FindKthToLast ( LinkedList<String> list, int k, boolean debug ) {
		ListIterator<String> listItr = list.listIterator();
		ListIterator<String> kthItr = list.listIterator();
		int count = 0;
		String valueK = "";
		int indexK = 0;
		
		
		if ( k < 0 ) { // Do not allow a negative k value.
			if ( debug ) { System.out.println("    Negative numbers are not allowed"); }
			return -1;
		}
		
		while ( listItr.hasNext() ) {
			if ( count >= k ) {
				valueK = kthItr.next();
				indexK = count - k;			
			}
			count++;
			listItr.next();
			;
		}
		
		if ( count <= k ) { // The kth node was never found - the list is too short. 
			if ( debug ) { System.out.println("    List is too short - cannot find " + k + "th to last element in list of size " + count); }
			return -1;
		}
		
		if ( debug ) { System.out.println("    Element located " + k + "th away from the end is: " + valueK + " [at index " + indexK + "]"); }
		return indexK;
	}
	
	/* Solution 2.B - Recursion [Discussion]
	 *  Alternatively, a recursive function could be implemented which calls itself
	 *  to iterate to the end of the list, then backs out while counting up to k.
	 *  Unfortunately, if a count is being returned, a pointer to the node itself cannot
	 *  be directly returned as well in Java.  A language which allows parameters to
	 *  be passed by reference could implement this.  For Java, a wrapper class could be
	 *  created to combine the pointer and the count together, and return the class.
	 *   - Time Complexity: O(n)
	 *   - Space Complexity: O(n) 
	 */
	
	/* Question 2 Test */
	private static void Question2Test ( LinkedList<String> test, int indexes[], int answers[], Boolean debug ) {		
		int result;
		boolean testPassed = true;
		
		System.out.println();
		System.out.println(" Question 2: FindKthToLast");
		System.out.println(" > Test: " + test.toString() + " > size of " + test.size());
		
		for ( int i = 0; i < indexes.length; i++ ) {
			result = FindKthToLast(test,indexes[i],debug);
			if ( result != answers[i] ) {
				testPassed = false;
			}
		}
		
		if ( testPassed ) {
			System.out.println("   - Test passed!");
		} else {
			System.out.println("   - FAILED");
		}
	}
	
	/* Question 3
	 *  Given a node located somewhere in the middle of a linked list (ie, not the first and not the last),
	 *  remove the node from the linked list.
	 *   > Notes:
	 *     [1] Java does not have direct pointers, like in C, so using list iterators instead.
	 *     [2] List iterators cannot be duplicated, so each "pointer" needs to be its own iterator object.
	 *       	>> passing in the LinkedList object as a parameter to make the iterators, but then not allowed to use the LinkedList object!
	 */
	
	/* Solution 3.A - Two Pointers
	 * Using two pointers, one a node ahead of the other, we can overwrite node k with the value stored in k+1,
	 * essentially "deleting" k.  When we get to the end of the list, remove the now "excess" node.
	 *  - Time Complexity: O(n)
	 *  - Space Complexity: O(1) 
	 */
	private static void DeleteMiddleNode ( LinkedList<String> list, int nodeIndex ) {
		ListIterator<String> listItr = list.listIterator(nodeIndex);
		ListIterator<String> prevItr = list.listIterator(nodeIndex);
		
		
		listItr.next();
		while ( prevItr.hasNext() ) {
			prevItr.next();					 // need to iterate prevItr to the next element here because...
			if ( listItr.hasNext() ) {
				prevItr.set(listItr.next()); // ...set changes the last element prevItr *was* pointing at
			} else {
				prevItr.remove();			 // ...remove deletes the last element prevItr *was* pointing at
			}
		}
		
	}
	
	/* Question 3 Test */
	private static void Question3Test ( LinkedList<String> test, int indexes[], LinkedList<String> answer, Boolean debug ) {		
		LinkedList<String> test1 = CopyLinkedList(test);
		
		System.out.println();
		System.out.println(" Question 3: DeleteMiddleNode");
		System.out.println(" > Test: " + test1.toString() + " > size of " + test1.size());
		for ( int index : indexes ) {
			DeleteMiddleNode(test1,index);
			if (debug) { System.out.println("    Removing index " + index + ": " + test1.toString() + " > size of " + test1.size()); }
		}

		
		if ( answer.toString().equals(test1.toString()) ) {
			System.out.println("   - Test passed!");
		} else {
			System.out.println("   - FAILED");
		}
	}
	
}

package questions;

import java.util.*;

public class StackAndQueueQuestions {

	public static void main(String[] args) {
		
		int testData[][] = { { 6, 5, 4, 3, 2 , 1 },
							 { 7, 5, 5, 2, 6, 2, 1, 3 },
							 { 13, 50, 13, 7, 0, -7, -12 } };
		int testOrder[][] = {  { 6, 4 },
							   { 3, 2, 5, 3, 0, 3 },
							   { 2, 0, 3, 1, 2, 1, 0, 5 } };
		int test3Order[][] = { { 3, 0, 2, 0, 3, 2, 1, 2, 0, 1, 1, 2},
							   { 4, 0, 6, 1, 2, 0, 2, 2, 1, 1, 1, 4 }, 
							   { 2, 0, 5, 2, 2, 1, 0, 2, 2, 3, 1, 2, 1, 2, 1, 3} };
		String test2Ans[][] = { { "1", "5" },
								{ "5", "7", "1", "2", "2", "--" }, 
								{ "13", "13", "0", "7", "-12", "-7", "-7", "--" } };
		String test3Ans[][] = { { "4 5", "2 3"},
								{ "2 6", "2", "3 1 5 5"},
								{ "7", "-12", "50 13", "0 -7 13" } };
		String test4Ans[][] = { { "6 5 4 3" },
								{ "7 5", "5 2 6", "2 1 3" }, 
								{ "", "13", "50", "13 7 0 -7 -12" } };
		
		Question2Test(testData, testOrder, test2Ans, false);
		Question3Test(testData, test3Order, test3Ans, false);
		Question4Test(testData,testOrder, test4Ans, false);
	}
	
	/* Question 1
	 *  Describe how a single array could be used to implement three stacks.
	 */
	
	/* Solution 1.A - Dynamic Size [Discussion]
	 *  If the max size of the three stacks is not known, a dynamic array can be made.
	 *  Then the ArrayIndex%3 would divide the elements into the separate stacks.
	 *    > Example:
	 *    		[0] - stack 1, position 1   (0 % 3 = 0)
	 *    		[1] - stack 2, position 1   (1 % 3 = 1)
	 *    		[2] - stack 3, position 1   (2 % 3 = 2)
	 *    		[3] - stack 1, position 2   (3 % 3 = 0)
	 *    		[4] - stack 2, position 2	(4 % 3 = 1)
	 *    		 ...
	 *    		[53] - stack 3, position 18 (53 % 3 = 2)
	 *    		 ...
	 *  > Pros: The size of the stacks can change, and the indexes assigned to each stack are constant.
	 *  > Cons: If one stack is much larger than the other stack, the array would need to expand
	 *  		to fit the large array, but the majority of the indexes added would remain empty. 
	 */
	
	/* Solution 1.B - Known Equal Sizes [Discussion]
	 *  If the size of the three stacks is known, an array of size 3*Size
	 *  can be made, and each of the stacks can start at Size*[0,1,2].
	 *      > Example: if Size = 5
	 *    		[0] - stack 1, position 1  (5 * 0 = 0)
	 *    		[1] - stack 1, position 2
	 *    		 ...
	 *    		[4] - stack 1, position 5
	 *    		[5] - stack 2, position 1  (5 * 1 = 5)
	 *    		 ...
	 *    		[9] - stack 2, position 5
	 *    		[10] - stack 3, position 1  (5 * 2 = 10)
	 *    		 ...
	 *    		[14] - stack 3, position 5
	 *  > Pros: The indexes assigned to each stack are constant - code written to access
	 *  		elements of one stack would work for another stack with static changes.
	 *  		Elements belonging to the same stack are next to each other.
	 *  > Cons: The stacks must all be the same max size. The max size cannot be changed
	 * 			unless the array is expanded and the elements are re-copied into their 
	 * 			new indexes.
	 */
	
	/* Solution 1.C - Known Unequal Sizes [Discussion]
	 *  If the sizes of the three stacks is known, but not equal, then we could just
	 *  make each stack equal to the largest size and use the previous solution.
	 *  But to conserve space, we can make an array of size Size1+Size2+Size3, and each
	 *  of the stacks would start at Stack1 => 0, Stack2 => Size1, and Stack3 => Size1+Size2
	 *      > Example: if Size1 = 5, Size2 = 15, Size 3 = 10
	 *    		[0] - stack 1, position 1
	 *    		[1] - stack 1, position 2
	 *    		 ...
	 *    		[4] - stack 1, position 5
	 *    		[5] - stack 2, position 1  (Size1 = 5)
	 *    		 ...
	 *    		[19] - stack 2, position 15
	 *    		[20] - stack 3, position 1  (Size1 + Size2 = 5 + 15 = 20)
	 *    		 ...
	 *    		[29] - stack 3, position 10
	 *  > Pros: The indexes assigned to each stack are variable - code written to access
	 *  		elements of one stack would work for another stack with size-flexible changes.
	 *  		Elements belonging to the same stack are next to each other.
	 *  > Cons: The max size for each stack cannot be changed unless the array is expanded
	 *  		and the elements are re-copied into their new indexes.
	 */
	
	/* Solution 1.D - Sizes Change [Discussion]
	 *  If the max size between the three stacks is known, but the size of the stacks are
	 *  allowed to change or expand into unused indexes, then we could use a circular array.
	 *  The array would be an initial size of size MaxSize, and the stacks could being at 
	 *  even divisions in the array, but we could shift the starting indexes of the stacks over
	 *  over time with the last index of the array wrapping back to index 0 to continue a stack.
	 *      > Example: suppose the stacks have a shared size of 15 and have shifted
	 *      			  such that the starting index for each stack is now 
	 *      			  Stack1 => 8, Stack2 => 14, Stack3 => 5
	 *    		[0] - stack 2, position 2
	 *    		[1] - stack 2, position 3
	 *    		 ...
	 *    		[4] - stack 2, position 6
	 *    		[5] - stack 3, position 1
	 *    		 ...
	 *    		[7] - stack 3, position 3
	 *    		[8] - stack 1, position 1
	 *    		 ...
	 *    		[13] - stack 1, position 6
	 *    		[14] - stack 2, position 1
	 *  > Pros: The indexes assigned to each stack are variable - code written to access
	 *  		elements of one stack would work for another stack with index-independent
	 *  		and size-flexible changes. Elements belonging to the same stack are next 
	 *  		to each other.
	 *  > Cons: The combined size of the stacks cannot exceed the max size of the array
	 *  		unless the array is expanded and the elements are re-copied into their 
	 *  		new indexes.
	 */
	
	/* Question 2
	 * 	Design a stack class that, in addition to push and pop, has a function that
	 * 	would return the minimum element included in a stack.
	 */
	
	/* [Solution 2.A is at the bottom] */
	
	/* Question 2 Test */
	private static void Question2Test ( int testData[][], int testOrder[][], String answers[][], Boolean debug ) {
		MinStack testStack;
		int answrIdx, dataIdx, data;
		String minStr = "";
		boolean testPassed;
		
		System.out.println();
		System.out.println(" Question 2: Minimum Value in Stack");
		for ( int i = 0; i < testData.length; i++ ) {
			//Begin new test
			System.out.print("  Test #" + (i+1));
			testStack = new MinStack();
			testPassed = true;
			answrIdx = 0;
			dataIdx = 0;
			data = 0;
			
			// testOrder indicates how many times a push or pop should be executed
			// with even-numbered indexes as pushes, odd-numbered indexes as pops
			for ( int j = 0; j < testOrder[i].length; j++ ) {				
				if( j % 2 == 0 ) {
					System.out.print("\n  > Push x" + testOrder[i][j] + ":");
					for (int k = 0; k < testOrder[i][j]; k++) {
						data = testData[i][dataIdx];
						System.out.print(" " + data);
						testStack.push(data);
						dataIdx++;
					}
					minStr = Integer.toString(testStack.min());
					if ( debug ) {
						 System.out.print("\n    >> Stack Contents: " + testStack.toString() + ", min: " + minStr);
					} else {
						 System.out.print(" (min: " + minStr + ")");
					}
				} else {
					System.out.print("\n  > Pop x" + testOrder[i][j] + ":");
					for ( int k = 0; k < testOrder[i][j]; k++ ) {
						data = testStack.pop();
						System.out.print(" " + data);
					}
					minStr = testStack.isEmpty() ? "--" : Integer.toString(testStack.min());
					if ( debug ) {
						 System.out.print("\n    >> Stack Contents: " + testStack.toString() + ", min: " + minStr);
					 } else {
						 System.out.print(" (min: " + minStr + ")");
					 }
				}
				
				if ( !answers[i][answrIdx].equals(minStr) ) {
					testPassed = false;
				}
				answrIdx++;
			}
			
			if ( testPassed ) {
				System.out.println("\n   - Test passed!");
			} else {
				System.out.println("\n   - FAILED");
			}
			System.out.println();
		}
	}
	
	/* Question 3
	 *  Create a class that implements a set of stacks, where one is filled to a 
	 *  max capacity, and then another stack is automatically started.
	 *  Push and pop should behave identically to a single stack.
	 *  Additionally, add a function popAt, which will perform a pop operation
	 *  on a specific sub-stack.
	 */
	
	/* [Solution 3.A is at the bottom] */
	
	/* Question 3 Test */
	private static void Question3Test ( int testData[][], int testOrder[][], String answers[][], Boolean debug ) {
		SetOfStacks testStacks;
		int caseIdx, popIdx, answrIdx, dataIdx, data;
		int indent = 8;
		String popStr = "";
		boolean testPassed;
		
		System.out.println();
		System.out.println(" Question 4: Queue with Two Stacks");
		for ( int i = 0; i < testData.length; i++ ) { //TODO finish testing
			//Begin new test
			System.out.print("  Test #" + (i+1));
			// first element in testOrder indicates the sub-stack capacity
			testStacks = new SetOfStacks(testOrder[i][0]);
			System.out.print(" (capacity = " + testOrder[i][0] + ")");
			testPassed = true;
			answrIdx = 0;
			dataIdx = 0;
			data = 0;
			
			// the rest of testOrder first indicate if we're doing a push (0), pop (1), or popAt (2),
			// > push and pop: the next index indicates how many times to do the call 
			// > popAt: the next index indicates which stack to popAt, and the index after that
			//			indicates how many times to do the call.
			//  (*Note: Each case is responsible for adjusting the counter to the data it needs.)
			for ( int j = 1; j < testOrder[i].length; j++ ) {	
				caseIdx = testOrder[i][j];
				popStr = "";
				
				switch ( caseIdx ) {
					case 0:
						j += 1;
						System.out.print("\n  > Push x" + testOrder[i][j] + ": ");
						for ( int k = 0; k < testOrder[i][j]; k++ ) {
							data = testData[i][dataIdx];
							System.out.print(data + " ");
							testStacks.push(data);
							dataIdx++;
						}
						if ( debug ) {
							 System.out.print("\n    >> Set Contents: " + testStacks.toString(indent));
						}
						break;
					case 1:
						j += 1;
						System.out.print("\n  > Pop x" + testOrder[i][j] + ": ");
						for ( int k = 0; k < testOrder[i][j]; k++ ) {
							popStr += testStacks.pop() + " ";
						}
						System.out.print(popStr);
						if ( debug ) {
							 System.out.print("\n    >> Set Contents: " + testStacks.toString(indent));
						}
						
						if ( !answers[i][answrIdx].equals(popStr.trim()) ) {
							testPassed = false;
						}
						answrIdx++;
						break;
					case 2:
						popIdx = testOrder[i][j+1];
						j += 2;
						System.out.print("\n  > Pop At Stack #" + popIdx + " x" + testOrder[i][j] + ": ");
						for ( int k = 0; k < testOrder[i][j]; k++ ) {
							popStr += testStacks.popAt(popIdx) + " ";
						}
						System.out.print(popStr);
						if ( debug ) {
							 System.out.print("\n    >> Set Contents: " + testStacks.toString(indent));
						}
						
						if ( !answers[i][answrIdx].equals(popStr.trim()) ) {
							testPassed = false;
						}
						answrIdx++;
						break;
				}
			}
			
			if ( testPassed ) {
				System.out.println("\n   - Test passed!");
			} else {
				System.out.println("\n   - FAILED");
			}
			System.out.println();
		}
	}
	
	/* Question 4
	 *  Create a class that implements a queue using only two stacks.
	 */
	
	/* [Solution 4.A is at the bottom] */
	
	/* Question 4 Test */
	private static void Question4Test ( int testData[][], int testOrder[][], String answers[][], Boolean debug ) {
		TwoStackQueue testQueue;
		int answrIdx, dataIdx, data;
		String dequeueStr = "";
		boolean testPassed;
		
		System.out.println();
		System.out.println(" Question 4: Queue with Two Stacks");
		for ( int i = 0; i < testData.length; i++ ) {
			//Begin new test
			System.out.print("  Test #" + (i+1));
			testQueue = new TwoStackQueue();
			testPassed = true;
			answrIdx = 0;
			dataIdx = 0;
			data = 0;
			
			// testOrder indicates how many times an enqueue or dequeue should be executed
			// with even-numbered indexes as enqueue, odd-numbered indexes as dequeue
			for ( int j = 0; j < testOrder[i].length; j++ ) {		
				dequeueStr = "";
				if ( j % 2 == 0 ) {
					System.out.print("\n  > Enqueue x" + testOrder[i][j] + ": ");
					for ( int k = 0; k < testOrder[i][j]; k++ ) {
						data = testData[i][dataIdx];
						System.out.print(data + " ");
						testQueue.enqueue(data);
						dataIdx++;
					}
					if ( debug ) {
						 System.out.print("\n    >> Queue Contents: " + testQueue.toString());
					}
				} else {
					System.out.print("\n  > Dequeue x" + testOrder[i][j] + ": ");
					for ( int k = 0; k < testOrder[i][j]; k++ ) {
						dequeueStr += testQueue.dequeue() + " ";
					}
					System.out.print(dequeueStr);
					if ( debug ) {
						 System.out.print("\n    >> Queue Contents: " + testQueue.toString());
					}
					
					if ( !answers[i][answrIdx].equals(dequeueStr.trim()) ) {
						testPassed = false;
					}
					answrIdx++;
				}
			}
			
			if ( testPassed ) {
				System.out.println("\n   - Test passed!");
			} else {
				System.out.println("\n   - FAILED");
			}
			System.out.println();
		}
	}
	
}

/* Solution 2.A
 * 	The class has two stacks.  One is a stack of all added elements, and the second 
 *  is a stack tracking the minimum of the elements.  When a new element is added to the stack,
 *  it is also compared to the top of the minimum stack -- if it is less than or equal to
 *  the current minimum value, it is added to that stack, becoming the new minimum. When
 *  an element is removed, if it is equal to the minimum value, it is similarly removed from
 *  the second stack.  This way the minimums stack only contains elements still in the overall stack.
 */
class MinStack{
	Stack<Integer> Stack;
	Stack<Integer> Min;
	
	public MinStack ( ) {
		Stack = new Stack<Integer>();
		Min = new Stack<Integer>();
	}
	
	public void push ( int data ) {
		Stack.add(data);
		if ( Min.isEmpty() ) {
			Min.push(data);
		} else {
			if ( Min.peek() >= data ) {
				Min.push(data);
			}
		}
	}
	
	public int pop ( ) {
		int data = Stack.pop();
		if ( Min.peek() == data ) {
			Min.pop();
		}
		return data;
	}
	
	public int min ( ) {
		return Min.peek();
	}
	
	public boolean isEmpty ( ) {
		return Stack.isEmpty();
	}
	
	public int peek ( ) {
		return Stack.peek();
	}
	
	public String toString ( ) {
		String dataStr = "";
		Stack<Integer> temp = new Stack<Integer>();
		
		while ( !Stack.isEmpty() ) {
			temp.push(Stack.pop());
		}
		
		while ( !temp.isEmpty() ) {
			dataStr = dataStr + Stack.push(temp.pop()) + " ";
		}
		
		return dataStr.trim();
	}
}

/* Solution 3.A
 *  The class constructor will accept the max capacity that the sub-stacks can be filled to.
 *      > Note: Specifically for the function "popAt":
 *      	[1] Sub-stacks that are now under capacity will be refilled in sequential order with 
 *      		any following push calls, rather than shifting the elements between the stacks to fill 
 *      		in the gaps. If a sub-stack is completely emptied, it is removed from the set entirely.
 *      	[2] If called with a stack that does not exist, the function will throw an exception.
 *      		
 */
class SetOfStacks{
	ArrayList<Stack<Integer>> Stacks;
	int MaxCap;
	
	public SetOfStacks ( int capacity ) {
		Stacks = new ArrayList<Stack<Integer>>();
		MaxCap = capacity;
	}
	
	public void push ( int data ) {
		Stack<Integer> newStack;
		
		for ( int i = 0; i < Stacks.size(); i++ ) {
			if ( !isFull(Stacks.get(i)) ) {
				Stacks.get(i).push(data);
				return;
			}
		}
		
		newStack = new Stack<Integer>();
		newStack.push(data);
		Stacks.add(newStack);
	}
	
	public int pop ( ) {
		int last = Stacks.size()-1;
		int data = Stacks.get(last).pop();
		
		if ( Stacks.get(last).isEmpty() ) {
			Stacks.remove(last);
		}
		
		return data;
	}
	
	/* popAt will take the same stack number shown from toString (ie, one-indexed list) */
	public int popAt ( int stackNum ) {
		int index = stackNum-1;
		if ( Stacks.get(index) == null ) {
			throw new NullPointerException("There is no stack " + stackNum + " in the set.");
		}
		
		int data = Stacks.get(index).pop();
		
		if ( Stacks.get(index).isEmpty() ) {
			Stacks.remove(index);
		}
		
		return data;
	}
	
	public boolean isEmpty ( ) {
		for ( int i = 0; i < Stacks.size(); i++ ) {
			if ( !Stacks.get(i).isEmpty() ) {
				return false;
			}
		}
		return true;
	}
	
	public String toString ( ) {
		return toString(0);
	}
	
	public String toString ( int indent  ) {
		String dataStr = "";
		String whiteSpace = "";
		
		for ( int i = 0; i < indent; i++) { whiteSpace += " "; }
		
		for ( int i = 0; i < Stacks.size(); i++ ) {
			dataStr += "\n" + whiteSpace;
			dataStr += "Stack #" + (i + 1) + ": ";
			dataStr += buildStackString(Stacks.get(i));
		}
		
		if ( dataStr.isBlank() ) {
			dataStr = "--";
		}
		
		return dataStr;
	}
	
	private boolean isFull ( Stack<Integer> stack ) {
		return (stack.size() == MaxCap);
	}
	
	/* Builds a string of the stack contents */
	private String buildStackString ( Stack<Integer> srcStack ) {
		Stack<Integer> tempStack = new Stack<Integer>();
		String dataStr = "";
		
		while ( !srcStack.isEmpty() ) {
			dataStr = tempStack.push(srcStack.pop()) + " " + dataStr;
		}
		
		//restore the original stack
		while ( !tempStack.isEmpty() ) {
			srcStack.push(tempStack.pop());
		}
		
		return dataStr.trim();
	}
}

/* Solution 4.A
 *  The class has two stacks. One is for adding elements and the other is for removing 
 *  elements.  When enqueue is called, the elements are added to the first stack like normal.
 *  When dequeue is called, the elements are moved from the first stack onto the other stack
 *  (reversing their order), then topmost element is removed.  So long as elements
 *  continue to be removed, the elements can stay in the second stack.  When another
 *  enqueue is called, the elements are returned to the original stack, and the new data 
 *  is added.
 */
class TwoStackQueue{
	Stack <Integer> PushStack;
	Stack <Integer> PopStack;
	
	public TwoStackQueue ( ) {
		PushStack = new Stack<Integer>();
		PopStack = new Stack<Integer>();
	}
	
	public void enqueue ( int data ) {
		if ( !PopStack.isEmpty() ) {
			stackShift(PopStack,PushStack);
		}
		PushStack.push(data);
	}
	
	public int dequeue ( ) {
		if ( !PushStack.isEmpty() ) {
			stackShift(PushStack, PopStack);
		}
		return PopStack.pop();
	}
	
	public boolean isEmpty ( ) {
		return (PushStack.isEmpty() && PopStack.isEmpty());
	}
	
	public String toString ( ) {
		String dataStr;
		
		if ( !PushStack.isEmpty() ) {
			dataStr = buildStackString(PushStack, PopStack, false);
		} else {
			dataStr = buildStackString(PopStack, PushStack, true);
		}
		
		if ( dataStr.isBlank() ) {
			dataStr = "--";
		}
		
		return dataStr;
	}
		
	/* Moves the entire contents of the source stack into the destination stack */
	private void stackShift ( Stack<Integer> srcStack, Stack<Integer> destStack ) {
		while ( !srcStack.isEmpty() ) {
			destStack.push(srcStack.pop());
		}
	}
	
	/* Builds a string of the stack contents, storing the data in reverse order in a holding stack */
	private String buildStackString ( Stack<Integer> srcStack, Stack<Integer> destStack, boolean reverse ) {
		String dataStr = "";
		
		while ( !srcStack.isEmpty() ) {
			if ( !reverse ) {
				dataStr = destStack.push(srcStack.pop()) + " " + dataStr;
			} else {
				dataStr += " " + destStack.push(srcStack.pop());
			}
		}
		
		return dataStr.trim();
	}
		
}

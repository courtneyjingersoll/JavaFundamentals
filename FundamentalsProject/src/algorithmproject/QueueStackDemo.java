package algorithmproject;

import java.util.*;

public class QueueStackDemo {
	
	public static void main(String[] args) {
		Queue<Integer> myQueue = new LinkedList<Integer>();
		Stack<Integer> myStack = new Stack<Integer>();
		
		myQueue.add(1);
		myQueue.add(2);
		myQueue.add(3);
		
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		
		System.out.print("queue:");
		while (!myQueue.isEmpty()) {
			System.out.print(" " + myQueue.remove());
		}
		
		System.out.print("stack:");
		while (!myQueue.isEmpty()) {
			System.out.print(" " + myStack.pop());
		}
		
	}
	
	
}

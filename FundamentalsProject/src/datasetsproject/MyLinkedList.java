package datasetsproject;

public class MyLinkedList {
	private Node head = null;
	
	class Node {
		Node next = null;
		Node prev = null;
		String data;
		
		public Node(String data) {
			this.data = data;
		}
		
		void appendToTail(String data){
			Node endNode = new Node(data);
			Node curNode;
			curNode = this;
					
			while(curNode.next != null) {
				curNode=curNode.next;
			}
			curNode.next = endNode;
			endNode.prev = curNode;
		}
	}
	
	public Node getHead() {
		return head;
	}
	
	public void insertData(String data) {
		if (head != null) {
			head.appendToTail(data);
		} else {
			head = new Node(data);
		}
	}
	
	public void deleteData(String data) {
		Node foundNode;
		foundNode = searchFor(data);
		if(foundNode != null) {
			deleteNode(foundNode);
		}
	}
	
	public void deleteAllData(String data) {
		Node foundNode;
		
		foundNode = head;
		while(foundNode != null){
			foundNode = deleteNode(searchFor(data,foundNode));
		}
	}
	
	public Node deleteNode(Node removeNode) {
		if (removeNode == null) {
			return null;
		}
		if (removeNode == head) {
			head = removeNode.next;
		}
		if(removeNode.next != null) {
			(removeNode.next).prev = removeNode.prev;
		}
		if (removeNode.prev != null) {
			(removeNode.prev).next = removeNode.next;
		}
		return removeNode.next;
	}
	
	public void insertAfter(Node curNode, Node newNode) {
		Node tempNode;
		
		tempNode = curNode.next;
		curNode.next = newNode;
		newNode.prev = curNode;
		newNode.next = tempNode;
	}
	
	public Node searchFor(String data) {	
		if (head == null) {
			return null;
		}
		return searchFor(data,head);
	}
	
	public Node searchFor(String data, Node curNode) {
		while(curNode != null) {
			if(curNode.data == data) {
				return curNode;
			}
			curNode = curNode.next;
		}
		return null;
	}
	
	
}



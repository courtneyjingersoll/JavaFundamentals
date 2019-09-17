package algorithmproject;

public class BinaryTree {
	Node root;
	
	public void addNode(int key, String data) {
		Node newNode = new Node(key, data);
		Node focusNode = root;
		Node parent;
		
		if (root == null) {
			root = newNode;
		} else {
			while(true) {
				parent = focusNode;
				if(key < focusNode.key) {
					focusNode = focusNode.ltChild;
					if (focusNode == null) {
						parent.ltChild = newNode;
						return;
					}
				} else {
					focusNode = focusNode.gtChild;
					if (focusNode == null) {
						parent.gtChild = newNode;
						return;
					}
				}
			}
		}
		
	}
	
	public void inOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			inOrderTraversal(focusNode.ltChild);
			
			System.out.println(focusNode);
			
			inOrderTraversal(focusNode.gtChild);
		}
	}
}

class Node{
	int key;
	String data;
	
	Node ltChild;
	Node gtChild;
	
	Node(int key, String data){
		this.key = key;
		this.data = data;
	}
	
	public String toString() {
		return "Node " + key + " contains data: " + data;
	}
}

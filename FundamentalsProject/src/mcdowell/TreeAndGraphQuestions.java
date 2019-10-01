package mcdowell;

import java.util.*;

public class TreeAndGraphQuestions {

	public static void main(String[] args) {
		TestGraph myGraph = new TestGraph(true, 6);
		myGraph.addEdge(1,2); myGraph.addEdge(2,3); myGraph.addEdge(2,4); myGraph.addEdge(3,2); 
		myGraph.addEdge(3,4); myGraph.addEdge(3,5); myGraph.addEdge(4,1); myGraph.addEdge(5,2); 
		
		System.out.println("Route 1 -> 4 exists? " + RouteBetweenNodes(myGraph,1,4));
		System.out.println("Route 4 -> 5 exists? " + RouteBetweenNodes(myGraph,4,5));
		System.out.println("Route 2 -> 6 exists? " + RouteBetweenNodes(myGraph,2,6));
		System.out.println("Route 5 -> 3 exists? " + RouteBetweenNodes(myGraph,5,3));
	}
	
	
	/* Question 1
	 *  Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
	 */
	
	/* Solution 1.A
	 *  Breadth-First Search transversal of the tree.
	 */
	public static boolean RouteBetweenNodes( TestGraph graph, int srcNode, int destNode ) {
		int nodeState[] = new int[graph.getSize()];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int currNode = 0;
		
		if ( srcNode == destNode ) return true;
		
		queue.add(srcNode);
		nodeState[srcNode] = 1;
		while ( !queue.isEmpty() ) {
			currNode = queue.pop();
			for ( int node : graph.getNodePairs(currNode)) {
				if ( nodeState[node] == 0 ) {
					if ( node == destNode ) return true; 
					queue.add(node);
					nodeState[node] = 1;
				}
			}
		}
		
		return false;
	}
	
	/* Question 3 Test */
	private static void Question3Test ( TestGraph graph, int tests[][], boolean answers[], Boolean debug ) {		
		
	}
}


class TestGraph {
	
	boolean directed;
	int nodes;
	LinkedList<Integer> pairs[];
	
	@SuppressWarnings("unchecked")
	TestGraph(boolean isDirected, int NumOfNodes){
		this.directed = isDirected;
		this.nodes = NumOfNodes;
		pairs = new LinkedList[NumOfNodes]; 
		
		for ( int i =0; i < NumOfNodes; i++ ) {
			pairs[i] = new LinkedList<Integer>();
		}
		
	}
	
	public void addEdge ( int srcNode, int destNode ) {
		
		pairs[srcNode].add(destNode);
		
		if ( !directed ) {
			pairs[destNode].add(srcNode);
		}
		
	}
	
	public LinkedList<Integer> getNodePairs ( int node ) {
		return pairs[node];
	}
	
	public int getSize ( ) {
		return nodes;
	}
	
}
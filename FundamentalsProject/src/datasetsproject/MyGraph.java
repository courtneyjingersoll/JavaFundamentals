package datasetsproject;

import java.util.*;

public class MyGraph {
	
	boolean directed;
	int verticies;
	LinkedList<Integer> edges[];
	
	@SuppressWarnings("unchecked")
	MyGraph(boolean directed, int verticies){
		this.directed = directed;
		this.verticies = verticies;
		edges = new LinkedList[verticies]; 
		
		for ( int i =0; i < verticies; i++ ) {
			edges[i] = new LinkedList<Integer>();
		}
		
	}
	
	public void addEdge(MyGraph graph, int src, int dest) {
		
		graph.edges[src].add(dest);
		
		if ( !directed ) {
			graph.edges[dest].add(src);
		}
		
	}
	
	
}

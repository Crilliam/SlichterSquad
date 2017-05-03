import java.util.ArrayList;
import java.util.List;

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          Program 5
// FILE:             GraphNodeWrapper.java
//
// TEAM:    Team 3
// Author: Nate Hoffman	nhoffman5@wisc.edu	9074286361	LEC002
// Author: Ray Smith	rsmith52@wisc.edu	9073116221	LEC003
// Author: Will Mustari	mustari@wisc.edu	9075210683	LEC002
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * The GraphNodeWrapper is a helper class for the GetShortestRoute method in
 * NavigationGraph. It allows each node to have corresponding values such as
 * if it has been visited, what it's predecessor in the path is, and what the
 * weight of the path to the node is.
 */
class GraphNodeWrapper implements Comparable {
	
	// Reference to the GraphNode object this wrapper holds
	private GraphNode<Location, Path> node;
	// Reference to the previous GraphNodeWrapper in the shortest path
	private GraphNodeWrapper predecessor;
	// If this node has been visited yet
	private boolean visited;
	// Current weight of the path from start to this node
	private double weight;
	
	/**
	 * Constructor for a GraphNodeWrapper. It sets visited to false, predecessor
	 * to null, and pathLength to infinity as per Dijkstra's algortithm.
	 * 
	 * @param graphNode The graphNode this wrapper holds
	 */
	public GraphNodeWrapper (GraphNode<Location, Path> graphNode) {
		setNode(graphNode);
		setVisited(false);
		setPredecessor(null);
		setPathLength(Double.POSITIVE_INFINITY);
	}

	/**
	 * Getter for the node this wrapper object holds
	 * 
	 * @return This wrapper's held graphNode
	 */
	public GraphNode<Location, Path> getNode() {
		return node;
	}

	/**
	 * Setter for the node this wrapper object holds
	 * 
	 * @param node The node for this wrapper to hold
	 */
	public void setNode(GraphNode<Location, Path> node) {
		this.node = node;
	}

	/**
	 * Getter for the weight of the path up to this node
	 * 
	 * @return The weight of the path up to this node
	 */
	public double getPathLength() {
		return weight;
	}

	/**
	 * Setter for the weight of the path up to this node
	 * 
	 * @param pathLength The weight of the path up to this node
	 */
	public void setPathLength(double pathLength) {
		this.weight = pathLength;
	}

	/**
	 * Getter for whether this node has been visited or not
	 * 
	 * @return The weight of the path up to this node
	 */
	public boolean getVisited() {
		return visited;
	}

	/**
	 * Setter whether this node has been visited
	 * 
	 * @param visited Whether this node has been visited or not
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Getter for the node in the path before this one
	 * 
	 * @return The node previous to this one in the shortest path
	 */
	public GraphNodeWrapper getPredecessor() {
		return predecessor;
	}

	/**
	 * Setter for the node previous to this one
	 * 
	 * @param predecessor The node previous to this one in the shortest path
	 */
	public void setPredecessor(GraphNodeWrapper predecessor) {
		this.predecessor = predecessor;
	}
	
	/**
	 * Override compare method to compare just the weight of the path
	 * up to the node.
	 * 
	 * @param o The other node to compare to
	 */
	@Override
	public int compareTo(Object o) {
		return compareTo((GraphNodeWrapper)o);
	}
	/**
	 * Helper method for the compareTo method
	 * 
	 * @param o The other node to compare to
	 */
	private int compareTo(GraphNodeWrapper o) {
		return (int)(weight - o.getPathLength());
	}
}

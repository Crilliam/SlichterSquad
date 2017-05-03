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

class GraphNodeWrapper implements Comparable {
	
	// Reference to the GraphNode object this wrapper holds
	private GraphNode<Location, Path> node;
	// Reference to the previous GraphNodeWrapper
	private GraphNodeWrapper predecessor;
	private boolean visited;
	private double weight;
	
	public GraphNodeWrapper (GraphNode<Location, Path> graphNode) {
		setNode(graphNode);
		setVisited(false);
		setPredecessor(null);
		setPathLength(Double.POSITIVE_INFINITY);
	}

	public GraphNode<Location, Path> getNode() {
		return node;
	}

	public void setNode(GraphNode<Location, Path> node) {
		this.node = node;
	}

	public double getPathLength() {
		return weight;
	}

	public void setPathLength(double pathLength) {
		this.weight = pathLength;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public GraphNodeWrapper getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(GraphNodeWrapper predecessor) {
		this.predecessor = predecessor;
	}
	
	@Override
	public int compareTo(Object o) {
		return compareTo((GraphNodeWrapper)o);
	}
	
	private int compareTo(GraphNodeWrapper o) {
		return (int)(weight - o.getPathLength());
	}
}

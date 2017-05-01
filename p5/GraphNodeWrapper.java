/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          Program 5
// FILE:             NavigatoinGraph.java
//
// TEAM:    Team 3
// Authors: Nate Hoffman	nhoffman5@wisc.edu	9074286361	LEC002
// Author1: Ray Smith		rsmith52@wisc.edu	9073116221	LEC003
// Author2: Will Mustari	mustari@wisc.edu	9075210683	LEC002
//////////////////////////// 80 columns wide //////////////////////////////////

public class GraphNodeWrapper implements Comparable {
	
	private GraphNode<Location, Path> node;
	private double weight;
	
	public GraphNodeWrapper (GraphNode<Location, Path> graphNode, double pathValue) {
		setNode(graphNode);
		setPathLength(pathValue);
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

	@Override
	public int compareTo(Object o) {
		return compareTo((GraphNodeWrapper)o);
	}
	
	private int compareTo(GraphNodeWrapper o) {
		return (int)(weight - o.getPathLength());
	}

}

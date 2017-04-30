
public class GraphNodeWrapper implements Comparable {
	
	private GraphNode<Location, Path> node;
	private double weight;
	
	public GraphNodeWrapper (GraphNode<Location, Path> graphNode, int pathValue) {
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

	public void setPathLength(int pathLength) {
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

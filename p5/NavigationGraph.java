import java.util.ArrayList;
import java.util.List;

public class NavigationGraph implements GraphADT<Location, Path> {

	// TODO: Implement all methods of GraphADT
	private ArrayList<GraphNode<Location, Path>> vertices;
	private int numVertices;
	private ArrayList<String> edgePropertyNames;

	public NavigationGraph(String[] edgePropertyNames) {
		this.vertices = new ArrayList<GraphNode<Location, Path>>();
		this.numVertices = 0;
		this.edgePropertyNames = new ArrayList<String>();
		for (String s: edgePropertyNames) {
			this.edgePropertyNames.add(s);
		}
	}

	/**
	 * Returns a Location object given its name
	 * 
	 * @param name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		for (GraphNode<Location, Path> v : vertices) {
			if (v.getVertexData().getName().equals(name)) {
				return v.getVertexData();
			}
		}
		return null;
	}

	private GraphNode<Location, Path> getVertexByLocation(Location vertex) {
		for (GraphNode<Location, Path> v : vertices) {
			if (v.getVertexData().equals(vertex)) {
				return v;
			}
		}
		return null;
	}

	@Override
	public void addVertex(Location vertex) {
		if (getVertexByLocation(vertex) != null) {
			throw new IllegalArgumentException();
		}
		vertices.add(new GraphNode<Location, Path>(vertex, numVertices));
		numVertices++;

	}

	@Override
	public void addEdge(Location src, Location dest, Path edge) {
		GraphNode<Location, Path> vertex = getVertexByLocation(src);
		vertex.addOutEdge(edge);
	}

	@Override
	public List<Location> getVertices() {
		ArrayList<Location> vertexList = new ArrayList<Location>();
		for (GraphNode<Location, Path> v : vertices) {
			vertexList.add(v.getVertexData());
			return vertexList;
		}
		return null;
	}

	@Override
	public Path getEdgeIfExists(Location src, Location dest) {
		GraphNode<Location, Path> vertex = getVertexByLocation(src);
		// No vertex with starting point exists
		if (vertex == null) {
			return null;
		}
		for (Path path: vertex.getOutEdges()) {
			if (path.getDestination().equals(dest)) {
				return path;
			}
		}
		// No vertex with starting and ending point exists
		return null;
	}

	@Override
	public List<Path> getOutEdges(Location src) {
		return getVertexByLocation(src).getOutEdges();
	}

	@Override
	public List<Location> getNeighbors(Location vertex) {
		ArrayList<Location> neighborsList = new ArrayList<Location>();
		GraphNode<Location, Path> v = getVertexByLocation(vertex);
		
		if (v == null) {
			return null;
		}
		for (Path p: v.getOutEdges()) {
			neighborsList.add(p.getDestination());
		}
		return neighborsList;
	}

	@Override
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getEdgePropertyNames() {
		return (String [])edgePropertyNames.toArray();
	}

}
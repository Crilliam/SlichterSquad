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
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The NavigationGraph class is the graph that stores the locations and routes.
 * It also allows you to add things to it as well as get the information stored.
 */
public class NavigationGraph implements GraphADT<Location, Path> {

	private ArrayList<GraphNode<Location, Path>> vertices;
	//the ArrayList that stores the locations
	private int numVertices;
	//stores the amount of vertices in the ArrayList
	private ArrayList<String> edgePropertyNames;
	//stores all of the edge properties

	/**
	 * Creates the NavigationGraph object
	 * 
	 * @param edgePropertyNames stores all of the edge properties
	 */
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
		name = name.toLowerCase();
		for (GraphNode<Location, Path> v : vertices) {
			if (v.getVertexData().getName().equals(name)) {
				return v.getVertexData();
			}
		}
		return null;
	}

	/**
	 * Returns a location when given a vertex.
	 * 
	 * @param vertex of the location
	 * @return Location object
	 */
	private GraphNode<Location, Path> getVertexByLocation(Location vertex) {
		for (GraphNode<Location, Path> v : vertices) {
			if (v.getVertexData().equals(vertex)) {
				return v;
			}
		}
		return null;
	}
	
	private int getIndexOfProperty (String edgeProperty) {
		for (int i = 0; i < edgePropertyNames.size(); ++i) {
			if (edgePropertyNames.get(i).equals(edgeProperty)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Adds a location to the graph and increases the numVertices int.
	 * 
	 * @param vertex of the location
	 */
	@Override
	public void addVertex(Location vertex) {
		if (getVertexByLocation(vertex) != null) {
			throw new IllegalArgumentException();
		}
		vertices.add(new GraphNode<Location, Path>(vertex, numVertices));
		numVertices++;

	}

	/**
	 * Adds a path between locations.
	 * 
	 * @param src location where the path comes from
	 * @param dest location where the path goes to
	 * @param edge path between locations
	 */
	@Override
	public void addEdge(Location src, Location dest, Path edge) {
		GraphNode<Location, Path> vertex = getVertexByLocation(src);
		vertex.addOutEdge(edge);
	}

	/**
	 * Gets all of the vertices as an ArrayList
	 * 
	 * @return vertexList all of the vertices as an ArrayList
	 */
	@Override
	public List<Location> getVertices() {
		ArrayList<Location> vertexList = new ArrayList<Location>();
		for (GraphNode<Location, Path> v : vertices) {
			vertexList.add(v.getVertexData());
		}
		return vertexList;
	}

	/**
	 * Gets the path between locations if it exists.
	 * 
	 * @param src location where the path comes from
	 * @param dest location where the path goes to
	 * @return path the path between locations
	 */
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

	/**
	 * Gets the edges going out from a location.
	 * 
	 * @param src the location that we want the out edges from
	 * @return the paths going out from the location
	 */
	@Override
	public List<Path> getOutEdges(Location src) {
		return getVertexByLocation(src).getOutEdges();
	}
	
	private Path getEdge(Location src, Location dest) {
		List<Path> edges = getOutEdges(src);
		for (Path edge: edges) {
			if (edge.getDestination().equals(dest)) {
				return edge;
			}
		}
		return null;
	}

	/**
	 * Gets the locations accessible from the given location.
	 * 
	 * @param vertex location we want neighbors for
	 * @return neighborsList locations accessible from given vertex
	 */
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

	/**
	 * Calculates the shortest distance to get from one given location to
	 * another given location using Dijkstra's algorithm.
	 * 
	 * @param src location we are trying to find the shortest path from
	 * @param dest location we want to get the shortest path to
	 * @param edgePropertyName properties that define the path
	 * @return a list of paths that give you the shortest route
	 */
	@Override
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName) {
		ArrayList<GraphNodeWrapper> list = new ArrayList<GraphNodeWrapper>();
		// For each vertex set visited to false, weight to infinity, and predecessor to null
		for (GraphNode<Location, Path> v: vertices) {
			list.add(new GraphNodeWrapper(v));
		}
		GraphNodeWrapper start = null;
		GraphNodeWrapper end = null;
		for (GraphNodeWrapper v : list) {
			if (v.getNode().getVertexData().getName().equals(src.getName())) {
				start =  v;
			}
			else if (v.getNode().getVertexData().getName().equals(dest.getName())) {
				end =  v;
			}
		}
		// Set start weight to 0
		start.setPathLength(0);
		// Create a new priority queue
		PriorityQueue<GraphNodeWrapper> pq = new PriorityQueue<GraphNodeWrapper>();
		// Insert start vertex
		pq.add(start);
		// Main Loop
		while (!pq.isEmpty()) {
			GraphNodeWrapper C = pq.remove();
			C.setVisited(true);
			// Get all unvisited successors
			List<Location> neighbors = getNeighbors(C.getNode().getVertexData());
			ArrayList<GraphNodeWrapper> successors = new ArrayList<GraphNodeWrapper>();
			for (GraphNodeWrapper w: list) {
				if (w.getVisited() == false) {
					if (neighbors.contains(w.getNode().getVertexData())) {
						successors.add(w);
					}
				}
			}
			for (GraphNodeWrapper s: successors) {
				// Find edge weight from C to s
				Path edge = getEdge(C.getNode().getVertexData(), s.getNode().getVertexData());
				double weight = edge.getProperties().get(getIndexOfProperty(edgePropertyName));
				if (s.getPathLength() > C.getPathLength() + weight) {
					// Update s's weight
					s.setPathLength(C.getPathLength() + weight);
					s.setPredecessor(C);
					pq.add(s);
				}
			}
		}
		List<Path> paths = new ArrayList<Path>();
		GraphNodeWrapper pointer = end;
		while (true) {
			if (pointer.getPredecessor() == null) {
				break;
			}
			paths.add(0, getEdgeIfExists(pointer.getPredecessor().getNode().getVertexData(),
					pointer.getNode().getVertexData()));
			pointer = pointer.getPredecessor();
		}
		return paths;
	}

	/**
	 * The properties that define a path.
	 * 
	 * @return the properties as a string array
	 */
	@Override
	public String[] getEdgePropertyNames() {
		String[] edgeProperties = new String[edgePropertyNames.size()];
		for (int i = 0; i < edgePropertyNames.size(); ++i) {
			edgeProperties[i] = edgePropertyNames.get(i);
		}
		return edgeProperties;
	}
	
	@Override
	public String toString() {
		return null; //TODO ADD THIS
	}
}
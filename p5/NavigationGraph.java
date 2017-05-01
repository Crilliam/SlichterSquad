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
//			boolean visited = false;
//			double weight = Double.POSITIVE_INFINITY;
//			Location predecessor = null;
//			int totalWeight = 0;
//
//			PriorityQueue<???> pq = new PriorityQueue<???>(totalWeight, src);
//			
//			while(!pq.isEmpty()) {
//				[C total weight , C] = pq.removeMin();
//				visited = false;
//				
//	            u = pq.Delete_root();
//	            v = G.nextneighbor(u);
//
//	            while(v != -1)  			// for each neighbor of u
//	            {
//	            	if(dist[v] > dist[u] + G.edgeLength(u,v)) {
//	            		dist[v] = dist[u] + G.edgeLength(u,v);
//				pq.Update(v, dist[v]);
//	                     }
//
//	            	v = G.nextneighbor(u);  	// get the next neighbor of u
//
//			}
//			return null;
//
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
//			   public static void dijkstra_function(graph G, int s) throws IOException
//	  	   public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName)
//			   {                  					// s is the index of the starting vertex
//			   	// declare variables
//				int nVerts, u, v;
//				int [] dist;
//
//			        nVerts = G.vertices(); 			// get number of vertices in the graph class
//
//			        // initialize array
//			        dist = new int[nVerts];
//
//			        for(v=0; v<nVerts; v++) 			// initializations
//			        {
//			        	dist[v] = 99999; 				// 99999 represents infinity
//
//			        }// end for
//
//			        dist[src] = 0;
//
//			        PriorityQueue pq = new PriorityQueue(dist);  		
//
//			        while(pq.isEmpty() == 0) 			// if heap is not empty
//			        {
//
//			                u = pq.Delete_root();
//			                v = G.nextneighbor(u);
//
//			                while(v != -1)  			// for each neighbor of u
//			                {
//			                	if(dist[v] > dist[u] + G.edgeLength(u,v)) {
//			                		dist[v] = dist[u] + G.edgeLength(u,v);
//			
//									pq.Update(v, dist[v]);
//			                    }
//
//			                	v = G.nextneighbor(u);  	// get the next neighbor of u
//
//			                }// end while
//
//			        }// end while
//
//			        System.out.println("");    			// display the array dist
//			        for(int row=0; row<nVerts; row++)
//			      	{
//			      		System.out.print(dist[row] + " ");
//			      	  	System.out.println("");
//			      	}// end for
//			   }// end bfs_function()
//			}//end class dijkstra
//		}
		return null;
	}

	/**
	 * The properties that define a path.
	 * 
	 * @return the properties as a string array
	 */
	@Override
	public String[] getEdgePropertyNames() {
		return (String [])edgePropertyNames.toArray();
	}

}
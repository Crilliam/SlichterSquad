/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          Program 5
// FILE:             NavigatoinGraph.java
//
// TEAM:    Team 3
// Authors: Nate Hoffman	nhoffman5@wisc.edu	9074286361	LEC002
// Author1: Ray Smith		rsmith52@wisc.edu	9073116221	LEC003
// Author2: Will Mustari									LEC002
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.ArrayList;
import java.util.List;

/**
 * The NavigationGraph class is the graph 
 */
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

	@Override
	public String[] getEdgePropertyNames() {
		return (String [])edgePropertyNames.toArray();
	}

}
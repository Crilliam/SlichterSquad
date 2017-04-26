import java.util.ArrayList;

public class NavigationGraph implements GraphADT<Location, Path> {

    //TODO: Implement all methods of GraphADT
  	private ArrayList<GraphNode<Location, Path>> vertices;
  	private int numVertices;
	
  	public NavigationGraph(String[] edgePropertyNames) {
    	vertices = new ArrayList<GraphNode<Location, Path>>();
    	numVertices = 0;
  	}
	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
      for (GraphNode<Location, Path> vertex: vertices) {
    	  if (vertex.getVertexData().getName().equals(name)) {
    		  return vertex.getVertexData();
    	  }
      }
      return null;
    }
}
package jalds.alds.al.graphs;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

/**
 * Used to transpose a directed graph
 * 
 * @author Devender Gollapally
 * 
 */
public class TransposeGraph {

	/**
	 * Returns a new graph which is the transpose of the given one
	 * 
	 * @param graph
	 * @return
	 */
	public Graph transpose(Graph graph) {
		if (graph.getType() == Graph.Type.DIRECTED) {
			Graph transposedGraph = new Graph(Graph.Type.DIRECTED);
			Vertex[][] adjacencyList = graph.getAdjacencyList();
			for (Vertex[] vertices : adjacencyList) {
				if (vertices.length > 0) {
					Vertex from = vertices[0];
					for (int i = 1; i < vertices.length; i++) {
						Vertex to = vertices[i];
						transposedGraph.addEdge(to, from);
					}
				}
			}

			return transposedGraph;
		}
		return null;
	}
}

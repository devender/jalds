package jalds.alds.al.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

/**
 * Finds the strongly connected components in a given directed graph
 * 
 * A strongly connected component of a directed graph G=(V,E) is a max set of
 * vertices C such that for every pair of vertices u and v in C u is reachable
 * from v and v is reachable from u.
 * 
 * To find the strongly connected component in given graph G, we transpose G,
 * both G and G(t) will have the same strongly connected component
 * 
 * @author Devender Gollapally
 * 
 */
public class StronglyConnectedComponents {

	public void compute(Graph graph) {
		DepthFirstSearch dfs = new DepthFirstSearch(graph);
		dfs.getFinishedAtMap();

		TransposeGraphService tgs = new TransposeGraphService();
		Graph transposedGraph = tgs.transpose(graph);

		DepthFirstSearch dfst = new DepthFirstSearch(transposedGraph);
		dfst.init();

	}

	private List<Vertex> getDescendingFinishedTime(Map<Vertex, Integer> map) {
		List<Vertex> list = new LinkedList<Vertex>();
		for (Vertex vertex : map.keySet()) {
			int f = map.get(vertex);
			
		}

	}
}

package jalds.alds.al.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

/**
 * Searches deaper in the graph whenever possible. In DFS edges are explored out
 * of the most recently discovered vertex v that still has unexplored edges
 * leaving it. When all of v edges are explored the search backtracks to explore
 * edges leaving the vertex from which v was discovered. The process continues
 * till we have discovered all the vertices that are reachable from the original
 * source. If any undiscovered vertices remain then one of them is selected as
 * the new source and the search is repeated.
 * 
 * @author Devender Gollapally
 * 
 */
public class DepthFirstSearch {

	private Graph graph;
	private int time = 0;
	private Map<Vertex, Color> colorMap;
	private Map<Vertex, Vertex> predecessorMap;
	private Map<Vertex, Integer> discoveredAtMap;
	private Map<Vertex, Integer> finishedAtMap;

	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
		colorMap = new HashMap<Vertex, Color>();
		predecessorMap = new HashMap<Vertex, Vertex>();
		discoveredAtMap = new HashMap<Vertex, Integer>();
		finishedAtMap = new HashMap<Vertex, Integer>();
	}

	public void compute() {
		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			colorMap.put(vertex, Color.WHITE);
			predecessorMap.put(vertex, null);
		}

		for (Vertex vertex : set) {
			if (colorMap.get(vertex).compareTo(Color.WHITE) == 0) {
				visit(vertex);
			}
		}
	}

	private void visit(Vertex vertex) {
		colorMap.put(vertex, Color.GRAY);
		time = time + 1;
		discoveredAtMap.put(vertex, time);

		Vertex[] vertices = graph.getAllAdjacentVertices(vertex);
		if (vertices != null) {
			for (Vertex adjacentVertex : vertices) {
				if (colorMap.get(adjacentVertex).compareTo(Color.WHITE) == 0) {
					predecessorMap.put(adjacentVertex, vertex);
				}
			}
		}

		time = time + 1;
		finishedAtMap.put(vertex, time);
		colorMap.put(vertex, Color.BLACK);
	}

	public Map<Vertex, Vertex> getPredecessorMap() {
		return predecessorMap;
	}

	public Map<Vertex, Integer> getDiscoveredAtMap() {
		return discoveredAtMap;
	}

	public Map<Vertex, Integer> getFinishedAtMap() {
		return finishedAtMap;
	}

}

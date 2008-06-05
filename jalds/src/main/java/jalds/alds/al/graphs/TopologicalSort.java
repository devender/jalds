package jalds.alds.al.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

/**
 * DAG or a directed acyclic graph is a directed graph with directed cycles. A
 * topological sort of a dag G(V,E) is a linear ordering of all its vertices
 * such that if G contains an edge (u,v) the u will appear before v in the
 * ordering, a topological sort can be viewed as an ordering of its vertices
 * along a horizontal line so that all directed edges go from left to right.
 * 
 * @author Devender Gollapally
 * 
 */
public class TopologicalSort implements FinishedEventObserver {

	private Graph graph;
	private List<Vertex> list;

	public TopologicalSort(Graph graph) {
		this.graph = graph;
		list = new LinkedList<Vertex>();
	}

	public List<Vertex> compute() {
		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
		depthFirstSearch.registerFinishedEventObserver(this);
		depthFirstSearch.compute();
		Map<Vertex, Integer> finishedAtMap = depthFirstSearch.getFinishedAtMap();
		Map<Vertex, Integer> discoveredAtMap = depthFirstSearch.getDiscoveredAtMap();

		for (Vertex vertex : list) {
			System.out.println(vertex.getPrettyName() + " " + discoveredAtMap.get(vertex) + "/" + finishedAtMap.get(vertex));
		}
		return list;
	}

	public void update(Vertex vertex, int discoveredTime, int finishedTime) {
		list.add(0, vertex);
	}
}

package test.jalds.alds.al.graphs;

import java.util.Map;
import java.util.Set;

import jalds.alds.al.graphs.DepthFirstSearch;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestDepthFirstSearch extends TestCase {

	public void test() {
		Vertex u = new Vertex(85);
		Vertex v = new Vertex(86);
		Vertex w = new Vertex(87);
		Vertex x = new Vertex(88);
		Vertex y = new Vertex(89);
		Vertex z = new Vertex(90);

		Graph graph = new Graph(Graph.Type.DIRECTED);

		graph.addEdge(u, x);
		graph.addEdge(u, v);
		graph.addEdge(x, v);
		graph.addEdge(v, y);
		graph.addEdge(y, x);
		graph.addEdge(w, y);
		graph.addEdge(w, z);
		graph.addEdge(z, z);

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
		depthFirstSearch.compute();

		Set<Vertex> set = graph.getVertices();
		Map<Vertex, Integer> discoveredAtMap = depthFirstSearch.getDiscoveredAtMap();
		Map<Vertex, Vertex> predecessorMap = depthFirstSearch.getPredecessorMap();
		Map<Vertex, Integer> finishedAtMap = depthFirstSearch.getFinishedAtMap();

		for (Vertex vertex : set) {
			assertTrue(predecessorMap.containsKey(vertex));
			assertTrue(discoveredAtMap.containsKey(vertex));
			int d = discoveredAtMap.get(vertex);
			assertTrue(d > 0);
			assertTrue(finishedAtMap.containsKey(vertex));
			int f = finishedAtMap.get(vertex);
			assertTrue(f > 0);
			assertTrue(f > d);
		}
		
		assertTrue(depthFirstSearch.getBlackEdge().size() == 2);

	}
}

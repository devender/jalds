package test.jalds.alds.al.graphs;

import jalds.alds.al.graphs.DepthFirstSearch;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

public class TestDepthFirstSearch extends TestCase {

	public void test() {
		Vertex u = new Vertex("u");
		Vertex v = new Vertex("v");
		Vertex w = new Vertex("w");
		Vertex x = new Vertex("x");
		Vertex y = new Vertex("y");
		Vertex z = new Vertex("z");

		Graph graph = new Graph(Graph.Type.DIRECTED);
		graph.addEdge(u, v).addEdge(u, x);
		graph.addEdge(x, v).addEdge(v, y).addEdge(y, x).addEdge(z, z);
		graph.addEdge(w, y).addEdge(w, z);

		assertEquals(8, graph.numberOfEdges());

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
		depthFirstSearch.compute();

		Set<Vertex> set = graph.getVertices();
		Map<Vertex, Integer> discoveredAtMap = depthFirstSearch.getDiscoveredAtMap();
		Map<Vertex, Integer> finishedAtMap = depthFirstSearch.getFinishedAtMap();

		for (Vertex vertex : set) {
			assertTrue(discoveredAtMap.containsKey(vertex));
			int d = discoveredAtMap.get(vertex);
			assertTrue(d > 0);
			assertTrue(finishedAtMap.containsKey(vertex));
			int f = finishedAtMap.get(vertex);
			assertTrue(f > 0);
			assertTrue(f > d);
		}

		assertTrue(depthFirstSearch.getBlackEdges().size() == 2);
		assertFalse(depthFirstSearch.isAcyclic());
	}

}

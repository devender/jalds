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
		u.setPrettyName("u");
		Vertex v = new Vertex(86);
		v.setPrettyName("v");
		Vertex w = new Vertex(87);
		w.setPrettyName("w");
		Vertex x = new Vertex(88);
		x.setPrettyName("x");
		Vertex y = new Vertex(89);
		y.setPrettyName("y");
		Vertex z = new Vertex(90);
		z.setPrettyName("z");

		Graph graph = new Graph(Graph.Type.DIRECTED);

		graph.addEdge(w, z);
		graph.addEdge(v, y);
		graph.addEdge(u, x);
		graph.addEdge(u, v);
		graph.addEdge(x, v);

		graph.addEdge(y, x);
		graph.addEdge(w, y);

		graph.addEdge(z, z);

		assertEquals(8, graph.numberOfEdges());

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
		depthFirstSearch.compute();

		Set<Vertex> set = graph.getVertices();
		Map<Vertex, Integer> discoveredAtMap = depthFirstSearch.getDiscoveredAtMap();
		Map<Vertex, Integer> finishedAtMap = depthFirstSearch.getFinishedAtMap();

		assertTrue(discoveredAtMap.get(u) < finishedAtMap.get(u));
		assertTrue(discoveredAtMap.get(v) < finishedAtMap.get(v));
		assertTrue(discoveredAtMap.get(w) < finishedAtMap.get(w));
		assertTrue(discoveredAtMap.get(x) < finishedAtMap.get(x));
		assertTrue(discoveredAtMap.get(y) < finishedAtMap.get(y));
		assertTrue(discoveredAtMap.get(z) < finishedAtMap.get(z));
		
		for (Vertex vertex : set) {
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

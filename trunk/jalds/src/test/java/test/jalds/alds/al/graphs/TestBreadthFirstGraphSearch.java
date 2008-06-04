package test.jalds.alds.al.graphs;

import java.util.Map;

import jalds.alds.al.graphs.BreadthFirstGraphSearch;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestBreadthFirstGraphSearch extends TestCase {

	/**
	 * Take a look at the graph on page 533 of introduction to algorithms to get
	 * a picture of this graph
	 */
	public void test() {
		Vertex r = new Vertex(82);
		Vertex s = new Vertex(83);
		Vertex t = new Vertex(84);
		Vertex u = new Vertex(85);
		Vertex v = new Vertex(86);
		Vertex w = new Vertex(87);
		Vertex x = new Vertex(88);
		Vertex y = new Vertex(89);

		Graph graph = new Graph(Graph.Type.UNDIRECTED);
		graph.addEdge(v, r);
		graph.addEdge(r, s);
		graph.addEdge(s, w);
		graph.addEdge(w, t);
		graph.addEdge(w, x);
		graph.addEdge(x, t);
		graph.addEdge(x, u);
		graph.addEdge(x, y);
		graph.addEdge(t, u);
		graph.addEdge(y, u);

		BreadthFirstGraphSearch breadthFirstGraphSearch = new BreadthFirstGraphSearch(graph, s);
		Map<Vertex, Integer> distanceMap = breadthFirstGraphSearch.compute();
		assertEquals(1, (int) distanceMap.get(r));
		assertEquals(2, (int) distanceMap.get(v));
		assertEquals(0, (int) distanceMap.get(s));
		assertEquals(1, (int) distanceMap.get(w));
		assertEquals(2, (int) distanceMap.get(t));
		assertEquals(2, (int) distanceMap.get(x));
		assertEquals(3, (int) distanceMap.get(u));
		assertEquals(3, (int) distanceMap.get(y));
	}
}

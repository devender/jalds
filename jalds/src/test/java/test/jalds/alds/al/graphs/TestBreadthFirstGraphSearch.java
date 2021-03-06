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
		Vertex r = new Vertex("r");
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");
		Vertex u = new Vertex("u");
		Vertex v = new Vertex("v");
		Vertex w = new Vertex("w");
		Vertex x = new Vertex("x");
		Vertex y = new Vertex("y");

		Graph graph = new Graph(Graph.Type.UNDIRECTED);
		graph.addEdge(v, r).addEdge(r, s).addEdge(s, w).addEdge(w, t).addEdge(w, x).addEdge(x, t).addEdge(x, u).addEdge(x, y).addEdge(t, u).addEdge(y, u);

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
		assertEquals("s->w->x->y->", breadthFirstGraphSearch.printShortestPathTo(y));
		assertEquals("s->w->t->", breadthFirstGraphSearch.printShortestPathTo(t));

	}

	public void testConnectivity() {
		Vertex r = new Vertex("r");
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");

		Graph graph = new Graph(Graph.Type.UNDIRECTED);
		graph.addVertex(t);
		graph.addEdge(s, r);

		BreadthFirstGraphSearch breadthFirstGraphSearch = new BreadthFirstGraphSearch(graph, s);
		breadthFirstGraphSearch.compute();
		assertFalse(breadthFirstGraphSearch.isConnected());
	}
}

package test.jalds.alds.al.graphs;

import jalds.alds.al.graphs.TransposeGraphService;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestTransposeGraph extends TestCase {

	public void test() {
		Vertex a1 = new Vertex("1");
		Vertex a2 = new Vertex("2");
		Vertex a3 = new Vertex("3");
		Vertex a4 = new Vertex("4");
		Vertex a5 = new Vertex("5");
		Vertex a6 = new Vertex("6");

		Graph graph = new Graph(Graph.Type.DIRECTED);
		graph.addEdge(a1, a2);
		graph.addEdge(a1, a4);
		graph.addEdge(a2, a5);
		graph.addEdge(a3, a5);
		graph.addEdge(a3, a6);
		graph.addEdge(a4, a2);
		graph.addEdge(a5, a4);
		graph.addEdge(a6, a6);
		TransposeGraphService transposeGraph = new TransposeGraphService();
		Graph transposed = transposeGraph.transpose(graph);

		assertEquals(6, transposed.numberOfVertices());
		assertEquals(8, transposed.numberOfEdges());
		assertTrue(transposed.containsEdge(a2, a1));
		assertTrue(transposed.containsEdge(a4, a1));
		assertTrue(transposed.containsEdge(a5, a2));
		assertTrue(transposed.containsEdge(a5, a3));
		assertTrue(transposed.containsEdge(a6, a3));
		assertTrue(transposed.containsEdge(a2, a4));
		assertTrue(transposed.containsEdge(a4, a5));
		assertTrue(transposed.containsEdge(a6, a6));
	}

	public void test2() {
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");
		Vertex g = new Vertex("g");
		Vertex h = new Vertex("h");
		Graph graph = new Graph(Graph.Type.DIRECTED);

		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(b, e);
		graph.addEdge(b, f);
		graph.addEdge(c, d);
		graph.addEdge(c, g);
		graph.addEdge(d, c);
		graph.addEdge(d, h);
		graph.addEdge(e, a);
		graph.addEdge(e, f);
		graph.addEdge(f, g);
		graph.addEdge(g, f);
		graph.addEdge(g, h);
		graph.addEdge(h, h);
		TransposeGraphService transposeGraph = new TransposeGraphService();
		Graph transposed = transposeGraph.transpose(graph);
		assertTrue(transposed.containsEdge(b, a));

		assertTrue(transposed.containsEdge(a, e));

		assertTrue(transposed.containsEdge(e, b));

		assertTrue(transposed.containsEdge(c, b));

		assertTrue(transposed.containsEdge(c, d));

		assertTrue(transposed.containsEdge(h, h));
		assertTrue(transposed.containsEdge(h, g));
		assertTrue(transposed.containsEdge(d, c));

		assertTrue(transposed.containsEdge(f, e));
		assertTrue(transposed.containsEdge(f, g));
		assertTrue(transposed.containsEdge(f, b));
		assertTrue(transposed.containsEdge(g, f));

	}
}

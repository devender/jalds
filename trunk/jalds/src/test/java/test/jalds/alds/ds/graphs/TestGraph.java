package test.jalds.alds.ds.graphs;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestGraph extends TestCase {

	public void testUnDirectedGraph() {
		Graph graph = new Graph(Graph.Type.UNDIRECTED);

		Vertex a1 = new Vertex(1);
		graph.addVertex(a1);

		Vertex a2 = new Vertex(2);
		graph.addVertex(a2);

		Vertex a3 = new Vertex(3);
		graph.addVertex(a3);

		Vertex a4 = new Vertex(4);
		graph.addVertex(a4);

		Vertex a5 = new Vertex(5);
		graph.addVertex(a5);

		graph.addEdge(a1, a2);
		graph.addEdge(a1, a5);
		graph.addEdge(a2, a5);
		graph.addEdge(a2, a4);
		graph.addEdge(a2, a3);
		graph.addEdge(a3, a4);
		graph.addEdge(a5, a4);

		assertEquals(5, graph.numberOfVertices());
		assertEquals(14, graph.numberOfEdges());

	}

	public void testDirectedGraph() {
		Graph graph = new Graph(Graph.Type.DIRECTED);
		Vertex a1 = new Vertex(1);
		graph.addVertex(a1);

		Vertex a2 = new Vertex(2);
		graph.addVertex(a2);

		Vertex a3 = new Vertex(3);
		graph.addVertex(a3);

		Vertex a4 = new Vertex(4);
		graph.addVertex(a4);

		Vertex a5 = new Vertex(5);
		graph.addVertex(a5);

		Vertex a6 = new Vertex(6);
		graph.addVertex(a6);

		graph.addEdge(a1, a2);
		graph.addEdge(a1, a4);
		graph.addEdge(a2, a5);
		graph.addEdge(a3, a5);
		graph.addEdge(a3, a6);
		graph.addEdge(a4, a2);
		graph.addEdge(a5, a4);
		graph.addEdge(a6, a6);
		assertEquals(6, graph.numberOfVertices());
		assertEquals(8, graph.numberOfEdges());

	}

}

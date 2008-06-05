package test.jalds.alds.ds.graphs;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestGraph extends TestCase {
	Vertex a1 = new Vertex("1");
	Vertex a2 = new Vertex("2");
	Vertex a3 = new Vertex("3");
	Vertex a4 = new Vertex("4");
	Vertex a5 = new Vertex("5");
	Vertex a6 = new Vertex("6");

	public void testUnDirectedGraph() {
		Graph graph = new Graph(Graph.Type.UNDIRECTED);
		graph.addVertex(a1);
		graph.addVertex(a2);
		graph.addVertex(a3);
		graph.addVertex(a4);
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
		int[][] is = graph.getAdjacencyMatrix();
		assertEquals(0, is[0][0]);
		assertEquals(1, is[0][1]);
		assertEquals(0, is[0][2]);
		assertEquals(0, is[0][3]);
		assertEquals(1, is[0][4]);

		assertEquals(1, is[1][0]);
		assertEquals(0, is[1][1]);
		assertEquals(1, is[1][2]);
		assertEquals(1, is[1][3]);
		assertEquals(1, is[1][4]);

		assertEquals(0, is[2][0]);
		assertEquals(1, is[2][1]);
		assertEquals(0, is[2][2]);
		assertEquals(1, is[2][3]);
		assertEquals(0, is[2][4]);

		assertEquals(0, is[3][0]);
		assertEquals(1, is[3][1]);
		assertEquals(1, is[3][2]);
		assertEquals(0, is[3][3]);
		assertEquals(1, is[3][4]);

		assertEquals(1, is[4][0]);
		assertEquals(1, is[4][1]);
		assertEquals(0, is[4][2]);
		assertEquals(1, is[4][3]);
		assertEquals(0, is[4][4]);

	}

	public void testDirectedGraph() {
		Graph graph = new Graph(Graph.Type.DIRECTED);
		graph.addVertex(a1);
		graph.addVertex(a2);
		graph.addVertex(a3);
		graph.addVertex(a4);
		graph.addVertex(a5);
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
		int[][] is = graph.getAdjacencyMatrix();

		assertEquals(0, is[0][0]);
		assertEquals(1, is[0][1]);
		assertEquals(0, is[0][2]);
		assertEquals(1, is[0][3]);
		assertEquals(0, is[0][4]);
		assertEquals(0, is[0][5]);

		assertEquals(0, is[1][0]);
		assertEquals(0, is[1][1]);
		assertEquals(0, is[1][2]);
		assertEquals(0, is[1][3]);
		assertEquals(1, is[1][4]);
		assertEquals(0, is[1][5]);

		assertEquals(0, is[2][0]);
		assertEquals(0, is[2][1]);
		assertEquals(0, is[2][2]);
		assertEquals(0, is[2][3]);
		assertEquals(1, is[2][4]);
		assertEquals(1, is[2][5]);

		assertEquals(0, is[3][0]);
		assertEquals(1, is[3][1]);
		assertEquals(0, is[3][2]);
		assertEquals(0, is[3][3]);
		assertEquals(0, is[3][4]);
		assertEquals(0, is[3][5]);

		assertEquals(0, is[4][0]);
		assertEquals(0, is[4][1]);
		assertEquals(0, is[4][2]);
		assertEquals(1, is[4][3]);
		assertEquals(0, is[4][4]);
		assertEquals(0, is[4][5]);
		
		assertEquals(0, is[5][0]);
		assertEquals(0, is[5][1]);
		assertEquals(0, is[5][2]);
		assertEquals(0, is[5][3]);
		assertEquals(0, is[5][4]);
		assertEquals(1, is[5][5]);

	}

}
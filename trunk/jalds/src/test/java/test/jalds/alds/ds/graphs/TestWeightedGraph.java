package test.jalds.alds.ds.graphs;

import jalds.alds.ds.graphs.Vertex;
import jalds.alds.ds.graphs.WeightedGraph;
import jalds.alds.ds.graphs.Graph.Type;
import junit.framework.TestCase;

public class TestWeightedGraph extends TestCase {

	public void testWeightedGraph() {
		WeightedGraph weightedGraph = new WeightedGraph(Type.DIRECTED);
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");
		Vertex x = new Vertex("x");
		Vertex z = new Vertex("z");
		Vertex y = new Vertex("y");

		weightedGraph.addVertex(s).addVertex(t).addVertex(x).addVertex(z)
				.addVertex(y);

		weightedGraph.addEdge(s, t, 10).addEdge(s, y, 5);
		weightedGraph.addEdge(t, x, 1).addEdge(t, y, 2);
		weightedGraph.addEdge(x, z, 4);
		weightedGraph.addEdge(z, x, 6).addEdge(z, s, 7);
		weightedGraph.addEdge(y, z, 2).addEdge(y, t, 3).addEdge(y, x, 9);

		assertEquals(10, weightedGraph.getEdgeWeight(s, t));
		assertEquals(5, weightedGraph.getEdgeWeight(s, y));
		assertEquals(1, weightedGraph.getEdgeWeight(t, x));
		assertEquals(2, weightedGraph.getEdgeWeight(t, y));
		assertEquals(4, weightedGraph.getEdgeWeight(x, z));
		assertEquals(6, weightedGraph.getEdgeWeight(z, x));
		assertEquals(7, weightedGraph.getEdgeWeight(z, s));
		assertEquals(2, weightedGraph.getEdgeWeight(y, z));
		assertEquals(3, weightedGraph.getEdgeWeight(y, t));
		assertEquals(9, weightedGraph.getEdgeWeight(y, x));
	}
}

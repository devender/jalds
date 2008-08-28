package test.jalds.alds.al.graphs;

import java.util.Map;

import jalds.alds.al.graphs.PrimMinimumSpanningTree;
import jalds.alds.ds.graphs.Vertex;
import jalds.alds.ds.graphs.WeightedGraph;
import jalds.alds.ds.graphs.Graph.Type;
import junit.framework.TestCase;

public class TestPrimMinimumSpanningTree extends TestCase {

	public void test() {
		WeightedGraph weightedGraph = new WeightedGraph(Type.UNDIRECTED);
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");
		Vertex g = new Vertex("g");
		Vertex h = new Vertex("h");
		Vertex i = new Vertex("i");

		weightedGraph.addVertex(a).addVertex(b).addVertex(c).addVertex(d).addVertex(e).addVertex(f).addVertex(g).addVertex(h).addVertex(i);
		weightedGraph.addEdge(a, b, 4).addEdge(a, h, 8);
		weightedGraph.addEdge(b, c, 7).addEdge(b, h, 11);
		weightedGraph.addEdge(c, d, 7).addEdge(c, f, 4).addEdge(c, i, 2);
		weightedGraph.addEdge(d, e, 9).addEdge(d, f, 14);
		weightedGraph.addEdge(e, f, 10);
		weightedGraph.addEdge(f, g, 2);
		weightedGraph.addEdge(g, i, 6).addEdge(g, h, 1);
		weightedGraph.addEdge(h, i, 7);

		PrimMinimumSpanningTree minimumSpanningTree = new PrimMinimumSpanningTree(weightedGraph, a);
		Map<Vertex, Vertex> predecessor = minimumSpanningTree.compute().getPredecessorMap();
		assertEquals(a, predecessor.get(b));
		assertEquals(b, predecessor.get(c));
		assertEquals(c, predecessor.get(i));
		assertEquals(c, predecessor.get(f));
		assertEquals(c, predecessor.get(d));
		assertEquals(d, predecessor.get(e));
		assertEquals(f, predecessor.get(g));
		assertEquals(g, predecessor.get(h));
	}
}

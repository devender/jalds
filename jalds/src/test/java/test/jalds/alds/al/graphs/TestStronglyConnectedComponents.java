package test.jalds.alds.al.graphs;

import java.util.Set;

import jalds.alds.al.graphs.StronglyConnectedComponents;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestStronglyConnectedComponents extends TestCase {

	public void test() {
		StronglyConnectedComponents scc = new StronglyConnectedComponents();
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
		Set<Set<Vertex>> set = scc.compute(graph);
		assertEquals(4, set.size());

		for (Set<Vertex> set2 : set) {
			if (set2.size() == 3) {
				assertTrue(set2.contains(a));
				assertTrue(set2.contains(b));
				assertTrue(set2.contains(e));
			}
			if (set2.size() == 1) {
				assertTrue(set2.contains(h));
			}
			if (set2.size() == 2 && set2.contains(g)) {
				assertTrue(set2.contains(f));
			}
			if (set2.size() == 2 && set2.contains(d)) {
				assertTrue(set2.contains(c));
			}
		}
	}
}

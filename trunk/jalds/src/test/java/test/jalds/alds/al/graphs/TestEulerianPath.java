package test.jalds.alds.al.graphs;

import jalds.alds.al.graphs.EulerianPath;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestEulerianPath extends TestCase {

	public void testConnectivity() {
		Vertex r = new Vertex("r");
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");

		Graph graph = new Graph(Graph.Type.UNDIRECTED);
		graph.addVertex(t);
		graph.addEdge(s, r);
		EulerianPath eulerianPath = new EulerianPath(graph);
		eulerianPath.compute();
		assertFalse(eulerianPath.hasEulerianCycle());
		assertFalse(eulerianPath.hasEulerianPath());
	}
}

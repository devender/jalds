package test.jalds.alds.al.graphs;

import java.util.List;

import jalds.alds.al.graphs.TopologicalSort;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestTopologicalSort extends TestCase {

	public void test() {
		Vertex undershorts = new Vertex("undershorts");
		Vertex pants = new Vertex("pants");
		Vertex belt = new Vertex("belt");
		Vertex shirt = new Vertex("shirt");
		Vertex tie = new Vertex("tie");
		Vertex jacket = new Vertex("jacket");
		Vertex socks = new Vertex("socks");
		Vertex shoes = new Vertex("shoes");
		Vertex watch = new Vertex("watch");

		Graph graph = new Graph(Graph.Type.DIRECTED);
		graph.addVertex(watch);

		graph.addEdge(socks, shoes);

		graph.addEdge(shirt, belt);
		graph.addEdge(shirt, tie);

		graph.addEdge(undershorts, shoes);
		graph.addEdge(undershorts, pants);
		graph.addEdge(pants, shoes);
		graph.addEdge(pants, belt);
		graph.addEdge(belt, jacket);

		graph.addEdge(tie, jacket);

		assertEquals(9, graph.numberOfEdges());

		TopologicalSort sort = new TopologicalSort(graph);
		List<Vertex> list = sort.compute();
		System.out.println();
		for (Vertex vertex : list) {
			System.out.print(vertex + "->");
		}

	}
}

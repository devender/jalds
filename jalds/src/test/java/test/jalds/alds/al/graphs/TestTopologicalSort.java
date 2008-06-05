package test.jalds.alds.al.graphs;

import java.util.List;

import jalds.alds.al.graphs.TopologicalSort;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;
import junit.framework.TestCase;

public class TestTopologicalSort extends TestCase {

	public void test() {
		Vertex undershorts = new Vertex(1);
		undershorts.setPrettyName("undershorts");
		Vertex pants = new Vertex(2);
		pants.setPrettyName("pants");
		Vertex belt = new Vertex(3);
		belt.setPrettyName("belt");
		Vertex shirt = new Vertex(4);
		shirt.setPrettyName("shirt");
		Vertex tie = new Vertex(5);
		tie.setPrettyName("tie");
		Vertex jacket = new Vertex(6);
		jacket.setPrettyName("jacket");
		Vertex socks = new Vertex(7);
		socks.setPrettyName("socks");
		Vertex shoes = new Vertex(8);
		shoes.setPrettyName("shoes");
		Vertex watch = new Vertex(9);
		watch.setPrettyName("watch");

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
			System.out.print(vertex.getPrettyName() + "->");
		}

	}
}

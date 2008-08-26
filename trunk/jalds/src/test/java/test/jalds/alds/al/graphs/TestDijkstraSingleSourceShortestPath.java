package test.jalds.alds.al.graphs;

import java.util.Map;

import jalds.alds.al.graphs.DijkstraSingleSourceShortestPath;
import jalds.alds.ds.graphs.Vertex;
import jalds.alds.ds.graphs.WeightedGraph;
import jalds.alds.ds.graphs.Graph.Type;
import junit.framework.TestCase;

public class TestDijkstraSingleSourceShortestPath extends TestCase {

	public void test() {
		WeightedGraph weightedGraph = new WeightedGraph(Type.DIRECTED);
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");
		Vertex x = new Vertex("x");
		Vertex z = new Vertex("z");
		Vertex y = new Vertex("y");

		weightedGraph.addVertex(s).addVertex(t).addVertex(x).addVertex(z).addVertex(y);

		weightedGraph.addEdge(s, t, 10).addEdge(s, y, 5);
		weightedGraph.addEdge(t, x, 1).addEdge(t, y, 2);
		weightedGraph.addEdge(x, z, 4);
		weightedGraph.addEdge(z, x, 6).addEdge(z, s, 7);
		weightedGraph.addEdge(y, z, 2).addEdge(y, t, 3).addEdge(y, x, 9);

		DijkstraSingleSourceShortestPath dijkstraSingleSourceShortestPath = new DijkstraSingleSourceShortestPath();
		dijkstraSingleSourceShortestPath.compute(weightedGraph, s);
		Map<Vertex, Integer> distanceMap = dijkstraSingleSourceShortestPath.getDistanceMap();
		
		assertEquals(8,distanceMap.get(t).intValue());
		assertEquals(9,distanceMap.get(x).intValue());
		assertEquals(5,distanceMap.get(y).intValue());
		assertEquals(7,distanceMap.get(z).intValue());
		
		
	}
}

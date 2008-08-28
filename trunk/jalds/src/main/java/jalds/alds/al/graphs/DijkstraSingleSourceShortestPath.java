/*
 * Copyright 2008 the original author or authors.
 * 
 * http://www.gnu.org/licenses/gpl.txt
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jalds.alds.al.graphs;

import jalds.alds.SortableObject;
import jalds.alds.ds.graphs.Vertex;
import jalds.alds.ds.graphs.WeightedGraph;
import jalds.alds.ds.heaps.Heap;
import jalds.alds.ds.heaps.HeapFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * For a given source vertex (node) in the graph, the algorithm finds the path
 * with lowest cost (i.e. the shortest path) between that vertex and every other
 * vertex. It can also be used for finding costs of shortest paths from a single
 * vertex to a single destination vertex by stopping the algorithm once the
 * shortest path to the destination vertex has been determined.
 * 
 * Dijkstra's algorithm solves the single source shortest paths problem on a
 * weighted directed graph G for the case in which all edge weights are non
 * negative. This algorithm will therefor assume that w(u,v) >=0 for each edge.
 * 
 * Dijkstra's algorithm maintains a set S of vertices whose final shortest path
 * weights from the source s have already been determined. The algorithm
 * repeatedly selects the vertex u with the minimum shortest path estimate, adds
 * u to S and relaxes all edges leaving u.
 * 
 * @author Devender R. Gollapally
 * 
 */
public final class DijkstraSingleSourceShortestPath {
	private final Map<Vertex, Integer> distanceMap;
	private final Map<Vertex, Vertex> predecessorMap;
	private final WeightedGraph graph;
	private final Vertex source;

	/**
	 * Requires a {@link WeightedGraph} and a Source
	 * 
	 * @param graph
	 * @param source
	 */
	public DijkstraSingleSourceShortestPath(WeightedGraph graph, Vertex source) {
		this.graph = graph;
		this.source = source;
		distanceMap = new HashMap<Vertex, Integer>(graph.numberOfVertices());
		predecessorMap = new HashMap<Vertex, Vertex>(graph.numberOfVertices());
	}

	/**
	 * Will compute the shortest path, after this method is called the
	 * distanceMap and the predecessorMap are populated.
	 */
	public DijkstraSingleSourceShortestPath compute() {
		initializeSingleSource(graph, source);
		Set<Vertex> set = new HashSet<Vertex>(graph.numberOfVertices());
		Heap minQueue = createMinQueue(graph, set);

		while (minQueue.size() > 0) {
			@SuppressWarnings("unchecked")
			SortableObject<Vertex> sortableObject = minQueue.extract();
			Vertex u = sortableObject.getObject();
			set.add(u);
			for (Vertex v : graph.getAllAdjacentVertices(u)) {
				relax(u, v, graph.getEdgeWeight(u, v));
			}
			minQueue = createMinQueue(graph, set);
		}
		return this;
	}

	/**
	 * Sets the distance of all vertices to the source to Integer.MAX_VALUE and
	 * all the predecessors to null, initializes the distance of source to
	 * itself to 0
	 * 
	 * @param graph
	 * @param source
	 */
	private void initializeSingleSource(WeightedGraph graph, Vertex source) {
		for (Vertex vertex : graph.getVertices()) {
			distanceMap.put(vertex, Integer.MAX_VALUE);
			predecessorMap.put(vertex, null);
		}
		distanceMap.put(source, 0);
	}

	/**
	 * The process of relaxing an edge consists of testing whether we can
	 * improve the shortest path to v found so far by going through u and if so
	 * updating the distanceMap and the predecessorMap, A relax step may reduce
	 * the value of the shortest path estimate
	 * 
	 * @param u
	 * @param v
	 * @param weight
	 */
	private void relax(Vertex u, Vertex v, int edgeWeightOfuTov) {
		int vDistanceFromSource = distanceMap.get(v).intValue();
		int uDistanceFromSource = distanceMap.get(u).intValue();
		if (vDistanceFromSource > uDistanceFromSource + edgeWeightOfuTov) {
			distanceMap.put(v, uDistanceFromSource + edgeWeightOfuTov);
			predecessorMap.put(v, u);
		}
	}

	/**
	 * Create a Min Priority Queue keyed on the distance map
	 * 
	 * @param graph
	 * @param set
	 * @return
	 */
	private Heap createMinQueue(WeightedGraph graph, Set<Vertex> set) {
		Heap minQueue = HeapFactory.minHeap();
		for (Vertex vertex : graph.getVertices()) {
			if (!set.contains(vertex))
				minQueue.insert(new SortableObject<Vertex>(vertex, distanceMap.get(vertex).intValue()));
		}
		return minQueue;
	}

	/**
	 * Returns the Distance Map that contains the distance of each node from the
	 * source
	 * 
	 * @return Map<Vertex, Integer>
	 */
	public Map<Vertex, Integer> getDistanceMap() {
		return distanceMap;
	}

	/**
	 * Returns the Predecessor Map that contains the predecessor of each node.
	 * Can use this to build a path from the source to the node.
	 * 
	 * @return Map<Vertex, Integer>
	 */
	public Map<Vertex, Vertex> getPredecessorMap() {
		return predecessorMap;
	}

}

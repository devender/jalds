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

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Breadth First Search(BFS) is one of the simplest algorithms for searching a
 * graph, given a graph and a source vertex (s), BFS systematically explores the
 * edges of G to discover every vertex that is reachable from s. It computes the
 * distance from s to each reachable vertex. It also produces a breadth first
 * tree with root s that contains all reachable vertices. For any vertex v
 * reachable from s the path in the BFS tree from s to v corresponds to the
 * shortest path from s to v in the graph. This works on both directed and un
 * directed graphs.
 * <p>
 * BFS is so named cause it expands the frontier between discovered and
 * undiscovered vertices uniformly across the breadth of the frontier.
 * <p>
 * To keep track of progress breadth first search colors each vertex white, gray
 * or black. All vertices start out white and my later become gray and then
 * black.A vertex is discovered the first time it is encountered during the
 * search at which time it becomes gray. Gray and black vertices, therefore have
 * been discovered but BFS distinguishes between them to ensure that the search
 * proceeds in a breadth first manner.
 * <p>
 * Source Introduction To Algorithms
 * 
 * @author Devender
 * 
 */
public class BreadthFirstGraphSearch {

	private Graph graph;
	private Vertex source;
	private Map<Vertex, Integer> distanceMap;
	private Map<Vertex, Color> colorMap;
	private Map<Vertex, Vertex> predecessorMap;

	/**
	 * 
	 * @param graph
	 *            The graph on which the Breadth First Search is to be performed
	 * @param source
	 *            The Source vertex
	 */
	public BreadthFirstGraphSearch(Graph graph, Vertex source) {
		this.graph = graph;
		this.source = source;
		distanceMap = new HashMap<Vertex, Integer>();
		colorMap = new HashMap<Vertex, Color>();
		predecessorMap = new HashMap<Vertex, Vertex>();
	}

	/**
	 * Computes the shortest distance from each vertex to the source.
	 * <p>
	 * Colors the source grey since it has already been discovered, and inserts
	 * into a queue.Then runs a while loop as long as there are any nodes in the
	 * queue.
	 * <p>
	 * In the while loop, extracts a node from the queue, then get all of it
	 * adjacent nodes and for each adjacent node runs a for loop
	 * <p> * For loop: if a node is white, then colors the node grey and sets
	 * the distance from it to the source as the distance from its parent to the
	 * source +1, enter it into the tail of queue. Else color it black.
	 * <p>
	 * Runs in O(number of vertices + number of edges)
	 * 
	 * @return Map<Vertex, Integer> Returns a map of each vertex and the
	 *         distance from it to the source vertex.
	 */
	public Map<Vertex, Integer> compute() {
		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			colorMap.put(vertex, Color.WHITE);
			distanceMap.put(vertex, 0);
			predecessorMap.put(vertex, null);
		}

		colorMap.put(source, Color.GRAY);
		distanceMap.put(source, 0);

		Queue<Vertex> queue = new ArrayBlockingQueue<Vertex>(graph
				.numberOfVertices(), true);
		queue.add(source);

		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove();
			Vertex[] adjacentVertices = graph.getAllAdjacentVertices(vertex);
			if (adjacentVertices != null) {
				for (Vertex adjacentVertex : adjacentVertices) {
					if (colorMap.get(adjacentVertex).equals(Color.WHITE)) {
						colorMap.put(adjacentVertex, Color.GRAY);
						distanceMap.put(adjacentVertex,
								distanceMap.get(vertex) + 1);
						predecessorMap.put(adjacentVertex, vertex);
						queue.add(adjacentVertex);
					}
					colorMap.put(vertex, Color.BLACK);
				}
			}
		}
		return distanceMap;
	}

	/**
	 * Returns a String which shows the shortest path between the source vertex
	 * and any other vertex
	 * 
	 * @param v
	 *            a vertex in the graph.
	 * @return
	 */
	public String printShortestPathTo(Vertex v) {
		StringBuffer buffer = new StringBuffer();
		printShortestPath(source, v, buffer);
		return buffer.toString();
	}

	private void printShortestPath(Vertex s, Vertex v, StringBuffer buffer) {
		if (v.equals(source)) {
			buffer.append(source.getName());
			buffer.append("->");
		} else {
			if (predecessorMap.get(v) == null) {
				buffer.append("No Path");
			} else {
				printShortestPath(s, predecessorMap.get(v), buffer);
				buffer.append(v.getName());
				buffer.append("->");
			}
		}
	}
}

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

import java.util.Set;

/**
 * A Eulerian path is a path in a graph which visits each edge exactly once.A
 * Eulerian circuit is an eulerian path which starts and ends on the same
 * vertex.
 * <ul>
 * <li> A connected un-directed graph has a Eulerian cycle if every graph vertex
 * has an even degree</li>
 * <li> A connected un-directed graph has a Eulerian cycle if it can be
 * decomposed into edge disjoint cycles. </li>
 * <li> A connected un-directed graph has a Eulerian path if at most two
 * vertices in the graph are of odd degree.</li>
 * </ul>
 * 
 * <ul>
 * <li> A directed connected graph has a Eulerian cycle if every vertex has
 * equal in and out degrees. </li>
 * <li> A directed graph has an eulerian path if and only if it is connected and
 * each vertex except 2 have the same in-degree as out-degree, and one of those
 * 2 vertices has out-degree with one greater than in-degree</li>
 * </ul>
 * 
 * Check out http://www.graph-magics.com/articles/euler.php
 * 
 * @author Devender Gollapally
 * 
 */
public class EulerianPath {
	private Graph graph;
	private boolean eulerianPath = false;
	private boolean eulerianCycle = false;

	/**
	 * 
	 * @param graph
	 *            The graph in which a Euler's path is searched.
	 */
	public EulerianPath(Graph graph) {
		this.graph = graph;
	}

	public void compute() {
		if (!connected()) {
			return;
		}

		switch (graph.getType()) {
		case DIRECTED:
			directed();
			break;
		case UNDIRECTED:
			undirected();
			break;
		}
	}

	private boolean connected() {
		BreadthFirstGraphSearch bfs = new BreadthFirstGraphSearch(graph, graph.getAdjacencyList()[0][0]);
		bfs.compute();
		return bfs.isConnected();
	}

	private void directed() {
		int nodeWithOutSameInOutDegree = 0;
		// the vertices that do not have the same in and out degree, if it is
		// greater than 2 we dont care
		Vertex[] vertices = new Vertex[2];
		int i = 0;
		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			if (graph.getInDegree(vertex) != graph.getOutDegree(vertex)) {
				nodeWithOutSameInOutDegree += 1;
				if (i < 2) {
					vertices[i] = vertex;
					i += 1;
				}
			}
		}
		if (nodeWithOutSameInOutDegree == 0) {
			eulerianCycle = true;
			eulerianPath = true;
		} else if (nodeWithOutSameInOutDegree == 2) {
			int vertexOneIndegree = graph.getInDegree(vertices[0]);
			int vertexOneOutdegree = graph.getOutDegree(vertices[0]);
			int vertexTwoIndegree = graph.getInDegree(vertices[1]);
			int vertexTwoOutdegree = graph.getOutDegree(vertices[1]);
			if ((vertexOneIndegree - vertexOneOutdegree == 1 || vertexOneOutdegree - vertexOneIndegree == 1)
					&& (vertexTwoIndegree - vertexTwoOutdegree == 1 || vertexTwoOutdegree - vertexTwoIndegree == 1)) {
				eulerianPath = true;
			}
		}

	}

	private void undirected() {
		int numberOfNodesWithOddDegree = 0;
		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			numberOfNodesWithOddDegree += graph.getInDegree(vertex) % 2;
		}
		if (numberOfNodesWithOddDegree == 0) {
			eulerianCycle = true;
			eulerianPath = true;
		} else if (numberOfNodesWithOddDegree == 2) {
			eulerianPath = true;
		}
	}

	/**
	 * 
	 * @return boolean returns true if the given graph has an eular path.
	 */
	public boolean hasEulerianPath() {
		return eulerianPath;
	}

	/**
	 * 
	 * @return boolean returns true if the given graph has an eular cycle
	 */
	public boolean hasEulerianCycle() {
		return eulerianCycle;
	}

}

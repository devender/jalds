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
 * <li> A directed graph has Eulerian cycle if it is connected and can be
 * decomposed into edge disjoint directed cycles </li>
 * </ul>
 * 
 * @author Devender Gollapally
 * 
 */
public class EulerianPath {
	private Graph graph;

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
			break;
		case UNDIRECTED:
			break;
		}
	}

	private boolean connected() {
		BreadthFirstGraphSearch bfs = new BreadthFirstGraphSearch(graph, graph.getAdjacencyList()[0][0]);
		bfs.compute();
		
		return false;
	}

	private void directed() {

	}

	private void undirected() {

	}
}

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

import java.util.LinkedList;
import java.util.List;

/**
 * DAG or a directed acyclic graph is a directed graph with no cycles. A
 * topological sort of a dag G(V,E) is a linear ordering of all its vertices
 * such that if G contains an edge (u,v) the u will appear before v in the
 * ordering, a topological sort can be viewed as an ordering of its vertices
 * along a horizontal line so that all directed edges go from left to right.
 * <p>
 * This produces a linked list of vertices in the given graph that are in
 * topological order.
 * <p>
 * In order to compute the topological order, the Graph is first examined using
 * a depth first search and when ever a vertex has been completely explored (i.e
 * finished) we add it to the front of the list.
 * 
 * @see DepthFirstSearch
 * @see FinishedEventObserver
 * 
 * @author Devender Gollapally
 * 
 */
public class TopologicalSort implements FinishedEventObserver {

	private Graph graph;
	private List<Vertex> list;

	/**
	 * The graph from which a topological order of vertices is needed.
	 * 
	 * @param graph
	 */
	public TopologicalSort(Graph graph) {
		this.graph = graph;
		list = new LinkedList<Vertex>();
	}

	/**
	 * Returns a list of vertices of the given graph sorted in topological
	 * order.
	 * 
	 * @return
	 */
	public List<Vertex> compute() {
		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
		depthFirstSearch.registerFinishedEventObserver(this);
		depthFirstSearch.compute();
		return list;
	}

	public void update(Vertex vertex, int discoveredTime, int finishedTime) {
		list.add(0, vertex);
	}
}

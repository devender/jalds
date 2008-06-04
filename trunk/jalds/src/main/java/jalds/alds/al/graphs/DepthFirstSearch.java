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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

/**
 * Searches deaper in the graph whenever possible. In DFS edges are explored out
 * of the most recently discovered vertex v that still has unexplored edges
 * leaving it. When all of v edges are explored the search backtracks to explore
 * edges leaving the vertex from which v was discovered. The process continues
 * till we have discovered all the vertices that are reachable from the original
 * source. If any undiscovered vertices remain then one of them is selected as
 * the new source and the search is repeated.
 * 
 * O(V+E)
 * 
 * @author Devender Gollapally
 * 
 */
public class DepthFirstSearch {

	private Graph graph;
	private int time = 0;
	private Map<Vertex, Color> colorMap;
	private Map<Vertex, Vertex> predecessorMap;
	private Map<Vertex, Integer> discoveredAtMap;
	private Map<Vertex, Integer> finishedAtMap;
	private Map<Vertex, Vertex> treeEdge;
	private Map<Vertex, Vertex> blackEdge;

	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
		colorMap = new HashMap<Vertex, Color>();
		predecessorMap = new HashMap<Vertex, Vertex>();
		discoveredAtMap = new HashMap<Vertex, Integer>();
		finishedAtMap = new HashMap<Vertex, Integer>();
		treeEdge = new HashMap<Vertex, Vertex>();
		blackEdge = new HashMap<Vertex, Vertex>();
	}

	public void compute() {
		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			colorMap.put(vertex, Color.WHITE);
			predecessorMap.put(vertex, null);
		}

		for (Vertex vertex : set) {
			if (colorMap.get(vertex).compareTo(Color.WHITE) == 0) {
				visit(vertex);
			}
		}
	}

	/**
	 * Visits the given vertex, gets all of its adjacent vertices and visits
	 * them recursively. while exploring each edge it will classify each edge as
	 * either a tree edge or a black edge. A tree edge (u,v) is an edge that was
	 * first discovered if v was first discovered from u that is V was white
	 * when it was discovered.A black edge (u,v) is an edge connecting u to an
	 * ancestor, that is v is gray when (u,v) is discovered.
	 * 
	 * @param vertex
	 */
	private void visit(Vertex vertex) {
		colorMap.put(vertex, Color.GRAY);
		time = time + 1;
		discoveredAtMap.put(vertex, time);

		Vertex[] vertices = graph.getAllAdjacentVertices(vertex);
		if (vertices != null) {
			for (Vertex adjacentVertex : vertices) {
				switch (colorMap.get(adjacentVertex)) {
				case WHITE:
					predecessorMap.put(adjacentVertex, vertex);
					visit(adjacentVertex);
					treeEdge.put(vertex, adjacentVertex);
					break;
				case GRAY:
					blackEdge.put(vertex, adjacentVertex);
					break;
				}
			}
		}

		time = time + 1;
		finishedAtMap.put(vertex, time);
		colorMap.put(vertex, Color.BLACK);
	}

	public Map<Vertex, Vertex> getPredecessorMap() {
		return predecessorMap;
	}

	public Map<Vertex, Integer> getDiscoveredAtMap() {
		return discoveredAtMap;
	}

	public Map<Vertex, Integer> getFinishedAtMap() {
		return finishedAtMap;
	}

	public Map<Vertex, Vertex> getTreeEdge() {
		return treeEdge;
	}

	/**
	 * If the size of this Map is greater than 1, it means that we have an
	 * acyclic map.
	 * 
	 * @return
	 */
	public Map<Vertex, Vertex> getBlackEdge() {
		return blackEdge;
	}

}

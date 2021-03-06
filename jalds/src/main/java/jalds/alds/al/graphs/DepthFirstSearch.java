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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Searches deeper in the graph whenever possible. In DFS edges are explored out
 * of the most recently discovered vertex v that still has unexplored edges
 * leaving it. When all of v's edges are explored the search backtracks to
 * explore edges leaving the vertex from which v was discovered. The process
 * continues till we have discovered all the vertices that are reachable from
 * the original source. If any undiscovered vertices remain then one of them is
 * selected as the new source and the search is repeated.
 * <p>
 * Runs in O(V+E) time
 * <p>
 * DFS forms a a depth first forest containing several depth first trees.
 * 
 * @author Devender Gollapally
 * 
 */
public final class DepthFirstSearch {

	private final Graph graph;
	private int time = 0;
	private final Map<Vertex, Color> colorMap;
	private final Map<Vertex, Vertex> predecessorMap;
	private final Map<Vertex, Integer> discoveredAtMap;
	private final Map<Vertex, Integer> finishedAtMap;
	private final Map<Vertex, Vertex> treeEdge;
	private final Map<Vertex, Vertex> blackEdge;
	private final List<FinishedEventObserver> finishedEventObserversList;
	private final Set<Set<Vertex>> depthFirstForest;

	/**
	 * 
	 * @param graph
	 *            The graph on which Depth First Search is to be run.
	 */
	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
		colorMap = new HashMap<Vertex, Color>();
		predecessorMap = new HashMap<Vertex, Vertex>();
		discoveredAtMap = new HashMap<Vertex, Integer>();
		finishedAtMap = new HashMap<Vertex, Integer>();
		treeEdge = new HashMap<Vertex, Vertex>();
		blackEdge = new HashMap<Vertex, Vertex>();
		finishedEventObserversList = new ArrayList<FinishedEventObserver>();
		depthFirstForest = new HashSet<Set<Vertex>>();
	}

	/**
	 * Run the depth first search on the given graph.
	 */
	public void compute() {
		init();

		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			process(vertex);
		}
	}

	/**
	 * Initializes the DFS by coloring all vertices white.
	 * 
	 * @return
	 */
	Set<Vertex> init() {
		Set<Vertex> set = graph.getVertices();
		for (Vertex vertex : set) {
			colorMap.put(vertex, Color.WHITE);
			predecessorMap.put(vertex, null);
		}
		return set;
	}

	/**
	 * Run DFS on the given vertex.
	 * 
	 * @param vertex
	 */
	void process(Vertex vertex) {
		if (colorMap.get(vertex).compareTo(Color.WHITE) == 0) {
			Set<Vertex> depthFirstTree = new HashSet<Vertex>();
			depthFirstTree.add(vertex);
			visit(vertex, depthFirstTree);
			depthFirstForest.add(depthFirstTree);
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
	private void visit(Vertex vertex, Set<Vertex> depthFirstTree) {
		depthFirstTree.add(vertex);
		colorMap.put(vertex, Color.GRAY);
		time = time + 1;
		discoveredAtMap.put(vertex, time);

		Vertex[] vertices = graph.getAllAdjacentVertices(vertex);
		if (vertices != null) {

			for (Vertex adjacentVertex : vertices) {
				switch (colorMap.get(adjacentVertex)) {
				case WHITE:
					predecessorMap.put(adjacentVertex, vertex);
					visit(adjacentVertex, depthFirstTree);
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
		notifyFinishedEvent(vertex);
		colorMap.put(vertex, Color.BLACK);
	}

	/**
	 * Notifies an registered Finished Event Observers about a finished event.
	 * 
	 * @param vertex
	 */
	private void notifyFinishedEvent(Vertex vertex) {
		for (FinishedEventObserver eventObserver : finishedEventObserversList) {
			eventObserver.update(vertex, discoveredAtMap.get(vertex),
					finishedAtMap.get(vertex));
		}
	}

	/**
	 * Useful if you want to observe when depth first is finished processing a
	 * node
	 * 
	 * @param finishedEventObserver
	 */
	public void registerFinishedEventObserver(
			FinishedEventObserver finishedEventObserver) {
		finishedEventObserversList.add(finishedEventObserver);
	}

	/**
	 * 
	 * @return Map<Vertex, Vertex> A map of each vertex and its predecessor
	 */
	public Map<Vertex, Vertex> getPredecessorMap() {
		return predecessorMap;
	}

	/**
	 * 
	 * @return Map<Vertex, Integer> a map of vertices and when they were
	 *         discovered.
	 */
	public Map<Vertex, Integer> getDiscoveredAtMap() {
		return discoveredAtMap;
	}

	/**
	 * 
	 * @return Map<Vertex, Integer> a map of vertices and when they were
	 *         finished.
	 */
	public Map<Vertex, Integer> getFinishedAtMap() {
		return finishedAtMap;
	}

	/**
	 * Returns all tree edges, A tree edge or a white edge is an edge that
	 * represents the edge between the parent and the child.
	 * 
	 * @return
	 */
	public Map<Vertex, Vertex> getTreeEdges() {
		return treeEdge;
	}

	/**
	 * If the size of this Map is greater than 1, it means that we have an
	 * acyclic map.
	 * 
	 * @return
	 */
	public Map<Vertex, Vertex> getBlackEdges() {
		return blackEdge;
	}

	/**
	 * Returns true of the graph has no cycles.
	 * 
	 * @return
	 */
	public boolean isAcyclic() {
		return blackEdge.size() == 0;

	}

	/**
	 * Returns the depth first forest created by running DFS
	 * 
	 * @return
	 */
	public Set<Set<Vertex>> getDepthFirstForest() {
		return depthFirstForest;
	}
}

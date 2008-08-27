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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jalds.alds.SortableObject;
import jalds.alds.ds.graphs.Vertex;
import jalds.alds.ds.graphs.WeightedGraph;
import jalds.alds.ds.heaps.Heap;
import jalds.alds.ds.heaps.HeapFactory;

/**
 * A spanning tree (T) is a graph that connects all the vertices of a graph. A
 * Minimum spanning tree is a spanning tree whose total weight is minimized.
 * 
 * @author Devender Gollapally
 * 
 */
public class PrimMinimumSpanningTree {
	private Map<Vertex, Integer> keyMap;
	private Map<Vertex, Vertex> predecessorMap;

	public void compute(WeightedGraph graph, Vertex root) {
		initialize(graph, root);
		Set<Vertex> removed = new HashSet<Vertex>();
		Heap queue = createMinPriorityQueue(graph, removed);

		while (queue.size() > 0) {
			SortableObject<Vertex> sortableObject = queue.extract();
			Vertex u = sortableObject.getObject();
			removed.add(u);
			for (Vertex v : graph.getAllAdjacentVertices(u)) {
				if (!removed.contains(v) && graph.getEdgeWeight(u, v) < keyMap.get(v)) {
					predecessorMap.put(v, u);
					keyMap.put(v, graph.getEdgeWeight(u, v));
				}
			}
			queue = createMinPriorityQueue(graph, removed);
		}

	}

	private Heap createMinPriorityQueue(WeightedGraph graph, Set<Vertex> removed) {
		Heap heap = HeapFactory.minHeap();
		for (Vertex vertex : graph.getVertices()) {
			if (!removed.contains(vertex)) {
				heap.insert(new SortableObject<Vertex>(vertex, keyMap.get(vertex)));
			}
		}
		return heap;
	}

	private void initialize(WeightedGraph graph, Vertex root) {
		keyMap = new HashMap<Vertex, Integer>(graph.numberOfVertices());
		predecessorMap = new HashMap<Vertex, Vertex>(graph.numberOfVertices());

		for (Vertex vertex : graph.getVertices()) {
			keyMap.put(vertex, Integer.MAX_VALUE);
			predecessorMap.put(vertex, null);
		}

		keyMap.put(root, 0);
	}

	public Map<Vertex, Integer> getKeyMap() {
		return keyMap;
	}

	public Map<Vertex, Vertex> getPredecessorMap() {
		return predecessorMap;
	}
}

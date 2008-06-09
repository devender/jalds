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

/**
 * Used to transpose a directed graph, if a graph has an edge (u,v) the
 * transposed graph will have the edge (v,u)
 * 
 * @author Devender Gollapally
 * 
 */
public class TransposeGraphService {

	/**
	 * Returns a new graph which is the transpose of the given one
	 * 
	 * @param graph
	 * @return
	 */
	public Graph transpose(Graph graph) {
		if (graph.getType() == Graph.Type.DIRECTED) {
			Graph transposedGraph = new Graph(Graph.Type.DIRECTED);

			Vertex[][] adjacencyList = graph.getAdjacencyList();

			for (Vertex[] vertices : adjacencyList) {
				if (vertices.length > 0) {
					Vertex from = vertices[0];
					for (int i = 1; i < vertices.length; i++) {
						Vertex to = vertices[i];
						transposedGraph.addEdge(to, from);
					}
				}
			}

			return transposedGraph;
		}
		return null;
	}
}

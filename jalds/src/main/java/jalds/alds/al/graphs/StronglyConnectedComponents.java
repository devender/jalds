package jalds.alds.al.graphs;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.QuickSort;
import jalds.alds.ds.graphs.Graph;
import jalds.alds.ds.graphs.Vertex;

import java.util.Map;
import java.util.Set;

/**
 * Finds the strongly connected components in a given directed graph
 * 
 * A strongly connected component of a directed graph G=(V,E) is a max set of
 * vertices C such that for every pair of vertices u and v in C, u is reachable
 * from v and v is reachable from u.
 * 
 * To find the strongly connected components in given graph G
 * <ol>
 * <li>Run DFS on the given graph</li>
 * <li>Create G^t which is the transpose of the given graph</li>
 * <li>Run DFS on G^t, but in the main loop of DFS we consider the vertices in
 * order of decreasing finished time produced by 1.
 * <li>
 * <li>Output the depth first forest produced by 3</li>
 * </ol>
 * 
 * 
 * @author Devender Gollapally
 * 
 */
public class StronglyConnectedComponents {

	public Set<Set<Vertex>> compute(Graph graph) {
		// Compute depth first on the original graph
		DepthFirstSearch dfs = new DepthFirstSearch(graph);
		dfs.compute();

		// transpose the graph
		TransposeGraphService tgs = new TransposeGraphService();
		Graph transposedGraph = tgs.transpose(graph);

		// compute depth first on the transposed graph but order the vertices in
		// decending finished time order
		DepthFirstSearch dfst = new DepthFirstSearch(transposedGraph);
		dfst.init();
		Vertex[] list = getDescendingFinishedTime(dfs.getFinishedAtMap());
		for (Vertex vertex : list) {
			dfst.process(vertex);
		}

		Set<Set<Vertex>> depthFirstForest = dfst.getDepthFirstForest();

		for (Set<Vertex> depthFirstTree : depthFirstForest) {
			print(depthFirstTree);
		}

		return depthFirstForest;
	}

	private void print(Set<Vertex> list) {
		for (Vertex vertex : list) {
			System.out.print(vertex);
		}
		System.out.println();
	}

	private Vertex[] getDescendingFinishedTime(Map<Vertex, Integer> map) {
		SortableObject[] sortableObjects = new SortableObject[map.size()];
		int j = 0;
		for (Vertex vertex : map.keySet()) {
			sortableObjects[j++] = new SortableObject(vertex, map.get(vertex));
		}
		Sort sort = new QuickSort();
		sortableObjects = sort.sort(sortableObjects);
		Vertex[] vertexs = new Vertex[sortableObjects.length];

		j = 0;
		for (int i = sortableObjects.length - 1; i >= 0; i--) {
			vertexs[j++] = (Vertex) sortableObjects[i].getObject();
		}

		return vertexs;
	}

}

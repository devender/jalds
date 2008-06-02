package jalds.alds.ds.graphs;

/**
 * A simple class to represent a graph, has useful methods to add an edge and
 * vertex.
 * 
 * @author Devender Gollapally
 * 
 */
public class Graph {
	private Vertex[][] adjacencyList = null;
	private Type type;

	/**
	 * Creates a new graph which can be either directed or un-directed
	 * 
	 * @param type
	 */
	public Graph(Type type) {
		adjacencyList = new Vertex[0][];
		this.type = type;
	}

	/**
	 * Adds a new vertex to the graph
	 * 
	 * @param vertex
	 */
	public void addVertex(Vertex vertex) {
		// check for duplicates
		if (getVertexIndex(vertex) != -1) {
			return;
		}

		int preLength = adjacencyList.length;
		increaseVertices();
		adjacencyList[preLength] = new Vertex[1];
		adjacencyList[preLength][0] = vertex;
	}

	/**
	 * Adds an edge from a into b, if it is an UN directed graph will also add
	 * the edge from b into a
	 * 
	 * NOTE: At present does not check for duplicates.
	 * 
	 * @param a
	 * @param b
	 */
	public void addEdge(Vertex a, Vertex b) {
		for (int i = 0; i < adjacencyList.length; i++) {
			Vertex[] vertexs = adjacencyList[i];
			int preLength = vertexs.length;

			if (vertexs[0].equals(a)) {
				vertexs = increaseSize(vertexs);
				vertexs[preLength] = b;
				adjacencyList[i] = vertexs;
			} else if (type.equals(Type.UNDIRECTED) && vertexs[0].equals(b)) {
				vertexs = increaseSize(vertexs);
				vertexs[preLength] = a;
				adjacencyList[i] = vertexs;
			}

		}
	}

	/**
	 * Increases size of the given Vertex[] array by 1
	 * 
	 * @param vertexs
	 * @return
	 */
	private Vertex[] increaseSize(Vertex[] vertexs) {
		Vertex[] newVertexs = new Vertex[vertexs.length + 1];
		for (int i = 0; i < vertexs.length; i++) {
			newVertexs[i] = vertexs[i];
		}
		return newVertexs;
	}

	/**
	 * Increases the total number of vertices in this graph by one
	 * 
	 * @return
	 */
	private Vertex[][] increaseVertices() {
		Vertex[][] newAdjacencyList = new Vertex[adjacencyList.length + 1][];

		for (int i = 0; i < adjacencyList.length; i++) {
			newAdjacencyList[i] = adjacencyList[i];
		}

		adjacencyList = newAdjacencyList;
		return adjacencyList;
	}

	/**
	 * Returns the total number of vertices in this graph
	 * 
	 * @return
	 */
	public int numberOfVertices() {
		return adjacencyList.length;
	}

	/**
	 * Returns the total number of edges in this graph
	 * 
	 * @return
	 */
	public int numberOfEdges() {
		int numberOfEdges = 0;
		for (int i = 0; i < numberOfVertices(); i++) {
			numberOfEdges = numberOfEdges + adjacencyList[i].length - 1;
		}
		return numberOfEdges;
	}

	/**
	 * Returns the internal graph representation in the form of an adjacency
	 * list
	 * 
	 * @return
	 */
	public Vertex[][] getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * Returns the internal graph representation in the form of a adjacency
	 * matrix, NOTE this method will construct the matrix everytime you call it,
	 * since graph uses a list and not the matrix .
	 */
	public int[][] getAdjacencyMatrix() {
		int[][] matrix = new int[numberOfVertices()][];
		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = new int[numberOfVertices()];
		}

		for (int i = 0; i < numberOfVertices(); i++) {
			Vertex[] edges = adjacencyList[i];
			if (edges.length > 1) {
				int indexOfRow = getVertexIndex(edges[0]);
				for (int j = 1; j < edges.length; j++) {
					int indexOfColumn = getVertexIndex(edges[j]);
					matrix[indexOfRow][indexOfColumn] = 1;
				}
			}
		}

		return matrix;
	}

	/**
	 * Returns the index of the vertex in the adjacency list if the vertex is
	 * not present else returns -1
	 * 
	 * @param vertex
	 * @return
	 */
	private int getVertexIndex(Vertex vertex) {
		for (int i = 0; i < numberOfVertices(); i++) {
			if (adjacencyList[i][0].equals(vertex)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Prints the graph's adjacency list
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < numberOfVertices(); i++) {
			Vertex[] vertexs = adjacencyList[i];
			for (int j = 0; j < vertexs.length; j++) {
				builder.append(vertexs[j]);
				builder.append("->");
			}
			builder.append("/ \n");
		}

		return builder.toString();
	}

	/**
	 * Useful to print out the graph as an adjacency matrix
	 * 
	 * @return
	 */
	public String matrixToString() {
		StringBuilder builder = new StringBuilder();
		int[][] matrix = getAdjacencyMatrix();
		for (int i = 0; i < matrix.length; i++) {
			int edges[] = matrix[i];
			for (int j = 0; j < edges.length; j++) {
				builder.append(edges[j]);
				builder.append(" ");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	/**
	 * Represents the type of Graph either a directed or un-directed
	 * 
	 * @author Devender Gollapally
	 * 
	 */
	public enum Type {
		DIRECTED, UNDIRECTED;
	}

}

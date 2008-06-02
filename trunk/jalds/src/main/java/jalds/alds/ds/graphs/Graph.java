package jalds.alds.ds.graphs;

public class Graph {
	private Vertex[][] adjacencyList = null;
	private Type type;

	public Graph(Type type) {
		adjacencyList = new Vertex[0][];
		this.type = type;
	}

	public void addVertex(Vertex vertex) {
		int preLength = adjacencyList.length;
		increaseVertices();
		adjacencyList[preLength] = new Vertex[1];
		adjacencyList[preLength][0] = vertex;
	}

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

	private Vertex[] increaseSize(Vertex[] vertexs) {
		Vertex[] newVertexs = new Vertex[vertexs.length + 1];
		for (int i = 0; i < vertexs.length; i++) {
			newVertexs[i] = vertexs[i];
		}
		return newVertexs;
	}

	private Vertex[][] increaseVertices() {
		Vertex[][] newAdjacencyList = new Vertex[adjacencyList.length + 1][];

		for (int i = 0; i < adjacencyList.length; i++) {
			newAdjacencyList[i] = adjacencyList[i];
		}

		adjacencyList = newAdjacencyList;
		return adjacencyList;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < adjacencyList.length; i++) {
			Vertex[] vertexs = adjacencyList[i];
			for (int j = 0; j < vertexs.length; j++) {
				builder.append(vertexs[j]);
				builder.append("->");
			}
			builder.append("/ \n");
		}

		return builder.toString();
	}

	public enum Type {
		DIRECTED, UNDIRECTED;
	}

}

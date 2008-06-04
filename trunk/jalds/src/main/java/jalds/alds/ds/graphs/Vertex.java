package jalds.alds.ds.graphs;

/**
 * Represents a Vertex in a graph.
 * 
 * @author Devender
 * 
 */
public class Vertex {

	private int name;

	public Vertex(int name) {
		this.name = name;
	}

	public int getName() {
		return name;
	}

	public String toString() {
		return Integer.toString(name);
	}

	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Vertex other = (Vertex) obj;

		return (this.name == other.name);
	}

	public int hashCode() {
		return 37 * name;
	}

}

package jalds.alds.ds.graphs;

public class Vertex {

	private int name;

	public Vertex(int name) {
		this.name = name;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
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
}

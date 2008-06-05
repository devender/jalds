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
package jalds.alds.ds.graphs;

/**
 * Represents a Vertex in a graph.
 * 
 * @author Devender
 * 
 */
public class Vertex {

	private int name;
	private String prettyName;

	public Vertex(int name) {
		this.name = name;
	}

	public void setPrettyName(String prettyName) {
		this.prettyName = prettyName;
	}

	public String getPrettyName() {
		return prettyName;
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

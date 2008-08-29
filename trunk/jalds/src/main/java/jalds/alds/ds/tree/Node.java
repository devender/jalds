package jalds.alds.ds.tree;

import jalds.alds.SortableObject;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {

	private Node<T> parent;
	private List<Node<T>> children = new LinkedList<Node<T>>();
	private SortableObject<T> sortableObject;

	public Node(SortableObject<T> sortableObject) {
		this.sortableObject = sortableObject;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	public boolean containsChild(Node<T> child) {
		return children.contains(child);
	}

	public Node<T> addChild(SortableObject<T> child) {
		Node<T> node = new Node<T>(child);
		children.add(node);
		node.setParent(this);
		return this;
	}

	public void setSortableObject(SortableObject<T> sortableObject) {
		this.sortableObject = sortableObject;
	}

	public SortableObject<T> getSortableObject() {
		return sortableObject;
	}

	/**
	 * Two Nodes are equal if they have the same parent and the value of their
	 * sortable objects is the same.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}
		Node other = (Node) obj;
		boolean hasSameParent = false;

		if (this.getParent() == null && other.getParent() == null) {
			hasSameParent = true;
		} else if (this.getParent() != null && other.getParent() != null && this.getParent().equals(other.getParent())) {
			hasSameParent = true;
		}

		if (hasSameParent) {
			boolean hasSameKey = false;
			if (this.getSortableObject() == null && other.getSortableObject() == null) {
				hasSameKey = true;
			} else if (this.getSortableObject() != null && other.getSortableObject() != null && this.getSortableObject().equals(other.getSortableObject())) {
				hasSameKey = true;
			}
			return hasSameKey;
		} else {
			return false;
		}
	}
}

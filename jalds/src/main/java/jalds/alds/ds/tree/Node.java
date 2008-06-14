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
package jalds.alds.ds.tree;

import jalds.alds.SortableObject;

/**
 * A node in the tree, User will not have to use this class, it is used
 * internally by the package.
 * 
 * @author Devender Gollapally
 * 
 */
public class Node {

	private Node parent;
	private Node left;
	private Node right;
	private SortableObject sortableObject;

	/**
	 * Create an empty node.
	 */
	public Node() {
	}

	/**
	 * Creates an node whose key is set to the supplied sortable object
	 * 
	 * @param sortableObject
	 */
	public Node(SortableObject sortableObject) {
		this.sortableObject = sortableObject;
	}

	/**
	 * Returns the parent of this node.
	 * 
	 * @return Node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Set the parent of this node.
	 * 
	 * @param parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Returns the left child.
	 * 
	 * @return Node
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * Set the Left child.
	 * 
	 * @param left
	 */
	public void setLeft(Node left) {
		this.left = left;
		if (left != null)
			left.setParent(this);
	}

	/**
	 * Returns the Right child of this node.
	 * 
	 * @return Node
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * Set the right child.
	 * 
	 * @param right
	 */
	public void setRight(Node right) {
		this.right = right;
		if (right != null)
			right.setParent(this);
	}

	/**
	 * Returns the Sortable Object Stored in this node.
	 * 
	 * @return SortableObect
	 */
	public SortableObject getSortableObject() {
		return sortableObject;
	}

	/**
	 * Set the sortable object for this node.
	 * 
	 * @param sortableObject
	 */
	public void setSortableObject(SortableObject sortableObject) {
		this.sortableObject = sortableObject;
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

	@Override
	public int hashCode() {
		int result = 17;
		if (parent != null) {
			result = 31 * result + parent.hashCode();
		}
		if (sortableObject != null) {
			result = 31 * result + sortableObject.hashCode();
		}
		return result;
	}
}

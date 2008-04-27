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

	public Node() {
	}

	public Node(SortableObject sortableObject) {
		this.sortableObject = sortableObject;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
		left.setParent(this);
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
		right.setParent(this);
	}

	public SortableObject getSortableObject() {
		return sortableObject;
	}

	public void setSortableObject(SortableObject sortableObject) {
		this.sortableObject = sortableObject;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
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

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
 * A node in the tree, User will not have to use this class, it is used internally by the package.
 * 
 * @author Devender Gollapally
 * 
 */
public class Node {

	private Node parent;
	private Node left;
	private Node right;
	private SortableObject sortableObject;

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
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
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
		} else {
			Node other = (Node) obj;
			if (this.getSortableObject().equals(other.getSortableObject())) {
				return true;
			} else {
				return false;
			}
		}
	}
}

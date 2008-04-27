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
package jalds.alds.ds.tree.rb;

import jalds.alds.SortableObject;

/**
 * A Red Black Node is extends a regular tree node and add one extra bit of
 * storage the color of the node.
 * 
 * @author Devender Gollapally
 * 
 */
public class RedBlackNode {
	private RedBlackNode parent;
	private RedBlackNode left;
	private RedBlackNode right;
	private SortableObject sortableObject;

	/**
	 * A Nil node has a special Property of always being black.
	 */
	public static final RedBlackNode NilNode = new RedBlackNode();
	static {
		NilNode.setNodeColor(NodeColor.Black);
	}

	private NodeColor nodeType;

	private RedBlackNode() {
		this.setParent(NilNode);
		this.setLeft(NilNode);
		this.setRight(NilNode);
	}

	public RedBlackNode(SortableObject sortableObject) {
		this.setSortableObject(sortableObject);
		this.setParent(NilNode);
		this.setLeft(NilNode);
		this.setRight(NilNode);
	}

	public NodeColor getNodeColor() {
		return nodeType;
	}

	public void setNodeColor(NodeColor nodeType) {
		this.nodeType = nodeType;
	}

	public RedBlackNode getParent() {
		return parent;
	}

	public void setParent(RedBlackNode parent) {
		this.parent = parent;
	}

	public RedBlackNode getLeft() {
		return left;
	}

	public void setLeft(RedBlackNode left) {
		this.left = left;
	}

	public RedBlackNode getRight() {
		return right;
	}

	public void setRight(RedBlackNode right) {
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
		}
		RedBlackNode other = (RedBlackNode) obj;
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

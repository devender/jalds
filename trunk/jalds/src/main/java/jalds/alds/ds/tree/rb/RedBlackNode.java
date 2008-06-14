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
 * storage the color of the node.The users will probably never have to use this
 * class directly.
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

	/**
	 * Constructs an empty Node.
	 */
	private RedBlackNode() {
		this.setParent(NilNode);
		this.setLeft(NilNode);
		this.setRight(NilNode);
	}

	/**
	 * Constructs a RedBackNode with the given Sortable Object
	 * 
	 * @param sortableObject
	 */
	public RedBlackNode(SortableObject sortableObject) {
		this.setSortableObject(sortableObject);
		this.setParent(NilNode);
		this.setLeft(NilNode);
		this.setRight(NilNode);
	}

	/**
	 * Returns the color of this Node.
	 */
	public NodeColor getNodeColor() {
		return nodeType;
	}

	/**
	 * Set the color of this node.
	 * 
	 * @param nodeType
	 */
	public void setNodeColor(NodeColor nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * Return the Parent of this node.
	 * 
	 * @return RedBlackNode
	 */
	public RedBlackNode getParent() {
		return parent;
	}

	/**
	 * Set the Parent of this node.
	 * 
	 * @param parent
	 */
	public void setParent(RedBlackNode parent) {
		this.parent = parent;
	}

	/**
	 * Returns the left child
	 * 
	 * @return RedBlackNode
	 */
	public RedBlackNode getLeft() {
		return left;
	}

	/**
	 * Set the left child.
	 * 
	 * @param left
	 */
	public void setLeft(RedBlackNode left) {
		this.left = left;
	}

	/**
	 * Return the right child.
	 * 
	 * @return RedBlackNode
	 */
	public RedBlackNode getRight() {
		return right;
	}

	/**
	 * Set the right child.
	 * 
	 * @param right
	 */
	public void setRight(RedBlackNode right) {
		this.right = right;
	}

	/**
	 * Returns this Node's key
	 * 
	 * @return SortableObject
	 */
	public SortableObject getSortableObject() {
		return sortableObject;
	}

	/**
	 * Sets this Node's key
	 * 
	 * @param sortableObject
	 */
	public void setSortableObject(SortableObject sortableObject) {
		this.sortableObject = sortableObject;
	}

	/**
	 * Two RedBlackNodes are considered to be equal if they have the same parent
	 * and same key.
	 */
	public boolean equals(Object obj) {
		if ( !(obj instanceof RedBlackNode) ) {
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

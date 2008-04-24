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
 * A very simple implementation of the Binary Tree, it hopes all the elements are inserted in a
 * random order, if elements are inserted in a random order into a binary tree the height of the
 * tree will be approximately <em>O(n log n)</em> but things are rarely random in the real world.
 * 
 * @author Devender Gollapally
 * 
 */
public class SimpleBinaryTree extends BinaryTree {

	@Override
	public void insertNode(SortableObject sortableObject, boolean allowDuplicates) {
		if (!allowDuplicates && find(root, sortableObject.getValue()) != null) {
			return;
		}
		if (root == null) {
			root = new Node();
			root.setSortableObject(sortableObject);
		} else {
			checkAndCreate(root, sortableObject);
		}
	}

	private void checkAndCreate(Node node, SortableObject sortableObject) {
		// should I go right or left ?
		if (sortableObject.getValue() >= node.getSortableObject().getValue()) {
			if (node.getRight() == null) {
				Node right = createNode(node, sortableObject);
				node.setRight(right);
			} else {
				checkAndCreate(node.getRight(), sortableObject);
			}
		} else {
			if (node.getLeft() == null) {
				Node left = createNode(node, sortableObject);
				node.setLeft(left);
			} else {
				checkAndCreate(node.getLeft(), sortableObject);
			}
		}
	}

	private Node createNode(Node parent, SortableObject sortableObject) {
		Node node = new Node();
		node.setParent(parent);
		node.setSortableObject(sortableObject);
		return node;
	}

	/**
	 * Deletes the first node which has the given value.
	 * <ul>
	 * <li>Find the Node which has the given value</li>
	 * <li>If the Node is the root node then delete the root</li>
	 * <li>Else if the Node has no children then set the Parent of this node's link to child as
	 * null.</li>
	 * <li>Else if the Node has only one child, move the child to the parent</li>
	 * <li>If the Node has both children
	 * <ul>
	 * 
	 * </ul>
	 * </li>
	 * </ul>
	 */
	public void deleteNodeWithValue(int value) {
		Node nodeToDelete = find(root, value);
		deleteNode(nodeToDelete);
	}

	private void deleteNode(Node nodeToDelete) {
		Node parent = nodeToDelete.getParent();
		// is this the root node ?
		if (parent == null) {
			root = null;
		} else if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
			// easier if no kids
			if (nodeToDelete.equals(parent.getLeft())) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else {
			if (nodeToDelete.getLeft() != null && nodeToDelete.getRight() != null) {
				// has both children
				Node successor = findSuccessor(nodeToDelete);
				if (successor != null) {
					nodeToDelete.setSortableObject(successor.getSortableObject());
					deleteNode(successor);
				} else {
					throw new RuntimeException("Unable to find Successor");
				}
			} else if ((nodeToDelete.getLeft() != null && nodeToDelete.getRight() == null) || (nodeToDelete.getLeft() == null && nodeToDelete.getRight() != null)) {
				// has only one child
				Node child = (nodeToDelete.getLeft() != null) ? nodeToDelete.getLeft() : nodeToDelete.getRight();
				if (nodeToDelete.equals(parent.getLeft())) {
					parent.setLeft(child);
				} else {
					parent.setRight(child);
				}
			}
		}
	}
}

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

import java.util.ArrayList;
import java.util.List;

import jalds.alds.SortableObject;

/**
 * Maintains the following invariant
 * <p>
 * Let x be a node in the BST, if y is a node in the left subtree of x then key(y) < key(x) and if y
 * is is a node in the right subtree of x then key(y)>=key(x)
 * 
 * @author Devender Gollapally
 * 
 */
public abstract class BinaryTree {

	protected Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * Insert the given Sortable Object into the Tree, also needs to know if duplicates are allowed.
	 * 
	 * @param sortableObject
	 * @param allowDuplicates
	 */
	public abstract void insertNode(SortableObject sortableObject, boolean allowDuplicates);

	/**
	 * Delete the First Node whose Sortable Object's value is the given value
	 * 
	 * @param value
	 */
	public abstract void deleteNodeWithValue(int value);

	/**
	 * Find the node with the smallest value greater than the give key, Note the given key must be
	 * in the tree i.e A sortable object with the given key must be in the tree
	 * <p>
	 * If a node which holds the given key is found and that node has a right child, the successor
	 * of this node will be the node with the most min value in the right subtree.Else we start
	 * going up the tree to find an ancestor which has the given node in its left subtree, that
	 * would be the successor of this node.
	 * <p>
	 * <em>NOTE</em>: findSuccessor will work correctely only if there are no duplicates, if
	 * duplicates are allowed it can return back null when you don't expect it.
	 * 
	 * @param key
	 * @return {@link SortableObject}
	 */
	public SortableObject findSuccessor(int key) {
		SortableObject successor = null;
		Node nodeForKey = find(root, key);
		if (nodeForKey != null) {
			if (nodeForKey.getRight() != null) {
				successor = findMin(nodeForKey.getRight()).getSortableObject();
			} else {
				// go find the closest ancestor where which is on the left
				Node parentNode = nodeForKey.getParent();
				while (parentNode != null && nodeForKey.equals(parentNode.getRight())) {
					nodeForKey = parentNode;
					parentNode = parentNode.getParent();
				}
				if (parentNode != null) {
					successor = parentNode.getSortableObject();
				}
			}
		}
		return successor;
	}

	/**
	 * Symmetric to findSuccessor
	 * 
	 * @param key
	 * @return SortableObject
	 */
	public SortableObject findPredecessor(int key) {
		SortableObject predecessor = null;
		Node nodeForKey = find(root, key);
		if (nodeForKey != null) {
			if (nodeForKey.getLeft() != null) {
				predecessor = findMax(nodeForKey.getLeft()).getSortableObject();
			} else {
				// go find the closest ancestor where which is on the right
				Node parentNode = nodeForKey.getParent();
				while (parentNode != null && nodeForKey.equals(parentNode.getLeft())) {
					nodeForKey = parentNode;
					parentNode = parentNode.getParent();
				}
				if (parentNode != null) {
					predecessor = parentNode.getSortableObject();
				}
			}
		}

		return predecessor;
	}

	/**
	 * Find the sortable object whose value is equal to the given key
	 * 
	 * @param key
	 * @return {@link SortableObject}
	 */
	public SortableObject find(int key) {
		return iterativeFind(root, key).getSortableObject();
	}

	protected Node find(Node node, int key) {
		if (node == null || node.getSortableObject().getValue() == key) {
			return node;
		} else {
			if (key < node.getSortableObject().getValue()) {
				return find(node.getLeft(), key);
			} else {
				return find(node.getRight(), key);
			}
		}
	}

	protected Node iterativeFind(Node node, int key) {
		while (node != null && node.getSortableObject().getValue() != key) {
			if (key < node.getSortableObject().getValue()) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		return node;
	}

	/**
	 * Puts Root in the middle, traversing a binary tree in order will give you a sorted list of
	 * elements. Takes O(n) time to make the list, where n is
	 * <ul>
	 * <li>Traverse the left subtree</li>
	 * <li>Visit the node</li>
	 * <li>Traverse the right subtree</li>
	 * </ul>
	 * 
	 * @return a list of Sortable objects collected using In-Order Traversal
	 */
	public List<SortableObject> inOrder() {
		// in order on binary will gives us sorted list
		List<SortableObject> list = new ArrayList<SortableObject>();
		inOrder(root, list);
		return list;
	}

	private void inOrder(Node node, List<SortableObject> list) {
		if (node != null) {
			inOrder(node.getLeft(), list);
			list.add(node.getSortableObject());
			inOrder(node.getRight(), list);
		}
	}

	/**
	 * Visit the Parent node before left and right subtrees (also know as depth first traversal)
	 * <ul>
	 * <li>Visit the node</li>
	 * <li>Traverse the left subtree</li>
	 * <li>Traverse the right subtree</li>
	 * </ul>
	 * 
	 * @return post ordered list of Sortable Objects
	 */
	public List<SortableObject> preOrder() {
		List<SortableObject> list = new ArrayList<SortableObject>();
		preOrder(root, list);
		return list;
	}

	private void preOrder(Node node, List<SortableObject> list) {
		if (node != null) {
			list.add(node.getSortableObject());
			preOrder(node.getLeft(), list);
			preOrder(node.getRight(), list);
		}
	}

	/**
	 * Vist parent after visiting left and right subtrees.
	 * <ul>
	 * <li>Traverse the left subtree</li>
	 * <li>Traverse the right subtree</li>
	 * <li>Visit the node</li>
	 * </ul>
	 * 
	 * @return post order sorted list
	 */
	public List<SortableObject> postOrder() {
		List<SortableObject> list = new ArrayList<SortableObject>();
		postOrder(root, list);
		return list;
	}

	private void postOrder(Node node, List<SortableObject> list) {
		if (node != null) {
			postOrder(node.getLeft(), list);
			postOrder(node.getRight(), list);
			list.add(node.getSortableObject());
		}
	}

	/**
	 * Find the Object with the max value
	 * 
	 * @return SortableObject
	 */
	public SortableObject findMax() {
		return findMax(root).getSortableObject();
	}

	/**
	 * Finds the max from Node
	 * 
	 * @param node
	 * @return
	 */
	private Node findMax(Node node) {
		Node max = null;
		for (; node != null; node = node.getRight()) {
			max = node;
		}
		return max;
	}

	/**
	 * Find the Object with the Min Value
	 * 
	 * @return SortableObject
	 */
	public SortableObject findMin() {
		return findMin(root).getSortableObject();
	}

	/**
	 * Finds the most min from the given node
	 * 
	 * @param node
	 * @return node
	 */
	private Node findMin(Node node) {
		Node min = null;
		for (; node != null; node = node.getLeft()) {
			min = node;
		}
		return min;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		print(root, builder);

		return builder.toString();
	}

	private void print(Node node, StringBuilder builder) {
		if (node == null) {
			return;
		}
		builder.append(node.getSortableObject().getValue());
		builder.append("\n__|\n");
		print(node.getLeft(), builder);
		builder.append("|__\n");
		print(node.getRight(), builder);
	}
}

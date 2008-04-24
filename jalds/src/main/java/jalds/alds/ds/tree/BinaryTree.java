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
 * <p>
 * This is a very simple implementation of the Binary Tree, it hopes all the elements are inserted
 * in a random order, if a binary tree is created by inserting elements in a random order the height
 * of the tree will be approximately <em>O(n log n)</em> but things are rarely random in the real
 * world.For a faster implementation use the RedBlack tree, which is a blanced tree.
 * <p>
 * The height of a tree is important since all operations such as Search,Insert,Delete..run in O(h)
 * time
 * 
 * @see RedBlackTree
 * 
 * @author Devender Gollapally
 * 
 */
public class BinaryTree {

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

	private Node checkAndCreate(Node node, SortableObject sortableObject) {
		// should I go right or left ?
		if (sortableObject.getValue() >= node.getSortableObject().getValue()) {
			if (node.getRight() == null) {
				Node right = createNode(node, sortableObject);
				node.setRight(right);
				return right;
			} else {
				return checkAndCreate(node.getRight(), sortableObject);
			}
		} else {
			if (node.getLeft() == null) {
				Node left = createNode(node, sortableObject);
				node.setLeft(left);
				return left;
			} else {
				return checkAndCreate(node.getLeft(), sortableObject);
			}
		}
	}

	protected Node createNode(Node parent, SortableObject sortableObject) {
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
	 * <li> Find the Node's Successor.</li>
	 * <li> Replace this Node's Sortable object with the Successors' Sortable Object</li>
	 * <li> Delete Successor </li>
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
		Node nodeForKey = find(root, key);
		SortableObject successor = findSuccessor(nodeForKey).getSortableObject();
		return successor;
	}

	protected Node findSuccessor(Node node) {
		Node successor = null;
		if (node != null) {
			if (node.getRight() != null) {
				successor = findMin(node.getRight());
			} else {
				// go find the closest ancestor where which is on the left
				Node parentNode = node.getParent();
				while (parentNode != null && node.equals(parentNode.getRight())) {
					node = parentNode;
					parentNode = parentNode.getParent();
				}
				if (parentNode != null) {
					successor = parentNode;
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

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
import jalds.alds.ds.tree.rb.RedBlackTree;

/**
 * This is a very simple implementation of the Binary Tree, it hopes all the
 * elements are inserted in a random order, if a binary tree is created by
 * inserting elements in a random order the height (The maximal length of a path
 * in a tree is also called the height of the tree) of the tree will be
 * approximately <em>O(n log n)</em> but things are rarely random in the real
 * world, if node are inserted in a sequential order then the max tree height
 * will be n-1 where n is the number of nodes.For a faster implementation use
 * the RedBlack tree, which is a balanced tree.
 * <p>
 * The height of a tree is important since all operations such as
 * Search,Insert,Delete..run in O(h) time
 * 
 * @see RedBlackTree
 * 
 * @author Devender Gollapally
 * 
 */
public class SimpleBinaryTree implements BinaryTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertNode(SortableObject sortableObject, boolean allowDuplicates) {
		if (!allowDuplicates && find(root, sortableObject.getValue()) != null) {
			return;
		}

		Node z = new Node(sortableObject);
		Node y = null;
		Node x = root;
		while (x != null) {
			y = x;
			if (z.getSortableObject().getValue() < x.getSortableObject().getValue()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if (y == null) {
			root = z;
		} else {
			if (z.getSortableObject().getValue() < y.getSortableObject().getValue()) {
				y.setLeft(z);
			} else {
				y.setRight(z);
			}
		}
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public SortableObject findSuccessor(int key) {
		Node nodeForKey = find(root, key);
		SortableObject successor = findSuccessor(nodeForKey).getSortableObject();
		return successor;
	}

	private Node findSuccessor(Node node) {
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public SortableObject find(int key) {
		return find(root, key).getSortableObject();
	}

	/*
	 * private Node find(Node node, int key) { if (node == null ||
	 * node.getSortableObject().getValue() == key) { return node; } else { if
	 * (key < node.getSortableObject().getValue()) { return find(node.getLeft(),
	 * key); } else { return find(node.getRight(), key); } } }
	 */
	private Node find(Node node, int key) {
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public SortableObject findMax() {
		return findMax(root).getSortableObject();
	}

	/**
	 * Finds the max of the given Node's subtree
	 * 
	 * @param node
	 * @return Node
	 */
	private Node findMax(Node node) {
		Node max = null;
		for (; node != null; node = node.getRight()) {
			max = node;
		}
		return max;
	}

	/**
	 * {@inheritDoc}
	 */
	public SortableObject findMin() {
		return findMin(root).getSortableObject();
	}

	/**
	 * Finds the most min in the given Node's sub tree.
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

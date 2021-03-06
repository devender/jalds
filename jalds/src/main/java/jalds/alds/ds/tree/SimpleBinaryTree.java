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
 * <p>
 * For a better implementation that guarantee that the a balanced tree is build
 * use RedBlackTree. The difference between this and the RedBlack Tree is after
 * every insertion and deletion the tree is balanced which could potentially add
 * an overhead. So if the user knows that the elements will definitely be
 * inserted in a random order this is probably a good enough implementation to
 * use.
 * 
 * @see RedBlackTree
 * 
 * @author Devender Gollapally
 * 
 */
public class SimpleBinaryTree implements BinaryTree {

	private BinaryNode root;

	public BinaryNode getRoot() {
		return root;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertNode(SortableObject sortableObject) {
		if (find(root, sortableObject.getValue()) != null) {
			return;
		}

		BinaryNode z = new BinaryNode(sortableObject);
		BinaryNode y = null;
		BinaryNode x = root;
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
		BinaryNode nodeToDelete = find(root, value);
		deleteNode(nodeToDelete);
	}

	/**
	 * Deletes the given Node.
	 * <ul>
	 * <li> First determines what node to splice/move (y) </li>
	 * <ul>
	 * <li> if given node has only one child then y = given node</li>
	 * <li> if given node has both children then y = successor(node)</li>
	 * </ul>
	 * <li>Splice/Move y</li>
	 * <ul>
	 * <li>Find the non null child of y (x) </li>
	 * <li>Set the parent of x as parent of y (making y and orphan).</li>
	 * <li>If parent of y is null, i.e y was the root, set x as the new root</li>
	 * <li>If y had a parent then set x as either the right node or the left
	 * node of the parent, depending on what y was </li>
	 * </ul>
	 * <li>By now, if the given node had no children it would have been
	 * deleted, if the given node had one child, the child would have been moved
	 * into the node's spot.Only if the given node had 2 children, the successor
	 * will found and the successor's not null child moved to the right spot and
	 * the given node's value replaced with the successor's value.</li>
	 * </ol>
	 * 
	 * @param nodeToDelete
	 */
	private void deleteNode(BinaryNode nodeToDelete) {
		// first let us determine what node to splice/move out
		BinaryNode y = null;
		if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) {
			y = nodeToDelete;
		} else {
			// or the successor if node to delete has both childs
			y = findSuccessor(nodeToDelete);
		}

		// Now let us splice/move out y
		BinaryNode x = null;
		// first find the not null child of y
		if (y.getLeft() != null) {
			x = y.getLeft();
		} else {
			x = y.getRight();
		}
		// set x's parent same as y's parent, essentially y is an orphan now
		if (x != null) {
			x.setParent(y.getParent());
		}
		// if y was the root, set x as the new root
		if (y.getParent() == null) {
			root = x;
		} else {
			// if y was right set x as right or if y was left set x as the left
			BinaryNode parent = y.getParent();
			if (y.equals(parent.getLeft())) {
				parent.setLeft(x);
			} else {
				parent.setRight(x);
			}
		}
		// splicing is done, if we spliced/moved a node with no children then
		// nothing more to do, but if we had moved either one of the children
		// or the successor we will now replace the node to delete with y
		if (!y.equals(nodeToDelete)) {
			nodeToDelete.setSortableObject(y.getSortableObject());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SortableObject findSuccessor(int key) {
		BinaryNode nodeForKey = find(root, key);
		SortableObject successor = findSuccessor(nodeForKey).getSortableObject();
		return successor;
	}

	/**
	 * Finds the successor for the given node.
	 * 
	 * @param node
	 * @return Node
	 */
	private BinaryNode findSuccessor(BinaryNode node) {
		BinaryNode successor = null;
		if (node != null) {
			if (node.getRight() != null) {
				successor = findMin(node.getRight());
			} else {
				// go find the closest ancestor where which is on the left
				BinaryNode parentNode = node.getParent();
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
		BinaryNode nodeForKey = find(root, key);
		if (nodeForKey != null) {
			if (nodeForKey.getLeft() != null) {
				predecessor = findMax(nodeForKey.getLeft()).getSortableObject();
			} else {
				// go find the closest ancestor where which is on the right
				BinaryNode parentNode = nodeForKey.getParent();
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

	/**
	 * Finds a node with the given key, in the given node's sub tree, starts off
	 * with the root and keeps walking down the tree till it finds the node,
	 * discards half of the subtree on each iteration.
	 * 
	 * @param node
	 * @param key
	 * @return Node
	 */
	private BinaryNode find(BinaryNode node, int key) {
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

	/**
	 * Recursively calls itself, start off with the root calls
	 * inOrder(left),node,inOrder(right).
	 * 
	 * @param node
	 * @param list
	 */
	private void inOrder(BinaryNode node, List<SortableObject> list) {
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

	private void preOrder(BinaryNode node, List<SortableObject> list) {
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

	private void postOrder(BinaryNode node, List<SortableObject> list) {
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
	private BinaryNode findMax(BinaryNode node) {
		BinaryNode max = null;
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
	private BinaryNode findMin(BinaryNode node) {
		BinaryNode min = null;
		for (; node != null; node = node.getLeft()) {
			min = node;
		}
		return min;
	}
}

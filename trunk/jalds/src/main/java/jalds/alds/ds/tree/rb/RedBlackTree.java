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
import jalds.alds.ds.tree.BinaryTree;
import jalds.alds.ds.tree.Node;

/**
 * A red black tree is a binary tree with one extra bit of storage per node, its
 * color which can either be black or red. By constraining the way nodes can be
 * colored on any path from the root to a lead, red-black trees ensure that no
 * such path is more than twice as long as any other, so the tree is
 * approximately balanced.
 * <p>
 * Most of the common operation on a Binary tree run in O(h) time where h is the
 * height of the tree, the height of a randomly built tree is approximately >=
 * log(n) but things are rarely random in the real world, if nodes are inserted
 * in a sorted order the height of a tree will be n. Which will slow down all
 * the operation.In order to keep the tree balanced Reb Black trees were
 * created.The height of a red black tree will be at most 2log(n+1)
 * <p>
 * <em>Properties</em> of a Red black tree.
 * <ol>
 * <li> Every Node is either Red or Black. </li>
 * <li> The root is black </li>
 * <li> Every leaf (Nil) is black. </li>
 * <li> If a node is red, then both its children are black </li>
 * <li> For each node all paths from the node to descendant leaves contain the
 * same number of black nodes </li>
 * </ol>
 * <p>
 * Source Introduction To Algorithms book
 * 
 * @author Devender Gollapally
 * 
 */
public class RedBlackTree extends BinaryTree {

	/**
	 * A RedBlack tree's root node is initialized to a Nil node, (A Nil node has
	 * a special Property of always being black.)
	 */
	public RedBlackTree() {
		initializeRoot();
	}

	/**
	 * Create a new node, colors it red and inserts it using the same logic as a
	 * regular binary tree insert, after which it checks to see if the red black
	 * tree properties are violated and fixes it.
	 */
	public void insertNode(SortableObject sortableObject, boolean allowDuplicates) {
		if (!allowDuplicates && find(root, sortableObject.getValue()) != null) {
			return;
		}
		if (root.equals(RedBlackNode.NilNode)) {
			root = createNode(sortableObject);
		} else {
			Node node = checkAndCreate(root, sortableObject);
		}
		insertFixUp();
	}

	/**
	 * Finds the right place to insert the node and returns the node created.
	 * 
	 * @param node
	 * @param sortableObject
	 * @return Node
	 */
	private Node checkAndCreate(Node node, SortableObject sortableObject) {
		// should I go right or left ?
		if (sortableObject.getValue() >= node.getSortableObject().getValue()) {
			if (node.getRight().equals(RedBlackNode.NilNode)) {
				Node right = createNode(sortableObject);
				node.setRight(right);
				return right;
			} else {
				return checkAndCreate(node.getRight(), sortableObject);
			}
		} else {
			if (node.getLeft().equals(RedBlackNode.NilNode)) {
				Node left = createNode(sortableObject);
				node.setLeft(left);
				return left;
			} else {
				return checkAndCreate(node.getLeft(), sortableObject);
			}
		}
	}

	private void insertFixUp() {
		// TODO Auto-generated method stub

	}

	/**
	 * When we do a left rotate on a node we assume its right child(y) is not
	 * nil.Makes right child(y) the new root of the subtree and the given node
	 * as the left child of y.
	 * 
	 * @param node
	 */
	private void leftRotate(RedBlackNode x) {
		if (!x.getRight().equals(RedBlackNode.NilNode)) {
			Node y = x.getRight();
			if (!y.getLeft().equals(RedBlackNode.NilNode)) {
				x.setRight(y.getLeft());
			}
			if (!x.getParent().equals(RedBlackNode.NilNode)) {
				Node parent = x.getParent();
				if (parent.getLeft().equals(x)) {
					parent.setLeft(y);
				} else {
					parent.setRight(y);
				}
			} else {
				this.root = y;
			}
			y.setLeft(x);
		}
	}

	/**
	 * Completely inverse of left rotate
	 * 
	 * @param y
	 */
	private void rightRotate(RedBlackNode y) {
		if (!y.getLeft().equals(RedBlackNode.NilNode)) {
			Node x = y.getLeft();
			if (!x.getRight().equals(RedBlackNode.NilNode)) {
				y.setLeft(x.getRight());
			}
			if (!y.getParent().equals(RedBlackNode.NilNode)) {
				Node parent = y.getParent();
				if (parent.getLeft().equals(y)) {
					parent.setLeft(x);
				} else {
					parent.setRight(x);
				}
			} else {
				this.root = x;
			}
			x.setRight(y);
		}
	}

	private Node createNode(SortableObject sortableObject) {
		RedBlackNode node = new RedBlackNode(sortableObject);
		node.setNodeType(NodeType.Red);
		return node;
	}

	/**
	 * If Root is null set it to Null Node else return root.
	 * 
	 * @return
	 */
	private Node initializeRoot() {
		if (root == null) {
			root = RedBlackNode.NilNode;
		}
		return root;
	}

	@Override
	public void deleteNodeWithValue(int value) {

	}

}

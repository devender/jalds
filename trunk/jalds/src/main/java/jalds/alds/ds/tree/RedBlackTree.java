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
 * A red black tree is a binary tree with one extra bit of storage per node, its color which can
 * either be black or red. By constraining the way nodes can be colored on any path from the root to
 * a lead, red-black trees ensure that no such path is more than twice as long as any other, so the
 * tree is approximately balanced.
 * <p>
 * Source Introduction To Algorithms book
 * 
 * @author Devender Gollapally
 * 
 */
public class RedBlackTree extends BinaryTree {

	/**
	 * A Nil node has a special Property of always being black.
	 */
	private static final Node NilNode = new Node();
	static {
		NilNode.setNodeType(NodeType.Black);
	}

	/**
	 * A RedBlack tree's root node is initialized to a Nil node, (A Nil node has a special Property
	 * of always being black.)
	 */
	public RedBlackTree() {
		initializeRoot();
	}

	/**
	 * 
	 */
	public void insertNode(SortableObject sortableObject, boolean allowDuplicates) {
		if (!allowDuplicates && find(root, sortableObject.getValue()) != null) {
			return;
		}
		initializeRoot();
		Node node = checkAndCreate(root, sortableObject);
		insertFixUp();
	}

	private void insertFixUp() {
		// TODO Auto-generated method stub

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
			if (node.getRight().equals(NilNode)) {
				Node right = createNode(node, sortableObject);
				node.setRight(right);
				return right;
			} else {
				return checkAndCreate(node.getRight(), sortableObject);
			}
		} else {
			if (node.getLeft().equals(NilNode)) {
				Node left = createNode(node, sortableObject);
				node.setLeft(left);
				return left;
			} else {
				return checkAndCreate(node.getLeft(), sortableObject);
			}
		}
	}

	protected Node createNode(Node parent, SortableObject sortableObject) {
		Node node = super.createNode(parent, sortableObject);
		node.setNodeType(NodeType.Red);
		node.setLeft(NilNode);
		node.setRight(NilNode);
		return node;
	}

	private void initializeRoot() {
		if (root == null) {
			root = NilNode;
		}
	}

	@Override
	public void deleteNodeWithValue(int value) {

	}

}

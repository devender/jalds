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
 * The keys in a BST are always stored in such a way as to satisfy the BST property. Let x be a ode
 * in the BST, if y is a node in the left subtree key(y) <= key(x) and if y is is a node in the
 * right subtree of x then key(y)>=key(x)
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

	public abstract void insertNode(SortableObject sortableObject);

	/**
	 * Find the sortable object whose value is equal to the given key
	 * 
	 * @param key
	 * @return {@link SortableObject}
	 */
	public SortableObject find(int key) {
		return find(root, key);
	}

	private SortableObject find(Node node, int key) {
		if (node == null || node.getSortableObject().getValue() == key) {
			return node.getSortableObject();
		} else {
			if (key > node.getSortableObject().getValue()) {
				return find(node.getRight(), key);
			} else {
				return find(node.getLeft(), key);
			}
		}
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

	public SortableObject findMax() {
		SortableObject max = null;
		for (Node node = root; node != null; node = node.getRight()) {
			max = node.getSortableObject();
		}
		return max;
	}

}

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

import java.util.List;

import jalds.alds.SortableObject;

/**
 * Bnary trees maintains the following invariant
 * <p>
 * Let x be a node in the BST, if y is a node in the left subtree of x then
 * key(y) < key(x) and if y is is a node in the right subtree of x then
 * key(y)>=key(x)
 * <p>
 * 
 * @author Devender R. Gollapally
 * 
 */
public interface BinaryTree {

	/**
	 * Insert the given Sortable Object into the Tree, also needs to know if
	 * duplicates are allowed.
	 * 
	 * @param sortableObject
	 * @param allowDuplicates
	 */
	void insertNode(SortableObject sortableObject, boolean allowDuplicates);

	/**
	 * Deletes the first node which has the given value.
	 * <ul>
	 * <li>Find the Node which has the given value</li>
	 * <li>If the Node is the root node then delete the root</li>
	 * <li>Else if the Node has no children then set the Parent of this node's
	 * link to child as null.</li>
	 * <li>Else if the Node has only one child, move the child to the parent</li>
	 * <li>If the Node has both children
	 * <ul>
	 * <li> Find the Node's Successor.</li>
	 * <li> Replace this Node's Sortable object with the Successors' Sortable
	 * Object</li>
	 * <li> Delete Successor </li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	void deleteNodeWithValue(int value);

	/**
	 * Find the node with the smallest value greater than the give key, Note the
	 * given key must be in the tree i.e A sortable object with the given key
	 * must be in the tree
	 * <p>
	 * If a node which holds the given key is found and that node has a right
	 * child, the successor of this node will be the node with the most min
	 * value in the right subtree.Else we start going up the tree to find an
	 * ancestor which has the given node in its left subtree, that would be the
	 * successor of this node.
	 * <p>
	 * <em>NOTE</em>: findSuccessor will work correctely only if there are no
	 * duplicates, if duplicates are allowed it can return back null when you
	 * don't expect it.
	 * 
	 * @param key
	 * @return {@link SortableObject}
	 */
	SortableObject findSuccessor(int key);

	/**
	 * Symmetric to findSuccessor
	 * 
	 * @param key
	 * @return SortableObject
	 */
	SortableObject findPredecessor(int key);

	/**
	 * Find the sortable object whose value is equal to the given key
	 * 
	 * @param key
	 * @return {@link SortableObject}
	 */
	SortableObject find(int key);

	/**
	 * Puts Root in the middle, traversing a binary tree in order will give you
	 * a sorted list of elements. Takes O(n) time to make the list, where n is
	 * <ul>
	 * <li>Traverse the left subtree</li>
	 * <li>Visit the node</li>
	 * <li>Traverse the right subtree</li>
	 * </ul>
	 * 
	 * @return a list of Sortable objects collected using In-Order Traversal
	 */
	List<SortableObject> inOrder();

	/**
	 * Visit the Parent node before left and right subtrees (also know as depth
	 * first traversal)
	 * <ul>
	 * <li>Visit the node</li>
	 * <li>Traverse the left subtree</li>
	 * <li>Traverse the right subtree</li>
	 * </ul>
	 * 
	 * @return post ordered list of Sortable Objects
	 */
	List<SortableObject> preOrder();

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
	List<SortableObject> postOrder();

	/**
	 * Find the Object with the max value
	 * 
	 * @return SortableObject
	 */
	SortableObject findMax();

	/**
	 * Finds the Object with the Min Value
	 * 
	 * @return SortableObject
	 */
	SortableObject findMin();
}

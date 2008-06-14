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
package jalds.alds.al.sorting.comparisonsort;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.BinaryTree;
import jalds.alds.ds.tree.SimpleBinaryTree;

import java.util.List;

/**
 * A sort algorithm that first builds a binary search tree of the keys then
 * accesses the keys with an in-order traversal. Sorts in <em> O(log n) </em>time,
 * since all operations on a binary tree can be done in O(h) where h is the
 * height of the tree.
 * 
 * @see BinaryTree
 * @author Devender Gollapally
 * 
 */
final class BinaryTreeSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		BinaryTree binaryTree = new SimpleBinaryTree();
		for (SortableObject sortableObject : unSortedList) {
			binaryTree.insertNode(sortableObject);
		}
		List<SortableObject> list = binaryTree.inOrder();
		unSortedList = new SortableObject[list.size()];
		for (int i = 0; i < list.size(); i++) {
			unSortedList[i] = list.get(i);
		}
		return unSortedList;
	}

}

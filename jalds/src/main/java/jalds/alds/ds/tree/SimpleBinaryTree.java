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
 * A very simple implementation of the Binary Tree, it hope all the elements are inserted in a
 * random order, if elements are inserted in a random order into a binary tree the height of the
 * tree will be <em>O(n log n)</em> but things are rarely random in the real world.
 * 
 * @author Devender Gollapally
 * 
 */
public class SimpleBinaryTree extends BinaryTree {

	@Override
	public void insertNode(SortableObject sortableObject) {
		if (root == null) {
			root = new Node();
			root.setSortableObject(sortableObject);
		} else {
			checkAndCreate(root, sortableObject);
		}
	}

	private void checkAndCreate(Node node, SortableObject sortableObject) {
		// should I go right or left ?
		if (sortableObject.getValue() > node.getSortableObject().getValue()) {
			if (node.getRight() == null) {
				Node right = new Node();
				right.setParent(node);
				right.setSortableObject(sortableObject);
				node.setRight(right);
			} else {
				checkAndCreate(node.getRight(), sortableObject);
			}
		} else {
			if (node.getLeft() == null) {
				Node left = new Node();
				left.setParent(node);
				left.setSortableObject(sortableObject);
				node.setLeft(left);
			} else {
				checkAndCreate(node.getLeft(), sortableObject);
			}
		}
	}

}

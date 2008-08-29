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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import jalds.alds.SortableObject;

/**
 * A very generic tree
 * 
 * @author Devender Gollapally
 * 
 */
public class Tree<T> {
	private final Node<T> root;

	/**
	 * Needs a sortable object that will be used as the root node
	 * 
	 * @param sortableObject
	 */
	public Tree(SortableObject<T> sortableObject) {
		root = new Node<T>(sortableObject);
	}

	/**
	 * returns the root of the tree
	 * 
	 * @return
	 */
	public Node<T> getRoot() {
		return root;
	}

	/**
	 * Finds the node which has the given sortable object
	 * 
	 * @param sortableObject
	 * @return
	 */
	public Node<T> findNode(SortableObject<T> sortableObject) {
		if (root.getSortableObject().equals(sortableObject)) {
			return root;
		} else {
			List<Node<T>> list = new LinkedList<Node<T>>();
			list.addAll(root.getChildren());
			ListIterator<Node<T>> listIterator = list.listIterator();
			Node<T> node = null;
			while (listIterator.hasNext()) {
				node = listIterator.next();
				if (node.getSortableObject().equals(sortableObject)) {
					return node;
				} else {
					listIterator.remove();
					for (Node<T> a : node.getChildren()) {
						listIterator.add(a);
					}
				}
			}
			return node;
		}
	}
}

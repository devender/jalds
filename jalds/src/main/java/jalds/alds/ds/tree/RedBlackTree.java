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
 * tree is approimately balanced.
 * <p>
 * Source Introduction To Algorithms book
 * 
 * @author Devender Gollapally
 * 
 */
public class RedBlackTree extends BinaryTree {

	@Override
	public void deleteNodeWithValue(int value) {
		// TODO Auto-generated method stub

	}

}

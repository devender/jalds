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
import jalds.alds.ds.tree.Node;

/**
 * A Red Black Node is extends a regular tree node and add one extra bit of
 * storage the color of the node.
 * 
 * @author Devender Gollapally
 * 
 */
public class RedBlackNode extends Node {
	/**
	 * A Nil node has a special Property of always being black.
	 */
	public static final RedBlackNode NilNode = new RedBlackNode();
	static {
		NilNode.setNodeType(NodeType.Black);
	}

	private NodeType nodeType;

	public RedBlackNode() {
		this.setLeft(NilNode);
		this.setRight(NilNode);
	}

	public RedBlackNode(SortableObject sortableObject) {
		this.setSortableObject(sortableObject);
		this.setLeft(NilNode);
		this.setRight(NilNode);
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
}

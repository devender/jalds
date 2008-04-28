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

import java.util.ArrayList;
import java.util.List;

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
public class RedBlackTree implements BinaryTree {

	private RedBlackNode root;

	/**
	 * A RedBlack tree's root node is initialized to a Nil node, (A Nil node has
	 * a special Property of always being black.)
	 */
	public RedBlackTree() {
		initializeRoot();
	}

	/**
	 * Create a new node, colors it red and inserts it using the same logic as a
	 * regular binary tree insert after which it checks to see if the red black
	 * tree properties are violated and fixes it using the insertFixUp
	 * procedure.
	 */
	public void insertNode(SortableObject sortableObject, boolean allowDuplicates) {
		if (!allowDuplicates && find(root, sortableObject.getValue()) != null) {
			return;
		}

		RedBlackNode y = RedBlackNode.NilNode;
		RedBlackNode x = root;
		while (!x.equals(RedBlackNode.NilNode)) {
			y = x;
			if (sortableObject.getValue() < x.getSortableObject().getValue()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}
		RedBlackNode z = createNode(sortableObject);
		z.setParent(y);
		if (y.equals(RedBlackNode.NilNode)) {
			root = z;
		} else {
			if (z.getSortableObject().getValue() < y.getSortableObject().getValue()) {
				y.setLeft(z);
			} else {
				y.setRight(z);
			}
		}
		z.setLeft(RedBlackNode.NilNode);
		z.setRight(RedBlackNode.NilNode);
		z.setNodeColor(NodeColor.Red);
		insertFixUp(z);
	}

	/**
	 * Creates a new node whose key is the Sortable Object and whose color is
	 * Red
	 * 
	 * @param sortableObject
	 * @return RedBlackNode
	 */
	private RedBlackNode createNode(SortableObject sortableObject) {
		RedBlackNode node = new RedBlackNode(sortableObject);
		node.setNodeColor(NodeColor.Red);
		return node;
	}

	/**
	 * Inserting a new node could have caused some some of the properties of the
	 * RB Tree to become invalid, the insert fix up fixes these errors.
	 * <p>
	 * The only 2 violations that can be introduced when a new node is inserted
	 * are :
	 * <ol>
	 * <li> The root is no longer Black (violates property 2)</li>
	 * <li> A red node gets a red child (violates property 4) </li>
	 * </ol>
	 * <p>
	 * The Insert Fix Up can be categorized into 3 broad cases. Remember the new
	 * node will always be red.
	 * <ol>
	 * <li> The New Node's Parent and the New Node's Parent's sibling (uncle)
	 * are both Red, if a node's parent is red then both of its children should
	 * be black (from property 4), in order to fix this violation we change the
	 * node's parent and its uncle to black and the node grand parent to red,
	 * and then set the new node to the grandparent node and call the procedure
	 * again.</li>
	 * <li> The Uncle is black, and the new node is the right child of the
	 * parent, in this case we set the new node to the parent and do a left
	 * rotate on the new node and then go to case 3</li>
	 * <li> Case 3 where the new node is the left child of the parent, we set
	 * the parent's color to black and the grandparent's color to red and do a
	 * right rotate.</li>
	 * </ol>
	 * 
	 * @param newNode
	 */
	private void insertFixUp(RedBlackNode newNode) {
		while (newNode.getParent().getNodeColor().equals(NodeColor.Red)) {
			if (newNode.getParent().equals(newNode.getParent().getParent().getLeft())) {
				RedBlackNode uncle = newNode.getParent().getParent().getRight();
				if (uncle.getNodeColor().equals(NodeColor.Red)) {
					newNode.getParent().setNodeColor(NodeColor.Black);
					uncle.setNodeColor(NodeColor.Black);
					newNode.getParent().getParent().setNodeColor(NodeColor.Red);
					newNode = newNode.getParent().getParent();
				} else {
					if (newNode.equals(newNode.getParent().getRight())) {
						newNode = newNode.getParent();
						leftRotate(newNode);
					}
					newNode.getParent().setNodeColor(NodeColor.Black);
					newNode.getParent().getParent().setNodeColor(NodeColor.Red);
					rightRotate(newNode.getParent().getParent());
				}
			} else if (newNode.getParent().equals(newNode.getParent().getParent().getRight())) {
				RedBlackNode uncle = newNode.getParent().getParent().getLeft();
				if (uncle.getNodeColor().equals(NodeColor.Red)) {
					newNode.getParent().setNodeColor(NodeColor.Black);
					uncle.setNodeColor(NodeColor.Black);
					newNode.getParent().getParent().setNodeColor(NodeColor.Red);
					newNode = newNode.getParent().getParent();
				} else {
					if (newNode.equals(newNode.getParent().getLeft())) {
						newNode = newNode.getParent();
						rightRotate(newNode);
					}
					newNode.getParent().setNodeColor(NodeColor.Black);
					newNode.getParent().getParent().setNodeColor(NodeColor.Red);
					leftRotate(newNode.getParent().getParent());

				}
			}
		}
		root.setNodeColor(NodeColor.Black);
	}

	/**
	 * When we do a left rotate on a node(known as x) we assume its right
	 * child(y) is not nil. This procedure, makes right child( know as y ) the
	 * new root of the subtree and x as the left child of y.If y has a left
	 * child, it becomes x's right child.
	 * 
	 * @param x
	 */
	private void leftRotate(RedBlackNode x) {
		if (!x.getRight().equals(RedBlackNode.NilNode)) {
			RedBlackNode y = x.getRight();
			if (!y.getLeft().equals(RedBlackNode.NilNode)) {
				x.setRight(y.getLeft());
			}
			if (!x.getParent().equals(RedBlackNode.NilNode)) {
				RedBlackNode parent = x.getParent();
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
			RedBlackNode x = y.getLeft();
			if (!x.getRight().equals(RedBlackNode.NilNode)) {
				y.setLeft(x.getRight());
			}
			if (!y.getParent().equals(RedBlackNode.NilNode)) {
				RedBlackNode parent = y.getParent();
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

	/**
	 * If Root is null set it to Null Node else return root.
	 * 
	 * @return RedBlackNode
	 */
	private RedBlackNode initializeRoot() {
		if (root == null) {
			root = RedBlackNode.NilNode;
		}
		return root;
	}

	/**
	 * {@inheritDoc} Similar to SimpleBinaryTree Delete, after delete checks to
	 * see if the spliced/moved node is black and if it is calls delete fix up.
	 */
	public void deleteNodeWithValue(int value) {
		RedBlackNode nodeToDelete = find(root, value);
		deleteNode(nodeToDelete);
	}

	/**
	 * Similar to Simple Binary Tree Delete
	 * 
	 * @param nodeToDelete
	 */
	private void deleteNode(RedBlackNode nodeToDelete) {
		// first let us determine what node to splice/move out
		RedBlackNode y = null;
		if (nodeToDelete.getLeft().equals(RedBlackNode.NilNode) || nodeToDelete.getRight().equals(RedBlackNode.NilNode)) {
			y = nodeToDelete;
		} else {
			// or the successor if node to delete has both childs
			y = findSuccessor(nodeToDelete);
		}

		// Now let us splice/move out y
		RedBlackNode x = null;
		// first find the not null child of y
		if (!y.getLeft().equals(RedBlackNode.NilNode)) {
			x = y.getLeft();
		} else {
			x = y.getRight();
		}
		// set x's parent same as y's parent, essentially y is an orphan now
		x.setParent(y.getParent());
		// if y was the root, set x as the new root
		if (y.getParent().equals(RedBlackNode.NilNode)) {
			root = x;
		} else {
			// if y was right set x as right or if y was left set x as the left
			RedBlackNode parent = y.getParent();
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

		if (y.getNodeColor().equals(NodeColor.Black)) {
			deleteFixup(x);
		}
	}

	/**
	 * 
	 * @param x
	 */
	private void deleteFixup(RedBlackNode x) {

	}

	/*
	 * -----------------------------------------------------------------------
	 * The following is the same as the Simple Binary Tree except Node is
	 * replaced with RedBlackNode
	 * -----------------------------------------------------------------------
	 */
	/**
	 * {@inheritDoc}
	 */
	public SortableObject find(int key) {
		return find(root, key).getSortableObject();
	}

	private RedBlackNode find(RedBlackNode node, int key) {
		while (!node.equals(RedBlackNode.NilNode) && node.getSortableObject().getValue() != key) {
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
	public SortableObject findMax() {
		return findMax(root).getSortableObject();
	}

	/**
	 * Finds the max of the given Node's subtree
	 * 
	 * @param node
	 * @return Node
	 */
	private RedBlackNode findMax(RedBlackNode node) {
		RedBlackNode max = null;
		for (; !node.equals(RedBlackNode.NilNode); node = node.getRight()) {
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
	private RedBlackNode findMin(RedBlackNode node) {
		RedBlackNode min = null;
		for (; !node.equals(RedBlackNode.NilNode); node = node.getLeft()) {
			min = node;
		}
		return min;
	}

	/**
	 * {@inheritDoc}
	 */
	public SortableObject findPredecessor(int key) {
		SortableObject predecessor = null;
		RedBlackNode nodeForKey = find(root, key);
		if (nodeForKey != null) {
			if (nodeForKey.getLeft() != null) {
				predecessor = findMax(nodeForKey.getLeft()).getSortableObject();
			} else {
				// go find the closest ancestor where which is on the right
				RedBlackNode parentNode = nodeForKey.getParent();
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
	public SortableObject findSuccessor(int key) {
		RedBlackNode nodeForKey = find(root, key);
		SortableObject successor = findSuccessor(nodeForKey).getSortableObject();
		return successor;
	}

	private RedBlackNode findSuccessor(RedBlackNode node) {
		RedBlackNode successor = null;
		if (node != null) {
			if (node.getRight() != null) {
				successor = findMin(node.getRight());
			} else {
				// go find the closest ancestor where which is on the left
				RedBlackNode parentNode = node.getParent();
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
	public List<SortableObject> inOrder() {
		// in order on binary will gives us sorted list
		List<SortableObject> list = new ArrayList<SortableObject>();
		inOrder(root, list);
		return list;
	}

	private void inOrder(RedBlackNode node, List<SortableObject> list) {
		if (!node.equals(RedBlackNode.NilNode)) {
			inOrder(node.getLeft(), list);
			if (!node.equals(RedBlackNode.NilNode))
				list.add(node.getSortableObject());
			inOrder(node.getRight(), list);
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

	private void postOrder(RedBlackNode node, List<SortableObject> list) {
		if (!node.equals(RedBlackNode.NilNode)) {
			postOrder(node.getLeft(), list);
			postOrder(node.getRight(), list);
			list.add(node.getSortableObject());
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

	private void preOrder(RedBlackNode node, List<SortableObject> list) {
		if (!node.equals(RedBlackNode.NilNode)) {
			list.add(node.getSortableObject());
			preOrder(node.getLeft(), list);
			preOrder(node.getRight(), list);
		}
	}

}

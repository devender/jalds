package test.jalds.alds.ds.tree;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.BinaryTree;
import jalds.alds.ds.tree.SimpleBinaryTree;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

/**
 * 
 * @author Devender Gollapally
 * 
 */
public class TestSimpleBinaryTree extends TestCase {
	private BinaryTree binaryTree;

	protected void setUp() throws Exception {
		binaryTree = new SimpleBinaryTree();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int key = random.nextInt(10);
			binaryTree.insertNode(new SortableObject(key, key), true);
		}
	}

	public void testBTInvarient() {
		assertTrue(binaryTree.getRoot() != null);
		if (binaryTree.getRoot().getLeft() != null) {
			assertTrue(binaryTree.getRoot().getLeft().getSortableObject().getValue() < binaryTree.getRoot().getSortableObject().getValue());
		}

		if (binaryTree.getRoot().getRight() != null) {
			assertTrue(binaryTree.getRoot().getRight().getSortableObject().getValue() >= binaryTree.getRoot().getSortableObject().getValue());
		}
	}

	public void testInOrder() {
		// walking in order should produce a sorted list
		int value = Integer.MIN_VALUE;
		List<SortableObject> list = binaryTree.inOrder();
		for (int i = 0; i < list.size(); i++) {
			SortableObject object = list.get(i);
			assertTrue(object.getValue() >= value);
			value = object.getValue();
		}
	}

	public void testFind() {
		List<SortableObject> list = binaryTree.inOrder();
		for (SortableObject object : list) {
			assertNotNull(binaryTree.find(object.getValue()));
		}
	}

	public void testMax() {
		List<SortableObject> list = binaryTree.inOrder();
		SortableObject maxKnown = list.get(list.size() - 1);
		SortableObject max = binaryTree.findMax();
		assertEquals(maxKnown.getValue(), max.getValue());
	}

	public void testMin() {
		List<SortableObject> list = binaryTree.inOrder();
		SortableObject minKnown = list.get(0);
		SortableObject min = binaryTree.findMin();
		assertEquals(minKnown.getValue(), min.getValue());
	}

	public void testSuccessor() {
		binaryTree = new SimpleBinaryTree();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int key = random.nextInt(10);
			binaryTree.insertNode(new SortableObject(key, key), false);
		}
		List<SortableObject> list = binaryTree.inOrder();
		for (int i = 0; i < list.size() - 2; i++) {
			int key = list.get(i).getValue();
			SortableObject object = binaryTree.findSuccessor(key);
			assertTrue(object.getValue() >= key);
		}
	}

	public void testPredecessor() {
		binaryTree = new SimpleBinaryTree();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int key = random.nextInt(10);
			binaryTree.insertNode(new SortableObject(key, key), false);
		}
		List<SortableObject> list = binaryTree.inOrder();
		for (int i = list.size() - 1; i > 0; i--) {
			int key = list.get(i).getValue();
			SortableObject object = binaryTree.findPredecessor(key);
			assertTrue(object.getValue() <= key);
		}
	}
}

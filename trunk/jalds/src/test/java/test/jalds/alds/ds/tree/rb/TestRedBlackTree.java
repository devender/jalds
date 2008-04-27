package test.jalds.alds.ds.tree.rb;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.SimpleBinaryTree;
import jalds.alds.ds.tree.rb.RedBlackTree;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

public class TestRedBlackTree extends TestCase {
	private RedBlackTree redBlackTree;

	protected void setUp() throws Exception {
		redBlackTree = new RedBlackTree();
		Random random = new Random(Calendar.getInstance().getTimeInMillis());
		for (int i = 0; i < 10; i++) {
			int key = random.nextInt(10);
			redBlackTree.insertNode(new SortableObject(key, key), true);
		}
	}

	public void testFind() {
		List<SortableObject> list = redBlackTree.inOrder();
		for (SortableObject object : list) {
			assertNotNull(redBlackTree.find(object.getValue()));
		}
	}

	public void testMax() {
		List<SortableObject> list = redBlackTree.inOrder();
		SortableObject maxKnown = list.get(list.size() - 1);
		SortableObject max = redBlackTree.findMax();
		assertEquals(maxKnown.getValue(), max.getValue());
	}

	public void testMin() {
		List<SortableObject> list = redBlackTree.inOrder();
		SortableObject minKnown = list.get(0);
		SortableObject min = redBlackTree.findMin();
		assertEquals(minKnown.getValue(), min.getValue());
	}

	public void testSuccessor() {
		redBlackTree = new RedBlackTree();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int key = random.nextInt(10);
			redBlackTree.insertNode(new SortableObject(key, key), false);
		}
		List<SortableObject> list = redBlackTree.inOrder();
		for (int i = 0; i < list.size() - 2; i++) {
			int key = list.get(i).getValue();
			SortableObject object = redBlackTree.findSuccessor(key);
			assertTrue(object.getValue() >= key);
		}
	}

	public void testPredecessor() {
		redBlackTree = new RedBlackTree();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int key = random.nextInt(10);
			redBlackTree.insertNode(new SortableObject(key, key), false);
		}
		List<SortableObject> list = redBlackTree.inOrder();
		for (int i = list.size() - 1; i > 0; i--) {
			int key = list.get(i).getValue();
			SortableObject object = redBlackTree.findPredecessor(key);
			assertTrue(object.getValue() <= key);
		}
	}

	public void testInOrder() {
		// walking in order should produce a sorted list
		List<SortableObject> list = redBlackTree.inOrder();
		orderTest(list);
	}

	private void orderTest(List<SortableObject> list) {
		int value = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			SortableObject object = list.get(i);
			assertTrue(object.getValue() >= value);
			value = object.getValue();
		}
	}
}

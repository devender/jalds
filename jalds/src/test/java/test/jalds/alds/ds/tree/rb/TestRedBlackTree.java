package test.jalds.alds.ds.tree.rb;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.rb.RedBlackTree;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

public class TestRedBlackTree extends TestCase {
	private RedBlackTree redBlackTree;

	private void createRedBlackTree(boolean allowDups, int n) {
		redBlackTree = new RedBlackTree();
		Random random = new Random(Calendar.getInstance().getTimeInMillis());
		for (int i = 0; i < n; i++) {
			int key = random.nextInt(n);
			redBlackTree.insertNode(new SortableObject(key, key), allowDups);
		}
	}

	protected void setUp() throws Exception {
		createRedBlackTree(true, 20);
	}

	public void testSimple() {
		assertNotNull(redBlackTree);
	}

	public void testLargeTree() {
		createRedBlackTree(false, 1000);
		orderTest(redBlackTree.inOrder());
	}

	public void testNoDups() {
		createRedBlackTree(false, 20);
		List<SortableObject> list = redBlackTree.inOrder();
		for (SortableObject object : list) {
			int numberOfElementsWithSameValue = 0;
			for (int i = 0; i < list.size(); i++) {
				if (object.getValue() == list.get(i).getValue()) {
					numberOfElementsWithSameValue++;
				}
			}
			assertEquals(1, numberOfElementsWithSameValue);
		}
	}

	public void testAllOrder() {
		List<SortableObject> inOrder = redBlackTree.inOrder();
		List<SortableObject> postOrder = redBlackTree.postOrder();
		List<SortableObject> preOrder = redBlackTree.preOrder();
		assertTrue(inOrder.size() > 0);
		assertTrue(inOrder.size() == postOrder.size());
		assertTrue(postOrder.size() == preOrder.size());
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
		List<SortableObject> list = redBlackTree.inOrder();
		for (int i = 0; i < list.size() - 2; i++) {
			int key = list.get(i).getValue();
			SortableObject object = redBlackTree.findSuccessor(key);
			assertTrue(object.getValue() >= key);
		}
	}

	public void testInOrder() {
		// walking in order should produce a sorted list
		orderTest(redBlackTree.inOrder());
	}

	public void testPredecessor() {
		List<SortableObject> list = redBlackTree.inOrder();
		for (int i = list.size() - 1; i > 1; i--) {
			int key = list.get(i).getValue();
			SortableObject object = redBlackTree.findPredecessor(key);
			assertTrue(object.getValue() <= key);
		}
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

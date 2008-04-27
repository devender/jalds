package test.jalds.alds.ds.tree;

import jalds.alds.SortableObject;
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

package test.jalds.alds.ds.tree;

import java.util.Random;

import junit.framework.TestCase;
import jalds.alds.SortableObject;
import jalds.alds.ds.tree.BinaryTree;
import jalds.alds.ds.tree.SimpleBinaryTree;

/**
 * 
 * @author Devender Gollapally
 *
 */
public class TestSimpleBinaryTree extends TestCase {

	public void testsimpleTest() {
		BinaryTree binaryTree = new SimpleBinaryTree();
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			int key = random.nextInt(10);
			binaryTree.insertNode(new SortableObject(key, key));
		}
		assertTrue(binaryTree.getRoot() != null);
		if (binaryTree.getRoot().getLeft() != null) {
			assertTrue(binaryTree.getRoot().getLeft().getSortableObject().getValue() <= binaryTree.getRoot().getSortableObject().getValue());
		}

		if (binaryTree.getRoot().getRight() != null) {
			assertTrue(binaryTree.getRoot().getRight().getSortableObject().getValue() > binaryTree.getRoot().getSortableObject().getValue());
		}
	}

}

package test.jalds.alds.ds.tree;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.BinaryNode;
import junit.framework.TestCase;

public class TestBinaryNode extends TestCase {

	public void testNode() {
		BinaryNode parentNode = new BinaryNode();
		parentNode.setSortableObject(new SortableObject(null, 1));

		BinaryNode nodeX = new BinaryNode();
		nodeX.setSortableObject(new SortableObject(null, 12));
		parentNode.setRight(nodeX);
		
		BinaryNode nodeY = new BinaryNode();
		nodeY.setSortableObject(new SortableObject(null,0));
		parentNode.setLeft(nodeY);
		
		assertFalse(nodeX.equals(nodeY));
		assertTrue(nodeX.equals(nodeX));
		assertFalse(nodeX.equals(parentNode));

	}
}

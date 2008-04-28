package test.jalds.alds.ds.tree;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.Node;
import junit.framework.TestCase;

public class TestNode extends TestCase {

	public void testNode() {
		Node parentNode = new Node();
		parentNode.setSortableObject(new SortableObject(null, 1));

		Node nodeX = new Node();
		nodeX.setSortableObject(new SortableObject(null, 12));
		parentNode.setRight(nodeX);
		
		Node nodeY = new Node();
		nodeY.setSortableObject(new SortableObject(null,0));
		parentNode.setLeft(nodeY);
		
		assertFalse(nodeX.equals(nodeY));
		assertTrue(nodeX.equals(nodeX));
		assertFalse(nodeX.equals(parentNode));

	}
}

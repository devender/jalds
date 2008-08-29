package test.jalds.alds.ds.tree;

import jalds.alds.SortableObject;
import jalds.alds.ds.tree.Node;
import jalds.alds.ds.tree.Tree;
import junit.framework.TestCase;

public class TestTree extends TestCase {

	public void testTree() {
		SortableObject<String> sortableObjectForRoot = new SortableObject<String>("A");
		Tree<String> tree = new Tree<String>(sortableObjectForRoot);

		Node<String> rootNode = tree.getRoot();
		assertEquals(rootNode, tree.findNode(sortableObjectForRoot));

		SortableObject<String> b = new SortableObject<String>("B");
		rootNode.addChild(b);

		Node<String> bNode = tree.findNode(b);
		assertNotNull(bNode);
		assertTrue(rootNode.containsChild(bNode));
		assertTrue(rootNode.equals(bNode.getParent()));

	}
}

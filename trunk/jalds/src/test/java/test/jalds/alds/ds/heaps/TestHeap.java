package test.jalds.alds.ds.heaps;

import jalds.alds.SortableObject;
import jalds.alds.ds.heaps.Heap;
import jalds.alds.ds.heaps.MaxHeapImpl;

public class TestHeap extends junit.framework.TestCase {

	public void testInsert() {
		Heap heap = new MaxHeapImpl();
		for (int i = 0; i < 10; i++) {
			heap.insert(new SortableObject(i, i));
		}

		for (int i = 9; i > -1; i--) {
			SortableObject sortableObject = heap.extract();
			assertEquals(i, sortableObject.getValue());
		}

		// assertEquals(9, heap.size());
	}

}

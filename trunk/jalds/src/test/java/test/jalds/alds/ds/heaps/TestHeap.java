package test.jalds.alds.ds.heaps;

import jalds.alds.SortableObject;
import jalds.alds.ds.heaps.Heap;
import jalds.alds.ds.heaps.HeapFactory;

public class TestHeap extends junit.framework.TestCase {

	public void testMaxHeap() {
		Heap heap = HeapFactory.maxHeap();
		for (int i = 0; i < 10; i++) {
			heap.insert(new SortableObject(i, i));
		}

		for (int i = 9; i > -1; i--) {
			SortableObject sortableObject = heap.extract();
			assertEquals(i, sortableObject.getValue());
		}
	}

	public void testMinHeap() {
		Heap heap = HeapFactory.minHeap();
		for (int i = 0; i < 10; i++) {
			heap.insert(new SortableObject(i, i));
		}

		for (int i = 0; i < 10; i++) {
			SortableObject sortableObject = heap.extract();
			assertEquals(i, sortableObject.getValue());
		}
	}

}

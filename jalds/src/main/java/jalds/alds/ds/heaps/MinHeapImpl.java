package jalds.alds.ds.heaps;

public class MinHeapImpl extends AbstractHeap implements Heap {

	/**
	 * use the child with the
	 * 
	 * @param i
	 */
	protected void heapdown(int i) {
		while (true) {
			int left = left(i);
			int right = right(i);
			int childToUse = i;

			if (left < list.size() && list.get(i).getValue() > list.get(left).getValue()) {
				childToUse = left;
			}

			if (right < list.size() && list.get(childToUse).getValue() > list.get(right).getValue()) {
				childToUse = right;
			}

			if (childToUse == i) {
				break;
			} else {
				swap(i, childToUse);
				i = childToUse;
			}
		}
	}

	protected void heapup(int index) {
		int parentIndex = parent(index);
		if (parentIndex > -1 && list.get(index).getValue() < list.get(parentIndex).getValue()) {
			swap(parentIndex, index);
			heapup(parentIndex);
		}
	}
}

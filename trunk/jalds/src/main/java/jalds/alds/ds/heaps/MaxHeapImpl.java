package jalds.alds.ds.heaps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jalds.alds.SortableObject;

/**
 * A simple max heap implementation
 * 
 * @author Devender Gollapally
 * 
 */
public class MaxHeapImpl implements Heap {

	List<SortableObject> list;

	public MaxHeapImpl() {
		list = new LinkedList<SortableObject>();
	}

	/**
	 * {@inheritDoc}
	 */
	public SortableObject extract() {
		if (list.size() == 0) {
			return null;
		}

		SortableObject object = list.get(0);
		if (list.size() - 1 > 0) {
			list.set(0, list.remove(list.size() - 1));
			heapdown(0);
		}
		return object;
	}

	/**
	 * use the child with the
	 * 
	 * @param i
	 */
	private void heapdown(int i) {
		while (true) {
			int left = left(i);
			int right = right(i);
			int childToUse = i;

			if (left < list.size() && list.get(i).getValue() < list.get(left).getValue()) {
				childToUse = left;
			}

			if (right < list.size() && list.get(childToUse).getValue() < list.get(right).getValue()) {
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

	/**
	 * {@inheritDoc}
	 */
	public Heap initialize() {
		list = new ArrayList<SortableObject>();
		return this;
	}

	/**
	 * {@inheritDoc} the runtime complexity of this operation is log(n), in the
	 * worst case we have to travers the height of the tree
	 */
	public void insert(SortableObject sortableObject) {
		int index = list.size();
		list.add(sortableObject);
		heapup(index);
	}

	private void heapup(int index) {
		int parentIndex = parent(index);
		if (parentIndex > -1 && list.get(index).getValue() > list.get(parentIndex).getValue()) {
			swap(parentIndex, index);
			heapup(parentIndex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return this.list.size();
	}

	private void swap(int pos1, int pos2) {
		SortableObject object = list.get(pos1);
		list.set(pos1, list.get(pos2));
		list.set(pos2, object);
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return (2 * i) + 1;
	}

	private int right(int i) {
		return (2 * i) + 2;
	}
}

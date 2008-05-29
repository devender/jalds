package jalds.alds.ds.heaps;

import java.util.LinkedList;
import java.util.List;

import jalds.alds.SortableObject;

public abstract class AbstractHeap implements Heap {
	protected List<SortableObject> list;

	public AbstractHeap() {
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

	protected abstract void heapdown(int i);

	/**
	 * {@inheritDoc} the runtime complexity of this operation is log(n), in the
	 * worst case we have to travers the height of the tree
	 */
	public void insert(SortableObject sortableObject) {
		int index = list.size();
		list.add(sortableObject);
		heapup(index);
	}

	protected abstract void heapup(int index);

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return this.list.size();
	}

	protected void swap(int pos1, int pos2) {
		SortableObject object = list.get(pos1);
		list.set(pos1, list.get(pos2));
		list.set(pos2, object);
	}

	protected int parent(int i) {
		return (i - 1) / 2;
	}

	protected int left(int i) {
		return (2 * i) + 1;
	}

	protected int right(int i) {
		return (2 * i) + 2;
	}
}

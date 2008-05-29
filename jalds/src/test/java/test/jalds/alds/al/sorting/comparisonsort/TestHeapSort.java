package test.jalds.alds.al.sorting.comparisonsort;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.HeapSort;

import java.util.Calendar;

public class TestHeapSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = new HeapSort();
		objects = sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("HeapSort    || " + objects.length + " || " + (t2 - t1));
		return objects;
	}

}

package test.jalds.alds.al.sorting.comparisonsort;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.QuickSort;

import java.util.Calendar;

import test.jalds.alds.al.sorting.TestSorting;

public class TestQuickSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = new QuickSort();
		objects = sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Quick       || " + objects.length + " || " + (t2 - t1));
		return objects;
	}

}

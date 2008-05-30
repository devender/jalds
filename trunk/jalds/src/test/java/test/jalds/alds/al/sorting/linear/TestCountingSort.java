package test.jalds.alds.al.sorting.linear;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.linear.CountingSort;

import java.util.Calendar;

import test.jalds.alds.al.sorting.TestSorting;

public class TestCountingSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = new CountingSort();
		objects = sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();		
		System.out.println("Counting    || "  + (t2 - t1));
		return objects;
	}
}

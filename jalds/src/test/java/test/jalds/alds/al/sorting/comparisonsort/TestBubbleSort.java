package test.jalds.alds.al.sorting.comparisonsort;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.SortFactory;

import java.util.Calendar;

import test.jalds.alds.al.sorting.TestSorting;

/**
 * 
 * @author Devender Gollapally
 * 
 */
public class TestBubbleSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = SortFactory.bubbleSort();
		sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Bubble Sort || " +  (t2 - t1));
		return objects;
	}

}

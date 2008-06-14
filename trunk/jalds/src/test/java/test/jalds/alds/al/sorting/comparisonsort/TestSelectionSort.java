package test.jalds.alds.al.sorting.comparisonsort;

import java.util.Calendar;

import test.jalds.alds.al.sorting.TestSorting;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.SelectionSort;

/**
 * 
 * @author Devender Gollapally
 *
 */
public class TestSelectionSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = new SelectionSort();
		sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Selection   || " +  (t2 - t1));
		return objects;
	}

}
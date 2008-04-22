package test.jalds.alds.al.sorting.comparisonsort;

import java.util.Calendar;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.InsertionSort;

/**
 * 
 * @author Devender Gollapally
 *
 */
public class TestInsertionSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = new InsertionSort();
		sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Insertion   || " + objects.length + " || " + (t2 - t1));
		return objects;
	}

}

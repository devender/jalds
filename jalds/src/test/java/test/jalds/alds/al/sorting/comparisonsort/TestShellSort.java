package test.jalds.alds.al.sorting.comparisonsort;

import java.util.Calendar;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;
import jalds.alds.al.sorting.comparisonsort.ShellSort;

/**
 * 
 * @author Devender Gollapally
 *
 */
public class TestShellSort extends TestSorting {

	@Override
	protected SortableObject[] sortEm(SortableObject[] objects) {
		long t1 = Calendar.getInstance().getTimeInMillis();
		Sort sort = new ShellSort();
		sort.sort(objects);
		long t2 = Calendar.getInstance().getTimeInMillis();
		System.out.println("Shell Sort  || " + objects.length + " || " + (t2 - t1));
		return objects;
	}

}

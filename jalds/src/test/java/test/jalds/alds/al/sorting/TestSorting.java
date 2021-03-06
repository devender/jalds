package test.jalds.alds.al.sorting;

import test.jalds.alds.al.sorting.comparisonsort.TestSortingHelper;
import junit.framework.TestCase;
import jalds.alds.SortableObject;

/**
 * 
 * @author Devender Gollapally
 * 
 */
public abstract class TestSorting extends TestCase {

	public void testSort() {
		SortableObject[] objects = TestSortingHelper.makeUnSortedList(100);
		objects = sortEm(objects);
		int value = Integer.MIN_VALUE;
		for (int i = 0; i < objects.length && objects[i] != null; i++) {
			assertTrue(objects[i].getValue() >= value);
			value = objects[i].getValue();
		}
	}

	protected abstract SortableObject[] sortEm(SortableObject[] objects);

}

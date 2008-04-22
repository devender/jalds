package test.jalds.alds.al.sorting.comparisonsort;

import java.util.Random;

import jalds.alds.SortableObject;

/**
 * 
 * @author Devender Gollapally
 *
 */
public class TestSortingHelper {

	public static SortableObject[] makeUnSortedList(int n) {
		SortableObject[] sortableObjects = new SortableObject[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int d = random.nextInt(n*10);
			SortableObject object = new SortableObject(d, d);
			sortableObjects[i] = object;
		}
		return sortableObjects;
	}

	public static String print(SortableObject[] objects) {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for (SortableObject object : objects) {
			builder.append(object.getValue());
			builder.append(",");
		}
		builder.append("}");
		System.out.println(builder.toString());
		return builder.toString();
	}
}

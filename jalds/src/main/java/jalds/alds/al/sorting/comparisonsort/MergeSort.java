/*
 * Copyright 2008 the original author or authors.
 * 
 * http://www.gnu.org/licenses/gpl.txt
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jalds.alds.al.sorting.comparisonsort;

import jalds.alds.SortableObject;

/**
 * Merge sort or mergesort is an <em>O(n log n)</em> comparison-based sorting
 * algorithm. In most implementations it is stable, meaning that it preserves
 * the input order of equal elements in the sorted output. It is an example of
 * the divide and conquer algorithmic paradigm. It was invented by John von
 * Neumann in 1945.
 * <p>
 * Conceptually, merge sort works as follows:
 * <p>
 * <ul>
 * <li>Divide the unsorted list into two sublists of about half the size</li>
 * <li>Divide each of the two</li>
 * sublists recursively until we have list sizes of length 1, in which case the
 * list itself is returned </li>
 * <li>Merge the two sublists back into one sorted list.</li>
 * </ul>
 * <p>
 * In the worst case, merge sort does approximately[1] (n lg n - 2^lg n + 1)
 * comparisons, which is between (n lg n - n + 1) and (n lg n + n + O(lg n)). In
 * the worst case, merge sort does about 39% fewer comparisons than quicksort
 * does in the average case; merge sort always makes fewer comparisons than
 * quicksort, except in extremely rare cases. Recursive implementations of merge
 * sort make 2n - 1 method calls in the worst case, compared to quicksort's n,
 * thus has roughly twice as much recursive overhead as quicksort.
 * <p>
 * Mergesort incorporates two main ideas to improve its runtime:
 *<P>
 * A small list will take fewer steps to sort than a large list.
 * <p>
 * Fewer steps are required to construct a sorted list from two sorted lists
 * than two unsorted lists.
 * <p>
 * source http://en.wikipedia.org/wiki/Merge_sort
 * 
 * @author Devender Gollapally
 * 
 */
final class MergeSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		if (unSortedList.length == 1) {
			return unSortedList;
		}

		int m = unSortedList.length / 2;
		SortableObject[] left = sort(subSet(unSortedList, 0, m));
		SortableObject[] right = sort(subSet(unSortedList, m, unSortedList.length));
		return merge(left, right);
	}

	/**
	 * Merges two arrays of Sortable objects and returns the merged array
	 * 
	 * @param left
	 * @param right
	 * @return SortableObject[]
	 */
	private SortableObject[] merge(SortableObject[] left, SortableObject[] right) {
		SortableObject[] sortableObjects = new SortableObject[left.length + right.length];
		int index = 0;
		while (left.length > 0 && right.length > 0) {
			if (left[0].getValue() <= right[0].getValue()) {
				sortableObjects[index++] = left[0];
				left = subSet(left, 1, left.length);
			} else {
				sortableObjects[index++] = right[0];
				right = subSet(right, 1, right.length);
			}
		}

		if (left.length > 0) {
			for (int i = 0; i < left.length; i++) {
				sortableObjects[index++] = left[i];
			}
		}
		if (right.length > 0) {
			for (int i = 0; i < right.length; i++) {
				sortableObjects[index++] = right[i];
			}
		}
		return sortableObjects;
	}

	private SortableObject[] subSet(SortableObject[] sortableObjects, int n1, int n2) {
		SortableObject[] temp = new SortableObject[n2 - n1];
		int index = 0;
		for (int i = n1; i < n2; i++) {
			temp[index++] = sortableObjects[i];
		}
		return temp;
	}
}

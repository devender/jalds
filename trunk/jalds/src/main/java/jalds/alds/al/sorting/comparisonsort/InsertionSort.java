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
 * The insertion sort works just like its name suggests - it inserts each item into its proper place
 * in the final list. The simplest implementation of this requires two list structures - the source
 * list and the list into which sorted items are inserted. To save memory, most implementations use
 * an in-place sort that works by moving the current item past the already sorted items and
 * repeatedly swapping it with the preceding item until it is in place.
 * 
 * Worst case <em> O(n^2) </em>
 * 
 * advantages:
 * <ul>
 * <li>Simple to implement</li>
 * <li>Efficient on (quite) small data sets
 * <li>Efficient on data sets which are already substantially sorted: it runs in O(n + d) time,
 * where d is the number of inversions</li>
 * <li>More efficient in practice than most other simple O(n^2) algorithms such as selection sort
 * or bubble sort: the average time is n2/4 and it is linear in the best case</li>
 * <li>Stable (does not change the relative order of elements with equal keys)</li>
 * <li>In-place (only requires a constant amount O(1) of extra memory space)</li>
 * <li>It is an online algorithm, in that it can sort a list as it receives it.</li>
 * </ul>
* <p>
 * source http://linux.wku.edu/~lamonml/algor/sort/insertion.html &
 * http://en.wikipedia.org/wiki/Insertion_sort
 * <p>
 * 
 * @author Devender Gollapally
 * 
 */
class InsertionSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		for (int i = 1; i < unSortedList.length; i++) {
			SortableObject object = unSortedList[i];
			for (int j = i; j > 0; j--) {
				if (unSortedList[j - 1].getValue() > object.getValue()) {
					swap(j - 1, j, unSortedList);
				}
			}
		}
		return unSortedList;
	}
}

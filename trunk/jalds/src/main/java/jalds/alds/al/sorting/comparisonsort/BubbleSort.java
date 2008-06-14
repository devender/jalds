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
 * Bubble sort has worst-case complexity <em>O(n^2)</em>, where n is the number of items being
 * sorted. There exist many other sorting algorithms with substantially better worst-case complexity
 * O(n log n), meaning that bubble sort should not be used when n is large.
 * <p>
 * One way to optimize bubblesort is to note that, after each pass, the largest element will always
 * move down to the bottom. During each comparison, it is clear that the largest element will move
 * downwards. Given a list of size n, the nth element will be guaranteed to be in its proper place.
 * Thus it suffices to sort the remaining n - 1 elements. Again, after this pass, the n - 1th
 * element will be in its final place.
 * <p>
 * Source http://en.wikipedia.org/wiki/Bubble_sort
 * 
 * @author Devender Gollapally
 * 
 */
final class BubbleSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 0; (i < unSortedList.length) && (i + 1 < unSortedList.length); i++) {
				if (unSortedList[i].getValue() > unSortedList[i + 1].getValue()) {
					swapped = true;
					swap(i, i + 1, unSortedList);
				}
			}
		} while (swapped);
		return unSortedList;
	}
}

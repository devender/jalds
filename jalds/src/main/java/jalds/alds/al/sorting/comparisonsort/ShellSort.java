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
 * Invented by Donald Shell in 1959, the shell sort is the most efficient of the <em>O(n^2)</em>
 * class of sorting algorithms. Of course, the shell sort is also the most complex of the O(n^2)
 * algorithms.
 * <p>
 * The shell sort is a "diminishing increment sort", better known as a "comb sort" to the unwashed
 * programming masses. The algorithm makes multiple passes through the list, and each time sorts a
 * number of equally sized sets using the insertion sort. The size of the set to be sorted gets
 * larger with each pass through the list, until the set consists of the entire list. (Note that as
 * the size of the set increases, the number of sets to be sorted decreases.) This sets the
 * insertion sort up for an almost-best case run each iteration with a complexity that approaches
 * O(n).
 * <p>
 * source http://en.wikipedia.org/wiki/Shell_sort The gap sequence that was originally suggested by
 * Donald Shell was to begin with N / 2 and to halve the number until it reaches 1. Depending on the
 * choice of gap sequence, Shellsort has a proven worst-case running time of O(n^2) (using Shell's
 * increments that start with 1/2 the array size and divide by 2 each time), O(n ^ 3 / 2) (using
 * Hibbard's increments of 2k - 1), O(n4 / 3) (using Sedgewick's increments of 9(4i) - 9(2i) + 1, or
 * 4i + 1 + 3(2i) + 1), or O(nlog2n), and possibly unproven better running times. The existence of
 * an O(nlogn) worst-case implementation of Shellsort was precluded by Poonen, Plaxton, and Suel[5].
 * 
 * <p>
 * source http://linux.wku.edu/~lamonml/algor/sort/shell.html
 * 
 * @author Devender Gollapally
 * 
 */
class ShellSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		int gap = unSortedList.length / 2;

		while (gap > 0) {

			for (int i = gap; i < unSortedList.length; i++) {
				SortableObject object = unSortedList[i];
				for (int j = i; (j >= gap && unSortedList[j - gap].getValue() > object.getValue()); j = j - gap) {
					swap(j - gap, j, unSortedList);
				}
			}

			if (gap == 2) {
				gap = 1;
			} else {
				gap = (int) (gap / 2.2);
			}
		}
		return unSortedList;
	}
}

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
 * 
 * Selection sort is a sorting algorithm, specifically an in-place comparison sort. It has
 * <em>O(n^2)</em> complexity, making it inefficient on large lists, and generally performs worse
 * than the similar insertion sort. Selection sort is noted for its simplicity, and also has
 * performance advantages over more complicated algorithms in certain situations.
 * <p>
 * source http://en.wikipedia.org/wiki/Selection_sort
 * 
 * @author Devender Gollapally
 * 
 */
public class SelectionSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		for (int i = 0; i < unSortedList.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < unSortedList.length; j++) {
				if (unSortedList[min].getValue() > unSortedList[j].getValue()) {
					min = j;
				}
			}
			if (min != i) {
				swap(i, min, unSortedList);
			}
		}
		return unSortedList;
	}
}

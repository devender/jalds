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
 * QuickSort is an algorithm whose worst case running time is Q(n^2), but is
 * remarkably efficient on the average and expected run time is )(n log n)
 * 
 * Recursively partations an array based on an element.
 * 
 * @author Devender Gollapally
 * 
 */
public class QuickSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		quickSort(unSortedList, 0, unSortedList.length - 1);
		return unSortedList;
	}

	private void quickSort(SortableObject[] unSortedList, int startIndex, int indexOfElementToUseForPartition) {
		if (startIndex < indexOfElementToUseForPartition) {
			int k = partition(unSortedList, startIndex, indexOfElementToUseForPartition);
			quickSort(unSortedList, startIndex, k - 1);
			quickSort(unSortedList, k + 1, indexOfElementToUseForPartition);
		}

	}

	/**
	 * any element that is smaller than the given element is moved to the front
	 * of the array+1 and finally the given element is moved to the front of the
	 * array+1
	 * 
	 * @param unSortedList
	 * @param startIndex
	 * @param indexOfElementToUseForPartition
	 * @return
	 */
	private int partition(SortableObject[] unSortedList, int startIndex, int indexOfElementToUseForPartition) {
		SortableObject k = unSortedList[indexOfElementToUseForPartition];
		int i = startIndex - 1;
		for (int j = startIndex; j < indexOfElementToUseForPartition; j++) {
			if (unSortedList[j].getValue() < k.getValue()) {
				i = i + 1;
				swap(i, j, unSortedList);
			}
		}
		i = i + 1;
		swap(i, indexOfElementToUseForPartition, unSortedList);
		return i;
	}
}

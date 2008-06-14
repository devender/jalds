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
 * This is a divide and conqure algorithm, on each run divides the given
 * partation into elements less than and elements greater than a given piviot
 * element, till it gets down to one element.
 * 
 * @author Devender Gollapally
 * 
 */
class QuickSort extends AbstractComparisonSort {

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
	 * As the name says partitions the array into 2 sections the first half has
	 * all elements that are smaller than the index element and the second half
	 * has all elements that are greater than.
	 * 
	 * In order to do this, it compares each element from start to index of the
	 * element-1, checks to see if it is less than the index element, if it is
	 * it will move it to the first partation of the array and will finally move
	 * the index element to the the end of the first half.
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

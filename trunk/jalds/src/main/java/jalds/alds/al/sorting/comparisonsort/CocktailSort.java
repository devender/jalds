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
 * The algorithm differs from bubble sort in that sorts in both directions each
 * pass through the list. Still results in <em> O(n^2) </em>
 * 
 * This modification to bubble sort tries to solve the problem of turtles, at
 * the end of each pass the largest element will move to its right spot but
 * smaller elements take time to move to the front of the sorted list. By
 * running the sort on both directions smaller elements will move up faster.
 * 
 * @see BubbleSort
 * @author Devender Gollapally
 * 
 */
final class CocktailSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 0; i < unSortedList.length; i++) {
				if (i + 1 < unSortedList.length && unSortedList[i].getValue() > unSortedList[i + 1].getValue()) {
					swapped = true;
					swap(i, i + 1, unSortedList);
				}
			}
			if (!swapped) {
				break;
			}
			for (int i = unSortedList.length - 2; i >= 0; i--) {
				if (i + 1 > 0 && unSortedList[i].getValue() > unSortedList[i + 1].getValue()) {
					swapped = true;
					swap(i, i + 1, unSortedList);
				}
			}
		} while (swapped);
		return unSortedList;
	}
}

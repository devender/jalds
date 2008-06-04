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
package jalds.alds.al.sorting.linear;

import jalds.alds.SortableObject;
import jalds.alds.al.sorting.Sort;

/**
 * <ul>
 * <li>Find the MaxValue in the given unsorted set.</li>
 * <li>Create a second set with length 0..MaxValue. (lets call this C set) it
 * reperesents the number of times a given value appears in the unsorted set</li>
 * <li>For each value in the unsorted set increment the corresponding element
 * in the second set</li>
 * <li>Create a empty set with size same as the unsorted set (lets call this b)</li>
 * <li>For each element (i) in the unsorted set
 * <ul>
 * <li> place it in b(c(i))</li>
 * <li> decrement c(i)</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * 
 * @author Devender
 * 
 */
public class CountingSort implements Sort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		int maxValue = 0;
		for (SortableObject object : unSortedList) {
			if (object.getValue() > maxValue) {
				maxValue = object.getValue();
			}
		}

		int[] c = new int[maxValue + 1];

		// how many times does a value appear
		for (SortableObject object : unSortedList) {
			c[object.getValue()] = c[object.getValue()] + 1;
		}

		// where in the resulting array can the element go into, number of input
		// elements less than i
		for (int i = 0; i < c.length; i++) {
			if (i - 1 > 0) {
				c[i] = c[i] + c[i - 1];
			}
		}
		SortableObject[] b = new SortableObject[unSortedList.length];

		for (int j = unSortedList.length - 1; j >= 0; j--) {
			SortableObject object = unSortedList[j];
			int i = c[object.getValue()];
			b[i - 1] = object;
			c[object.getValue()] = c[object.getValue()] - 1;
		}
		return b;
	}
}

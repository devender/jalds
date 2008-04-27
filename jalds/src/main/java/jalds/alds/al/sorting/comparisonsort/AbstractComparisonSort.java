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
import jalds.alds.al.sorting.Sort;

/**
 * The base abstract comparison sort, implements Sort and provides helpfull
 * methods to all of the comparison sort classes.
 * 
 * @author Devender Gollapally
 * 
 */
public abstract class AbstractComparisonSort implements Sort {

	public abstract SortableObject[] sort(SortableObject[] unSortedList);

	/**
	 * Swaps to elements in a given array
	 * 
	 * @param pos1
	 * @param pos2
	 * @param array
	 */
	protected void swap(int pos1, int pos2, SortableObject[] array) {
		SortableObject object = array[pos2];
		array[pos2] = array[pos1];
		array[pos1] = object;
	}

}

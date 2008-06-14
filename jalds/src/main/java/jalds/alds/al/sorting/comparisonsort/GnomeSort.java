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
 * The simplest sort algorithm is not Bubble Sort..., it is not Insertion Sort..., it's Gnome Sort!
 * <p>
 * Gnome Sort is based on the technique used by the standard Dutch Garden Gnome (Du.: tuinkabouter).
 * Here is how a garden gnome sorts a line of flower pots. Basically, he looks at the flower pot
 * next to him and the previous one; if they are in the right order he steps one pot forward,
 * otherwise he swaps them and steps one pot backwards. Boundary conditions: if there is no previous
 * pot, he steps forwards; if there is no pot next to him, he is done.
 * 
 * <em> O(n^2) </em>
 * 
 * source http://www.cs.vu.nl/~dick/gnomesort.html
 * 
 * @author Devender Gollapally
 * 
 */
final class GnomeSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		int i = 0;
		while (i < unSortedList.length) {
			if (i == 0 || unSortedList[i - 1].getValue() <= unSortedList[i].getValue()) {
				i++;
			} else {
				swap(i - 1, i, unSortedList);
				i--;
			}
		}
		return unSortedList;
	}
}

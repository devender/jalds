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
import jalds.alds.ds.heaps.Heap;
import jalds.alds.ds.heaps.MinHeapImpl;

/**
 * Running time is <em>O(n log n)</em>, is In Place
 * 
 * @author Devender Gollapally
 * 
 */
public class HeapSort extends AbstractComparisonSort {

	public SortableObject[] sort(SortableObject[] unSortedList) {
		Heap heap = new MinHeapImpl();
		for (SortableObject object : unSortedList) {
			heap.insert(object);
		}

		for (int i = 0; i < unSortedList.length; i++) {
			unSortedList[i] = heap.extract();
		}

		return unSortedList;

	}
}

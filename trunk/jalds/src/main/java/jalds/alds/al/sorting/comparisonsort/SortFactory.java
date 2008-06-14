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

import jalds.alds.al.sorting.Sort;

/**
 * Constructs a Sort of a Type and returns.
 * 
 * @author Devender Gollapally
 * 
 */
public class SortFactory {

	private SortFactory() {
		// restricting any instances
	}

	/**
	 * 
	 * @return Sort returns an implementation of Bubble Sort
	 */
	public static Sort bubbleSort() {
		return new BubbleSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Binary Tree Sort
	 */
	public static Sort binaryTreeSort() {
		return new BinaryTreeSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Cocktail Sort
	 */
	public static Sort cocktailSort() {
		return new CocktailSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Comb Sort
	 */
	public static Sort combSort() {
		return new CombSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Gnome Sort
	 */
	public static Sort gnomeSort() {
		return new GnomeSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Heap Sort
	 */
	public static Sort heapSort() {
		return new HeapSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Insertion Sort
	 */
	public static Sort insertionSort() {
		return new InsertionSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Merge Sort
	 */
	public static Sort mergeSort() {
		return new MergeSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Quick Sort
	 */
	public static Sort quickSort() {
		return new QuickSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Selection Sort
	 */
	public static Sort selectionSort() {
		return new SelectionSort();
	}

	/**
	 * 
	 * @return Sort returns an implementation of Shell Sort
	 */
	public static Sort shellSort() {
		return new ShellSort();
	}
}

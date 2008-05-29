package jalds.alds.ds.heaps;

import jalds.alds.SortableObject;

/**
 * (From Mastering Algorithms with C) Heaps : Trees organized so that we can
 * determine the node with the largest value quickly. The cost to preserve this
 * property is less than that of keeping the data sorted. We can also organize a
 * heap so that we can determine the smallest value just as easily.
 * 
 * There are two kinds of heaps min and max heap, in a max heap for every node
 * the parent is greater than equal to the child node.
 * 
 * Heaps are stored in arrays, the left and right child of any node i is 2(i)+1
 * and 2i+2 and parent of any node at position i in an array is (i-1)/2
 * 
 * @author Devender Gollapally
 * 
 */
public interface Heap {

	/**
	 * initialize the heap, useful if you need to re-initialize the heap
	 * 
	 * @return
	 */
	Heap initialize();

	/**
	 * Insert a new object into the heap, if this violates the heap property
	 * then another procedure is called to fix it.
	 * 
	 * @param sortableObject
	 */
	void insert(SortableObject sortableObject);

	/**
	 * Extract the root node from the heap, this will either be the object with
	 * the max value or the min value depending on weather it is a max heap or a
	 * min heap.
	 * 
	 * The root node is extracted from the heap and the root node is replaced
	 * with the very last node, then the heap property is checked by comparing
	 * the root node with both its child and replaced with the child that is the
	 * greatest difference.
	 * 
	 * @return
	 */
	SortableObject extract();

	/**
	 * Number of nodes in a heap
	 * 
	 * @return
	 */
	int size();
}

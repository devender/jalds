package test.jalds.alds.al.sorting;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.jalds.alds.al.sorting.comparisonsort.TestBinaryTreeSort;
import test.jalds.alds.al.sorting.comparisonsort.TestBubbleSort;
import test.jalds.alds.al.sorting.comparisonsort.TestCocktailSort;
import test.jalds.alds.al.sorting.comparisonsort.TestCombSort;
import test.jalds.alds.al.sorting.comparisonsort.TestGnomeSort;
import test.jalds.alds.al.sorting.comparisonsort.TestInsertionSort;
import test.jalds.alds.al.sorting.comparisonsort.TestMergeSort;
import test.jalds.alds.al.sorting.comparisonsort.TestSelectionSort;
import test.jalds.alds.al.sorting.comparisonsort.TestShellSort;

/**
 * Runs the all of the Comparison Sort unit tests so the user can see how the performance varies
 * between each algorithm.
 * 
 * @author Devender Gollapally
 * 
 */
public class TestComparisonSorts {

	public static Test suite() {
		TestSuite suite = new TestSuite("Testing Comparison Sorts");
		suite.addTestSuite(TestBubbleSort.class);
		suite.addTestSuite(TestCocktailSort.class);
		suite.addTestSuite(TestCombSort.class);
		suite.addTestSuite(TestGnomeSort.class);
		suite.addTestSuite(TestSelectionSort.class);
		suite.addTestSuite(TestInsertionSort.class);
		// Looking at the Results so far, if I dont have time to implement one I would use the Comb
		// Bubble and if I do have time use the Comb Shell to sort a list
		suite.addTestSuite(TestShellSort.class);
		suite.addTestSuite(TestBinaryTreeSort.class);
		suite.addTestSuite(TestMergeSort.class);
		return suite;

	}
}

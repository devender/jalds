package test.jalds.math;

import jalds.math.Summations;
import junit.framework.TestCase;

public class TestSummations extends TestCase {

	public void testsumOfAllNumber1ToN() {
		assertEquals(15, Summations.sumOfAllNumber1ToN(5));
		assertEquals(55, Summations.sumOfAllNumber1ToN(10));
	}

	public void testsumOfSquares() {
		assertEquals(5, Summations.sumOfSquares(2));
		assertEquals(14, Summations.sumOfSquares(3));
	}
	
	public void testsumOfCubes()
	{
		assertEquals(9, Summations.sumOfCubes(2));
	}
}

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
package jalds.math;

/**
 * Some common summations
 * 
 * @author dgollapally
 * 
 */
public class Summations {

	/**
	 * Sum of all number 1 to n
	 * 
	 * @param n
	 * @return int
	 */
	public static int sumOfAllNumber1ToN(int n) {
		Double double1 = 0.5 * n * (n + 1);
		return double1.intValue();
	}

	/**
	 * Sum of all squares 0 to n
	 * 
	 * @param n
	 * @return
	 */
	public static int sumOfSquares(int n) {
		return (n * (n + 1) * (2 * n + 1)) / 6;
	}

	/**
	 * Sum of all cubes o to n
	 * 
	 * @param n
	 * @return
	 */
	public static int sumOfCubes(int n) {
		return ((n * n) * (n + 1) * (n + 1)) / 4;
	}
}

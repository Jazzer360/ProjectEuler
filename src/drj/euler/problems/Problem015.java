package drj.euler.problems;

import drj.euler.Utility;

/**
 * Starting in the top left corner of a 2x2 grid, and only being able to move
 * to the right and down, there are exactly 6 routes to the bottom right corner.
 * 		.__ __
 * 		|__|__|
 * 		|__|__|
 * 			  *
 * How many such routes are there through a 20 x 20 grid?
 */
public class Problem015 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int shortSide = 20;
		int sumOfSides = 40;

		System.out.println(
				Utility.factorial(sumOfSides).divide(
						Utility.factorial(shortSide).multiply(
								Utility.factorial(sumOfSides - shortSide)))
				);
		System.out.println(t.toDecimalString());
	}
}

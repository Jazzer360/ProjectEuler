package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Starting in the top left corner of a 2x2 grid, and only being able to move
 * to the right and down, there are exactly 6 routes to the bottom right corner.
 * <pre>
 * 	.__ __
 * 	|__|__|
 * 	|__|__|
 * 	      *
 * </pre>
 * How many such routes are there through a 20 x 20 grid?
 */
@Answer("137846528820")
public class Problem015 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem015();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int shortSide = 20;
		int sumOfSides = 40;

		return Utility.factorial(sumOfSides).divide(
				Utility.factorial(shortSide).multiply(
						Utility.factorial(sumOfSides - shortSide))).toString();
	}
}

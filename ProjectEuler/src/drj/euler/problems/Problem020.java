package drj.euler.problems;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * n! means n x (n - 1)  ...  3 x 2 x 1
 * 
 * For example, 10! = 10  9  ...  3  2  1 = 3628800,
 * and the sum of the digits in the number 10! is
 * 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * 
 * Find the sum of the digits in the number 100!
 */
public class Problem020 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem020();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int sum = 0;

		for (char digit : Utility.factorial(100).toString().toCharArray()) {
			sum += Utility.charToInt(digit);
		}

		return String.valueOf(sum);
	}
}

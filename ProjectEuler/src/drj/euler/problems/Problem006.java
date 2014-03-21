package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * The sum of the squares of the first ten natural numbers is,
 * 		1^2 + 2^2 + ... + 10^2 = 385
 * 
 * The square of the sum of the first ten natural numbers is,
 * 		(1 + 2 + ... + 10)^2 = 55^2 = 3025
 * 
 * Hence the difference between the sum of the squares of the first ten natural
 * numbers and the square of the sum is 3025  385 = 2640.
 * 
 * Find the difference between the sum of the squares of the first one hundred
 * natural numbers and the square of the sum.
 */
@Answer("25164150")
public class Problem006 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem006();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int sumOfSquares = 0;
		int squareOfSum = 0;

		for (int i = 1; i <= 100; i++) {
			sumOfSquares += i * i;
		}

		squareOfSum = (int) Math.pow((100 * 101) / 2, 2);

		return String.valueOf(squareOfSum - sumOfSquares);
	}
}

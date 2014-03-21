package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * It can be seen that the number, 125874, and its double, 251748, contain
 * exactly the same digits, but in a different order.
 * 
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
 * contain the same digits.
 */
@Answer("142857")
public class Problem052 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem052();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		boolean answerFound = false;
		for (int i = 1; !answerFound; i++) {
			for (int j = 2; j <= 6; j++) {
				if (!Utility.isPermutation(i, i * j)) {
					break;
				} else if (j == 6) {
					answerFound = true;
					return String.valueOf(i);
				}
			}
		}

		return "not found";
	}
}

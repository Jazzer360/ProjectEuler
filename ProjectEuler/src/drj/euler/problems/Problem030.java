package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * Surprisingly there are only three numbers that can be written as the sum of
 * fourth powers of their digits:
 * 
 * <p>		1634 = 1^4 + 6^4 + 3^4 + 4^4
 * <br>		8208 = 8^4 + 2^4 + 0^4 + 8^4
 * <br>		9474 = 9^4 + 4^4 + 7^4 + 4^4
 * 
 * <p>As 1 = 1^4 is not a sum it is not included.
 * 
 * <p>The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * 
 * <p>Find the sum of all the numbers that can be written as the sum of fifth
 * powers of their digits.
 */
@Answer("443839")
public class Problem030 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem030();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int total = 0;
		for (int i = 10; i < 194_980; i++) {
			int num = i;
			int sum = 0;
			while (num > 0) {
				sum += Math.pow(num % 10, 5);
				num /= 10;
				if (sum > i) {
					break;
				}
			}
			if (sum == i) {
				total += i;
			}
		}

		return String.valueOf(total);
	}
}

package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * 
 * <p>Find the sum of all numbers which are equal to the sum of the factorial
 * of their digits.
 * 
 * <p>Note: as 1! = 1 and 2! = 2 are not sums they are not included. 
 */
@Answer("40730")
public class Problem034 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem034();
		System.out.println(p);
	}

	private static final int[] FACTORIALS =
		{1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880};

	@Override
	protected String onSolve() {
		int total = 0;

		for (int i = 10; i < 2_540_160; i++) {
			if (isFactorian(i)) {
				total += i;
			}
		}

		return String.valueOf(total);
	}

	private static boolean isFactorian(int n) {
		int sum = 0;
		int num = n;

		while (num > 0) {
			sum += FACTORIALS[num % 10];
			num /= 10;
		}

		return sum == n ? true : false;
	}
}

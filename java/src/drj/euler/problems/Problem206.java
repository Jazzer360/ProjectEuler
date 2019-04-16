package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * Find the unique positive integer whose square has the form
 * 
 * <p>		1_2_3_4_5_6_7_8_9_0
 * 
 * <p>where each “_” is a single digit.
 */
@Answer("1389019170")
public class Problem206 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem206();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		long answer = 0;

		for (long i = 1_010_101_010; i < 1_414_213_563 ; i++) {
			if (meetsCriteria(i * i)) {
				answer = i;
				break;
			}
		}

		return String.valueOf(answer);
	}

	private static boolean meetsCriteria(long i) {
		if (i % 10 != 0) {
			return false;
		}

		i /= 100;

		for (long n = 9; n > 0; n--, i /= 100) {
			if (i % 10 != n) {
				return false;
			}
		}

		return true;
	}
}

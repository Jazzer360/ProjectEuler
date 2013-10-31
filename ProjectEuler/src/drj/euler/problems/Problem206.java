package drj.euler.problems;

import drj.euler.Utility;

/**
 * Find the unique positive integer whose square has the form
 * 
 * 		1_2_3_4_5_6_7_8_9_0
 * 
 * where each “_” is a single digit.
 */
public class Problem206 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		long answer = 0;

		for (long i = 1_010_101_010; i < 1_414_213_563 ; i++) {
			if (meetsCriteria(i * i)) {
				answer = i;
				break;
			}
		}

		System.out.println(answer);
		System.out.println(t.toDecimalString());
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

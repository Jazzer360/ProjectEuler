package drj.euler.problems;

import drj.euler.Utility;

/**
 * Some positive integers n have the property that the sum [ n + reverse(n) ]
 * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and
 * 409 + 904 = 1313. We will call such numbers reversible; so 36, 63, 409, and
 * 904 are reversible. Leading zeroes are not allowed in either n or reverse(n).
 * 
 * There are 120 reversible numbers below one-thousand.
 * 
 * How many reversible numbers are there below one-billion (10^9)?
 */
public class Problem145 {

	private static final int THRESHOLD = 1_000_000_000;

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int count = 0;

		for (int i = 0; i < THRESHOLD; i++) {
			if (isReversible(i))
				count++;
		}

		System.out.println(count);
		System.out.println(t.toDecimalString());
	}

	private static int reverse(int num) {
		int reverse = 0;

		while (num > 0) {
			reverse *= 10;
			reverse += num % 10;
			num /= 10;
		}

		return reverse;
	}

	private static boolean isReversible(int num) {
		if (num % 10 == 0)
			return false;
		int sum = num;
		sum += reverse(num);

		while (sum > 0) {
			if (sum % 10 % 2 == 0) {
				return false;
			}
			sum /= 10;
		}

		return true;
	}
}

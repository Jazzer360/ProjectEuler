package drj.euler.problems;

import drj.euler.Utility;

/**
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * 
 * Find the sum of all numbers which are equal to the sum of the factorial of
 * their digits.
 * 
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included. 
 */
public class Problem034 {

	private static final int[] FACTORIALS =
		{1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880};

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int total = 0;

		for (int i = 10; i < 2_540_160; i++) {
			if (isFactorian(i)) {
				total += i;
			}
		}
		System.out.println(total);
		System.out.println(t.toDecimalString());
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

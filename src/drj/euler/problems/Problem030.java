package drj.euler.problems;

import drj.euler.Utility;

/**
 * Surprisingly there are only three numbers that can be written as the sum of
 * fourth powers of their digits:
 * 
 * 		1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 		8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 		9474 = 9^4 + 4^4 + 7^4 + 4^4
 * 
 * As 1 = 1^4 is not a sum it is not included.
 * 
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * 
 * Find the sum of all the numbers that can be written as the sum of fifth
 * powers of their digits.
 */
public class Problem030 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int total = 0;
		for (int i = 10; i < 200_000; i++) {
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

		System.out.println(total);
		System.out.println(t.toDecimalString());
	}
}

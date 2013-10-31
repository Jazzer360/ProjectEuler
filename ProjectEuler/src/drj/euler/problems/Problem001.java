package drj.euler.problems;

import drj.euler.Utility;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class Problem001 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int sumOfMultiples = 0;

		for (int i = 0; i < 1000; i++) {
			if ((i % 3 == 0) || (i % 5 == 0)) {
				sumOfMultiples += i;
			}
		}
		System.out.println(sumOfMultiples);
		System.out.println(t.toDecimalString());
	}
}

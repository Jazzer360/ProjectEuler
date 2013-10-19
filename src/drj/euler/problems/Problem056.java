package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Utility;

/**
 * A googol (10^100) is a massive number: one followed by one-hundred zeros;
 * 100^100 is almost unimaginably large: one followed by two-hundred zeros.
 * Despite their size, the sum of the digits in each number is only 1.
 * 
 * Considering natural numbers of the form, a^b, where a, b < 100, what is the
 * maximum digital sum?
 */
public class Problem056 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int maxSum = 0;

		for (int a = 1; a < 100; a++) {
			for (int b = 1; b < 100; b++) {
				if (digitalSum(BigInteger.valueOf(a).pow(b)) > maxSum) {
					maxSum = digitalSum(BigInteger.valueOf(a).pow(b));
				}
			}
		}

		System.out.println(maxSum);
		System.out.println(t.toDecimalString());
	}

	private static int digitalSum(BigInteger num) {

		int sum = 0;

		for (char digit : num.toString().toCharArray()) {
			sum += Utility.charToInt(digit);
		}

		return sum;
	}
}

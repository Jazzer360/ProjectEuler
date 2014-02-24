package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Utility;

/**
 * There are exactly ten ways of selecting three from five, 12345:
 * 
 * 		123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * 
 * In combinatorics, we use the notation, 5C3 = 10.
 * 
 * In general,
 * 		nCr = n! / r!(n-r)!  ,where r <= n, n! = n(n-1)...3x2x1, and 0! = 1.
 * 
 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
 * 
 * How many, not necessarily distinct, values of  nCr, for 1 <= n <= 100, are
 * greater than one-million?
 */
public class Problem053 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int count = 0;

		for (int n = 1; n <= 100; n++) {
			for (int r = n; r > 0; r--) {
				if (combinations(n, r)
						.compareTo(BigInteger.valueOf(1_000_000)) > 0) {
					count++;
				}
			}
		}

		System.out.println(count);
		System.out.println(t.toDecimalString());
	}

	private static BigInteger combinations(int n, int r) {
		return Utility.factorial(n)
				.divide(Utility.factorial(r)
						.multiply(Utility.factorial(n - r)));
	}
}
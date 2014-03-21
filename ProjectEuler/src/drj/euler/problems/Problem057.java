package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Problem;

/**
 * It is possible to show that the square root of two can be expressed as an
 * infinite continued fraction.
 * 
 * 			2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
 * 
 * By expanding this for the first four iterations, we get:
 * 
 * 			1 + 1/2 = 3/2 = 1.5
 * 			1 + 1/(2 + 1/2) = 7/5 = 1.4
 * 			1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 * 			1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 * 
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth
 * expansion, 1393/985, is the first example where the number of digits in the
 * numerator exceeds the number of digits in the denominator.
 * 
 * In the first one-thousand expansions, how many fractions contain a numerator
 * with more digits than denominator?
 */
public class Problem057 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem057();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		BigInteger TWO = BigInteger.valueOf(2);
		BigInteger numerator = BigInteger.ONE;
		BigInteger denominator = TWO;
		int count = 0;

		for (int i = 1; i <= 1000; i++) {
			int numeratorLength =
					numerator.add(denominator).toString().length();
			int denominatorLength =
					denominator.toString().length();
			if (numeratorLength > denominatorLength) {
				count++;
			}

			BigInteger newNumerator = denominator;
			denominator = denominator.multiply(TWO).add(numerator);
			numerator = newNumerator;
		}

		return String.valueOf(count);
	}
}

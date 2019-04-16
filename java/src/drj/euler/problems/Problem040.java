package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * An irrational decimal fraction is created by concatenating the positive
 * integers:
 * 
 * <p>		0.123456789101112131415161718192021...
 * 
 * <p>It can be seen that the 12th digit of the fractional part is 1.
 * 
 * <p>If dn represents the nth digit of the fractional part, find the value of
 * the following expression.
 * 
 * <p>		d1 x d10 x d100 x d1000 x d10000 x d100000 x d1000000
 */
@Answer("210")
public class Problem040 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem040();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		StringBuilder decimal = new StringBuilder();

		for (int i = 1; decimal.length() < 1_000_000; i++) {
			decimal.append(i);
		}

		int product = 1;

		for (int i = 1; i <= 1_000_000; i *= 10) {
			product *= Utility.charToInt(decimal.charAt(i - 1));
		}

		return String.valueOf(product);
	}
}

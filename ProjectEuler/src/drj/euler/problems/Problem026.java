package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of
 * the unit fractions with denominators 2 to 10 are given:
 * 
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 * 
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be
 * seen that 1/7 has a 6-digit recurring cycle.
 * 
 * Find the value of d < 1000 for which 1/d contains the longest recurring
 * cycle in its decimal fraction part.
 */
@Answer("983")
public class Problem026 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem026();
		System.out.println(p);
	}

	private static BigInteger k = BigInteger.TEN.pow(10);

	@Override
	protected String onSolve() {
		int maxReciprocal = 2;
		int maxPeriod = 0;

		for (int i = 2; i < 1000; i++) {
			if (!reciprocalTerminates(i)) {
				int period = periodOfReciprocal(i);
				if (period > maxPeriod) {
					maxPeriod = period;
					maxReciprocal = i;
				}
			}
		}

		return String.valueOf(maxReciprocal);
	}

	private static boolean reciprocalTerminates(int n) {
		if (k.mod(BigInteger.valueOf(n)).equals(BigInteger.ZERO)) {
			return true;
		}
		return false;
	}

	private static int periodOfReciprocal(int n) {
		int d = 1;

		while (!k.multiply(
				BigInteger.valueOf(10).pow(d).subtract(BigInteger.valueOf(1)))
				.mod(BigInteger.valueOf(n)).equals(BigInteger.valueOf(0))) {
			d++;
		}

		return d;
	}
}

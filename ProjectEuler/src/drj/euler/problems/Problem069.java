package drj.euler.problems;

import java.util.HashMap;
import java.util.Map;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Euler's Totient function, t(n) [sometimes called the phi function], is used
 * to determine the number of numbers less than n which are relatively prime to
 * n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and
 * relatively prime to nine, t(9)=6.
 * 
 * n	Relatively Prime	t(n)	n/t(n)
 * 2	1					1		2
 * 3	1,2					2		1.5
 * 4	1,3					2		2
 * 5	1,2,3,4				4		1.25
 * 6	1,5					2		3
 * 7	1,2,3,4,5,6			6		1.1666...
 * 8	1,3,5,7				4		2
 * 9	1,2,4,5,7,8			6		1.5
 * 10	1,3,7,9				4		2.5
 * 
 * It can be seen that n=6 produces a maximum n/t(n) for n <= 10.
 *
 * Find the value of n <= 1,000,000 for which n/t(n) is a maximum.
 */
public class Problem069 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem069();
		System.out.println(p);
	}

	private static Map<Integer, Integer> totients =
			new HashMap<Integer, Integer>();

	@Override
	protected String onSolve() {
		double tMax = 2.0;
		int nMax = 2;

		for (int i = 2; i < 1_000_000; i++) {
			int totient = totient(i);
			double quotient = (double) i / totient;
			if (quotient > tMax) {
				nMax = i;
				tMax = quotient;
			}
		}

		return String.valueOf(nMax);
	}

	private static int totient(int num) {
		if (num == 1) {
			return 1;
		}

		int factorTotientSum = 0;

		for (int factor : Utility.getFactors(num)) {
			if (factor == num) {
				break;
			}
			if (!totients.containsKey(factor)) {
				totients.put(factor, totient(factor));
			}
			factorTotientSum += totients.get(factor);
		}

		return num - factorTotientSum;
	}
}

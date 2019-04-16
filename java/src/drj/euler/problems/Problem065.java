package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * The square root of 2 can be written as an infinite continued fraction.
 * <pre>
 *      root2 = 1 + 1
 *                  2 + 1
 *                      2 + 1
 *                          2 + 1
 *                              2 + ...</pre>
 * 
 * The infinite continued fraction can be written, root2 = [1;(2)], (2)
 * indicates that 2 repeats ad infinitum. In a similar way,
 * root23 = [4;(1,3,1,8)].
 * 
 * <p>It turns out that the sequence of partial values of continued fractions for
 * square roots provide the best rational approximations. Let us consider the
 * convergents for root2.
 * <pre>
 *      1 + 1 = 3/2
 *          2
 * 
 *      1 + 1 = 7/5
 *          2 + 1
 *              2
 * 
 *      1 + 1  = 17/12
 *          2 + 1
 *              2 + 1
 *                  2
 * 
 *      1 + 1 = 41/29
 *          2 + 1
 *              2 + 1
 *                  2 + 1
 *                      2</pre>
 * 
 *  <p>Hence the sequence of the first ten convergents for root2 are:
 * 
 *  <p>1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378,
 *  ...
 * 
 *  <p>What is most surprising is that the important mathematical constant,
 *  e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].
 * 
 *  <p>The first ten terms in the sequence of convergents for e are:
 * 
 *  <p>2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
 * 
 *  <p>The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.
 * 
 *  <p>Find the sum of digits in the numerator of the 100th convergent of the
 *  continued fraction for e.
 */
@Answer("272")
public class Problem065 extends Problem {

	private static final int TERM_NUMBER = 100;

	public static void main(String[] args) {
		Problem p = new Problem065();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int[] eConvergents = new int[1000];
		eConvergents[0] = 2;

		for (int i = 1, num = 0; i < 1000; i++) {
			eConvergents[i] = (i - 2) % 3 == 0 ? num += 2 : 1;
		}

		BigInteger numerator = BigInteger.ONE;
		BigInteger denominator =
				BigInteger.valueOf(eConvergents[TERM_NUMBER - 1]);

		for (int i = TERM_NUMBER - 2; true; i--) {
			BigInteger temp =
					denominator.multiply(BigInteger.valueOf(eConvergents[i]))
					.add(numerator);
			if (i == 0) {
				denominator = numerator;
				numerator = temp;
				break;
			}
			numerator = denominator;
			denominator = temp;
		}

		int sum = 0;
		while (numerator.compareTo(BigInteger.ZERO) > 0) {
			sum += numerator.mod(BigInteger.TEN).intValue();
			numerator = numerator.divide(BigInteger.TEN);
		}

		return String.valueOf(sum);
	}
}

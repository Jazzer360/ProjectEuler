package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Euler published the remarkable quadratic formula:
 * 
 * 		n² + n + 41
 * 
 * It turns out that the formula will produce 40 primes for the consecutive
 * values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is
 * divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly
 * divisible by 41.
 * 
 * Using computers, the incredible formula  n²  79n + 1601 was discovered,
 * which produces 80 primes for the consecutive values n = 0 to 79. The product
 * of the coefficients, 79 and 1601, is 126479.
 * 
 * Considering quadratics of the form:
 * 
 * 		n² + an + b, where |a|  1000 and |b|  1000
 * 
 * 		where |n| is the modulus/absolute value of n
 * 		e.g. |11| = 11 and |4| = 4
 * 
 * Find the product of the coefficients, a and b, for the quadratic expression
 * that produces the maximum number of primes for consecutive values of n,
 * starting with n = 0.
 */
@Answer("-59231")
public class Problem027 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem027();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int maxPrimeCount = 0;
		int maxPrimeA = -999;
		int maxPrimeB = -999;

		for (int a = -999; a < 1000; a++) {
			for (int b = -999; b < 1000; b++) {
				int n;
				for (n = 0; returnsPrime(a, b, n); n++) {}
				if (n > maxPrimeCount) {
					maxPrimeCount = n;
					maxPrimeA = a;
					maxPrimeB = b;
				}
			}
		}

		return String.valueOf(maxPrimeA * maxPrimeB);
	}

	private static boolean returnsPrime(int a, int b, int n) {
		return Utility.isPrime((n * n) + (a * n) + b) ? true : false;
	}
}

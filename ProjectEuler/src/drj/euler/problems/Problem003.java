package drj.euler.problems;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * 
 * What is the largest prime factor of the number 600851475143 ?
 */
public class Problem003 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem003();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		long num = 600851475143L;
		long maxPrimeFactor = 2;

		for (int factor = 3; factor < Math.sqrt(num); factor += 2) {
			if (num % factor == 0) {
				long other = num / factor;
				if (Utility.isPrime(other)) {
					maxPrimeFactor = other;
					break;
				}
				if (factor > maxPrimeFactor && Utility.isPrime(factor)) {
					maxPrimeFactor = factor;
				}
			}
		}

		return String.valueOf(maxPrimeFactor);
	}
}

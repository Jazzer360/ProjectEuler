package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * 
 * <p>Find the sum of all the primes below two million.
 */
@Answer("142913828922")
public class Problem010 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem010();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		Utility.PrimeSieve p = new Utility.PrimeSieve(1_999_999);

		long sum = 0;

		for (int prime = 2;
				prime < 2_000_000;
				prime = (int) p.getNextPrime(prime)) {
			sum += prime;
		}

		return String.valueOf(sum);
	}
}

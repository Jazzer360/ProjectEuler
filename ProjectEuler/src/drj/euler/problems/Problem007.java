package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13.
 * 
 * <p>What is the 10 001st prime number?
 */
@Answer("104743")
public class Problem007 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem007();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		Utility.PrimeSieve p = new Utility.PrimeSieve();

		int prime = 1;
		for (int i = 1; i <= 10_001; i++) {
			prime = (int) p.getNextPrime(prime);
		}

		return String.valueOf(prime);
	}
}

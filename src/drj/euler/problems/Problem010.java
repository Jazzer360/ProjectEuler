package drj.euler.problems;

import drj.euler.Utility;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * 
 * Find the sum of all the primes below two million.
 */
public class Problem010 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		Utility.PrimeSieve p = new Utility.PrimeSieve(1_999_999);

		long sum = 0;

		for (int prime = 2;
				prime < 2_000_000;
				prime = (int) p.nextPrime(prime)) {
			sum += prime;
		}

		System.out.println(sum);
		System.out.println(t.toDecimalString());
	}
}

package drj.euler.problems;

import drj.euler.Utility;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13.
 * 
 * What is the 10 001st prime number?
 */
public class Problem007 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		Utility.PrimeSieve p = new Utility.PrimeSieve();

		int prime = 1;
		for (int i = 1; i <= 10_001; i++) {
			prime = (int) p.nextPrime(prime);
		}

		System.out.println(prime);
		System.out.println(t.toDecimalString());
	}
}

package drj.euler.problems;

import java.util.ArrayList;

import drj.euler.Utility;

/**
 * The first two consecutive numbers to have two distinct prime factors are:
 *
 * 		14 = 2 x 7
 * 		15 = 3 x 5
 *
 * The first three consecutive numbers to have three distinct prime factors are:
 *
 * 		644 = 2² x 7 x 23
 * 		645 = 3 x 5 x 43
 * 		646 = 2 x 17 x 19
 *
 * Find the first four consecutive integers to have four distinct prime
 * factors. What is the first of these numbers?
 */
public class Problem047 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int numPrimeFactors = 4;
		int numConsecutive = 4;
		int streak = 0;
		int answer = 0;

		for (int i = 1; true; i++) {
			if (numberOfPrimes(Utility.getFactors(i)) == numPrimeFactors) {
				streak++;
			} else {
				streak = 0;
			}

			if (streak == numConsecutive) {
				answer = i - streak + 1;
				break;
			}
		}

		System.out.println(answer);
		System.out.println(t.toDecimalString());
	}

	private static int numberOfPrimes(ArrayList<Integer> list) {
		int count = 0;

		for (int num : list) {
			if (Utility.isPrime(num)) {
				count++;
			}
		}

		return count;
	}
}

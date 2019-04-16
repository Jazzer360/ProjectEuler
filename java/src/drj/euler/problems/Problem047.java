package drj.euler.problems;

import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The first two consecutive numbers to have two distinct prime factors are:
 *
 * <p>		14 = 2 x 7
 * <br>		15 = 3 x 5
 *
 * <p>The first three consecutive numbers to have three distinct prime factors are:
 *
 * <p>		644 = 2² x 7 x 23
 * <br>		645 = 3 x 5 x 43
 * <br>		646 = 2 x 17 x 19
 *
 * <p>Find the first four consecutive integers to have four distinct prime
 * factors. What is the first of these numbers?
 */
@Answer("134043")
public class Problem047 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem047();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
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

		return String.valueOf(answer);
	}

	private static int numberOfPrimes(List<Integer> list) {
		int count = 0;

		for (int num : list) {
			if (Utility.isPrime(num)) {
				count++;
			}
		}

		return count;
	}
}

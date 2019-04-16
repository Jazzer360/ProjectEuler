package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from
 * 1 to 10 without any remainder.
 * 
 * <p>What is the smallest positive number that is evenly divisible by all of
 * the numbers from 1 to 20?
 */
@Answer("232792560")
public class Problem005 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem005();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int step = 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19;
		int num = step;
		while (!isDivisibleByRange(num)) num += step;
		return String.valueOf(num);
	}

	private static boolean isDivisibleByRange(int num) {
		for (int i = 20; i > 1; i--) {
			if (num % i != 0) {
				return false;
			}
		}
		return true;
	}
}

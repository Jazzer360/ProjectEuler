package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * A number chain is created by continuously adding the square of the digits in
 * a number to form a new number until it has been seen before.
 * 
 * For example,
 * 			44 -> 32 -> 13 -> 10 -> 1 -> 1
 * 			85 -> 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 -> 89
 * 
 * Therefore any chain that arrives at 1 or 89 will become stuck in an endless
 * loop. What is most amazing is that EVERY starting number will eventually
 * arrive at 1 or 89.
 * 
 * How many starting numbers below ten million will arrive at 89?
 */
@Answer("8581146")
public class Problem092 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem092();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int count = 0;

		for (long i = 1; i < 10_000_000; i++) {
			long num = i;
			while (num != 1 && num != 89) {
				num = getNext(num);
			}
			if (num == 89) {
				count++;
			}
		}

		return String.valueOf(count);
	}

	private static long getNext(long num) {
		long next = 0;
		while (num > 0) {
			next += (num % 10) * (num % 10);
			num /= 10;
		}
		return next;
	}
}

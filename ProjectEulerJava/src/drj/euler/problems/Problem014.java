package drj.euler.problems;

import java.util.HashMap;
import java.util.Map;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * 
 * <p>		n -> n/2 (n is even)
 * <br>		n -> 3n + 1 (n is odd)
 * 
 * <p>Using the rule above and starting with 13, we generate the following
 * sequence:
 * 
 * <p>		13  40  20  10  5  16  8  4  2  1
 * 
 * <p>It can be seen that this sequence (starting at 13 and finishing at 1)
 * contains 10 terms. Although it has not been proved yet (Collatz Problem), it
 * is thought that all starting numbers finish at 1.
 * 
 * <p>Which starting number, under one million, produces the longest chain?
 * 
 * <p>NOTE: Once the chain starts the terms are allowed to go above one million.
 */
@Answer("837799")
public class Problem014 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem014();
		System.out.println(p);
	}

	private Map<Long, Integer> cache;

	@Override
	protected String onSolve() {
		cache = new HashMap<>();

		int maxStart = 1;
		int maxLength = 1;

		for (int i = maxStart; i < 1_000_000; i++) {
			int length = getCollatzLength(i);
			if (length > maxLength) {
				maxStart = i;
				maxLength = length;
			}
		}

		return String.valueOf(maxStart);
	}

	private int getCollatzLength(long num) {
		if (num == 1) return 1;
		if (cache.get(num) != null) return cache.get(num);
		return getCollatzLength(num % 2 == 0 ? num / 2 : num * 3 + 1) + 1;
	}
}
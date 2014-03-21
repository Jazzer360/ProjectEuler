package drj.euler.problems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * 
 * 		n -> n/2 (n is even)
 * 		n -> 3n + 1 (n is odd)
 * 
 * Using the rule above and starting with 13, we generate the following
 * sequence:
 * 
 * 		13  40  20  10  5  16  8  4  2  1
 * 
 * It can be seen that this sequence (starting at 13 and finishing at 1)
 * contains 10 terms. Although it has not been proved yet (Collatz Problem), it
 * is thought that all starting numbers finish at 1.
 * 
 * Which starting number, under one million, produces the longest chain?
 * 
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
@Answer("837799")
public class Problem014 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem014();
		System.out.println(p);
	}

	private static final BigInteger THREE = BigInteger.valueOf(3);
	private static final BigInteger TWO = BigInteger.valueOf(2);
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger ZERO = BigInteger.ZERO;
	
	private Map<BigInteger, Integer> COLLATZ_LENGTH_CACHE;

	@Override
	protected String onSolve() {
		COLLATZ_LENGTH_CACHE = new HashMap<>();
		int maxStart = 2;
		int maxLength = 2;

		for (int i = maxStart; i < 1_000_000; i++) {
			int length = getCollatzLength(BigInteger.valueOf(i));
			if (getCollatzLength(BigInteger.valueOf(i)) > maxLength) {
				maxStart = i;
				maxLength = length;
			}
		}

		return String.valueOf(maxStart);
	}

	private int getCollatzLength(BigInteger num) {
		if (num.compareTo(ONE) == 0) {
			return 1;
		}
		if (COLLATZ_LENGTH_CACHE.get(num) != null) {
			return COLLATZ_LENGTH_CACHE.get(num);
		}
		if (num.mod(TWO).compareTo(ZERO) == 0) {
			COLLATZ_LENGTH_CACHE.put(
					num, getCollatzLength(num.divide(TWO)) + 1);
			return COLLATZ_LENGTH_CACHE.get(num);
		} else {
			COLLATZ_LENGTH_CACHE.put(
					num, getCollatzLength(num.multiply(THREE).add(ONE)) + 1);
			return COLLATZ_LENGTH_CACHE.get(num);
		}
	}
}

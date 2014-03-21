package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Problem;

/**
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * 
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class Problem048 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem048();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		BigInteger sum = BigInteger.ZERO;

		for (int i = 1; i <= 1000; i++) {
			sum = sum.add(BigInteger.valueOf(i).pow(i));
		}

		String answer = sum.toString();

		return answer.substring(
				answer.length() - 10, answer.length());
	}
}

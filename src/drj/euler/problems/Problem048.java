package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Utility;

/**
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * 
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class Problem048 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		BigInteger sum = BigInteger.ZERO;

		for (int i = 1; i <= 1000; i++) {
			sum = sum.add(BigInteger.valueOf(i).pow(i));
		}

		String answer = sum.toString();

		System.out.println(answer.substring(
				answer.length() - 10, answer.length()));
		System.out.println(t.toDecimalString());
	}
}

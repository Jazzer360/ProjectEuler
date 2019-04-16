package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * 
 * <p>What is the sum of the digits of the number 2^1000?
 */
@Answer("1366")
public class Problem016 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem016();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		BigInteger num = BigInteger.valueOf(2).pow(1000);
		int sum = 0;

		for (char digit : num.toString().toCharArray()) {
			sum += Utility.charToInt(digit);
		}

		return String.valueOf(sum);
	}
}

package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Utility;

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * 
 * What is the sum of the digits of the number 2^1000?
 */
public class Problem016 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		BigInteger num = BigInteger.valueOf(2).pow(1000);
		int sum = 0;

		for (char digit : num.toString().toCharArray()) {
			sum += Utility.charToInt(digit);
		}

		System.out.println(sum);
		System.out.println(t.toDecimalString());
	}
}

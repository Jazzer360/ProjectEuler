package drj.euler.problems;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit
 * number, 134217728=8^9, is a ninth power.
 * 
 * <p>How many n-digit positive integers exist which are also an nth power?
 */
@Answer("49")
public class Problem063 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem063();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		Set<BigInteger> nums = new HashSet<BigInteger>();

		for (int i = 1; i <= 9; i++) {
			BigInteger n = BigInteger.valueOf(i);
			BigInteger base = BigInteger.valueOf(i);
			int exp = 1;
			while (base.toString().length() == exp) {
				nums.add(base);
				base = base.multiply(n);
				exp++;
			}
		}

		return String.valueOf(nums.size());
	}
}

package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 * 
 * <p>Find the sum of all numbers, less than one million, which are palindromic
 * in base 10 and base 2.
 * 
 * <p>(Please note that the palindromic number, in either base, may not include
 * leading zeros.)
 */
@Answer("872187")
public class Problem036 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem036();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int sum = 0;
		for (int i = 0; i < 1_000_000; i++) {
			if (Utility.isPalindrome(i) &&
					Utility.isPalindrome(Integer.toBinaryString(i))) {
				sum += i;
			}
		}

		return String.valueOf(sum);
	}
}

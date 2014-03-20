package drj.euler.problems;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 x 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Problem004 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem004();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int maxFound = 0;

		for (int i = 999; i >= 100; i--) {
			for (int j = 999; j >=100; j--) {
				int n = i * j;
				if (n > maxFound) {
					if (Utility.isPalindrome(n)) maxFound = n;
				} else {
					break;
				}
			}
		}

		return String.valueOf(maxFound);
	}
}

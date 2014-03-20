package drj.euler.problems;

import drj.euler.Utility;

/**
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 x 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Problem004 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int maxFound = 0;

		for (int i = 999; i >= 100; i--) {
			for (int j = 999; j >=100; j--) {
				int n = i * j;
				if (n > maxFound) {
					if (isPalindrome(n)) maxFound = n;
				} else {
					break;
				}
			}
		}

		System.out.println(maxFound);
		System.out.println(t.toDecimalString());
	}
	
	private static boolean isPalindrome(int num) {
		return String.valueOf(num).equals(
				new StringBuilder(String.valueOf(num)).reverse().toString());
	}
}

package drj.euler.problems;

import drj.euler.Utility;

/**
 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 * 
 * Find the sum of all numbers, less than one million, which are palindromic in
 * base 10 and base 2.
 * 
 * (Please note that the palindromic number, in either base, may not include
 * leading zeros.)
 */
public class Problem036 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int sum = 0;
		for (int i = 0; i < 1_000_000; i++) {
			if (Utility.isPalindrome(i) &&
					Utility.isPalindrome(Integer.toBinaryString(i))) {
				sum += i;
			}
		}
		System.out.println(sum);
		System.out.println(t.toDecimalString());
	}
}

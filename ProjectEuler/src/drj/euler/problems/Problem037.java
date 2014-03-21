package drj.euler.problems;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The number 3797 has an interesting property. Being prime itself, it is
 * possible to continuously remove digits from left to right, and remain prime
 * at each stage: 3797, 797, 97, and 7. Similarly we can work from right to
 * left: 3797, 379, 37, and 3.
 * 
 * Find the sum of the only eleven primes that are both truncatable from left
 * to right and right to left.
 * 
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class Problem037 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem037();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int sum = 0;

		for (int count = 0, num = 23;
				count < 11;
				num += 2) {
			if (isTruncatablePrime(num)) {
				sum += num;
				count++;
			}
		}

		return String.valueOf(sum);
	}

	private static boolean isTruncatablePrime(int num) {
		char[] digits = String.valueOf(num).toCharArray();
		int len = digits.length;

		if ((digits[0] == '2' || digits[0] == '3'
				|| digits[0] == '5' || digits[0] == '7')
				&&
				(digits[len - 1] == '3' || digits[len - 1] == '5'
				|| digits[len - 1] == '7')) {
			StringBuilder numString = new StringBuilder();
			for (int i = 0; i < len; i++) {
				numString.append(digits[i]);
				if (!Utility.isPrime(Integer.parseInt(numString.toString()))) {
					return false;
				}
			}
			for (int i = 0; i < len; i++) {
				numString.delete(0, Integer.MAX_VALUE);
				for (int j = i; j < len; j++) {
					numString.append(digits[j]);
				}
				if (!Utility.isPrime(Integer.parseInt(numString.toString()))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}

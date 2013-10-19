package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;

import drj.euler.Utility;

/**
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six
 * of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
 * 
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this
 * 5-digit number is the first example having seven primes among the ten
 * generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663,
 * 56773, and 56993. Consequently 56003, being the first member of this family,
 * is the smallest prime with this property.
 * 
 * Find the smallest prime which, by replacing part of the number (not
 * necessarily adjacent digits) with the same digit, is part of an eight prime
 * value family.
 */
public class Problem051 {

	private static Utility.PrimeSieve sieve = new Utility.PrimeSieve();

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		List<Integer> results = new ArrayList<Integer>();
		boolean answerFound = false;

		for (int i = 56003; !answerFound; i+=2) {
			if (sieve.isPrime(i)) {
				String num = String.valueOf(i);
				for (int mask = 1; mask <= Math.pow(2, num.length()) - 2;
						mask++) {
					if (getPrimeCount(num, mask) == 8) {
						results = getMaskedNums(num, mask);
						answerFound = true;
						break;
					}
				}
			}
		}
		for (int n : results) {
			if (sieve.isPrime(n)) {
				System.out.println(n);
				break;
			}
		}

		System.out.println(t.toDecimalString());
	}

	private static int getPrimeCount(String num, int mask) {
		int count = 0;

		for (int i : getMaskedNums(num, mask)) {
			if (sieve.isPrime(i)) {
				count++;
			}
		}

		return count;
	}

	private static List<Integer> getMaskedNums(String num, int mask) {
		List<Integer> result = new ArrayList<Integer>();
		StringBuilder s = new StringBuilder(num);

		for (int i = 0; i <= 9; i++) {
			maskNumber(s, mask, i);
			if (s.charAt(0) != '0') {
				result.add(Integer.valueOf(s.toString()));
			}
		}

		return result;
	}

	private static void maskNumber(StringBuilder num, int mask, int replacer) {
		for (int bit = 1; bit <= mask; bit<<=1) {
			if ((bit & mask) == bit) {
				int maxIndex = num.length() - 1;
				num.setCharAt(maxIndex - Integer.numberOfTrailingZeros(bit),
						Character.forDigit(replacer, 10));
			}
		}
	}
}

package drj.euler.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import drj.euler.Problem;

/**
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up
 * of each of the digits 0 to 9 in some order, but it also has a rather
 * interesting sub-string divisibility property.
 * 
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we
 * note the following:
 * 
 * 		d2 d3 d4 = 406 is divisible by 2
 * 		d3 d4 d5 = 063 is divisible by 3
 * 		d4 d5 d6 = 635 is divisible by 5
 * 		d5 d6 d7 = 357 is divisible by 7
 * 		d6 d7 d8 = 572 is divisible by 11
 * 		d7 d8 d9 = 728 is divisible by 13
 * 		d8 d9 d10 = 289 is divisible by 17
 * 
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class Problem043 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem043();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		List<ArrayList<String>> multiples = new ArrayList<ArrayList<String>>();
		int[] primes = {2, 3, 5, 7, 11, 13, 17};

		for (int i = 1; i <= 7; i++) {
			multiples.add(new ArrayList<String>());
		}

		for (int i = 12; i < 1000; i++) {
			for (int j = 0; j < 7; j++) {
				if (i % primes[j] == 0 && !hasDuplicates(String.valueOf(i))) {
					if (i < 100) {
						if (!(i % 10 == 0)) {
							multiples.get(j).add("0" + String.valueOf(i));
						}
					} else {
						multiples.get(j).add(String.valueOf(i));
					}
				}
			}
		}

		List<String> answers = new ArrayList<String>();

		for (String s2 : multiples.get(0)) {
			for (String s7 : multiples.get(3)) {
				for (String s17 : multiples.get(6)) {
					String temp = s2 + s7 + s17;
					if (!hasDuplicates(temp) &&
							multiples.get(1).contains(temp.substring(1, 4)) &&
							multiples.get(2).contains(temp.substring(2, 5)) &&
							multiples.get(4).contains(temp.substring(4, 7)) &&
							multiples.get(5).contains(temp.substring(5, 8))) {
						answers.add(appendDigit(temp));
					}
				}
			}
		}

		long sum = 0;

		for (String answer : answers) {
			sum += Long.parseLong(answer);
		}

		return String.valueOf(sum);
	}

	private static String appendDigit(String canidate) {
		for (int i = 0; i <= 9; i++) {
			if (!canidate.contains(String.valueOf(i))) {
				return String.valueOf(i) + canidate;
			}
		}
		return null;
	}

	private static boolean hasDuplicates(String... nums) {
		List<Character> digits = new ArrayList<Character>();

		for (String num : nums) {
			for (char digit : num.toCharArray()) {
				digits.add(digit);
			}
		}

		Set<Character> temp = new HashSet<Character>();
		temp.addAll(digits);

		return temp.size() != digits.size() ? true : false;
	}
}

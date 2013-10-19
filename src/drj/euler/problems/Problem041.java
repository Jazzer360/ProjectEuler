package drj.euler.problems;

import java.util.ArrayList;

import drj.euler.Utility;

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
 * also prime.
 * 
 * What is the largest n-digit pandigital prime that exists?
 */
public class Problem041 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		ArrayList<Integer> digits = new ArrayList<Integer>();

		for (int i = 1; i <= 9; i++) {
			digits.add(i);
		}

		int answer = 0;
		boolean answerFound = false;

		for (int i = 9; i > 1; i--) {
			for (int j = Utility.factorial(i).intValue(); j > 0; j--) {
				long permutation = Utility.getPermutation(digits, j);
				if (Utility.isPrime(permutation)) {
					answer = (int) permutation;
					answerFound = true;
					break;
				}
			}
			if (answerFound) {
				break;
			}
			digits.remove(i - 1);
		}

		System.out.println(answer);
		System.out.println(t.toDecimalString());
	}
}

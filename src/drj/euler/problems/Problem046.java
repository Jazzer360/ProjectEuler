package drj.euler.problems;

import drj.euler.Utility;

/**
 * It was proposed by Christian Goldbach that every odd composite number can be
 * written as the sum of a prime and twice a square.
 * 
 * 9 = 7 + 2x1^2
 * 15 = 7 + 2x2^2
 * 21 = 3 + 2x3^2
 * 25 = 7 + 2x3^2
 * 27 = 19 + 2x2^2
 * 33 = 31 + 2x1^2
 *
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a
 * prime and twice a square?
 */
public class Problem046 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		boolean answerFound = false;
		int answer = 0;

		for (int i = 3; !answerFound; i += 2) {
			if (!Utility.isPrime(i)) {
				answerFound = true;
				answer = i;
				for (int j = 1; 2 * j * j < i; j++) {
					if (Utility.isPrime(i - (2 * j * j))) {
						answerFound = false;
					}
				}
			}
		}

		System.out.println(answer);
		System.out.println(t.toDecimalString());
	}
}

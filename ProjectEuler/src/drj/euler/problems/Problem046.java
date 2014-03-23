package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * It was proposed by Christian Goldbach that every odd composite number can be
 * written as the sum of a prime and twice a square.
 * 
 * <p>9 = 7 + 2x1^2
 * <br>15 = 7 + 2x2^2
 * <br>21 = 3 + 2x3^2
 * <br>25 = 7 + 2x3^2
 * <br>27 = 19 + 2x2^2
 * <br>33 = 31 + 2x1^2
 *
 * <p>It turns out that the conjecture was false.
 *
 * <p>What is the smallest odd composite that cannot be written as the sum of a
 * prime and twice a square?
 */
@Answer("5777")
public class Problem046 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem046();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
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

		return String.valueOf(answer);
	}
}

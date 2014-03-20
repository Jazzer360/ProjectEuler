package drj.euler.problems;

import drj.euler.Problem;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from
 * 1 to 10 without any remainder.
 * 
 * What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 */
public class Problem005 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem005();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int seeking = 20;
		int answer = 0;
		boolean answerFound = false;

		for (int i = seeking * 18; !answerFound; i += seeking) {
			for (int j = seeking - 1; j > 1; j--) {
				if (i % j != 0) {
					break;
				} else if (j == 2) {
					answer = i;
					answerFound = true;
				}
			}
		}

		return String.valueOf(answer);
	}
}

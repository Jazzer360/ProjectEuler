package drj.euler.problems;

import drj.euler.Utility;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from
 * 1 to 10 without any remainder.
 * 
 * What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 */
public class Problem005 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

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

		System.out.println(answer);
		System.out.println(t.toDecimalString());
	}
}

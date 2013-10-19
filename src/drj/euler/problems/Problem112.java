package drj.euler.problems;

import drj.euler.Utility;

/**
 * Working from left-to-right if no digit is exceeded by the digit to its left
 * it is called an increasing number; for example, 134468.
 * 
 * Similarly if no digit is exceeded by the digit to its right it is called a
 * decreasing number; for example, 66420.
 * 
 * We shall call a positive integer that is neither increasing nor decreasing a
 * "bouncy" number; for example, 155349.
 * 
 * Clearly there cannot be any bouncy numbers below one-hundred, but just over
 * half of the numbers below one-thousand (525) are bouncy. In fact, the least
 * number for which the proportion of bouncy numbers first reaches 50% is 538.
 * 
 * Surprisingly, bouncy numbers become more and more common and by the time we
 * reach 21780 the proportion of bouncy numbers is equal to 90%.
 * 
 * Find the least number for which the proportion of bouncy numbers is exactly
 * 99%.
 */
public class Problem112 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int bouncyCount = 1;
		int num = 101;

		while (!(num % 100 == 0 && bouncyCount == (num / 100) * 99)) {
			num++;
			if (isBouncy(num)) bouncyCount++;
		}

		System.out.println(num + ", " + bouncyCount);
		System.out.println(t.toDecimalString());
	}

	private static boolean isBouncy(long num) {
		int increasing = 9;
		int decreasing = 0;

		boolean isIncreasing = true;
		boolean isDecreasing = true;

		while (num > 0) {
			int digit = (int) (num % 10);

			if (digit > increasing) {
				isIncreasing = false;
			} else {
				increasing = digit;
			}

			if (digit < decreasing) {
				isDecreasing = false;
			} else {
				decreasing = digit;
			}

			if (!isIncreasing && !isDecreasing) {
				return true;
			}

			num /= 10;
		}

		return false;
	}
}

package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * Working from left-to-right if no digit is exceeded by the digit to its left
 * it is called an increasing number; for example, 134468.
 * 
 * <p>Similarly if no digit is exceeded by the digit to its right it is called
 * a decreasing number; for example, 66420.
 * 
 * <p>We shall call a positive integer that is neither increasing nor decreasing
 * a "bouncy" number; for example, 155349.
 * 
 * <p>Clearly there cannot be any bouncy numbers below one-hundred, but just
 * over half of the numbers below one-thousand (525) are bouncy. In fact, the
 * least number for which the proportion of bouncy numbers first reaches 50% is
 * 538.
 * 
 * <p>Surprisingly, bouncy numbers become more and more common and by the time
 * we reach 21780 the proportion of bouncy numbers is equal to 90%.
 * 
 * <p>Find the least number for which the proportion of bouncy numbers is
 * exactly 99%.
 */
@Answer("1587000")
public class Problem112 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem112();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int bouncyCount = 1;
		int num = 101;

		while (!(num % 100 == 0 && bouncyCount == (num / 100) * 99)) {
			num++;
			if (isBouncy(num)) bouncyCount++;
		}

		return String.valueOf(num);
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

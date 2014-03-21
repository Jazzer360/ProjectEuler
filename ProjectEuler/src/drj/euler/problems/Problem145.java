package drj.euler.problems;

import java.util.concurrent.atomic.AtomicInteger;

import drj.euler.Answer;
import drj.euler.AsyncWorker;
import drj.euler.Problem;
import drj.euler.Utility.Range;

/**
 * Some positive integers n have the property that the sum [ n + reverse(n) ]
 * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and
 * 409 + 904 = 1313. We will call such numbers reversible; so 36, 63, 409, and
 * 904 are reversible. Leading zeroes are not allowed in either n or reverse(n).
 * 
 * There are 120 reversible numbers below one-thousand.
 * 
 * How many reversible numbers are there below one-billion (10^9)?
 */
@Answer("608720")
public class Problem145 extends Problem {

	public static void main(String[] args) throws InterruptedException {
		Problem p = new Problem145();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		final AtomicInteger count = new AtomicInteger();
		AsyncWorker<Range, Void> computer = new AsyncWorker<>(
				in -> {
					for (int i = (int) in.from; i <= in.to; i++) {
						if (isReversible(i)) count.getAndIncrement();
					}
					return null;
				});
		int cores = Runtime.getRuntime().availableProcessors();
		try {
			for (Range range : new Range(0, 999_999_999).split(cores)) {
				computer.submit(range);
			}
			computer.finish();
		} catch (InterruptedException e) {}

		return String.valueOf(count);
	}

	private static int reverse(int num) {
		int reverse = 0;

		while (num > 0) {
			reverse *= 10;
			reverse += num % 10;
			num /= 10;
		}

		return reverse;
	}

	private static boolean isReversible(int num) {
		if (num % 10 == 0)
			return false;
		int sum = num;
		sum += reverse(num);

		while (sum > 0) {
			if (sum % 10 % 2 == 0) {
				return false;
			}
			sum /= 10;
		}

		return true;
	}
}

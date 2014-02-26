package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import drj.euler.Utility;

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
public class Problem145 {

	private static final long THRESHOLD = 1_000_000_000;

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService exec = Executors.newFixedThreadPool(cores);

		List<Future<Integer>> futures = new ArrayList<>();

		for (int i = 0; i < cores; i++) {
			long start = THRESHOLD * i / cores;
			long end = THRESHOLD * (i + 1) / cores - 1;

			futures.add(exec.submit(new ReversibleChecker(
					(int) start, (int) end)));
		}
		exec.shutdown();

		int count = 0;

		for (Future<Integer> future : futures) {
			try {
				count += future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println(count);
		System.out.println(t.toDecimalString());
	}

	private static class ReversibleChecker implements Callable<Integer> {

		private int start;
		private int last;

		public ReversibleChecker(int startNum, int lastNum) {
			start = startNum;
			last = lastNum;
		}

		@Override
		public Integer call() throws Exception {
			int count = 0;
			for (int i = start; i <= last; i++) {
				if (isReversible(i)) count++;
			}
			return count;
		}
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

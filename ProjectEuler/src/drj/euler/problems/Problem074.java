package drj.euler.problems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import drj.euler.Answer;
import drj.euler.AsyncWorker;
import drj.euler.Problem;

/**
 * The number 145 is well known for the property that the sum of the factorial
 * of its digits is equal to 145:
 * 
 * 		1! + 4! + 5! = 1 + 24 + 120 = 145
 * 
 * Perhaps less well known is 169, in that it produces the longest chain of
 * numbers that link back to 169; it turns out that there are only three such
 * loops that exist:
 * 
 * 		169 -> 363601 -> 1454 -> 169
 * 		871 -> 45361 -> 871
 * 		872 -> 45362 -> 872
 * 
 * It is not difficult to prove that EVERY starting number will eventually get
 * stuck in a loop. For example,
 * 
 * 		69 -> 363600 -> 1454 -> 169 -> 363601 (-> 1454)
 * 		78 -> 45360 -> 871 -> 45361 (-> 871)
 * 		540 -> 145 (-> 145)
 * 
 * Starting with 69 produces a chain of five non-repeating terms, but the
 * longest non-repeating chain with a starting number below one million is
 * sixty terms.
 * 
 * How many chains, with a starting number below one million, contain exactly
 * sixty non-repeating terms?
 */
@Answer("402")
public class Problem074 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem074();
		System.out.println(p);
	}

	private static final int[] FACTORIALS =
		{1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880};

	private Map<Long, Integer> loopSizes;

	@Override
	protected String onSolve() {
		loopSizes = new ConcurrentHashMap<Long, Integer>();

		loopSizes.put(169L, 3);
		loopSizes.put(363601L, 3);
		loopSizes.put(1454L, 3);
		loopSizes.put(871L, 2);
		loopSizes.put(45361L, 2);
		loopSizes.put(872L, 2);
		loopSizes.put(45362L, 2);
		loopSizes.put(145L, 1);
		loopSizes.put(40585L, 1);
		loopSizes.put(1L, 1);
		loopSizes.put(2L, 1);

		final AtomicInteger count = new AtomicInteger();

		AsyncWorker<Integer, Void> computer = new AsyncWorker<>(
				in -> {
					if (termsInLoop(in) == 60) count.getAndIncrement();
					return null;
				});

		try {
			for (int i = 1; i < 1_000_000; i++) {
				computer.submit(i);
			}
			computer.finish();
		} catch (InterruptedException e) {}

		return String.valueOf(count.get());
	}

	private int termsInLoop(long n) {
		if (loopSizes.containsKey(n)) {
			return loopSizes.get(n);
		} else {
			int size = 1 + termsInLoop(nextTerm(n));
			loopSizes.put(n, size);
			return size;
		}
	}

	private static long nextTerm(long n) {
		long term = 0;
		while (n > 0) {
			term += FACTORIALS[(int) (n % 10)];
			n /= 10;
		}
		return term;
	}
}

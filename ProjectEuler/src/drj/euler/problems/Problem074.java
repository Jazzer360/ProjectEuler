package drj.euler.problems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import drj.euler.AsyncWorker;
import drj.euler.Utility;

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
public class Problem074 {

	private static Map<Long, Integer> loopSizes = new ConcurrentHashMap<>();

	public static void main(String[] args) throws InterruptedException {
		Utility.Timer t = new Utility.Timer();
		t.start();

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

		for (int i = 1; i < 1_000_000; i++) {
			computer.submit(i);
		}
		computer.finish();

		System.out.println(count.get());
		System.out.println(t.toDecimalString());
	}

	private static int termsInLoop(long n) {
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
			term += Utility.factorial((int) (n % 10)).intValue();
			n /= 10;
		}
		return term;
	}
}

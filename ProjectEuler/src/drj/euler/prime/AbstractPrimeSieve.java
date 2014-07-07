package drj.euler.prime;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementations must provide backing data structure to hold the prime status
 * of positive odd integers. Since this abstraction is designed to be thread
 * safe, subclasses MUST ensure that calls to setNotPrime
 */
abstract class AbstractPrimeSieve extends PrimeService {

	private static class DaemonThreadFactory implements ThreadFactory {
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			return thread;
		}
	}

	private static final int CORES = Runtime.getRuntime().availableProcessors();

	protected AtomicLong sievedTo = new AtomicLong();
	private Lock sieveLock = new ReentrantLock();
	private CountDownLatch latch;
	private ExecutorService queryService = Executors
			.newCachedThreadPool(new DaemonThreadFactory());
	private ExecutorService sieveService = Executors.newFixedThreadPool(
			CORES + 1, new DaemonThreadFactory());

	/**
	 * Subclasses must implement this method to check the backing data to see if
	 * the submitted number is prime.
	 * 
	 * @param num
	 *            number to check if prime
	 * @return true if the submitted number is prime
	 */
	protected abstract boolean checkPrime(long num);

	/**
	 * Returns the number of odd numbers to check for primality on each sieving
	 * pass. This number is exactly how many additional data points are required
	 * for each sieve pass.
	 * 
	 * @return the amount of odd numbers to be sieved on the next sieve pass
	 */
	protected abstract long getSieveIncrement();

	/**
	 * Subclasses must handle any preparation required for the sieve process in
	 * this method. Such examples would be expanding the sieve to accomodate the
	 * additional number of data points the sieve will need. The number passed
	 * is the number of additional data points this sieve requires on the next
	 * sieve pass.
	 * 
	 * @param space
	 *            number of additional data points the sieve must accomodate
	 */
	protected abstract void onSieveStart(long space);

	/**
	 * If a subclass needs to know how large the sieve is after each sieve pass
	 * this method may be overridden to handle any internal changes after each
	 * sieve pass completes. The number passed is the largest number that the
	 * sieve has verified as prime or not prime and will always be odd.
	 * 
	 * @param sievedTo
	 *            the number to which the sieve is sieved to
	 */
	protected void onSieveComplete(long sievedTo) {
	}

	/**
	 * Subclasses must implement this method to change the backing data of the
	 * sieve. In addition, the implementation must call
	 * {@code latch.countDown()} on the latch for things to function properly.
	 * Failing to do so will permanently block any threads querying for primes
	 * that have not yet been sieved.
	 * 
	 * @param latch
	 *            a latch which must be counted down after the backing data has
	 *            been updated
	 * @param nums
	 *            non-prime numbers which we must set as non-prime in the
	 *            backing data
	 */
	protected abstract void setNotPrime(CountDownLatch latch, long... nums);

	private void sieve() {
		long sievedTo = this.sievedTo.get();
		long increment = Math.min(getSieveIncrement(), sievedTo);
		onSieveStart(increment);
		increment *= 2;
		long sieveTarget = increment + sievedTo;
		long maxSieve = sieveTarget / 3;

		Collection<Runnable> runnables = new ConcurrentLinkedQueue<Runnable>();

		class SieveTask implements Runnable {
			private long prime;

			private SieveTask(long prime) {
				this.prime = prime;
			}

			@Override
			public void run() {
				long step = prime * 2;
				long firstMultiple = sievedTo / prime + 1;
				if (firstMultiple % 2 == 0)
					firstMultiple++;
				firstMultiple *= prime;
				long multipleCount = (long) (Math
						.floor((sieveTarget - firstMultiple) / (double) step) + 1);
				long[] multiples = new long[(int) multipleCount];
				for (int i = 0; i < multipleCount; i++) {
					multiples[i] = firstMultiple + step * i;
				}
				setNotPrime(latch, multiples);
			}
		}

		for (long num = 3; num <= maxSieve; num += 2) {
			if (isPrime(num))
				runnables.add(new SieveTask(num));
		}

		latch = new CountDownLatch(runnables.size());

		for (Runnable runnable : runnables) {
			sieveService.execute(runnable);
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new CompletionException(e);
		}

		this.sievedTo.set(sieveTarget);
		onSieveComplete(this.sievedTo.get());
	}

	@Override
	public boolean isPrime(long num) {
		if (num % 2 == 0)
			return num == 2 ? true : false;
		if (num < 2 || num > MAX_PRIME)
			return false;
		if (num > sievedTo.get()) {
			sieveLock.lock();
			if (num <= sievedTo.get()) {
				sieveLock.unlock();
				return isPrime(num);
			}
			sieve();
			sieveLock.unlock();
			return isPrime(num);
		}

		Future<Boolean> prime = queryService.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return checkPrime(num);
			}
		});

		try {
			return prime.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new CompletionException(e);
		}
	}
}

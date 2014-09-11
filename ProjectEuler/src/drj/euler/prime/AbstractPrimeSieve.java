package drj.euler.prime;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionException;
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
 * of positive odd integers.
 */
abstract class AbstractPrimeSieve extends PrimeService {

	private static final int CORES = Runtime.getRuntime().availableProcessors();
	private static final ThreadFactory DAEMON_FACTORY = new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			return thread;
		}
	};

	protected AtomicLong sievedTo;
	private Lock sieveLock = new ReentrantLock();
	private ExecutorService queryService = Executors
			.newCachedThreadPool(DAEMON_FACTORY);
	private ExecutorService sieveService = Executors.newFixedThreadPool(
			CORES + 1, DAEMON_FACTORY);

	/**
	 * 
	 * @param initSievedTo
	 */
	protected AbstractPrimeSieve(long initSievedTo) {
		sievedTo = new AtomicLong(initSievedTo);
	}

	/**
	 * Subclasses must implement this method to check the backing data to see if
	 * the submitted number is prime.
	 * 
	 * @param num
	 *            number to check if prime
	 * @return true if the submitted number is prime
	 */
	protected abstract boolean isSievedPrime(long num);

	/**
	 * Returns the number of odd numbers to check for primality on each sieving
	 * pass. This number is exactly how many additional data points are required
	 * for each sieve pass.
	 * 
	 * @return the amount of odd numbers to be sieved on the next sieve pass
	 */
	protected abstract long getSieveAmount();

	public long getSievedTo() {
		return sievedTo.get();
	}

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
	protected abstract void onSieveComplete(long sievedTo);

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
	protected abstract void setNotPrime(long num);

	private void doSieve() {
		long sievedTo = getSievedTo();
		long increment = Math.min(getSieveAmount(), sievedTo);
		onSieveStart(increment);
		increment *= 2;
		long sieveTarget = increment + sievedTo;
		long maxPrimeMultiple = sieveTarget / 3;

		for (long num = 3; num <= maxPrimeMultiple; num += 2) {
			if (isPrime(num)) doFilterMultiples(num, getSievedTo() + 2,
					sieveTarget);
		}

		this.sievedTo.set(sieveTarget);
		onSieveComplete(getSievedTo());
	}

	private void doFilterMultiples(long prime, long start, long end) {
		long step = prime * 2;
		long multiple = start / prime;
		if (multiple % 2 == 0) multiple++;
		multiple *= prime;
		while (multiple <= end) {
			setNotPrime(multiple);
			multiple += step;
		}
	}

	@Override
	public boolean isPrime(long num) {
		if (num < 2 || num > MAX_PRIME) return false;
		if (num % 2 == 0) return num == 2 ? true : false;
		if (num > getSievedTo()) {
			sieveLock.lock();
			if (num <= getSievedTo()) {
				sieveLock.unlock();
				return isPrime(num);
			}
			doSieve();
			sieveLock.unlock();
			return isPrime(num);
		}

		Future<Boolean> prime = queryService.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return isSievedPrime(num);
			}
		});

		try {
			return prime.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new CompletionException(e);
		}
	}
}

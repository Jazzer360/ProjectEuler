package drj.euler.prime;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

abstract class AbstractPrimeSieve extends PrimeService {

	/*
	 * TODO Fields Needed
	 * 
	 * Type: ExecutorService
	 * An executor that can appropriately handle each submitted request to
	 * isPrime(long). This should gracefully handle a swarm of requests to
	 * isPrime(long) while waiting for calculation to complete.
	 *   Considerations:
	 *     Requests sent to this executor should complete quickly IF the prime
	 *       status is computed for the submitted input.
	 *     If the sieve is not filled up to the number submitted, how should
	 *       isPrime(long) behave?
	 */
	private ExecutorService queryService = Executors.newCachedThreadPool();
	private Object sieveLock = new Object();
	
	private AtomicLong sievedTo = new AtomicLong();;

	/**
	 * TODO
	 * 
	 * @param num
	 * @param prime
	 */
	protected abstract void setPrime(long num, boolean prime);

	/**
	 * TODO
	 * 
	 * Need: sievedTo
	 * 
	 * Is the number
	 * 
	 * @param num
	 * @return
	 */
	protected abstract boolean checkPrime(long num);

	public final void sieveTo(long num) {
		/*
		 * TODO
		 * 
		 * Concurrency: Should be okay if we sieve, then change sievedTo, but
		 * we need to be able to concurrently handle requests to isPrime(long)
		 * when the sieve hasn't sorted there yet.
		 * 
		 * Exploitable Parallellism: We can accumulate which bits to set in
		 * parallell. However, if we are to do any I/O operation, like file
		 * writing, we must do those serially. Maybe we change the
		 * setPrime(long, boolean) method to something like setNotPrime(long...)
		 * This would allow us to specify in our implementation, a way to take
		 * advantage of a batch of changes to the underlying sieve
		 * implementation.
		 * 
		 * Condition: Is the sieve already sorted to the given number?
		 * 
		 * Need: Number last sorted to (so we know where to begin the
		 * sorting)
		 * 
		 * Algorithm: 
		 * 
		 * Iterate through each prime found so far
		 * ?is num / 2 okay with the truncation?
		 * 
		 * cases: 14, 15, 16
		 * for (long prime = 3; prime <= num / 2; prime = * nextPrime(prime))
		 *   case: 14 (prime <= 7)
		 *   case: 15 (prime <= 7)
		 *   case: 16 (prime <= 8)
		 *   
		 * Each prime we iterate through, we start another
		 * iteration of each (multiple) of the prime we're looking at, up to
		 * (num) and setPrime(multiple, false).
		 * case: multiple > Long.MAX_VALUE / 2
		 * 
		 *   for(long multiple = sievedTo; multiple < num / 2; multiple += prime)
		 *     setPrime(multiple, false);
		 *     case: 14 (multiple < 7)
		 *     case: 15 (multiple < 7)
		 *     case: 16 (multiple < 8)
		 */
	}

	@Override
	public boolean isPrime(long num) {
		/*
		 * TODO
		 * 
		 * Take the passed number and pass it off to the executor service that
		 * handles queries to isPrime(long).
		 * 
		 * If the number is not yet sieved to, request that more sieving be done
		 * (how much?). While sieving is being done, park the request until the
		 * sieve has finished up to the next point. Maybe store the requests as
		 * callables that can be dumped to the query service with a call to
		 * invokeAll(Collection)
		 * 
		 * Consideration:
		 * How to determine the amount of sieving be done?
		 * Make a reasonable guess, but leave option up to user?
		 * Can a unit of time be used? Probably not when the ever-increasing
		 * iteration costs increase.
		 * We know that the average gap between primes increases the bigger the
		 * primes get. Should this factor into determining how much sieving we
		 * do?
		 * We know that we must at least sieve to the number requested, but
		 * should we do more?
		 * If a user is calling nextPrime(long) in a loop, on top of using the
		 * default nextPrime(long) implementation, then we are basically calling
		 * isPrime(long) repeatedly, which may stack up a long queue of requests
		 * to isPrime(long).
		 * What EXACTLY occurs during sieving?
		 * Can we make the sieving sufficiently cheap enough and smart enough to
		 * not do any redundant marking of non-primes, so that we don't have to
		 * worry so much about the static cost of sieving the numbers?
		 * We will ALWAYS need to walk through the prime numbers when sieving,
		 * so that is a cost that we cannot mitigate any further.
		 */
		Future<Boolean> prime = queryService.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});
		try {
			return prime.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return false;
	}
}

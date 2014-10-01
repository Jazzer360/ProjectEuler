package drj.euler.prime;

/**
 * A service that handles various prime-related requests.
 */
public abstract class PrimeService implements PrimeChecker {

	/**
	 * Returns the first prime number greater than the submitted number.
	 * 
	 * @param num
	 *            the number to start the search for the next prime from
	 * @return the first prime number greater than the number submitted
	 */
	public long getNextPrime(long num) {
		if (num >= MAX_PRIME)
			throw new IllegalArgumentException("No primes greater than " + num
					+ " that fit in a long");
		if (num < 2)
			return 2;
		num += num % 2 == 0 ? 1 : 2;

		while (!isPrime(num)) {
			num += 2;
		}
		return num;
	}
}

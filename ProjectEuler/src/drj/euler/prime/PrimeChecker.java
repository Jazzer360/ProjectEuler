package drj.euler.prime;

/**
 * An object which can check if a number passed to it is prime.
 */
public interface PrimeChecker {

	/**
	 * Maximum prime number that can fit in a long.
	 */
	public static final long MAX_PRIME = 9223372036854775783L;

	/**
	 * Returns true if the number submitted is prime.
	 * 
	 * @param num
	 *            number to check
	 * @return true if prime
	 */
	public boolean isPrime(long num);
}

package drj.euler.prime;

public class PrimeServices {

	private PrimeServices() {
	}

	/**
	 * Gets a very basic prime checker for checking individual prime numbers.
	 * 
	 * <p>
	 * All the calculations are done independent of each number submitted. There
	 * is no state associated with this implementation.
	 * 
	 * <p>
	 * Useful when checking smaller numbers, or when checking smaller amounts of
	 * larger numbers
	 * 
	 * @return a simple PrimeService for checking primes
	 */
	public static PrimeService basicPrimeChecker() {
		return new PrimeService() {
			@Override
			public boolean isPrime(long num) {
				if (num < 2)
					return false;
				else if (num < 4)
					return true;
				else if (num % 2 == 0 || num % 3 == 0)
					return false;
				else if (num < 9)
					return true;
				else if (num > MAX_PRIME)
					return false;
				for (long i = 5, lim = (long) Math.sqrt(num); i <= lim; i += 6) {
					if (num % i == 0 || num % (i + 2) == 0)
						return false;
				}
				return true;
			}

			@Override
			public long nextPrime(long num) {
				if (num >= MAX_PRIME)
					throw new IllegalArgumentException(
							"No primes greater than " + num
									+ " that fit in a long");

				if (num % 2 == 0)
					num++;

				while (!isPrime(num)) {
					num += 2;
				}

				return num;
			}
		};
	}

	public static PrimeService sieveOnStorage() {

	}

	public static PrimeService sieveInMemory() {

	}
}

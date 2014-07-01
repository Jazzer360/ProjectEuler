package drj.euler.prime;

public interface PrimeService extends PrimeChecker {

	public static final long MAX_PRIME = 9223372036854775783L;

	public long nextPrime(long num);
}

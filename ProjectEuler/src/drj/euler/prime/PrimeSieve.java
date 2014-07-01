package drj.euler.prime;

abstract class PrimeSieve implements PrimeChecker {
	
	private long sievedTo;
	
	protected abstract void setPrime(long num, boolean prime);
	
	
	public final void sieveTo(long num) {
		
	}
}

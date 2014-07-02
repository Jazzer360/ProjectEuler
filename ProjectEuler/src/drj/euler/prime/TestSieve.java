package drj.euler.prime;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import drj.euler.Utility;

public class TestSieve extends AbstractPrimeSieve {

	public static void main(String[] args) {
		PrimeService sieve = new TestSieve();
		
		long prime = 0;
		for (int i = 0; i < 1000; i++) {
			if (sieve.isPrime(i) ^ Utility.isPrime(i))
				System.out.println(i);
		}
	}
	
	List<Boolean> sieve = new Vector<>();
	
	public TestSieve() {
		sieve.add(false);
		sieve.add(true);
		sieve.add(true);
		sievedTo.set(5);
	}
	
	@Override
	protected boolean checkPrime(long num) {
		return sieve.get((int) (num / 2));
	}

	@Override
	protected long getSieveIncrement() {
		return 10000;
	}

	@Override
	protected void onSieveStart(long space) {
		for (int i = 0; i < space; i++) {
			sieve.add(true);
		}
	}

	@Override
	protected void setNotPrime(CountDownLatch latch, long... nums) {
		for (long num : nums) {
			sieve.set((int) (num / 2), false);
		}
		latch.countDown();
	}
}

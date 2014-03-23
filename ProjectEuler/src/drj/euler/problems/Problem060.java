package drj.euler.problems;

import java.util.TreeMap;
import java.util.TreeSet;

import drj.euler.Problem;
import drj.euler.Utility;
import drj.euler.Utility.PrimeSieve;

/**
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two
 * primes and concatenating them in any order the result will always be prime.
 * For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of
 * these four primes, 792, represents the lowest sum for a set of four primes
 * with this property.
 * 
 * <p>Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 */
public class Problem060 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem060();
		System.out.println(p);
	}

	private static final PrimeSieve s = new PrimeSieve();
	private static final TreeMap<Integer, TreeSet<Integer>>	concatables =
			(new TreeMap<>());

	@Override
	protected String onSolve() {
		long prime = s.nextPrime(30);

		while (prime < 10_000_000) {
			splitPrime(prime);
			prime = s.nextPrime(prime);
		}

		return getPrimeSet(4, 3, null).toString();
	}

	private static void splitPrime(long prime) {
		int splitPoint = 10;
		while (prime / splitPoint > 0) {
			int n1 = (int) (prime / splitPoint);
			int n2 = (int) (prime % splitPoint);
			int reverse = n1 + n2 * (int) Math.pow(10, Utility.numDigits(n1));
			if (s.isPrime(n1) && s.isPrime(n2) && s.isPrime(reverse)
					&& n1 != 2 && n1 != 5) {
				if (concatables.containsKey(n1)) {
					TreeSet<Integer> set = concatables.get(n1);
					set.add(n2);
				} else {
					TreeSet<Integer> set = new TreeSet<>();
					set.add(n2);
					concatables.put(n1, set);
				}
			}
			while (n1 % 10 == 0) {
				splitPoint *= 10;
				n1 /= 10;
			}
			splitPoint *= 10;
		}
	}

	private static TreeSet<Integer> getPrimeSet(int setSize, int startPrime,
			TreeSet<Integer> primeSet) {
		if (primeSet == null) {
			primeSet = new TreeSet<>();
			primeSet.add(startPrime);
			return getPrimeSet(setSize, startPrime, primeSet);
		}

		if (primeSet.size() == setSize) {
			return primeSet;
		}

		Integer next = concatables.get(primeSet.first())
				.ceiling(primeSet.last() + 1);

		while (true) {
			if (next == null) {
				Integer root = primeSet.first();
				Integer last = primeSet.last();
				if (root == last) {
					Integer newRoot = concatables.ceilingKey(root + 1);
					primeSet.clear();
					primeSet.add(newRoot);
					return getPrimeSet(setSize, startPrime, primeSet);
				} else {
					next = concatables.get(root).ceiling(last + 1);
					primeSet.remove(last);
					continue;
				}
			}

			boolean nextIsValid = true;
			for (Integer prime : primeSet) {
				if (!(concatables.get(prime).contains(next) &&
						concatables.get(next).contains(prime))) {
					next = concatables.get(primeSet.first())
							.ceiling(next + 1);
					nextIsValid = false;
					break;
				}
			}

			if (nextIsValid) {
				primeSet.add(next);
				return getPrimeSet(setSize, startPrime, primeSet);
			}
		}
	}
}

package drj.euler.problems;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import drj.euler.Utility;
import drj.euler.Utility.PrimeSieve;

/**
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two
 * primes and concatenating them in any order the result will always be prime.
 * For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of
 * these four primes, 792, represents the lowest sum for a set of four primes
 * with this property.
 * 
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 */
public class Problem060 {

	private static final PrimeSieve s = new PrimeSieve();
	private static final Map<Integer, TreeSet<Integer>>	concatables =
			new TreeMap<Integer, TreeSet<Integer>>();
	@SuppressWarnings("unused")
	private static final Set<TreeSet<Integer>> sets = new HashSet<>();

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		long prime = s.nextPrime(30);

		while (prime < 1_000_000) {
			splitPrime(prime);
			prime = s.nextPrime(prime);
		}

		for (Integer key : concatables.keySet()) {
			System.out.print(key);
			System.out.println(concatables.get(key));
		}

		System.out.println(concatables);
		System.out.println(t.toDecimalString());
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
					//set.add(n1);
					set.add(n2);
					concatables.put(n1, set);
				}
			}
			splitPoint *= 10;
		}
	}
}

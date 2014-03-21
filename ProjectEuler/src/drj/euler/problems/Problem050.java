package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The prime 41, can be written as the sum of six consecutive primes:
 * 
 * 		41 = 2 + 3 + 5 + 7 + 11 + 13
 * 
 * This is the longest sum of consecutive primes that adds to a prime below
 * one-hundred.
 * 
 * The longest sum of consecutive primes below one-thousand that adds to a
 * prime, contains 21 terms, and is equal to 953.
 * 
 * Which prime, below one-million, can be written as the sum of the most
 * consecutive primes?
 */
@Answer("997651")
public class Problem050 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem050();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		List<Integer> primes = new ArrayList<Integer>();

		primes.add(2);
		for (int i = 3; i < 1_000_000; i += 2) {
			if (Utility.isPrime(i)) {
				primes.add(i);
			}
		}

		int maxSum = 1;

		for (int i = 0, startIndex = 0, sum = 0, terms = 0, maxTerms = 0
				; i < primes.size(); i++) {
			sum += primes.get(i);
			terms++;
			if (sum > 999_999) {
				i = ++startIndex;
				sum = 0;
				terms = 0;
			} else if (terms > maxTerms && Utility.isPrime(sum)) {
				maxTerms = terms;
				maxSum = sum;
			}
		}

		return String.valueOf(maxSum);
	}
}

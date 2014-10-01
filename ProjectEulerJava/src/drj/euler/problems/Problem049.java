package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
 * increases by 3330, is unusual in two ways: (i) each of the three terms are
 * prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * 
 * <p>There are no arithmetic sequences made up of three 1-, 2-, or 3-digit
 * primes, exhibiting this property, but there is one other 4-digit increasing
 * sequence.
 * 
 * <p>What 12-digit number do you form by concatenating the three terms in this
 * sequence?
 */
@Answer("296962999629")
public class Problem049 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem049();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		StringBuilder answer = new StringBuilder();

		for (int i = 1001; i < 10000 - 6660; i += 2) {
			if (Utility.isPrime(i)
					&& Utility.isPrime(i + 3330)
					&& Utility.isPrime(i + 6660)
					&& Utility.isPermutation(i, i + 3330)
					&& Utility.isPermutation(i, i + 6660)
					&& i != 1487) {
				answer.append(i);
				answer.append(i + 3330);
				answer.append(i + 6660);
				break;
			}
		}

		return answer.toString();
	}
}

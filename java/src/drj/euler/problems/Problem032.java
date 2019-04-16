package drj.euler.problems;

import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1
 * through 5 pandigital.
 * 
 * <p>The product 7254 is unusual, as the identity, 39 x 186 = 7254, containing
 * multiplicand, multiplier, and product is 1 through 9 pandigital.
 * 
 * <p>Find the sum of all products whose multiplicand/multiplier/product
 * identity can be written as a 1 through 9 pandigital.
 * 
 * <p>HINT: Some products can be obtained in more than one way so be sure to
 * only include it once in your sum.
 */
@Answer("45228")
public class Problem032 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem032();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int sum = 0;

		for (int i = 1; i < 100_000; i++) {
			if (isPandigitalProduct(i)) {
				sum += i;
			}
		}

		return String.valueOf(sum);
	}

	private static boolean isPandigitalProduct(int num) {
		List<Integer> factors = Utility.getFactors(num);
		for (int i = 0; i < factors.size() / 2; i++) {
			if (Utility.isPandigital(
					1, 9, num, factors.get(i), num / factors.get(i))) {
				return true;
			}
		}
		return false;
	}
}

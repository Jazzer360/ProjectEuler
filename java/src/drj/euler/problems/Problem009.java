package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which,
 * <p>		a^2 + b^2 = c^2
 * 
 * <p>For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * 
 * <p>There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * <br>Find the product abc.
 */
@Answer("31875000")
public class Problem009 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem009();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		for (int a = 1; a < 1000; a++) {
			for (int b = a + 1; true; b++) {
				if (a + 2 * b + 1 > 1000) {
					break;
				}
				int c = 1000 - a - b;
				if (isPythagoreanTriplet(a,b,c)) {
					return String.valueOf(a * b * c);
				}
			}
		}

		return "not found";
	}

	private static boolean isPythagoreanTriplet(int a, int b, int c) {
		return (a*a) + (b*b) == (c*c) ? true : false;
	}
}


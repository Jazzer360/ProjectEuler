package drj.euler.problems;

import drj.euler.Utility;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which,
 * 		a^2 + b^2 = c^2
 * 
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class Problem009 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		loop:
			for (int a = 1; a < 1000; a++) {
				for (int b = a + 1; true; b++) {
					if (a + b > 1000) {
						break;
					}
					for (int c = b + 1; true; c++) {
						if (a + b + c > 1000) {
							break;
						}
						if (a + b + c == 1000 && isPythagoreanTriplet(a,b,c)) {
							System.out.println(a * b * c);
							break loop;
						}
					}
				}
			}

		System.out.println(t.toDecimalString());
	}

	private static boolean isPythagoreanTriplet(int a, int b, int c) {
		if ((a*a) + (b*b) == (c*c)) {
			return true;
		} else {
			return false;
		}
	}
}


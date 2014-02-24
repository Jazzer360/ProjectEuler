package drj.euler.problems;

import drj.euler.Utility;

/**
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician
 * in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which
 * is correct, is obtained by cancelling the 9s.
 * 
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * 
 * There are exactly four non-trivial examples of this type of fraction, less
 * than one in value, and containing two digits in the numerator and
 * denominator.
 * 
 * If the product of these four fractions is given in its lowest common terms,
 * find the value of the denominator. 
 */
public class Problem033 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int nProduct = 1;
		int dProduct = 1;

		for (int numerator = 10; numerator <= 99; numerator++) {
			for (int denominator = numerator + 1; denominator <= 99
					; denominator++) {
				if (gypsyMathWorks(numerator, denominator)) {
					nProduct *= numerator;
					dProduct *= denominator;
				}
			}
		}
		System.out.println(dProduct / Utility.getGCF(nProduct, dProduct));
		System.out.println(t.toDecimalString());
	}

	private static boolean gypsyMathWorks(int numerator, int denominator) {
		char[] nDigits = String.valueOf(numerator).toCharArray();
		char[] dDigits = String.valueOf(denominator).toCharArray();

		double decimal = (double) numerator / denominator;

		if (nDigits[0] == nDigits[1] && dDigits[0] == dDigits[1]) {
			return false;
		} else if ((double) Utility.charToInt(nDigits[0]) /
				Utility.charToInt(dDigits[1]) == decimal &&
				nDigits[1] == dDigits[0]) {
			return true;
		} else if ((double) Utility.charToInt(nDigits[1]) /
				Utility.charToInt(dDigits[0]) == decimal &&
				nDigits[0] == dDigits[1]) {
			return true;
		} else {
			return false;
		}
	}
}
package drj.euler.problems;

import drj.euler.Utility;

/**
 * If p is the perimeter of a right angle triangle with integral length sides,
 * {a,b,c}, there are exactly three solutions for p = 120.
 * 
 * 		{20,48,52}, {24,45,51}, {30,40,50}
 * 
 * For which value of p <= 1000, is the number of solutions maximized?
 */
public class Problem039 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int pMax = 1000;
		int maxNum = 0, maxPVal = 0;

		for (int p = 1; p <= pMax; p++) {
			if (numberIntegralRightTriangles(p) > maxNum) {
				maxNum = numberIntegralRightTriangles(p);
				maxPVal = p;
			}
		}

		System.out.println(maxPVal);
		System.out.println(t.toDecimalString());
	}

	private static int numberIntegralRightTriangles(int p) {
		int a = 1;
		int b = 2;
		int count = 0;

		while (Math.sqrt((a * a) + (b * b)) + a + b <= p) {
			while (Math.sqrt((a * a) + (b * b)) + a + b <= p) {
				if (Math.sqrt((a * a) + (b * b)) + a + b == p) {
					count++;
				}
				b++;
			}
			a++;
			b = a + 1;
		}

		return count;
	}
}

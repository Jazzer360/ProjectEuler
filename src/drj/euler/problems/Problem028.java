package drj.euler.problems;

import drj.euler.Utility;

/**
 * Starting with the number 1 and moving to the right in a clockwise direction
 * a 5 by 5 spiral is formed as follows:
 * 
 * 				21 22 23 24 25
 * 				20  7  8  9 10
 * 				19  6  1  2 11
 * 				18  5  4  3 12
 * 				17 16 15 14 13
 * 
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * 
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
 * formed in the same way?
 */
public class Problem028 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		int spiralSize = 1001;

		System.out.println(getSumOfDiagonals(spiralSize));
		System.out.println(t.toDecimalString());
	}

	private static long getSumOfDiagonals(long spiralSize) {
		if (spiralSize == 1) {
			return 1;
		}

		int sum = 0;

		for (int i = 0; i < 4; i++) {
			sum += (spiralSize * spiralSize)
					- ((spiralSize - 1) * i);
		}

		sum += getSumOfDiagonals(spiralSize - 2);

		return sum;
	}
}

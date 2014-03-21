package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Starting with 1 and spiraling anti-clockwise in the following way, a square
 * spiral with side length 7 is formed.
 * 
 * 			37 36 35 34 33 32 31
 * 			38 17 16 15 14 13 30
 * 			39 18  5  4  3 12 29
 * 			40 19  6  1  2 11 28
 * 			41 20  7  8  9 10 27
 * 			42 21 22 23 24 25 26
 * 			43 44 45 46 47 48 49
 * 
 * It is interesting to note that the odd squares lie along the bottom right
 * diagonal, but what is more interesting is that 8 out of the 13 numbers lying
 * along both diagonals are prime; that is, a ratio of 8/13  62%.
 * 
 * If one complete new layer is wrapped around the spiral above, a square
 * spiral with side length 9 will be formed. If this process is continued, what
 * is the side length of the square spiral for which the ratio of primes along
 * both diagonals first falls below 10%?
 */
@Answer("26241")
public class Problem058 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem058();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int primeCount = 0;
		int diagonalCount = 1;
		int answer = 0;

		for (int side = 3; true; side += 2) {
			for (int i = 1; i < 4; i++) {
				int digit = side * side - (side - 1) * i;
				if (Utility.isPrime(digit)) {
					primeCount++;
				}
			}
			diagonalCount += 4;

			if ((double) primeCount / diagonalCount < 0.1) {
				answer = side;
				break;
			}
		}

		return String.valueOf(answer);
	}
}

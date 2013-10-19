package drj.euler.problems;

import java.util.Random;

/**
 * Peter has nine four-sided (pyramidal) dice, each with faces numbered:
 * 		1, 2, 3, 4.
 * 
 * Colin has six six-sided (cubic) dice, each with faces numbered:
 * 		1, 2, 3, 4, 5, 6.
 * 
 * Peter and Colin roll their dice and compare totals: the highest total wins.
 * The result is a draw if the totals are equal.
 * 
 * What is the probability that Pyramidal Pete beats Cubic Colin? Give your
 * answer rounded to seven decimal places in the form 0.abcdefg
 */
public class Problem205 {

	public static void main(String[] args) {
		long gamesPlayed = 0;
		long peteWins = 0;

		while (true) {
			if (peteWins()) {
				peteWins++;
			}
			gamesPlayed++;

			if (gamesPlayed % 10_000_000 == 0) {
				System.out.println((double) peteWins / gamesPlayed);
			}
		}
	}

	private static boolean peteWins() {
		Random r = new Random();

		int peteTotal = 0;
		int colinTotal = 0;

		for (int i = 0; i < 9; i++) {
			peteTotal += r.nextInt(4) + 1;
		}
		for (int i = 0; i < 6; i++) {
			colinTotal += r.nextInt(6) + 1;
		}

		return peteTotal > colinTotal ? true : false;
	}
}

package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * In England the currency is made up of pound, £, and pence, p, and there are
 * eight coins in general circulation:
 * 
 * 		1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * 
 * It is possible to make £2 in the following way:
 * 
 * 		1£1 + 150p + 220p + 15p + 12p + 31p
 * 
 * How many different ways can £2 be made using any number of coins? 
 */
@Answer("73682")
public class Problem031 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem031();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
		int target = 200;

		return String.valueOf(combinations(coins, target));
	}

	private static int combinations(int[] coins, int target) {
		return recurseWays(coins, target, coins.length);
	}

	private static int recurseWays(int[] coins, int target, int coinIndex) {
		if (target == 0) {
			return 1;
		}
		if (target < 0 || coinIndex == 0) {
			return 0;
		}
		int combosCurrentIndex =
				recurseWays(coins, target - coins[coinIndex - 1], coinIndex);
		int combosNextIndex =
				recurseWays(coins, target, coinIndex - 1);
		return combosCurrentIndex + combosNextIndex;
	}
}

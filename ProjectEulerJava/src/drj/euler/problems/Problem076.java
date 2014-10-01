package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * It is possible to write five as a sum in exactly six different ways:
 * 
 * <p>		4 + 1
 * <br>		3 + 2
 * <br>		3 + 1 + 1
 * <br>		2 + 2 + 1
 * <br>		2 + 1 + 1 + 1
 * <br>		1 + 1 + 1 + 1 + 1
 * 
 * <p>How many different ways can one hundred be written as a sum of at least
 * two positive integers?
 */
@Answer("190569291")
public class Problem076 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem076();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int[] nums = new int[99];
		for (int i = 0; i < 99; i++) {
			nums[i] = i + 1;
		}
		int target = 100;

		return String.valueOf(combinations(nums, target));
	}

	private static int combinations(int[] nums, int target) {
		return recurseWays(nums, target, nums.length);
	}

	private static int recurseWays(int[] nums, int target, int numIndex) {
		if (target == 0) {
			return 1;
		}
		if (target < 0 || numIndex == 0) {
			return 0;
		}
		int combosCurrentIndex =
				recurseWays(nums, target - nums[numIndex - 1], numIndex);
		int combosNextIndex =
				recurseWays(nums, target, numIndex - 1);
		return combosCurrentIndex + combosNextIndex;
	}
}

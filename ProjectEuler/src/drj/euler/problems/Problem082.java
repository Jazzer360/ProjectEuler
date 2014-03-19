package drj.euler.problems;

import java.util.List;

import drj.euler.Utility;

/**
 * NOTE: This problem is a more challenging version of Problem 81.
 * 
 * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in
 * the left column and finishing in any cell in the right column, and only
 * moving up, down, and right, is indicated in red and bold; the sum is equal
 * to 994.
 * 
 * 			131	673	234	103	18
 * 			201	96	342	965	150
 * 			630	803	746	422	111
 * 			537	699	497	121	956
 * 			805	732	524	37	331
 * 
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target
 * As...'), a 31K text file containing a 80 by 80 matrix, from the left column
 * to the right column.
 */
public class Problem082 {

	private static long[][] nums;
	private static long[][] sums;

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		String filePath = "external data/matrix3.txt";
		List<String> lines = Utility.getFileContents(filePath);

		nums = new long[lines.size()][];
		sums = new long[lines.size()][];

		for (int y = 0; y < lines.size(); y++) {
			String[] values = lines.get(y).split(",");
			nums[y] = new long[values.length];
			sums[y] = new long[values.length];
			for (int x = 0; x < values.length; x++) {
				nums[y][x] = Integer.parseInt(values[x]);
				sums[y][x] = -1;
			}
		}

		long minSum = Long.MAX_VALUE;

		for (int y = 0; y < sums.length; y++) {
			long sum = minSum(sums[y].length - 1, y);
			if (sum < minSum) {
				minSum = sum;
			}
		}

		System.out.println(minSum);
		System.out.println(t.toDecimalString());
	}

	private static long minSum(int x, int y) {
		if (sums[y][x] > -1) {
			return sums[y][x];
		} else if (x == 0) {
			sums[y][x] = nums[y][x];
			return sums[y][x];
		}

		long left = minSum(x - 1, y);
		long above = y > 0 ? minSumAbove(x, y - 1) : Long.MAX_VALUE;
		long below =
				y + 1 < nums.length ? minSumBelow(x, y + 1) : Long.MAX_VALUE;

		sums[y][x] = Math.min(Math.min(left, above), below) + nums[y][x];
		return sums[y][x];
	}

	private static long minSumAbove(int x, int y) {
		if (sums[y][x] > -1) {
			return sums[y][x];
		}

		long left = minSum(x - 1, y);
		long above = y > 0 ? minSumAbove(x, y - 1) : Long.MAX_VALUE;

		return Math.min(left, above) + nums[y][x];
	}

	private static long minSumBelow(int x, int y) {
		if (sums[y][x] > -1) {
			return sums[y][x];
		}

		long left = minSum(x - 1, y);
		long below =
				y + 1 < nums.length ? minSumBelow(x, y + 1) : Long.MAX_VALUE;

		return Math.min(left, below) + nums[y][x];
	}
}

package drj.euler.problems;

import java.util.List;

import drj.euler.Utility;

/**
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the
 * bottom right, by only moving to the right and down, is indicated in bold red
 * and is equal to 2427.
 * 
 * 			131	673	234	103	18
 * 			201	96	342	965	150
 * 			630	803	746	422	111
 * 			537	699	497	121	956
 * 			805	732	524	37	331
 *
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target
 * As...'), a 31K text file containing a 80 by 80 matrix, from the top left to
 * the bottom right by only moving right and down.
 */
public class Problem081 {

	private static long[][] nums;
	private static long[][] sums;

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		String filePath = "external data/matrix2.txt";
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

		System.out.println(minSum(nums[0].length - 1, nums.length - 1));
		System.out.println(t.toDecimalString());
	}

	private static long minSum(int x, int y) {
		if (sums[y][x] > -1) {
			return sums[y][x];
		} else if (x == 0) {
			if (y == 0) {
				sums[y][x] = nums[y][x];
				return sums[y][x];
			}
			sums[y][x] = minSum(0, y - 1) + nums[y][x];
			return sums[y][x];
		} else if (y == 0) {
			sums[y][x] = minSum(x - 1, 0) + nums[y][x];
			return sums[y][x];
		}

		long left = minSum(x - 1, y);
		long above = minSum(x, y - 1);

		sums[y][x] = Math.min(left, above) + nums[y][x];
		return sums[y][x];
	}
}

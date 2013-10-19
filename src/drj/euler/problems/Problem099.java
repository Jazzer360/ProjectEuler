package drj.euler.problems;

import drj.euler.Utility;

/**
 * Comparing two numbers written in index form like 2^11 and 3^7 is not
 * difficult, as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.
 * 
 * However, confirming that 632382^518061 > 519432^525806 would be much more
 * difficult, as both numbers contain over three million digits.
 * 
 * Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text
 * file containing one thousand lines with a base/exponent pair on each line,
 * determine which line number has the greatest numerical value.
 * 
 * NOTE: The first two lines in the file represent the numbers in the example
 * given above.
 */
public class Problem099 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		String filePath = "external data/base_exp.txt";

		int currLine = 0;
		int maxLine = 0;
		double maxLog = 0.0;

		for (String line : Utility.getFileContents(filePath)) {
			currLine++;
			String[] nums = line.split(",");
			int base = Integer.valueOf(nums[0]);
			int exp = Integer.valueOf(nums[1]);

			double log = exp * Math.log10(base);
			if (log > maxLog) {
				maxLine = currLine;
				maxLog = log;
			}
		}

		System.out.println(maxLine);
		System.out.println(t.toDecimalString());
	}
}

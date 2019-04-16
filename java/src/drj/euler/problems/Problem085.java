package drj.euler.problems;

import java.util.TreeMap;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * By counting carefully it can be seen that a rectangular grid measuring
 * 3 by 2 contains eighteen rectangles:<pre>
 *   _           _ _         _ _ _
 *  |_| - 6     |_|_| - 4   |_|_|_| - 2
 *   _           _ _         _ _ _
 *  |_| - 3     |_|_| - 2   |_|_|_| - 1
 *  |_|         |_|_|       |_|_|_|</pre>
 * 
 * Although there exists no rectangular grid that contains exactly two million
 * rectangles, find the area of the grid with the nearest solution.
 */
@Answer("2772")
public class Problem085 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem085();
		System.out.println(p);
	}

	private static final int THRESHOLD = 2_000_000;

	@Override
	protected String onSolve() {
		TreeMap<Integer, Integer> map = new TreeMap<>();

		outerLoop:
			for (int w = 1; true; w++) {
				for (int h = w; true; h++) {
					int rectangles = numRectangles(h, w);
					map.put(rectangles, h * w);
					if (rectangles > THRESHOLD) {
						if (h == w) {
							break outerLoop;
						}
						break;
					}
				}
			}

		int underKey = map.floorKey(THRESHOLD);
		int overKey = map.ceilingKey(THRESHOLD);
		int answerKey = THRESHOLD - underKey < overKey - THRESHOLD
				? underKey : overKey;

		return String.valueOf(map.get(answerKey));
	}

	private static int numRectangles(int w, int h) {
		int count = 0;
		for (int i = 1; i <= w; i++) {
			for (int j = 1; j <= h; j++) {
				count += numRectanglesOfSize(i, j, w, h);
			}
		}
		return count;
	}

	private static int numRectanglesOfSize(int w, int h, int wMax, int hMax) {
		return (wMax + 1 - w) * (hMax + 1 - h);
	}
}

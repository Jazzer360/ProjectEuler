package drj.euler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes:
 * 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest
 * cube which has exactly three permutations of its digits which are also cube.
 * 
 * <p>Find the smallest cube for which exactly five permutations of its digits
 * are cube.
 */
@Answer("127035954683")
public class Problem062 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem062();
		System.out.println(p);
	}

	private static final int NUM_PERMUTATIONS = 5;

	@Override
	protected String onSolve() {
		Map<ArrayList<Character>, ArrayList<Integer>> map = new HashMap<>();
		boolean answerFound = false;
		long answer = 0;

		for (int i = 5; !answerFound; i++) {
			long cube = (long) Math.pow(i, 3);
			ArrayList<Character> key = new ArrayList<>();
			ArrayList<Integer> values;

			for (char c : Long.toString(cube).toCharArray()) {
				key.add(c);
			}
			Collections.sort(key);

			if (map.containsKey(key)) {
				values = map.get(key);
				values.add(i);
				if (values.size() == NUM_PERMUTATIONS) {
					answerFound = true;
					answer = (long) Math.pow(values.get(0), 3);
				}
			} else {
				values = new ArrayList<>();
				values.add(i);
				map.put(key, values);
			}
		}

		return String.valueOf(answer);
	}
}

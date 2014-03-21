package drj.euler.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import drj.euler.Answer;
import drj.euler.Problem;

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
@Answer("0.5731441")
public class Problem205 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem205();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		Map<Integer, Integer> peteOutcomes = new HashMap<>();
		Map<Integer, Integer> colinOutcomes = new HashMap<>();

		fillOutcomeMap(4, 9, peteOutcomes, 0);
		fillOutcomeMap(6, 6, colinOutcomes, 0);

		long totalOutcomes = 0;
		long peteWins = 0;

		for (Entry<Integer, Integer> pete : peteOutcomes.entrySet()) {
			for (Entry<Integer, Integer> colin : colinOutcomes.entrySet()) {
				totalOutcomes += pete.getValue() * colin.getValue();
				if (pete.getKey() > colin.getKey()) {
					peteWins += pete.getValue() * colin.getValue();
				}
			}
		}

		double peteWinPct = (double) peteWins / totalOutcomes;

		return String.format("%.7f", peteWinPct);
	}

	private static void fillOutcomeMap(int sides, int dice,
			Map<Integer, Integer> map, int sum) {
		if (dice == 1) {
			for (int i = 1; i <= sides; i++) {
				int thisSum = sum;
				thisSum += i;
				addToMap(thisSum, 1, map);
			}
		} else {
			dice--;
			for (int i = 1; i <= sides; i++) {
				fillOutcomeMap(sides, dice, map, sum + i);
			}
		}
	}

	private static void addToMap(int key, int value,
			Map<Integer, Integer> map) {
		Integer sum = map.get(key);
		if (sum == null) {
			map.put(key, value);
		} else {
			map.put(key, sum + value);
		}
	}
}

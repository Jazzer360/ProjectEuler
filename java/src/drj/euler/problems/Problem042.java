package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The nth term of the sequence of triangle numbers is given by, tn = �n(n+1);
 * so the first ten triangle numbers are:
 * 
 * <p>		1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * <p>By converting each letter in a word to a number corresponding to its
 * alphabetical position and adding these values we form a word value. For
 * example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word
 * value is a triangle number then we shall call the word a triangle word.
 * 
 * <p>Using words.txt (right click and 'Save Link/Target As...'), a 16K text file
 * containing nearly two-thousand common English words, how many are triangle
 * words?
 */
@Answer("162")
public class Problem042 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem042();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		String filePath = "data/words.txt";
		List<String> words = new ArrayList<String>();

		String line = Utility.getFileContents(filePath).get(0);
		for (String word : line.split(",")) {
			words.add(word.replace("\"", ""));
		}

		int triangleWordCount = 0;

		for (String word : words) {
			if (isTriangleWord(word)) {
				triangleWordCount++;
			}
		}

		return String.valueOf(triangleWordCount);
	}

	private static boolean isTriangleWord(String word) {
		char[] letters = word.toCharArray();
		int value = 0;

		for (char letter : letters) {
			value += (int) letter - 64;
		}

		return Utility.isTriangular(value) ? true : false;
	}

}

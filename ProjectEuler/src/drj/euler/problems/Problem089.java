package drj.euler.problems;

import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * The rules for writing Roman numerals allow for many ways of writing each
 * number (see About Roman Numerals...). However, there is always a "best" way
 * of writing a particular number.
 * 
 * For example, the following represent all of the legitimate ways of writing
 * the number sixteen:
 * 
 * 		IIIIIIIIIIIIIIII
 * 		VIIIIIIIIIII
 * 		VVIIIIII
 * 		XIIIIII
 * 		VVVI
 * 		XVI
 * 
 * The last example being considered the most efficient, as it uses the least
 * number of numerals.
 * 
 * The 11K text file, roman.txt (right click and 'Save Link/Target As...'),
 * contains one thousand numbers written in valid, but not necessarily minimal,
 * Roman numerals; that is, they are arranged in descending units and obey the
 * subtractive pair rule (see About Roman Numerals... for the definitive rules
 * for this problem).
 * 
 * Find the number of characters saved by writing each of these in their
 * minimal form.
 * 
 * Note: You can assume that all the Roman numerals in the file contain no more
 * than four consecutive identical units.
 */
@Answer("743")
public class Problem089 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem089();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		String filePath = "data/roman.txt";

		List<String> numerals = Utility.getFileContents(filePath);
		int startCount = 0;
		int endCount = 0;

		for (String numeral : numerals) {
			int value = fromRoman(numeral);
			String reducedNumeral = toRoman(value);

			startCount += numeral.length();
			endCount += reducedNumeral.length();
		}

		return String.valueOf(startCount - endCount);
	}

	private static int fromRoman(String numeral) {
		int sum = 0;
		int currVal = 0;

		for (int i = numeral.length() - 1; i >= 0; i--) {
			int value = numeralValue(numeral.charAt(i));
			if (value >= currVal) {
				sum += value;
				currVal = value;
			} else {
				sum -= value;
			}
		}
		return sum;
	}

	private static String toRoman(int num) {
		return toRoman(new StringBuilder(), num).toString();
	}

	private static StringBuilder toRoman(StringBuilder string, int num) {
		if (num == 0) {
			return string;
		} else if (num / 1000 > 0) {
			string.append('M');
			return toRoman(string, num - 1000);
		} else if (num / 100 > 0) {
			if (num % 500 >= 400) {
				string.append('C');
				return toRoman(string, num + 100);
			} else if (num / 100 >= 5) {
				string.append('D');
				return toRoman(string, num - 500);
			} else {
				string.append('C');
				return toRoman(string, num - 100);
			}
		} else if (num / 10 > 0) {
			if (num % 50 >= 40) {
				string.append('X');
				return toRoman(string, num + 10);
			} else if (num / 10 >= 5) {
				string.append('L');
				return toRoman(string, num - 50);
			} else {
				string.append('X');
				return toRoman(string, num - 10);
			}
		} else {
			if (num % 5 >= 4) {
				string.append('I');
				return toRoman(string, num + 1);
			} else if (num >= 5) {
				string.append('V');
				return toRoman(string, num - 5);
			} else {
				string.append('I');
				return toRoman(string, num - 1);
			}
		}
	}

	private static int numeralValue(char numeral) {
		switch (numeral) {
		case 'I': return 1;
		case 'V': return 5;
		case 'X': return 10;
		case 'L': return 50;
		case 'C': return 100;
		case 'D': return 500;
		case 'M': return 1000;
		default: throw new IllegalArgumentException("char passed is not a"
				+ "valid roman numeral.");
		}
	}
}

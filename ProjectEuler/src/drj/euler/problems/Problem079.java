package drj.euler.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * A common security method used for online banking is to ask the user for
 * three random characters from a password. For example, if the password was
 * 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected
 * reply would be: 317.
 * 
 * The text file, keylog.txt, contains fifty successful login attempts.
 * 
 * Given that the three characters are always asked for in order, analyze the
 * file so as to determine the shortest possible secret password of unknown
 * length.
 */
@Answer("73162890")
public class Problem079 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem079();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		String filePath = "data/keylog.txt";
		Map<Character, HashSet<Character>> digitsAfter =
				new HashMap<>();

		for (String login : Utility.getFileContents(filePath)) {
			char key1 = login.charAt(0);
			char key2 = login.charAt(1);
			char key3 = login.charAt(2);
			HashSet<Character> c1;
			HashSet<Character> c2;

			if (!digitsAfter.containsKey(key1)) {
				c1 = new HashSet<Character>();
				c1.add(key2);
				c1.add(key3);
				digitsAfter.put(key1, c1);
			} else {
				c1 = digitsAfter.get(key1);
				c1.add(key2);
				c1.add(key3);
			}

			if (!digitsAfter.containsKey(key2)) {
				c2 = new HashSet<Character>();
				c2.add(key3);
				digitsAfter.put(key2, c2);
			} else {
				c2 = digitsAfter.get(key2);
				c2.add(key3);
			}
		}

		StringBuilder password = new StringBuilder();

		while (!digitsAfter.isEmpty()) {
			char digit = 0;
			int fewestFollowers = 10;

			for (char c = '0'; c <= '9'; c++) {
				if (digitsAfter.get(c) != null
						&& digitsAfter.get(c).size() < fewestFollowers) {
					digit = c;
					fewestFollowers = digitsAfter.get(c).size();
				}
			}

			password.insert(0, digitsAfter.get(digit).toArray()[0]);
			digitsAfter.remove(digit);

			for (char c : digitsAfter.keySet()) {
				digitsAfter.get(c).remove(password.charAt(0));
			}

			if (digitsAfter.size() == 1) {
				for (char c : digitsAfter.keySet()) {
					password.insert(0, c);
					digitsAfter.clear();
				}
			}
		}

		return password.toString();
	}
}

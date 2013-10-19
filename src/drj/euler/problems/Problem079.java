package drj.euler.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
public class Problem079 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		String filePath = "external data/keylog.txt";
		Map<Character, HashSet<Character>> digitsAfter =
				new HashMap<Character, HashSet<Character>>();

		for (String login : Utility.getFileContents(filePath)) {
			char d1 = login.charAt(0);
			char d2 = login.charAt(1);
			char d3 = login.charAt(2);
			HashSet<Character> c1;
			HashSet<Character> c2;

			if (!digitsAfter.containsKey(d1)) {
				c1 = new HashSet<Character>();
				c1.add(d2);
				c1.add(d3);
				digitsAfter.put(d1, c1);
			} else {
				c1 = digitsAfter.get(d1);
				c1.add(d2);
				c1.add(d3);
			}

			if (!digitsAfter.containsKey(d2)) {
				c2 = new HashSet<Character>();
				c2.add(d3);
				digitsAfter.put(d2, c2);
			} else {
				c2 = digitsAfter.get(d2);
				c2.add(d3);
			}
		}

		StringBuilder password = new StringBuilder();

		while (!digitsAfter.isEmpty()) {
			char digit = 'a';
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

		System.out.println(password);
		System.out.println(t.toDecimalString());
	}
}

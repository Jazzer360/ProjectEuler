package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Each character on a computer is assigned a unique code and the preferred
 * standard is ASCII (American Standard Code for Information Interchange). For
 * example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * 
 * A modern encryption method is to take a text file, convert the bytes to
 * ASCII, then XOR each byte with a given value, taken from a secret key. The
 * advantage with the XOR function is that using the same encryption key on the
 * cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107
 * XOR 42 = 65.
 * 
 * For unbreakable encryption, the key is the same length as the plain text
 * message, and the key is made up of random bytes. The user would keep the
 * encrypted message and the encryption key in different locations, and without
 * both "halves", it is impossible to decrypt the message.
 * 
 * Unfortunately, this method is impractical for most users, so the modified
 * method is to use a password as a key. If the password is shorter than the
 * message, which is likely, the key is repeated cyclically throughout the
 * message. The balance for this method is using a sufficiently long password
 * key for security, but short enough to be memorable.
 * 
 * Your task has been made easy, as the encryption key consists of three lower
 * case characters. Using cipher1.txt (right click and 'Save Link/Target
 * As...'), a file containing the encrypted ASCII codes, and the knowledge that
 * the plain text must contain common English words, decrypt the message and
 * find the sum of the ASCII values in the original text.
 */
public class Problem059 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem059();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		String filePath = "external data/cipher1.txt";
		List<Integer> encrypted = new ArrayList<Integer>();

		String line = Utility.getFileContents(filePath).get(0);
		for (String code : line.split(",")) {
			encrypted.add(Integer.parseInt(code));
		}

		char[] key = {'a', 'a', 'a'};
		List<Integer> decrypted = new ArrayList<Integer>();
		int maxLetterCount = 0;
		List<Integer> bestCanidate = new ArrayList<Integer>();

		do {
			decrypted.clear();
			int keyIndex = 0;
			int letterCount = 0;
			boolean hasInvalidChars = false;

			for (int code : encrypted) {
				char c = (char) (code ^ key[keyIndex++]);
				if (c < 123) {
					decrypted.add((int) c);
					if ((c >= 65 && c <= 90) || (c >=97 && c <= 122)) {
						letterCount++;
					}
					if (keyIndex > key.length - 1) {
						keyIndex = 0;
					}
				} else {
					hasInvalidChars = true;
					break;
				}
			}

			if (!hasInvalidChars && letterCount > maxLetterCount) {
				bestCanidate.clear();
				bestCanidate.addAll(decrypted);
				maxLetterCount = letterCount;
			}

		} while (nextKey(key));

		int sum = 0;

		for (int num : bestCanidate) {
			sum += num;
		}

		return String.valueOf(sum);
	}

	private static boolean nextKey(char[] key) {
		for (int i = key.length; i > 0; i--) {
			if (key[i - 1] < 122) {
				key[i - 1]++;
				return true;
			} else {
				key[i - 1] = 97;
			}
		}
		return false;
	}
}

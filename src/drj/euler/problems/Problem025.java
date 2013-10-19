package drj.euler.problems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import drj.euler.Utility;

/**
 * The Fibonacci sequence is defined by the recurrence relation:
 * 
 * Fn = Fn-1 + Fn-2, where F1 = 1 and F2 = 1.
 * 
 * Hence the first 12 terms will be:
 * 
 * F1 = 1
 * F2 = 1
 * F3 = 2
 * F4 = 3
 * F5 = 5
 * F6 = 8
 * F7 = 13
 * F8 = 21
 * F9 = 34
 * F10 = 55
 * F11 = 89
 * F12 = 144
 * 
 * The 12th term, F12, is the first term to contain three digits.
 * 
 * What is the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class Problem025 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		Map<Integer, BigInteger> fibonacciNumbers =
				new HashMap<Integer, BigInteger>();
		fibonacciNumbers.put(1, BigInteger.valueOf(1));
		fibonacciNumbers.put(2, BigInteger.valueOf(1));
		int term = 0;
		boolean answerFound = false;

		for (int i = 3, length = 1; !answerFound; i++) {
			fibonacciNumbers.put(i,
					fibonacciNumbers.get(i-1).add(fibonacciNumbers.get(i-2)));
			length = fibonacciNumbers.get(i).toString().length();
			if (length == 1000) {
				answerFound = true;
				term = i;
			}
		}
		System.out.println(term);
		System.out.println(t.toDecimalString());
	}
}

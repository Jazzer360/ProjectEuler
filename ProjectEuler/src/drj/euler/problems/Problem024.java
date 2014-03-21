package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one
 * possible permutation of the digits 1, 2, 3 and 4. If all of the permutations
 * are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 * 
 * 			012   021   102   120   201   210
 * 
 * What is the millionth lexicographic permutation of the digits
 * 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
@Answer("2783915460")
public class Problem024 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem024();
		System.out.println(p);
	}

	/*
	 * There is 10! permutations of the digits 0123456789.
	 * 
	 * Let's enumerate the permutations starting with index 0. Then were
	 * going to find the permutation with index 999999. The  permutation
	 * with index 0 is 0123456789.
	 * 
	 * We shall also enumerate the digits "available for use" in the same
	 * way, i.e. starting with index 0. At the beginning, those digits will
	 * be 0123456789 (in that order).
	 * 
	 * We now write 999999 = 2 * 9! + 274239.
	 * The quotient 2 gives the index (in 0123456789) of the first digit: 2.
	 * Remove that digit from the available digits: 013456789.
	 * 
	 * Next, we write 274239 = 6 * 8! + 32319.
	 * The quotient 6 again gives the index (now in 013456789) of the
	 * second digit: 7.
	 * Remove that digit from the available digits: 01345689.
	 * 
	 * Continue in this way, dividing by n!, until (including) n=0.
	 * The quotients will be (from the beginning):
	 * 		2, 6, 6, 2, 5, 1, 2, 1, 1 and 0, giving the digits
	 * 		2, 7, 8, 3, 9, 1, 5, 4, 6 and 0.
	 * 
	 * The searched-for permutation thus is: 2783915460.
	 */
	@Override
	protected String onSolve() {
		List<Integer> digits = new ArrayList<Integer>();
		for (int i = 0; i <= 9; i++) {
			digits.add(i);
		}

		return String.valueOf(Utility.getPermutation(digits, 1_000_000));
	}
}

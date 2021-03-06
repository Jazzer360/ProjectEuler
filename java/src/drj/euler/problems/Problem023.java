package drj.euler.problems;

import java.util.HashSet;
import java.util.Set;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * A perfect number is a number for which the sum of its proper divisors is
 * exactly equal to the number. For example, the sum of the proper divisors of
 * 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * 
 * <p>A number n is called deficient if the sum of its proper divisors is less
 * than n and it is called abundant if this sum exceeds n.
 * 
 * <p>As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the
 * smallest number that can be written as the sum of two abundant numbers is 24.
 * By mathematical analysis, it can be shown that all integers greater than
 * 28123 can be written as the sum of two abundant numbers. However, this upper
 * limit cannot be reduced any further by analysis even though it is known that
 * the greatest number that cannot be expressed as the sum of two abundant
 * numbers is less than this limit.
 * 
 * <p>Find the sum of all the positive integers which cannot be written as the
 * sum of two abundant numbers.
 */
@Answer("4179871")
public class Problem023 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem023();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		Set<Integer> abundants = new HashSet<Integer>();

		for (int i = 12; i <= 28123; i++) {
			if (isAbundant(i)) {
				abundants.add(i);
			}
		}

		int sum = 0;

		for (int i = 28123; i > 0; i--) {
			boolean iCanBeWrittenAsSum = false;
			for (int abundant : abundants) {
				if (abundants.contains(i - abundant)) {
					iCanBeWrittenAsSum = true;
					break;
				}
			}
			if (! iCanBeWrittenAsSum) {
				sum += i;
			}
		}

		return String.valueOf(sum);
	}

	private static boolean isAbundant(int num) {
		if (sumOfProperDivisors(num) > num) {
			return true;
		}
		return false;
	}

	private static int sumOfProperDivisors(int num) {
		int sum = 0;
		for (int factor : Utility.getFactors(num)) {
			sum += factor;
		}
		return sum - num;
	}
}

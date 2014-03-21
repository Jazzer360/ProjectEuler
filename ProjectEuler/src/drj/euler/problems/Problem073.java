package drj.euler.problems;

import java.util.HashSet;
import java.util.Set;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and
 * HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order
 * of size, we get:
 * 
 * 		1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2,
 * 		4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that there are 3 fractions between 1/3 and 1/2.
 * 
 * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced
 * proper fractions for d <= 12,000?
 */
@Answer("7295372")
public class Problem073 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem073();
		System.out.println(p);
	}

	private static final Fraction ONE_THIRD = new Fraction(1, 3);
	private static final Fraction ONE_HALF = new Fraction(1, 2);

	@Override
	protected String onSolve() {
		Set<Fraction> set = new HashSet<>();

		for (int i = 2; i <= 12_000; i++) {
			Fraction base = new Fraction(1, i);
			int multiple = (int) ONE_THIRD.divide(base).toDouble();
			multiple++;
			if (multiple > 0) {
				Fraction f = new Fraction(multiple, i);
				while (f.compareTo(ONE_HALF) < 0) {
					set.add(f);
					multiple++;
					f = new Fraction(multiple, i);
				}
			}
		}

		return String.valueOf(set.size());
	}

	public static class Fraction implements Comparable<Fraction> {
		private final int numerator;
		private final int denominator;

		public Fraction(int numerator, int denominator) {
			int GCF = Utility.getGCF(numerator, denominator);
			this.numerator = numerator / GCF;
			this.denominator = denominator / GCF;
		}

		public double toDouble() {
			return (double) numerator / denominator;
		}

		public Fraction divide(Fraction f) {
			return new Fraction(numerator * f.denominator,
					denominator * f.numerator);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + denominator;
			result = prime * result + numerator;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Fraction))
				return false;
			Fraction other = (Fraction) obj;
			if (denominator != other.denominator)
				return false;
			if (numerator != other.numerator)
				return false;
			return true;
		}

		@Override
		public int compareTo(Fraction o) {
			return Double.compare(toDouble(), o.toDouble());
		}

		@Override
		public String toString() {
			return numerator + "/" + denominator;
		}
	}
}

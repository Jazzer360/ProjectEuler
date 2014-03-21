package drj.euler.problems;

import java.util.NavigableSet;
import java.util.TreeSet;

import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and
 * HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order
 * of size, we get:
 * 
 * 		1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8,
 * 		2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that 2/5 is the fraction immediately to the left of 3/7.
 * 
 * By listing the set of reduced proper fractions for d <= 1,000,000 in
 * ascending order of size, find the numerator of the fraction immediately to
 * the left of 3/7.
 */
public class Problem071 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem071();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		Fraction rightbound = new Fraction(3, 7);
		NavigableSet<Fraction> set = new TreeSet<>();

		for (int i = 2; i <= 1_000_000; i++) {
			int multiple = (int) (rightbound.toDouble() / (1.0 / i));
			if (multiple > 0) {
				set.add(new Fraction(multiple, i));
			}
		}

		return String.valueOf(set.lower(rightbound).getNumerator());
	}

	public static class Fraction implements Comparable<Fraction> {
		private final int numerator;
		private final int denominator;

		public Fraction(int numerator, int denominator) {
			int GCF = Utility.getGCF(numerator, denominator);
			this.numerator = numerator / GCF;
			this.denominator = denominator / GCF;
		}

		public int getNumerator() {
			return numerator;
		}

		public double toDouble() {
			return (double) numerator / denominator;
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

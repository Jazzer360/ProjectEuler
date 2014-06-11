package drj.euler.problems;

import java.math.BigInteger;

import drj.euler.Problem;

/**
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs
 * and six red discs, and two discs were taken at random, it can be seen that
 * the probability of taking two blue discs, P(BB) = (15/21)×(14/20) = 1/2.
 * 
 * <p>
 * The next such arrangement, for which there is exactly 50% chance of taking
 * two blue discs at random, is a box containing eighty-five blue discs and
 * thirty-five red discs.
 * 
 * <p>
 * By finding the first arrangement to contain over 10^12 = 1,000,000,000,000
 * discs in total, determine the number of blue discs that the box would
 * contain.
 */
public class Problem100 extends Problem {

	private static final BigInteger TWO = BigInteger.valueOf(2);

	public static void main(String[] args) {
		Problem p = new Problem100();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		BigInteger blueDiscs = null;
		BigInteger total = BigInteger.valueOf((long) (Math.pow(10, 12) + 1));
		while (blueDiscs != null) {
			blueDiscs = getNumBlue(total);
			total = total.add(BigInteger.ONE);
		}
		return null;
	}

	private BigInteger getNumBlue(BigInteger totalDiscs) {
		BigInteger square = totalDiscs.pow(2).subtract(totalDiscs)
				.multiply(TWO).add(BigInteger.ONE);
		return null;
	}
}

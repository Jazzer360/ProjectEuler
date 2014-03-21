package drj.euler.problems;

import java.util.ArrayList;
import java.util.List;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 * 
 * 		192 x 1 = 192
 * 		192 x 2 = 384
 * 		192 x 3 = 576
 * 
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We
 * will call 192384576 the concatenated product of 192 and (1,2,3)
 * 
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
 * and 5, giving the pandigital, 918273645, which is the concatenated product
 * of 9 and (1,2,3,4,5).
 * 
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as
 * the concatenated product of an integer with (1,2, ... , n) where n > 1?
 */
@Answer("932718654")
public class Problem038 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem038();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		List<Integer> products = new ArrayList<Integer>();

		int maxPandigital = 0;

		for (int i = 1; i <= 9876; i++){
			products.clear();
			int length = 0;
			for (int j = 1; length < 9; j++) {
				products.add(i * j);
				length += Utility.numDigits(i * j);
			}

			if (length == 9) {
				if (Utility.isPandigital(1, 9, products)) {
					StringBuilder temp = new StringBuilder();
					for (int num : products) {
						temp.append(String.valueOf(num));
					}
					int pandigital = Integer.parseInt(temp.toString());

					if (pandigital > maxPandigital) {
						maxPandigital = pandigital;
					}
				}
			}
		}

		return String.valueOf(maxPandigital);
	}
}

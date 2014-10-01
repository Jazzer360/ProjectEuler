package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five,
 * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * 
 * <p>If all the numbers from 1 to 1000 (one thousand) inclusive were written
 * out in words, how many letters would be used?
 * 
 * <p>NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and
 * forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20
 * letters. The use of "and" when writing out numbers is in compliance with
 * British usage.
 */
@Answer("21124")
public class Problem017 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem017();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		final int AND = 3*891;		//101-199, 201-299 ... 801-899, 901-999 = 891	891	- 2673
		final int ONE = 3*191;		//1, 21, 31 ... 81, 91 = 9 * 10 = 90			191	- 573
									//100, 101, 102 ... 198, 199 = 100 
									//1000 = 1
		final int TWO = 3*190;		//2, 22, 32 ... 82, 92 = 9 * 10 = 90			190 - 570
									//200, 201, 202 ... 298, 299 = 100
		final int THREE = 5*190; 	//3, 23, 33 ... 83, 93 = 9 * 10 = 90			190 - 950
									//300, 301, 302 ... 398, 399 = 100
		final int FOUR = 4*190;		//												190 - 760
		final int FIVE = 4*190;		//												190 - 760
		final int SIX = 3*190;		//												190 - 570
		final int SEVEN = 5*190;	//												190 - 950
		final int EIGHT = 5*190;	//												190 - 950
		final int NINE = 4*190;		//												190 - 760
		final int TEN = 3*10; 		//10, 110, 210 ... 810, 910 = 10				10	- 30
		final int ELEVEN = 6*10;	//11, 111, 211 ... 811, 911 = 10				10	- 60
		final int TWELVE = 6*10;	//												10	- 60
		final int THIRTEEN = 8*10;	//												10	- 80
		final int FOURTEEN = 8*10;	//												10	- 80
		final int FIFTEEN = 7*10;	//												10	- 70
		final int SIXTEEN = 7*10;	//												10	- 70
		final int SEVENTEEN = 9*10;//												10	- 90
		final int EIGHTEEN = 8*10;	//												10	- 80
		final int NINETEEN = 8*10;	//												10	- 80
		final int TWENTY = 6*100;	//20, 21, 22 ... 28, 29 = 10
									//20-29, 120-129, 220-229 ... 820-829, 920-929	100	- 600
		final int THIRTY = 6*100;	//30, 31, 32 ... 38, 39 = 10
									//30-39, 130-139, 230-239 ... 					100	- 600
		final int FORTY = 5*100;	//												100	- 500
		final int FIFTY = 5*100;	//												100	- 500
		final int SIXTY = 5*100;	//												100	- 500
		final int SEVENTY = 7*100;	//												100	- 700
		final int EIGHTY = 6*100;	//												100	- 600
		final int NINETY = 6*100;	//												100	- 600
		final int HUNDRED = 7*900;	//100-199, 200-299 ... 900-999					900	- 6400
		final int THOUSAND = 8*1;	//1000											1	- 8

		// 1-9: 36
		// 10-19: 70
		// 20-29: 6*10 + 36 = 96
		// 30-39: 6*10 + 36 = 96
		// 40-49: 5*10 + 36 = 86
		// 50-59: 5*10 + 36 = 86
		// 60-69: 5*10 + 36 = 86
		// 70-79: 7*10 + 36 = 106
		// 80-89: 6*10 + 36 = 96
		// 90-99: 6*10 + 36 = 96
		// 100-109 (3 + 7)*10 + 3*9 + 36 = 163
		// 110-119 (3 + 7)*10 + 3*10 + 70 = 200
		// 120-129 (

		return String.valueOf(AND + ONE + TWO + THREE + FOUR + FIVE + SIX + SEVEN + 
				EIGHT + NINE + TEN + ELEVEN + TWELVE + THIRTEEN + FOURTEEN + FIFTEEN +
				SIXTEEN + SEVENTEEN + EIGHTEEN + NINETEEN + TWENTY + THIRTY + FORTY + 
				FIFTY + SIXTY + SEVENTY + EIGHTY + NINETY + HUNDRED + THOUSAND);
	}
}

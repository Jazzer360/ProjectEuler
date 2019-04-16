package drj.euler.problems;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import drj.euler.Answer;
import drj.euler.Problem;

/**
 * You are given the following information, but you may prefer to do some
 * research for yourself.
 * 
 * <p>1 Jan 1900 was a Monday.
 * 
 * <p>Thirty days has September,
 * <br>April, June and November.
 * <br>All the rest have thirty-one,
 * <br>Saving February alone,
 * <br>Which has twenty-eight, rain or shine.
 * <br>And on leap years, twenty-nine.
 * 
 * <p>A leap year occurs on any year evenly divisible by 4, but not on a
 * century unless it is divisible by 400. How many Sundays fell on the first of
 * the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
@Answer("171")
public class Problem019 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem019();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		int sundays = 0;

		LocalDate date = LocalDate.of(1901, Month.JANUARY, 1);
		while (date.getYear() < 2001) {
			if (date.getDayOfWeek() == DayOfWeek.SUNDAY) sundays++;
			date = date.plusMonths(1);
		}

		return String.valueOf(sundays);
	}
}

package drj.euler.problems;

import java.util.Calendar;

import drj.euler.Utility;

/**
 * You are given the following information, but you may prefer to do some
 * research for yourself.
 * 
 * 1 Jan 1900 was a Monday.
 * 
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * 
 * A leap year occurs on any year evenly divisible by 4, but not on a century
 * unless it is divisible by 400. How many Sundays fell on the first of the
 * month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class Problem019 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		Calendar c = Calendar.getInstance();

		int sundays = 0;

		for (int year = 1901; year <= 2000 ; year++) {

			for (int month = 0; month <= 11; month++) {
				c.set(year, month, 1);

				if (c.get(Calendar.DAY_OF_WEEK) == 1) {
					sundays++;
				}
			}
		}

		System.out.println(sundays);
		System.out.println(t.toDecimalString());
	}
}

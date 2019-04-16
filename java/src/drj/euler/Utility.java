package drj.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A helper class filled with static methods and a timer class that can be used
 * to assist in the writing of programs specifically targeted at solving the
 * problems at projecteuler.net
 */
public final class Utility {

	private Utility(){}

	/**
	 * A simple timer that you can start/stop/reset and get the total running
	 * time the timer has been started.
	 * 
	 * <p>To use, first create a new Timer using the public constructor.
	 * Following it's creation, you can start/stop/reset the timer using the
	 * methods of the same names.
	 * 
	 * <p>Resetting the timer resets the duration the timer has been running to
	 * 0 but the timer's running state is unchanged, so if the timer is already
	 * running it will track the time from when it was reset.
	 */
	public static class Timer {
		private long startedMs = 0;
		private long totalMs = 0;
		private boolean isRunning = false;

		private static final int[] SEGMENT_MULTIPLES = 
			{1000, 60, 60, 24, 1};
		private static final String[] SEGMENT_LABELS = 
			{" ms", " secs", " mins", " hrs", " days"};
		private static final String SEPARATOR = ", ";

		/**
		 * Returns the total number of milliseconds that the timer has been
		 * running.
		 * 
		 * @return milliseconds the timer has been running
		 */
		public long getMillis() {
			if (isRunning) {
				return totalMs + currentMs() - startedMs;
			} else {
				return totalMs;
			}
		}

		/**
		 * Returns the running state of the timer.
		 * 
		 * @return true if the timer is running, otherwise false
		 */
		public boolean isRunning() {
			return isRunning;
		}

		/**
		 * Resets the amount of time the timer has been running. Does not alter
		 * the running state, so if the timer is already started, resetting
		 * will continue to track the time since the reset.
		 */
		public void reset() {
			if (isRunning) {
				startedMs = currentMs();
			}
			totalMs = 0;
		}

		/**
		 * Starts the timer, setting the state to running.
		 */
		public void start() {
			if (!isRunning) {
				isRunning = true;
				startedMs = currentMs();
			}
		}

		/**
		 * Stops the timer, setting the state to not running.
		 */
		public void stop() {
			if (isRunning) {
				totalMs += currentMs() - startedMs;
				isRunning = false;
			}
		}

		/**
		 * Returns a string containing all relevant time sub-divisions all the
		 * way up to a day.
		 * 
		 * <p>Examples:
		 * <ul><li>1 days, 9 hrs, 27 mins, 41 secs, 123 ms</li>
		 * <li>1 mins, 28 secs, 473 ms</li></ul>
		 * 
		 * @return 	String representation of the total time the timer has been
		 * 			running
		 */
		@Override
		public String toString() {
			long time = getMillis();

			StringBuilder output = new StringBuilder();

			for (int i = 0; true; i++) {
				if (i == SEGMENT_MULTIPLES.length - 1) {
					output.insert(0, time + SEGMENT_LABELS[i]);
					break;
				} else {
					output.insert(0, time % SEGMENT_MULTIPLES[i]
							+ SEGMENT_LABELS[i]);
					time /= SEGMENT_MULTIPLES[i];
				}

				if (time == 0) {
					break;
				} else {
					output.insert(0, SEPARATOR);
				}
			}

			return output.toString();
		}

		/**
		 * Returns a string containing the largest relevant time subdivision
		 * in decimal format to two decimal places.
		 * 
		 * <p>Examples:
		 * <ul><li>4.72 secs</li>
		 * <li>472 ms</li>
		 * <li>8.12 hrs</li></ul>
		 * 
		 * @return 	String representation of the total time the timer has been
		 * 			running in decimal format
		 */
		public String toDecimalString() {
			long time = getMillis();
			double decimal = 0.0;

			if (time / SEGMENT_MULTIPLES[0] == 0) {
				return time % SEGMENT_MULTIPLES[0] + SEGMENT_LABELS[0];
			}

			for (int i = 0; true; i++) {
				if (i == SEGMENT_MULTIPLES.length - 1) {
					decimal += time;
					return String.format("%.2f", decimal) + SEGMENT_LABELS[i];
				} else if (time / SEGMENT_MULTIPLES[i] == 0) {
					decimal += time;
					return String.format("%.2f", decimal) + SEGMENT_LABELS[i];
				} else {
					decimal += time % SEGMENT_MULTIPLES[i];
					decimal /= SEGMENT_MULTIPLES[i];
					time /= SEGMENT_MULTIPLES[i];
				}
			}
		}

		private static long currentMs() {
			return System.nanoTime() / 1_000_000;
		}
	}

	/**
	 * An implementation of the sieve of Eratosthenes for generating
	 * consecutive prime numbers. To use the sieve, you must create one with
	 * either of the public constructors. The nature of the sieve requires all
	 * numbers be sieved in order from smallest to largest, so when checking
	 * a small amount of larger primes, the Utility.isPrime(num) function may
	 * be faster.
	 * 
	 * <p> When creating, the number to sieve to may be specified. This can
	 * also be done with the sieveTo(long num) function. Alternately, you may
	 * use the other sieve functions and the sieving will be done automatically
	 * in increments of 100,000.
	 */
	public static class PrimeSieve {
		private long sievedTo;
		private int partsAllocated;
		private final boolean[][] primeSieve;
		private static final int PARTITION_SIZE = 1_000_000;
		private static final int SIEVE_INCREMENT = 100_000;
		/**
		 * Maximum value that the sieve can sieve to.
		 */
		public static final long SIEVE_MAX = 1_999_999_999_999L;

		/**
		 * Default constructor for the sieve. Creates a new sieve without any
		 * sieving done.
		 */
		public PrimeSieve() {
			primeSieve = new boolean[PARTITION_SIZE][];
			partsAllocated = 0;
			addPartition();
			primeSieve[0][0] = false;
			sievedTo = 5;
		}

		/**
		 * Alternate constructor that creates a new sieve with the numbers
		 * pre-sieved to the number specified in the parameter.
		 * 
		 * @param num	number to sieve to
		 */
		public PrimeSieve(long num) {
			this();
			sieveTo(num);
		}

		private void addPartition() {
			boolean[] partition = new boolean[PARTITION_SIZE];
			for (int i = 0; i < partition.length; i++) {
				partition[i] = true;
			}
			primeSieve[partsAllocated++] = partition;
		}

		private static long indexOfValue(long value) {
			return value / 2;
		}

		private static long valueOfIndex(long index) {
			return index * 2 + 1;
		}

		/**
		 * Sets up the sieve for all numbers up to the number specified by the
		 * parameter. Will start sieving from the last number sieved to
		 * minimize redundancy.
		 * 
		 * @param num	number to sieve to
		 * @throws IllegalArgumentException
		 * 				if the number passed is greater than the maximum sieve
		 * 				size
		 */
		public void sieveTo(long num) {
			if (num > SIEVE_MAX) {
				throw new IllegalArgumentException(
						"Number exceeds the max size of sieve.");
			} else if (num <= sievedTo) {
				return;
			}

			int partitions = (int) (indexOfValue(num) / PARTITION_SIZE + 1);
			while (partsAllocated < partitions) {
				addPartition();
			}

			long sieveStart = sievedTo / 2;
			long sieveLimit = num / 2;
			int primeLimit = (int) ((Math.sqrt(num) - 1) / 2);
			for (long i = 1; i <= primeLimit; i++) {
				if (primeSieve[(int) (i / PARTITION_SIZE)]
						[(int) i % PARTITION_SIZE]) {
					for (long step = i * 2 + 1,
							j = i + step * Math.max(sieveStart / step, 1);
							j <= sieveLimit;
							j += step) {
						primeSieve[(int) (j / PARTITION_SIZE)]
								[(int) j % PARTITION_SIZE] = false;
					}
				}
			}

			sievedTo = valueOfIndex(sieveLimit);
		}

		/**
		 * Returns true or false depending on whether the passed number is
		 * prime.
		 * 
		 * <p>Compared to the Utility.isPrime(num) function, when looking for a
		 * small number of larger primes, this function may perform worse. This
		 * is due to to the sieving being done to generate not only the prime
		 * status of the number passed, but all numbers below it.
		 * 
		 * @param num	number to check for prime quality
		 * @return		true if number is prime, otherwise false
		 * @throws IllegalArgumentException
		 * 				if the number passed is greater than the maximum sieve
		 * 				size
		 */
		public boolean isPrime(long num) {
			if (num > SIEVE_MAX) {
				throw new IllegalArgumentException(
						"Number exceeds the max size of sieve.");
			} else if (num < 2) {
				return false;
			} else if (num % 2 == 0) {
				return num == 2;
			} else {
				while (num > sievedTo) {
					sieveTo(Math.min(
							sievedTo + SIEVE_INCREMENT, SIEVE_MAX));
				}
				long index = indexOfValue(num);
				return primeSieve[(int) (index / PARTITION_SIZE)]
						[(int) (index % PARTITION_SIZE)];
			}
		}

		/**
		 * Returns the first prime with a value higher than the number passed.
		 * 
		 * @param prime	number to get the next prime number from
		 * @return		smallest prime number greater than the passed number
		 * @throws IllegalArgumentException
		 * 				if the number passed does not have a prime number
		 * 				greater than it that fits inside the maximum size of
		 * 				the sieve
		 */
		public long getNextPrime(long prime) {
			if (prime >= SIEVE_MAX - 18) {
				throw new IllegalArgumentException(
						"Number exceeds the max size of sieve.");
			} else if (prime < 2) {
				return 2;
			} else if (prime % 2 == 0) {
				prime--;
			}

			long index = indexOfValue(prime);
			do {
				index++;
				while (valueOfIndex(index) > sievedTo) {
					sieveTo(Math.min(
							sievedTo + SIEVE_INCREMENT, SIEVE_MAX));
				}
			} while (!primeSieve[(int) (index / PARTITION_SIZE)]
					[(int) (index % PARTITION_SIZE)]);
			return valueOfIndex(index);
		}
	}

	/**
	 * Represents a range of integers. Utility method included to split the
	 * range equally into a set of smaller ranges.
	 */
	public static class Range {
		/** Beginning of the range */
		public final long from;
		/** End of the range */
		public final long to;

		/**
		 * Create a new range with the specified beginning and end. Throws an
		 * {@link IllegalArgumentException} if the end of the range is less
		 * than the beginning.
		 * 
		 * @param from beginning of the range
		 * @param to end of the range
		 */
		public Range(long from, long to) {
			if (from >= to) throw new IllegalArgumentException(
					"to argument must be > from argument");
			this.from = from;
			this.to = to;
		}

		/**
		 * Splits the range into equal parts specified by the parts param.
		 * 
		 * @param parts number of parts to split range into
		 * @return array of sub-ranges that fully represent the initial range
		 * @throws IllegalArgumentException if number of parts exceeds the
		 * 		number of values in the range
		 */
		public Range[] split(int parts) {
			if (parts > (to - from + 1) / 2) throw new IllegalArgumentException(
					"range not large enough to split into " + parts);
			Range[] ranges = new Range[parts];

			long from = this.from;
			long range = to - from + 1;
			long rangeSize = range / parts;
			long remainder = range % parts;

			for (int i = 0; i < parts; i++) {
				long newFrom = from;
				long newTo = newFrom + rangeSize - (remainder-- > 0 ? 0 : 1);
				from = newTo + 1;
				ranges[i] = new Range(newFrom, newTo);
			}

			return ranges;
		}

		@Override
		public String toString() {
			return "Range: [" + from + " - " + to + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (from ^ (from >>> 32));
			result = prime * result + (int) (to ^ (to >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof Range)) return false;
			Range other = (Range) obj;
			return from == other.from && to == other.to;
		}
	}

	/**
	 * Returns a List{@literal<String>} with each element in the list
	 * containing a string of one line in a file. The location of the file is
	 * passed via the parameter of this method.
	 * 
	 * @param filePath	String containing the path to the file to retrieve
	 * 					contents of
	 * @return			List{@literal<String>} containing the contents of
	 * 					the file
	 * @throws IllegalArgumentException
	 * 					if the path given causes an error during the opening or
	 * 					closing process
	 */
	public static List<String> getFileContents(String filePath) {
		BufferedReader inputStream = null;
		List<String> lines = new ArrayList<>();

		try {
			inputStream = new BufferedReader(new FileReader(filePath));

			String line;
			while ((line = inputStream.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Error opening file: " + filePath);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(
							"Error closing file: " + filePath);
				}
			}
		}

		return lines;
	}

	/**
	 * Returns the int value of a character primitive. Throws an
	 * IllegalArgumentException If the character passed is not an integer.
	 * 
	 * @param c		the character to return int value of
	 * @return		int value of a character
	 * @throws IllegalArgumentException
	 * 				if the passed character is not an integer
	 */
	public static int charToInt(char c) {
		if (c - 48 >= 0 && c - 48 <= 9) {
			return c - 48;
		} else {
			throw new IllegalArgumentException(
					"Character " + c + " can not be converted to an int value");
		}
	}

	/**
	 * Returns a BigInteger representing the factorial value of the passed
	 * parameter n.
	 * 
	 * @param n		the number to find the factorial of
	 * @return		BigInteger representing n!
	 * @throws IllegalArgumentException
	 * 				if the passed value is negative
	 */
	public static BigInteger factorial(int n) {
		if (n < 2) {
			if (n < 0) {
				throw new IllegalArgumentException(
						"Cannot calculate factorial for negative numbers");
			}
			return BigInteger.ONE;
		}
		return factorial(n-1).multiply(BigInteger.valueOf(n));
	}

	/**
	 * Returns a List{@literal<Integer>} of the factors of the passed
	 * parameter. The returned list is sorted from smallest to largest.
	 * 
	 * @param num	int value to find the factors of
	 * @return		List{@literal<Integer>} containing the factors of the
	 * 				passed parameter
	 * @throws IllegalArgumentException
	 * 				if the passed parameter is less than 1
	 */
	public static List<Integer> getFactors(int num) {
		if (num < 1) {
			throw new IllegalArgumentException(
					"Cannot get factors for a number less than 1");
		}

		List<Integer> factors = new ArrayList<>();
		double root = Math.sqrt(num);

		for (int i = 1; i < root; i++) {
			if (num % i == 0) {
				factors.add(i);
				factors.add(num / i);
			}
		}
		if (root % 1 == 0) factors.add((int) root);

		Collections.sort(factors);

		return factors;
	}

	/**
	 * Returns the greatest common factor between two integers. If passed
	 * negative values, the absolute value of the number is taken. If a number
	 * passed is zero, returns zero.
	 * 
	 * @param num1	first number to find GCF of
	 * @param num2	second number to find GCF of
	 * @return		greatest common factor between the passed numbers
	 */
	public static int getGCF(int num1, int num2) {
		num1 = Math.abs(num1);
		num2 = Math.abs(num2);
		int big = Math.max(num1, num2);
		int small = Math.min(num1, num2);

		if (small == 0 || big % small == 0) {
			return small;
		} else {
			return getGCF(big % small, small);
		}
	}

	/**
	 * Returns the lowest common multiple between two integers. If passed
	 * negative values, the absolute value of the number is taken. If either
	 * number passed is zero, returns zero.
	 * 
	 * @param num1	first number to find LCM of
	 * @param num2	second number to find LCM of
	 * @return		lowest common multiple between the passed numbers
	 */
	public static long getLCM(int num1, int num2) {
		if (num1 == 0 || num2 == 0) {
			return 0;
		}
		return Math.abs((long) num1 * num2) / getGCF(num1, num2);
	}

	/**
	 * Returns a long primitive representing the nth permutation of a set of
	 * digits. Each digit is treated as a different digit, so if passed a set
	 * of digits containing duplicates, there will be multiple values for the
	 * nth parameter that will return the same number. Sorting is not done
	 * before calculating the permutation, so if passed a non-sorted list, the
	 * permutations will not be ascending in order.
	 * 
	 * @param digits	ArrayList{@literal<Integer>} containing the digits to
	 * 					find a permutation of
	 * @param nth		the nth permutation of the passed digits to find
	 * @return			the nth permutation of the passed digits
	 */
	public static long getPermutation(List<Integer> digits, int nth) {
		StringBuilder output = new StringBuilder();

		List<Integer> nums = new ArrayList<>();
		nums.addAll(digits);

		for (int i = nums.size() - 1; i >= 0; i--) {
			int index = 0;
			while (nth - Utility.factorial(
					nums.size() - 1).longValue() > 0) {
				nth -= Utility.factorial(nums.size() - 1).longValue();
				index++;
			}
			output.append(nums.get(index));
			nums.remove(index);
		}

		return Long.parseLong(output.toString());
	}

	/**
	 * Returns true if the passed int is a palindrome. That is, the number is
	 * unchanged when you reverse the digits. Otherwise, returns false. Since
	 * negative numbers are prepended with a minus sign, they will always
	 * return false.
	 * 
	 * @param num	the number to check 
	 * @return		true if the number is a palindrome, otherwise false
	 */
	public static boolean isPalindrome(int num) {
		return String.valueOf(num).equals(
				new StringBuilder(String.valueOf(num)).reverse().toString());
	}

	/**
	 * Returns true if the passed BigInteger is a palindrome. That is, the
	 * number is unchanged when you reverse the digits. Otherwise, returns
	 * false. Since negative numbers are prepended with a minus sign, they will
	 * always return false.
	 * 
	 * @param num	the number to check 
	 * @return		true if the number is a palindrome, otherwise false
	 */
	public static boolean isPalindrome(BigInteger num) {
		return num.toString().equals(
				new StringBuilder(num.toString()).reverse().toString());
	}

	/**
	 * Returns true if the passed string is a palindrome. That is, the string
	 * is unchanged when you reverse the characters. Otherwise, returns false.
	 * Is case sensitive.
	 * 
	 * @param string	the string to check 
	 * @return			true if the string is a palindrome, otherwise false
	 */
	public static boolean isPalindrome(String string) {
		return string.equals(new StringBuilder(string).reverse().toString());
	}

	/**
	 * Returns true if the passed numbers, when combined, are pandigital with
	 * the range beg to end. That is, the digits that compose the numbers
	 * passed are used exactly once within the range beg to end.
	 * 
	 * @param beg	int representing the lowest int the number must contain
	 * @param end	int representing the highest int the number must contain
	 * @param nums	any number of integer values that this method will check
	 * 				for the pandigital quality 
	 * @return		true if the passed numbers are pandigital from beg to end,
	 * 				otherwise false
	 * @throws IllegalArgumentException
	 * 				when beg is greater than end, and when beg or end is less
	 * 				than 0 or greater than 9
	 */
	public static boolean isPandigital(int beg, int end, int... nums) {
		if (beg > end) {
			throw new IllegalArgumentException(
					"The parameter end must be greater than beg");
		} else if (beg > 9 || beg < 0 || end > 9 || end < 0) {
			throw new IllegalArgumentException(
					"The beg and end parameters must be between 0 and 9"
							+ " inclusive");
		}
		List<Integer> digits = new ArrayList<>();

		for (int num : nums) {
			while (num > 0) {
				digits.add(num % 10);
				num /= 10;
			}
		}

		if (digits.size() != end - beg + 1) {
			return false;
		}

		for (int i = beg; i <= end; i++) {
			if (!digits.contains(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if the passed numbers, when combined, are pandigital with
	 * the range beg to end. That is, the digits that compose the numbers
	 * passed are used exactly once within the range beg to end.
	 * 
	 * @param beg	int representing the lowest int the number must contain
	 * @param end	int representing the highest int the number must contain
	 * @param nums	ArrayList{@literal<Integer>} with any number of integer
	 * 				values that this method will check for the pandigital
	 * 				quality
	 * @return		true if the passed numbers are pandigital from beg to end,
	 * 				otherwise false
	 * @throws IllegalArgumentException
	 * 				when beg is greater than end, and when beg or end is less
	 * 				than 0 or greater than 9
	 */
	public static boolean isPandigital(int beg, int end,
			List<Integer> nums) {
		if (beg > end) {
			throw new IllegalArgumentException(
					"The parameter end must be greater than beg");
		} else if (beg > 9 || beg < 0 || end > 9 || end < 0) {
			throw new IllegalArgumentException(
					"The beg and end parameters must be between 0 and 9"
							+ " inclusive");
		}

		List<Integer> digits = new ArrayList<>();

		for (int num : nums) {
			while (num > 0) {
				digits.add(num % 10);
				num /= 10;
			}
		}

		if (digits.size() != end - beg + 1) {
			return false;
		}

		for (int i = beg; i <= end; i++) {
			if (!digits.contains(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if the two values passed are permutations of one another.
	 * That is, they are both composed of the same digits.
	 * 
	 * @param n1	first number to check
	 * @param n2	second number to check
	 * @return		true if both numbers are permutations of one another
	 */
	public static boolean isPermutation(int n1, int n2) {
		List<Integer> digits1 = new ArrayList<>();
		List<Integer> digits2 = new ArrayList<>();

		while (n1 > 0) {
			digits1.add(n1 % 10);
			n1 /= 10;
		}
		Collections.sort(digits1);

		while (n2 > 0) {
			digits2.add(n2 % 10);
			n2 /= 10;
		}
		Collections.sort(digits2);

		return digits1.equals(digits2);
	}

	/**
	 * Returns true if the passed int is a prime number. That is, the number
	 * has exactly two divisors, 1 and itself.
	 * 
	 * @param num	number to check for the prime quality
	 * @return		true if the number is prime, otherwise false
	 */
	public static boolean isPrime(int num) {
		if (num < 2) return false;
		else if (num < 4) return true;
		else if (num % 2 == 0 || num % 3 == 0) return false;
		else if (num < 9) return true;
		for (int i = 5, lim = (int) Math.sqrt(num); i <= lim; i += 6) {
			if (num % i == 0 || num % (i + 2) == 0) return false;
		}
		return true;
	}

	/**
	 * Returns true if the passed long is a prime number. That is, the number
	 * has exactly two divisors, 1 and itself.
	 * 
	 * @param num	number to check for the prime quality
	 * @return		true if the number is prime, otherwise false
	 */
	public static boolean isPrime(long num) {
		if (num < 2) return false;
		else if (num < 4) return true;
		else if (num % 2 == 0 || num % 3 == 0) return false;
		else if (num < 9) return true;
		for (long i = 5, lim = (long) Math.sqrt(num); i <= lim; i += 6) {
			if (num % i == 0 || num % (i + 2) == 0) return false;
		}
		return true;
	}

	/**
	 * Returns the nth triangle number based on the following formula:
	 * 
	 * 		Tn = n * (n + 1) / 2
	 * 
	 * @param n	the triangle number to retrieve
	 * @return	nth triangle number
	 * @throws IllegalArgumentException
	 * 			if passed a value less than 1
	 */
	public static long getTriangleNumber(int n) {
		if (n < 1) {
			throw new IllegalArgumentException(
					"Values passed must be greater than 0");
		}
		return ((long) n * (n + 1)) / 2;
	}

	/**
	 * Returns the nth pentagonal number based on the following formula:
	 * 
	 * 		Tn = n * (3n - 1) / 2
	 * 
	 * @param n	the pentagonal number to retrieve
	 * @return	nth pentagonal number
	 * @throws IllegalArgumentException
	 * 			if passed a value less than 1
	 */
	public static long getPentagonal(int n) {
		if (n < 1) {
			throw new IllegalArgumentException(
					"Values passed must be greater than 0");
		}
		return (n * (3 * (long) n - 1)) / 2;
	}

	/**
	 * Returns true if the passed number is a triangle number. That is, it
	 * can be generated using the formula:
	 * <ul><li>Tn = n (n + 1) / 2</li></ul>
	 * 
	 * @param num	number to check if is triangular
	 * @return		true if number is triangular, otherwise false
	 */
	public static boolean isTriangular(long num) {
		return (Math.sqrt(8 * num + 1) - 1) % 2 == 0;
	}

	/**
	 * Returns true if the passed number is square.
	 * 
	 * @param num	number to check if
	 * @return		true if number is a perfect square, otherwise false
	 */
	public static boolean isSquare(long num) {
		return Math.sqrt(num) % 1 == 0;
	}

	/**
	 * Returns true if the passed number is a pentagonal number. That is, it
	 * can be generated using the formula:
	 * <ul><li>Pn = n (3n - 1) / 2</li></ul>
	 * 
	 * @param num	number to check if is pentagonal
	 * @return		true if number is pentagonal, otherwise false
	 */
	public static boolean isPentagonal(long num) {
		return (Math.sqrt(24 * num + 1) + 1) % 6 == 0;
	}

	/**
	 * Returns true if the passed number is a hexagonal number. That is, it can
	 * be generated using the formula:
	 * <ul><li>Hn = n (2n - 1)</li></ul>
	 * 
	 * @param num	number to check if is hexagonal
	 * @return		true if number is hexagonal, otherwise false
	 */
	public static boolean isHexagonal(long num) {
		return (Math.sqrt(8 * num + 1) + 1) % 4 == 0;
	}

	/**
	 * Returns true if the passed number is a heptagonal number. That is, it
	 * can be generated using the formula:
	 * <ul><li>Hn = n (5n - 3) / 2</li></ul>
	 * 
	 * @param num	number to check if is heptagonal
	 * @return		true if number is heptagonal, otherwise false
	 */
	public static boolean isHeptagonal(long num) {
		return (Math.sqrt(40 * num + 9) + 3) % 10 == 0;
	}

	/**
	 * Returns true if the passed number is an octagonal number. That is, it
	 * can be generated using the formula:
	 * <ul><li>On = n (3n - 2)</li></ul>
	 * 
	 * @param num	number to check if is octagonal
	 * @return		true if number is octagonal, otherwise false
	 */
	public static boolean isOctagonal(long num) {
		return (Math.sqrt(3 * num + 1) + 1) % 3 == 0;
	}

	/**
	 * Returns the number of digits in the int passed to it. Negative numbers
	 * will not count the minus sign as a character for the purpose of counting
	 * the digits. Passing 0 returns 1.
	 * 
	 * @param num
	 *            number to count digits of
	 * @return number of digits in the number
	 */
	public static int numDigits(int num) {
		int count = 0;
		do {
			num /= 10;
			count++;
		} while (num > 0);
		return count;
	}
}

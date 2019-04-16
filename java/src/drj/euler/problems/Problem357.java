package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Consider the divisors of 30: 1,2,3,5,6,10,15,30.
 * It can be seen that for every divisor d of 30, d+30/d is prime.
 * <p>
 * <p>Find the sum of all positive integers n not exceeding 100 000 000
 * such that for every divisor d of n, d+n/d is prime.
 */
@Answer("1739023853137")
public class Problem357 extends Problem {

    public static void main(String[] args) {
        Problem p = new Problem357();
        System.out.println(p);
    }

    @Override
    protected String onSolve() {
        long sum = 0;

        Utility.PrimeSieve sieve = new Utility.PrimeSieve(100_000_001);
        outer:
        for (int i = 2; i < 100_000_001; i = (int) sieve.getNextPrime(i)) {
            int num = i - 1;
            for (int j = 1; j < Math.sqrt(i); j++) {
                if (num % j != 0) continue;
                int bigFactor = num / j;
                if (!sieve.isPrime(f(num, j)) || !sieve.isPrime(f(num, bigFactor))) {
                    continue outer;
                }
            }
            sum += num;
        }

        return String.valueOf(sum);
    }

    private static int f(int number, int divisor) {
        return divisor + number / divisor;
    }
}

package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A Harshad or Niven number is a number that is divisible by the sum of its digits.
 * <p>201 is a Harshad number because it is divisible by 3 (the sum of its digits.)
 * <p>When we truncate the last digit from 201, we get 20, which is a Harshad number.
 * <p>When we truncate the last digit from 20, we get 2, which is also a Harshad number.
 * <p>Let's call a Harshad number that, while recursively truncating the last digit, always
 * results in a Harshad number a <i>right truncatable Harshad number</i>.
 * <p>
 * <p>Also:
 * <p>201/3=67 which is prime.
 * <p>Let's call a Harshad number that, when divided by the sum of its digits,
 * results in a prime a <i>strong Harshad number</i>.
 * <p>
 * <p>Now take the number 2011 which is prime.
 * <p>When we truncate the last digit from it we get 201, a strong Harshad number that is also right truncatable.
 * <p>Let's call such primes <i>strong, right truncatable Harshad primes</i>.
 * <p>
 * <p>You are given that the sum of the strong, right truncatable Harshad primes less than 10000 is 90619.
 * <p>
 * <p>Find the sum of the strong, right truncatable Harshad primes less than 10^14.
 */
@Answer("696067597313468")
public class Problem387 extends Problem {

    public static void main(String[] args) {
        Problem p = new Problem387();
        System.out.println(p);
    }

    @Override
    protected String onSolve() {
        TruncatableHarshadTree tree = new TruncatableHarshadTree(13);
        List<Long> truncatables = tree.getValues();
        long sum = 0;
        for (Long truncatable : truncatables) {
            if (isStrongHarshad(truncatable)) {
                for (int suffix : new int[]{1, 3, 7, 9}) {
                    long candidate = truncatable * 10 + suffix;
                    if (Utility.isPrime(candidate)) {
                        sum += candidate;
                    }
                }
            }
        }
        return String.valueOf(sum);
    }

    public static class TruncatableHarshadTree {
        private List<TruncatableHarshadTree> children = new ArrayList<>();
        private Long number;

        public TruncatableHarshadTree(int depth) {
            this(depth, null);
        }

        private TruncatableHarshadTree(int depth, Long number) {
            this.number = number;
            if (number == null) {
                for (int i = 1; i < 10; i++) {
                    children.add(new TruncatableHarshadTree(depth - 1, (long) i));
                }
            } else if (depth >= 1) {
                for (int i = 0; i < 10; i++) {
                    long candidate = number * 10 + i;
                    if (isHarshad(candidate)) {
                        children.add(new TruncatableHarshadTree(depth - 1, candidate));
                    }
                }
            }
        }

        public List<Long> getValues() {
            List<Long> vals = new LinkedList<>();
            addValues(vals);
            return vals;
        }

        private void addValues(List<Long> list) {
            for (TruncatableHarshadTree tree : children) {
                list.add(tree.number);
                tree.addValues(list);
            }
        }
    }

    private static boolean isHarshad(long num) {
        int digitSum = sumDigits(num);
        return num % digitSum == 0;
    }

    private static boolean isStrongHarshad(long num) {
        int digitSum = sumDigits(num);
        return num % digitSum == 0 && Utility.isPrime(num / digitSum);
    }

    private static int sumDigits(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}

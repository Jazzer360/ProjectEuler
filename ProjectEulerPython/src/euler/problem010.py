from euler.util import primes
prime_sum = 0
for prime in primes(2000000):
    prime_sum += prime
print prime_sum

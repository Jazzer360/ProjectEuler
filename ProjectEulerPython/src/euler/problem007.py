from util import primes
for nth, prime in enumerate(primes()):
    if nth == 10000:
        print(prime)
        break
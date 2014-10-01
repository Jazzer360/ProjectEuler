def primes(limit=float("+inf")):
    composites = {}
    num = 2
    while True:
        if num not in composites:
            yield num
            composites[num * num] = [num]
        else:
            for prime in composites[num]:
                composites.setdefault(prime + num, []).append(prime)
            del composites[num]
        num += 1
        if num > limit:
            break

def prime_factorization(num):
    factors = []
    if (num < 2):
        return factors
    for prime in primes():
        count = 0
        while num % prime == 0:
            num = num / prime
            count += 1
        if count > 0:
            factors.append((prime, count))
        if (num == 1):
            return factors
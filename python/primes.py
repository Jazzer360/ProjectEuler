import math
from bitarray import bitarray


def primes(block_size=10000):
    yield 2
    sieve = bitarray(block_size)
    sieve.setall(True)
    sieve[0] = False
    _sieve_array(sieve)
    n = 3
    sieve_max = block_size * 2 - 1
    while True:
        while n <= sieve_max:
            if sieve[n // 2]:
                yield n
            n += 2
        sieve.extend([True] * block_size)
        sieve_max = len(sieve) * 2 - 1
        _sieve_array(sieve, len(sieve) - block_size)


def is_prime(num):
    if num < 2:
        return False
    elif num < 4:
        return True
    elif num % 2 == 0 or num % 3 == 0:
        return False
    elif num < 9:
        return True
    for n in range(5, int(math.sqrt(num)) + 1, 6):
        if num % n == 0 or num % (n + 2) == 0:
            return False
    return True


def _sieve_array(arr, start_index=0):
    print('sieving', start_index)
    for index in range((len(arr) - 2) // 3 + 1):
        if arr[index]:
            n = index * 2 + 1
            offset = (start_index - index) % n
            if offset:
                beg = start_index - (start_index - index) % n + n
            else:
                beg = start_index
            if beg == index:
                beg += n
            arr[beg::n] = False

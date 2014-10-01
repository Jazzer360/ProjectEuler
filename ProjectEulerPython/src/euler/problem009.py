from operator import mul
def is_triplet(a,b,c):
    return a ** 2 + b ** 2 == c ** 2

def triplets():
    for a in xrange(1, 333):
        for b in xrange(a + 1, (1000 - a - 1) // 2 + 1):
            c = 1000 - a - b
            yield (a, b, c)

for triplet in triplets():
    if is_triplet(*triplet):
        print reduce(mul, triplet)
        break

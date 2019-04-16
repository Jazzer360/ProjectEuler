def is_triplet(a, b, c):
    return a ** 2 + b ** 2 == c ** 2


def triplets():
    for a in range(1, 333):
        for b in range(a + 1, (1000 - a - 1) // 2 + 1):
            c = 1000 - a - b
            yield (a, b, c)

for triplet in triplets():
    if is_triplet(*triplet):
        product = 1
        for n in triplet:
            product *= n
        print(product)
        break

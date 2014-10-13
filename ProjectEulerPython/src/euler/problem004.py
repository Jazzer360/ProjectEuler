r = range(999, 99, -1)
max_prod = 0
for n1 in r:
    for n2 in r:
        prod = n1 * n2
        if prod < max_prod:
            break
        if str(prod) == str(prod)[::-1]:
            max_prod = prod
print(max_prod)
fib = [1, 2]
while fib[-1] < 4000000:
    fib.append(fib[-1] + fib[-2])
print(sum(filter(lambda n: n % 2 == 0, fib)))

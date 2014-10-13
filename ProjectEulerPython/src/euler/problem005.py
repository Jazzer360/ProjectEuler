def div_by_range(num):
    for n in range(19,1,-1):
        if num % n:
            return False
    return True

step = 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19
num = step
while not div_by_range(num):
    num += step
print(num)

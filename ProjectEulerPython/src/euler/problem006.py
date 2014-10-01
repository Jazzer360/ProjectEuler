nums = xrange(1, 101)
squares = [n ** 2 for n in nums]
print sum(nums) ** 2 - sum(squares)

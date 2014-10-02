nums = xrange(1, 101)
print sum(nums) ** 2 - sum(n ** 2 for n in nums)

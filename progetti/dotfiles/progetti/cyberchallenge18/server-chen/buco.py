import xorshift
def lotto(seed):
        rand = xorshift.XorShift32(seed)
        numbers = []
        for i in range(10):
            while True:
                candidate = rand.random() % 90 + 1
                if candidate not in numbers:
                    numbers.append(candidate)
                    break
        numbers.sort()

        print(numbers)

        return rand.random()



# for s in range(1, 100000):
    # if (lotto(s)):
        # print("FATTO: " + s)

s = 4237735981

for i in range(10):
    s = lotto(s)


# def tra(x):
    # if x == '🍎':
        # return 0
    # if x == '🍑':
        # return 1
    # if x == '🍒':
        # return 2
    # if x == '🍓':
        # return 3

# while True:
    # a, b, c = input().split()
    # print(tra(a), tra(b), tra(c))

# while True:
    # a = input()

x = input()

n = []
for a in x:
    n += [int(a)]
    if len(n) == 32:
        num = 0
        for i,l in enumerate(n):
            num += (l << (31 - i))
        print(num)
        # f.write(str(num)+"\n")
        n = []


# 0011100110110111101001100101010010110011011000001110000101010010001110001000111011110111001100101100111001001100001011011100001110001011000001110001010011110100110100011100100001101011010000111000010110101100100100001011000100011111111001001110001001011101

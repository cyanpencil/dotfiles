def lcg32(x):
    # parametri usati da glibc
    a = 1103515245
    c = 12345
    x = a * x + c
    return x % 2**32

class Lcg32():
    def __init__(self, seed):
        self.state = seed
    
    def random(self):
        self.state = lcg32(self.state)
        return self.state
    def seed(self):
        return self.state
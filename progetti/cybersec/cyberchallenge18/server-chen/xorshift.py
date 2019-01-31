def xorshift32(x):
    x ^= (x << 13) % 2**32;
    x ^= x >> 17 % 2**32;
    x ^= (x << 5) % 2**32;
    return x % 2**32

class XorShift32():
    def __init__(self, seed):
        self.state = seed
    
    def random(self):
        self.state = xorshift32(self.state)
        return self.state
    def seed(self):
        return self.state
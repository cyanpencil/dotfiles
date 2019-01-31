struct T {
    int x, y;
};

int sum(struct T t){
        return t.x + t.y;
}

struct T foo(int x, int y) {
    struct T t;
    t.x = x;
    t.y = y;
    return t;
}

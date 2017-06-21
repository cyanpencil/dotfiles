#include <fstream>
#include <math.h>

using namespace std;

int main() {
    int x, y, c;
    while (scanf(" %d %d %d", &y, &x, &c) == 3 &&
            (x != 0 || y != 0 || c != 0)) {
        bool pari_x = (x % 2 == 0);
        bool pari_y = (y % 2 == 0);
        int a = max(0, (int)ceil((x-7-(c ^ pari_x)) / 2.0));
        int b = max(0, (int)ceil((y-7) / 2.0));
        int e = max(0, (int)ceil((x-7-((1-c)^pari_x)) / 2.0));
        int d = max(0, (int)ceil(((y - 1) - 7 ) / 2.0 ));
        //printf("%d %d %d %d\n", a,b,e,d);
        printf("%d\n", a*b + e*d);
    }
}

#include <fstream>
#include <math.h>
using namespace std;

long long sum(long long n) {
    if (n == -1) return 0;
    if (n == 0) return 0;
    if (n == 1) return 1;
    return ((1 + (1 + 2*(n-1))) * n) / 2;
}

pair<long long,long  long> getcoord(long long n) {
    long long x, y;
    long long k = n + 1;
    //y = ceil(sqrt(n + .01)) - 1;
    //for (long long i = 0; i <= 2*n + 1; i++) {
        //long long s = sum(i);
        //if (s >= k) {
            //y = i - 1;
            //break;
        //}
    //}
    long long lo = 0, hi = 300000;
    while (lo <= hi) {
        long long mid = (hi + lo) / 2;
        if (sum(mid) >= k && sum(mid - 1) < k) {
            y = mid - 1; break;
        }
        else if (sum(mid) < k) lo = mid + 1;
        else hi = mid - 1;
    }
    long long length = 1 + 2*y;
    x = (k - sum(y + 1) + (length/2));
    //printf("Coordinate di %d: (%d, %d), la lunghezza del livello è %d\n", n, x, y, length);
    return pair<long long,long  long>(x, y);
}

bool is_upside(long long n, long long y_n) {
    return (n % 2 == 0) ^ (y_n % 2 == 0);
}

int main() {
    //freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
    long long s, e;
    while (scanf(" %d %d", &s, &e) == 2 && s >= 0 && e >= 0) {
        pair<long long, long long> p_s = getcoord(s), p_e = getcoord(e);
        long long s_x =  p_s.first, s_y = p_s.second;
        long long e_x =  p_e.first, e_y = p_e.second;
        //printf("(%lld, %lld), (%lld, %lld)\n", s_x, s_y, e_x, e_y);
        double p_s_x = s_x / 2.0;
        double p_e_x = e_x / 2.0;
        double p_s_y = (sqrt(3) / 2.0) * s_y + (sqrt(3) / 6.0) * (is_upside(s, s_y) ? 1 : 2);
        double p_e_y = (sqrt(3) / 2.0) * e_y + (sqrt(3) / 6.0) * (is_upside(e, e_y) ? 1 : 2);
        //printf("(%f, %f), (%f, %f)\n", p_s_x, p_s_y, p_e_x, p_e_y);
        printf("%.3lf\n", sqrt((p_s_x - p_e_x)*(p_s_x - p_e_x) + (p_s_y - p_e_y)*(p_s_y - p_e_y)));
    }
}

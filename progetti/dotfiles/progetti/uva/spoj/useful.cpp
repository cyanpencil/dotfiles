#include <fstream>
#include <vector>
#include <math.h>
#include <cstring>
#include <bitset>
#include <iostream>
using namespace std;
typedef long long ll;

vector<int> primes;
ll sieve_size;
bitset<10000010> bs;

void sieve2(ll ubound) {
    sieve_size = ubound + 1;
    bs.set();
    bs[0] = 0; bs[1] = 0;
    for (ll i = 2; i <= sieve_size; i++) if (bs[i]) {
        for (ll j = i * i; j <= sieve_size; j += i) bs[j] = 0;
        primes.push_back((int) i);
    }
}

void sieve(int n) {
    bool p[n + 1];
    memset(p, 0, sizeof p);
    p[0] = 1; p[1] = 1;
    for (int i = 2; i < n; i++) {
        if (p[i] == 0) {
            int a = i;
            while (a < n) {
                p[a] = 1;
                a += i;
            }
            primes.push_back(i);
        }
    }
}

bool isPrime(ll n) {
    if (n <= sieve_size) return bs[n];
    for (int i = 0; i < primes.size(); i++) 
        if (n % primes[i] == 0) return false;
    return true;
}

ll gcd(ll a, ll b) {return b == 0 ? a : gcd(b, a % b);}
int lcm(int a, int b) {return a * (b / gcd(a, b));}

vector<int> primeFactors(ll n) {
    vector<int> factors;
    ll pf = 2, i = 0;
    while (pf * pf <= n) {
        pf = primes[i++];
        while (n % pf == 0) {n /= pf; factors.push_back(pf);}
    }
    if (n != 1) factors.push_back(n);
    return factors;
}

vector<int> primeFactors2(ll n) {
    vector<int> factors;
    ll pf_index = 0, pf = primes[pf_index];
    while (pf * pf <= n) {
        while (n % pf == 0) {n /= pf; factors.push_back(pf);}
        pf = primes[++pf_index];
    }
    if (n != 1) factors.push_back(n);
    return factors;
}

ll polland_rho(ll n) {
    ll y = 2, x = 2, cycle = 2, factor = 1;
    while (factor == 1) {
        for (ll i = 1; i <= cycle && factor <= 1; i++) {
            x = (x * x + 1) % n;
            factor = gcd(x - y, n);
        }
        cycle *= 2;
        y = x;
        printf("%lld, %lld, %lld\n", x, y, cycle);
    }
    return factor;
}

ll num_prime_factors(ll n) {
    ll pf_idx = 0, pf = primes[pf_idx], sol = 0;
    while (pf * pf <= n) {
        while (n % pf == 0) {n /= pf; sol++;}
        pf = primes[++pf_idx];
    }
    if (n != 1) sol++;
    return sol;
}

ll num_divisors(ll n) {
    ll pf_index = 0, pf = 2, sol = 1, t = 0;
    while (pf * pf <= n) {
        t = 0;
        while (n % pf == 0) {n /= pf; t++;}
        pf = primes[++pf_index];
        sol *= t + 1;
    }
    if (n != 1) sol *= 2;
    return sol;
}

ll sum_divisors(ll n) {
    ll pf_index = 0, pf = 2, sol = 1, t = 0;
    while (pf * pf <= n) {
        t = 0;
        while (n % pf == 0) {n /= pf; t++;}
        sol *= ((pow(pf, t + 1) - 1) / (pf  - 1));
        pf = primes[++pf_index];
    }
    if (n != 1) sol *= (n + 1);
    return sol;
}

ll euler_phi(ll n) {
    ll pf_index = 0, pf = 2, sol = 1, t = 0;
    while (pf * pf <= n) {
        t = 0;
        while (n % pf == 0) {n /= pf; t++;}
        if (t > 0) sol *= (pf - 1) * pow(pf, t - 1);
        pf = primes[++pf_index];
    }
    if (n != 1) sol *= (n - 1);
    return sol;
}

ll euler_phi2(ll n) {
    ll pf_index = 0, pf = 2, sol = n;
    while (pf * pf <= n) {
        if (n % pf == 0) sol -= sol / pf;
        while (n % pf == 0) {n /= pf;}
        pf = primes[++pf_index];
    }
    if (n != 1) sol -= sol / n;
    return sol;
}

vector<int> modified_sieve(ll max_n) { //number of prime factors for numbers in range 1...max_n
    int numPrimeFactors[max_n + 1];
    memset(numPrimeFactors, 0, sizeof numPrimeFactors);
    for (int i = 2; i < max_n; i++) 
        if (numPrimeFactors[i] == 0) //is prime
            for (int j = i; j < max_n; j+=i) 
                numPrimeFactors[j]++;
}

int x = 4, y = 3, d = 0;
//mette nelle variabili x, y la soluzione dell'equazione ax + by = 1;
void extendedEuclid(int a, int b) {
    if (b == 0) {x = 1; y = 0; d = a; return;}
    extendedEuclid(b, a % b);
    int x1 = y;
    int y1 = x - (a / b) * y;
    x = x1;
    y = y1;
}

int f(int x) {
    return x + 1;
}

pair<int, int> floyd_cycle(int x0) {
    int T = f(x0), H = f(f(x0));
    while (T != H) {T = f(T); H = f(f(H));}
    int mu = 0; H = x0;
    while (T != H) {T = f(T); H = f(H); mu++;}
    int lambda = 1; H = f(T);
    while (T != H) {H = f(H); lambda++;}
    return pair<int,int>(mu, lambda);
}

int main() {
    sieve(1000000);
    //printf("%d ", isPrime(136117223861LL));
    //printf("%d", gcd(10, 5));
    //for (int i = 0; i < 10000; i++) {
    //vector<int> f = primeFactors(136117223861LL);
    //for (int i = 0; i < f.size(); i++) printf("%d ", f[i]);
    //}
    //printf("The factor is %lld.\n", polland_rho(136117223861LL));
    //printf("Num prime factors: %lld\n", num_prime_factors(40));
    //printf("Sum divisors: %lld\n", sum_divisors(72));
    //printf("Euler phi: %lld\n", euler_phi(99));
    extendedEuclid(27, 10);
    printf("Extended euclid: x:%d, y:%d\n", x, y);
    cout << HUGE_VAL << endl;
}

// -------  CLOSEST POINTS ---------

int N; // number of points
struct pt{
    double x;
    double y;
} A[40001];
struct obj1{
    bool operator() (pt a, pt b){ if (a.x==b.x) {return a.y<b.y;} else {return a.x<b.x;}}
} compx;
struct obj2{
    bool operator() (pt a, pt b){ if (a.y==b.y) {return a.x<b.x;} else {return a.y<b.y;}}
} compy;

double dist(pt a, pt b){
    return sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
}

multiset<pt,obj2> S; // needs to be multiset, since points with same coords are allowed
double d;

int main(){
    S.clear();
    // read input
    scanf("%d",&N);
    if (!N) return 0;
    for (int i=0; i<N; i++) scanf("%lf %lf",&(A[i].x),&(A[i].y)); 
    sort(A,A+N,compx); // A. order my points by the x coordinate
    // B. multiset<pt,obj2> S: maintain an active set of points - candidates to be a closest-pair with the current point.
    //    the set is ordered by the y coordinate
    int back=0;
    d=dist(A[0],A[1]);
    S.insert(A[0]);  S.insert(A[1]);
    // C. line sweep the points by x order
    for (int i=2; i<N; i++){ 
        S.insert(A[i]); // insert new point into 'event set'
        multiset<pt,obj2>::iterator pos=S.find(A[i]),tmp;
        // C-1. scan the active set for points that may be closer to the current point than the shortest distance
        // compare with ones above A[i]
        tmp=pos; tmp++;
        while (tmp!=S.end() && (tmp->y-pos->y)<d){
            d=min(dist(*tmp,*pos),d);
            tmp++;}
        // compare with ones below A[i]
        tmp=pos;
        if (tmp!=S.begin()){
            tmp--;
            while ((pos->y-tmp->y)<d){
                d=min(d,dist(*tmp,*pos));
                if (tmp==S.begin()) break;
                tmp--;}
        }
        // C-2. Update the active set - keep only the points whose distance with the current point is at most the shortest distance
        for (;A[i].x-A[back].x>d && back<i; back++)
            S.erase(S.find(A[back]));
    }
    printf("%.6lf\n",d);
}


////------------- END OF CLOSEST POINTS


//// -------  CLOSEST POINTS 2 -----------
//typedef complex<double> pnt;   // stores X in real part, Y in imaginary part

//struct compare_x  { // orders by X, breaking ties by Y{ 
    //bool operator()(const pnt &a, const pnt &b) const {
        //if (a.real() != b.real()) return a.real() < b.real();
        //else return a.imag() < b.imag(); }
//};

//struct compare_y  { // orders by Y, breaking ties by X 
    //bool operator()(const pnt &a, const pnt &b) const {
        //if (a.imag() != b.imag()) return a.imag() < b.imag();
        //else return a.real() < b.real(); }
//};

//// Returns the closest distance between two points in S
//static double closest(const vector<pnt> &S) {
    //int N = S.size(); double h = HUGE_VAL;
    //vector<pnt> Sx = S;     // to be sorted by X coordinate
    //set<pnt, compare_y> Sy; // active points, ordered by Y coordinate
    //int tail = 0;           // points in Sx in the range [tail, i) are in Sy
    //sort(Sx.begin(), Sx.end(), compare_x());
    //for (int i = 0; i < N; i++) {
        //// erase points whose X value is too small to even consider
        //while (Sx[i].real() - Sx[tail].real() > h) {
            //Sy.erase(Sx[tail]);
            //tail++;
        //}

        //set<pnt, compare_y>::const_iterator y1, y2; // range in Sy within h of current point
        //y1 = lower_bound(Sy.begin(), Sy.end(), pnt(-HUGE_VAL, Sx[i].imag() - h), compare_y());
        //y2 = upper_bound(Sy.begin(), Sy.end(), pnt( HUGE_VAL, Sx[i].imag() + h), compare_y());

        //for (set<pnt, compare_y>::const_iterator j = y1; j != y2; ++j)
            //h = min(h, abs(Sx[i] - *j));

        //Sy.insert(Sx[i]);  // add current point to active set
    //}
    //return h;
//}


// ---------- END OF CLOSEST POINTS 2 --------------
// ---------- KNUTT MORRIS PRATT -------------
//char P[MAX],T[MAX];
//int m,n,b[MAX+1];

//void kmpPreprocess(){
    //int j=0;
    //b[0]=-1;
    //b[1]=0;
    //for (int i=1; i<m; i++){
        //while (j>=0 and P[i]!=P[j]) j=b[j];
        //j++;
        //b[i+1]=j;
    //}
//}

//void kmpSearch(){
    //int j=0;
    //for (int i=0; i<n; i++){
        //while (j>=0 and T[i]!=P[j]) j=b[j];
        //j++;
        //if (j==m){
            //printf("%d\n",i+1-j);
            //j=b[j];
        //}
    //}
//}

//int main(){
    //scanf("%s %s",&T,&P);
    //m=strlen(P);
    //n=strlen(T);
    //kmpPreprocess();
    //kmpSearch();
//}
//// --------- END OF KNUTT MORRIS PRATT









//int f(int x) {
    //return x + 1;
//}

//pair<int, int> floyd_cycle(int x0) {
    //int T = f(x0), H = f(f(x0));
    //while (T != H) {T = f(T); H = f(f(H));}
    //int mu = 0; H = x0;
    //while (T != H) {T = f(T); H = f(H); mu++;}
    //int lambda = 1; H = f(T);
    //while (T != H) {H = f(H); lambda++;}
    //return pair<int,int>(mu, lambda);
//}


//int x = 4, y = 3, d = 0;
////mette nelle variabili x, y la soluzione dell'equazione ax + by = 1;
//void extendedEuclid(int a, int b) {
    //if (b == 0) {x = 1; y = 0; d = a; return;}
    //extendedEuclid(b, a % b);
    //int x1 = y;
    //int y1 = x - (a / b) * y;
    //x = x1;
    //y = y1;
//}

class UnionFind {
    vector<int> p, rank;
    UnionFind(int N) {rank.assign(N, 0);
        p.assign(N, 0); for (int i = 0; i < N; i++) p[i] = i;}
    int findSet(int i) {return (p[i] == i) ? i : (p[i] = findSet(p[i]));}
    bool isSameSet(int i, int j) {return findSet(i) == findSet(j);}
    void unionSet(int i, int j) {
        if (!isSameSet(i,j)) {
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) p[y] = x;
            else {
                p[x] = y;
                if (rank[x] == rank[y]) rank[y]++;
            }
        }
    }
}

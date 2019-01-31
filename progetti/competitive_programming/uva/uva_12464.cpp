#include <fstream>
#include <iostream>
#include <math.h>
#include <vector>
#include <string>
using namespace std;
typedef long long ll;

ll voti[1000000];

ll gcd(ll a, ll b) {return b == 0? a : gcd(b, a % b);}

class frac {
    public:
        ll a,b;
        frac(ll A, ll B) {
            a = A;
            b = B;
        }
        
        void reduce() {
            ll d = gcd(a,b);
            a /= d;
            b /= d;
        }

        void print() {
            printf("(%lld/%lld)", a, b);
        }

        frac mul(frac c) {
            frac d(a * c.a, b * c.b);
            d.reduce();
            return d;
        }

        frac div(frac c) {
            frac d(a * c.b, b * c.a);
            d.reduce();
            return d;
        }

        bool equals(frac c) {
            if (a == c.a && b == c.b)
                return true;
            return false;
        }
};

frac f(frac a, frac b) {
    frac c(b.a, b.b);
    c.a += b.b;
    //cout << "Frazione con +1: "; c.print(); cout << endl;
    c.reduce();
    //cout << "Frazione ridotta: "; c.print(); cout << endl;
    frac d = c.div(a);
    //cout << "Frazione divisa: "; d.print(); cout << endl;
    d.reduce();
    //cout << "Frazione divisa ridotta: "; d.print(); cout << endl;
    return d;
}

frac last_frac(0,0);

vector<frac> history;

frac next(ll a) {
    if (history.size() > a + 1) return history[a + 1];
    frac r = f(history[a - 1], history[a]);
    history.push_back(r);
    return r;
}

pair<ll, ll> floyd_cycle(ll x0) {
    ll t_idx = x0, h_idx = x0;
    frac T = next(t_idx); t_idx++;
    frac H = next(h_idx); h_idx++; 
    H = next(h_idx); h_idx++; 
    while (!(T.equals(H))) {
            T = next(t_idx); t_idx++;
            H = next(h_idx); h_idx++; 
            H = next(h_idx); h_idx++; 
        }
    ll mu = 0; h_idx = x0;
    while (!(T.equals(H))) {
        T = next(t_idx); t_idx++;
        H = next(h_idx); h_idx++;
        mu++;
        }
    ll lambda = 1; H = next(t_idx); h_idx = t_idx + 1;
    while (!(T.equals(H))) {
        H = next(h_idx); h_idx ++;
        lambda++;
        }
    return pair<ll,ll>(mu, lambda);
}

int main() {
    ll a = 1, b = 1, c = 1;


    //pair<ll,ll> sol = floyd_cycle(1);
    //cout << sol.first << "  " << sol.second;

    //frac A(7, 1);
    //frac B(4, 1);
    //last_frac = A;
    //frac d = next(next(next(B)));
    //d.print();
    //A.print();
    //B.print();

    //frac f(10, 20);
    //f.reduce();
    //f.print();


    cin >> a >> b >> c;
    while (a != 0 || b != 0 || c != 0) {
        history.clear();
        history.push_back(frac(a,  1));
        history.push_back(frac(b, 1));

        for (int i = 1; i < 10; i++) next(history.size() - 1);
        //for (int i = 0; i < 10; i++) history[i].print();
        //cout << endl << a << b << c << endl;
        cout << history[(c % 5) + 0].a << endl;
        cin >> a >> b >> c;
    }
}

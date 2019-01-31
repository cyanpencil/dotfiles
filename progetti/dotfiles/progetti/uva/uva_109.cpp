
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

double EPS = 0.0000000001;

struct vec { double x, y; };

struct point {double x, y;};

double dot(vec a, vec b) {
    return (a.x * b.x + a.y * b.y);
}
double norm_sq(vec v) {
    return v.x * v.x + v.y * v.y;
}

vec toVec(point a, point b) { // vector a‐>b
    vec v;
    v.x = b.x - a.x;
    v.y = b.y - a.y;
    return v;
}

double angle(point a, point o, point b) {
    vec oa = toVec(o, a), ob = toVec(o, b);
    return acos(dot(oa, ob) / sqrt(norm_sq(oa) * norm_sq(ob)));
}

//vec scale(vec v, double s) { // nonnegative s return
    //double a = v.x;
    //double b = v.y;
    //toVec(a * s, b * s);
//}


double cross(vec a, vec b){return a.x * b.y - a.y * b.x; }

bool ccw(point p, point q, point r) {
    return cross(toVec(p, q), toVec(p, r)) > 0; 
}

bool inPolygon(point pt, const vector<point> &P) {
    if ((int)P.size() == 0) return false;
    double sum = 0; // assume first vertex = last vertex
    for (int i = 0; i < (int)P.size()-1; i++) {
        if (ccw(pt, P[i], P[i+1]))
            sum += angle(P[i], pt, P[i+1]);// ccw
        else 
            sum -= angle(P[i], pt, P[i+1]);
    } //cw
    return fabs(fabs(sum) - 2*M_PI) < EPS; 
}

double area(const vector<point> &P) {
    double result = 0.0, x1, y1, x2, y2;
    for (int i = 0; i < (int)P.size()-1; i++) {
        x1 = P[i].x; x2 = P[i+1].x;
        y1 = P[i].y; y2 = P[i+1].y;
        result += (x1 * y2 - x2 * y1);
    }
    return fabs(result) / 2.0; 
}

double dist(point p1, point p2) {
return hypot(p1.x - p2.x, p1.y - p2.y); }

bool collinear(point p, point q, point r) {
return fabs(cross(toVec(p, q), toVec(p, r))) < EPS; }

point pivot;
bool angleCmp(point a, point b) { // angle‐sorting function
    if (collinear(pivot, a, b)) // special case
        return dist(pivot, a) < dist(pivot, b);
    // check which one is closer
    double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
    double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
    return (atan2(d1y, d1x) - atan2(d2y, d2x)) < 0; 
}
// compare two angles

vector<point> CH(vector<point> P) {





    //return vector<point>();







    // the content of P may be reshuffled
    int i, j, n = (int)P.size();
    if (n <= 3) {
        if (!(P[0].x == P[n-1].x && P[0].y == P[n-1].y)) P.push_back(P[0]);
        return P; // special case, the CH is P itself
    }
    //find P0=point with lowest Y and if tie:rightmost X
    int P0 = 0;
    for (i = 1; i < n; i++)
        if (P[i].y < P[P0].y || (P[i].y == P[P0].y && P[i].x > P[P0].x))
            P0 = i;

    point temp = P[0]; P[0] = P[P0]; P[P0] = temp;
    // swap P[P0] with P[0]
    // second, sort points by angle w.r.t. pivot P0
    pivot = P[0];// use global variable as reference
    sort(++P.begin(), P.end(), angleCmp);//skip P[0]
    // third, the ccw tests
    vector<point> S;
    S.push_back(P[n-1]);S.push_back(P[0]);
    S.push_back(P[1]);
    i = 2; // then, we check the rest

    while (i < n) { // note: N must be >= 3
        j = (int)S.size()-1;
        if (ccw(S[j-1], S[j], P[i]))
        S.push_back(P[i++]); // left turn, accept
        else S.pop_back(); 
    }
    // or pop the top of S until we have a left turn
    return S; 
}

int main() {

    pair<int, int> P[21];
    vector<pair<int, int> > H[21];

    int k = 0;

    int n;
    while(scanf(" %d", &n) == 1) {
        if (n == -1) break;


        int x, y;
        cin >> x >> y;
        P[k] = make_pair(x, y);
        for (int i = 0; i < n - 1; i++ ) {
            int x, y;
            cin >> x >> y;
            H[k].push_back(make_pair(x, y));
        }
        k++;
    }

    vector<point> polygons[k];

    for (int i = 0; i < k; i++) {
        vector<point> prova;
        for (int j = 0; j< H[i].size(); j++) {
            point c;
            c.x = H[i][j].first;
            c.y = H[i][j].second;
            prova.push_back(c);
        }
        point c;
        c.x = P[i].first;
        c.y = P[i].second;
        prova.push_back(c);
        polygons[i] = CH(prova);
    }

    double total = 0;
    bool destroyed[200];
    memset(destroyed, 0, sizeof destroyed);
    int ax, ay;
    while(scanf(" %d %d", &ax, &ay) == 2) {
        point p;
        p.x = ax;
        p.y = ay;

        for (int i = 0; i < k; i++) {
            if (!destroyed[i]) {
                if (inPolygon(p, polygons[i])) {
                    total += area(polygons[i]);
                    destroyed[i] = true;
                }
            }

        }


    }

    printf("%.2lf\n", total);

}



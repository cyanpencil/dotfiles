#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

#define EPS 0.000000001
#define PI 3.14159265359

struct point_i {
    int x, y;
    point_i() {x = 0; y = 0;}
    point_i(int _x, int _y) : x(_x), y(_y) {} 
};


struct point {
    double x, y;
    point() {x = 0; y = 0;}
    point(double _x, double _y) : x(_x), y(_y) {}
    bool operator < (point other) const {
        if (fabs(x - other.x) > EPS)
            return x < other.x;
        return y < other.y; 
    }
    bool operator == (point other) const {
        return (fabs(x - other.x) < EPS && (fabs(y - other.y) < EPS)); 
    }
};

struct line {double a, b, c;};

double distPoints(point p1, point p2) {
    return hypot(p1.x - p2.x, p1.y - p2.y);
}

double DEG_to_RAD(double theta) {
    return (theta * PI) / 180.0;
}

point rotatePoint(point p, double theta) {
    double rad = DEG_to_RAD(theta);
    return point(p.x * cos(rad) - p.y * sin(rad), p.x * sin(rad) + p.y * cos(rad));
}

//get the line passing between two points (pass by reference)
void pointsToLine(point p1, point p2, line &l) {
    if (fabs(p1.x - p2.x) < EPS) {
        l.a = 1.0; l.b = 0.0; l.c = -p1.x;
    } else {
        l.a = -(double)(p1.y - p2.y) / (p1.x - p2.x);
        l.b = 1.0;
        l.c = -(double)(l.a * p1.x) - p1.y;
    }
}

bool lines_areParallel(line l1, line l2) {
    return (fabs(l1.a - l2.a) < EPS) && (fabs(l1.b - l2.b) < EPS);
}

bool lines_areSame(line l1, line l2) {
    return lines_areParallel(l1, l2) && (fabs(l1.c - l2.c) < EPS);
}

bool lines_areIntersect(line l1, line l2, point &p) {
    if (lines_areParallel(l1, l2)) return false;
    p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
    if (fabs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
    else                  p.y = -(l2.a * p.x + l2.c);
}

struct vec { 
    double x, y;
    vec(double _x, double _y) : x(_x), y(_y) {} 
};

vec toVec(point a, point b) {
    return vec(b.x - a.x, b.y - a.y);
}

vec scale(vec v, double s) {
    return vec(v.x * s, v.y * s);
}

point translate(point p, vec v) {
    return point(p.x + v.x, p.y + v.y);
}

double dot(vec a, vec b) { return (a.x * b.x + a.y * b.y);}

double norm_sq(vec v) {return v.x * v.x + v.y * v.y;}

//return the distance from p to the line defined by
//two points a and b (that must be differenet)
//the losest point is stored in the 4th parameter
double distToLine(point p, point a, point b, point c) {
    vec ap = toVec(a, p), ab = toVec(a,b);
    double u = dot(ap, ab) / norm_sq(ab);
    c = translate(a, scale(ab, u));
    return distPoints(p, c);
}

//returns the distance from p to the line segment a defined by
//two points a and b (still OK if a == b)
//the closest point is stored in the 4th parameter
double distToLineSegment(point p, point a, point b, point &c) {
    vec ap = toVec(a, p), ab = toVec(a, b);
    double u = dot(ap, ab) / norm_sq(ab);
    if (u < 0.0) { c = point(a.x, a.y);
        return distPoints(p, a); }
    if (u > 1.0) { c = point(b.x, b.y);
        return distPoints(p, b); }
    return distToLine(p, a, b, c); 
}

//Calculates the angle between three points
double angle(point a, point o, point b) {
    vec oa = toVec(o, a), ob = toVec(o, b);
    return acos(dot(oa, ob) / sqrt(norm_sq(oa) * norm_sq(ob)));
}

double cross(vec a, vec b) { return a.x * b.y - a.y * b.x; }

//note: to accept collinear points, we have to change the "> 0"
//return true if point r is on the left side of line pq
bool ccw(point p, point q, point r) {
    return cross(toVec(p, q), toVec(p, r)) > 0;
}

//return true if point r is on the same line as the line pq
bool collinear(point p, point q, point r) {
    return fabs(cross(toVec(p, q), toVec(p, r))) < EPS;
}

int insideCircle(point_i p, point_i c, int r) { //all integer version
    int dx = p.x - c.x, dy = p.y - c.y;
    int Euc = dx * dx + dy * dy, rSq = r * r;
    return Euc < rSq ? 0 : Euc == rSq ? 1 : 2;  //inside/border/outside
}

double perimeterTriangle(double a, double b, double c) {
    return a + b + c;
}

//area del triangolo dati i lati
double areaTriangle(double a, double b, double c) {
    double s = (a + b + c) / 2.0;
    return sqrt(s * (s - a) * (s - b) * (s - c));
}


//ritorna il raggio del cerchio inscritto
double rInCircle(double ab, double bc, double ca) {
    return areaTriangle(ab, bc, ca) / (0.5 * perimeterTriangle(ab, bc, ca));
}

double rInCircle(point a, point b, point c) {
    return rInCircle(distPoints(a, b), distPoints(b, c), distPoints(c, a));
}

double rCircumCircle(double ab, double bc, double ca) {
    return ab * bc * ca / (4.0 * areaTriangle(ab, bc, ca));
}

double rCircumCircle(point a, point b, point c) {
    return rCircumCircle(distPoints(a, b), distPoints(b, c), distPoints(c, a));
}


// returns 1 if there is an incircle center, returns 0 otherwise
// if this function returns 1, ctr will be the incircle center and
// r is its radius
int inCircle(point p1, point p2, point p3, point &ctr, double &r) {
    r = rInCircle(p1, p2, p3);
    if (fabs(r) < EPS) return 0; //no incircle center

    line l1, l2; //the two angle disectors
    double ratio = distPoints(p1, p2) / distPoints(p1, p3);
    point p = translate(p2, scale(toVec(p2, p3), ratio / (1 + ratio)));
    pointsToLine(p1, p, l1);

    ratio = distPoints(p2, p1) / distPoints(p2, p3);
    p = translate(p1, scale(toVec(p1, p3), ratio / (1 + ratio)));
    pointsToLine(p2, p, l2);
    lines_areIntersect(l1, l2, ctr);
    return 1;
}

double perimeter(const vector<point> &P) {
    double result = 0.0;
    for (int i = 0; i < (int) P.size() - 1; i++) //remember that P[0] = P[n - 1]
        result += distPoints(P[i], P[i + 1]);
    return result;
}

double area(const vector<point> &P) {
    double result = 0.0, x1, y1, x2, y2;
    for (int i = 0; i < (int) P.size() - 1; i++) {
        x1 = P[i].x; x2 = P[i + 1].x;
        y1 = P[i].y; y2 = P[i + 1].y;
        result += (x1 * y2 - x2 * y1);
    }
    return fabs(result) / 2.0;
}

bool isConvex(const vector<point> &P) {
    int sz = (int) P.size();
    if (sz <= 3) return false;
    bool isLeft = ccw(P[0], P[1], P[2]);
    for (int i = 1; i < sz - 1; i++) {
        if (ccw(P[i], P[i + 1], P[(i + 2) == sz ? 1 : i + 2]) != isLeft)
            return false;
    }
    return true;
}

// returns true if point p in in either convex/concave polygon P
bool inPolygon(point pt, const vector<point> &P) {
    if ((int)P.size() == 0) return false;
    double sum = 0;
    for (int i = 0; i < (int)P.size() - 1; i++) {
        if (ccw(pt, P[i], P[i + 1]))
            sum += angle(P[i], pt, P[i + 1]);
        else sum -= angle(P[i], pt, P[i + 1]);
    }
    return fabs(fabs(sum) - 2*PI) < EPS;
}

// line segment p-q intersect with line A-B
point lineIntersectSeg(point p, point q, point A, point B) {
    double a = B.y - A.y;
    double b = A.x - B.x;
    double c = B.x * A.y - A.x * B.y;
    double u = fabs(a * p.x + b * p.y + c);
    double v = fabs(a * q.x + b * q.y + c);
    return point((p.x * v + q.x * u) / (u + v), (p.y * v + q.y * u) / (u + v));
}

// cuts polygon Q along the line formed by point a -> point b
// (note: the last point must be the same as the first one)
vector<point> cutPolygon(point a, point b, const vector<point> &Q) {
    vector<point> P;
    for (int i = 0; i < (int)Q.size(); i++) {
        double left1 = cross(toVec(a, b), toVec(a, Q[i])), left2 = 0;
        if (i != (int)Q.size() - 1) left2 = cross(toVec(a, b), toVec(a, Q[i + 1]));
        if (left1 > -EPS) P.push_back(Q[i]); //Q[i] is on the left of ab
        if (left1 * left2 < -EPS)     //edge (Q[i], Q[i+1]) crosses line ab
            P.push_back(lineIntersectSeg(Q[i], Q[i + 1], a, b));
    }
    if (!P.empty() && !(P.back() == P.front()))
        P.push_back(P.front());
    return P;
}

point pivot(0,0);
bool angleCmp(point a, point b) { //angle sorting function
    if (collinear(pivot, a, b)) //special case
        return distPoints(pivot, a) < distPoints(pivot, b); //check which one is closer
    double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
    double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
    return (atan2(d1y, d1x) - atan2(d2y, d2x)) < 0; //compare two angles
}

vector<point> CH(vector<point> P) {
    int i, j, n = (int) P.size();
    if (n <= 3) {
        if (!(P[0] == P[n - 1])) P.push_back(P[0]); //safeguard from corner case
        return P;
    }
    //first, find PO = point with lowest Y and if tiw: rightmost X
    int PO = 0;
    for (i = 1; i < n; i++) 
        if (P[i].y < P[PO].y || (P[i].y == P[PO].y && P[i].x > P[PO].x))
            PO = i;
    
    point temp = P[0]; P[0] = P[PO]; P[PO] = temp; //swap P[PO] with P[0]
    //second, sort points by angle w.r.t. pivot PO
    pivot = P[0];
    sort(++P.begin(), P.end(), angleCmp); //we do not sort P[0]

    //third, the ccw tests
    vector<point> S;
    S.push_back(P[n -1]); S.push_back(P[0]); S.push_back(P[1]);
    i = 2;
    while (i < n) { //N must be >= 3
        j = (int) S.size() - 1;
        if (ccw(S[j - 1], S[j], P[i])) S.push_back(P[i++]); //left turn, accept
        else S.pop_back(); //or pop the top of S until we have a left turn
    }
    return S;
}

int main() {
    //P.push_back(point(5,5));
    //sort(P.begin(), P.end());
    //for (int i = 0; i < P.size(); i++) cout << P[i].x << " - " << P[i].y << endl;
    ////cout << distPoints(P[3], P[1]);
    //cout << endl;
    //point p = rotatePoint(P[2], 90);
    //cout << p.x << " - " << p.y;
    //cout << c.x;
    vector<point> P;
    P.push_back(point(10,10));
    P.push_back(point(20,20));
    P.push_back(point(5,5));
    P.push_back(P[0]);
}

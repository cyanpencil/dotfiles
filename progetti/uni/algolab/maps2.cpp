#include <iostream>
#include <vector>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Exact_predicates_exact_constructions_kernel.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel IK;
typedef IK::Point_2 IP;

typedef CGAL::Exact_predicates_exact_constructions_kernel EK;
typedef EK::Point_2 EP;
typedef EK::Triangle_2 ET;
typedef EK::Line_2 EL;
typedef EK::Segment_2 ES;

using namespace std;

EP intersect(EL L1, EL L2) {
    auto o = CGAL::intersection(L1, L2);
    if (const EP* op = boost::get<EP>(&*o)) {
	return *op;
    }
    else // how could this be? -> error
	throw std::runtime_error("strange segment intersection");
}

void test() {
    int n, m;
    cin >> m >> n;
    vector<EP> EPoints;
    for (int i = 0; i < m; i++) {
	int x, y; cin >> x >> y;
	EPoints.push_back(EP(x, y));
    }
    vector<ET> ETriangles;
    for (int i = 0; i < n; i++) {
	int c[12];
	for (int j = 0; j < 12; j++) { int x; cin >> x; c[j] = x; }
	EP P1(c[0], c[1]), P2(c[2],c[3]), P3(c[4], c[5]), P4(c[6], c[7]), P5(c[8], c[9]), P6(c[10], c[11]);
	EL L1(P1, P2), L2(P3, P4), L3(P5, P6);
	EP I1 = intersect(L1, L2); 
	EP I2 = intersect(L2, L3);
	EP I3 = intersect(L1, L3);
	ETriangles.push_back(ET(I1, I2, I3));
    }
    int i = 0, j = 0;
    vector<ES> segs;
    for (int i = 0; i < m -1; i++) {
	segs.push_back(ES(EPoints[i], EPoints[i+1]));
    }
    vector<ES> missing;
    for (auto a: segs) missing.push_back(a);
    int b = 0, e = n;
    while(i < n) {
	for (int k = missing.size() -1; k >= 0; k--) {
	    if (ETriangles[j].has_on_unbounded_side(missing[k].source()) || 
	      ETriangles[j].has_on_unbounded_side(missing[k].target())) continue;
	    missing.erase(missing.begin() + k);
	}
	if (missing.size() == 0) {
	    if ((j-i) < (e-b)) {
		b = i; e = j;
	    }
	    for (int z = 0; z < m -1; z++) {
		missing.push_back(ES(EPoints[z], EPoints[z+1]));
	    }
	    i++; j = i;
	} else {
	    j++;
	}
	if (j >= n) {
	    i++; j = i;
	    missing.clear();
	    for (int z = 0; z < m -1; z++) {
		missing.push_back(ES(EPoints[z], EPoints[z+1]));
	    }
	}
    }
    cout << (e - b + 1) << endl;
}

int main() {
    ios::sync_with_stdio(false);
    int t; cin >> t;
    while (t--) test();
}

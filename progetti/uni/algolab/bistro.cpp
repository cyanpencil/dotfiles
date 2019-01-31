#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Edge_iterator  Edge_iterator;


using namespace std;

double ceil_to_double(K::FT a) {
	double x = ceil(CGAL::to_double(a));
	while (x - 1 > a) x--;
	while (x < a) x++;
	return x;
}

int main () {
    int n;
    while (scanf("%d", &n) == 1 && n > 0) {
		int x[n], y[n];
		vector<K::Point_2> pts;
		for (int i = 0; i < n; i++) {
			cin >> x[i] >> y[i];
			pts.push_back(K::Point_2(x[i], y[i]));
		}

		int m; cin >> m;

		Triangulation t;
		t.insert(pts.begin(), pts.end());
		bool never_done = true;
		K::FT ciao;
		for (int i = 0; i < m; i++) {
			int d, z;
			cin >> d >> z;
			K::Point_2 p = K::Point_2(d,z);
			K::Point_2 q = t.nearest_vertex(p)->point();
			long long bis = CGAL::to_double(CGAL::squared_distance(p, q));
			printf("%lld\n", bis);
		}
    }
}

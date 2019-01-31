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
		std::vector<K::Point_2> pts;
		for (int i = 0; i < n; i++) {
			cin >> x[i] >> y[i];
			pts.push_back(K::Point_2(x[i], y[i]));
		}

		Triangulation t;
		t.insert(pts.begin(), pts.end());
		bool never_done = true;
		K::FT ciao;
		for (Edge_iterator e = t.finite_edges_begin(); e != t.finite_edges_end(); ++e) {
			K::Point_2 a = (*e).first->vertex(((*e).second + 1) % 3)->point();
			K::Point_2 b = (*e).first->vertex(((*e).second + 2) % 3)->point();
			auto bis = CGAL::squared_distance(a, b);
			if (never_done || bis < ciao) ciao = bis, never_done=false;
		}
		cout << ceil_to_double(CGAL::sqrt(ciao)/0.02) << endl;
    }
}

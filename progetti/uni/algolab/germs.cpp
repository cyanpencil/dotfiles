#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Edge_iterator  Edge_iterator;
typedef Triangulation::Vertex_iterator  Vertex_iterator;


using namespace std;

long long ceil_to_long(K::FT a) {
	double x = ceil(CGAL::to_double(a));
	while (x - 1 > a) x--;
	while (x < a) x++;
	return (long long)x;
}

int main () {
    int n;
    while (scanf("%d", &n) == 1 && n > 0) {
		if (n == 0) return 0;
		int l,b,R,T;
		cin >> l >> b >> R >> T;
		int x[n], y[n];
		vector<K::Point_2> pts;
		for (int i = 0; i < n; i++) {
			cin >> x[i] >> y[i];
			pts.push_back(K::Point_2(x[i], y[i]));
		}

		vector<long long> uccisione;

		Triangulation t;
		t.insert(pts.begin(), pts.end());

		for (Vertex_iterator e = t.vertices_begin(); e != t.vertices_end(); ++e) {
			Triangulation::Edge_circulator c = t.incident_edges((e));
			bool unset = true;
			K::FT death_time;
			long long final_time = 1 << 30;
			final_time<<=30;
			//cout << "point: " << e->point() << endl;
			if (c != 0) {
				do {
					if (t.is_infinite(c)) {}
					else {
						K::Point_2 a = (*c).first->vertex(((*c).second + 1) % 3)->point();
						K::Point_2 b = (*c).first->vertex(((*c).second + 2) % 3)->point();
						auto bis = CGAL::squared_distance(a, b);
						//cout << "Squared distance between " << a << b << "is " << bis << endl;
						if (unset || bis < death_time) death_time = bis, unset = false;
					}
				} while (++c != t.incident_edges((e)));
			}
			if (!unset) {
				final_time = ceil_to_long(CGAL::sqrt((CGAL::sqrt(death_time) - 1)/2.0));
			}
			//cout << "final time is " << final_time << endl;
			long long xdist = ceil(sqrt(min(abs(e->point().x() - l), abs(e->point().x() - R)) - 0.5));
			long long ydist = ceil(sqrt(min(abs(e->point().y() - b), abs(e->point().y() - T)) - 0.5));
			if (unset || xdist < final_time) final_time = xdist, unset = false;
			if (unset || ydist < final_time) final_time = ydist, unset = false;
			//cout << "final time is " << final_time << endl;
			uccisione.push_back(final_time);
		}
		sort(uccisione.begin(), uccisione.end());
		cout << ceil(uccisione[0]) << " " << ceil(uccisione[uccisione.size()/2]) << " " << ceil(uccisione[uccisione.size()-1]) << endl;
    }
}

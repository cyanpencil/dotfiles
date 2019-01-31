#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Exact_predicates_exact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Exact_predicates_exact_constructions_kernel E;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Edge_iterator  Edge_iterator;

int x[90000], y[90000];

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
		vector<K::Point_2> pts;
		for (int i = 0; i < n; i++) {
			//cin >> x[i] >> y[i];
			scanf(" %d %d" , &x[i], &y[i]);
			pts.push_back(K::Point_2(x[i], y[i]));
		}
		double death[n] = {0};
		int m; cin >> m;

		Triangulation t;
		t.insert(pts.begin(), pts.end());

		unordered_map <Triangulation::Face_handle, K::FT> escape;
		priority_queue<pair<K::FT, Triangulation::Face_handle> > q;

		Triangulation::Face_circulator f = t.incident_faces(t.infinite_vertex());
		do {
			for (int i = 0; i < 3; i++) {

			}
			escape[f] = 1e100;
			q.push(make_pair(1e100, f));
		} while (++f != t.incident_faces(t.infinite_vertex()));

		while(q.size()) {
			Triangulation::Face_handle f = q.top().second;
			K::FT curdist = q.top().first;
			q.pop();
			for (int i = 0; i < 3; i++) {
				Triangulation::Face_handle f2 = f->neighbor(i);
				K::FT dist = CGAL::squared_distance(f->vertex((i+2) % 3)->point(), f->vertex((i+1)%3)->point());
				if (escape.find(f2) == escape.end()) {
					escape[f2] = min(dist, curdist);
					q.push(make_pair(escape[f2], f2));
				} else {
					K::FT really = min(dist, curdist);
					if (really > escape[f2]) {
						escape[f2] = really;
						q.push(make_pair(really, f2));
					}
				}
			}

		}


		for (int i = 0; i < m; i++) {
			long long p_x, p_y, d;
			scanf(" %lld %lld %lld", &p_x, &p_y, &d);
			K::Point_2 p = K::Point_2(p_x,p_y);
			K::Point_2 qq = t.nearest_vertex(p)->point();
			if (CGAL::squared_distance(p, qq) < d) {
				cout << "n" ; continue;
			}
			Triangulation::Face_handle faccia = t.locate(p);

			if (escape[faccia]/4.0 >= d) cout << "y";
			else cout << "n";
		}
		cout << endl;
    }
}

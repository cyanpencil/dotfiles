#include <bits/stdc++.h>
//#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Exact_predicates_exact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

//typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Exact_predicates_exact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Finite_faces_iterator  Face_iterator;

using namespace std;

long long b_s[100000];
long long x,y;
long long r2;
K::FT r;
long long n,m;

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		cin >> n >> m >> r2;
		r = r2;
		vector<K::Point_2> b;
		vector<K::Point_2> a;
		for (int i = 0; i < n; i++) {
			cin >> x >> y;
			K::FT x2 = x, y2 = y;
			a.push_back(K::Point_2(x2,y2));
		}
		for (int i = 0; i < m; i++) {
			cin >> x >> y >> b_s[i];
			K::FT x2 = x, y2 = y;
			b.push_back(K::Point_2(x2,y2));
		}

		Triangulation t;
		t.insert(a.begin(), a.end());

		map<Triangulation::Face_handle, K::FT> distmap;
		priority_queue<pair<K::FT, Triangulation::Face_handle> > q;

		Triangulation::Face_circulator f = t.incident_faces(t.infinite_vertex());
		do {
			distmap[f] = 1e299;
			q.push(make_pair(1e299,f));
		} while (++f != t.incident_faces(t.infinite_vertex()));

		for (Face_iterator f = t.finite_faces_begin(); f != t.finite_faces_end(); ++f) {
			K::Point_2 p = t.circumcenter(f);
			K::FT megadist = 1e299;
			for (int i = 0; i < 3; i++) {
				megadist = min(megadist, CGAL::squared_distance(p, f->vertex(i)->point()));
			}
			distmap[f] = megadist;
			q.push(make_pair(megadist, f));
		}

		while (q.size()) {
			auto ff = q.top().second;
			K::FT dist = q.top().first;
			q.pop();

			if (dist < distmap[ff]) continue;
			//if (dist > 1e299) continue;

			for (int i = 0; i < 3; i++) {
				auto z = ff->vertex((i+1)%3);
				auto zz = ff->vertex((i+2)%3);
				K::FT dist2;
				dist2 = CGAL::squared_distance(z->point(),zz->point());

				auto transmit = min(dist, dist2);
				
				if (transmit > distmap[ff->neighbor(i)]) {
					distmap[ff->neighbor(i)] = transmit;
					q.push(make_pair(transmit, ff->neighbor(i)));
				}
			}
		}

		for (int i = 0; i < b.size(); i++) {
			K::Point_2 p = t.nearest_vertex(b[i])->point();
			K::FT c = b_s[i];
			if (CGAL::squared_distance(p, b[i]) < (r+c)*(r+c)) {cout << "n"; continue;}
			if (distmap[t.locate(b[i])] >= (4*r*r + 4*c*c + 8*r*c)) cout << "y";
			else cout << "n";
		}
		cout << endl;
    }
}

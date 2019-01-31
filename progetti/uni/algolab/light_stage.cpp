#include <bits/stdc++.h>

#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Edge_iterator  Edge_iterator;

using namespace std;

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		//cerr << "vado " << endl;
		int m, n;
		cin >> m >> n;
		int players_x[m], players_y[m], players_r[m];
		K::Point_2 play[m];
		for (int i = 0; i < m; i++) {
			cin >> players_x[i] >> players_y[i] >> players_r[i];
			play[i] = K::Point_2(players_x[i], players_y[i]);

		}
		K::FT h;
		cin >> h;
		vector<K::Point_2> pts;
		for (int i = 0; i < n; i++) {
			int x,y;
			cin >> x >> y;
			pts.push_back(K::Point_2(x,y));
		}

		if (m < 30000) {

		bool zombie[m];
		bool dead[m];
		memset(dead, 0, sizeof dead);
		memset(zombie, 0, sizeof zombie);

		vector<int> stayin_alive;

		int s = 0, e = pts.size();
		while (s != e) {
			//cerr << s << " " << e << endl;
			int mm = (s+e)/2;

			Triangulation t;
			t.insert(pts.begin(), pts.begin() + mm + 1);

			bool all_dead = true;
			for (int i = 0; i < m; i++) {
				K::Point_2 pp = play[i];
				K::Point_2 p = t.nearest_vertex(pp)->point();
				if (CGAL::squared_distance(p, pp) < ((h+players_r[i])*(h+players_r[i]))) {
					//dead[i] = true;
				} else {
					if (all_dead) {
						stayin_alive.clear();
					}
					all_dead = false;
					stayin_alive.push_back(i);
				}
			}

			if (all_dead) {
				e = mm;
			} else {
				s = mm+1;
			}
		}

		for (int i = 0; i < stayin_alive.size(); i++) {
			cout << stayin_alive[i] << " ";
		}
		cout << endl;

		} else {

			Triangulation t;
			t.insert(pts.begin(), pts.end());

		
			for (int i = 0; i < m; i++) {
				K::Point_2 pp = K::Point_2(players_x[i],players_y[i]);
				K::Point_2 p = t.nearest_vertex(pp)->point();
				//cout << CGAL::squared_distance(p, pp) - ((h+players_r[i])*(h+players_r[i])) << endl;
				if (CGAL::squared_distance(p, pp) >= ((h+players_r[i])*(h+players_r[i]))) {
					cout << i << " ";
				}

			}
			cout << endl;

		}
    }
}

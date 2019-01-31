
#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Exact_predicates_exact_constructions_kernel.h>
#include <CGAL/enum.h>



using namespace std;

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Exact_predicates_exact_constructions_kernel E;

int tx[2000][6];
int ty[2000][6];
int px[2000];
int py[2000];

E::Triangle_2 tt[2000];
E::Point_2 points[2000];

bool is_inside (int p, int t) {
	K::Point_2 p1 = K::Point_2(tx[t][0], ty[t][0]);
	K::Point_2 p2 = K::Point_2(tx[t][1], ty[t][1]);
	K::Point_2 p3 = K::Point_2(tx[t][2], ty[t][2]);
	K::Point_2 p4 = K::Point_2(tx[t][3], ty[t][3]);
	K::Point_2 p5 = K::Point_2(tx[t][4], ty[t][4]);
	K::Point_2 p6 = K::Point_2(tx[t][5], ty[t][5]);
	K::Point_2 pp = K::Point_2(px[p], py[p]);
	return CGAL::left_turn(p1,p2,pp) && CGAL::left_turn(p3,p4,pp) && CGAL::left_turn(p5,p6,pp);
}

bool iss(E::Point_2 p, int t) {
    auto a = tt[t].has_on_bounded_side(p);
    auto b = tt[t].has_on_boundary(p);
    return a || b;
}

int main () {
	int t, n, m;
	cin >> t;
	while (t--) {
		cin >> m >> n;
		for (int i = 0; i < m; i++) {
			cin >> px[i] >> py[i];
			points[i] = E::Point_2(px[i], py[i]);
		}
		for (int i = 0; i < n; i++) {
			cin >> tx[i][0] >> ty[i][0];
			cin >> tx[i][1] >> ty[i][1];
			cin >> tx[i][2] >> ty[i][2];
			cin >> tx[i][3] >> ty[i][3];
			cin >> tx[i][4] >> ty[i][4];
			cin >> tx[i][5] >> ty[i][5];
			E::Point_2 p1 = E::Point_2(tx[i][0], ty[i][0]);
			E::Point_2 p2 = E::Point_2(tx[i][1], ty[i][1]);
			E::Point_2 p3 = E::Point_2(tx[i][2], ty[i][2]);
			E::Point_2 p4 = E::Point_2(tx[i][3], ty[i][3]);
			E::Point_2 p5 = E::Point_2(tx[i][4], ty[i][4]);
			E::Point_2 p6 = E::Point_2(tx[i][5], ty[i][5]);

			auto uno = CGAL::intersection(E::Line_2(p1,p2), E::Line_2(p3,p4));
			auto due = CGAL::intersection(E::Line_2(p3,p4), E::Line_2(p5,p6));
			auto tre = CGAL::intersection(E::Line_2(p5,p6), E::Line_2(p1,p2));

			const E::Point_2* op1 = boost::get<E::Point_2>(&*uno);
			const E::Point_2* op2 = boost::get<E::Point_2>(&*due);
			const E::Point_2* op3 = boost::get<E::Point_2>(&*tre);
			tt[i] = E::Triangle_2(*op1, *op2, *op3);

			//for (int l = 0; l < m; l++)
				//cout << tt[i].bounded_side(E::Point_2(px[l], py[l])) << endl;
		}
		int minimun = 9999999;
		bool fatto = true;
		for (int len = 1; len <= n && fatto; len++) {
		    for (int sss = 0; sss <= n - len; sss++) {
			//cout << "provo con " << sss << " e len " << len << endl;
			bool success = true;
			int s = sss;
			E::Point_2 curpoint = points[sss];
			while (true) {
				    if (s >= m-1) break;
				    bool taken = false, total = false, skip = false;;
				    E::Point_2 inters;
				    for (int t = sss; t < sss + len; t++) {
					cout << t << "s: " << s << " sss: " << sss << endl;
					if (iss(curpoint, t)) {
					    taken = true;
					    if (iss(points[s+1], t)) {
						total = true;
					    } else {
						    cout << "ainters " << inters << endl;
						auto uno = CGAL::intersection(E::Segment_2(curpoint,points[s+1]), tt[t]);
						if (const E::Point_2* op1 = boost::get<E::Point_2>(&*uno)) {
						    if (curpoint == (*op1)) {
							continue;
						    }
						    inters = *op1;
						}
						else if (const E::Segment_2* op1 = boost::get<E::Segment_2>(&*uno)) {
						    cout << "yo here " << endl;
						    if ((*op1).source() == curpoint) {
							curpoint = (*op1).target();
							skip = true;
							continue;
						    } else {
							curpoint = (*op1).source();
							skip = true;
							continue;
						    }
						}
						//cout << "inters " << inters << endl;
					    }
					    break;
					}
				    }
				    if (skip) {
					continue;
				    }
				    //cout << "taken  " << taken << " total " << total << endl;
				    if (!taken) {  // se non ci sta nessun 
					success = false;
					break;
				    }
				    if (total) { //se tutto il segmento e' compreso
					s++;
					curpoint = points[s];
					continue;
				    } else { // se solo un segmento e' compreso
					curpoint = inters;
				    }
			}
			if (success) {
			    cout << len << endl;
			    fatto = false;
			    break;
			}
		    }
		}
	}
}


#include <bits/stdc++.h>

#include <iostream>
#include <cassert>
#include <CGAL/QP_models.h>
#include <CGAL/QP_functions.h>
#include <CGAL/Gmpz.h>


#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Finite_faces_iterator  Face_iterator;

using namespace std;

typedef CGAL::Gmpz ET;

// program and solution types
typedef CGAL::Quadratic_program<ET> Program;
typedef CGAL::Quadratic_program_solution<ET> Solution;

typedef CGAL::Quotient<ET> SolT;

double ceil_to_double(const SolT& x)
{
  double a = std::ceil(CGAL::to_double(x));
  while (a < x) a += 1;
  while (a-1 >= x) a -= 1;
  return a;
}

long wx[201], wy[201], wss[201], wa[201];
long sx[21], sy[21], sd[21], su[21];
long r[201][21];
long cx[1000001], cy[1000001], cr[1000001];

int main() {
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	while (t--) {
		int n,m,c;
		vector<K::Point_2> vvv;
		cin >> n >> m >> c;
		for (int i = 0; i < n; i++) {
			cin >> wx[i] >> wy[i] >> wss[i] >> wa[i];
			vvv.push_back(K::Point_2(wx[i], wy[i]));
		}
		for (int i = 0; i < m; i++) {
			cin >> sx[i] >> sy[i] >> sd[i] >> su[i];
			vvv.push_back(K::Point_2(sx[i], sy[i]));
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> r[i][j];
			}
		}

		for (int i = 0; i < c; i++) {
			cin >> cx[i] >> cy[i] >> cr[i];
		}

		Triangulation t;
		t.insert(vvv.begin(), vvv.end());

		vector<int> real_c;

		for (int i = 0; i < c; i++) {
			K::Point_2 pp(cx[i], cy[i]);
			auto p = t.nearest_vertex(pp)->point();
			if (CGAL::squared_distance(pp, p) < cr[i]*cr[i]) {
				real_c.push_back(i);
			}
		}



		Program lp (CGAL::SMALLER, true, 0, false, 0); //TODO have a look at this

		// from 0 to w-1, supply rules
		// from w to w+(s-1), stadium demands
		// from w+s to w+s+(s-1), stadium uplimits

		//supply rules
		for (int w = 0; w < n; w++) {
			for (int s = 0; s < m; s++) {
				lp.set_a((m*w)+s, w, 1);
			}
			lp.set_b (w, wss[w]);
		}

		//stadium demands rules
		for (int s = 0; s < m; s++) {
			for (int w = 0; w < n; w++) {
				lp.set_a(((m*w)+s), n + s, 1);
				lp.set_a(((m*w)+s), n + 2*m + s, -1);
			}
			lp.set_b (n + s, sd[s]);
			//lp.set_r (n + s, CGAL::EQUAL);
			lp.set_b(n + 2*m + s, -sd[s]);
		}

		//stadium uplimits rules
		for (int s = 0; s < m; s++) {
			for (int w = 0; w < n; w++) {
				lp.set_a((m*w)+s, n + m + s, wa[w]);
			}
			lp.set_b (n + m + s, su[s]*100);
		}

		//cerr << real_c.size() << endl;

		for (int w = 0; w < n; w++) {
			for (int s = 0; s < m; s++) {
				int hills = 0;
				for (int j = 0; j < real_c.size(); j++) {
					int i = real_c[j];
					long long sqd = cr[i]*cr[i];
					long long dw = (cx[i] - wx[w])*(cx[i] - wx[w]) + 
						(cy[i] - wy[w])*(cy[i] - wy[w]);
					long long ds = (cx[i] - sx[s])*(cx[i] - sx[s]) + 
						(cy[i] - sy[s])*(cy[i] - sy[s]);
					if ((dw < sqd && ds > sqd) || (dw > sqd && ds < sqd)) {
						hills++;
					}

				}
				//hills=0;


				lp.set_c((m*w)+s, -r[w][s]*100+(hills));
				//lp.set_c((n*w)+s, -1);
			}
		}

		//cerr << "finished " << endl;

		Solution s = CGAL::solve_nonnegative_linear_program(lp, ET());

		//cerr << "finished lp" << endl;

		if (s.status() == CGAL::QP_INFEASIBLE) {
			std::cout << "RIOT!" << endl;
		} else if (s.status() == CGAL::QP_UNBOUNDED) {
			cout << "lol" << endl;
		}
		else {
			double res = ceil_to_double(s.objective_value()/100);
			cout << (long long) (-res) << endl;
		}
	}
}

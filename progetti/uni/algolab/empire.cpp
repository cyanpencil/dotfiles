#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>
#include <CGAL/QP_models.h>
#include <CGAL/QP_functions.h>
#include <CGAL/Gmpq.h>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Finite_faces_iterator  Face_iterator;


typedef CGAL::Gmpq ET;
// solution type the solver provides
typedef CGAL::Quotient<ET> SolT;
// program and solution types
typedef CGAL::Quadratic_program<ET> Program;
typedef CGAL::Quadratic_program_solution<ET> Solution;


using namespace std;

double ceil_to_double(const SolT& x)
{
  double a = std::ceil(CGAL::to_double(x));
  while (a < x) a += 1;
  while (a-1 >= x) a -= 1;
  return a;
}

using namespace std;

int main () {
    int t;
    cin >> t;
    while (t--) {
		int a,s,b,e;
		cin >> a >> s >> b;
		cin >> e;

		vector <K::Point_2> bad;
		vector <K::Point_2> shooting;
		vector <K::FT> maxdist;

		vector<int> a_x;
		vector<int> a_y;
		vector<int> a_d;


		for (int i = 0; i < a; i++) {
			int x,y,d;
			cin >> x >> y >> d;
			a_x.push_back(x);
			a_y.push_back(y);
			a_d.push_back(d);
		}

		for (int i = 0; i < s; i++) {
			int x,y;
			cin >> x >> y;
			shooting.push_back(K::Point_2(x,y));
		}

		for (int i = 0; i < b; i++) {
			int x,y;
			cin >> x >> y;
			bad.push_back(K::Point_2(x,y));
		}

		Triangulation t;
		t.insert(bad.begin(), bad.end());

		for (int i = 0; i < s; i++) {
			if (b > 0) {
				K::Point_2 nearest = t.nearest_vertex(shooting[i])->point();
				maxdist.push_back(CGAL::squared_distance(nearest, shooting[i]));
			} else {
				maxdist.push_back(99999999999);
			}
		}

		Program lp (CGAL::LARGER, true, 0, false, 0); 


		for (int i = 0; i < a; i++) {
			int x,y,d;
			K::Point_2 asteroid(a_x[i],a_y[i]);
			for (int j = 0; j < s; j++) {
				K::FT lol = CGAL::squared_distance(shooting[j], asteroid);
				//cout << "For asteroid at " << asteroid << " and shooting at " << shooting[j] << " reenergy will be divided by " << CGAL::to_double(lol) << endl;
				if (lol < maxdist[j]) {
					if (lol < 1) {
						lp.set_a(j, i, ET(1));
					} else {
						lp.set_a(j, i, ET(1)/ET(lol));
					}
				}
			}
			lp.set_b(i, a_d[i]);
		}

		for (int i = 0; i < s; i++) 
			lp.set_c(i, 1);

		Solution solution = CGAL::solve_linear_program(lp, ET());

		if (solution.status() == CGAL::QP_INFEASIBLE) {
			cout << "n" << endl;
		} else {
			//cout << ceil_to_double(solution.objective_value()) << endl;
			if (solution.objective_value() > e) {
				cout << "n" << endl;
			} else {
				cout << "y" << endl;
			}
		}
	}
}

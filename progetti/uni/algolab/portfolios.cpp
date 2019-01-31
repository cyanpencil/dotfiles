#include <iostream>
#include <cassert>
#include <CGAL/basic.h>
#include <CGAL/QP_models.h>
#include <CGAL/QP_functions.h>
#include <CGAL/Gmpq.h>

using namespace std;
typedef CGAL::Gmpq ET;
typedef CGAL::Quotient<ET> SolT;


// program and solution types
typedef CGAL::Quadratic_program<ET> Program;
typedef CGAL::Quadratic_program_solution<ET> Solution;

double ceil_to_double(const SolT& x)
{
  double a = std::ceil(CGAL::to_double(x));
  while (a < x) a += 1;
  while (a-1 >= x) a -= 1;
  return a;
}

double floor_to_double(const SolT& x)
{
  double a = std::floor(CGAL::to_double(x));
  while (a > x) a -= 1;
  while (a+1 <= x) a += 1;
  return a;
}

int main() {
	int n, m;
	while(scanf("%d %d", &n, &m) == 2 ) {
		if (n == 0 && m == 0) return 0;
		int cost[n] = {0};
		int rvalue[n] = {0};
		int covar[n][n] = {0};

		int r = 0;

		for (int i = 0; i < n; i++) {
			int a, b;
			cin >> cost[i] >> rvalue[i];
		}
		for (int i = 0; i < n; i++) {
			for (int z = 0; z < n; z++) {
				cin >> covar[i][z];
			}
		}

		for (int i = 0; i < m; i++) {
			int max_cost, min_return, max_variance;
			cin >> max_cost >> min_return >> max_variance;

			Program lp (CGAL::SMALLER, true, 0, false, 0);

			int r = 0;

			for (int z = 0; z < n; z++) {
				lp.set_a(z, r, cost[z]);
			}
			lp.set_b(r, max_cost);
			//lp.set_r(r, CGAL::SMALLER);

			r++;

			for (int z = 0; z < n; z++) {
				lp.set_a(z, r, -rvalue[z]);
			}
			lp.set_b(r, -min_return);
			//lp.set_r(r, CGAL::LARGER);

			for (int z = 0; z < n; z++) {
				for (int x = z; x < n; x++) {
					//cout << x << z << covar[x][z] << endl;
					lp.set_d(x, z, 2*covar[x][z]);
				}
			}


			Solution s = CGAL::solve_quadratic_program(lp, ET());
			assert (s.solves_quadratic_program(lp));

			if (s.status() == CGAL::QP_UNBOUNDED) {
				cout << "Yes." << endl;
			} else if (s.status() == CGAL::QP_INFEASIBLE) {
				cout << "No." << endl;
			} else if (s.is_optimal()) {
				CGAL::Quotient<ET> sol = s.objective_value();
				if (sol > max_variance) {
					cout << "No." << endl;
				} else {
					cout << "Yes." << endl;
				}
			}
		}




	}

	return 0;
}

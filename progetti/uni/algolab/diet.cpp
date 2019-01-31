#include <iostream>
#include <cassert>
#include <CGAL/basic.h>
#include <CGAL/QP_models.h>
#include <CGAL/QP_functions.h>
#include <CGAL/Gmpq.h>

using namespace std;
// choose exact integral type
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
		int nutrient[n] = {0};
		int mymax[n] = {0};
		int mymin[n] = {0};
		int price[m] = {0};

		Program lp (CGAL::SMALLER, true, 0, false, 0);
		int r = 0;

		for (int i = 0; i < n; i++) {
			nutrient[i] = i;
			int a, b;
			cin >> a >> b;
			mymax[i] = a;
			mymin[i] = b;
			lp.set_b(i, -a);
			lp.set_b(n + i, b);
		}

		for (int i = 0; i < m; i++) {
			cin >> price[i];
			for (int z = 0; z < n; z++) {
				int c; cin >> c;
				lp.set_a(i, z, -c);
				lp.set_a(i, n+z, c);
			}
			lp.set_c(i, price[i]);
		}

		Solution s = CGAL::solve_linear_program(lp, ET());
		assert (s.solves_linear_program(lp));

		if (s.status() == CGAL::QP_UNBOUNDED) {
			cout << "No such diet." << endl;
		} else if (s.status() == CGAL::QP_INFEASIBLE) {
			cout << "No such diet." << endl;
		} else if (s.is_optimal()) {
			int sol = (int)(floor_to_double(s.objective_value()));
			if (sol > 0) {
				cout << sol << endl;
			} else {
				cout << "No such diet." << endl;
			}
		}



	}

	return 0;
}

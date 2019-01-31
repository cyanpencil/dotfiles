#include <iostream>
#include <cassert>
#include <CGAL/basic.h>
#include <CGAL/QP_models.h>
#include <CGAL/QP_functions.h>
#include <CGAL/Gmpz.h>

using namespace std;
typedef CGAL::Gmpz ET;
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
	int n, d;
	while(scanf("%d %d", &n, &d) == 2 ) {
		if (n == 0) return 0;

		Program lp (CGAL::SMALLER, false, 0, false, 0); 

		int T = d+1;

		for (int i = 0; i < n; i++) {
			int a, b;
			int cc = 0;
			for (int j = 0; j < d; j++){
				cin >> a;
				cc += a*a;
				lp.set_a(j, i, a);
				lp.set_a(j, n+i, a);
			}
			//cout << sqrt(cc) << endl;
			lp.set_a(T, i, sqrt(cc));
			lp.set_a(T, n+i, -sqrt(cc));
			cin >> b;
			lp.set_b(i, b);
			lp.set_b(n+i, b);
		}
		//lp.set_a(T, 2*n+1, -1);
		//lp.set_b(0, 2*n+1);
		//
		lp.set_c(T, -1);

		//cout << n << d << endl;


		Solution s = CGAL::solve_linear_program(lp, ET());
		//assert (s.solves_linear_program(lp));

		if (s.status() == CGAL::QP_UNBOUNDED) {
			cout << "inf" << endl;
		}
		else if (s.status() == CGAL::QP_INFEASIBLE) {
			cout << "none" << endl;
		}
		else if (s.is_optimal()) {
			cout << (int)-ceil_to_double(s.objective_value()) << endl;
		} else {
			cout << "none" << endl;
		}




	}

	return 0;
}

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
  while (a-1 <= x) a += 1;
  return a;
}


int main() {
	int p, a, b;
	while(scanf("%d %d %d", &p, &a, &b) == 3 ) {

		if (p == 1) {
			Program lp (CGAL::SMALLER, true, 0, false, 0);

			// now set the non-default entries
			const int X = 0;
			const int Y = 1;
			//lp.set_a(X, 0, -1); lp.set_a(Y, 0,  0); lp.set_b(0, 0);  // -x <= 0
			//lp.set_a(X, 1,  0); lp.set_a(Y, 1, -1); lp.set_b(1, 0);  // -y <= 0

			lp.set_a(X, 0,  1); lp.set_a(Y, 0, 1); lp.set_b(0, 4);  // x + y <= 4
			lp.set_a(X, 1,  4); lp.set_a(Y, 1, 2); lp.set_b(1, a*b); // 4x + 2y <= a*b
			lp.set_a(X, 2, -1); lp.set_a(Y, 2, 1); lp.set_b(2, 1); // -x + y <= 1


			lp.set_d(X, X, 2*a);                                       // -32y
			lp.set_d(Y, X, 0);                                       // -32y
			lp.set_d(Y, Y, 0);                                       // -32y
			lp.set_c(Y, -b);                                       // -32y

			Solution s = CGAL::solve_quadratic_program(lp, ET());
			assert (s.solves_quadratic_program(lp));
			//cout << a << b << p << endl;
			//cout << s << endl << endl;

			if (s.status() == CGAL::QP_UNBOUNDED) {
				cout << "unbounded" << endl;
			} else if (s.status() == CGAL::QP_INFEASIBLE) {
				cout << "no" << endl;
			} else if (s.is_optimal()) {
				cout << (int)(-ceil_to_double(s.objective_value())) << endl;
			}
			
		} else {

			Program lp (CGAL::SMALLER, false, 0, false, 0);

			// now set the non-default entries
			const int X = 0;
			const int Y = 1;
			const int T = 2;
			lp.set_a(X, 0,  1); lp.set_a(Y, 0,  0); lp.set_a(T, 0,  0); lp.set_b(0, 0);  // x <= 0
			lp.set_a(X, 1,  0); lp.set_a(Y, 1,  1); lp.set_a(T, 1,  0); lp.set_b(1, 0);  // y <= 0
			//lp.set_a(X, 4,  0); lp.set_a(Y, 4,  0); lp.set_a(T, 4,  -1); lp.set_b(4, 0); // -t <= 0

			lp.set_a(X, 5, -1); lp.set_a(Y, 5, -1); lp.set_a(T, 5,  0); lp.set_b(5, 4); // x - y <= 1
			lp.set_a(X, 2, -4); lp.set_a(Y, 2, -2); lp.set_a(T, 2, -1); lp.set_b(2, a*b);  // -4x -2y -t <= a*b
			lp.set_a(X, 3,  1); lp.set_a(Y, 3, -1); lp.set_a(T, 3,  0); lp.set_b(3, 1); // x - y <= 1



			lp.set_d(X, X, 2*a);                                       // -32y
			lp.set_d(T, T, 2);                                       // -32y
			lp.set_c(Y, b);                                       // -32y

			Solution s = CGAL::solve_quadratic_program(lp, ET());
			assert (s.solves_quadratic_program(lp));

			if (s.status() == CGAL::QP_UNBOUNDED) {
				cout << "unbounded" << endl;
			} else if (s.status() == CGAL::QP_INFEASIBLE) {
				cout << "no" << endl;
			} else if (s.is_optimal()) {
				cout << (int)(ceil_to_double(s.objective_value())) << endl;
			}

		}

	}

	return 0;
}


#include <bits/stdc++.h>
#include <CGAL/QP_models.h>
#include <CGAL/QP_functions.h>

// choose exact integral type
#include <CGAL/Gmpz.h>
typedef CGAL::Gmpz ET;

// program and solution types
typedef CGAL::Quadratic_program<ET> Program;
typedef CGAL::Quadratic_program_solution<ET> Solution;

using namespace std;

int h,t;
int hx[50],hy[50],hz[50];
int tx[50],ty[50],tz[50];

ET px[60][3][31];

ET oh(int p, int c, int wow) {
	//if (wow == 0) return ET(1);
	//if (wow == 1) return px[p][c][1];
	//if (px[p][c][wow] != ET(-1)) return px[p][c][wow];
	//return px[p][c][wow] = ET(px[p][c][wow-1]*px[p][c][1]);
	return px[p][c][wow];
	//return 1;
}

int main () {
	ios_base::sync_with_stdio(false);
	int T;
	cin >> T;
	while (T--) {
		cin >> h >> t;

		for (int i = 0; i < h+t; i++) cin >> hx[i] >> hy[i] >> hz[i];


		for (int i = 0; i < h+t; i++) {
			px[i][0][0] = ET(1);
			px[i][1][0] = ET(1);
			px[i][2][0] = ET(1);
			px[i][0][1] = ET(hx[i]);
			px[i][1][1] = ET(hy[i]);
			px[i][2][1] = ET(hz[i]);
			for (int z = 2; z < 31; z++) {
				px[i][0][z] = px[i][0][z-1] * ET(hx[i]);
				px[i][1][z] = px[i][1][z-1] * ET(hy[i]);
				px[i][2][z] = px[i][2][z-1] * ET(hz[i]);
			}
		}


		for (int d = 0; d <= 30; d++) {
			Program lp (CGAL::SMALLER, false, 0, false, 0); 

			int count = 0;
			int delta;
			for (int i = 0; i < h; i++) {
			
				count = 0;
				for (int x = 0; x <= d; x++) {
					for (int y = 0; y <= d-x; y++) {
						for (int z = 0; z <= d-x-y; z++) {
							//lp.set_a(count, i, -pow(hx[i],x)*pow(hy[i],y)*pow(hz[i],z));
							lp.set_a(count, i, oh(i,0,x)*oh(i,1,y)*oh(i,2,z));
							//cout << x << "-" << y << "-" << z << endl;
							//cout << pow(hx[i],x)<< " " <<pow(hy[i],y)<< " " <<pow(hz[i],z) << endl;
							count++;
						}
					}
				}
				//lp.set_a(delta, i, 1);
				lp.set_b(i, -1); 
				lp.set_r(i, CGAL::SMALLER);
			}

			for (int i = 0; i < t; i++) {
				count = 0;
				for (int x = 0; x <= d; x++) {
					for (int y = 0; y <= d-x; y++) {
						for (int z = 0; z <= d-x-y; z++) {
							//lp.set_a(count, h+i, -pow(tx[i],x)*pow(ty[i],y)*pow(tz[i],z));
							lp.set_a(count, h+i, oh(h+i,0,x)*oh(h+i,1,y)*oh(h+i,2,z));
							count++;
						}
					}
				}
				//lp.set_a(delta, h+i, 1);
				lp.set_b(h+i, 1); 
				lp.set_r(h+i, CGAL::LARGER);
			}

			//lp.set_c(count - 1, -1);

			CGAL::Quadratic_program_options options;
			options.set_pricing_strategy(CGAL::QP_BLAND);
			Solution s = CGAL::solve_linear_program(lp, ET(), options);

			if (!s.is_infeasible()) {
				cout << d << endl;
				goto finish;
			}
		}
		cout << "Impossible!" << endl;
finish:
		continue;
	}
}





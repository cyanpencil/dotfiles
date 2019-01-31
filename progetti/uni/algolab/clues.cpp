
#include <bits/stdc++.h>

#include <CGAL/Exact_predicates_exact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

typedef CGAL::Exact_predicates_exact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Edge_iterator  Edge_iterator;

using namespace std;
int n,m;
K::FT r;
vector<K::Point_2> stations;
vector<K::Point_2> clues_a, clues_b;

vector<vector<int>> g;

int V[100000];
int P[100000];
int E[100000];
bool I[100000];

bool dfs (int from, int node) {
	if (V[node]) return true;
	V[node] = 1;
	bool fake = false;
	for (int i = 0; i < g[node].size(); i++) {
		if (g[node][i] == from) continue;
		fake = dfs(node, g[node][i]);
	}
	return fake;
}


int getfather(int p) {
	int a = p;
	while (P[a] != a) {
		a = P[a];
	}
	return P[p] = a;
}

int main () {
    int t;
    cin >> t;
    while (t--) {
		cin >> n >> m >> r;

		int count = 0;
		unordered_map<Triangulation::Vertex_handle, int> mappa;

		for (int i = 0; i < n; i++) {
			P[i] = i;
			E[i] = 0;
		}
		  
		for (int i = 0; i < n; i++) {
			int x,y;
			cin >> x >> y;
			stations.push_back(K::Point_2(x,y));
		}

		memset(V, 0, sizeof V);
		memset(I, 0, sizeof I);

		for (int i = 0; i < m; i++) { 
			int x,y;
			cin >> x >> y;
			clues_a.push_back(K::Point_2(x,y));
			cin >> x >> y;
			clues_b.push_back(K::Point_2(x,y));
		}


		Triangulation t;
		t.insert(stations.begin(), stations.end());
		for (auto v = t.finite_vertices_begin(); v != t.finite_vertices_end(); ++v) {
			mappa[v] = count++;
		}
		g.clear();
		g.resize(count+1);

		//for (Edge_iterator e = t.finite_edges_begin(); e != t.finite_edges_end(); ++e) {


			//auto v1 = e->first->vertex((e->second + 1) % 3);
			//auto v2 = e->first->vertex((e->second + 2) % 3);
			//if (CGAL::squared_distance(v1->point(),v2->point()) <= r*r) {
			//}

		//}

		for (auto v1 = t.finite_vertices_begin(); v1 != t.finite_vertices_end(); ++v1) {
			for (auto v2 = t.finite_vertices_begin(); v2 != t.finite_vertices_end(); ++v2) {
				if (v1 == v2) continue;
				if (CGAL::squared_distance(v1->point(), v2->point()) <= r*r) {
					if (mappa.find(v1) == mappa.end()) {cout<<"oh";mappa[v1] = count++;}
					if (mappa.find(v2) == mappa.end()) {cout<<"oh";mappa[v2] = count++;}
					E[mappa[v1]]++;
					E[mappa[v2]]++;
					g[mappa[v1]].push_back(mappa[v2]);
					g[mappa[v2]].push_back(mappa[v1]);
					P[getfather(mappa[v1])] = getfather(mappa[v2]);
				}
			}
		}

		bool interferences = false;

		//for (auto v = t.finite_vertices_begin(); v != t.finite_vertices_end(); ++v) {
			////cout << E[mappa[v]] << " ";
			//if (E[mappa[v]] > 2) {
				//interferences = true; break;
			//}
		//}
		for (auto v = t.finite_vertices_begin(); v != t.finite_vertices_end(); ++v) {
			int i = mappa[v];
			if (!V[i]) {
				if (dfs(-1, i)) {
					//cout << i << endl;
					I[getfather(i)] = 1;
					interferences = true; 
				}
			}
		}


		for (int i = 0; i < m; i++) {
			auto pp = t.nearest_vertex(clues_a[i]);
			auto pp2 = t.nearest_vertex(clues_b[i]);
			
			if (mappa.find(pp) == mappa.end() || mappa.find(pp2) == mappa.end()) {
				cout << "something went wront ";
				continue;
			}
			//if (interferences) {
				//cout << "n";
				//continue;
			//}

			//if (I[getfather(mappa[pp])] || I[getfather(mappa[pp2])]) {
				//cout << "N";
				//continue;
			//}
			//if (((CGAL::squared_distance(pp->point(), clues_a[i]) >= 
				 //CGAL::squared_distance(clues_a[i], clues_b[i])) ||
				//(CGAL::squared_distance(pp2->point(), clues_b[i]) >= 
				 //CGAL::squared_distance(clues_a[i], clues_b[i]))) && 
				//CGAL::squared_distance(clues_a[i], clues_b[i]) <= r*r) {
					//cout << "Cy";
					//continue;
			//}

			if (CGAL::squared_distance(clues_a[i], clues_b[i]) <= r*r) {
				cout << "Ay";
				continue;
			}
			//cout << pp->point() << " - " << pp2->point() << endl;
			if (CGAL::squared_distance(pp->point(), clues_a[i]) > r*r ||
			  CGAL::squared_distance(pp2->point(), clues_b[i]) > r*r) {
				cout << "n"; continue;
			}
			//cout << getfather(mappa[pp]) << getfather(mappa[pp2]) << endl;
			if (getfather(mappa[pp]) == getfather(mappa[pp2])) {
				cout << "y";
			} else {
				cout << "n";
			}
		}

		cout << endl;
    }
}

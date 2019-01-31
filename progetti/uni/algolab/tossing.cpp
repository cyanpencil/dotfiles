#include <bits/stdc++.h>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/edmonds_karp_max_flow.hpp>

using namespace std;
using namespace boost;

typedef adjacency_list_traits<vecS, vecS, directedS> Traits;
typedef adjacency_list<vecS, vecS, directedS, no_property,
			property<edge_capacity_t, long, 
				property<edge_residual_capacity_t, long,
					property<edge_reverse_t, Traits::edge_descriptor> > > > Graph;

typedef property_map<Graph, edge_capacity_t>::type EdgeCapacityMap;
typedef property_map<Graph, edge_residual_capacity_t>::type ResidualCapacityMap;
typedef property_map<Graph, edge_reverse_t>::type ReverseEdgeMap;
typedef graph_traits<Graph>::vertex_descriptor Vertex;
typedef graph_traits<Graph>::edge_descriptor Edge;

void addEdge(int from, int to, long capacity, EdgeCapacityMap &capacitymap, ReverseEdgeMap &revedgemap, Graph &G) {
	Edge e, rev_e;
	bool success;
	tie(e, success) = add_edge(from, to, G);
	tie(rev_e, success) = add_edge(to, from, G);
	capacitymap[e] = capacity;
	capacitymap[rev_e] = 0;
	revedgemap[e] = rev_e;
	revedgemap[rev_e] = e;
}

int main () {
    int t;
    cin >> t;
    while (t--) {
		int n, m;
		cin >> n >> m;
		int p = m+2;
		int score[n];
		memset(score, 0, sizeof score);
		Graph g(n + p + 3);
		int thesource = p + n + 1, thetarget = p + n + 2;
		EdgeCapacityMap capacitymap = get(edge_capacity, g);
		ReverseEdgeMap revedgemap = get(edge_reverse, g);
		ResidualCapacityMap rescapacitymap = get(edge_residual_capacity, g);
		int times = 0;
		for (int i = 0; i < m; i++) {
			int a, b, c;
			cin >> a >> b >> c;
			if (c == 1) {
				score[a]++;
			} else if (c == 2) {
				score[b]++;
			} else if (c == 0) {
				times++;
				addEdge(i, p + a, 1, capacitymap, revedgemap, g);
				addEdge(i, p + b, 1, capacitymap, revedgemap, g);
				addEdge(thesource, i, 1, capacitymap, revedgemap, g);
			}
		}
		int shouldbe = 0;
		bool fail = false;
		for (int i = 0; i < n; i++) {
			int t;
			cin >> t;
			int diff = t - score[i];
			cerr << diff << " " << shouldbe << endl;
			if (diff > 0) {
				addEdge(p + i, thetarget, diff, capacitymap, revedgemap, g);
				shouldbe += diff;
			}
			if (diff < 0) {
				fail = true;
			}
		}
		if (fail) {
			cout << "no" << endl;
			continue;
		}
		long flow = push_relabel_max_flow(g, thesource, thetarget);
		if (flow == shouldbe && shouldbe == times) cout << "yes" << endl;
		else cout << "no" << endl;
    }
}

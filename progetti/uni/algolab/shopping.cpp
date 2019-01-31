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
		int n, m, s;
		cin >> n >> m >> s;
		Graph g(n + 10);
		EdgeCapacityMap capacitymap = get(edge_capacity, g);
		ReverseEdgeMap revedgemap = get(edge_reverse, g);
		ResidualCapacityMap rescapacitymap = get(edge_residual_capacity, g);

		int stores[n] = {0};
		for (int i = 0; i < s; i++) {int c; cin >> c; stores[c]++;}
		for (int i = 1; i < n; i++) {
			if (stores[i] > 0) {
				addEdge(i, n + 2, stores[i], capacitymap, revedgemap, g);
			}
		}
		for (int i = 0; i < m; i++) {
			int a, b;
			cin >> a >> b;
			addEdge(a, b, 1, capacitymap, revedgemap, g);
			addEdge(b, a, 1, capacitymap, revedgemap, g);
		}
		long flow = push_relabel_max_flow(g, 0, n+2);
		if (flow >= s) cout << "yes" << endl;
		else cout << "no" << endl;
    }
}

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
		int n, q;
		cin >> n >> q;
		Graph g(n + 10);
		int source = n+2, target = n+3;
		EdgeCapacityMap capacitymap = get(edge_capacity, g);
		ReverseEdgeMap revedgemap = get(edge_reverse, g);
		ResidualCapacityMap rescapacitymap = get(edge_residual_capacity, g);

		int locations[n] = {0};
		int defense[n] = {0};
		for (int i = 0; i < n; i++) {int a,b; cin >> a >> b; locations[i] = a; defense[i] = b;}

		int total = 0;

		for (int i = 0; i < n; i++) {
			if (locations[i] < defense[i]) {
				addEdge(i, target, (defense[i] - locations[i]), capacitymap, revedgemap, g);
				total += (defense[i] - locations[i]);
			}
			if (locations[i] > defense[i]) addEdge(source, i, (locations[i] - defense[i]), capacitymap, revedgemap, g);
		}

		for (int i = 0; i < q; i++) {
			int a, b, c, d;
			cin >> a >> b >> c >> d;
			addEdge(a, b, d - c, capacitymap, revedgemap, g);
			if (c > 0) {
				addEdge(source, b, c, capacitymap, revedgemap, g);
				addEdge(a, target, c, capacitymap, revedgemap, g);
				total += c;
			}
		}


		long flow = push_relabel_max_flow(g, source, target);
		if (flow == total) cout << "yes" << endl;
		else cout << "no" << endl;
    }
}

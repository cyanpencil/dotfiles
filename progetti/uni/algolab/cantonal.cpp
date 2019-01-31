#include <bits/stdc++.h>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/tuple/tuple.hpp>

// BGL Graph definitions
// =====================
// Graph Type with nested interior edge properties for Flow Algorithms
typedef	boost::adjacency_list_traits<boost::vecS, boost::vecS, boost::directedS> Traits;
typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::directedS, boost::no_property,
	boost::property<boost::edge_capacity_t, long,
		boost::property<boost::edge_residual_capacity_t, long,
			boost::property<boost::edge_reverse_t, Traits::edge_descriptor> > > >	Graph;
// Interior Property Maps
typedef	boost::property_map<Graph, boost::edge_capacity_t>::type		EdgeCapacityMap;
typedef	boost::property_map<Graph, boost::edge_residual_capacity_t>::type	ResidualCapacityMap;
typedef	boost::property_map<Graph, boost::edge_reverse_t>::type		ReverseEdgeMap;
typedef	boost::graph_traits<Graph>::vertex_descriptor			Vertex;
typedef	boost::graph_traits<Graph>::edge_descriptor			Edge;
typedef	boost::graph_traits<Graph>::out_edge_iterator			OutEdgeIt;




using namespace std;

int c[101];
int J[101];
int Tn[101];
int T[101][101];

int main () {
    int t;
    cin >> t;
    while (t--) {
		int z,j;
		cin >> z >> j;

		int source = z + j + 1;
		int sink =   z + j + 2;
		int total = 0;

		Graph G(sink);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);

		auto addEdge = [&](int from, int to, long capacity) -> void {
				Edge e, rev_e;
				bool success;
				boost::tie(e, success) = boost::add_edge(from, to, G);
				boost::tie(rev_e, success) = boost::add_edge(to, from, G);
				capacitymap[e] = capacity;
				capacitymap[rev_e] = 0;
				revedgemap[e] = rev_e;
				revedgemap[rev_e] = e;
			};

		for (int i = 0; i < z; i++) { cin >> c[i]; addEdge(i, sink, c[i]);}
		for (int i = 0; i < j; i++) { cin >> J[i]; addEdge(source, z+i, J[i]); total+=J[i];}
		for (int i = 0; i < j; i++) {
			cin >> Tn[i];
			for (int j = 0; j < Tn[i]; j++) {
				cin >> T[i][j];
				addEdge(z+i, T[i][j], 99999999);
			}
		}

		int flow = boost::push_relabel_max_flow(G, source, sink);

		//cout << flow << endl;
		cout << total - flow << endl;

    }
}

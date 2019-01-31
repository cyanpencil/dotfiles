#include <bits/stdc++.h>

// BGL includes
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/cycle_canceling.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/successive_shortest_path_nonnegative_weights.hpp>
#include <boost/graph/find_flow_cost.hpp>

// BGL Graph definitions
// ===================== 
// Graph Type with nested interior edge properties for Cost Flow Algorithms
typedef boost::adjacency_list_traits<boost::vecS, boost::vecS, boost::directedS> Traits;
typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::directedS, boost::no_property,
	boost::property<boost::edge_capacity_t, long,
		boost::property<boost::edge_residual_capacity_t, long,
			boost::property<boost::edge_reverse_t, Traits::edge_descriptor,
				boost::property <boost::edge_weight_t, long> > > > > Graph; // new!
// Interior Property Maps
typedef boost::property_map<Graph, boost::edge_capacity_t>::type      EdgeCapacityMap;
typedef boost::property_map<Graph, boost::edge_weight_t >::type       EdgeWeightMap; // new!
typedef boost::property_map<Graph, boost::edge_residual_capacity_t>::type ResidualCapacityMap;
typedef boost::property_map<Graph, boost::edge_reverse_t>::type       ReverseEdgeMap;
typedef boost::graph_traits<Graph>::vertex_descriptor          Vertex;
typedef boost::graph_traits<Graph>::edge_descriptor            Edge;
typedef boost::graph_traits<Graph>::out_edge_iterator  OutEdgeIt; // Iterator
using namespace std;
using namespace boost;


int main () {
    int t;
    cin >> t;
    while (t--) {
		int n,m,l;
		cin >> n >> m >> l;

		int source = n+1;
		int sink = n+2;
		Graph G(n + 3);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap = boost::get(boost::edge_weight, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);

		auto addEdge = [&](int u, int v, long c, long w) -> void {
			Edge e, rev_e;
			boost::tie(e, boost::tuples::ignore) = boost::add_edge(u, v, G);
			boost::tie(rev_e, boost::tuples::ignore) = boost::add_edge(v, u, G);
			capacitymap[e] = c;
			weightmap[e] = w; // new!
			capacitymap[rev_e] = 0;
			weightmap[rev_e] = -w; // new
			revedgemap[e] = rev_e; 
			revedgemap[rev_e] = e; 
		};


		for (int i = 0; i < m; i++) {
			int x,y,q;
			cin >> x >> y >> q;
			addEdge(x, y, 1, (500*(y-x)) - q);
		}

		for (int i = 0; i < n; i++) addEdge(i, i+1, 999999, 500);

		addEdge(source, 0, l, 0);
		addEdge(n, sink, l, 0);

		boost::successive_shortest_path_nonnegative_weights(G, source, sink);
		int cost2 = boost::find_flow_cost(G);
		int s_flow = 0;
		OutEdgeIt e, eend;
		for(boost::tie(e, eend) = boost::out_edges(boost::vertex(source,G), G); e != eend; ++e) {
			s_flow += capacitymap[*e] - rescapacitymap[*e];
		}

		cout << s_flow*(500*n) - cost2 << endl;
    }
}

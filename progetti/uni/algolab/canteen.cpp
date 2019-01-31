#include <bits/stdc++.h>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/cycle_canceling.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/successive_shortest_path_nonnegative_weights.hpp>
#include <boost/graph/find_flow_cost.hpp>

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

class EdgeAdder {
	Graph &G;
	EdgeCapacityMap &capacitymap;
	EdgeWeightMap &weightmap;
	ReverseEdgeMap  &revedgemap;

	public:
	EdgeAdder(Graph &G, EdgeCapacityMap &capacitymap, EdgeWeightMap &weightmap, ReverseEdgeMap &revedgemap) 
	: G(G), capacitymap(capacitymap), weightmap(weightmap), revedgemap(revedgemap) {}

	void addEdge(int u, int v, long c, long w) {
		Edge e, rev_e;
		boost::tie(e, boost::tuples::ignore) = boost::add_edge(u, v, G);
		boost::tie(rev_e, boost::tuples::ignore) = boost::add_edge(v, u, G);
		capacitymap[e] = c;
		weightmap[e] = w; // new!
		capacitymap[rev_e] = 0;
		weightmap[rev_e] = -w; // new
		revedgemap[e] = rev_e; 
		revedgemap[rev_e] = e; 
	}
};

using namespace std;


int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		int n;
		cin >> n;
		int a[n], c[n], s[n], p[n], v[n], e[n];
		for (int i = 0; i < n; i++) cin >> a[i] >> c[i];
		for (int i = 0; i < n; i++) cin >> s[i] >> p[i];
		for (int i = 0; i < n-1; i++) cin >> v[i] >> e[i];


		int source = 3*n+1;
		int sink = 3*n+2;

		Graph G(3*n + 2);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap = boost::get(boost::edge_weight, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);
		EdgeAdder eaG(G, capacitymap, weightmap, revedgemap);

		for (int i = 0; i < n; i++) {
			eaG.addEdge(source, i, a[i], 20+c[i]);
			eaG.addEdge(i, sink, s[i], 20-p[i]);
		}
		for (int i = 0; i < n-1; i++) {
			eaG.addEdge(i, i+1, v[i], e[i]);
		}

		int totalsum = 0;
		for (int i = 0; i < n; i++) totalsum += s[i];

		//int flow1 = boost::push_relabel_max_flow(G, source, sink);
		//boost::cycle_canceling(G);
		//int cost1 = boost::find_flow_cost(G);


		boost::successive_shortest_path_nonnegative_weights(G, source, sink);
		int cost2 = boost::find_flow_cost(G);
		int s_flow = 0;
		OutEdgeIt eo, eend;
		for(boost::tie(eo, eend) = boost::out_edges(boost::vertex(source,G), G); eo != eend; ++eo) {
			s_flow += capacitymap[*eo] - rescapacitymap[*eo];
		}


		if (s_flow == totalsum) cout << "possible ";
		else cout << "impossible ";

		cout << s_flow << " " << -cost2 + (40*s_flow) << endl;
    }
}

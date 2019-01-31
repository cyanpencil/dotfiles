#include <bits/stdc++.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/cycle_canceling.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/successive_shortest_path_nonnegative_weights.hpp>
#include <boost/graph/find_flow_cost.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>


typedef boost::adjacency_list_traits<boost::vecS, boost::vecS, boost::directedS> Traits;
typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::directedS, boost::no_property,
	boost::property<boost::edge_capacity_t, long,
		boost::property<boost::edge_residual_capacity_t, long,
			boost::property<boost::edge_reverse_t, Traits::edge_descriptor,
				boost::property <boost::edge_weight_t, long> > > > > Graph; // new!
typedef boost::property_map<Graph, boost::edge_capacity_t>::type      EdgeCapacityMap;
typedef boost::property_map<Graph, boost::edge_weight_t >::type       EdgeWeightMap; // new!
typedef boost::property_map<Graph, boost::edge_residual_capacity_t>::type ResidualCapacityMap;
typedef boost::property_map<Graph, boost::edge_reverse_t>::type       ReverseEdgeMap;
typedef boost::graph_traits<Graph>::vertex_descriptor          Vertex;
typedef boost::graph_traits<Graph>::edge_descriptor            Edge;
typedef boost::graph_traits<Graph>::out_edge_iterator  OutEdgeIt; // Iterator
typedef boost::graph_traits<Graph>::edge_iterator  EdgeIt; // Iterator

using namespace std;
using namespace boost;

int main () {
    int t;
    cin >> t;
    while (t--) {

		int n,m,s,f;
		cin >> n >> m >> s >> f;

		Graph G(n);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap = boost::get(boost::edge_weight, G);

		Graph G2(n);
		EdgeCapacityMap capacitymap2 = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap2 = boost::get(boost::edge_weight, G);
		ReverseEdgeMap revedgemap2 = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap2 = boost::get(boost::edge_residual_capacity, G);

		auto addEdgeF = [&] (int u, int v, long c, long w) -> void {
			//cerr << "Added!" << endl;
			Edge e, rev_e;
			boost::tie(e, boost::tuples::ignore) = boost::add_edge(u, v, G2);
			boost::tie(rev_e, boost::tuples::ignore) = boost::add_edge(v, u, G2);
			capacitymap2[e] = c;
			weightmap2[e] = w; // new!
			capacitymap2[rev_e] = 0;
			weightmap2[rev_e] = -w; // new
			revedgemap2[e] = rev_e; 
			revedgemap2[rev_e] = e; 
		};

		auto addEdge = [&] (int u, int v, long c, long w) -> void {
			Edge e;
			boost::tie(e, boost::tuples::ignore) = boost::add_edge(u, v, G);
			weightmap[e] = w; 
			capacitymap[e] = c;
		};

		for (int i = 0; i < m; i++) {
			int a,b,c,d;
			cin >> a >> b >> c >> d;
			addEdge(a,b,c,d);
			addEdge(b,a,c,d);
		}



		vector<int> distmap(n);	
		vector<Vertex> predmap(n);	
		dijkstra_shortest_paths(G, f,
		  predecessor_map(make_iterator_property_map(	predmap.begin(),
				  get(vertex_index, G))).
		  distance_map(make_iterator_property_map(	distmap.begin(),
				get(vertex_index, G))));

		EdgeIt ebeg, eend;
		for (boost::tie(ebeg, eend) = boost::edges(G); ebeg != eend; ++ebeg) {
			Vertex u = boost::source(*ebeg, G), v = boost::target(*ebeg, G);
			if (distmap[v] == distmap[u] - weightmap[*ebeg]) addEdgeF(u,v,capacitymap[*ebeg],weightmap[*ebeg]);
		}

		long flow1 = push_relabel_max_flow(G2, s, f);
		cout << flow1 << endl;

		//cout << "End of testcase" << endl;
	}
}

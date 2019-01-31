
#include <bits/stdc++.h>

// BGL includes
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

using namespace std;

int main () {
    int t;
    cin >> t;
    while (t--) {
		int b,s,p;
		cin >> b >> s >> p;
		
		int source = b + s + 100;
		int megasource = source + 2;
		int sink = source + 1;


		Graph G(sink+1);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap = boost::get(boost::edge_weight, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);

		auto addEdge = [&] (int u, int v, long c, long w) -> void {
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

		for (int i = 0; i < p; i++) {
			int s_i,b_i,c;
			cin >> b_i >> s_i >> c;
			//cout << "aggiungo edge " << b_i << " " << s_i << " " << c << endl;
			addEdge(s_i,s+b_i,1,50-c);
		}

		for (int i = 0; i < s; i++) {
			addEdge(source, i, 1, 0);
			addEdge(i, sink, 1, 50);
		}
		for (int i = 0; i < b; i++) {
			addEdge(s+i, sink, 1, 0);
		}

		Edge e, rev_e;
		boost::tie(e, boost::tuples::ignore) = boost::add_edge(megasource, source, G);
		boost::tie(rev_e, boost::tuples::ignore) = boost::add_edge(source, megasource, G);
		weightmap[e] = 0; // new!
		weightmap[rev_e] = 0; // new
		revedgemap[e] = rev_e; 
		revedgemap[rev_e] = e; 

		int maxxx = 0;

		int f;
		int start = 0; int end = s;
		f = min(p,min(s,b));
			capacitymap[e] = f;
			capacitymap[rev_e] = 0;

			boost::successive_shortest_path_nonnegative_weights(G, megasource, sink);
		    int cost2 = boost::find_flow_cost(G);
			int s_flow = 0;
			OutEdgeIt e2, eend;
			for(boost::tie(e2, eend) = boost::out_edges(boost::vertex(megasource,G), G); e2 != eend; ++e2) {
				s_flow += capacitymap[*e2] - rescapacitymap[*e2];
			}
			 
			int actual = 50*s_flow - cost2;

			//if (actual < maxxx) break;

			maxxx = max(maxxx, 50*s_flow - cost2);
		//}


		//cout << s << " " << b << " " << flow1 << " " << endl;
		//cout << maxxx << " " << f << "," << min(p,min(s,b)) << endl;
		cout << maxxx << endl;

    }
}


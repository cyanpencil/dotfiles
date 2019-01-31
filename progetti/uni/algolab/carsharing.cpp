#include <bits/stdc++.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/cycle_canceling.hpp>
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


long long n,s;
long long l[10];
long long si[20000], ti[20000], di[20000], ai[20000], pi[20000];

int main() {
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	while (t--) {
		cin >> n >> s;
		map<long long, long long> mappa[10];
		long long count = 0;

		Graph G(20000);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap = boost::get(boost::edge_weight, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);

		auto addEdge = [&](long long u, long long v, long long c, long long w) -> void {
			//cout << u << " - " << v << endl;
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
		long long maxtime=0;

		for (int i = 0; i < s; i++) cin >> l[i];
		for (int i = 0; i < n; i++) {
			cin >> si[i] >> ti[i] >> di[i] >> ai[i] >> pi[i];
			si[i]--; ti[i]--;
			maxtime = max(maxtime, ai[i]);
			//cout << "Read " << si[i] << " " << ti[i] << "   " << di[i] << " " << ai[i] << endl;
			if (mappa[si[i]].find(di[i]) == mappa[si[i]].end()) {
				mappa[si[i]][di[i]] = count++;
			}
			if (mappa[ti[i]].find(ai[i]) == mappa[ti[i]].end()) {
				mappa[ti[i]][ai[i]] = count++;
			}
			//cout << "-adding" << mappa[si[i]][di[i]] << " - " << mappa[ti[i]][ai[i]] << endl;
			addEdge(mappa[si[i]][di[i]], mappa[ti[i]][ai[i]], 1, (100*(ai[i] - di[i]))-pi[i]);
		}

		long long source = count++;
		long long target = count++;

		for (int i = 0; i < s; i++) {
			long long prev = -1;
			long long prevcost = -1;
			for (auto p : mappa[i]) {
				if (prev != -1) {
					addEdge(prev, p.second, 1000, 100*(p.first - prevcost));
				} else {
					addEdge(source, p.second, l[i], 100*p.first);
				}
				prev = p.second;
				prevcost = p.first;
			}
			//cout << i << "adding " << prev << " " << target << endl;
			if (prev == -1) continue;
			addEdge(prev, target, 1000, (maxtime - prevcost)*100);
		}


		//int flow1 = boost::push_relabel_max_flow(G, source, target);
		//cout << "The flow is " <<  flow1 << endl;
		//boost::cycle_canceling(G);
		//int cost1 = boost::find_flow_cost(G);

		//cout << "RESULT: " << cost1 << endl;
		//cout << -cost1 << endl;
		  




		boost::successive_shortest_path_nonnegative_weights(G, source, target);
		long long cost2 = boost::find_flow_cost(G);
		long long s_flow = 0;
		OutEdgeIt e, eend;
		for(boost::tie(e, eend) = boost::out_edges(boost::vertex(source,G), G); e != eend; ++e) {
			s_flow += capacitymap[*e] - rescapacitymap[*e];
		}
		cout << s_flow*maxtime*100 - cost2 << endl;
	}
}

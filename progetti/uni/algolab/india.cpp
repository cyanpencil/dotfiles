#include <bits/stdc++.h>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/edmonds_karp_max_flow.hpp>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/cycle_canceling.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/successive_shortest_path_nonnegative_weights.hpp>
#include <boost/graph/find_flow_cost.hpp>

using namespace std;
using namespace boost;

typedef adjacency_list_traits<vecS, vecS, directedS> Traits;
typedef adjacency_list<vecS, vecS, directedS, no_property,
		property<edge_capacity_t, long, 
		property<edge_residual_capacity_t, long,
		property<edge_reverse_t, Traits::edge_descriptor,
		property<edge_weight_t, long>> > > > Graph;

typedef property_map<Graph, edge_capacity_t>::type EdgeCapacityMap;
typedef property_map<Graph, edge_residual_capacity_t>::type ResidualCapacityMap;
typedef property_map<Graph, edge_reverse_t>::type ReverseEdgeMap;
typedef property_map<Graph, edge_weight_t >::type       EdgeWeightMap; // new!
typedef graph_traits<Graph>::vertex_descriptor Vertex;
typedef graph_traits<Graph>::edge_descriptor Edge;
typedef graph_traits<Graph>::out_edge_iterator  OutEdgeIt; // Iterator


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



int main () {
    int t;
    cin >> t;
    while (t--) {
		int c,g,b,k,a;
		cin >> c >> g >> b >> k >> a;

		Graph G(c+3);
		int source = c+1, target = c+2;
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		EdgeWeightMap weightmap = boost::get(boost::edge_weight, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);
		EdgeAdder eaG(G, capacitymap, weightmap, revedgemap);

		for (int i = 0; i < g; i++) {
			int x, y,d,e;
			cin >> x >> y >> d >> e;
			eaG.addEdge(x, y, e, d);
		}
		//cout << "finish" << endl;

		int sol = 1;
		int count = 3;
		int mmin = 0, mmax = 9999999;
		//while (true) {
			//int ss = c + count++;
			//eaG.addEdge(ss, k, sol, 0);

			//int flow1 = push_relabel_max_flow(G, ss, a);
			//cycle_canceling(G);
			//int cost1 = find_flow_cost(G);

			//cout << cost1 << " - " << flow1 << endl;

			//if (cost1 < b) {
				//mmin = sol;
				//sol *= 2;
			//} else {
				//mmax = sol;
				//break;
			//}
		//}

		//cout << "then " << endl;

		int flow1, cost1, gflow = -1;

		while (mmin != mmax) {
			int m = 1 + (mmax+mmin)/2;
			sol = m;

			count++;
			int ss = c + count;
			eaG.addEdge(ss, k, sol, 0);

			successive_shortest_path_nonnegative_weights(G, ss, a);

			int s_flow = 0;
			OutEdgeIt e, eend;
			for(boost::tie(e, eend) = boost::out_edges(boost::vertex(ss,G), G); e != eend; ++e) {
				s_flow += capacitymap[*e] - rescapacitymap[*e];
			}
			cost1 = find_flow_cost(G);

			//cout << cost1 << " - " << s_flow << "(" << mmin << "," << mmax << ")" << endl;

			if (cost1 <= b) {
				mmin = sol;
				gflow = s_flow;
			} else {
				mmax = min(s_flow, sol - 1);
			}
		}

		cout << (gflow == -1? 0 : gflow) << endl;

    }
}

#include <bits/stdc++.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/tuple/tuple.hpp>

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

typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::undirectedS, boost::no_property,
		boost::property<boost::edge_capacity_t, long> >	Graph2;
typedef	boost::property_map<Graph2, boost::edge_capacity_t>::type	EdgeCapacityMap2;
typedef	boost::graph_traits<Graph2>::edge_descriptor			Edge2;
typedef	boost::graph_traits<Graph2>::out_edge_iterator			OutEdgeIt2;
typedef	boost::graph_traits<Graph2>::in_edge_iterator			InEdgeIt2;

void addEdge(int from, int to, long capacity , Graph &G, EdgeCapacityMap	&capacitymap, ReverseEdgeMap	&revedgemap) { 
	Edge e, rev_e;
	bool success;
	boost::tie(e, success) = boost::add_edge(from, to, G);
	boost::tie(rev_e, success) = boost::add_edge(to, from, G);
	capacitymap[e] = capacity;
	capacitymap[rev_e] = 0;
	revedgemap[e] = rev_e;
	revedgemap[rev_e] = e;
}


using namespace std;

int main () {
    int t;
    cin >> t;
	int g, s, l;
    while (t--) {
		cin >> g >> s >> l;

		int source = g+s+1;
		int target = g+s;


		Graph G(g + s + 2);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);

		for (int i = 0; i < l; i++) {
			int a, b;
			cin >> a >> b;
			addEdge(a, g + b, 1, G, capacitymap, revedgemap);
			//addEdge(g + b, a, 1, G, capacitymap, revedgemap);
		}

		for (int i = 0; i < g; i++) addEdge(source, i, 1, G, capacitymap, revedgemap);
		for (int i = 0; i < s; i++) addEdge(g+i, target, 1, G, capacitymap, revedgemap);



		// Find a min cut via maxflow
		int flow = boost::push_relabel_max_flow(G, source, target);
		//std::cout << "maximum flow = minimum cut = " << flow << std::endl;

		Graph2 GG(g+s);
		EdgeCapacityMap2 capacitymap2 = boost::get(boost::edge_capacity, GG);

		// BFS to find vertex set S
		std::vector<int> vis(g+s, false); // visited flags
		std::queue<int> Q; // BFS queue (from std:: not boost::)
		for (int i = 0; i < g; i++) {
			bool found = false;
			OutEdgeIt ebeg, eend;
			for (boost::tie(ebeg, eend) = boost::out_edges(i, G); ebeg != eend; ++ebeg) {
				Edge2 e;
				bool success;
				boost::tie(e, success) = boost::add_edge(i, boost::target(*ebeg, G), GG);
				if (capacitymap[*ebeg] - rescapacitymap[*ebeg] > 0) {
					found = true; 
					capacitymap2[e] = 1;
				} else {
					capacitymap2[e] = 0;
				}
			}
			if (!found) {
				Q.push(i);
				//cout << "visito " << i << endl;
				vis[i] = true;
			}
		}
		while (!Q.empty()) {
			int u = Q.front();
			Q.pop();
			if (u < g) { //LEFT
				OutEdgeIt2 ebeg, eend;
				for (boost::tie(ebeg, eend) = boost::out_edges(u, GG); ebeg != eend; ++ebeg) {
					int v = boost::target(*ebeg, GG);
					if (v >= g+s) continue;
					if (vis[v]) continue;
					if (capacitymap2[*ebeg] > 0) { // NOT in the matching
						continue;
					}
					Q.push(v);
					//cout << v << endl;
					vis[v] = true;
				}
			} else { //RIGHT
				OutEdgeIt2 ebeg, eend;
				for (boost::tie(ebeg, eend) = boost::out_edges(u, GG); ebeg != eend; ++ebeg) {
					int v = boost::target(*ebeg, GG);
					if (v >= g+s) continue;
					if (vis[v]) continue;
					if (capacitymap2[*ebeg] == 0) { // in the matching
						continue;
					}
					Q.push(v);
					//cout << v << endl;
					vis[v] = true;
				}
			}
		}

		//cout << "end" << endl;

		vector<int> cose_left;
		vector<int> cose_right;
		for (int i = 0; i < g; i++) {
			if (!vis[i]) cose_left.push_back(i);
		}
		for (int i = g; i < g+s; i++) {
			if (vis[i]) cose_right.push_back(i);
		}

		//for (int i = 0; i < g+s; i++) cout << vis[i] << " ";
		//cout << endl;

		cout << cose_left.size() << " " << cose_right.size() << endl;
		for (auto i : cose_left) cout << i << " ";
		for (auto j : cose_right) cout << j - g << " ";
		cout << endl;
    }
}

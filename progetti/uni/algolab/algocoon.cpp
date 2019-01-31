#include <bits/stdc++.h>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/tuple/tuple.hpp>

typedef	boost::adjacency_list_traits<boost::vecS, boost::vecS, boost::directedS> Traits;
typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::directedS, boost::no_property,
	boost::property<boost::edge_capacity_t, long,
		boost::property<boost::edge_residual_capacity_t, long,
			boost::property<boost::edge_reverse_t, Traits::edge_descriptor> > > >	Graph;
typedef	boost::property_map<Graph, boost::edge_capacity_t>::type		EdgeCapacityMap;
typedef	boost::property_map<Graph, boost::edge_residual_capacity_t>::type	ResidualCapacityMap;
typedef	boost::property_map<Graph, boost::edge_reverse_t>::type		ReverseEdgeMap;
typedef	boost::graph_traits<Graph>::vertex_descriptor			Vertex;
typedef	boost::graph_traits<Graph>::edge_descriptor			Edge;
typedef	boost::graph_traits<Graph>::out_edge_iterator			OutEdgeIt;

class EdgeAdder {
	Graph &G;
	EdgeCapacityMap	&capacitymap;
	ReverseEdgeMap	&revedgemap;

public:
	// to initialize the Object
	EdgeAdder(Graph & G, EdgeCapacityMap &capacitymap, ReverseEdgeMap &revedgemap):
		G(G), capacitymap(capacitymap), revedgemap(revedgemap){}

	// to use the Function (add an edge)
	void addEdge(int from, int to, long capacity) {
		Edge e, rev_e;
		bool success;
		boost::tie(e, success) = boost::add_edge(from, to, G);
		boost::tie(rev_e, success) = boost::add_edge(to, from, G);
		capacitymap[e] = capacity;
		capacitymap[rev_e] = 0;
		revedgemap[e] = rev_e;
		revedgemap[rev_e] = e;
	}
};

using namespace std;

int main () {
    int t;
    cin >> t;
    while (t--) {
		int n,m;
		cin >> n >> m;
		Graph G(n+3);
		EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
		ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
		ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);
		EdgeAdder eaG(G, capacitymap, revedgemap);

		int src = n+1;
		int sink = n+2;

		for (int i = 0; i < m; i++) {
			int a,b,c;
			cin >> a >> b >> c;
			eaG.addEdge(a,b,c);
		}

		int min_flo = 999999999;
		int where = 0;

		//cout << "yo..." << endl;

		for (int i = 0; i < n; i++) {
			int b = i+1;
			if (i == n-1) {
				b = 0;
			}
			eaG.addEdge(src, i, 999999999);
			eaG.addEdge(b, sink, 999999999);

			int flow = boost::push_relabel_max_flow(G, src, sink);
			if (flow < min_flo) {
				min_flo = flow;
				where = i;
			}

			boost::remove_edge(src, i, G);
			boost::remove_edge(i, src, G);
			boost::remove_edge(b, sink, G);
			boost::remove_edge(sink, b, G);
		}

		//cout << "finished" << endl;

		int b = where+1;
		if (b == n) b = 0;

		eaG.addEdge(src, where, 999999999);
		eaG.addEdge(b, sink, 999999999);
		int flow = boost::push_relabel_max_flow(G, src, sink);

		cout << flow << endl;
			

		std::vector<int> vis(2*n, false); // visited flags
		std::queue<int> Q; // BFS queue (from std:: not boost::)
		vis[src] = true; // Mark the source as visited
		Q.push(src);
		int count = 0;
		//cout << "starting" << endl;
		while (!Q.empty()) {
			const int u = Q.front();
			Q.pop();
			OutEdgeIt ebeg, eend;
			for (boost::tie(ebeg, eend) = boost::out_edges(u, G); ebeg != eend; ++ebeg) {
				const int v = boost::target(*ebeg, G);
				// Only follow edges with spare capacity
				if (rescapacitymap[*ebeg] == 0 || vis[v]) continue;
				vis[v] = true;
				count++;
				Q.push(v);
			}
		}

		// Output S
		cout << count << " ";
		for (int i = 0; i < n; ++i) {
			if (vis[i]) std::cout << i << " ";
		}
		std::cout << std::endl;
    }
}

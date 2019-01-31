#include <bits/stdc++.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>
#include <boost/graph/push_relabel_max_flow.hpp>
#include <boost/graph/edmonds_karp_max_flow.hpp>

using namespace std;
using namespace boost;

typedef adjacency_list<vecS, vecS, directedS,
		no_property,
		property<edge_weight_t, int>
		>					Graph;
typedef graph_traits<Graph>::vertex_descriptor		Vertex;	// Vertex type		
typedef graph_traits<Graph>::edge_descriptor		Edge;	// Edge type
typedef graph_traits<Graph>::edge_iterator		EdgeIt;	// Edge iterator
typedef property_map<Graph, edge_weight_t>::type	WeightMap;



typedef	boost::adjacency_list_traits<boost::vecS, boost::vecS, boost::directedS> Traits;
typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::directedS, boost::no_property,
	boost::property<boost::edge_capacity_t, long,
		boost::property<boost::edge_residual_capacity_t, long,
			boost::property<boost::edge_reverse_t, Traits::edge_descriptor> > > >	FGraph;

typedef	boost::property_map<FGraph, boost::edge_capacity_t>::type		EdgeCapacityMap;
typedef	boost::property_map<FGraph, boost::edge_residual_capacity_t>::type	ResidualCapacityMap;
typedef	boost::property_map<FGraph, boost::edge_reverse_t>::type		ReverseEdgeMap;
typedef	boost::graph_traits<FGraph>::vertex_descriptor			FVertex;
typedef	boost::graph_traits<FGraph>::edge_descriptor			FEdge;
typedef	boost::graph_traits<FGraph>::edge_iterator			FEdgeIt;

class EdgeAdder {
	FGraph &G;
	EdgeCapacityMap	&capacitymap;
	ReverseEdgeMap	&revedgemap;

public:
	EdgeAdder(FGraph & G, EdgeCapacityMap &capacitymap, ReverseEdgeMap &revedgemap):
		G(G), capacitymap(capacitymap), revedgemap(revedgemap){}

	void addEdge(int from, int to, long capacity) {
		FEdge e, rev_e;
		bool success;
		boost::tie(e, success) = boost::add_edge(from, to, G);
		boost::tie(rev_e, success) = boost::add_edge(to, from, G);
		capacitymap[e] = capacity;
		capacitymap[rev_e] = 0; // reverse edge has no capacity!
		revedgemap[e] = rev_e;
		revedgemap[rev_e] = e;
	}
};

int main () {
    int t;
    cin >> t;
    while (t--) {
		int n,m,a,s,c,d;
		cin >> n >> m >> a >> s >> c >> d;

		int agents[a], shelters[s];

		Graph GT(n);	// Creates an empty graph on V vertices
		WeightMap weightmap = get(edge_weight, GT);
		for (int i = 0; i < m; ++i) {
			char c2;
			int x,y,z;
			cin >> c2 >> x >> y >> z;
			Edge e;	bool success;				
			tie(e, success) = add_edge(x, y, GT);	
			weightmap[e] = z;
			if (c2 == 'L') {
				tie(e, success) = add_edge(y, x, GT);
				weightmap[e] = z;
			}
		}
		for (int i = 0; i < a; i++) cin >> agents[i];
		for (int i = 0; i < s; i++) cin >> shelters[i];


		vector<vector<int> > dists;		
		for (int i = 0; i < a; i++) {
			vector<int> distmap(n);
			vector<Vertex> predmap(n);	

			dijkstra_shortest_paths(GT, agents[i], predecessor_map(make_iterator_property_map(	predmap.begin(), get(vertex_index, GT))).  distance_map(make_iterator_property_map(	distmap.begin(), get(vertex_index, GT))));

			for (int j = 0; j < n; j++) if (predmap[j] == j) distmap[j] = 100000000;
			distmap[agents[i]] = 0;

			dists.push_back(distmap);
		}

		//for (int i = 0; i < a; i++)  {
			//cerr << a << endl;
			//for (int j = 0; j < dists[i].size(); j++) cout << dists[i][j] << " ";
			//cout << endl;
		//}
		//cout << endl;
		//cout << endl;
		//cout << endl;

		int start = 0, end = 200000000;
		while (start != end) {
			//cerr << "Trying with " << start << " and " << end << endl;
			int m = (start + end)/2 + 1;



			FGraph G(a + c*s + 10);
			EdgeCapacityMap capacitymap = boost::get(boost::edge_capacity, G);
			ReverseEdgeMap revedgemap = boost::get(boost::edge_reverse, G);
			ResidualCapacityMap rescapacitymap = boost::get(boost::edge_residual_capacity, G);
			EdgeAdder eaG(G, capacitymap, revedgemap);

			int source = a + c*s + 1;
			int target = a + c*s + 2;
			for (int i = 0; i < a; i++) eaG.addEdge(source, i, 1);
			for (int i = 0; i < s; i++) eaG.addEdge(a+i, target, 1);
			if (c == 2) for (int i = 0; i < s; i++) eaG.addEdge(a+s+i, target, 1);

			for (int i = 0; i < a; i++) {
				for (int j = 0; j < s; j++) {
					if (dists[i][shelters[j]] + d <= m) {
						eaG.addEdge(i, a+j, 1);
					}
					if (c==2 && dists[i][shelters[j]] + 2*d <= m) {
						eaG.addEdge(i, a+s+j, 1);
					}
				}
			}

			long flow1 = boost::push_relabel_max_flow(G, source, target);
			//cerr << flow1 << endl;


			if (flow1 < a) {
				start = m;
			} else {
				end = m - 1;
			}
		}

		cout << start + 1 << endl;

    }
}




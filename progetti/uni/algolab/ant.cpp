#include <bits/stdc++.h>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/kruskal_min_spanning_tree.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>

#include <boost/graph/graph_traits.hpp>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>
#include <boost/property_map/property_map.hpp>

using namespace std;

typedef boost::adjacency_list<
	boost::vecS,
	boost::vecS,
	boost::undirectedS,
	boost::no_property,
	boost::property<boost::edge_weight_t, int>
	> graph;

typedef boost::graph_traits<graph>::vertex_descriptor vertex;
typedef boost::graph_traits<graph>::edge_descriptor edge;
typedef boost::graph_traits<graph>::edge_iterator edge_it;

typedef boost::property_map<graph, boost::edge_weight_t>::type weight_map;

typedef boost::adjacency_list<
	boost::vecS,
	boost::vecS,
	boost::directedS,
	boost::no_property,
	boost::property<boost::edge_weight_t, int>
	> graph2;

typedef boost::graph_traits<graph2>::vertex_descriptor vertex2;
typedef boost::graph_traits<graph2>::edge_descriptor edge2;
typedef boost::graph_traits<graph2>::edge_iterator edge_it2;

typedef boost::property_map<graph2, boost::edge_weight_t>::type weight_map2;

int max_dist = 0;
int n, m, s, A , B;
int omg=100000;


int code (int n, int t) {
    return n*max_dist + t;
}


int main () {
	int t;
	cin >> t;
	while (t--) {
		cin >> n >> m >> s >> A >> B;
		graph2 T(omg);
		vector<graph> G;
		weight_map2 weightmappa = boost::get(boost::edge_weight, T);
		for (int i = 0; i < s; i++) G.push_back (graph(n));
		vector<vector<int>> distmaps;
		vector<weight_map> weightmap;
		for (int i = 0; i < s; i++) weightmap.push_back (boost::get(boost::edge_weight, G[i]));
		int a, b, c;
		for (int i = 0; i < m; i++) {
			cin >> a >> b;
			for (int i = 0; i < s; i++) {
				cin >> c;
				edge e; bool success;
				boost::tie (e, success) = boost::add_edge(a, b, G[i]);
				weightmap[i][e] = c;
			}
		}
		int hives [s];
		max_dist = 0;
		for (int i = 0; i < s; i++) { 
			cin >> hives[i];
			vector<int> distmap(10*n);
			vector<vertex> predmap(10*n);
			cout << hives[i] << endl;
			boost::dijkstra_shortest_paths(G[i],hives[i],predecessor_map(boost::make_iterator_property_map(predmap.begin(), boost::get(boost::vertex_index, G[i]))).distance_map(boost::make_iterator_property_map(distmap.begin(), boost::get(boost::vertex_index, G[i]))));
			distmaps.push_back(distmap);
			for (int q : distmap) max_dist = max(max_dist, 2*q);
		}

		bool visitato [n];
		memset (visitato, 0, sizeof visitato);
		for (int i = 0; i < n; i++) {
			cout << "edge " << i << endl;
			for (int t = 0; t+1 < max_dist; t++) {
				edge2 e; bool success;
				boost::tie (e, success) = boost::add_edge(code(i, t), code(i, t+1), T);
				//cout << "added edge from " << code(i,t) << " to " << code(i, t+1) << endl;
				weightmappa[e] = 1;
				for (int ss = 0; ss < s; ss++) {
					auto zz = boost::in_edges(i, G[ss]);
					for (auto z = zz.first; z != zz.second; z++) {
						int altro = -1;
						if (boost::source(*z, G[ss]) == i) altro = boost::target(*z, G[ss]);
						else altro = boost::source(*z, G[ss]);
						//if (!visitato[altro]) {
						//cout << "provo " << code(i,t) << " to " << altro << endl;;
						if (distmaps[ss][i] <= t) {
							edge2 e2; bool success;
							if (t+weightmap[ss][*z] >= max_dist) continue;
							boost::tie (e2, success) = boost::add_edge(code(i, t), code(altro, t+weightmap[ss][*z]), T);
							cout << "Added edge from " << code(i,t) << " to " << code(altro, t+weightmap[ss][*z]) << endl;
							weightmappa[e2] = weightmap[ss][*z];
						}
						//}
					}
				}
			}
			visitato[i] = true;
		}

		cout << "max_dist " << max_dist << endl;

		vector<int> distmap2(omg);
		vector<vertex> predmap2(omg);
		boost::dijkstra_shortest_paths(T,code(A,0),predecessor_map(boost::make_iterator_property_map(predmap2.begin(), boost::get(boost::vertex_index, T))).distance_map(boost::make_iterator_property_map(distmap2.begin(), boost::get(boost::vertex_index, T))));
		for (int i = 0; i < max_dist; i++) {
			cout << code(B, i) << ", dist:" << distmap2[code(B, i)] << endl;
		}
	}
}

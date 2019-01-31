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




int n, m, s, A, B;

int main () {
    int t;
    cin >> t;
    while (t--) {
	cin >> n >> m >> s >> A >> B;
	graph T(n);
	vector<graph> G;
	weight_map weightmappa = boost::get(boost::edge_weight, T);
	for (int i = 0; i < s; i++) G.push_back (graph(n));
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
	for (int i = 0; i < s; i++) {
	    cin >> c;
	    vector<edge> mst;
	    boost::kruskal_minimum_spanning_tree (G[i], back_inserter(mst));
	    for (auto e : mst) {
		    edge t; bool success;
		    //cout << "added" << e << weightmap[i][e] << endl;
		    bool found = false;
		    auto zz = boost::in_edges(e.m_source, T);
		    for (auto z = zz.first; z != zz.second; z++) {
			if ((*z).m_source == e.m_source) {
			    if ((*z).m_target == e.m_target) {
				    weightmappa[*z] = min(weightmappa[*z], weightmap[i][e]);
				    found = true;
				    break;
			    }
			}
		    }
		    if (!found) {
			    boost::tie (t, success) = boost::add_edge(boost::source(e, G[i]), boost::target(e, G[i]), T);
			    weightmappa[t] = weightmap[i][e];
		    }
	    }
	}
	vector<int> distmap(2*n);
	vector<vertex> predmap(2*n);
	boost::dijkstra_shortest_paths(T,A,predecessor_map(boost::make_iterator_property_map(predmap.begin(), boost::get(boost::vertex_index, T))).distance_map(boost::make_iterator_property_map(distmap.begin(), boost::get(boost::vertex_index, T))));

	////for (int i = 0; i < n; i++) cout << distmap[i] << endl;
	cout << distmap[B] << endl;
    }
}


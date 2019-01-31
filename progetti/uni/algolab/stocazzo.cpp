#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>
#include <boost/graph/kruskal_min_spanning_tree.hpp>

typedef CGAL::Exact_predicates_inexact_constructions_kernel K;
typedef CGAL::Delaunay_triangulation_2<K>  Triangulation;
typedef Triangulation::Edge_iterator  Edge_iterator;

// BGL Graph definitions
typedef boost::adjacency_list<boost::vecS, boost::vecS, boost::undirectedS, boost::no_property,	
		boost::property<boost::edge_weight_t, K::FT> 		>					Graph;
typedef boost::graph_traits<Graph>::edge_descriptor		Edge;		
typedef boost::graph_traits<Graph>::vertex_descriptor		Vertex;	
typedef boost::graph_traits<Graph>::edge_iterator		EdgeIt;	
typedef boost::property_map<Graph, boost::edge_weight_t>::type	WeightMap;

double ceil_to_double(K::FT a) {
	double x = ceil(CGAL::to_double(a));
	while (x - 1 > a) x--;
	while (x < a) x++;
	return x;
}

using namespace std;

int main () {
    int t;
    cin >> t;
    while (t--) {
		long long n, m, p;
		cin >> n >> m >> p;
		int x[n], y[n];
		int m_x[n], m_y[n];
		int M_x[n], M_y[n];
		vector<K::Point_2> pts;
		vector<K::Point_2> mssn;
		vector<K::Point_2> MSSN;
		for (int i = 0; i < n; i++) {
			cin >> x[i] >> y[i];
			pts.push_back(K::Point_2(x[i], y[i]));
		}
		for (int i = 0; i < m; i++) {
			cin >> m_x[i] >> m_y[i] >> M_x[i] >> M_y[i];
			mssn.push_back(K::Point_2(m_x[i], m_y[i]));
			MSSN.push_back(K::Point_2(M_x[i], M_y[i]));
		}
		Triangulation t;
		t.insert(pts.begin(), pts.end());

		Graph G(n);
		WeightMap weightmap = boost::get(boost::edge_weight, G);


		map<K::Point_2, int> mappa;

		for (Edge_iterator e = t.finite_edges_begin(); e != t.finite_edges_end(); ++e) {
			K::Point_2 a = (*e).first->vertex(((*e).second + 1) % 3)->point();
			K::Point_2 b = (*e).first->vertex(((*e).second + 2) % 3)->point();
			auto mydist = CGAL::squared_distance(a, b);

			if (mappa.find(a) == mappa.end()) {
				mappa[a] = mappa.size();
			}
			if (mappa.find(b) == mappa.end()) {
				mappa[b] = mappa.size();
			}

			Edge ed;	bool success;					
			tie(ed, success) = add_edge(mappa[a], mappa[b], G);	
			weightmap[ed] = mydist;
		}




		Graph GG(n);
		WeightMap ww2 = boost::get(boost::edge_weight, G);




		std::vector<Edge>	mst; 
		std::vector<Vertex>	kruskalpredmap(n);	
		std::vector<int> rankmap(n);
		boost::kruskal_minimum_spanning_tree(G, std::back_inserter(mst),	
		  boost::rank_map(boost::make_iterator_property_map(rankmap.begin(), boost::get(boost::vertex_index, G))).
		  predecessor_map(boost::make_iterator_property_map(kruskalpredmap.begin(), boost::get(boost::vertex_index, G))));
		long long totalweight = 0;
		std::vector<Edge>::iterator	mstbeg, mstend = mst.end();
		for (mstbeg = mst.begin(); mstbeg != mstend; ++mstbeg) {
			totalweight += weightmap[*mstbeg];
		}
		for (size_t i = 0; i < mst.size(); ++i) {
			Edge e = mst[i];
			Vertex u = boost::source(e, G);
			Vertex v = boost::target(e, G);

			Edge ed; bool success;					
			tie(ed, success) = add_edge(u, v, GG);	
			ww2[ed] = weightmap[e];
		}

		K::FT maxxx[m] = {0};

		for (int z = 0; z < m; z++) {

			K::Point_2 source_point = t.nearest_vertex(mssn[z])->point();
			K::Point_2 target_point = t.nearest_vertex(MSSN[z])->point();

			maxxx[z] = max(CGAL::squared_distance(source_point, mssn[z]), CGAL::squared_distance(target_point, MSSN[z]));

			if (source_point == target_point) continue;

			vector<K::FT> distmap(n);		
			vector<Vertex> predmap(n);	
			dijkstra_shortest_paths(GG, mappa[source_point],
			  boost::predecessor_map(boost::make_iterator_property_map(predmap.begin(), get(boost::vertex_index, GG))).
			  distance_map(boost::make_iterator_property_map(distmap.begin(), get(boost::vertex_index, GG))));

			Vertex curr = mappa[target_point];
			Vertex prev = predmap[curr];
			while (curr != prev) {
				maxxx[z] = max(maxxx[z], (distmap[curr] - distmap[prev])/4.0);
				curr = prev;
				prev = predmap[curr];
			}
		}

		for (int z = 0; z < m; z++) {
			K::FT real = 4*maxxx[z];
			if (real <= p) {
				cout << 'y';
			} else {
				cout << 'n';
			}
		}
		cout << endl;
		K::FT real_maximum = 0;
		for (int z = 0; z < m; z++) {
			real_maximum = max(real_maximum, maxxx[z]);
		}
		cout << (long long)ceil_to_double(4*real_maximum) << endl;
		K::FT less_real_maximum = 0;
		for (int z = 0; z < m; z++) {
			K::FT real = 4*maxxx[z];
			if (real <= p) {
				less_real_maximum = max(less_real_maximum, real);
			} 
		}
		cout << (long long) ceil_to_double(less_real_maximum) << endl;
    }
}

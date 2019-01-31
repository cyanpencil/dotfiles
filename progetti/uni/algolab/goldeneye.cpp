#include <bits/stdc++.h>
#include <CGAL/Exact_predicates_inexact_constructions_kernel.h>
#include <CGAL/Delaunay_triangulation_2.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>
#include <boost/graph/dag_shortest_paths.hpp>
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
typedef boost::graph_traits<Graph>::out_edge_iterator		OutEdgeIt;	// to iterate over all outgoing edges of a vertex


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
		int a, b, c, d;
		vector<K::Point_2> pts;
		vector<K::Point_2> mssn;
		vector<K::Point_2> MSSN;
		for (int i = 0; i < n; i++) {
			cin >> a >> b;
			pts.push_back(K::Point_2(a, b));
		}
		for (int i = 0; i < m; i++) {
			cin >> a >> b >> c >> d;
			mssn.push_back(K::Point_2(a, b));
			MSSN.push_back(K::Point_2(c, d));
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
				//cout << "Aggiunto il punto " << a << " con counter " << mappa[a] << endl;
			}
			if (mappa.find(b) == mappa.end()) {
				mappa[b] = mappa.size();
				//cout << "Aggiunto il punto " << b << " con counter " << mappa[b] << endl;
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
		//std::cout << "Kruskal builds a minimum spanning tree of total weight: ";
		//std::cout << totalweight << "\n";
		//std::cout << "Edges in Kruskal's minimum spanning tree:\n";
		for (size_t i = 0; i < mst.size(); ++i) {
			Edge e = mst[i];
			Vertex u = boost::source(e, G);
			Vertex v = boost::target(e, G);
			//std::cout << u << " -- " << v << " (weight " << weightmap[e] << ")\n";


			Edge ed; bool success;					
			tie(ed, success) = add_edge(u, v, GG);	
			//cout << "oooh "<< u << v << endl;
			ww2[ed] = weightmap[e];
		}
		//std::cout << std::endl;

		K::FT maxxx[m] = {0};

		for (int z = 0; z < m; z++) {

			K::Point_2 source_point = t.nearest_vertex(mssn[z])->point();
			K::Point_2 target_point = t.nearest_vertex(MSSN[z])->point();

			maxxx[z] = max(CGAL::squared_distance(source_point, mssn[z]), CGAL::squared_distance(target_point, MSSN[z]));

			if (source_point == target_point) continue;

			K::FT ddd[n] = {0};

			queue<pair<Vertex, K::FT>> q;
			Vertex curr = mappa[source_point];
			q.push(make_pair(curr, 0));
			while (q.size() > 0) {
				Vertex v = q.front().first;
				K::FT dist = q.front().second;
				q.pop();

				OutEdgeIt oebeg, oeend;
				for (boost::tie(oebeg, oeend) = boost::out_edges(v, GG); oebeg != oeend; ++oebeg) {
					if (dist + weightmap[*oebeg] > ddd[boost::target(*oebeg, GG)]) {
						ddd[boost::target(*oebeg, GG)] = dist + weightmap[*oebeg];
						q.push(make_pair(boost::target(*oebeg, GG), dist + weightmap[*oebeg]));
					}
				}
				if (ddd[mappa[target_point]] != 0) break;
				cout << q.size() << endl;
			}
			maxxx[z] = max(maxxx[z], (ddd[mappa[target_point]])/4.0);

			//vector<K::FT> distmap(n);		
			//vector<Vertex> predmap(n);	
			//dag_shortest_paths(GG, mappa[source_point],
			  //boost::predecessor_map(boost::make_iterator_property_map(predmap.begin(), get(boost::vertex_index, GG))).
			  //distance_map(boost::make_iterator_property_map(distmap.begin(), get(boost::vertex_index, GG))));

			//Vertex curr = mappa[target_point];
			//Vertex prev = predmap[curr];
			//while (curr != prev) {
				//maxxx[z] = max(maxxx[z], (distmap[curr] - distmap[prev])/4.0);
				//curr = prev;
				//prev = predmap[curr];
			//}
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
		K::FT stupid_maximum = 0;
		for (int z = 0; z < m; z++) {
			stupid_maximum = max(stupid_maximum, maxxx[z]);
		}
		cout << (long long)ceil_to_double(4*stupid_maximum) << endl;
		K::FT less_stupid_maximum = 0;
		for (int z = 0; z < m; z++) {
			K::FT real = 4*maxxx[z];
			if (real <= p) {
				less_stupid_maximum = max(less_stupid_maximum, real);
			} 
		}
		cout << (long long) ceil_to_double(less_stupid_maximum) << endl;
    }
}

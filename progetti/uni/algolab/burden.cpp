#include <bits/stdc++.h>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/strong_components.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>

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




int k;
int w[2001][2001];

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		cin >> k;

		Graph GT(k*(k/2));	// Creates an empty graph on V vertices
		WeightMap weightmap = get(edge_weight, GT);	

		int count = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j <= i; j++) {
				cin >> w[i][j];

				// laterali
				if (j > 0) {
					Edge e;	bool success;			
					tie(e, success) = add_edge(count, count-1, GT);	
					weightmap[e] = w[i][j];
				}
				if (j < i) {
					Edge e;	bool success;			
					tie(e, success) = add_edge(count, count+1, GT);	
					weightmap[e] = w[i][j];
				}

				if (i > 0) {
					//altosinistra
					if (j > 0) {
						Edge e;	bool success;			
						tie(e, success) = add_edge(count, count-(i+1), GT);	
						weightmap[e] = w[i][j];
					}
					//altodestra
					if (j < i) {
						Edge e;	bool success;			
						tie(e, success) = add_edge(count, count-i, GT);	
						weightmap[e] = w[i][j];
					}
				}

				if (i < k-1) {
					//bassosinistra
					Edge e;	bool success;			
					tie(e, success) = add_edge(count, count+(i+1), GT);	
					weightmap[e] = w[i][j];

					//bassodestra
					tie(e, success) = add_edge(count, count+(i+1)+1, GT);	
					weightmap[e] = w[i][j];
				}
				count++;
			}
		}

		long long total = k*(k+1)/2;
		long long uno = ((k-1)*(k))/2;
		long long due = (k*(k+1))/2 - 1;

		vector<long long> distmap_0(total);
		vector<Vertex> predmap(total);	
		dijkstra_shortest_paths(GT, 0, predecessor_map(make_iterator_property_map(	predmap.begin(), get(vertex_index, GT))).  distance_map(make_iterator_property_map(	distmap_0.begin(), get(vertex_index, GT))));

		vector<long long> distmap_1(total);
		dijkstra_shortest_paths(GT, uno, predecessor_map(make_iterator_property_map(	predmap.begin(), get(vertex_index, GT))).  distance_map(make_iterator_property_map(	distmap_1.begin(), get(vertex_index, GT))));

		vector<long long> distmap_2(total);
		dijkstra_shortest_paths(GT, due, predecessor_map(make_iterator_property_map(	predmap.begin(), get(vertex_index, GT))).  distance_map(make_iterator_property_map(	distmap_2.begin(), get(vertex_index, GT))));

		long long minsol = 99999999999999;
		int c = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j <= i; j++) {
				minsol = min(minsol, w[i][j] + distmap_0[c] + distmap_1[c] + distmap_2[c]);
				c++;
			}
		}

		c = 0;
		//for (int i = 0; i < k; i++) {
			//for (int j = 0; j <= i; j++) {
				//cout << distmap_1[c] + w[i][j] << " ";
				//c++;
			//}
			//cout << endl;
		//}

		cout << minsol << endl;
    }
}

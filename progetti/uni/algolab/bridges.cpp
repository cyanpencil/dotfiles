#include <bits/stdc++.h>
#include <boost/config.hpp>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/kruskal_min_spanning_tree.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>

#include <boost/graph/graph_traits.hpp>
#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/dijkstra_shortest_paths.hpp>
#include <boost/property_map/property_map.hpp>

#include <boost/graph/biconnected_components.hpp>
#include <boost/graph/adjacency_list.hpp>

namespace boost {
    struct edge_component_t {
	enum {num = 999999999};
	typedef edge_property_tag kind;
    } edge_component;
}

typedef boost::adjacency_list<
	boost::vecS,
	boost::vecS,
	boost::undirectedS,
	boost::no_property,
	boost::property<boost::edge_component_t, int>
	> Graph;

typedef boost::graph_traits<Graph>::vertex_descriptor Vertex;
typedef boost::graph_traits<Graph>::edge_descriptor Edge;
typedef boost::graph_traits<Graph>::edge_iterator edge_it;



int n, m;

int main () {
    using namespace boost;
    int t;
    std::cin >> t;
    while (t--) {
	std::cin >> n >> m;
	Graph G;
	int a, b;
	for (int i = 0; i < m; i++) {
	    std::cin >> a >> b;
	    Edge e; bool success;
	    if (a < b) {
		boost::tie (e, success) = boost::add_edge(a,b,  G);
	    } else {
		    boost::tie (e, success) = boost::add_edge(b,a,  G);
	    }
	}


	property_map < Graph, edge_component_t >::type comp = boost::get(edge_component, G);
	int num_comps = biconnected_components(G, comp);

	std::vector<Vertex> art_points;
	articulation_points(G, std::back_inserter(art_points));

	std::vector<Edge> eee;
	std::vector<int> conta(2*n);
	for (int i = 0; i < 2*n; i++) conta[i] = 0;

	edge_it ei, ei_end;
	for (boost::tie(ei, ei_end) = edges(G); ei != ei_end; ++ei) {
	    conta[comp[*ei]]++;
	}
	for (boost::tie(ei, ei_end) = edges(G); ei != ei_end; ++ei) {
	    if (conta[comp[*ei]] <= 1) {
		eee.push_back(*ei);
	    }
	}
	sort(eee.begin(), eee.end(), [](Edge a, Edge b) {
	  if (a.m_source == b.m_source) return a.m_target < b.m_target;
	  else  return a.m_source < b.m_source;
	});

	std::cout << eee.size() << std::endl;

	for (auto e : eee) {
	    //if (e.m_source < e.m_target) 
		    std::cout << e.m_source << " " << e.m_target << std::endl;
	    //else 
		    //std::cout << e.m_target << " " << e.m_source << std::endl;
	}



	

  //std::cout << "graph A {\n" << "  node[shape=\"circle\"]\n";

  //for (std::size_t i = 0; i < art_points.size(); ++i) {
    //std::cout << (char)(art_points[i] + 'A')
              //<< " [ style=\"filled\", fillcolor=\"red\" ];"
              //<< std::endl;
  //}

  //graph_traits < graph_t >::edge_iterator ei, ei_end;
  //for (boost::tie(ei, ei_end) = edges(g); ei != ei_end; ++ei)
    //std::cout << (char)(source(*ei, g) + 'A') << " -- "
              //<< (char)(target(*ei, g) + 'A')
              //<< "[label=\"" << component[*ei] << "\"]\n";
  //std::cout << "}\n";

  //return 0;
    }
}

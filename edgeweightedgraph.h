#include <vector>
#include <map>
#include <string>
#include "edge.h"

#ifndef EWGRAPH_H
#define EWGRAPH_H

class EdgeWeightedGraph
{

public:
	EdgeWeightedGraph();
	EdgeWeightedGraph(std::string filename);

	std::vector<Edge> getAdj(std::string v);
	std::vector<std::string> getVerts();

	void addEdge(std::string v, std::string w, float weight);

	std::string toDot();


protected:
	void addToList(std::string v, Edge e);

private:
	std::map<std::string, std::vector<Edge>> graph;
};

#endif

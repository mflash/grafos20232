#include <vector>
#include <map>
#include <string>

#ifndef GRAPH_H
#define GRAPH_H

class Graph
{

public:
	Graph();
	Graph(std::string filename);

	std::vector<std::string> getAdj(std::string v);
	std::vector<std::string> getVerts();

	void addEdge(std::string v, std::string w);

	std::string toDot();

protected:
	void addToList(std::string v, std::string w);

private:
	std::map<std::string, std::vector<std::string>> graph;
};

#endif

#include "digraph.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <set>

using namespace std;

void Digraph::addEdge(string v, string w)
{
	addToList(v, w);
}

string Digraph::toDot()
{
	// Usa um conjunto de arestas para evitar duplicatas
	set<string> edges;
	const string NEWLINE = "\n";
	string sb;
	sb = "graph {" + NEWLINE;
	sb += "rankdir = LR;" + NEWLINE;
	sb += "node [shape = circle];" + NEWLINE;
	for (auto const &v : getVerts())
	{
		for (auto const &w : getAdj(v))
		{
			string edge = v < w ? v + w : w + v;
			if (edges.find(edge) == edges.end())
			{
				sb += v + " -> " + w + NEWLINE;
				edges.insert(edge);
			}
		}
	}
	sb += "}" + NEWLINE;
	return sb;
}

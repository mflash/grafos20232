#include "digraph.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <set>

using namespace std;

Digraph::Digraph(string filename) {
	ifstream file;
	file.open(filename);
	string line, v, w;
	if(file.is_open()) {
		while(getline(file, line)) {
			stringstream ss(line);
			getline(ss, v, ' ');
			getline(ss, w, ' ');
  	  addEdge(v, w);
    }
    file.close();
	}
}

void Digraph::addEdge(string v, string w)
{
	addToList(v, w);
}

string Digraph::toDot()
{
	const string NEWLINE = "\n";
	string sb;
	sb = "graph {" + NEWLINE;
	sb += "rankdir = LR;" + NEWLINE;
	sb += "node [shape = circle];" + NEWLINE;
	for (auto const &v : getVerts())
	{
		for (auto const &w : getAdj(v))
		    sb += v + " -> " + w + NEWLINE;
	}
	sb += "}" + NEWLINE;
	return sb;
}

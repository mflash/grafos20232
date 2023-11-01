#include "unionfind.h"
#include <sstream>
#include <iostream>
#include <set>

using namespace std;

void UnionFind::addSet(string s)
{
	uf[s] = "-";
}

string UnionFind::find(string s)
{
	string x = uf[s];
	while (x != "-")
	{
		s = x;
		x = uf[x];
	}
	return s;
}

void UnionFind::join(string s, string t)
{
	s = find(s);
	t = find(t);
	uf[t] = s;
}

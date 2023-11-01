#include <vector>
#include <map>
#include <string>
#include "edge.h"

#ifndef UFIND_H
#define UFIND_H

class UnionFind
{

public:

	void addSet(std::string s);
	std::string find(std::string s);
	void join(std::string s, std::string t);

private:
	std::map<std::string, std::string> uf;
};

#endif

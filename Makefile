CPPFLAGS = -Wall -g -std=c++11   # Opções do compilador: todos warnings e debug info

# classe Graph
appgraph = appgraph
appgraph_src = appgraph.cpp graph.cpp
appgraph_obj = $(appgraph_src:.cpp=.o)

# DFS
appdfs = appdfs
appdfs_src = appdfs.cpp graph.cpp depthfirstsearch.cpp
appdfs_obj = $(appdfs_src:.cpp=.o)

all: appgraph appdfs

$(appgraph): $(appgraph_obj)
	g++ $(CPPFLAGS) $(appgraph_obj) -o $@

$(appdfs): $(appdfs_obj)
	g++ $(CPPFLAGS) $(appdfs_obj) -o $@

clean:
	-@ rm -f $(appgraph_obj) $(appdfs_obj) $(appgraph) $(appdfs)


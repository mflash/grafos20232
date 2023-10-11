CPPFLAGS = -Wall -g -std=c++11   # Opções do compilador: todos warnings e debug info

PROG = appgraph
FONTES = appgraph.cpp graph.cpp
OBJETOS = $(FONTES:.cpp=.o)

$(PROG): $(OBJETOS)
	g++ $(CPPFLAGS) $(OBJETOS) -o $@

clean:
	-@ rm -f $(OBJETOS) $(PROG)


from graph import Graph
from queue import Queue

class BreadthFirstSearch:
    def __init__(self, g, s):
        self.s = s
        self.marked = {}
        self.edgeTo = {}
        self.distTo = {}
        self.__bfs(g, s)

    def hasPathTo(self, v):
        return v in self.marked

    def pathTo(self, v):
        path = []
        while v != self.s:
            path.insert(0, v)
            v = self.edgeTo[v]
        path.insert(0, self.s)
        return path

    def __bfs(self, g, v):
        fila = Queue()
        fila.put(v)
        self.distTo[v] = 0
        self.marked[v] = True
        while not fila.empty():
            v = fila.get()
            dist = self.distTo[v]
            for w in g.getAdj(v):
                if w not in self.marked:
                    self.marked[w] = True
                    self.edgeTo[w] = v
                    self.distTo[w] = dist + 1
                    fila.put(w)


if __name__ == "__main__":

    g = Graph("tinyG.txt")

    dfs = BreadthFirstSearch(g, "0")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        if dfs.hasPathTo(v):
            for w in dfs.pathTo(v):
                print(f"{w} ", end="")
        print()
    print()

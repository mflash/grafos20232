public class EdgeWeightedDigraph extends EdgeWeightedGraph {

  public EdgeWeightedDigraph(String filename) {
    super(filename);
  }

  @Override
  public void addEdge(String v, String w, double weight) {
    Edge e = new Edge(v, w, weight);
    addToList(v, e);
    vertices.add(v);
    vertices.add(w);
  }

  @Override
  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (Edge e : getEdges())
      sb.append(String.format("%s -> %s [label=\"%.3f\"]", e.getV(), e.getW(), e.getWeight()) + NEWLINE);
    sb.append("}" + NEWLINE);
    return sb.toString();
  }
}

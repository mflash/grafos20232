import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EdgeWeightedGraph {
  protected static final String NEWLINE = System.getProperty("line.separator");

  protected Map<String, List<Edge>> graph;
  protected Set<String> vertices;

  public EdgeWeightedGraph() {
    graph = new HashMap<>();
    vertices = new HashSet<>();
  }

  public EdgeWeightedGraph(String filename) {
    this();
    In in = new In(filename);
    String line;
    while ((line = in.readLine()) != null) {
      String[] edge = line.split(" ");
      addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
    }
    in.close();
  }

  public void addEdge(String v, String w, double weight) {
    Edge e = new Edge(v, w, weight);
    addToList(v, e);
    addToList(w, e);
    vertices.add(v);
    vertices.add(w);
  }

  public Iterable<Edge> getAdj(String v) {
    List<Edge> res = graph.get(v);
    if (res == null) res = new LinkedList<>();
    return res;
  }

  public Set<String> getVerts() {
    return vertices;
  }

  public Iterable<Edge> getEdges() {
    Set<Edge> ed = new HashSet<>();
    for (String v : getVerts().stream().sorted().toList()) {
      for (Edge e : getAdj(v)) {
        if (!ed.contains(e)) {
          ed.add(e);
        }
      }
    }
    return ed;
  }

  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("graph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (Edge e : getEdges())
      sb.append(String.format("%s -- %s [label=\"%.3f\"]", e.getV(), e.getW(), e.getWeight()) + NEWLINE);
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

  // Adiciona um vértice adjacente a outro, criando a lista
  // de adjacências caso ainda não exista no dicionário
  protected List<Edge> addToList(String v, Edge e) {
    List<Edge> list = graph.get(v);
    if (list == null)
      list = new LinkedList<>();
    list.add(e);
    graph.put(v, list);
    return list;
  }
}

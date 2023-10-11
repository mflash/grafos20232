import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
  protected static final String NEWLINE = System.getProperty("line.separator");

  protected Map<String, List<String>> graph;

  public Graph() {
    graph = new HashMap<>();
  }

  public Graph(String filename) {
    this();
    In in = new In(filename);
    String line;
    while((line = in.readLine()) != null) {
      String[] edge = line.split(" ");
      addEdge(edge[0], edge[1]);
    }
    in.close();
  }

  public void addEdge(String v, String w) {
    addToList(v, w);
    addToList(w, v);
  }

  public Iterable<String> getAdj(String v) {
    return graph.get(v);
  }

  public Set<String> getVerts() {
    return graph.keySet();
  }

  public String toDot() {
    // Usa um conjunto de arestas para evitar duplicatas
    Set<String> edges = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    sb.append("graph {"+NEWLINE);
    sb.append("rankdir = LR;"+NEWLINE);
    sb.append("node [shape = circle];"+NEWLINE);
    for(String v: getVerts().stream().sorted().toList()) {
      for (String w: getAdj(v)) {
        String edge = v.compareTo(w) > 0 ? v + w : w + v;
        if(!edges.contains(edge)) {
          sb.append(v + " -- " + w + NEWLINE);
          edges.add(edge);
        }
      }
    }
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

  // Adiciona um vértice adjacente a outro, criando a lista
  // de adjacências caso ainda não exista no dicionário
  protected List<String> addToList(String v, String w) {
    List<String> list = graph.get(v);
    if (list == null)
      list = new LinkedList<>();
    list.add(w);
    graph.put(v, list);
    return list;
  }
}

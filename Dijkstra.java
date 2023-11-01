import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class Dijkstra
{
  private Map<String, Edge> edgeTo;
  private Map<String, Double> distTo;
  private IndexMinHeap<String, Double> pq;

  public Dijkstra(EdgeWeightedDigraph g, String s) {
    edgeTo = new HashMap<>();
    distTo = new HashMap<>();
    pq = new IndexMinHeap<>();

    for(String v: g.getVerts()) {
      distTo.put(v, Double.POSITIVE_INFINITY);
    }
    distTo.put(s, 0.0);

    pq.insert(s, 0.0);
    while(!pq.isEmpty()) {
      String v = pq.delMin();
      for(Edge e: g.getAdj(v)) {
        relax(e);
      }
    }
  }

  public double distTo(String v) {
    return distTo.get(v);
  }

  public boolean hasPathTo(String v) {
    return edgeTo.get(v) != null;
  }

  public Iterable<Edge> pathTo(String v) {
    LinkedList<Edge> path = new LinkedList<>();
    Edge e = edgeTo.get(v);
    while(e != null) {
      path.add(0, e);
      e = edgeTo.get(e.getV());
    }
    return path;
  }

  private void relax(Edge e) {
    String v = e.getV();
    String w = e.getW();
    if(distTo.get(w) > distTo.get(v) + e.getWeight()) {
      distTo.put(w, distTo.get(v) + e.getWeight());
      edgeTo.put(w, e);
      if(pq.contains(w)) pq.decreaseValue(w, distTo.get(w));
      else               pq.insert(w, distTo.get(w));
    }
  }

  public static void main(String args[]) {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph("tinyEWD.txt");
    Dijkstra d = new Dijkstra(g, "0");

    for(String v: g.getVerts()) {
      System.out.print(v+": ");
      if(d.hasPathTo(v)) {
        System.out.print("("+d.distTo(v)+") ");
        for(Edge e: d.pathTo(v)) {
          System.out.print(e + " ");
        }
      }
      System.out.println();
    }

  }
}

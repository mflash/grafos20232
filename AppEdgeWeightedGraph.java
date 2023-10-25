public class AppEdgeWeightedGraph
{
  public static void main(String[] args) {
    /* 
    Graph g = new Graph();
    g.addEdge("0", "1");
    g.addEdge("0", "2");
    g.addEdge("2", "1");
    */
    EdgeWeightedGraph g = new EdgeWeightedGraph("tinyEWG.txt");

    for (String v : g.getVerts()) {
      System.out.print(v + ": ");
      for (Edge e : g.getAdj(v))
        System.out.print(e + " ");
      System.out.println();
    }

    System.out.println();
    System.out.println(g.toDot());
  }
}

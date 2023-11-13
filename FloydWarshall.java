import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* 
 * Implementação do algoritmo de Floyd-Warshall usando matriz de adjacência
 */
public class FloydWarshall {
  private static final String NEWLINE = System.getProperty("line.separator");
  private boolean temCicloNegativo; // tem ciclo negativo?
  private double[][] dist; // dist[v][w] = distancia do caminho mais curto de v->w
  private int[][] next; // next[v][w] = ultima aresta no caminho mais curto de v->w
  private AdjMatrixEdgeWeightedDigraph g;

  private long startTime, endTime;

  /**
   * Calcula uma arvore de caminhos mais curtos de todos os vertices para todos os
   * vertices
   * em um digrafo valorado {@code G}. Se houver um ciclo negativo, seta a flag e
   * retorna.
   * 
   * @param G o digrafo valorado
   */
  public FloydWarshall(AdjMatrixEdgeWeightedDigraph g) {
    int V = g.getTotalVertices();
    this.g = g;
    dist = new double[V][V]; // inicialize todos com Double.POSITIVE_INFINITY
    next = new int[V][V]; // inicialize todos com -1

    // Comeco do algoritmo...
    startTime = System.currentTimeMillis();

    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        dist[i][j] = Double.POSITIVE_INFINITY;
        next[i][j] = -1;
      }
    }

    for (Edge e : g.getDigraph().getEdges()) {
      int v = g.mapToArray(e.getV());
      int w = g.mapToArray(e.getW());
      dist[v][w] = e.getWeight();
      next[v][w] = v;
    }

    for (String u : g.getDigraph().getVerts()) {
      int v = g.mapToArray(u);
      dist[v][v] = 0;
      next[v][v] = v;
    }

    // Loop de Floyd-Warshall
    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            // System.out.println("TROCOU!");
            dist[i][j] = dist[i][k] + dist[k][j];
            next[i][j] = next[k][j];
          }
          if (dist[i][i] < 0) {
            temCicloNegativo = true;
            return;
          }
        }
      }
    }

    // Fim do algoritmo
    endTime = System.currentTimeMillis();
  }

  public long tempoTotal() {
    return endTime - startTime;
  }

  /**
   * Tem ciclo negativo?
   * 
   * @return {@code true} se existe um ciclo negativo, e {@code false} caso não
   *         exista
   */
  public boolean temCicloNegativo() {
    return temCicloNegativo;
  }

  /**
   * Existe caminho entre o vertice {@code s} e o vertice {@code t}?
   * 
   * @param s o vertice de origem
   * @param t o vertice de destino
   * @return {@code true} se existe um caminho
   */
  public boolean temCaminho(String s, String t) {
    int u = g.mapToArray(s);
    int v = g.mapToArray(t);
    return dist[u][v] < Double.POSITIVE_INFINITY;
  }

  /**
   * Retorna o comprimento do caminho mais curto do vertice {@code s} ao vertice
   * {@code t}.
   * 
   * @param s o vertice de origem
   * @param t o vertice de destino
   * @return o comprimento do caminho mais curto de {@code s} a {@code t};
   *         {@code Double.POSITIVE_INFINITY} se não houver caminho
   * @throws UnsupportedOperationException se existir um ciclo negativo
   */
  public double dist(String s, String t) {
    if (temCicloNegativo())
      throw new UnsupportedOperationException("Existe um ciclo negativo!");
    int u = g.mapToArray(s);
    int v = g.mapToArray(t);
    return dist[u][v];
  }

  /**
   * Retorna o caminho mais curto de {@code s} a {@code t}.
   * 
   * @param u o vertice de origem
   * @param v o vertice de destino
   * @return o caminho mais curto de {@code u} a {@code v}
   *         como um ArrayList de String, e {@code null} se não houver caminho
   * @throws UnsupportedOperationException se existir um ciclo negativo
   */
  public List<String> caminho(String u, String v) {
    if (temCicloNegativo())
      throw new UnsupportedOperationException("Existe um ciclo negativo!");
    if (!temCaminho(u, v))
      return null;

    List<String> path = new ArrayList<>();

    int u1 = g.mapToArray(u);
    int v1 = g.mapToArray(v);
    // Adiciona vertices no caminho...
    path.add(v);
    while (u1 != v1) {
      v1 = next[u1][v1];
      path.add(0, g.mapToString(v1));
    }
    return path;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist[i].length; j++) {
        if (next[i][j] != -1)
          sb.append(String.format("%5.2f ", dist[i][j]));
        else
          sb.append("----- ");
      }
      sb.append(NEWLINE);
    }
    return sb.toString();
  }

  /**
   * Testa a classe {@code FloydWarshall}
   * 
   */
  public static void main(String[] args) {

    // Carrega um grafo de um arquivo texto
    EdgeWeightedDigraph g = new EdgeWeightedDigraph("tinyEWD.txt");

    // ...e cria um AdjMatrixEdgeWeightedDigraph...
    AdjMatrixEdgeWeightedDigraph ag = new AdjMatrixEdgeWeightedDigraph(g);

    System.out.println(ag);

    // Executa Floyd-Warshall
    FloydWarshall spt = new FloydWarshall(ag);

    System.out.println();
    System.out.println(spt);

    // Mostra todas as distâncias dos caminhos mais curtos
    // ...

    Set<String> verts = g.getVerts();
    // Exibe mensagem se houver ciclo negativo
    if (spt.temCicloNegativo()) {
      System.out.println("Existe um ciclo negativo!");
    } else {
      // Exibe todos os caminhos
      for (String u : verts) {
        for (String v : verts) {
          if (u != v && spt.temCaminho(u, v)) {
            System.out.print(u + "->" + v + ": ");
            for (String x : spt.caminho(u, v)) {
              System.out.print(x + " ");
            }
            System.out.println();
          }
        }
      }
    }
    System.out.println();
    System.out.println("Tempo de Floyd-Warshall: " + spt.tempoTotal());
  }

}

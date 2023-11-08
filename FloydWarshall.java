import java.util.ArrayList;

/* 
 * Implementação do algoritmo de Floyd-Warshall usando matriz de adjacência
 */
public class FloydWarshall {
    private static final String NEWLINE = System.getProperty("line.separator");
    private boolean temCicloNegativo; // tem ciclo negativo?
    private double[][] dist; // dist[v][w] = distancia do caminho mais curto de v->w
    private int[][] next; // next[v][w] = ultima aresta no caminho mais curto de v->w

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
    public boolean temCaminho(int s, int t) {
        return dist[s][t] < Double.POSITIVE_INFINITY;
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
    public double dist(int s, int t) {
        if (temCicloNegativo())
            throw new UnsupportedOperationException("Existe um ciclo negativo!");
        return dist[s][t];
    }

    /**
     * Retorna o caminho mais curto de {@code s} a {@code t}.
     * 
     * @param s o vertice de origem
     * @param t o vertice de destino
     * @return o caminho mais curto de {@code s} a {@code t}
     *         como um ArrayList de inteiros, e {@code null} se não houver caminho
     * @throws UnsupportedOperationException se existir um ciclo negativo
     */
    public ArrayList<Integer> caminho(int u, int v) {
        if (temCicloNegativo())
            throw new UnsupportedOperationException("Existe um ciclo negativo!");
        if (!temCaminho(u, v))
            return null;

        ArrayList<Integer> path = new ArrayList<Integer>();

        // Adiciona vertices no caminho...
        path.add(v);
        while (u != v) {
            v = next[u][v];
            path.add(0, v);
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

        // Exibe mensagem se houver ciclo negativo
        if (spt.temCicloNegativo()) {
            System.out.println("Existe um ciclo negativo!");
        } else {
            // Exibe todos os caminhos
            for (int u = 0; u < g.getTotalVerts(); u++) {
                for (int v = 0; v < g.getTotalVerts(); v++) {
                    if (u != v && spt.temCaminho(u, v)) {
                        System.out.print(u + "->" + v + ": ");
                        for (int x : spt.caminho(u, v)) {
                            String vert = ag.mapToString(x);
                            System.out.print(vert + " ");
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

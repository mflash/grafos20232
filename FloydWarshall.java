import java.util.ArrayList;

/* 
 * Implementação do algoritmo de Floyd-Warshall usando matriz de adjacência
 */
public class FloydWarshall {
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

        // Loop de Floyd-Warshall
        for (int k = 0; k < V; k++) {
            // ....
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
    public ArrayList<Integer> caminho(int s, int t) {
        if (temCicloNegativo())
            throw new UnsupportedOperationException("Existe um ciclo negativo!");
        if (!temCaminho(s, t))
            return null;

        ArrayList<Integer> path = new ArrayList<Integer>();

        // Adiciona vertices no caminho...

        return path;
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

        // Mostra todas as distâncias dos caminhos mais curtos
        // ...

        // Exibe mensagem se houver ciclo negativo
        if (spt.temCicloNegativo()) {
            System.out.println("Existe um ciclo negativo!");
        } else {
            // Exibe todos os caminhos
            // ...
        }

        System.out.println("Tempo de Floyd-Warshall: " + spt.tempoTotal());
    }

}

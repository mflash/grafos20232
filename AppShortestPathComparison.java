
public class AppShortestPathComparison {

    public static void main(String[] args) {

        EdgeWeightedDigraph DG = null;
        // true para ler do arquivo, false para gerar um grafo
        boolean leitura = false;
        if (!leitura) {

            // Grafo completo:
            // Caso em que F-W é MAIS eficiente que Dijkstra
            Digraph dg = DigraphGenerator.complete(1000);

            // Árvore binária
            // Digraph dg = DigraphGenerator.binaryTree(3000);

            // DAG
            // Digraph dg = DigraphGenerator.dag(5000, 2000);

            DG = new EdgeWeightedDigraph();
            for (String v : dg.getVerts()) {
                for (String w : dg.getAdj(v)) {
                    double weight = StdRandom.uniform(1, 10);
                    DG.addEdge(v, w, weight);
                }
            }
        } else
            // Carrega EWG de um arquivo texto e cria um AdjMatrixEdgeWeightedDigraph
            DG = new EdgeWeightedDigraph("1000EWD.txt");

        AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(DG);

        System.out.println("Vértices: " + G.getTotalVertices());
        System.out.println("Arestas: " + G.getTotalEdges());

        // Executa Floyd-Warshall e Dijkstra, compara os tempos
        FloydWarshall fw = new FloydWarshall(G);
        System.out.println("Floyd-Warshall: " + fw.tempoTotal());

        long tempoDijsktra = 0;
        // Lembrando que é preciso executar Dijkstra para TODOS os vértices
        for (String v : DG.getVerts()) {
            long start = System.currentTimeMillis();
            Dijkstra d = new Dijkstra(DG, v);
            long end = System.currentTimeMillis();
            tempoDijsktra += (end - start);
        }
        // Mesmo assim, em 99% dos casos, Dijkstra é melhor!
        System.out.println("Dijkstra: " + tempoDijsktra);
    }

}

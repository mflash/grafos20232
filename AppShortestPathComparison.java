
public class AppShortestPathComparison {

    public static void main(String[] args) {

        EdgeWeightedDigraph DG = null;
        boolean leitura = true;
        if (!leitura) {

            // Grafo completo
            // Digraph dg = DigraphGenerator.complete(1000);

            // Árvore binária
            // Digraph dg = DigraphGenerator.binaryTree(3000);

            // DAG
            Digraph dg = DigraphGenerator.dag(5000, 2000);

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

        // Executa Floyd-Warshall e Dijkstra, compara os tempos
        // Lembrando que é preciso executar Dijkstra para TODOS os vértices
    }

}

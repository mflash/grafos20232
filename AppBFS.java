public class AppBFS {
    public static void main(String[] args) {
        Graph g = new Graph("tinyG.txt");

        BreadthFirstSearch bfs = new BreadthFirstSearch(g, "0");

        for (String v : g.getVerts()) {
            System.out.print(v + ": (" + bfs.distTo(v) + ") ");
            for (String w : bfs.pathTo(v)) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }

}
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BreadthFirstSearch {
    private Set<String> marked;
    private Map<String, String> edgeTo;
    private Map<String, Integer> distTo;
    private String s;

    public BreadthFirstSearch(Graph g, String s) {
        this.s = s;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        bfs(g, s);
    }

    public boolean hasPathTo(String v) {
        return marked.contains(v);
    }

    public int distTo(String v) {
        if (hasPathTo(v))
            return distTo.get(v);
        return -1;
    }

    public Iterable<String> pathTo(String v) {
        List<String> path = new LinkedList<>();
        if (hasPathTo(v)) {
            while (!v.equals(s)) {
                path.add(0, v);
                v = edgeTo.get(v);
            }
            path.add(0, s);
        }
        return path;
    }

    private void bfs(Graph g, String v) {
        List<String> fila = new LinkedList<>();
        fila.add(v);
        marked.add(v);
        distTo.put(v, 0);
        while (!fila.isEmpty()) {
            String x = fila.remove(0);
            // marked.add(x);
            int dist = distTo.get(x);
            // System.out.println("Visitando " + x);
            for (String w : g.getAdj(x)) {
                if (!marked.contains(w)) {
                    edgeTo.put(w, x);
                    distTo.put(w, dist + 1);
                    // System.out.println(" >> adicionando " + w);
                    fila.add(w);
                    marked.add(w);
                }
            }
        }
    }
}
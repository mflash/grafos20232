import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Topological {
    private Set<String> marked;
    private List<String> verts;

    public Topological(Digraph g) {
        marked = new HashSet<>();
        verts = new LinkedList<>();
        for (String v : g.getVerts()) {
            if (!marked.contains(v)) {
                System.out.println("Start: " + v);
                dfs(g, v);
            }
        }
    }

    public Iterable<String> getTopological() {
        return verts;
    }

    private void dfs(Graph g, String v) {
        marked.add(v);
        System.out.println("Estou em " + v);
        Iterable<String> adj = g.getAdj(v);
        if (adj != null) {
            for (String w : adj) {
                if (!marked.contains(w)) {
                    dfs(g, w);
                }
            }
        }
        System.out.println("Add: " + v);
        // Pós-ordem reversa:
        verts.add(0, v); // insere no inicio da lista
    }
}
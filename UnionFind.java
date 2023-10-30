import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<String, String> uf;

    public UnionFind() {
        uf = new HashMap<>();
    }

    public void addSet(String s) {
        uf.put(s, null);
    }

    public String find(String s) {
        String x = uf.get(s);
        while (x != null) {
            s = x;
            x = uf.get(x);
        }
        return s;
    }

    public void union(String s, String t) {
        s = find(s);
        t = find(t);
        uf.put(t, s);
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind();
        for (int s = 1; s <= 6; s++)
            uf.addSet(s + "");

        System.out.println("conj. 5:" + uf.find("5"));
        System.out.println();
        System.out.println("Unindo 1-3, 1-5, e 5-6");
        uf.union("1", "3");
        uf.union("1", "5");
        uf.union("5", "6");
        System.out.println("conj. 3: " + uf.find("3"));
        System.out.println("conj. 5: " + uf.find("5"));
        System.out.println("conj. 6: " + uf.find("6"));
    }
}
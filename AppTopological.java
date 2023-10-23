public class AppTopological {
    public static void main(String[] args) {
        Digraph g = new Digraph("tinydag.txt");

        Topological topo = new Topological(g);

        for (String v : topo.getTopological())
            System.out.print(v + " ");
        System.out.println();
    }
}
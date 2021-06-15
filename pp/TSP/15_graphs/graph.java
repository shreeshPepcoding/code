import java.util.*;

public class graph {

    public static class Edge {
        int src;
        int nbr;
        int wt;

        public Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int src, int nbr, int wt) {
        graph[src].add(new Edge(src, nbr, wt));
        graph[nbr].add(new Edge(nbr, src, wt));
    }

    public static void display(ArrayList<Edge>[] graph) {
        for(int v = 0; v < graph.length; v++) {
            System.out.print("[" + v + "] -> ");
            for(int n = 0; n < graph[v].size(); n++) {
                Edge e = graph[v].get(n);
                System.out.print("[" + e.src + "-" + e.nbr + " @ " + e.wt + "], ");  
            }
            System.out.println();
        }
    }


    
    public static ArrayList<Edge>[] createGraph() {
        // n-> number of vertices
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[][] data = {
            {0, 1, 10},
            {0, 3, 40}, 
            {1, 2, 10},
            {2, 3, 10}, 
            {3, 4, 2}, 
            {4, 5, 3},
            {4, 6, 8}, 
            {5, 6, 3}
        };

        // addEdge(graph, 0, 1, 10);
        // addEdge(graph, 0, 3, 40);
        // addEdge(graph, 1, 2, 10);
        // addEdge(graph, 2, 3, 10);
        // addEdge(graph, 3, 4, 2);
        // addEdge(graph, 4, 5, 3);
        // addEdge(graph, 4, 6, 8);
        // addEdge(graph, 5, 6, 3);

        for(int i = 0; i < data.length; i++) {
            addEdge(graph,  data[i][0], data[i][1], data[i][2]);
        }

        return graph;
    }
 
    // dfs -> depth first search
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis) {
        if(src == dst) {
            return true;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            // if neighbour is unvisited, move toward it
            if(vis[nbr] == false) {
                boolean res = hasPath(graph, nbr, dst, vis);
                if(res == true) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis, String psf, int wsf) {
        if(src == dst) {
            psf += dst;
            System.out.println(psf + " @ " + wsf);
            return;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            int wt = e.wt;
            // if neighbour is unvisited, move toward it
            if(vis[nbr] == false) {
                printAllPaths(graph, nbr, dst, vis, psf + src + " ", wsf + wt);
            }
        }
        vis[src] = false;
    }

    public static void fun() {
        int n = 7;
        ArrayList<Edge>[] graph = createGraph();
        boolean[] vis = new boolean[n];

        // System.out.println(hasPath(graph, 0, 6, vis));
        printAllPaths(graph, 0, 6, vis, "", 0);

        // display(graph);
    }


    public static void main(String[] args) {
        fun();
    }
}
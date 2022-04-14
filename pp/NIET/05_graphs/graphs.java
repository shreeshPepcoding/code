import java.util.*;

public class graphs {

    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int v1, int v2, int wt) {
        graph[v1].add(new Edge(v1, v2, wt));
        graph[v2].add(new Edge(v2, v1, wt));
    }

    public static void display(ArrayList<Edge>[] graph) {
        for(int v = 0; v < graph.length; v++) {
            System.out.print("[" + v + "] -> ");
            for(Edge e : graph[v]) {
                System.out.print("[" + e.src + "-" + e.nbr + "@" + e.wt + "], ");
            }
            System.out.println();
        }
    }

    // dfs Algorithm -> depth first search
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, 
        int dst, boolean[] vis) {
        if(src == dst) return true;

        vis[src] = true;
        // for(int i = 0; i < graph[src].size(); i++) {
        //     Edge e = graph[src].get(i);
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            if(vis[nbr] == false) {
                boolean res = hasPath(graph, nbr, dst, vis);
                if(res == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printAllPath(ArrayList<Edge>[] graph, int src,
         int dst, boolean[] vis, String psf, int wsf) {
        if(src == dst) {
            System.out.println(psf + src + " @ " + wsf);
            return;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                printAllPath(graph, e.nbr, dst, vis, psf + src, wsf + e.wt);
            }
        }
        vis[src] = false;
    }

    static String mnpath;
    static int mncost = Integer.MAX_VALUE;
    static String mxpath;
    static int mxcost = Integer.MIN_VALUE;

    public static void graphTraversal(ArrayList<Edge>[] graph, int src,
         int dst, boolean[] vis, String psf, int wsf) {
        if(src == dst) {
            // max creation
            if(mxcost < wsf) {
                mxcost = wsf;
                mxpath = psf + src;
            }

            // min creation
            if(mncost > wsf) {
                mncost = wsf;
                mnpath = psf + src;
            }
            return;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                graphTraversal(graph, e.nbr, dst, vis, psf + src, wsf + e.wt);
            }
        }
        vis[src] = false;
    }

    public static void fun() {
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }        
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        display(graph);

        boolean[] vis = new boolean[n];
        // printAllPath(graph, 0, 6, vis, "", 0);
        graphTraversal(graph, 0, 6, vis, "", 0);

        System.out.println("Min Path : " + mnpath + " @ " + mncost);
        System.out.println("Max Path : " + mxpath + " @ " + mxcost);
    }

    public static void main(String[] args) {
        fun();
    }
}

import java.util.*;

public class graphs {

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

    public static ArrayList<Edge>[] creation() {
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        // to provide memory of arrayList at every index of array
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // int[] src = {0, 0, 1, 2, 3, 4, 4, 5};
        // int[] nbr = {1, 3, 2, 3, 4, 5, 6, 6};
        // int[] wt = {10, 40, 10, 10, 2, 3, 8, 3};

        // for(int i = 0; i < src.length; i++) {
        // int s = src[i];
        // int nb = nbr[i];
        // int w = wt[i];

        // addEdge(graph, s, nb, w);
        // }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        return graph;
    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int v = 0; v < graph.length; v++) {
            System.out.print("[" + v + "] -> ");
            for (Edge e : graph[v]) {
                System.out.print("[" + e.src + "-" + e.nbr + " @ " + e.wt + "], ");
            }
            System.out.println();
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis) {
        if (src == dst) {
            return true;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            int nbr = e.nbr;
            if (vis[nbr] == false) {
                boolean res = hasPath(graph, nbr, dst, vis);
                if (res == true)
                    return true;
            }
        }
        return false;
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis, String psf) {
        if (src == dst) {
            psf += dst;
            System.out.println(psf);
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false) {
                printAllPaths(graph, e.nbr, dst, vis, psf + src);
            }
        }
        vis[src] = false;
    }

    public static String minPsf = "";
    public static int minwsf = (int) 1e9;

    public static void minPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis, String psf, int wsf) {
        if (src == dst) {
            psf += dst;

            if (wsf < minwsf) {
                minPsf = psf;
                minwsf = wsf;
            }
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false) {
                minPath(graph, e.nbr, dst, vis, psf + src, wsf + e.wt);
            }
        }
        vis[src] = false;
    }

    public static String maxPsf = "";
    public static int maxwsf = (int) -1e9;

    public static void maxPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis, String psf, int wsf) {
        if (src == dst) {
            psf += dst;

            if (wsf > maxwsf) {
                maxPsf = psf;
                maxwsf = wsf;
            }
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false) {
                maxPath(graph, e.nbr, dst, vis, psf + src, wsf + e.wt);
            }
        }
        vis[src] = false;
    }

    public static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;
  
        Pair(int wsf, String psf){
           this.wsf = wsf;
           this.psf = psf;
        }
  
        public int compareTo(Pair o){
           return this.wsf - o.wsf;
        }
    }
    // multisolver -> max, min, ceil, floor, kth largest
    static String spath;    // smallest path so far
    static Integer spathwt = Integer.MAX_VALUE; // smallest wsf
    static String lpath;    // largest path so far
    static Integer lpathwt = Integer.MIN_VALUE; // largest wsf
    static String cpath;    // ceil path so far
    static Integer cpathwt = Integer.MAX_VALUE; // ceil wsf
    static String fpath;    // floor path so far
    static Integer fpathwt = Integer.MIN_VALUE; // floor wsf
    static PriorityQueue<Pair> pq = new PriorityQueue<>(); // priority Queue

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, 
        boolean[] vis, int factor, int k, String psf, int wsf) {
        
        if(src == dst) {
            // min path
            if(wsf < spathwt) {
                spathwt = wsf;
                spath = psf;
            }
            // max path
            if(wsf > lpathwt) {
                lpathwt = wsf;
                lpath = psf;
            }
            // ceil path
            if(factor < wsf && wsf < cpathwt) {
                cpathwt = wsf;
                cpath = psf;
            }
            // floor path
            if(fpathwt < wsf && wsf < factor) {
                fpathwt = wsf;
                fpath = psf;
            }
            // kth largest path
            if(pq.size() < k) {
                pq.add(new Pair(wsf, psf));
            } else {
                if(pq.peek().wsf < wsf) {
                    pq.remove();
                    pq.add(new Pair(wsf, psf));
                }
            }
            return;
        }
            
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                multisolver(graph, e.nbr, dst, vis, factor, k, psf + e.nbr, wsf + e.wt);
            } 
        }
        vis[src] = false;
    }


    public static void getConnectedComp(ArrayList<Edge>[] graph, int src, 
        boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                getConnectedComp(graph, e.nbr, vis, comp);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> gcc(ArrayList<Edge>[] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                getConnectedComp(graph, v, vis, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    public static boolean isGraphConnected(ArrayList<Edge>[] graph) {
        int count = 1;
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                if(count > 1) {
                   return false; 
                }
                ArrayList<Integer> comp = new ArrayList<>();
                getConnectedComp(graph, v, vis, comp);
                count++;
            }
        }
        return true;
    }

    public static void fun() {
        int n = 7;
        ArrayList<Edge>[] graph = creation();
        display(graph);

        System.out.println(gcc(graph));



        // int src = 0;
        // int dst = 6;
        // boolean[] vis = new boolean[n];

        // multisolver(graph, src, dst, vis, 45, 3, src + "", 0);

        // System.out.println("Min Path : " + spath + " @ " + spathwt);
        // System.out.println("Max Path : " + lpath + " @ " + lpathwt);
        // System.out.println("Ceil Path : " + cpath + " @ " + cpathwt);
        // System.out.println("Floor Path : " + fpath + " @ " + fpathwt);
        // System.out.println("Kth Largest : " + pq.peek().psf + " @ " + pq.peek().wsf); 
       
       
       
       
       
        // boolean res = hasPath(graph, src, dst, vis);
        // System.out.println("Has Path from " + src + " to " + dst + " : " + res);
        // printAllPaths(graph, src, dst, vis, "");

        // minPath(graph, src, dst, vis, "", 0);
        // maxPath(graph, src, dst, vis, "", 0);
        // System.out.println(maxPsf + " @ " + maxwsf);
    }

    public static void main(String[] args) {
        fun();
    }
}
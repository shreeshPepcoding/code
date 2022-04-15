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


    static class Pair implements Comparable<Pair> {
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

    static String spath; // smallest path
    static Integer spathwt = Integer.MAX_VALUE; // smallest path cost
    static String lpath; // largest path 
    static Integer lpathwt = Integer.MIN_VALUE; // largest path cost
    static String cpath; // ceil path
    static Integer cpathwt = Integer.MAX_VALUE; // ceil path cost
    static String fpath; // floor path 
    static Integer fpathwt = Integer.MIN_VALUE; // floor path cost 
    static PriorityQueue<Pair> pq = new PriorityQueue<>(); // priority queue for kth largest path
    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst,
     boolean[] vis, int factor, int k, String psf, int wsf) {
        if(src == dst) {
            psf += src;
            // smallest path
            if(wsf  < spathwt) {
                spathwt = wsf;
                spath = psf; 
            }
            // largest path
            if(wsf > lpathwt) {
                lpathwt = wsf;
                lpath = psf;
            }
            // ceil path
            if(wsf > factor && cpathwt > wsf) {
                cpathwt = wsf;
                cpath = psf;
            }
            // floor path
            if(wsf < factor && fpathwt < wsf) {
                fpathwt = wsf;
                fpath = psf;
            }
            // kth largest using priority queue
            if(pq.size() < k) {
                pq.add(new Pair(wsf, psf));
            } else {
                pq.add(new Pair(wsf, psf));
                pq.remove();
            }
            return;
        }
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                multisolver(graph, e.nbr, dst, vis, factor, k, psf + src, wsf + e.wt);
            }
        }
        vis[src] = false;
    }


    public static void gcc(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                gcc(graph, e.nbr, vis, comp);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>(); // components
        boolean[] vis = new boolean[graph.length];

        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>(); // component
                gcc(graph, v, vis, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    public static boolean isGraphConnected(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        int count = 0;
        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                if(count == 1) return  false;
                 ArrayList<Integer> comp = new ArrayList<>(); // component
                count++;
                gcc(graph, v, vis, comp);
            }
        }
        return true;
    }

    public static void perfectFriendComp(ArrayList<Integer>[] graph, 
        int src, boolean[] vis, int[] count) {
        vis[src] = true;
        count[0]++;
        for(int nbr : graph[src]) {
            if(vis[nbr] == false) {
                perfectFriendComp(graph, nbr, vis, count);
            }
        }
    }

    public static int perfectFriends(ArrayList<Integer>[] graph) {
        ArrayList<Integer> compsSize = new ArrayList<>();
        boolean[] vis = new boolean[graph.length];

        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                int[] count = {0};
                perfectFriendComp(graph, v, vis, count);
                compsSize.add(count[0]);
            }
        }

        // O(n^2)
        // int res = 0;

        // for(int i = 0; i < compsSize.size(); i++) {
        //     for(int j = i + 1; j < compsSize.size(); j++) {
        //         res += compsSize.get(i) * compsSize.get(j);
        //     }
        // }
        // return res;

        // O(n);
        int sum = 0;
        int res = 0;

        for(int i = compsSize.size() - 1; i >= 0; i--) {
            int sz = compsSize.get(i);
            res += sz * sum;
            sum += sz;
        }
        return res;
    }

    // journey to the moon, https://www.hackerrank.com/challenges/journey-to-the-moon/problem
    // same as perfect friend

    


    public static void fun() {
        int n = 9;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }        
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        // addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        // addEdge(graph, 2, 5, 4);
        addEdge(graph, 7, 8, 10);
        display(graph);

        // boolean[] vis = new boolean[n];
        // System.out.println();
        // printAllPath(graph, 0, 6, vis, "", 0);
        // System.out.println();
        // multisolver(graph, 0, 6, vis, 45, 4, "", 0);
        // System.out.println("Smallest path : " + spath + " @ " + spathwt);
        // System.out.println("Largest path : " + lpath + " @ " + lpathwt);
        // System.out.println("Ceil path : " + cpath + " @ " + cpathwt);
        // System.out.println("Floor path : " + fpath + " @ " + fpathwt);
        // System.out.println("Kth Largest Path path : " + pq.peek().psf + " @ " + pq.peek().wsf);
        
        System.out.println(getConnectedComponents(graph));

    
    }


    public static void main(String[] args) {
        fun();
    }
}

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
        System.out.println("===========GRAPH===========");
        for(int v = 0; v < graph.length; v++) {
            System.out.print("[" + v + "] -> ");
            for(Edge e : graph[v]) {
                System.out.print("(" + e.src + "-" + e.nbr + " @ " + e.wt + "), ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, boolean[] vis, int src, int dst) {

        if(src == dst) {
            return true;
        }

        //mark
        vis[src] = true;
        // ask from nbrs
        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                boolean rres = hasPath(graph, vis, n, dst);
                if(rres == true) return true;
            }
        }
        return false;
    }

    // psf = path so far
    // csf = cost so far

    public static void printAllPath(ArrayList<Edge>[] graph, boolean[] vis, int src, 
                int dst , String psf, int csf) {

        if(src == dst) {
            psf += src;
            System.out.println(psf + " @ " + csf);
        }

        //mark
        vis[src] = true;

        // ask from nbrs
        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                printAllPath(graph, vis, n, dst, psf + src + " ", csf + e.wt);
            }
        }

        //unmark
        vis[src] = false;
    }

    public static int SPcsf = Integer.MAX_VALUE;
    public static String SPpsf = "";
    public static void smallestPath(ArrayList<Edge>[] graph, boolean[] vis, int src, 
    int dst , String psf, int csf) {
        if(src == dst) {
            psf += src;
            if(csf < SPcsf) {
                SPcsf = csf;
                SPpsf = psf;
            }
            return;
        }

        //mark
        vis[src] = true;

        // ask from nbrs
        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                smallestPath(graph, vis, n, dst, psf + src + " ", csf + e.wt);
            }
        }

        //unmark
        vis[src] = false;
    }

    public static int LPcsf = Integer.MIN_VALUE;
    public static String LPpsf = "";
    public static void largestPath(ArrayList<Edge>[] graph, boolean[] vis, int src, 
    int dst , String psf, int csf) {
        if(src == dst) {
            psf += src;
            if(csf > LPcsf) {
                LPcsf = csf;
                LPpsf = psf;
            }
            return;
        }

        //mark
        vis[src] = true;

        // ask from nbrs
        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                largestPath(graph, vis, n, dst, psf + src + " ", csf + e.wt);
            }
        }

        //unmark
        vis[src] = false;
    }

    public static int CPcsf = Integer.MAX_VALUE;
    public static String CPpsf = "";
    public static void ceilPath(ArrayList<Edge>[] graph, boolean[] vis, int src, 
    int dst , String psf, int csf, int factor) {
        if(src == dst) {
            psf += src;
            if(csf > factor && csf < CPcsf) {
                CPcsf = csf;
                CPpsf = psf;
            }
            return;
        }

        //mark
        vis[src] = true;

        // ask from nbrs
        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                ceilPath(graph, vis, n, dst, psf + src + " ", csf + e.wt, factor);
            }
        }

        //unmark
        vis[src] = false;
    }

    public static int FPcsf = -1;
    public static String FPpsf = "";
    public static void floorPath(ArrayList<Edge>[] graph, boolean[] vis, int src, 
    int dst , String psf, int csf, int factor) {
        if(src == dst) {
            psf += src;
            if(csf < factor && csf > FPcsf) {
                FPcsf = csf;
                FPpsf = psf;
            }
            return;
        }

        //mark
        vis[src] = true;

        // ask from nbrs
        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                floorPath(graph, vis, n, dst, psf + src + " ", csf + e.wt, factor);
            }
        }

        //unmark
        vis[src] = false;
    }

    public static class KLHelper implements Comparable<KLHelper>{
        String psf; // path so far
        int csf; // cost so far

        public KLHelper(String psf, int csf) {
            this.psf = psf;
            this.csf = csf;
        }

        public int compareTo(KLHelper other) {
            return this.csf - other.csf;
        }
    }

    public static PriorityQueue<KLHelper> pq = new PriorityQueue<>();

    public static void kthLargest(ArrayList<Edge>[] graph, boolean[] vis, int src, 
            int dst, String psf, int csf, int k) {
        
        if(src == dst) {
            psf += src;
            if(pq.size() < k) {
                pq.add(new KLHelper(psf, csf));
            } else {
                if(pq.peek().csf < csf) {
                    pq.remove();
                    pq.add(new KLHelper(psf, csf));
                }
            }
            return;
        }
        
        vis[src] = true;
        
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            if(vis[nbr] == false) {
                kthLargest(graph, vis, nbr, dst, psf + src + " ", csf + e.wt, k); 
            }
        }

        vis[src] = false;
    }

    public static void dfsForGCC(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                dfsForGCC(graph, n, vis, comp);
            }
        }
    }

    public static void gcc(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();

        // write your code here

        boolean[] vis = new boolean[graph.length];
        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                dfsForGCC(graph, v, vis, comp);
                comps.add(comp);
            }
        }

        System.out.println(comps);
    }

    public static void dfsForIsConnected(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;

        for(Edge e : graph[src]) {
            int n = e.nbr;
            if(vis[n] == false) {
                dfsForIsConnected(graph, n, vis);
            }
        }
    }

    public static void isGraphConnected(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        boolean isConnected = true;
        int count = 0;
        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                if(count == 0) {
                    dfsForIsConnected(graph, v, vis);
                    count++;
                } else {
                    isConnected = false;
                    break;
                }
            }
        }

        System.out.println(isConnected);
    }


    public static int[] rdir = {0, 1, 0, -1};
    public static int[] cdir = {1, 0, -1, 0};
    // sr => source row, sc => source column
    public static void gccForIsland(int[][] graph, int sr, int sc) {
        
        graph[sr][sc] = 2;
        for(int d = 0; d < 4; d++) {
            int nr = sr + rdir[d];
            int nc = sc + cdir[d];
            // nr -> neighbour row, nc-> neighbout column
            // check for valid nr, nc as well as unvisited
            if(nr >= 0 && nr < graph.length && nc >= 0 && nc < graph[0].length && graph[nr][nc] == 0) {
                gccForIsland(graph, nr, nc);
            } 
        }
    }
    public static String str = "";
    public static void GetgccForIsland(int[][] graph, int sr, int sc) {
        
        str += "(" + sr + "," + sc + ")--";

        graph[sr][sc] = 2;
        for(int d = 0; d < 4; d++) {
            int nr = sr + rdir[d];
            int nc = sc + cdir[d];
            // nr -> neighbour row, nc-> neighbout column
            // check for valid nr, nc as well as unvisited
            if(nr >= 0 && nr < graph.length && nc >= 0 && nc < graph[0].length && graph[nr][nc] == 0) {
                GetgccForIsland(graph, nr, nc);
            } 
        }
    }


    public static void noOfIsland() {
        int[][] island = {
            {0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 1, 0},
            {1, 1, 0, 0, 1, 0},
            {0, 0, 1, 1, 0, 0},
            {0, 1, 0, 0, 1, 1}
        };

        // 0 -> land
        // 1 -> water
        // 2 -> visited
        int count = 0;

        ArrayList<String> islands = new ArrayList<>();
        for(int r = 0; r < island.length; r++) {
            for(int c = 0; c < island[0].length; c++) {
                if(island[r][c] == 0) {
                    str = "";
                    GetgccForIsland(island, r, c);
                    // gccForIsland(island, r, c);
                    islands.add(str);
                    count++;
                }
            }
        }

        for(String i : islands) {
            System.out.println(i);
        }
        // System.out.println("No. of island : " + count);
    }


    public static void ques() {
        // n is no. of vertex
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];


        // assign memory, so that i can add edge
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // int[] v1 = {0, 0, 1, 2, 3, 4, 4, 5};
        // int[] v2 = {3, 1, 2, 3, 4, 5, 6, 6};
        // int[] wt = {40, 10, 10, 10, 2, 3, 8, 3};

        // for(int e = 0; e < v1.length; e++) {
        //     addEdge(graph, v1[e], v2[e], wt[e]);
        // }


        // int[][] edges = {
        //     {0, 3, 40},
        //     {0, 1, 10},
        //     {1, 2, 10},
        //     {2, 3, 10},
        //     {3, 4, 2},
        //     {4, 5, 3},
        //     {4, 6, 8},
        //     {5, 6, 3}
        // };

        // for(int e = 0; e < edges.length; e++) {
        //     addEdge(graph, edges[e][0], edges[e][1], edges[e][2]);
        // }


        addEdge(graph, 0, 3, 40);
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        display(graph);

        noOfIsland();
        // isGraphConnected(graph);
        // gcc(graph);
        



        // int src = 0;
        // int dst = 6;
        // boolean[] vis = new boolean[n];
        // boolean hasP = hasPath(graph, vis, src, dst);
        // System.out.println(hasP);

        // printAllPath(graph, vis, src, dst, "", 0);
        // System.out.println("======================");

        // smallestPath(graph, vis, src, dst, "", 0);
        // largestPath(graph, vis, src, dst, "", 0);
        // ceilPath(graph, vis, src, dst, "", 0, 45);
        // floorPath(graph, vis, src, dst, "", 0, 45);

        // System.out.println("Smallest Path : " + SPpsf + " @ " + SPcsf);
        // System.out.println("Largest Path : " + LPpsf + " @ " + LPcsf);
        // System.out.println("Ceil Path : " + CPpsf + " @ " + CPcsf);
        // System.out.println("Floor Path : " + FPpsf + " @ " + FPcsf);
        
        // int k = 2;
        // kthLargest(graph, vis, src, dst, "", 0, k);
        // KLHelper res = pq.peek();
        // System.out.println("Kth Largest : " + res.psf + " @ " + res.csf);
    }

    public static void main(String[] args) {
        ques();
    }

}
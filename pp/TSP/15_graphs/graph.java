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

        // int[][] data = {
        //     {0, 1, 10},
        //     {0, 3, 40}, 
        //     {1, 2, 10},
        //     {2, 3, 10}, 
        //     {3, 4, 2}, 
        //     {4, 5, 3},
        //     {4, 6, 8}, 
        //     {5, 6, 3}
        // };

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        // addEdge(graph, 2, 5, 0);


        // for(int i = 0; i < data.length; i++) {
        //     addEdge(graph,  data[i][0], data[i][1], data[i][2]);
        // }

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

    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();
    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis,
                            int factor, int k, String psf, int wsf) {
        if(src == dst) {
            // smallest path
            if(wsf < spathwt) {
                spath = psf;
                spathwt = wsf;
            }
            // largest path
            if(wsf > lpathwt) {
                lpath = psf;
                lpathwt = wsf;
            }
            // ceil path using factor
            if(wsf > factor) {
                if(wsf < cpathwt) {
                    cpath = psf;
                    cpathwt = wsf;
                }
            }
            // floor path using factor
            if(wsf < factor) {
                if(fpathwt < wsf) {
                    fpathwt = wsf;
                    fpath = psf;
                }
            }
            // kth largest using min priority queue
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
            int nbr = e.nbr;
            int wt = e.wt;

            if(vis[nbr] == false) {
                multisolver(graph, nbr, dst, vis, factor, k, psf + nbr, wsf + wt);
            }
        }
        vis[src] = false;
    }

    public static void getConnectedComp(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            if(vis[nbr] == false) {
                getConnectedComp(graph, nbr, vis, comp);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];

        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                getConnectedComp(graph, v, vis, comp);
                comps.add(comp);
            }
        }

        System.out.println(comps);
        return comps;
    }

    public static void gcc(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            if(vis[nbr] == false) {
                gcc(graph, nbr, vis);
            }
        }
    }

    public static boolean isConnected(ArrayList<Edge>[] graph) {
        int count = 0;
        int n = graph.length;
        boolean[] vis = new boolean[n];

        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                count++;
                if(count > 1) {
                    return false;
                }
                gcc(graph, v, vis);
            }
        }
        return true;
    }


    // order of direction -> top, left, down, right
    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};

    public static void gccForIsland(int[][] graph, int x, int y) {
        graph[x][y] = -1;
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];

            if(r >= 0 && r < graph.length && c >= 0 && c < graph[0].length && graph[r][c] == 0) {
                gccForIsland(graph, r, c);
            }
        }
        
    }

    public static int numOfIsland(int[][] graph) {
        int count = 0;

        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] == 0) {
                    count++;
                    gccForIsland(graph, i, j);
                }
            }
        }

        return count;
    }

    public static void hamiltonian(ArrayList<Edge>[] graph, int src, int osrc, HashSet<Integer> vis, String psf) {
        if(vis.size() == graph.length - 1) {
            psf += src;
            System.out.print(psf);

            // thinking about star(cyclic) and dot(normal)
            boolean isCyclic = false;
            for(Edge e : graph[osrc]) {
                if(e.nbr == src) {
                    isCyclic = true;
                    break;
                }
            }

            if(isCyclic == true) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }
            return;
        }
        
        vis.add(src);
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            if(vis.contains(nbr) == false) {
                hamiltonian(graph, nbr, osrc, vis, psf + src);
            }
        }
        vis.remove(src);
    }

    public static class BFSPair {
        int vtx;
        String psf;

        BFSPair(int vtx, String psf) {
            this.vtx = vtx;
            this.psf = psf;
        }
    }

    public static void bfs(ArrayList<Edge>[] graph, int src) {
        Queue<BFSPair> qu = new LinkedList<>();
        qu.add(new BFSPair(src, "" + src));
        boolean[] vis = new boolean[graph.length];

        while(qu.size() > 0) {
            // 1. get + remove
            BFSPair rem = qu.remove();
            // 2. mark *
            if(vis[rem.vtx] == true) {
                // already visited -> continue
                continue;
            } else {
                // unvisited -> mark it as visited
                vis[rem.vtx] = true;
            }
            // 3. work -> printing
            System.out.println(rem.vtx + " @ " + rem.psf);
            // 4. add unvisited neighbours
            for(Edge e : graph[rem.vtx]) {
                if(vis[e.nbr] == false) {
                    qu.add(new BFSPair(e.nbr, rem.psf + e.nbr));
                }
            }
        }
    }

    public static boolean bfsForCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(src);

        while(qu.size() > 0) {
            // 1. get + remove
            int rem = qu.remove();
            // 2. mark *
            if(vis[rem] == true) {
                // already visited -> cycle is detected
                return true;
            } else {
                // unvisited -> mark it as visited
                vis[rem] = true;
            }
            // 3. work*
            // 4. add unvisited neighbours
            for(Edge e : graph[rem]) {
                if(vis[e.nbr] == false) {
                    qu.add(e.nbr);
                }
            }
        }
        return false;
    }


    public static boolean isCyclic(ArrayList<Edge>[] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];

        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                boolean res = bfsForCycle(graph, v, vis);
                if(res == true) return true;
            }
        }
        return false;
    }

    public static void fun() {
        int n = 7;
        ArrayList<Edge>[] graph = createGraph();
        
        bfs(graph, 2);
        
        // boolean[] vis = new boolean[n];

        // HashSet<Integer> vis = new HashSet<>();
        // hamiltonian(graph, 5, 5, vis, "");


        // int[][] arr = {
        //     {0, 1, 1, 0, 0},
        //     {0, 0, 1, 0, 1},
        //     {1, 0, 1, 1, 1},
        //     {1, 1, 1, 0, 1},
        //     {1, 0, 0, 0, 1},
        //     {0, 1, 1, 1, 1}
        // };

        // int numOfIslands = numOfIsland(arr);
        // System.out.println(numOfIslands);


        // display(graph);
        // getConnectedComponents(graph);
        // System.out.println(hasPath(graph, 0, 6, vis));
        // printAllPaths(graph, 0, 6, vis, "", 0);

        // display(graph);
    }


    public static void main(String[] args) {
        fun();
    }
}
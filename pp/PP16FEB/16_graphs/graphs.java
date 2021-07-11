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
        // addEdge(graph, 2, 5, 0);
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

        Pair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    // multisolver -> max, min, ceil, floor, kth largest
    static String spath; // smallest path so far
    static Integer spathwt = Integer.MAX_VALUE; // smallest wsf
    static String lpath; // largest path so far
    static Integer lpathwt = Integer.MIN_VALUE; // largest wsf
    static String cpath; // ceil path so far
    static Integer cpathwt = Integer.MAX_VALUE; // ceil wsf
    static String fpath; // floor path so far
    static Integer fpathwt = Integer.MIN_VALUE; // floor wsf
    static PriorityQueue<Pair> pq = new PriorityQueue<>(); // priority Queue

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis, int factor, int k,
            String psf, int wsf) {

        if (src == dst) {
            // min path
            if (wsf < spathwt) {
                spathwt = wsf;
                spath = psf;
            }
            // max path
            if (wsf > lpathwt) {
                lpathwt = wsf;
                lpath = psf;
            }
            // ceil path
            if (factor < wsf && wsf < cpathwt) {
                cpathwt = wsf;
                cpath = psf;
            }
            // floor path
            if (fpathwt < wsf && wsf < factor) {
                fpathwt = wsf;
                fpath = psf;
            }
            // kth largest path
            if (pq.size() < k) {
                pq.add(new Pair(wsf, psf));
            } else {
                if (pq.peek().wsf < wsf) {
                    pq.remove();
                    pq.add(new Pair(wsf, psf));
                }
            }
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false) {
                multisolver(graph, e.nbr, dst, vis, factor, k, psf + e.nbr, wsf + e.wt);
            }
        }
        vis[src] = false;
    }

    public static void getConnectedComp(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false) {
                getConnectedComp(graph, e.nbr, vis, comp);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> gcc(ArrayList<Edge>[] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if (vis[v] == false) {
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
        for (int v = 0; v < n; v++) {
            if (vis[v] == false) {
                if (count > 1) {
                    return false;
                }
                ArrayList<Integer> comp = new ArrayList<>();
                getConnectedComp(graph, v, vis, comp);
                count++;
            }
        }
        return true;
    }


    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};
    public static void numberOfIslandsComp(int[][] graph, int x, int y) {
        // mark
        graph[x][y] = 1;

        for(int dir = 0; dir < 4; dir++) {
            // possible edges
            int r = x + xdir[dir];
            int c = y + ydir[dir];

            // segregate invalid index as well as water area
            if(r >= 0 && r < graph.length && c >= 0 && c < graph[0].length && graph[r][c] == 0) {
                numberOfIslandsComp(graph, r, c);
            }
        }
    }

    public static int numbersOfIslands(int[][] graph) {
        int n = graph.length;
        int m = graph[0].length;

        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(graph[i][j] == 0){
                    count++;
                    numberOfIslandsComp(graph, i, j);
                } 
            }
        }
        return count;
    }

    public static void hamiltonian(ArrayList<Edge>[] graph, int src,
        int osrc, boolean[] vis, String psf) {
        
        if(psf.length() == graph.length - 1){
            psf += src;

            System.out.print(psf);
            boolean isCyclic = false;

            for(Edge e : graph[osrc]) {
                if(src == e.nbr) {
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
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                hamiltonian(graph, e.nbr, osrc, vis, psf + src);
            }
        }
        vis[src] = false;
    }

    public static class BFSPair {
        int src;
        String psf;

        public BFSPair(int src, String psf) {
            this.src = src;
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
            if(vis[rem.src] == true) {
                continue;
            }
            vis[rem.src] = true;
            // 3. work -> print src @ psf
            System.out.println(rem.src + "@" + rem.psf);
            // 4. add unvisited neighbours
            for(Edge e : graph[rem.src]) {
                int nbr = e.nbr;
                if(vis[nbr] == false) {
                    qu.add(new BFSPair(nbr, rem.psf + nbr));
                }
            }
        }
    }

    public static boolean isCyclicHelper(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        Queue<BFSPair> qu = new LinkedList<>();
        qu.add(new BFSPair(src, "" + src));
    
        while(qu.size() > 0) {
            // 1. get + remove
            BFSPair rem = qu.remove();
            // 2. mark *
            if(vis[rem.src] == true) {
                return true;
            }
            vis[rem.src] = true;
            // 3. work -> print src @ psf
            // System.out.println(rem.src + "@" + rem.psf);
            // 4. add unvisited neighbours
            for(Edge e : graph[rem.src]) {
                int nbr = e.nbr;
                if(vis[nbr] == false) {
                    qu.add(new BFSPair(nbr, rem.psf + nbr));
                }
            }
        }
        return false;
    }

    public static boolean isCyclic(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];

        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                boolean res = isCyclicHelper(graph, v, vis);
                if(res == true) return true;
            }
        }
        return false;
    }

    public static class BPair {
        int src;
        int lvl;
        public BPair(int src, int lvl) {
            this.src = src;
            this.lvl = lvl;
        }
    }

    public static boolean bipartiteHelper(ArrayList<Edge>[] graph, int src, int[] vis) {
        // bfs
        Queue<BPair> qu = new LinkedList<>();
        qu.add(new BPair(src, 0));
        
        while(qu.size() > 0) {
            // 1. Get + Remove
            BPair rem = qu.remove();

            // 2. mark
            if(vis[rem.src] != -1) {
                // already marked
                if(vis[rem.src] != rem.lvl) {
                    return false;
                } else {
                    continue;
                }
            }
            vis[rem.src] = rem.lvl;
            // 3. work
            // 4. add unvisited neighbours
            for(Edge e : graph[rem.src]) {
                if(vis[e.nbr] == -1) {
                    qu.add(new BPair(e.nbr, rem.lvl + 1));
                }
            }
        }
        return true;
    }

    public static boolean bipartite(ArrayList<Edge>[] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);

        for(int v = 0; v < n; v++) {
            if(vis[v] == -1) {
                boolean res = bipartiteHelper(graph, v, vis);
                if(res == false) return false;
            }
        }
        return true;
    }  

    public static int spreadOfInfection(ArrayList<Edge>[] graph, int src, int t) {
        Queue<BPair> qu = new LinkedList<>();
        qu.add(new BPair(src, 1));
        int count = 0;
        boolean[] vis = new boolean[graph.length];
        while(qu.size() > 0) {
            BPair rem = qu.remove();
            if(vis[rem.src] == true) {
                continue;
            }
            vis[rem.src] = true;
            if(rem.lvl == t + 1)
                break;

            count++;
            for(Edge e : graph[rem.src]) {
                if(vis[e.nbr] == false) {
                    qu.add(new BPair(e.nbr, rem.lvl + 1));
                }
            }
        }
        return count;
    }

    // leetcode : https://leetcode.com/problems/rotting-oranges/
    public static class OPair {
        int x;
        int y;
        int t; 
        public OPair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<OPair> qu = new LinkedList<>();
        // 1. iterate on grid and find out fresh orange count and fill qu as well
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    count++;
                } else if(grid[i][j] == 2) {
                    qu.add(new OPair(i, j, 0));
                }
            }
        }
        // add count of rotted orange as well
        count += qu.size();

        // bfs
        int time = 0;
        while(!qu.isEmpty()) {
            OPair rem = qu.remove();
            int x = rem.x;
            int y = rem.y;
            int t = rem.t;
            if(grid[x][y] == -1) {
                continue;
            }
            grid[x][y] = -1; // marking
            count--;
            time = t;

            for(int d = 0; d < 4; d++) {
                int r = x + xdir[d];
                int c = y + ydir[d];

                if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && 
                    grid[r][c] == 1) {
                    qu.add(new OPair(r, c, t + 1));
                }
            }
        }

        return count == 0 ? time : -1;
    }

    public static void fireInTheCity(int[][] grid) {
        Queue<OPair> qu = new LinkedList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    qu.add(new OPair(i, j, 0));
                }
            }
        }
        // bfs
        while(!qu.isEmpty()) {
            OPair rem = qu.remove();
            int x = rem.x;
            int y = rem.y;
            int t = rem.t;
            if(grid[x][y] == -1) {
                continue;
            }
            grid[x][y] = -1; // marking
            System.out.println(x + ", " + y  + " burns at time t = " + t);
            for(int d = 0; d < 4; d++) {
                int r = x + xdir[d];
                int c = y + ydir[d];

                if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && 
                    grid[r][c] == 1) {
                    qu.add(new OPair(r, c, t + 1));
                }
            }
        }        
    }

    public static class DHelper implements Comparable<DHelper> {
        int src;
        String psf;
        int wsf;
 
        public DHelper(int src, String psf, int wsf) {
            this.src = src;
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(DHelper o) {
            return this.wsf - o.wsf;
        } 
    }

    public static void dijkstras(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<DHelper> pq = new PriorityQueue<>();
        pq.add(new DHelper(src, "" + src, 0));
        boolean[] vis = new boolean[graph.length];

        while(pq.size() > 0) {
            DHelper rem = pq.remove();
            if(vis[rem.src] == true) {
                continue;
            }
            vis[rem.src] = true;
            System.out.println(rem.src + " via " + rem.psf + " @ " + rem.wsf);
            for(Edge e : graph[rem.src]) {
                if(vis[e.nbr] == false) {
                    pq.add(new DHelper(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
                }
            }
        }
    }

    public static class PHelper implements Comparable<PHelper> {
        int src;
        int parent;
        int wt;

        public PHelper(int src, int parent, int wt) {
            this.src = src;
            this.parent = parent;
            this.wt = wt;
        }

        public int compareTo(PHelper o) {
            return this.wt - o.wt;
        }
    }

    public static void Prims(ArrayList<Edge>[] graph) {
        PriorityQueue<PHelper> pq = new PriorityQueue<>();
        pq.add(new PHelper(0, -1, 0));
        boolean[] vis = new boolean[graph.length];
        while(!pq.isEmpty()) {
            PHelper rem = pq.remove();
            if(vis[rem.src] == true) {
                continue;
            }
            vis[rem.src] = true;
            if(rem.parent != -1) {
                System.out.println("[" + rem.src + "-" + rem.parent + "@" + rem.wt + "]");
            }
            for(Edge e : graph[rem.src]) {
                if(vis[e.nbr] == false) {
                    pq.add(new PHelper(e.nbr, rem.src, e.wt));
                }
            }
        }
    }

    public static void topologicalSortHelper(ArrayList<Edge>[] graph, int src, boolean[] vis, Stack<Integer> st) {
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                topologicalSortHelper(graph, e.nbr, vis, st);
            }
        }
        st.push(src);
    }

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        Stack<Integer> st = new Stack<>();
        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                topologicalSortHelper(graph, v, vis, st);
            }
        }

        while(st.size() > 0) {
            System.out.println(st.pop());
        }
    }


    public static void fun() {
        int n = 7;
        ArrayList<Edge>[] graph = creation();
        dijkstras(graph, 0); 
        

        // int[][] grid = {
        //     {2,	1,	1,	1,	0,	1},
        //     {1,	1,	1,	0,	0,	1},
        //     {1,	0,	1,	1,	1,	1},
        //     {1,	0,	1,	1,	1,	1},
        //     {1,	1,	1,	1,	1,	0},
        //     {1,	1,	1,	2,	1,	0},
        //     {2,	1,	1,	1,	1,	1},
        //     {1,	1,	1,	0,	0,	0},
        //     {1,	1,	1,	1,	0,	2}
        // };
        // fireInTheCity(grid);
        
        
        // display(graph);
        // bfs(graph, 2);

        // boolean[] vis = new boolean[n];
        // hamiltonian(graph, 0, 0, vis, "");
        // System.out.println(gcc(graph));
        // int src = 0;
        // int dst = 6;
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
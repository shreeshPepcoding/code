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

    // leetcode 200 https://leetcode.com/problems/number-of-islands/
    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};

    private void numIslandsComp(char[][] grid, int x, int y) {
        grid[x][y] = '#';
        // from x, y we can move top left down and right
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];

            if(r >= 0 && r < grid.length && c >= 0 && 
            c < grid[0].length && grid[r][c] == '1') {
                numIslandsComp(grid, r, c);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    numIslandsComp(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void hamiltonianPathAndCycle(ArrayList<Edge>[] graph, int src, 
        boolean[] vis, String psf, int osrc) {
        if(psf.length() == graph.length - 1) {
            psf += src;
            System.out.print(psf);
            // now check is path is cyclic or not
            boolean isCycle = false;
            // check if back edge is exist or not
            for(Edge e : graph[src]) {
                if(e.nbr == osrc) {
                    isCycle = true;
                    break;
                }
            }
            if(isCycle == true) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }
            return;
        }
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                hamiltonianPathAndCycle(graph, e.nbr, vis, psf + src, osrc);
            }
        }
        vis[src] = false;
    }


    static int[] rdir = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] cdir = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printKnightsTour(int[][] chess, int x, int y, int upcomingMove) {
        chess[x][y] = upcomingMove;
        if(upcomingMove == chess.length * chess.length) {
            displayBoard(chess);
            chess[x][y] = 0;
            return;
        }
        for(int d = 0; d < 8; d++) {
            int r = x + rdir[d];
            int c = y + cdir[d];

            if(r >= 0 && r < chess.length && c >= 0 && c < chess.length && chess[r][c] == 0) {
                printKnightsTour(chess, r, c, upcomingMove + 1);
            }
        }
        chess[x][y] = 0;
    }

    /*  you have a source, 
        print shortest path(in terms opf edge) 
        from source to every vertex
    */
    private static class BFSHelper {
        int vtx;
        String psf;
        int wsf;

        BFSHelper(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
    }

    public static void bfsAlgo(ArrayList<Edge>[] graph, int src) {
        Queue<BFSHelper> qu = new LinkedList<>();
        boolean[] vis = new boolean[graph.length];
        qu.add(new BFSHelper(src, src + "", 0));
        while(qu.size() > 0) {
            // 1. get + remove
            BFSHelper rem = qu.remove();
            // 2. mark*
            if(vis[rem.vtx] == true) {
                continue;
            } else {
                vis[rem.vtx] = true;
            }
            // 3. work -> print dst + @ + psf
            System.out.println(rem.vtx + "@" + rem.psf + "-" + rem.wsf);
            // 4. add unvisited nbrs
            for(Edge e : graph[rem.vtx]) {
                if(vis[e.nbr] == false) {
                    qu.add(new BFSHelper(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
                }
            }
        }
    }

    public static boolean isCompCyclic(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(src);
        while(qu.size() > 0) {
            // 1. get + remove
            int rem = qu.remove();
            // 2. mark*
            if(vis[rem] == true) {
                return true;
            } else {
                vis[rem] = true;
            }
            // 3. work -> check cycle
            
            // 4. add unvisited nbrs
            for(Edge e : graph[rem]) {
                if(vis[e.nbr] == false) {
                    qu.add(e.nbr);
                }
            }
        }
        return false;
    }

    public static boolean isCyclicGraph(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                if(isCompCyclic(graph, v, vis) == true) {
                    return true;
                }
            }
        }
        return false;
    }


    private static class DHelper implements Comparable<DHelper> {
        int vtx;
        String psf;
        int wsf;

        DHelper(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(DHelper o) {
            return this.wsf - o.wsf;
        }
    }

    // djikstra's Algorithm -> shortest path in terms of weight
    public static void djikstrasAlgo(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<DHelper> pq = new PriorityQueue<>();
        pq.add(new DHelper(src, src + "", 0));
        boolean[] vis = new boolean[graph.length];
        while(pq.size() > 0) {
            // 1. get + remove
            DHelper rem = pq.remove();
            // 2. mark*
            if(vis[rem.vtx] == true) {
                continue;
            } else {
                vis[rem.vtx] = true;
            }
            // 3. work
            System.out.println(rem.vtx + " via " + rem.psf + " @ " + rem.wsf);
            // 4. add unvisited nbrs
            for(Edge e : graph[rem.vtx]) {
                if(vis[e.nbr] == false) {
                    pq.add(new DHelper(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
                } 
            }
        }
    }

    // prims Algo -> min wire required to connect all PCs
    public static class PHelper implements Comparable<PHelper> {
        int src;
        int prt;
        int wt;

        PHelper(int src, int prt, int wt) {
            this.src = src;
            this.prt = prt;
            this.wt = wt;
        }

        public int compareTo(PHelper o) {
            return this.wt - o.wt;
        }
    }

    public static void primsAlgo(ArrayList<Edge>[] graph) {
        PriorityQueue<PHelper> pq = new PriorityQueue<>();
        pq.add(new PHelper(0, -1, 0));

        boolean[] vis = new boolean[graph.length];

        while(pq.size() > 0) {
            PHelper rem = pq.remove();

            if(vis[rem.src] == true) {
                continue;
            } 
            vis[rem.src] = true;

            // work -> add edge
            if(rem.prt != -1)
                System.out.println("[" + rem.src + "-" + rem.prt + "@" + rem.wt + "]");
            
            for(Edge e : graph[rem.src]) {
                if(vis[e.nbr] == false) {
                    pq.add(new PHelper(e.nbr, rem.src, e.wt));
                }
            }
        }
    }

    // bpair -> bipartite pair
    public static class bpair {
        int src;
        int time;

        public bpair(int src, int time) {
            this.src = src;
            this.time = time;
        }
    }

    public static boolean isBipartiteComp(ArrayList<Edge>[] graph, int src, int[] vis) {
        Queue<bpair> qu = new LinkedList<>();

        qu.add(new bpair(src, 0));
        while(qu.size() > 0) {
            bpair rem = qu.remove();
            
            if(vis[rem.src] != -1) {
                // already marked
                if(vis[rem.src] % 2 == rem.time % 2) {
                    continue;
                } else {
                    return false;
                }
            } else {
                vis[rem.src] = rem.time;
            }

            for(Edge e : graph[rem.src]) {
                if(vis[e.nbr] == -1) {
                    qu.add(new bpair(e.nbr, rem.time + 1));
                }
            }
        }
        return true;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int[] vis = new int[graph.length];
        Arrays.fill(vis, -1);

        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == -1) {
                boolean res = isBipartiteComp(graph, v, vis);
                if(res == false) return false;
            }
        }
        return true;
    }

    // spread of infection
    public static int spreadOfInfection(ArrayList<Edge>[] graph, int src, int time) {
        int count = 0;
        Queue<int[]> qu = new LinkedList<>();
        // int[] first element is src, and second is time
        qu.add(new int[]{src, 1});
        boolean[] vis = new boolean[graph.length];
        while(qu.size() > 0) {
            int[] rem = qu.remove();
            if(vis[rem[0]] == true) {
                continue;
            } else {
                vis[rem[0]] = true;
            }
            System.out.println(rem[0] + " at time t = " + rem[1]);
            count++;
            for(Edge e : graph[rem[0]]) {
                if(vis[e.nbr] == false && rem[1] < time) {
                    qu.add(new int[]{e.nbr, rem[1] + 1});
                }
            }
        }
        return count;
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
        // addEdge(graph, 2, 5, 4);
        // addEdge(graph, 7, 8, 10);
        // display(graph);

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
        
        // System.out.println(getConnectedComponents(graph));
        
        // hamiltonianPathAndCycle(graph, 6, vis, "", 6);

        // int[][] board = new int[5][5];
        // printKnightsTour(board, 2, 0, 1);

        // bfsAlgo(graph, 0);
        // djikstrasAlgo(graph, 0);
        // primsAlgo(graph);
        // System.out.println(isBipartite(graph));
        System.out.println(spreadOfInfection(graph, 6, 3));
    }


    public static void main(String[] args) {
        fun();
    }
}

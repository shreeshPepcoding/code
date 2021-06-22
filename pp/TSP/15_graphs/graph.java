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

        // addEdge(graph, 0, 1, 10);
        // addEdge(graph, 0, 3, 40);
        // addEdge(graph, 1, 2, 10);
        // addEdge(graph, 2, 3, 10);
        // addEdge(graph, 3, 4, 2);
        // addEdge(graph, 4, 5, 3);
        // addEdge(graph, 4, 6, 8);
        // addEdge(graph, 5, 6, 3);
        // addEdge(graph, 2, 5, 0);


        addEdge(graph, 0, 1, 30);
        addEdge(graph, 0, 3, 20);
        addEdge(graph, 1, 2, 5);
        addEdge(graph, 2, 3, 5);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 4);

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

    public static boolean dfsFroCycle(ArrayList<Edge>[] graph, int src, boolean[] vis, int par) {
        vis[src] = true;
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            // cycle is present
            if(vis[nbr] == true && nbr != par) {
                return true;
            }
            if(vis[nbr] == false) {
                boolean res = dfsFroCycle(graph, nbr, vis, src);
                if(res == true) return true;
            }
        }
        return false;
    }

    public static boolean isCyclic(ArrayList<Edge>[] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];

        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                // boolean res = bfsForCycle(graph, v, vis);
                boolean res = dfsFroCycle(graph, v, vis, -1);
                if(res == true) return true;
            }
        }
        return false;
    }

    public static class BPair {
        int vtx;
        int level; // discovery time

        public BPair(int vtx, int level) {
            this.vtx = vtx;
            this.level = level;
        }
    }

    public static boolean isBipertiteComp(ArrayList<Edge>[] graph, int src, int[] vis) {
        Queue<BPair> qu = new LinkedList<>();

        qu.add(new BPair(src, 0));
        while(qu.size() > 0) {
            BPair rem = qu.remove();
            
            if(vis[rem.vtx] != -1) {
                // already discovered
                // 1. if again discovered with same level-> continue
                // 2. if again discovered, but with different level -> return false, 
                // because odd size cycle is present in graph
                if(vis[rem.vtx] == rem.level) 
                    continue;
                else 
                    return false;
            } 
            vis[rem.vtx] = rem.level;

            for(Edge e : graph[rem.vtx]) {
                int nbr = e.nbr;
                if(vis[nbr] == -1) {
                    qu.add(new BPair(nbr, rem.level + 1));
                }
            }
        }
        return true;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        
        for(int v = 0; v < n; v++) {
            if(vis[v] == -1) {
                boolean res = isBipertiteComp(graph, v, vis);
                if(res == false) return false;
            }
        }
        return true;
    }

    public static int spreadInfection(ArrayList<Edge>[] graph, int src, int t) {
        Queue<BPair> qu = new LinkedList<>();
        
        int[] vis = new int[graph.length];
        qu.add(new BPair(src, 1));
        int count = 0;
        while(qu.size() > 0) {
            // 1. get + remove
            BPair rem = qu.remove();
            // 2. mark * 
            if(vis[rem.vtx] != 0) {
                continue;
            }
            vis[rem.vtx] = rem.level;
            // 3. work
            if(rem.level > t) {
                break;
            }
            System.out.println(rem.vtx + " become sick at time " + rem.level);
            count++;
            // 4. add neighbours
            for(Edge e : graph[rem.vtx]) {
                int nbr = e.nbr;
                if(vis[nbr] == 0) {
                    qu.add(new BPair(nbr, rem.level + 1));
                }
            }
        }
        return count;
    }

    public static class DPair implements Comparable<DPair> {
        int wsf;
        int vtx;
        String psf;

        public DPair(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(DPair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void dijkstras(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<DPair> pq = new PriorityQueue<>();
        pq.add(new DPair(src, "" + src, 0));
        boolean[] vis = new boolean[graph.length];

        while(pq.size() > 0) {
            // 1. get + remove
            DPair rem = pq.remove();
            // 2. mark*
            if(vis[rem.vtx] == true) {
                continue;
            }
            vis[rem.vtx] = true;
            // 3. work -> printing of paths
            System.out.println(rem.vtx + " via " + rem.psf + " @ " + rem.wsf);
            // 4. add neighbours
            for(Edge e : graph[rem.vtx]) {
                int nbr = e.nbr;
                if(vis[nbr] == false) {
                    pq.add(new DPair(nbr, rem.psf + nbr, rem.wsf + e.wt));
                }
            }
        }
    }

    public static class Phelper implements Comparable<Phelper> {
        int vtx;
        int parent;
        int wt;

        public Phelper(int vtx, int parent, int wt) {
            this.vtx = vtx;
            this.parent = parent;
            this.wt = wt;
        }

        public int compareTo(Phelper o) {
            return this.wt - o.wt;
        } 
    }

    public static void prims(ArrayList<Edge>[] graph) {
        PriorityQueue<Phelper> pq = new PriorityQueue<>();
        pq.add(new Phelper(3, -1, 0));

        int n = graph.length;
        ArrayList<Edge>[] mst = new ArrayList[n];
        for(int v = 0; v < n; v++) {
            mst[v] = new ArrayList<>();
        }

        boolean[] vis = new boolean[n];

        while(pq.size() > 0) {
            // 1. get + rem
            Phelper rem = pq.remove();
            // 2. mark
            if(vis[rem.vtx] == true) continue;
            vis[rem.vtx] = true;
            // 3. work -> print(for question) + add edge(mst graph)
            if(rem.parent != -1) {
                System.out.println("[" + rem.vtx + "-" + rem.parent + "@" + rem.wt + "]");
                addEdge(mst, rem.vtx, rem.parent, rem.wt);
            }

            // 4. add neighbour
            for(Edge e : graph[rem.vtx]) {
                if(vis[e.nbr] == false) {
                    pq.add(new Phelper(e.nbr, rem.vtx, e.wt));
                }
            }
        }
        display(mst);
    }


    // ~~~~~~Topological sort-> valid for directed and acyclic graph~~~~~~~~
    public static void tsHelper(ArrayList<Edge>[] graph, int src, boolean[] vis, Stack<Integer> st) {
        vis[src] = true;
        for(Edge e : graph[src]) {
            int nbr = e.nbr;
            if(vis[nbr] == false) {
                tsHelper(graph, nbr, vis, st);
            }
        }

        st.push(src);
    }

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        int n = graph.length;

        boolean[] vis = new boolean[n];

        Stack<Integer> st = new Stack<>();
        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                tsHelper(graph, v, vis, st);
            }
        }

        // order of compilation is just reverse of topological sort
        // and in stack we achieve topological sort
        // but for now, we have to print topo. sort
        while(st.size() > 0) {
            System.out.println(st.pop());
        }
    }

    // cycle detection in directed graph : https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
    public boolean dfsCycle(ArrayList<ArrayList<Integer>> graph, int src, boolean[] vis, boolean[] mypath) {
        vis[src] = true;
        mypath[src] = true;
        for(int nbr : graph.get(src)) {
            if(mypath[nbr] == true) {
                return true;
            } else if(vis[nbr] == false) {
                boolean res = dfsCycle(graph, nbr, vis, mypath);
                if(res == true) return true;
            }
        }

        mypath[src] = false;
        return false;
    }

    public boolean isCyclic(int n, ArrayList<ArrayList<Integer>> graph)  {
        boolean[] vis = new boolean[n];
        boolean[] mypath = new boolean[n];
        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                boolean res = dfsCycle(graph, v, vis, mypath);
                if(res == true) return true;
            }
        }
        return false; 
    }

    // leetcode 994: https://leetcode.com/problems/rotting-oranges/
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
    public static int orangesRotting(int[][] graph) {
        /* 1. make a queue and add rotted orange in it with time t = 0
        as well as maintain a count of fresh + rotted orange for final result */

        Queue<OPair> qu = new LinkedList<>();
        int orange = 0;
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] == 2) {
                    qu.add(new OPair(i, j, 0));
                    orange++;
                } else if(graph[i][j] == 1) {
                    orange++;
                }
            }
        }
        if(orange == 0) return 0;
        // make a direction array for edges of graph
        int[] xdir = {-1, 0, 1, 0};
        int[] ydir = {0, -1, 0, 1};
        // 2. process bfs and find max time; -> marking as -1
        int time = -1;

        while(qu.size() > 0) {
            int size = qu.size();
            while(size-- > 0) {
                OPair rem = qu.remove();
                if(graph[rem.x][rem.y] == -1) {
                    continue;
                }
                graph[rem.x][rem.y] = -1;
                time = rem.t;
                orange--;
                for(int d = 0; d < 4; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];
 
                    // check validity + unvisited orange
                    if(0 <= r && r < graph.length && 0 <= c && c < graph[0].length && graph[r][c] == 1) {
                        qu.add(new OPair(r, c, rem.t + 1));
                    }
                }
            }
        }
        // System.out.println("total time : " + time);
        // System.out.println("Orange remains : " + orange);
        return orange == 0 ? time : -1;
    }


    public static void fun() {
        // int n = 7;
        // ArrayList<Edge>[] graph = createGraph();

        int[][] grid = {
            {2, 0, 1, 0, 1},
            {0, 1, 2, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 1, 1},
            {1, 2, 1, 1, 2}
        };

        orangesRotting(grid);



        // prims(graph);
        // dijkstras(graph, 0);


        // spreadInfection(graph, 6, 5);
        // System.out.println(isBipartite(graph));
        // bfs(graph, 2);
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
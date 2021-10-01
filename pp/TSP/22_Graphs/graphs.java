import java.util.*;

public class graphs {

    public static class Edge {
        int nbr;
        int wt;

        public Edge(int nbr, int wt) {
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int v1, int v2, int wt) {
        graph[v1].add(new Edge(v2, wt));
        graph[v2].add(new Edge(v1, wt));
    }

    public static void solve() {
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 0, 1, 10);
        /* and so on....*/
    }

        // vis[src] = true;
        // for(Edge e : graph[src]) {
        //     if(vis[e.nbr] == false) {
        //         if(dfs(graph, e.nbr, dst, vis, psf + src) == true) {
        //             return true;
        //         }
        //     }
        // }
        // return false;

    public static boolean dfs(ArrayList<Edge>[] graph, int src, int dst,
         boolean[] vis, String psf) {
        if(src == dst) {
            psf += dst;
            System.out.println(psf);
            return true;
        }

        vis[src] = true;
        boolean res = false;
        for(Edge e : graph[src]) {
            if(vis[e.nbr] == false) {
                res = res || dfs(graph, e.nbr, dst, vis, psf + src);
            }
        }
        return res;
    }

    public static class BPair {
        int vtx;
        String psf;
        int wsf;

        public BPair(int vtx, int wsf, String psf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
    }

    public static boolean bfs(ArrayList<Edge>[] graph, int src,
         int dst, boolean[] vis) {
        Queue<BPair> qu = new LinkedList<>();
        qu.add(new BPair(src, 0, "" + src));
        boolean res = false;
        while(qu.size() > 0) {
            // 1. get + remove
            BPair rem = qu.remove();
            // 2. mark *
            if(vis[rem.vtx] == true)
                continue;
            vis[rem.vtx] = true;
            // 3. work
            System.out.println(rem.vtx + " " + rem.psf + " @ " + rem.wsf);
            if(src == dst) {
                res = true;
                break;
            }
            // 4. add neighbours
            for(Edge e : graph[rem.vtx]) {
                if(vis[e.nbr] == false) {
                    qu.add(new BPair(e.nbr, rem.wsf + e.wt, rem.psf + e.nbr));
                }
            }
        }
        return res;
    }

    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};

    // leetcode 200. https://leetcode.com/problems/number-of-islands/
    private static void islands_dfs(char[][] graph, int x, int y) {
        graph[x][y] = '0';
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];
            if(r >= 0 && r < graph.length && c >= 0 && c < graph[0].length && graph[r][c] != '0') {
                islands_dfs(graph, r, c);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int islands = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    islands_dfs(grid, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    // leetcode 1020. https://leetcode.com/problems/number-of-enclaves/
    private static void numEnclave_dfs(int[][] graph, int x, int y) {
        graph[x][y] = 0;
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];
            if(r >= 0 && r < graph.length && c >= 0 
                && c < graph[0].length && graph[r][c] != 0) {
                numEnclave_dfs(graph, r, c);
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        // for 0th row
        for(int c = 0; c < grid[0].length; c++) {
            if(grid[0][c] == 1) {
                numEnclave_dfs(grid, 0, c);
            }
        }
        // for last row
        for(int c = 0; c < grid[0].length; c++) {
            if(grid[grid.length - 1][c] == 1) {
                numEnclave_dfs(grid, grid.length - 1, c);
            }
        }
        // for 0th col
        for(int r = 0; r < grid.length; r++) {
            if(grid[r][0] == 1) {
                numEnclave_dfs(grid, r, 0);
            }
        }
        // for last col
        for(int r = 0; r < grid.length; r++) {
            if(grid[r][grid[0].length - 1] == 1) {
                numEnclave_dfs(grid, r, grid[0].length - 1);
            }
        }
        // count remaining one
        int one = 0;
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1) one++;
            }
        }
        return one;
    }

    // leetcode 994. https://leetcode.com/problems/rotting-oranges/
    public class OPair {
        int r;
        int c;
        int t;

        public OPair(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<OPair> qu = new LinkedList<>();

        // 1. travel on grid, add rotted orange in queue, and count oranges
        int orange = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2)
                    qu.add(new OPair(i, j, 0));

                if(grid[i][j] == 1 || grid[i][j] == 2)
                    orange++;
            }
        }

        // 2. apply BFS and find remaining orange count and time as well
        int time = 0;
        
        while(qu.size() > 0) {
            OPair rem = qu.remove();
            if(grid[rem.r][rem.c] == -2) {
                continue;
            }
            grid[rem.r][rem.c] = -2;

            orange--;
            time = rem.t;

            for(int d = 0; d < 4; d++) {
                int x = rem.r + xdir[d];
                int y = rem.c + ydir[d];

                if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                    qu.add(new OPair(x, y, rem.t + 1));
                }
            }
        }
        return orange == 0 ? time : -1;
    } 

    // lintcode 860. https://www.lintcode.com/problem/860/
    static StringBuilder str;

    static char[] dir = {'t', 'l', 'd', 'r'};

    private void distinctCount(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];

            if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                str.append("" + dir[d]);
                distinctCount(grid, r, c);
            }
        }
        str.append("z");
    }

    public int numberofDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    str = new StringBuilder("x");
                    distinctCount(grid, i, j);
                    set.add(str.toString());
                }
            }
        }
        return set.size();
    }

    // leetcode 815. https://leetcode.com/problems/bus-routes/
    private void makeMapWithStandAndBus(int[][] routes, HashMap<Integer, ArrayList<Integer>> map) {
        for(int r = 0; r < routes.length; r++) { // r -> bus number
            for(int c = 0; c < routes[r].length; c++) {
                int stand = routes[r][c];
                if(map.containsKey(stand) == true) {
                    // key is already present, so just add value i.e. bus number with that bus stop
                    map.get(stand).add(r);
                } else {
                    ArrayList<Integer> busNo = new ArrayList<>();
                    busNo.add(r);
                    map.put(stand, busNo);
                }
            }
        }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        makeMapWithStandAndBus(routes, map);

        HashSet<Integer> visBus = new HashSet<>();
        HashSet<Integer> visStand = new HashSet<>();
        Queue<Integer> qu = new LinkedList<>();
        qu.add(source);
        int level = -1;
        while(qu.size() > 0) {
            int size = qu.size();
            level++;
            while(size-- > 0) {
                int rem = qu.remove();
                
                if(rem == target) return level;

                for(int busNum : map.get(rem)) {
                    if(visBus.contains(busNum) == true) {
                        continue;
                    } else {
                        for(int busStand : routes[busNum]) {
                            if(visStand.contains(busStand) == false) {
                                visStand.add(busStand);
                                qu.add(busStand);
                            }
                        }
                        visBus.add(busNum);
                    }
                }
            }
        }
        return -1;
    }

    // leetcode 1584. https://leetcode.com/problems/min-cost-to-connect-all-points/
    private class Pair01 {
        int x;
        int y;

        int l;

        public Pair01(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair01(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }

    }
    public int[][] updateMatrix(int[][] mat) {
        Queue<Pair01> qu = new LinkedList<>();
        boolean[][] vis = new boolean[mat.length][mat[0].length];
        // 1. tarvel and fill queue with initial 0
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 0) {
                    qu.add(new Pair01(i, j));
                    vis[i][j] = true;
                }
            }
        }
        // 2. Run BFS and mark level in given matrix and use a visited matrix
        int level = -1;
        while(qu.size() > 0) {
            level++;
            int size = qu.size();
            while(size-- > 0) {
                Pair01 rem = qu.remove();
                mat[rem.x][rem.y] = level;
                for(int d = 0; d < 4; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];
                    if(r >= 0 && r < mat.length && c >= 0 && c < mat[0].length && vis[r][c] == false) {
                        qu.add(new Pair01(r, c));
                        vis[r][c] = true;
                    }
                }
            }
        }
        return mat;
    }

    // methos 2 for BFS
    public int[][] updateMatrix2(int[][] mat) {
        Queue<Pair01> qu = new LinkedList<>();
        boolean[][] vis = new boolean[mat.length][mat[0].length];
        // 1. tarvel and fill queue with initial 0
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 0) {
                    qu.add(new Pair01(i, j, 0));
                    vis[i][j] = true;
                }
            }
        }
        // 2. Run BFS and mark level in given matrix and use a visited matrix
        while(qu.size() > 0) {
            // 1. get + remove
            Pair01 rem = qu.remove();
            // 2. mark *
            // 3. work
            mat[rem.x][rem.y] = rem.l;
            for(int d = 0; d < 4; d++) {
                int r = rem.x + xdir[d];
                int c = rem.y + ydir[d];
                if(r >= 0 && r < mat.length && c >= 0 && c < mat[0].length && vis[r][c] == false) {
                    qu.add(new Pair01(r, c, rem.l + 1));
                    vis[r][c] = true;
                }
            }
        }
        return mat;
    }

    // leetcode 1162. https://leetcode.com/problems/as-far-from-land-as-possible/
    private class pairD {
        int x;
        int y;

        public pairD(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int maxDistance(int[][] grid) {
        // 1. iterate on grid and add all one's in queue and mark it as well
        Queue<pairD> qu = new LinkedList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    qu.add(new pairD(i, j));
                    grid[i][j] = -1;
                }
            }
        }

        // run BFS 
        if(qu.size() == 0 || qu.size() == grid.length * grid[0].length)
            return -1;
        
        int level = -1;
        while(qu.size() > 0) {
            level++;
            int size = qu.size();
            while(size-- > 0) {
                pairD rem = qu.remove();
                for(int d = 0; d < 4; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];

                    if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != -1) {
                        qu.add(new pairD(r, c));
                        grid[r][c] = -1;
                    }
                }
            }
        }
        return level;
    }

    // mother vertex -> portal (https://practice.geeksforgeeks.org/problems/mother-vertex/1)
    private static void dfsForStack(ArrayList<ArrayList<Integer>> graph, 
        int src, boolean[] vis, Stack<Integer> st) {
        vis[src] = true;
        for(int nbr : graph.get(src)) {
            if(vis[nbr] == false) {
                dfsForStack(graph, nbr, vis, st);
            }
        }
        st.push(src);
    }

    private static int count = 0;
    private static void dfsForCount(ArrayList<ArrayList<Integer>> graph, 
        int src, boolean[] vis) {
        vis[src] = true;
        count++;
        for(int nbr : graph.get(src)) {
            if(vis[nbr] == false) {
                dfsForCount(graph, nbr, vis);
            }
        }
    }

    public static int findMotherVertex(int N, ArrayList<ArrayList<Integer>> graph){
        boolean[] vis = new boolean[N];
        Stack<Integer> st = new Stack<>();
        for(int v = 0; v < N; v++) {
            if(vis[v] == false) {
                dfsForStack(graph, v, vis, st);
            }
        }

        int top = st.peek();
        count = 0;
        vis = new boolean[N];
        dfsForCount(graph, top, vis);
        return count == N ? top + 1: -1;
    }

    // leetcode 934. https://leetcode.com/problems/shortest-bridge/
    private void dfsShortestBridge(int[][] grid, int x, int y, Queue<pairD> qu) {
        grid[x][y] = -1;
        qu.add(new pairD(x, y));
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];

            if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                dfsShortestBridge(grid, r, c, qu);
            }
        }

    }

    public int shortestBridge(int[][] grid) {
        Queue<pairD> qu = new LinkedList<>();
        // 1. add all one in queue, using DFS from first one encoutered
        loop1 : for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    dfsShortestBridge(grid, i, j, qu);
                    break loop1;
                }
            }
        }
        // 2. apply BFS on queue and return level when another one is encounter
        int level = -1;

        while(qu.size() > 0) {
            int size = qu.size();
            level++;
            while(size-- > 0) {
                pairD rem = qu.remove();
                for(int d = 0; d < 4; d++) {
                    int r = rem.x + xdir[d];
                    int c = rem.y + ydir[d];
                    if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != -1) {
                        if(grid[r][c] == 1) {
                            return level;
                        }
                        grid[r][c] = -1;
                        qu.add(new pairD(r, c));
                    }
                }
                
            }
        }

        return -1;
    }

    // leetcode 1192. https://leetcode.com/problems/critical-connections-in-a-network/
    static int time = 0;

    private static void dfsArticulation(ArrayList<ArrayList<Integer>> graph, 
    int src, boolean[] vis, int[] parent, int[] disc, int[] low, boolean[] arti,
    List<List<Integer>> res) {
        disc[src] = low[src] = time;
        time++;
        vis[src] = true;
        int count = 0; // count for finding original source status about articulation
        for(int nbr : graph.get(src)) {
            if(vis[nbr] == true && parent[src] != nbr) {
                // visited but not parent
                low[src] = Math.min(low[src], disc[nbr]);
            } else if(vis[nbr] == false) {
                // unvisited neighbour
                parent[nbr] = src;
                dfsArticulation(graph, nbr, vis, parent, disc, low, arti, res);
                low[src] = Math.min(low[src], low[nbr]);
                count++; // increment in count if we are making call to nbr
                if(disc[src] < low[nbr]) {
                    List<Integer> edge = new ArrayList<>();
                    edge.add(src);
                    edge.add(nbr);
                    res.add(edge);
                    arti[src] = true;
                }
            }
        }
    }


    private static List<List<Integer>> articulationBridge(ArrayList<ArrayList<Integer>> graph) {
        int count = 0;
        int n = graph.size();
        int[] parent = new int[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] vis = new boolean[n];
        boolean[] arti = new boolean[n];
        parent[0] = -1; // parent of starting point is -1
        time = 0;
        List<List<Integer>> res = new ArrayList<>();
        dfsArticulation(graph,  0, vis, parent, disc, low, arti, res); 
        return res;
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(List<Integer> edge : connections) {
            int v1 = edge.get(0);
            int v2 = edge.get(1);

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        List<List<Integer>> res = articulationBridge(graph);
        return res;
    }

    public static void main(String[] args) {

    }
}
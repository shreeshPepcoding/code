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

    // lintcode 434. https://www.lintcode.com/problem/434/
    class Point {
        int x;
        int y;
        
        Point() { x = 0; y = 0; }
        
        Point(int a, int b) { 
            x = a; y = b;
        }
    }

    public int find(int[] parent, int x) {
        if(parent[x] == x) {
            return x;
        }

        int temp = find(parent, parent[x]);
        parent[x] = temp;
        return temp;
    }

    public void union(int lx, int ly, int[] rank, int[] parent) {
        if(rank[lx] > rank[ly]) {
            parent[ly] = lx;
        } else if(rank[lx] < rank[ly]) {
            parent[lx] = ly;
        } else {
            parent[ly] = lx;
            rank[lx]++;
        }
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        if(operators == null) {
            return new ArrayList<>();
        }
        int[] parent = new int[n * m];
        Arrays.fill(parent, -1);
        int[] rank = new int[n * m];

        List<Integer> res = new ArrayList<>();
        int count = 0;

        for(Point p : operators) {
            int row = p.x;
            int col = p.y;

            int boxNo = row * m + col;
            if(parent[boxNo] != -1) {
                res.add(count);
                continue;
            }
            parent[boxNo] = boxNo;
            rank[boxNo] = 1;
            count++;

            for(int d = 0; d < 4; d++) {
                int r = row + xdir[d];
                int c = col + ydir[d];

                if(r < 0 || r >= n || c < 0 || c >= m || parent[r * m + c] == -1)
                    continue;
                
                int bNo = r * m + c;
                // find set leader
                int lx = find(parent, bNo);
                int ly = find(parent, boxNo);

                // union them
                if(lx != ly) {
                    union(lx, ly, rank, parent);
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }

    // leetcode 684. https://leetcode.com/problems/redundant-connection/
    public int[] findRedundantConnection(int[][] edges) {
        // number of vertices
        int n = edges.length;

        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            rank[i] = 1;
            parent[i] = i;
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // find set leader of both u and v
            int lu = find(parent, u);
            int lv = find(parent, v);
            if(lu == lv) {
                // it means u-v edge is responsible for cycle
                return edge;
            }
            union(lu, lv, rank, parent);
        }
        return new int[0];
    }

    // leetcode 685. https://leetcode.com/problems/redundant-connection-ii/
    private static int[] parent;
    private static int[] rank;

    private int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        int temp = find(parent[x]);
        parent[x] = temp;
        return temp;
    }

    private boolean union(int x, int y) {
        int lx = find(x);
        int ly = find(y);

        if(lx == ly) {
            return true;
        } else {
            if(rank[lx] > rank[ly]) {
                parent[ly] = lx;
            } else if(rank[lx] < rank[ly]) {
                parent[lx] = ly;
            } else {
                parent[ly] = lx;
                rank[lx]++;
            }
        }
        return false;
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] indegree = new int[n + 1]; // because index in 1 based
        Arrays.fill(indegree, -1);
        int blackList1 = -1;
        int blackList2 = -1;

        for(int i = 0; i < edges.length; i++) {
            int v = edges[i][1];

            if(indegree[v] == -1) {
                indegree[v] = i;
            } else {
                blackList1 = i;
                blackList2 = indegree[v];
                break;
            }
        }

        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // apply DSU and avoid blackList1 edge index, and check if graqph is cyclic or not
        for(int i = 0; i < edges.length; i++) {
            if(i == blackList1) {
                continue;
            }
            int u = edges[i][0];
            int v = edges[i][1];

            boolean isCyclic = union(u, v);
            if(isCyclic == true) {
                if(blackList1 == -1) {
                    return edges[i];
                } else {
                    return edges[blackList2];
                }
            }
        }
        return edges[blackList1];
    }

    // leetcode 1584. https://leetcode.com/problems/min-cost-to-connect-all-points/
    public int minCostConnectPoints(int[][] coords) {
        int size = coords.length * (coords.length - 1) / 2;
        int[][] points = new int[size][3];

        int indx = 0;
        for(int i = 0; i < coords.length; i++) {
            for(int j = i + 1; j < coords.length; j++) {
                int dist = Math.abs(coords[j][0] - coords[i][0]) + Math.abs(coords[j][1] - coords[i][1]);
                int[] point = {i, j, dist};
                points[indx] = point;
                indx++;
            }
        }
        
        // kruskal Algo, sort all the edges 
        Arrays.sort(points, (val1, val2) -> Integer.compare(val1[2], val2[2]));

        int[] parent = new int[coords.length];
        int[] rank = new int[coords.length];

        for(int i = 0; i < coords.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        int cost = 0;
        for(int i = 0; i < size; i++) {
            int u = points[i][0];
            int v = points[i][1];
            int wt = points[i][2];

            int lu = find(parent, u);
            int lv = find(parent, v);

            if(lu == lv)
                continue;
            
            union(lu, lv, rank, parent);
            // add edge
            cost += wt;
        }
        return cost;
    }

    // leetcode 990. https://leetcode.com/problems/satisfiability-of-equality-equations/
    public boolean equationsPossible(String[] equations) {
        // process '==' and union then
        int[] parent = new int[26];
        int[] rank = new int[26];
        for(int i = 0; i < 26; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i = 0; i < equations.length; i++) {
            if(equations[i].charAt(1) == '=') {
                int x = equations[i].charAt(0) - 'a';
                int y = equations[i].charAt(3) - 'a';

                int lx = find(parent, x);
                int ly = find(parent, y);

                if(lx == ly) {
                    continue;
                }
                union(lx, ly, rank, parent);
            }
        }
        
        // check set leader for '!=' equality, if same then return false;
        for(int i = 0; i < equations.length; i++) {
            if(equations[i].charAt(1) == '!') {
                int x = equations[i].charAt(0) - 'a';
                int y = equations[i].charAt(3) - 'a';
                int lx = find(parent, x);
                int ly = find(parent, y);
                if(lx == ly) return false;
            }
        }
        return true;
    }

    // leetcode 737, sentence similarity 2

    private static HashMap<String, String> par; // parent
    private static HashMap<String, Integer> ran; // rank

    private static String find(String x) {
        if(par.containsKey(x) == false) {
            par.put(x, x);
            ran.put(x, 1);
        }

        if(par.get(x).equals(x) == true) {
            return x;
        }
        String temp = find(par.get(x));
        par.put(x, temp);
        return temp;
    }

    private static void union(String u, String v) {
        String lx = find(u);
        String ly = find(v);

        if(lx.equals(ly) == false) {
            if(ran.get(lx) > ran.get(ly)) {
                par.put(ly, lx);
            } else if(ran.get(lx) < ran.get(ly)) {
                par.put(lx, ly);
            } else {
                par.put(ly, lx);
                ran.put(lx, ran.get(lx) + 1);
            }
        }
    }

    public static boolean areSentencesSimilarTwo(String[] Sentence1, String[] Sentence2, String[][] pairs) {
        par = new HashMap<>();
        ran = new HashMap<>();

        if(Sentence1.length != Sentence2.length) return false;

        for(String[] pair : pairs) {
            union(pair[0], pair[1]);
        }

        for(int i = 0; i < Sentence1.length; i++) {
            String w1 = Sentence1[i];
            String w2 = Sentence2[i];

            if(w1.equals(w2) == true || find(w1).equals(find(w2)) == true) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    // ====================CYCLE=======================
    // cycle in undirected graph, https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
    public boolean isCycle1(int V, ArrayList<ArrayList<Integer>> adj) {
        
    }

    
    // cycle in directed graph, https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
    public boolean isCyclic2(int V, ArrayList<ArrayList<Integer>> adj) {
        
    }

    // leetcode 207, https://leetcode.com/problems/course-schedule/
    public boolean canFinish(int n, int[][] edges) {
        // prepare graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // make an indegree array from graph
        int[] indegree = new int[n];
        for(int i = 0; i < n; i++) {
            for(int nbr : graph.get(i)) {
                indegree[nbr]++;
            }
        }

        // add element in queue which have 0 indegree
        Queue<Integer> qu = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                qu.add(i);
            }
        }

        int count = 0;
        while(qu.size() > 0) {
            // 1. get + remove
            int rem = qu.remove();
            // 2. print*
            count++;
            // 3. decrease indegree of nbr and if indegree become 0 then add in queue
            for(int nbr : graph.get(rem)) {
                indegree[nbr]--;
                if(indegree[nbr] == 0) {
                    qu.add(nbr);
                }
            }
        }
        return count == n;
    }

    // leetcode 210, https://leetcode.com/problems/course-schedule-ii/
    public int[] findOrder(int n, int[][] edges) {
        // prepare graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // make an indegree array from graph
        int[] indegree = new int[n];
        for(int i = 0; i < n; i++) {
            for(int nbr : graph.get(i)) {
                indegree[nbr]++;
            }
        }

        // add element in queue which have 0 indegree
        Queue<Integer> qu = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                qu.add(i);
            }
        }

        int count = 0;
        int[] res = new int[n];
        int indx = n - 1;
        while(qu.size() > 0) {
            // 1. get + remove
            int rem = qu.remove();
            // 2. print*
            res[indx] = rem;
            indx--;
            count++;
            // 3. decrease indegree of nbr and if indegree become 0 then add in queue
            for(int nbr : graph.get(rem)) {
                indegree[nbr]--;
                if(indegree[nbr] == 0) {
                    qu.add(nbr);
                }
            }
        }
        if(count != n) {
            res = new int[0];
            return res;
        }
        return res;
    }

    // bellman ford
    private static int[] bellmanFord(int n, int[][] edges) {
        int[] path = new int[n];
        Arrays.fill(path, Integer.MAX_VALUE);
        path[0] = 0;
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(path[u] != Integer.MAX_VALUE && path[u] + wt < path[v]) {
                path[v] = path[u] + wt;
            }
        }
        return path;
    }    

    // negative weight cycle present in graph
    private static int isNegativeCyclePresent(int n, int[][] edges) {
        int[] path = new int[n];
        Arrays.fill(path, Integer.MAX_VALUE);
        path[0] = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if(path[u] != Integer.MAX_VALUE && path[u] + wt < path[v]) {
                    path[v] = path[u] + wt;
                }
            }
        }
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if(path[u] != Integer.MAX_VALUE && path[u] + wt < path[v]) {
                return 1;
            }
        }
        return 0;
    }

    // remove maximum number of edges
    private int find4Edge(int x, int[] parent) {
        if(parent[x] == x) {
            return x;
        }
        int temp = find4Edge(parent[x], parent);
        parent[x] = temp;
        return temp;
    }

    private boolean unionEdge(int x, int y, int[] parent, int[] rank) {
        int lx = find4Edge(x, parent);
        int ly = find4Edge(y, parent);

        if(lx == ly) {
            return false;
        } else {
            if(rank[lx] > rank[ly]) {
                parent[ly] = lx;
            } else if(rank[lx] < rank[ly]) {
                parent[lx] = ly;
            } else {
                parent[ly] = lx;
                rank[lx]++;
            }
        }
        return true;
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> {
            return -Integer.compare(a[0], b[0]);
        });

        int mergea = 1;
        int mergeb = 1;
        int removeEdge = 0;

        int[] parenta = new int[n + 1];
        int[] parentb = new int[n + 1];
        int[] ranka = new int[n + 1];
        int[] rankb = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            parenta[i] = i;
            parentb[i] = i;
            ranka[i] = 1;
            rankb[i] = 1;
        }

        for(int[] edge : edges) {
            if(edge[0] == 3) {
                boolean tempa = unionEdge(edge[1], edge[2], parenta, ranka);
                boolean tempb = unionEdge(edge[1], edge[2], parentb, rankb);
                if(tempa == true) {
                    mergea++;
                }
                if(tempb == true) {
                    mergeb++;
                }
                if(tempa == false && tempb == false) {
                    removeEdge++;
                }
            } else if(edge[0] == 1) {
                boolean tempa = unionEdge(edge[1], edge[2], parenta, ranka);
                if(tempa == true) {
                    mergea++;
                } else {
                    removeEdge++;
                }
            } else {
                boolean tempb = unionEdge(edge[1], edge[2], parentb, rankb);
                if(tempb == true) {
                    mergeb++;
                } else {
                    removeEdge++;
                }
            }
        }
        if(mergea == n && mergeb == n) {
            return removeEdge;
        } else {
            return -1;
        }
    }

    // leetcode 1034. https://leetcode.com/problems/coloring-a-border/

    private void dfs(int[][] grid, int x, int y, boolean[][] vis) {
        int count = 0;
        int val = grid[x][y];
        grid[x][y] *= -1;
        vis[x][y] = true;
        for(int d = 0; d < 4; d++) {
            int r = x + xdir[d];
            int c = y + ydir[d];

            if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
                if(grid[r][c] == val || grid[r][c] == -val) {
                    count++;
                }

                if(grid[r][c] == val && vis[r][c] == false) {
                    dfs(grid, r, c, vis);
                }
            }
        }
        if(count == 4) {
            grid[x][y] *= -1;
        }
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        dfs(grid, row, col, vis);
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] < 0) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }

    // minimum number of swap to make array sorted
    private static class Pair implements Comparable<Pair> {
        int val;
        int idx;
    
        Pair(int val, int idx) {
          this.val = val;
          this.idx = idx;
        }
    
        @Override
        public int compareTo(Pair o) {
          return this.val - o.val;
        }
    }

    public static int minSwaps(int[] arr1) {
        Pair[] arr = new Pair[arr1.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = new Pair(arr1[i], i);
        }

        Arrays.sort(arr);
        int ans = 0;
        boolean[] vis = new boolean[arr.length];
        for(int i = 0; i < arr.length; i++) {
            if(vis[i] == true || arr[i].idx == i) {
                continue;
            }
            int clen = 0;
            int j = i;
            while(vis[j] == false) {
                vis[j] = true;
                clen++;
                j = arr[j].idx;
            }
            ans += clen - 1;
        }
        return ans;
    }

    // eulerian path and circuit, https://practice.geeksforgeeks.org/problems/euler-circuit-and-path/1#
    public int isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj) {
        int odd = 0;
        int even = 0;

        for(int i = 0; i < V; i++) {
            if(adj.get(i).size() % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if(odd == 0) {
            return 2;
        } else if(odd == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    // leetcode 332. https://leetcode.com/problems/reconstruct-itinerary/
    public HashMap<String, PriorityQueue<String>> graph;
    public LinkedList<String> ans;
    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        ans = new LinkedList<>();

        for(List<String> edge : tickets) {
            String u = edge.get(0);
            String v = edge.get(1);

            if(graph.containsKey(u)) {
                graph.get(u).add(v);
            } else {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(v);
                graph.put(u, pq);
            }
        }

        dfs("JFK");
        return ans;
    }

    public void dfs(String src) {
        PriorityQueue<String> nbrs = graph.get(src);
        while(nbrs != null && nbrs.size() > 0) {
            String nbr = nbrs.remove();
            dfs(nbr);
        }
        ans.addFirst(src);
    }

    public static void main(String[] args) {

    }
}
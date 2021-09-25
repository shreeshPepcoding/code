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

    // lintcode 860. https://www.lintcode.com/problem/860/
    public int numberofDistinctIslands(int[][] grid) {
        
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


    public static void main(String[] args) {

    }
}
import java.util.*;

public class chefAndReverse {
    private static class Edge {
        int nbr;
        int wt;
        public Edge(int nbr, int wt) {
            this.nbr = nbr;
            this.wt = wt;
        }
    }
        
    public static ArrayList<Edge>[] graph;
    
    private static class Pair {
        int vtx;
        int csf;
        public Pair(int vtx, int csf) {
            this.vtx = vtx;
            this.csf = csf;
        }
    }
    
    private static int BFS01() {
        boolean[] vis = new boolean[graph.length];
        LinkedList<Pair> qu = new LinkedList<>();
        qu.addFirst(new Pair(0, 0));
        
        while(qu.size() > 0) {
            Pair rem = qu.removeFirst();
            vis[rem.vtx] = true;
            if(rem.vtx == vis.length - 1) {
                return rem.csf;
            }
            for(Edge e : graph[rem.vtx]) {
                if(vis[e.nbr] == false) {
                    if(e.wt == 0) {
                        qu.addFirst(new Pair(e.nbr, rem.csf + 0));
                    } else {
                        qu.addLast(new Pair(e.nbr, rem.csf + 1));
                    }
                }
            }
        }
        return -1;
    }
    
    private static int minEdgeReverse(int[][] edges, int v) {
        graph = new ArrayList[v];
        for(int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            int U = edge[0];
            int V = edge[1];
            // normal addition
            graph[U].add(new Edge(V, 0));
            // add reverse edge with cost 1
            graph[V].add(new Edge(U, 1));
        }
        int cost = BFS01();
        return cost;
    }

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int[][] edges = new int[m][2];
		for(int i = 0; i < m; i++) {
		    edges[i][0] = scn.nextInt() - 1;
		    edges[i][1] = scn.nextInt() - 1;
		}
		int res = minEdgeReverse(edges, n);
		System.out.println(res);
	}
}

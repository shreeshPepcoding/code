import java.util.*;

public class perfectFriends {

    public static void gcc(ArrayList<Integer>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for(int nbr : graph[src]) {
            if(vis[nbr] == false) {
                gcc(graph, nbr, vis, comp);
            }
        }
    }

    public static int countPair(ArrayList<Integer>[] graph) {
        // find components then find count
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];
        
        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {;
                ArrayList<Integer> comp = new ArrayList<>();
                gcc(graph, v, vis, comp);
                comps.add(comp);
            }
        }
        
        // System.out.println(comps);
        int count = 0;
        
        // time complexity O(n^2) where n is number of components
        // for(int i = 0; i < comps.size(); i++) {
        //     int s1 = comps.get(i).size();
        //     for(int j = i + 1; j < comps.size(); j++) {
        //         int s2 = comps.get(j).size();
        //         count += s1 * s2;
        //     }
        // }


        // time complexity O(n)
        int sum = 0;
        for(int i = comps.size() - 1; i >= 0; i--) {
            int sz = comps.get(i).size();
            count += sum * sz;
            sum += sz;
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = scn.nextInt();
        for(int e = 0; e < edges; e++) {
            int src = scn.nextInt();
            int nbr = scn.nextInt();

            // add edge
            graph[src].add(nbr);
            graph[nbr].add(src);
        }

        int res = countPair(graph);
        System.out.println(res);
    }
}

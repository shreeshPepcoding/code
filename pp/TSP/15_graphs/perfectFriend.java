import java.util.*;

public class perfectFriend {
    
    public static void gcc(ArrayList<Integer>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for(int nbr : graph[src]) {
            if(vis[nbr] == false)  {
                gcc(graph, nbr, vis, comp);
            }
        }
    }

    public static int perfectFr(ArrayList<Integer>[] graph) {
        int n = graph.length;
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean[] vis = new boolean[n];

        for(int v = 0; v < n; v++) {
            if(vis[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                gcc(graph, v, vis, comp);
                comps.add(comp);
            }
        }
        // find count with different approaches
        int count = 0;
        // method 1 O(N^2), where N is no. of components
        // for(int i = 0; i < comps.size(); i++) {
        //     int s1 = comps.get(i).size();
        //     for(int j = i + 1; j < comps.size(); j++) {
        //         int s2 = comps.get(j).size();
        //         count += s1 * s2;
        //     }
        // }

        // method 2, O(N)
        int sum = comps.get(comps.size() - 1).size();
        for(int i = comps.size() - 2; i >= 0; i--) {
            int s = comps.get(i).size();
            count += sum * s;
            sum += s;
        }

        return count;
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(); // no. of bvertices
        int k = scn.nextInt(); // no. of edges

        ArrayList<Integer>[] graph = new ArrayList[n];

        // provide memory reference at every location
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // add edges in graph, k times
        for(int i = 0; i < k; i++) {
            int src = scn.nextInt();
            int nbr = scn.nextInt();

            graph[src].add(nbr);
            graph[nbr].add(src);
        }

        // no. of pairs of perfect friend, i.e. count so that both are not in same club
        int count = perfectFr(graph);
        System.out.println(count);
    }    
}

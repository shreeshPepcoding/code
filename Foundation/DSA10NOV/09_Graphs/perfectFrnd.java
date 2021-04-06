import java.util.*;

public class perfectFrnd {

    public static void gcc(ArrayList<Integer>[] graph, int src, boolean[] vis, ArrayList<Integer> comp) {
        vis[src] = true;
        comp.add(src);

        for(int nbr : graph[src]) {
            if(vis[nbr] == false) {
                gcc(graph, nbr, vis, comp);
            }
        }
    }

    public static void SolveFrndPair(ArrayList<Integer>[] graph) {
        // get connected component
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean[] vis = new boolean[graph.length];

        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                gcc(graph, v, vis, comp);
                comps.add(comp);
            }
        }

        // System.out.println(comps);
        // count number of pairs
        int count = 0;

        for(int i = 0; i < comps.size() - 1; i++) {
            int isize = comps.get(i).size();
            for(int j = i + 1; j < comps.size(); j++) {
                int jsize = comps.get(j).size();

                count += isize * jsize;
            }
        }

        System.out.println(count);
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in); 
        int n = scn.nextInt();
        int k = scn.nextInt();
        
        ArrayList<Integer>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) { 
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < k; i++) {
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        SolveFrndPair(graph);        

    }    
}





import java.util.*;

public class perfectFrnd {

    public static int gcc(ArrayList<Integer>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        // comp.add(src);
        int count = 0;
        for(int nbr : graph[src]) {
            if(vis[nbr] == false) {
                count += gcc(graph, nbr, vis);
            }
        }
        return count + 1;
    }

    public static void SolveFrndPair(ArrayList<Integer>[] graph) {
        // get connected component
        // ArrayList<Integer> comps = new ArrayList<>();
        boolean[] vis = new boolean[graph.length];
        
        long psum = 0;
        long res = 0;
        
        for(int v = 0; v < graph.length; v++) {
            if(vis[v] == false) {
                int compSize = gcc(graph, v, vis);
                res += psum * compSize;
                psum += compSize;
            }   
        }
        
        System.out.println(res);   
         
        // System.out.println(comps);
        // count number of pairs
        // int count = 0;

        // for(int i = 0; i < comps.size() - 1; i++) {
        //     int isize = comps.get(i).size();
        //     for(int j = i + 1; j < comps.size(); j++) {
        //         int jsize = comps.get(j).size();

        //         count += isize * jsize;
        //     }
        // }

        // System.out.println(count);
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in); 
        int n = scn.nextInt();
        int k = scn.nextInt();
        
        ArrayList<Integer>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++) { 
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < k; i++) {
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        SolveFrndPair(graph);        

    }    
}

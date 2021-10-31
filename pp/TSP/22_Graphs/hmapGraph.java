import java.util.*;

public class hmapGraph {

    private class Edge {
        String nbr;
        double wt;

        public Edge(String nbr, double wt) {
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    
    private boolean pathCost(HashMap<String, ArrayList<Edge>> graph, String src,
         String dst, double[] res, int indx, double csf, HashSet<String> vis) {
        if(src.equals(dst) == true) {
            res[indx] = csf;
            return true;
        }
        vis.add(src);
        boolean rres = false;
        for(Edge e : graph.get(src)) {
            if(vis.contains(e.nbr) == false) {
                rres = rres || pathCost(graph, e.nbr, dst, res, indx, csf * e.wt, vis);
            }
        }
        return rres;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        int indx = 0;
        for(List<String> edge : equations) {
            String u = edge.get(0);
            String v = edge.get(1);
            double wt = values[indx];
            indx++;

            if(graph.containsKey(u)) {
                graph.get(u).add(new Edge(v, wt));
            } else {
                ArrayList<Edge> list = new ArrayList<>();
                list.add(new Edge(v, wt));
                graph.put(u, list);
            }

            if(graph.containsKey(v)) {
                graph.get(v).add(new Edge(u, 1.0 / wt));
            } else {
                ArrayList<Edge> list = new ArrayList<>();
                list.add(new Edge(u, 1.0 / wt));
                graph.put(v, list);
            }
        }

        double[] res = new double[queries.size()];

        indx = 0;
        for(List<String> query : queries) {
            String u = query.get(0);
            String v = query.get(1);
            
            if(graph.containsKey(u) == false || graph.containsKey(v) == false) {
                res[indx] = -1.0;
            } else if(u.equals(v) == true) {
                res[indx] = 1.0;
            } else {
                HashSet<String> vis = new HashSet<>();
                boolean rres = pathCost(graph, u, v, res, indx, 1.0, vis);
                if(rres == false) {
                    res[indx] = -1.0;
                }
            }
            indx++;
        }
        return res;
    }
    
    public static void main(String[] args) {
        
    }
}

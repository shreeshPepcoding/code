import java.util.*;

public class Hmap {
    
    // print number of employee under every manager    
    private static int getSize(HashMap<String, HashSet<String>> tree, 
        String man, HashMap<String, Integer> res) {
        if(tree.containsKey(man) == false) {
            res.put(man, 0);
            return 1;
        }
        int count = 0;
        for(String emp : tree.get(man)) {
            count += getSize(tree, emp, res);
        }
        res.put(man, count);
        return count + 1;
    }

    private static void printEmployeeUnderManager(String[][] relations) {
        HashMap<String, HashSet<String>> tree = new HashMap<>();
        String ceo = "";
        for(String[] relation : relations) {
            String emp = relation[0];
            String man = relation[1];
            if(emp.equals(man) == true) {
                ceo = man;
                continue;
            }
            if(tree.containsKey(man) == true) {
                tree.get(man).add(emp);
            } else {
                HashSet<String> set = new HashSet<>();
                set.add(emp);
                tree.put(man, set);
            }
        }
        HashMap<String, Integer> res = new HashMap<>();
        getSize(tree, ceo, res);
        for(String str : res.keySet()) {
            System.out.println(str + " " + res.get(str));
        }
    }

    // Find Itinerary From Tickets
    private static void printPath(HashMap<String, String> tickets) {
        // find starting point of journey
        HashMap<String, Boolean> map = new HashMap<>();
        for(String city1 : tickets.keySet()) {
            String city2 = tickets.get(city1);
            map.put(city2, false);
            if(map.containsKey(city1) == false) {
                map.put(city1, true);
            }
        }
        String src = "";
        for(String city : map.keySet()) {
            if(map.get(city) == true) {
                src = city;
                break;
            }
        }
        // find routes using starting point
        String path = "";
        while(tickets.containsKey(src) == true) {
            path += src + " -> ";
            src = tickets.get(src);
        }
        path += src + ".";
        System.out.println(path);
    }

    // Count Distinct Elements In Every Window Of Size K
    public static ArrayList<Integer> countDistinct(int[] arr, int k) {
        HashMap<Integer, Integer> fmap = new HashMap<>();
        for(int i = 0; i < k - 1; i++) {
            int fq = fmap.getOrDefault(arr[i], 0);
            fmap.put(arr[i], fq + 1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        int j = 0;
        for(int i = k - 1; i < arr.length; i++, j++) {
            // add impact of ith index
            int fq = fmap.getOrDefault(arr[i], 0);
            fmap.put(arr[i], fq + 1);
            // make result
            res.add(fmap.size());
            // remove impact of j
            if(fmap.get(arr[j]) == 1) {
                fmap.remove(arr[j]);
            } else {
                fmap.put(arr[j], fmap.get(arr[j]) - 1);
            }
        }
        return res;
    }

    // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
    public static void isPairingPossible(int[] arr, int k){
        HashMap<Integer, Integer> fmap = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            int fq = fmap.getOrDefault(arr[i] % k, 0);
            fmap.put(arr[i] % k, fq + 1);
        }

        boolean res = true;
        for(int rem1 : fmap.keySet()) {
            if(rem1 == 0 || (k % 2 == 0 && rem1 == k / 2)) {
                if(fmap.get(rem1) % 2 != 0) {
                    res = false;
                    break;
                }
            } else {
                int rem2 = k - rem1;
                if(fmap.containsKey(rem2) == false || fmap.get(rem1) != fmap.get(rem2)) {
                    res = false;
                    break;
                }
            }
        }
        System.out.println(res);
	}

    // leetcode 1497, https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
    

    public static void main(String[] args) {
        
    }
}

import java.util.*;

public class Hmap {

    // Number of Employees and Every Manager
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

    private static void printEmployee(String[][] relations) {
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

        // travel on tree and get number of employees under every manager
        HashMap<String, Integer> res = new HashMap<>();
        getSize(tree, ceo, res);
        // travel and print result
        for(String str : res.keySet()) {
            System.out.println(str + " " + res.get(str));
        }
    }

    // Find Itinerary From Tickets
    private static void printPath(HashMap<String, String> tickets) {
        // find starting source
        HashMap<String, Boolean> map = new HashMap<>();
        for(String city1 : tickets.keySet()) {
            String city2 = tickets.get(city1);
            // route city1->city2
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
        // find path from starting source using tickets
        String res = "";
        while(tickets.containsKey(src) == true) {
            res += src + " -> ";
            src = tickets.get(src);
        }
        res += src;
        System.out.println(res + ".");
    }

    // Count Distinct Elements In Every Window Of Size K
    public static ArrayList<Integer> solution(int[] arr, int k) {
        HashMap<Integer, Integer> fmap = new HashMap<>();
        for(int i = 0; i < k - 1; i++) {
            int fq = fmap.getOrDefault(arr[i], 0);
            fmap.put(arr[i], fq + 1);
        }
        ArrayList<Integer> res = new ArrayList<>();
        int j = 0;
        for(int i = k - 1; i < arr.length; i++, j++) {
            int fq = fmap.getOrDefault(arr[i], 0);
            fmap.put(arr[i], fq + 1);
            res.add(fmap.size());

            if(fmap.get(arr[j]) == 1) {
                fmap.remove(arr[j]);
            } else {
                fmap.put(arr[j], fmap.get(arr[j]) - 1);
            }
        }
        return res;
	}

    // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
    public static void isPairPossible(int[] arr, int k){
        HashMap<Integer, Integer> fmap = new HashMap<>();

        for(int i = 0; i < arr.length; i++) {
            int rem = arr[i] % k;
            int fq = fmap.getOrDefault(rem, 0);
            fmap.put(rem, fq + 1);
        }

        boolean res = true;
        for(int fx : fmap.keySet()) {
            if(fx == 0 || (k % 2 == 0 && fx == k / 2)) {
                if(fmap.get(fx) % 2 != 0) {
                    res = false;
                    break;
                }
            } else {
                if(fmap.containsKey(k - fx) == false || fmap.get(fx) != fmap.get(k - fx)) {
                    res = false;
                    break;
                }
            }
        }
        System.out.println(res);
	}

    // Largest Subarray With Zero Sum
    public static int largestSubArrayWith0Sum(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        int len = 0;
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum) == false) {
                map.put(sum, i);
            } else {
                len = Math.max(len, i - map.get(sum));
            }
        }
        return len;
	}

    public static void main(String[] args) {
        
    }
}
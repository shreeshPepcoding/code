import java.util.*;

public class hmap {

    private static Scanner scn = new Scanner(System.in);

    // number of employee under every manager, portal
    private static int printCountOfEmployee(HashMap<String, HashSet<String>> tree, String root) {
        if(tree.get(root) == null) {
            System.out.println(root + " 0");
            return 1;
        }
        int count = 0;
        for(String child : tree.get(root)) {
            count += printCountOfEmployee(tree, child);
        }
        System.out.println(root + " " + count);
        return count + 1;
    }

    public static void numberOfEmployee() {
        int n = scn.nextInt();
        HashMap<String, HashSet<String>> tree = new HashMap<>();
        String root = "";
        for(int i = 0; i < n; i++) {
            String emp = scn.next();
            String man = scn.next();
            if(emp.equals(man) == true) {
                root = emp;
                continue;
            }
            if(tree.containsKey(man) == true) {
                tree.get(man).add(emp);
            } else {
                HashSet<String> list = new HashSet<>();
                list.add(emp);
                tree.put(man, list);
            }
        }
        printCountOfEmployee(tree, root);
    }

    // find itinerary from tickets
    public static void printIntinerary(HashMap<String, String> connections) {
        HashMap<String, Boolean> begin = new HashMap<>();
        for(String start : connections.keySet()) {
            String end = connections.get(start);
            begin.put(end, false); // update or insert
            if(begin.containsKey(start) == false) {
                begin.put(start, true);
            }
        }
        String src = "";
        for(String key : begin.keySet()) {
            if(begin.get(key) == true) {
                src = key;
                break;
            }
        }

        System.out.print(src);
        while(connections.containsKey(src)) {
            src = connections.get(src);
            System.out.print(" -> " + src);
        }
        System.out.println(".");
    }

    // count distinct element, portal
    public static ArrayList<Integer> solution(int[] arr, int k) {
        HashMap<Integer, Integer> fmap = new HashMap<>();
        // add element from 0 to k-1
        for(int i = 0; i < k - 1; i++) {
            int ofq = fmap.getOrDefault(arr[i], 0);
            fmap.put(arr[i], ofq + 1);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = k - 1; i < arr.length; i++) {
            // add current element
            int ofq = fmap.getOrDefault(arr[i], 0);
            fmap.put(arr[i], ofq + 1);
            // add size of fmap in result
            res.add(fmap.size());
            // reduce freq of first element of window, if freq is 0 then remove it from window
            int j = i - k + 1;
            ofq = fmap.get(arr[j]);
            if(ofq == 1) {
                fmap.remove(arr[j]);
            } else {
                fmap.put(arr[j], ofq - 1);
            }
        }
        return res;
	}

    // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K, portal
    public static void solution(int[] arr, int k){
		HashMap<Integer, Integer> map = new HashMap<>();
        // prepare freq map of remainders
        for(int val : arr) {
            int rem = val % k;
            int ofq = map.getOrDefault(rem, 0);

            map.put(rem, ofq + 1);
        }

        for(int rem : map.keySet()) {
            if(rem == 0) {
                if(map.get(rem) % 2 == 1) {
                    System.out.println(false);
                    return;
                }
            } else if(rem * 2 == k) {
                if(map.get(rem) % 2 == 1) {
                    System.out.println(false);
                    return;
                }
            } else {
                int rem2 = k - rem;
                if(map.get(rem2) != map.get(rem)) {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
	}
    
    // largest subarray with 0 sum
    public static int solution(int[] arr) {
		int psum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        map.put(0, -1);
        for(int i = 0; i < arr.length; i++) {
            int val = arr[i];
            psum += val;
            if(map.containsKey(psum) == false) {
                map.put(psum, i);
            } else {
                len = Math.max(len, i - map.get(psum));
            }
        }
		return len;
	}

    // Number of Subarray having sum is equal to 0
    public static int countSubArray1(int[] arr) {
		int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int psum = 0;
        for(int i = 0; i < arr.length; i++) {
            int val = arr[i];
            psum += val;
            if(map.containsKey(psum) == false) {
                map.put(psum, 1);
            } else {
                int ofq = map.get(psum);
                count += ofq;
                map.put(psum, ofq + 1);
            }
        }
		return count;
	}

    public static int longest01SubArray(int[] arr) {
        // step 1 : change 0 to -1
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                arr[i] = -1;
            }
        }
        // step 2 : solve for longest subarray having sum equal to 0
        return solution(arr);
    }

    public static int count01SubArray(int[] arr) {
        // step 1 : change 0 to -1
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                arr[i] = -1;
            }
        }
        // step 2 : solve for count subarray having sum equal to 0
        return countSubArray1(arr);
    }

    // longest length of subarray having sum equal to K
    public static int maxLenSubarray(int[] nums, int k) {
        int psum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            psum += val;
            if(map.containsKey(psum - k) == true) {
                len = Math.max(len, i - map.get(psum - k));
            }

            if(map.containsKey(psum) == false) {
                map.put(psum, i);
            }
        }
        return len;
    }

    // count subarray having sum is equal to K
    public static int countSumK(int[] nums, int k){
		int psum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            psum += val;
            if(map.containsKey(psum - k) == true) {
                count += map.get(psum - k);
            }

            if(map.containsKey(psum) == false) {
                map.put(psum, 1);
            } else {
                map.put(psum, map.get(psum) + 1);
            }
        }
        return count;
	}
	


    public static void main(String[] args) {

    }
}
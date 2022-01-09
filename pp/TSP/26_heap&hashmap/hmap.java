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
    public static ArrayList<Integer> solution1(int[] arr, int k) {
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
    public static void solution2(int[] arr, int k){
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
	
    // longest subarray having sum divisible by k
    public static int longestSumDivisibleByK(int[] arr, int k) {
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for(int i = 0; i < arr.length; i++) {
            int val = arr[i];
            sum += val;
            int rem = sum % k;
            if(rem < 0) rem += k;
            if(map.containsKey(rem) == false) {
                map.put(rem, i);
            } else {
                len = Math.max(len, i - map.get(rem));
            }
        }
        return len;
    }  

    // count of subarray with sum divisible by k
    public static int CountSumDivisibleByK(int[] arr, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 1);
        for(int i = 0; i < arr.length; i++) {
            int val = arr[i];
            sum += val;
            int rem = sum % k;
            if(rem < 0) rem += k;
            if(map.containsKey(rem) == false) {
                map.put(rem, 1);
            } else {
                count += map.get(rem);
                map.put(rem, map.get(rem) + 1);
            }
        }
        return count;
    }

    // longest subarray with equal number of 0, 1 and 2
    public static int longestSubarray012(int[] arr) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int len = 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("0#0", -1);
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                count0++;
            } else if(arr[i] == 1) {
                count1++;
            } else {
                count2++;
            }
            String key = (count1 - count0) + "#" + (count2 - count1);
            if(map.containsKey(key) == false) {
                map.put(key, i);
            } else {
                len = Math.max(len, i - map.get(key));
            } 
        }
        return len;
    }

    // count of subarrays having equal number of 0, 1 and 2
    public static int countSubArray012(int[] arr) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int count = 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("0#0", 1);
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                count0++;
            } else if(arr[i] == 1) {
                count1++;
            } else {
                count2++;
            }
            String key = (count1 - count0) + "#" + (count2 - count1);
            if(map.containsKey(key) == false) {
                map.put(key, 1);
            } else {
                count +=map.get(key);
                map.put(key, map.get(key) + 1);
            } 
        }
        return count;
    }

    // complete task
    public static void completeTask(int n, int m, int[] arr) {
        ArrayList<Integer> s1 = new ArrayList<>();
        ArrayList<Integer> s2 = new ArrayList<>();
        boolean flag = true;

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < m; i++) {
            set.add(arr[i]);
        }

        for(int i = 1; i <= n; i++) {
            if(set.contains(i) == true) continue;

            if(flag == true) {
                s1.add(i);
                flag = false;
            } else {
                s2.add(i);
                flag = true;
            }
        }

        for(int val : s1) {
            System.out.print(val + " ");
        }
        System.out.println();
        for(int val : s2) {
            System.out.print(val + " ");
        }
	}

    // smallest substring of string containing all characters of another string
    public static String smallestSubStringAllChar(String s1, String s2){
        // frequency map for string 2
        HashMap<Character, Integer> fmap = new HashMap<>();
        for(int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
        }
        
        // move toward solution
        int acq = -1; // acquire
        int rel = -1; // release
        int count = 0;
        int requirement = s2.length();
        HashMap<Character, Integer> map = new HashMap<>();
        String ans = "";
        while(true) {
            boolean acFlag = false; // acquire flag
            boolean reFlag = false; // release flag
            // acquire
            while(acq < s1.length() - 1 && count < requirement) {
                acq++;
                char ch = s1.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                // conditional increment in count
                if(map.get(ch) <= fmap.getOrDefault(ch, 0)) {
                    count++;
                }
                acFlag = true;
            }
        
            // release
            while(count == requirement) {
                // hold ans, and if smallest then update the result
                String tempAns = s1.substring(rel + 1, acq + 1);
                if(ans.length() == 0 || tempAns.length() < ans.length()) {
                    ans = tempAns;
                }
                // get the character and remove from map(either dec. freq. or remove key from map)
                rel++;
                char ch = s1.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) map.remove(ch);

                // decrement in count if release is invalid
                if(map.getOrDefault(ch, 0) < fmap.getOrDefault(ch, 0)) {
                    count--;
                }
                reFlag = true;    
            }
            
            // conditional break from loop
            if(acFlag == false && reFlag == false) {
                break;
            }
        }
        return ans;
	}

    // smallest substring of a string containing all unique character of itself
    private static int smallestSubStringAllUniqueChar(String str) {
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        int acq = -1;
        int rel = -1;
        int ansLen = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean acflag = false;
            boolean reflag = false;
            // acquire
            while(acq < str.length() - 1 && map.size() < set.size()) {
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                acflag = true;
            }
            // release
            while(map.size() == set.size()) {
                int tempLen = acq - rel;
                if(tempLen < ansLen) {
                    ansLen = tempLen;
                }

                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) map.remove(ch);

                reflag = true;
            }

            if(acflag == false && reflag == false) break;
        }
        return ansLen;
    }

    // longest substring with non repeating character
    public static int longestSubStringNonRepeating(String str) {
        int ansLen = 0;
        int acq = -1;
        int rel = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean acflag = false;
            boolean reflag = false;

            // acquire
            while(acq < str.length() - 1) {
                acflag = true;
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if(map.get(ch) == 2) {
                    // stop acq.
                    break;
                } else {
                    int tempLen = acq - rel;
                    if(tempLen > ansLen) ansLen = tempLen;
                }
            }
            // release
            while(rel < acq) {
                reflag = true;
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 1) {
                    // after reducing, if freq is 1, that means this ch charcacter is responsible for repition
                    break;
                }
            }

            if(acflag == false && reflag == false) break;
        }
        return ansLen;
	}

    // count substrings with non repeating character
    private static int countSubStringsNonRepeating(String str) {
        int count = 0;
        int acq = -1;
        int rel = -1;

        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean acflag = false;
            boolean reflag = false;

            while(acq < str.length() - 1) {
                acflag = true;
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if(map.get(ch) == 2) {
                    // int n = acq - rel - 1;
                    // count += n * (n + 1) / 2;
                    break;
                } else {
                    count += acq - rel;
                }
            }


            while(rel < acq) {
                reflag = true;
                rel++;
                char ch = str.charAt(rel);

                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 1) {
                    count += acq - rel;
                    break;
                }
            }

            if(acflag == false && reflag == false) break;
        }
        return count;
    }

    private static int longestKUniqueCharacter(String str, int k) {
        int ans = -1;

        int acq = -1;
        int rel = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean acflag = false;
            boolean reflag = false;

            while(acq < str.length() - 1) {
                acflag = true;
                acq++;
                char ch = str.charAt(acq);

                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if(map.size() < k) {
                    continue;
                } else if(map.size() == k) {
                    ans = Math.max(ans, acq - rel);
                } else {
                    break;
                }
            }

            while(rel < acq) {
                reflag = true;
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if(map.get(ch) == 0) {
                    map.remove(ch);
                }

                if(map.size() > k) {
                    continue;
                } else if(map.size() == k) {
                    break;
                }
            }

            if(acflag == false && reflag == false) break;
        }
        return ans;
    }

    private static int handleWhenK1(String str) {
        int acq = -1;
        int rel = -1;
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean flag1 = false;
            boolean flag2 = false;

            while(acq < str.length() - 1) {
                flag1 = true;
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if(map.size() == 2) {
                    map.remove(ch);
                    acq--;
                    break;
                }
            }

            while(rel < acq) {
                count += acq - rel;
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) map.remove(ch);

                if(map.size() == 0) break;
            }

            if(flag1 == false && flag2 == false) {
                break;
            }
        }

        return count;
    }

    private static int countSubStringExactlyK(String str, int k) {
        if(k == 1) {
            return handleWhenK1(str);
        }
        HashMap<Character, Integer> mapb = new HashMap<>();
        HashMap<Character, Integer> maps = new HashMap<>();

        int count = 0;

        int is = -1;
        int ib = -1;
        int j = -1;

        while(true) {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            // k - 1 for map small
            while(is < str.length() - 1) {
                flag1 = true;
                is++;
                char ch = str.charAt(is);
                maps.put(ch, maps.getOrDefault(ch, 0) + 1);
                if(maps.size() == k) {
                    maps.remove(ch);
                    is--;
                    break;
                }
            }
            // k for map big
            while(ib < str.length() - 1) {
                flag2 = true;
                ib++;
                char ch = str.charAt(ib);
                mapb.put(ch, mapb.getOrDefault(ch, 0) + 1);
                if(mapb.size() == k + 1) {
                    mapb.remove(ch);
                    ib--;
                    break;
                }
            }
            // release
            while(j < is) {
                flag3 = true;
                if(mapb.size() == k && maps.size() == k - 1) {
                    count += ib - is;
                }
                j++;
                char ch = str.charAt(j);
                // remove from maps
                maps.put(ch, maps.get(ch) - 1);
                if(maps.get(ch) == 0) maps.remove(ch);
                // remove from mapb
                mapb.put(ch, mapb.get(ch) - 1);
                if(mapb.get(ch) == 0) mapb.remove(ch);

                if(maps.size() < k - 1|| mapb.size() < k) {
                    break;
                }
            }
            if(flag1 == false && flag2 == false  && flag3 == false) break;
        }
        return count;
    }

    // maximum consecutive one - I
    public static int maxConsecutive1sI(int[] arr){
        int ans = 0;
        int j = -1;
        int count0 = 0;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) count0++;

            while(count0 > 1) {
                // release
                j++;
                if(arr[j] == 0) count0--;
            }
            // count 0 must be less than equal to 1
            ans = Math.max(ans, i - j);
        }
        return ans;
    }

    // maximum consecutive one - II
    public static int maxConsecutive1sII(int[] arr, int k){
        int ans = 0;
        int j = -1;
        int count0 = 0;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) count0++;

            while(count0 > k) {
                // release
                j++;
                if(arr[j] == 0) count0--;
            }
            // count 0 must be less than equal to 1
            ans = Math.max(ans, i - j);
        }
        return ans;
    }

    // longest subarray with contiguous elements
    private static int longestSubArrayContiguous(int[] arr) {
        int len = 0;
        for(int i = 0; i < arr.length; i++) {
            int max = arr[i];
            int min = arr[i];
            HashSet<Integer> set = new HashSet<>();
            for(int j = i; j < arr.length; j++) {
                if(set.contains(arr[j]) == true) {
                    break;
                }
                set.add(arr[j]);

                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                if(max - min == j - i) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    // longest substring with at most k unique characters
    private static int longestSubStringAtMostK(String str, int k) {
        int len = 0;
        int acq = -1;
        int rel = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean flag1 = false;
            boolean flag2 = false;
            while(acq < str.length() - 1) {
                flag1 = true;
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if(map.size() <= k) {
                    len = Math.max(len, acq - rel);
                } else {
                    break;
                }
            }
            while(rel < acq) {
                flag2 = true;
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) map.remove(ch);
                if(map.size() == k) break;
            }
            if(flag1 == false && flag2 == false) break;
        }
        return len;
    }

    // count of substring with at most k unique characters
    private static int countOfSubStringAtMostK(String str, int k) {
        int count = 0;
        int acq = -1;
        int rel = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        while(true) {
            boolean flag1 = false;
            boolean flag2 = false;
            while(acq < str.length() - 1) {
                flag1 = true;
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if(map.size() <= k) {
                    count += acq - rel;
                } else {
                    break;
                }
            }
            if(acq == str.length() - 1 && map.size() <= k) {
                break;
            }
            while(rel < acq) {
                flag2 = true;
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) map.remove(ch);
                if(map.size() == k) {
                    count += acq - rel;
                    break;
                }
            }
            if(flag1 == false && flag2 == false) break;
        }
        return count;
    }

    // valid anagram
    private static boolean validAnagram(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if(map.containsKey(ch) == false) return false;
            map.put(ch, map.get(ch) - 1);
            if(map.get(ch) == 0) map.remove(ch);
        }
        return map.size() == 0;
    }

    // find mapping of anagrams
    public static int[] anagramMappings(int[] arr1, int[] arr2) {
		HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr2.length; i++) {
            int val = arr2[i];
            if(map.containsKey(val) == true) {
                map.get(val).addLast(i);
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(val, list);
            }
        }
        int[] res = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++) {
            int val = arr1[i];
            int indx = map.get(val).removeFirst();
            res[i] = indx;
        }
		return res;
	}

    // find all anagrams in a string
    private static boolean match(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        for(Character key : map1.keySet()) {
            int val1 = map1.getOrDefault(key, -1);
            int val2 = map2.getOrDefault(key, -1);
            if(val1 != val2) return false;
        }
        return true;
    }

    public static void findAnagrams(String s, String p) {
        HashMap<Character, Integer> pmap = new HashMap<>(); // pattern map
        for(int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pmap.put(ch, pmap.getOrDefault(ch, 0) + 1);
        }
        
        HashMap<Character, Integer> smap = new HashMap<>(); // source map
        // acquire first window of length =  p.length
        for(int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = p.length(); i < s.length(); i++) {
            // match
            if(match(smap, pmap) == true) {
                res.add(i - p.length());
            }

            // we can use equals function too
            // if(smap.equals(pmap) == true) {
            // }

            // acquire
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
            // release
            char relCh = s.charAt(i - p.length()); // relCh - > releasing character
            smap.put(relCh, smap.get(relCh) - 1);
            if(smap.get(relCh) == 0) smap.remove(relCh);
        }
        if(match(smap, pmap) == true) {
            res.add(s.length() - 1 - p.length());
        }
        System.out.println(res.size());
        for(int val : res) System.out.print(val + " ");
	}
    
    public static void main(String[] args) {
        
    }

}
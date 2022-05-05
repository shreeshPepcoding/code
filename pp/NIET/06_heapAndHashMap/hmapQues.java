import java.util.*;

public class hmapQues {

    public static void printMaxFreqCharacter(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char res = 'a';
        int maxfreq = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int oldFreq = map.getOrDefault(ch, 0);
            map.put(ch, oldFreq + 1);

            if(oldFreq + 1 > maxfreq) {
                maxfreq = oldFreq + 1;
                res = ch;
            }
        }
        System.out.println(res);
    }

    public static void printCommonElement1(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        for(int val : arr1) {
            set.add(val);
        }

        for(int val : arr2) {
            if(set.contains(val) == true) {
                // print
                System.out.println(val);
                // remove from set
                set.remove(val);
            }
        }
    }

    public static void printCommonElement2(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int ele : arr1) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        for(int ele : arr2) {
            if(map.containsKey(ele) == true && map.get(ele) > 0) {
                // print
                System.out.println(ele);
                // decrease freq
                map.put(ele, map.get(ele) - 1);
            }
        }
    }

    // leetcode 1002, https://leetcode.com/problems/find-common-characters/
    public List<String> commonChars(String[] words) {
        HashMap<Character, Integer> cmap = new HashMap<>();
        // cmap => common map
        for(int i = 0; i < words[0].length(); i++) {
            char ch = words[0].charAt(i);
            cmap.put(ch, cmap.getOrDefault(ch, 0) + 1);
        }
        for(int i = 1; i < words.length; i++) {
            String word = words[i];
            // create freq map of current word
            HashMap<Character, Integer> map = new HashMap<>();
            for(int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
            // extract common map
            HashMap<Character, Integer> map2 = new HashMap<>();
            for(char key : map.keySet()) {
                if(cmap.containsKey(key) == true) {
                    map2.put(key, Math.min(map.get(key), cmap.get(key)));
                }
            }
            cmap = map2;
        }
        List<String> res = new ArrayList<>();
        for(char key : cmap.keySet()) {
            int freq = cmap.get(key);
            for(int i = 0; i < freq; i++) {
                res.add("" + key);
            }
        }
        return res;
    }

    public static void longestConsecutiveSeq(int[] arr) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        // add all element in map with false key
        for(int ele : arr) {
            map.put(ele, false);
        }

        // mark starting point in map as true
        for(int key : map.keySet()) {
            if(map.containsKey(key - 1) == false) {
                map.put(key, true);
            }
        }

        int len = 0;
        int start = 0;

        for(int key : map.keySet()) {
            if(map.get(key) == true) {
                // key is starting point of seq
                int sz = 1;
                int i = 1;
                while(map.containsKey(key + i) == true) {
                    sz++;
                    i++;
                }
                if(sz > len) {
                    len = sz;
                    start = key;
                }
            }
        }

        for(int i = 0; i < len; i++) {
            System.out.println(start);
            start++;
        }
    }

    public static void ques() {

    }

    public static void demo() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 10000);
        map.put("Pakistan", 100);
        map.put("UK", 1000);
        map.put("USA", 1500);
        map.put("China", 1700);
        map.put("SriLanka", 5000);

        System.out.println(map);
        map.put("India", 2700);
        System.out.println(map.size());

        System.out.println("India check : " + map.containsKey("India"));
        System.out.println("London check : " + map.containsKey("London"));

        System.out.println("India get : " + map.get("India"));
        System.out.println("London get : " + map.get("London"));

        System.out.println(map.keySet());

        for(String key : map.keySet()) {
            System.out.println("Key : " + key + ", value : " + map.get(key));
        }


        // demo of hashset

        HashSet<String> set = new HashSet<>();
        set.add("India");
        set.add("Australia");
        set.add("Germany");
        set.add("China");
        System.out.println(set);
        System.out.println("China find : " + set.contains("China"));
        set.remove("China");
        System.out.println(set);
        System.out.println("China find : " + set.contains("China"));

    }

    public static void main(String[] args) {
        demo();
    }
}
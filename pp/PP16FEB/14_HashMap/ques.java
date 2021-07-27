import java.util.*;

public class ques {

    public static void printHighestFreqChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(map.containsKey(ch) == true) {
                // update
                map.put(ch, map.get(ch) + 1);
            } else {
                // insertion
                map.put(ch, 1);
            }
        }

        int maxFreq = 0;
        char ch = '1';
        for(char key : map.keySet()) {
            if(map.get(key) > maxFreq) {
                maxFreq = map.get(key);
                ch = key;
            }
        }
        System.out.println(ch);
    }

    public static void printCommonElement1(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // generate ,ap from array1
        for(int ele : arr1) {
            map.put(ele, 1);
        }
        // print common 1 from array2
        for(int ele : arr2) {
            if(map.containsKey(ele) == true) {
                System.out.println(ele);
                map.remove(ele);
            }
        }
    }

    public static void printLongestConsecutiveSeq(int[] arr) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        // 1. fill all the element in hashmap with true as value
        for(int ele : arr) {
            map.put(ele, true);
        }

        // 2. make starting point visible
        for(int ele : arr) {
            if(map.containsKey(ele - 1) == true) {
                map.put(ele, false);
            }
        }

        // 3. find longest consecutive seq
        int len = 0;
        int sp = 0;

        for(int ele : arr) {
            if(map.get(ele) == true) {
                int stp = ele; // stp -> starting point
                int count = 1;

                while(map.containsKey(ele + count) == true) {
                    count++;
                }

                if(count > len) {
                    sp = ele;
                    len = count;
                }
            }
        }

        // 4. print consecutive seq
        for(int i = 1; i <= len; i++) {
            System.out.println(sp);
            sp++;
        }
    }

    public static void printCommonElement2(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // generate freq map from array1
        for(int ele : arr1) {
            if(map.containsKey(ele) == true) {
                map.put(ele, map.get(ele) + 1);
            } else {
                map.put(ele, 1);
            }
        }
        // print common 1 from array2
        for(int ele : arr2) {
            if(map.containsKey(ele) == true) {
                if(map.get(ele) > 0) {
                    System.out.println(ele);
                    map.put(ele, map.get(ele) - 1);
                }
            }
        }
    }



    public static void fun() {
        String str = "aabdbcjdhjkshfjshfjkshfjbghaadajuakhahajljakljaaaojaoaaa";
        printHighestFreqChar(str);
    }

    public static void demo() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 150);
        map.put("pak", 110);
        map.put("UK", 120);
        map.put("Uganda", 70);
        map.put("Nigeria", 80);
        map.put("England", 105);
        
        System.out.println(map);

        map.put("India", 160);
        map.put("England", 110);
        System.out.println(map);

        System.out.println(map.get("India"));
        System.out.println(map.get("Australia"));

        System.out.println(map.remove("pak"));
        System.out.println(map);
        System.out.println(map.remove("England"));
        System.out.println(map);

        System.out.println(map.containsKey("India"));
        System.out.println(map.containsKey("pak"));

        System.out.println(map.keySet());
    }


    public static void main(String[] args) {
        // demo();
        fun();
    }
}

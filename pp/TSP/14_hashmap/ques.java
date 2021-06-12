import java.util.*;

public class ques {

    public static void printHighestFreqChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        // maintain freq map
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(map.containsKey(ch) == true) {
                // increase frequency
                map.put(ch, map.get(ch) + 1);
            } else {
                // first time occurrence
                map.put(ch, 1);
            }
        }

        // find max freq character
        char ch = 'a';
        int maxFreq = 0;
        for(char key : map.keySet()) {
            if(map.get(key) > maxFreq) {
                maxFreq = map.get(key);
                ch = key;
            }
        }

        System.out.println(ch + " " + maxFreq);
    }

    public static void printCommonElement1(int[] arr1, int[] arr2) {
        // 1. prepare map for array 1 elements to figure out presence
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int key : arr1) {
            map.put(key, 1);
        }

        // 2. travel in arrays2 and check if it is present in array 1 or not
        for(int key : arr2) {
            if(map.containsKey(key) == true) {
                System.out.println(key);
                map.remove(key);
            }
        }
    }

    public static void printCommonElement2(int[] arr1, int[] arr2) {
        // 1. preprae freq map
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int key : arr1) {
            if(map.containsKey(key) == true) {
                // increase freq
                map.put(key, map.get(key) + 1);
            } else {
                // add new jey with freq 1
                map.put(key, 1);
            }
        }
        // 2. map element of arr 2 from freq map
        for(int key : arr2) {
            if(map.containsKey(key) == true && map.get(key) > 0) {
                System.out.println(key);
                map.put(key, map.get(key) - 1);
            }
        }
    }

    public static void printLongestConsecutiveSequence(int[] arr) {
        // 1. make a hashmap of presence
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int key : arr) {
            map.put(key, true);
        }

        // 2. make starting point of sequence
        for(int key : arr) {
            if(map.containsKey(key - 1) == true) {
                map.put(key, false);
            }
        }

        // 3. get length and starting point of sequnec
        int maxLength = 0;
        int starting  = 0;

        for(int key : arr) {
            if(map.get(key) == true) {
                // key is starting point
                int len = 1;
                int st = key;

                while(map.containsKey(key + 1) == true) {
                    len++;
                    key++;
                }

                if(maxLength < len) {
                    maxLength = len;
                    starting = st;
                }
                
                map.put(st, false);
            }
        }

        // 4. print answer
        for(int i = 0; i < maxLength; i++) {
            System.out.println(starting);
            starting++;
        }
    }

    public static void ques() {
        int[] arr = {1, 10, 3, 12, 4, 23, 22, 2, 11, 21, 5, 24, 25};
        printLongestConsecutiveSequence(arr);

        // String str = "abcdsacfagfhgsajhahsjaaajfajfgjhajhahaahjag";
        // printHighestFreqChar(str);
    }


    public static class Pair {
        int val1 = 0;
        int val2 = 0;
    }

    public static void demo() {
        
        //1. Put function in hashmap
        // HashMap<LinkedList<Integer>, Integer> map = new HashMap<>();
        // // frequency map
        // LinkedList<Integer> list1 = new LinkedList<>();
        // list1.add(10);
        // list1.add(20);
        // System.out.println(list1.hashCode());
        // LinkedList<Integer> list2 = new LinkedList<>();
        // list2.add(10);
        // list2.add(20);
        // list2.add(30);
        // System.out.println(list2.hashCode());
        // map.put(list1, 20);
        // map.put(list2, 30);
        // map.put('a', 1);
        // map.put('b', 1);
        // map.put('c', 1);
        // map.put('d', 1);
        // System.out.println(map);
        // map.put('a', 5);

        // Pair p1 = new Pair();
        // p1.val1 = 10;
        // p1.val2 = 20;


        // Pair p2 = new Pair();
        // p2.val1 = 10;
        // p2.val2 = 20;

        // HashMap<Pair, Integer> mp = new HashMap<>();
        // mp.put(p1, 10);
        // mp.put(p2, 20);
        // System.out.println(mp);

        // System.out.println(p1);
        // System.out.println(p2);

        // 2. get in hashmap

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('a', 10);

        System.out.println(map);
        System.out.println(map.get('a'));
        System.out.println(map.get('c'));
        System.out.println(map.get('n'));

        // 3. contains key to ensure presence of key
        System.out.println(map.containsKey('a'));
        System.out.println(map.containsKey('n'));

        // 4. to get all keys and iteratre on hashmap
        Set<Character> st = map.keySet();
        for(Character key : st) {
            System.out.println(key + " " + map.get(key));
        }

        // 5. removal in hashmap
        System.out.println(map.remove('a'));
        System.out.println(map.remove('n'));
    }

    public static void main(String[] args) {
        // demo();
        ques();
    }
}
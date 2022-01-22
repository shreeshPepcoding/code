import java.util.*;

public class bits {

    // basic of bits
    private static void basicBit(int n, int i, int j, int k, int m) {
        // 1. on ith bit
        int bm1 = (1 << i);
        System.out.println(n | bm1);
        // 2. off jth bit
        int bm2 = (1 << j);
        bm2 = (~bm2); // 1's compliment
        System.out.println(n & bm2);
        // 3. toggle kth bit
        int bm3 = (1 << k);
        System.out.println(n ^ bm3);
        // 4. check if mth bit is on or off
        int bm4 = (i << m);
        System.out.println((bm4 & n) == bm4);
    }

    // print rightmost set bit mask
    private static void printRSBM(int n) {
        int rsbm = (n & (-n));
        System.out.println(Integer.toBinaryString(rsbm));
    }

    // kernighan's algorithm
    private static int countSetBit(int n) {
        int count = 0;
        while(n != 0) {
            int rsbm = (n & (-n));
            n = n - rsbm;
            count++;
        }
        return count;
    }

    // josephus from bit mathematics
    public static int josephusBit(int n){
        // find 2^x
        int i = 1;
        while(i * 2 <= n) {
            i = i * 2;
        }

        // we know that n = 2^x + l, l = n - 2^x
        int l = n - i; // i is 2^ x
        // result is 2 * l + 1
        return 2 * l + 1;
    }

    // gray code
    public static List<Integer> grayCode(int n) {
        if(n <= 1) {
            List<Integer> bres = new ArrayList<>();
            bres.add(0);
            if(n == 1)
                bres.add(1);
            return bres;
        }

        List<Integer> rres = grayCode(n - 1);
        List<Integer> mres = new ArrayList<>();
        // add normal sequence of rres in mres
        for(int val : rres) 
            mres.add(val);

        // on (n - 1)th bit and add it in mres (make loop iteration in reverse order)
        int bm = (1 << (n - 1));
        for(int i = rres.size() - 1; i >= 0; i--) {
            int val = rres.get(i);
            mres.add(val | bm);
        }
        return mres;
    }

    // leetcode 1125
    private ArrayList<Integer> ans; // for min selection

    private void smallestSufficientTeam_rec(int devIndx, int[] devMasks, 
        int skillGain, ArrayList<Integer> selectedDevs, int skillCount) {
        // check if all skills are gained
        if(skillGain == ((1 << skillCount) - 1)) {
            // all skills are gained
            // make ans, if answer is already there then skill developers count
            if(ans.size() == 0 || ans.size() > selectedDevs.size()) {
                ans = new ArrayList<>(selectedDevs);
            }
            return;
        }
        // base case
        if(devIndx == devMasks.length) {
            return;
        }
        // no call
        smallestSufficientTeam_rec(devIndx + 1, devMasks, skillGain, selectedDevs, skillCount);
        // yes call
        if(skillGain == devMasks[devIndx]) return;
        skillGain = (skillGain | devMasks[devIndx]);
        selectedDevs.add(devIndx);
        smallestSufficientTeam_rec(devIndx + 1, devMasks, skillGain, selectedDevs, skillCount);
        selectedDevs.remove(selectedDevs.size() - 1);
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        // prepare skill to bit mask
        HashMap<String, Integer> skillToBitMap = new HashMap<>();
        for(int i = 0; i < req_skills.length; i++) {
            String skill = req_skills[i];
            skillToBitMap.put(skill, i);
        }
        // prepare dev mask
        int[] devsMask = new int[people.size()];
        int indx = 0;
        for(List<String> skillSet : people) {
            for(String skill : skillSet) {
                int bitForSkill = skillToBitMap.get(skill);
                devsMask[indx] = (devsMask[indx] | (1 << bitForSkill));
            }
            indx++;
        }
        ans = new ArrayList<>();
        smallestSufficientTeam_rec(0, devsMask, 0, new ArrayList<>(), req_skills.length);
        int[] res = new int[ans.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    // no. of valid words
    public static ArrayList<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		// map of char vs bit mask of word
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < 26; i++) {
            char ch = (char)(i + 'a');
            map.put(ch, new ArrayList<>());
        }

        for(String word : words) {
            HashSet<Character> set = new HashSet<>();
            // prepare mask for word
            int mask = 0;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int k = (int)(ch - 'a');
                mask = (mask | (1 << k));
            }

            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(set.contains(ch)) continue;
                set.add(ch);
                map.get(ch).add(mask);
            }
        }

        // prepare count
        ArrayList<Integer> counts = new ArrayList<>();
        for(String puzzle : puzzles) {
            // prepare mask of puzzle
            int mask = 0;
            for(int i = 0; i < puzzle.length(); i++) {
                char ch = puzzle.charAt(i);
                int k = (int)(ch - 'a');
                mask = (mask | (1 << k));
            }

            // check in valid word and find count
            char firstChar = puzzle.charAt(0);
            int count = 0;
            for(int wordMask : map.get(firstChar)) {
                if((wordMask & mask) == wordMask) {
                    count++;
                }
            }
            counts.add(count);
        }
        return counts;
	}

    // non repeating number
    private static int nonRepeatingNum(int[] arr) {
        int res = 0;
        for(int val : arr) {
            res = (res ^ val);
        }
        return res;
    }

    // all repeating except two numbers
    public static void allReeatingExceptTwoNum(int[] arr){
        // find rsbm in xor of all numbersa
        int xor = 0;
        for(int val : arr) {
            xor = (xor ^ val);
        }

        int rsbm = (xor & (-xor));

        int x = 0; // rsbm bit is OFF
        int y = 0; // rsbm bit is ON

        for(int val : arr) {
            if((val & rsbm) == 0) {
                // rsbm bit is OFF
                x = (x ^ val);
            } else {
                // rsbm bit is ON
                y = (y ^ val);
            }
        }
        if(x < y) {
            System.out.println(x);
            System.out.println(y);
        } else {
            System.out.println(y);
            System.out.println(x);
        }
    }

    // one number is repeating and one is missing
    public static void oneRepeatOneMiss(int[] arr){
        int xor = 0;
        for(int val : arr) {
            xor = (xor ^ val);
        }    
        for(int i = 1; i <= arr.length; i++) {
            xor = (xor ^ i);
        }

        int rsbm = (xor & (-xor));

        int x = 0;
        int y = 0;

        for(int val : arr) {
            if((val & rsbm) == 0) {
                // bit is off
                x = (x ^ val);
            } else {
                // bit is on
                y = (y ^ val);
            }
        }
        for(int val = 1; val <= arr.length; val++) {
            if((val & rsbm) == 0) {
                // bit is off
                x = (x ^ val);
            } else {
                // bit is on
                y = (y ^ val);
            }
        }

        // check of x is missing or y is missing
        boolean xmiss = true;
        for(int val : arr) {
            if(x == val) {
                xmiss = false;
                break;
            }
        }
        if(xmiss == true) {
            System.out.println(x);
            System.out.println(y);
        } else {
            System.out.println(y);
            System.out.println(x);
        }
    }


    public static void main(String[] args) {
        System.out.println();
    }
}

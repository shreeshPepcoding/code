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

    // all repeating thrice except one
    public static void repeatThriceExceptOne1(int[] arr){
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int count = 0;
            int bm = (1 << i);
            for(int val : arr) {
                // check if ith bit is ON or OFF in val
                if((val & bm) == bm) {
                    count++;
                }
            }
            if(count % 3 == 1) {
                // ON ith bit in result
                res = (res | bm);
            }
        }   
        System.out.println(res);
    }

    public static void repeatThriceExceptOne2(int[] arr){
        int n3p0 = (~0); // all bits are 1
        int n3p1 = 0; // all bits are 0
        int n3p2 = 0; // all bits are 0

        for(int val : arr) {
            int cn3p1 = (n3p0 & val);
            int cn3p2 = (n3p1 & val);
            int cn3p3 = (n3p2 & val);

            // off in same bit and on in next one
            // cn3p1 -> OFF in n3p0 and ON in n3p1
            n3p0 = (n3p0 & (~cn3p1));
            n3p1 = (n3p1 | cn3p1);
            
            // cn3p2 -> OFF in n3p1 and ON in n3p2
            n3p1 = (n3p1 & (~cn3p2));
            n3p2 = (n3p2 | cn3p2);
            
            // cn3p3 -> OFF in n3p2 and ON in n3p0
            n3p2 = (n3p2 & (~cn3p3));
            n3p0 = (n3p0 | cn3p3);
        }
        System.out.println(n3p1);
    }

    // triplet 1
    public static void triplet1(int[] arr){
        int count = 0;

        for(int i = 0; i < arr.length; i++) {
            int xor = arr[i];
            for(int k = i + 1; k < arr.length; k++) {
                xor = (xor ^ arr[k]);
                if(xor == 0) {
                    count += (k - i);
                } 
            }
        }
        System.out.println(count);
    }

    // reduce from N to 1
    public static int reduceNto1(long n) {
        int count = 0;
        while(n != 1) {
            if((n & 1) == 0) {
                // n % 2 == 0
                // even
                n = n / 2;
            } else if(n == 3) {
                // special case
                n = n - 1;
            } else if((n & 3) == 1) {
                // n % 4 == 1
                // odd 1 type
                n = n - 1;
            } else if((n & 3) == 3) {
                // n % 4 == 3
                // odd 2 type
                n = n + 1;
            }
            count++;
        }
        return count;
    }

    // XOR of sum of all pairs
    public static int XORofSumOfAllPairs(int[] arr){
        int res = 0;
        for(int val : arr) {
            res = (res ^ val);
        }
        return 2 * res;
    }

    // pepcoder and bits
    public static long ncr(long n, long r){
        if(n < r){
            return 0L;    
        }
        long res = 1L;
        for(long i = 0L; i < r; i++){
            res = res * (n - i);
            res = res / (i + 1);
        }
        return res;
    }
        
    public static int csb(long n){
        // kernighan's Algorithm
        int res = 0;
        while(n > 0){
            long rsb = n & -n;
            n -= rsb;
            res++;
        }
        
        return res;
    }
    
    // n -> number, srb -> required set bits, lbi -> leftmost bit index => 63
    private static long pepcoderAndBits_rec(long n, int rsb, int lbi) {
        if(rsb == 0) return 0L;
        long count = 0L;
        // check left most index bit
        long bm = (1L << lbi);
        if((n & bm) == 0) {
            // leftmost bit index's bit is OFF
            count = pepcoderAndBits_rec(n, rsb, lbi - 1);
        } else {
            // bit is ON
            // find count if lbi is considered as OFF
            count = ncr(lbi, rsb);
            // find count if lbi is ON
            count += pepcoderAndBits_rec(n, rsb - 1, lbi - 1);
        }
        return count;
    }

    public static long pepcoderAndBits(long n) {
        int rsb = csb(n);
        return pepcoderAndBits_rec(n, rsb, 63);
    }

    public static void main(String[] args) {
        System.out.println();
    }
}

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

    // abbreviation 1
    public static void abbreviation1(String str){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < (1 << str.length()); i++) {
            int count = 0;
            for(int j = 0; j < str.length(); j++) {
                int k = str.length() - 1 - j; // bit index, because bit index begin from right to left

                // check if kth bit is ON or OFF in 'i'
                int bm = 1 << k;
                char ch = str.charAt(j);
                if((i & bm) == 0) {
                    // bit is OFF
                    if(count == 0) {
                        res.append(ch);
                    } else {
                        res.append(count);
                        res.append(ch);
                        count = 0; // reset count
                    }
                } else {
                    // bit is ON
                    count++;
                }
            }
            if(count != 0) {
                res.append(count);
            }
            res.append("\n");
        }
        System.out.println(res);
    }

    // utf 8 encoding
    public static boolean utf8Encoding(int[] arr) {
        int remByte = 0;
        for(int val : arr) {
            if(remByte == 0) {
                if((val >> 7) == 0b0) {
                    // 1 byte character
                    remByte = 0;
                } else if((val >> 5) == 0b110) {
                    // 2 byte character
                    remByte = 1;
                } else if((val >> 4) == 0b1110) {
                    // 3 byte character
                    remByte = 2;
                } else if((val >> 3) == 0b11110) {
                    // 4 byte character
                    remByte = 3;
                } else {
                    return false;
                }
            } else {
                // check for remain byte i.e. it begin with 10_____
                if((val >> 6) != 0b10) {
                    return false;
                }
                remByte--;
            }
        }
        // return true;
        return remByte == 0;
    }

    // sudoku
    public static void display(int[][] arr){
        for (int ii = 0; ii < arr.length; ii++) {
          for (int jj = 0; jj < arr.length; jj++) {
            System.out.print(arr[ii][jj] + " ");
          }
          System.out.println();
        }
        System.out.println();
    }


    private static boolean isSafeToPlace(int[] rows, int[] cols, int[][] grid, int i, int j, int n) {
        int bm = (1 << n);
        
        // row repitition check
        int rowBitMask = rows[i];
        // check if nth bit is ON in rowBitMask
        if((rowBitMask & (1 << n)) == (1 << n)) {
            return false;
        }

        // col repitition check
        int colBitMask = cols[j];
        // check if nth bit is ON in rowBitMask
        if((colBitMask & bm) == bm) {
            return false;
        }

        // submatrix repitition check
        int smri = i / 3; // smri -> submatrix row index
        int smci = j / 3; // smci -> submatrix col index

        int smBitMask = grid[smri][smci];
        if((smBitMask & bm) == bm) {
            return false;
        }

        return true;
    }

    private static void OnBitInChecks(int[] rows, int[] cols, int[][] grid, int i, int j, int n) {
        rows[i] = (rows[i] | (1 << n));
        cols[j] = (cols[j] | (1 << n));
        grid[i / 3][j / 3] = (grid[i / 3][j / 3] | (1 << n));
    }

    private static void OffBitInChecks(int[] rows, int[] cols, int[][] grid, int i, int j, int n) {
        int bm = (1 << n);
        rows[i] = (rows[i] & (~bm));
        cols[j] = (cols[j] & (~bm));
        grid[i / 3][j / 3] = (grid[i / 3][j / 3] & (~bm));
    }

    public static void solveSudoku(int[][] arr, int[] rows, int[] cols, int[][] grid, int i, int j) {
        // for particular i, j index, we check all 9 possible number to place it at i, j, if it is not occupied
        if(j == arr[0].length) {
            i++;
            j = 0;
        }

        if(i == arr.length) {
            display(arr);
            return;
        }

        if(arr[i][j] != 0) {
            // place is occupied
            solveSudoku(arr, rows, cols, grid, i, j + 1); // try on next cell
        } else {
            for(int num = 1; num <= 9; num++) {
                // check if we can place num at i, j
                if(isSafeToPlace(rows, cols, grid, i, j, num) == true) {
                    // place num
                    arr[i][j] = num;
                    OnBitInChecks(rows, cols, grid, i, j, num);
                    // make call to next cell
                    solveSudoku(arr, rows, cols, grid, i, j + 1);
                    // unplace num for backtracking
                    OffBitInChecks(rows, cols, grid, i, j, num);
                    arr[i][j] = 0;
                }
            }
        }
    }

    // n queen using bits
    private static boolean isQueenSafe(int i, int j, int cmask, int dmask, int admask, int n) {
        // column check
        int indx = j;
        if((cmask & (1 << indx)) != 0) {
            // in cmask, jth bit is ON, it means jth column is occupied
            return false;
        }

        // diagonal check
        indx = j - i + n - 1;
        if((dmask & (1 << indx)) != 0) {
            // in cmask, jth bit is ON, it means jth column is occupied
            return false;
        }
        // antidiagonal check
        indx = i + j;
        if((admask & (1 << indx)) != 0) {
            // in cmask, jth bit is ON, it means jth column is occupied
            return false;
        }

        return true;
    }

    // cmask -> column mask, dmask -> diagonal mask, admask -> anti diagonal mask
    public static void nqueen(boolean[][] board, int r, int cmask, int dmask, int admask, String asf) {
        // board is not required, we can solve this without board
        int n = board.length;
        if(r == n) {
            System.out.println(asf + ".");
            // System.out.println("yes");
            return;
        }

        for(int c = 0; c < board.length; c++) {
            if(isQueenSafe(r, c, cmask, dmask, admask, n) == true) {
                // place 
                board[r][c] = true;

                cmask = (cmask | (1 << c));
                dmask = (dmask | (1 << (c - r + n - 1)));
                admask = (admask | 1 << (r + c));

                // call to next row
                nqueen(board, r + 1, cmask, dmask, admask, asf + r + "-" + c + ", ");
                // unplace

                int bm1 = (1 << c);
                cmask = (cmask & (~bm1));
                int bm2 = (1 << (c - r + n - 1));
                dmask = (dmask & (~bm2));
                int bm3 = (1 << (r + c));
                admask = (admask & (~bm3));
                board[r][c] = false;
            }
        }
    }

    // is a power of 2, leetcode 231
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // is power of 4, leetcode 342
    public boolean isPowerOfFour(int n) {
        // int bm = 0b1010_1010_1010_1010_1010_1010_1010_1010; // literals for binary to int
        int bm = 0xA_A_A_A_A_A_A_A; // literals for hexadecimal to int
        return n > 0 && ((n & (n - 1)) == 0) && (n & bm) == 0;
    }

    // swap odd and even index bits
    private static int swapBits(int n) {
        int mask1 = 0x5_5_5_5_5_5_5_5;
        int mask2 = 0xA_A_A_A_A_A_A_A;

        // 1. Preserve odd index
        int n1 = (n & mask1);
        // 2. Preserve even index
        int n2 = (n & mask2);
        // 3. left shift in n1
        n1 = (n1 << 1);
        // 4. Right shift in n2
        n2 = (n2 >> 1);
        // 5. res = n1 OR n2
        int res = (n1 | n2);
        return res;
    }

    // sum of bit difference of all pairs 
    public static long sumOfBitDifference(int[] arr){
        long res = 0;
        for(int i = 0; i < 32; i++) {
            long count0 = 0;
            long count1 = 0;
            for(int val : arr) {
                // check if ith bit is ON in val or not
                int bm = (1 << i);
                if((val & bm) != 0) {
                    // bit is ON
                    count1++;
                } else {
                    // bit is OFF
                    count0++;
                }
            }
            res += count0 * count1 * 2;
        }
        return res;
    }

    // print binary and Decimal form of reverse of Binary
    private static void printBinaryAndReverseDecimal(int n) {
        boolean flag = false;
        int rev = 0;
        int j = 0;
        for(int i = 31; i >= 0; i--) {
            int bm = 1 << i;
            if(flag == true) {
                if((bm & n) != 0) {
                    // bit is ON
                    System.out.print(1);
                    int bm2 = 1 << j;
                    rev = (rev | bm2);
                } else {
                    System.out.print(0);
                }
                j++;
            } else {
                if((bm & n) != 0) {
                    flag = true;
                    // bit is ON
                    System.out.print(1);
                    int bm2 = 1 << j;
                    rev = (rev | bm2);
                    j++;
                } else {
                    // leading zero, skip
                }
            }
        }
        System.out.println("\n" + rev);
    }

    // check if binary String is divisible by 3
    private static boolean isDivisibleBy3(String num) {
        int odd = 0;
        int even = 0;
        for(int i = 0; i < num.length(); i++) {
            int bit = num.charAt(i) - '0';
            if(i % 2 == 0) {
                even += bit;
            } else {
                odd += bit;
            }
        }
        return (Math.abs(odd - even) % 11) == 0;
    }

    // count set bit in N natural numbers
    private static int powerOf2(int n) {
        int num = 1;
        int x = 0;
        while(num <= n) {
            num = (num << 1);
            x++;
        }
        x--;
        return x;
    }

    public static int countSetBitInNNaturalNumber(int n){
        if(n == 0) return 0;
        int x = powerOf2(n);
        int first = x * (1 << (x - 1));
        int second = n + 1 - (1 << x);
        int recRes = countSetBit(n - (1 << x));
        int res = first + second + recRes;    
        return res;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}

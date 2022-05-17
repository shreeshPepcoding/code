import java.util.*;

public class backtracking {


    // ci-> current item, ti-> total items
    public static void permutations1(int[] boxes, int ci, int ti){
        if(ci > ti) {
            // print arrange of items in box and return
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }
        // loop of options
        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // place current item at bth box
                boxes[b] = ci;
                // recursive call
                permutations1(boxes, ci + 1, ti);
                // unplace current item at bth box
                boxes[b] = 0;
            }
        }
    }

    // cb-> current box, tb-> total box, ssf -> selected so far, ts-> total selection, asf-> answer so far
    public static void combinations1(int cb, int tb, int ssf, int ts, String asf){
        if(cb > tb) {
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }
        // yes call
        if(ssf < ts) {
            combinations1(cb + 1, tb, ssf + 1, ts, asf + "i");
        }
        // no call
        combinations1(cb + 1, tb, ssf, ts, asf + "-");
    }

    public static void permutations2(int cb, int tb, int[] items, int ssf, int ts, String asf){
        // base case
        if(cb > tb) {
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }
        // yes call
        for(int i = 0; i < items.length; i++) {
            if(items[i] == 0 && ssf < ts) {
                // use ith item -> mark
                items[i] = i + 1;
                permutations2(cb + 1, tb, items, ssf + 1, ts, asf + (i + 1));
                // make it available -> unmark
                items[i] = 0;
            }
        }
        // no call
        permutations2(cb + 1, tb, items, ssf, ts, asf + "0");
    }

    // ci -> current item, ti-> total item, lb -> last box
    public static void combinations2(int[] boxes, int ci, int ti, int lb){
        if(ci > ti) {
            for(int val : boxes) {
                if(val == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }   
        for(int b = lb + 1; b < boxes.length; b++) {
            // place
            boxes[b] = ci;
            // call
            combinations2(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    // qpsf -> queen place so far, tq -> total queen, asf-> answer so far, level -> box, option, choice of queen
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        if(row == tq) {
            if(qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }

        if(col + 1 < tq) {
            // mauka no. 1 -> only increment in column 
            // yes call
            queensCombinations(qpsf + 1, tq, row, col + 1, asf + "q");
            // no call
            queensCombinations(qpsf, tq, row, col + 1, asf + "-");
        } else {
            // mauka no. 2 -> increment row, and column begin from 0
            // yes call
            queensCombinations(qpsf + 1, tq, row + 1, 0, asf + "q\n");
            // no call
            queensCombinations(qpsf, tq, row + 1, 0, asf + "-\n");
        }
    }

    // qpsf -> queen place so far, tq -> total queen, level -> queen, options -> box
    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        if(qpsf == tq) {
            // print result
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess.length; j++) {
                    if(chess[i][j] != 0)
                        System.out.print("q" + chess[i][j] + "\t");
                    else
                        System.out.print("-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        // option -> box
        for(int i = 0; i < chess.length; i++) {
            for(int j = 0; j < chess.length; j++) {
                if(chess[i][j] == 0) {
                    chess[i][j] = qpsf + 1;
                    queensPermutations(qpsf + 1, tq, chess);
                    chess[i][j] = 0;
                }
            }
        }
    }

    // level -> box, row , col
    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        if(row == tq) {
            if(qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }

        if(col + 1 < tq) {
            // yes call
            for(int q = 0; q < queens.length; q++) {
                if(queens[q] == false) {
                    queens[q] = true;
                    queensPermutations(qpsf + 1, tq, row, col + 1, asf + "q" + (q + 1) + "\t", queens);
                    queens[q] = false;
                }
            }
            // no call
            queensPermutations(qpsf, tq, row, col + 1, asf +  "-\t", queens);
        } else {
            // yes call
            for(int q = 0; q < queens.length; q++) {
                if(queens[q] == false) {
                    queens[q] = true;
                    queensPermutations(qpsf + 1, tq, row + 1, 0, asf + "q" + (q + 1) + "\n", queens);
                    queens[q] = false;
                }
            }
            // no call
            queensPermutations(qpsf, tq, row + 1, 0, asf +  "-\n", queens);
        }
    }

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int i, int j){
        if(qpsf == tq) {
            for(int r = 0; r < chess.length; r++) {
                for(int c = 0; c < chess.length; c++) {
                    if(chess[r][c] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }


        // finish current row
        for(int c = j + 1; c < chess.length; c++) {
            int r = i;
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, r, c);
            chess[r][c] = false;
        }

        // fininsh remaining board
        for(int r = i + 1; r < chess.length; r++) {
            for(int c = 0; c < chess.length; c++) {
                chess[r][c] = true;
                queensCombinations(qpsf + 1, tq, chess, r, c);
                chess[r][c] = false;
            }
        }

    }

    // qpsf -> queen place so far, tq-> total queen, lbno-> last box number
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lbno) {
        if(qpsf == tq) {
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess.length; j++) {
                    if(chess[i][j] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int b = lbno + 1; b < chess.length * chess.length; b++) {
            int r = b / chess.length;
            int c = b % chess.length;
            
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, b);
            chess[r][c] = false;
        }
    }

    public static boolean isQueenSafe1(boolean[][] chess, int r, int c) {
        // direction 1
        for(int j = c - 1; j >= 0; j--) {
            if(chess[r][j] == true) return false;
        }
        // direction 2
        for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if(chess[i][j] == true) return false;
        }
        // direction 3
        for(int i = r - 1; i >= 0; i--) {
            if(chess[i][c] == true) return false;
        }
        // direction 4
        for(int i = r - 1, j = c + 1; i >= 0 && j < chess[0].length; i--, j++) {
            if(chess[i][j] == true) return false;
        }

        return true;
    }

    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lbno) {
        if(qpsf == tq) {
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess.length; j++) {
                    if(chess[i][j] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return; 
        }

        for(int b = lbno + 1; b < tq * tq; b++) {
            int r = b / chess[0].length;
            int c = b % chess[0].length;

            if(isQueenSafe1(chess, r, c) == true) {
                chess[r][c] = true; // queen is placed at (r, c)
                nqueens(qpsf + 1, tq, chess, b);
                chess[r][c] = false;
            }
        }   
    }

    static int[] xdir = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] ydir = {0, 1, 1, 1, 0, -1, -1, -1};

    public static boolean IsQueenSafe2(int[][] chess, int row, int col) {
        for(int d = 0; d < 8; d++) {
            for(int rad = 1; rad < chess.length; rad++) {
                int r = row + (xdir[d] * rad);
                int c = col + (ydir[d] * rad);

                if(r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        if(qpsf == tq) {
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess[0].length; j++) {
                    if(chess[i][j] != 0) {
                        System.out.print("q" + chess[i][j] + "\t");
                    } else {
                        System.out.print("-\t");
                    }
                } 
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int b = 0; b < tq * tq; b++) {
            int r = b / chess[0].length;
            int c = b % chess[0].length;

            if(chess[r][c] == 0 && IsQueenSafe2(chess, r, c) == true) {
                chess[r][c] = qpsf + 1;
                nqueens(qpsf + 1, tq, chess);
                chess[r][c] = 0;
            }
        }
    }

    static int[] rdir = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] cdir = {1, 2, 2, 1, -1, -2, -2, -1};

    public static boolean IsKnightSafe(boolean[][] chess, int x, int y) {
        for(int d = 0; d < 8; d++) {
            int r = x + rdir[d];
            int c = y + cdir[d];

            if(r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] == true) {
                return false;
            }
        }
        return true;
    }

    public static void nknights(int kpsf, int tk, boolean[][] chess, int lbno) {
        if(kpsf == tk) {
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess[0].length; j++) {
                    if(chess[i][j] == true) {
                        System.out.print("k\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int b = lbno + 1; b < tk * tk; b++) {
            int r = b / chess[0].length;
            int c = b % chess[0].length;

            if(IsKnightSafe(chess, r, c)) {
                chess[r][c] = true;
                nknights(kpsf + 1, tk, chess, b);
                chess[r][c] = false;
            }
        }
    }

    // permutation word - 1
    // cb -> current box, tb-> total box
    public static void generateWords(int cb, int tb, HashMap<Character, Integer> fmap, String asf) {
        if(cb > tb) {
            System.out.println(asf);
            return;
        }
        for(char ch : fmap.keySet()) {
            if(fmap.get(ch) > 0) {
                // exhaust character
                fmap.put(ch, fmap.get(ch) - 1);
                // call
                generateWords(cb + 1, tb, fmap, asf + ch);
                // regain character
                fmap.put(ch, fmap.get(ch) + 1);
            }
        }
    }

    // leetcode 47, https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> res;
    
    public void permutation(int cb, int tb, HashMap<Integer, Integer> map, List<Integer> asf) {
        if(cb > tb) {
            List<Integer> bres = new ArrayList<>();
            for(int val : asf) {
                bres.add(val);
            }
            res.add(bres);
            return;
        }
        
        for(int val : map.keySet()) {
            if(map.get(val) > 0) {
                map.put(val, map.get(val) - 1);
                asf.add(val);
                permutation(cb + 1, tb, map, asf);
                asf.remove(asf.size() - 1);
                map.put(val, map.get(val) + 1);
            }
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        
        List<Integer> asf = new ArrayList<>();
        permutation(1, nums.length, map, asf);
        return res;
    }

    // permutation word - 2
    public static void generateWords(int indx, String str, Character[] box, HashMap<Character, Integer> lastIndx) {
        if(indx == str.length()) {
            for(char c : box) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }
        char ch = str.charAt(indx);
        int li = lastIndx.get(ch);
        for(int b = li + 1; b < box.length; b++) {
            if(box[b] == null) {
                // change last index for char ch
                lastIndx.put(ch, b);
                box[b] = ch;
                // call
                generateWords(indx + 1, str, box, lastIndx);
                // regain last index for char ch
                lastIndx.put(ch, li);
                box[b] = null;
            }
        }
    }

    // word-k-selection 1
    public static void combination(int indx, String uniqStr, int ssf, int k, String asf ) {
        if(indx == uniqStr.length()) {
            if(ssf == k) {
                System.out.println(asf);
            }
            return;
        }
        char ch = uniqStr.charAt(indx);
        // yes call
        if(ssf < k)
            combination(indx + 1, uniqStr, ssf + 1, k, asf + ch);
        
        // no call  
        combination(indx + 1, uniqStr, ssf, k, asf);
    }

    public static void combination2(int lci, String uniqStr, int ssf, int k, String asf ) {
        if(ssf == k) {
            System.out.println(asf);
            return;
        }
        for(int i = lci + 1; i < uniqStr.length(); i++) {
            char ch = uniqStr.charAt(i);
            combination2(i, uniqStr, ssf + 1, k, asf + ch);
        }
    }

    private static void words_k_length_words_1(String ustr, int indx, Character[] spots, int ssf) {
        if(indx == ustr.length()) {
            if(ssf == spots.length) {
                for(char c : spots) {
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }
        
        char ch = ustr.charAt(indx);

        // yes call
        for(int spot = 0; spot < spots.length; spot++) {
            if(spots[spot] == null) {
                // occupy the spot
                spots[spot] = ch;
                words_k_length_words_1(ustr, indx + 1, spots, ssf + 1);
                // release spot
                spots[spot] = null;
            }
        }
        // no call
        words_k_length_words_1(ustr, indx + 1, spots, ssf);
    }

    private static void word_k_length_words_2(String ustr, boolean[] items, String asf, int ssf, int k) {
        if(ssf == k) {
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            if(items[i] == false) {
                items[i] = true;
                word_k_length_words_2(ustr, items, asf + ch, ssf + 1, k);
                items[i] = false;
            }
        }
    }

    // Words - K Selection - 3, cc-> current character
    public static void combination(String ustr, int cc, HashMap<Character, Integer> fmap, String asf, int k) {
        int ssf = asf.length();

        if (ssf == k) {
            System.out.println(asf);
            return;
        }

        if (cc == ustr.length())
            return;

        char ch = ustr.charAt(cc);
        int freq = fmap.get(ch);

        // yes call
        for (int i = freq; i > 0; i--) {
            if (i + ssf <= k) {

                String str = "";
                for (int j = 0; j < i; j++) {
                    str += ch;
                }
                combination(ustr, cc + 1, fmap, asf + str, k);
            }
        }
        // no call
        combination(ustr, cc + 1, fmap, asf, k);
    }

    // Words - K Selection - 4, li->last index, cs-> current spot, ts-> total spot
    public static void combination(String ustr, HashMap<Character, Integer> fmap, int li, String asf, int cs, int ts) {
        if (cs == ts) {
            System.out.println(asf);
            return;
        }

        for (int i = li; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if (freq > 0) {
                fmap.put(ch, freq - 1);
                combination(ustr, fmap, i, asf + ch, cs + 1, ts);
                fmap.put(ch, freq);
            }
        }
    }

    // Words - K Length Words - 3, cci-> current character index
    public static void words_k_length_words3(String str, int cci, int ssf, int ts, Character[] slots,
            HashMap<Character, Integer> map) {
        if (cci == str.length()) {
            if (ssf == ts) {
                for (char ch : slots) {
                    System.out.print(ch);
                }
                System.out.println();
            }
            return;
        }

        char ch = str.charAt(cci); // ch-> current character
        int loc = map.get(ch); // loc-> last occurrence of character

        // yes call
        for (int i = loc + 1; i < slots.length; i++) {
            if (slots[i] == null) {
                // change last occurrence of character
                map.put(ch, i);
                // place
                slots[i] = ch;

                words_k_length_words3(str, cci + 1, ssf + 1, ts, slots, map);

                // unplace
                slots[i] = null;
                // reset last occurrence of character
                map.put(ch, loc);
            }
        }

        if (loc == -1) {
            // no call
            words_k_length_words3(str, cci + 1, ssf, ts, slots, map);
        }
    }

    // Words - K Length Words - 4, cs-> current slots, ts -> total slots
    public static void words_k_length_words4(String ustr, int cs, int ts, String asf,
            HashMap<Character, Integer> fmap) {
        if (cs == ts) {
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if (freq > 0) {
                // freq reduce
                fmap.put(ch, freq - 1);

                words_k_length_words4(ustr, cs + 1, ts, asf + ch, fmap);

                // freq regain
                fmap.put(ch, freq);
            }
        }
    }

    // coin change combination 1, amtsf-> amount so far, tamt-> total amount
    public static void coinChange1(int i, int[] coins, int amtsf, int tamt, String asf) {
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }
        if (i == coins.length) {
            return;
        }
        // yes call
        if (coins[i] + amtsf <= tamt) {
            coinChange1(i + 1, coins, amtsf + coins[i], tamt, asf + coins[i] + "-");
        }
        // no call
        coinChange1(i + 1, coins, amtsf, tamt, asf);
    }

    // coin change combination 2
    public static void coinChange2(int i, int[] coins, int amtsf, int tamt, String asf) {
        if (i == coins.length) {
            if (amtsf == tamt) {
                System.out.println(asf + ".");
            }
            return;
        }
        // yes call
        if (coins[i] + amtsf <= tamt) {
            coinChange2(i, coins, amtsf + coins[i], tamt, asf + coins[i] + "-");
        }
        // no call
        coinChange2(i + 1, coins, amtsf, tamt, asf);
    }

    // coin change permutation 1
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf, boolean[] used) {
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            if (used[i] == false && coins[i] + amtsf <= tamt) {
                used[i] = true;
                coinChange(coins, amtsf + coins[i], tamt, asf + coins[i] + "-", used);
                used[i] = false;
            }
        }
    }

    // coin change permutation 2
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf) {
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] + amtsf <= tamt) {
                coinChange(coins, amtsf + coins[i], tamt, asf + coins[i] + "-");
            }
        }
    }

    // ===================================================================================
    // ~~~~~~~~~~~~~~~~~~~~~~Questions On Recursion and Backtracking~~~~~~~~~~~~~~~~~~~~~~
    // ===================================================================================
    // print abbreviations
    public static void solution(String str, String asf, int count, int indx) {
        if (indx == str.length()) {
            // base case
            String res = asf + (count != 0 ? count : "");
            System.out.println(res);
            return;
        }

        char ch = str.charAt(indx);
        // yes call -> answer so far + count(if != 0) + yes of char
        solution(str, asf + (count != 0 ? count : "") + ch, 0, indx + 1);
        // no call
        solution(str, asf, count + 1, indx + 1);
    }

    // N Queens - Branch And Bound -> initialise these arrays from main
    static boolean[] cols;
    static boolean[] nd; // normal diagonals
    static boolean[] rd; // reverse diagonals

    public static boolean isSafeToPlace(int r, int c) {
        // for saftey across column
        if (cols[c] == true) {
            return false;
        }
        // for safety across normal diagonal
        if (nd[r + c] == true) {
            return false;
        }
        // for saftey across reverse diagonal
        if (rd[r - c + cols.length - 1] == true) {
            return false;
        }
        return true;
    }

    public static void nQueens(int row, int n, String asf) {
        if (row == n) {
            System.out.println(asf + ".");
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafeToPlace(row, col)) {
                // mark
                cols[col] = true;
                nd[row + col] = true;
                rd[row - col + n - 1] = true;
                nQueens(row + 1, n, asf + row + "-" + col + ", ");
                // unmark
                cols[col] = false;
                nd[row + col] = false;
                rd[row - col + n - 1] = false;
            }
        }
    }

    // Max Score
    public static int solution(String[] words, int[] farr, int[] score, int idx) {
        if (idx == words.length)
            return 0;

        // no call
        int no_ans = solution(words, farr, score, idx + 1);

        // yes call
        String word = words[idx];
        int myScore = 0;
        boolean flag = true;
        // exhaust frequency + make score for current
        for (int i = 0; i < word.length(); i++) {
            int charIndx = word.charAt(i) - 'a';
            myScore += score[charIndx];

            if (farr[charIndx] <= 0) {
                flag = false;
            }
            farr[charIndx]--;
        }
        int yes_ans = 0;
        if (flag == true) {
            yes_ans = myScore + solution(words, farr, score, idx + 1);
        }
        // reset frequencies
        for (int i = 0; i < word.length(); i++) {
            int charIndx = word.charAt(i) - 'a';
            farr[charIndx]++;
        }
        return Math.max(no_ans, yes_ans);
    }

    // Josephus Problem
    public static int solution(int n, int k) {
        if (n == 1)
            return 0;
        return (solution(n - 1, k) + k) % n;
    }

    // Lexicographical Numbers
    public static void lexicography(int val, int n) {
        // base case
        if (val > n)
            return;
        // self printing
        System.out.println(val);
        // family printing
        for (int i = 0; i < 10; i++) {
            lexicography(10 * val + i, n);
        }
    }

    // Goldmine - 2
    static int max = 0;
    static int[] xdir1 = { -1, 0, 1, 0 };
    static int[] ydir1 = { 0, -1, 0, 1 };

    public static int dfs(int[][] arr, int i, int j) {
        // mark
        int gold = arr[i][j];
        arr[i][j] *= -1;
        // visit in neighbours
        for (int d = 0; d < xdir1.length; d++) {
            int r = i + xdir1[d];
            int c = j + ydir1[d];
            if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length && arr[r][c] > 0) {
                gold += dfs(arr, r, c);
            }
        }
        return gold;
    }

    public static void getMaxGold(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    int res = dfs(arr, i, j);
                    max = Math.max(max, res);
                }
            }
        }
    }

    // leetcode 52, https://leetcode.com/problems/n-queens-ii/submissions/
    private boolean[] col; // column
    private boolean[] dia1; // normal diagonal
    private boolean[] dia2; // reverse diagonal
    private int count;
    
    private boolean isSafeToPlace(int i, int j, int n) {
        if(col[j] == true) return false;
        else if(dia1[i + j] == true) return false;
        else if(dia2[i - j + n - 1] == true) return false;
        else return true;
    }
    
    private void nQueen(int row, int n) {
        if(row == n) {
            count++;
            return;
        }
        
        for(int c = 0; c < n; c++) {
            if(isSafeToPlace(row, c, n)) {
                col[c] = true;
                dia1[row + c] = true;
                dia2[row - c + n - 1] = true;
                
                nQueen(row + 1, n);
                
                col[c] = false;
                dia1[row + c] = false;
                dia2[row - c + n - 1] = false;
            }
        }
    }
    
    
    public int totalNQueens(int n) {
        if(n == 1) return 1;
        else if(n == 2 || n == 3) {
            return 0;
        }
        
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        count = 0;
        nQueen(0, n);
        return count;
    }

    // solve sudoku -> nados portal
    public static void display(int[][] board){
        for(int i = 0; i < board.length; i++){
          for(int j = 0; j < board[0].length; j++){
            System.out.print(board[i][j] + " ");
          }
          System.out.println();
        }
    }

    private static boolean safeSudoku(int[][] bd, int row, int col, int num) {
        // 1. check in row
        for(int c = 0; c < bd.length; c++) {
            if(bd[row][c] == num) return false;
        }
        // 2. check in col
        for(int r = 0; r < bd.length; r++) {
            if(bd[r][col] == num) return false;
        }
        // 3. check in sub matrix
        int r = row - row % 3;
        int c = col - col % 3;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int rr = r + i;
                int cc = c + j;

                if(bd[rr][cc] == num) return false;
            }
        }
        return true;
    }

    private static void solveSudoku_1(int[][] bd, ArrayList<Integer> list, int li) {
        if(li == list.size()) {
            display(bd);
            return;
        }

        int cell_no = list.get(li);
        int r = cell_no / bd.length;
        int c = cell_no % bd.length;

        for(int num = 1; num <= 9; num++) {
            if(safeSudoku(bd, r, c, num) == true) {
                bd[r][c] = num;
                solveSudoku_1(bd, list, li + 1);
                bd[r][c] = 0;
            }
        }
    }

    public static void solveSudoku(int[][] board, int r, int c) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == 0) {
                    list.add(board.length * i + j);
                }
            }
        }

        solveSudoku_1(board, list, 0);
    }




    public static void fun() {
        combinations1(1, 4, 0, 2, "");
    }

    public static void main(String[] args) {
        fun();
    }

}

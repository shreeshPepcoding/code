import java.util.*;

public class recursion {

    // ===================================================================================
    // ~~~~~~~~~~~~~~~~~~~~~~~~~PERMUTATION AND COMBINATIONS SET~~~~~~~~~~~~~~~~~~~~~~~~~~
    // ===================================================================================

    // ci-> current items. ti-> total items
    public static void permutations(int[] boxes, int ci, int ti) {
        if (ci > ti) {
            for (int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }

        for (int b = 0; b < boxes.length; b++) {
            if (boxes[b] == 0) {
                // box is empty
                // place object
                boxes[b] = ci;
                permutations(boxes, ci + 1, ti);
                // unplace objects
                boxes[b] = 0;
            }
        }
    }

    // cb-> current box, tb-> total box, isf->item so far, ti->total item, asf->
    // answer so far
    public static void combinations(int cb, int tb, int isf, int ti, String asf) {
        if (cb > tb) {
            if (isf == ti) {
                // print
                System.out.println(asf);
            }
            return;
        }

        // yes call
        if (isf + 1 <= ti)
            combinations(cb + 1, tb, isf + 1, ti, asf + "i");
        // no call
        combinations(cb + 1, tb, isf, ti, asf + "-");
    }

    // cb -> current box, tb-> total box, isf-> item so far, ti-> total item, asf->
    // asnwer so far
    public static void permutations(int cb, int tb, int[] items, int isf, int ti, String asf) {
        if (cb > tb) {
            if (isf == ti) {
                // print
                System.out.println(asf);
            }
            return;
        }

        // yes call
        for (int i = 0; i < items.length && isf < ti; i++) {
            if (items[i] == 0) {
                // select item
                items[i] = 1;
                permutations(cb + 1, tb, items, isf + 1, ti, asf + (i + 1));
                // deselect item
                items[i] = 0;
            }
        }
        // no call
        permutations(cb + 1, tb, items, isf, ti, asf + "0");
    }

    // ci-> current item, ti-> total item, lb-> last box used
    public static void combinations(int[] boxes, int ci, int ti, int lb) {
        if (ci > ti) {
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }

        for (int b = lb + 1; b < boxes.length; b++) {
            // place ci item on bth box
            boxes[b] = ci;
            combinations(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    // qpsf->queen placed so far, tq -> total queen, asf-> answer so far
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf) {
        if (row == tq) {
            if (qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }
        if (col + 1 < tq) {
            // yes call
            if (qpsf < tq)
                queensCombinations(qpsf + 1, tq, row, col + 1, asf + "q");
            // no call
            queensCombinations(qpsf, tq, row, col + 1, asf + "-");
        } else {
            // yes call
            if (qpsf < tq)
                queensCombinations(qpsf + 1, tq, row + 1, 0, asf + "q\n");
            // no call
            queensCombinations(qpsf, tq, row + 1, 0, asf + "-\n");
        }
    }

    public static void queensPermutations(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            // print result
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] != 0) {
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

        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                if (chess[i][j] == 0) {
                    // place queen
                    chess[i][j] = qpsf + 1;
                    queensPermutations(qpsf + 1, tq, chess);
                    // unplace queen
                    chess[i][j] = 0;
                }
            }
        }
    }

    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        if (row == tq) {
            if (qpsf == tq) {
                System.out.println(asf);
                System.out.println();
            }
            return;
        }

        // yes call
        for (int q = 0; q < queens.length; q++) {
            if (queens[q] == false) {
                // place the queen
                queens[q] = true;
                if (col + 1 < tq) {
                    queensPermutations(qpsf + 1, tq, row, col + 1, asf + "q" + (q + 1) + "\t", queens);
                } else {
                    queensPermutations(qpsf + 1, tq, row + 1, 0, asf + "q" + (q + 1) + "\n", queens);
                }
                // unplace queen
                queens[q] = false;
            }
        }
        // no call
        if (col + 1 < tq) {
            queensPermutations(qpsf, tq, row, col + 1, asf + "-\t", queens);
        } else {
            queensPermutations(qpsf, tq, row + 1, 0, asf + "-\n", queens);
        }
    }

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int i, int j) {
        if (qpsf == tq) {
            for (int r = 0; r < chess.length; r++) {
                for (int c = 0; c < chess[0].length; c++) {
                    if (chess[r][c] == true) {
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

        // travel in remaining columns in current row
        for (int c = j + 1; c < chess[0].length; c++) {
            int r = i;
            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, r, c);
            // unplace
            chess[r][c] = false;
        }

        // travel in remaining rows and columns
        for (int r = i + 1; r < chess.length; r++) {
            for (int c = 0; c < chess[0].length; c++) {
                // place
                chess[r][c] = true;
                queensCombinations(qpsf + 1, tq, chess, r, c);
                // unplace
                chess[r][c] = false;
            }
        }
    }

    // lcno -> last cell number
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            // System.out.println();
            return;
        }

        for (int b = lcno + 1; b < chess.length * chess[0].length; b++) {
            int r = b / chess.length;
            int c = b % chess[0].length;
            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, b);
            // unplace
            chess[r][c] = false;
        }
    }

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        // direction 0
        for (int r = row, c = col; c >= 0; c--) {
            if (chess[r][c] == true) {
                return false;
            }
        }
        // direction 1
        for (int r = row, c = col; c >= 0 && r >= 0; c--, r--) {
            if (chess[r][c] == true) {
                return false;
            }
        }
        // direction 2
        for (int r = row, c = col; r >= 0; r--) {
            if (chess[r][c] == true) {
                return false;
            }
        }
        // direction 3
        for (int r = row, c = col; c < chess[0].length && r >= 0; c++, r--) {
            if (chess[r][c] == true) {
                return false;
            }
        }
        return true;
    }

    // lcno -> last cell number
    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] == true) {
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

        for (int b = lcno + 1; b < chess.length * chess[0].length; b++) {
            int r = b / chess.length;
            int c = b % chess[0].length;
            if (IsQueenSafe(chess, r, c)) {
                // place
                chess[r][c] = true;
                nqueens(qpsf + 1, tq, chess, b);
                // unplace
                chess[r][c] = false;
            }
        }
    }

    static int[] rdir = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] cdir = { -1, -1, 0, 1, 1, 1, 0, -1 };

    // permutation for arrangement of queens
    public static boolean IsQueenSafe2(int[][] chess, int row, int col) {
        // write your code here
        for (int rad = 1; rad < chess.length; rad++) {
            for (int d = 0; d < 8; d++) {
                int r = row + rdir[d] * rad;
                int c = col + cdir[d] * rad;
                if (r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] != 0) {
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
        for (int b = 0; b < chess.length * chess[0].length; b++) {
            int r = b / chess.length;
            int c = b % chess.length;
            if (chess[r][c] == 0 && IsQueenSafe2(chess, r, c)) {
                // place
                chess[r][c] = qpsf + 1;
                nqueens(qpsf + 1, tq, chess);
                // unplace
                chess[r][c] = 0;
            }
        }
    }

    static int[] xdir = { -2, -1, -1, -2 };
    static int[] ydir = { 1, 2, -2, -1 };

    public static boolean IsKnightSafe(boolean[][] chess, int i, int j) {
        for (int d = 0; d < 4; d++) {
            int r = i + xdir[d];
            int c = j + ydir[d];

            if (r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] == true) {
                return false;
            }
        }
        return true;
    }

    public static void nknights(int kpsf, int tk, boolean[][] chess, int lcno) {
        if (kpsf == tk) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "k\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (chess[row][col] == false && IsKnightSafe(chess, row, col)) {
                chess[row][col] = true;
                nknights(kpsf + 1, tk, chess, row * chess.length + col);
                chess[row][col] = false;
            }
        }
    }

    // cs-> current selection, ts-> total selection, fmap->freq. map, asf->answer so
    // far
    public static void generateWords(int cs, int ts, HashMap<Character, Integer> fmap, String asf) {
        if (cs > ts) {
            System.out.println(asf);
            return;
        }

        for (char ch : fmap.keySet()) {
            if (fmap.get(ch) > 0) {
                int oldFreq = fmap.get(ch);
                fmap.put(ch, oldFreq - 1);
                generateWords(cs + 1, ts, fmap, asf + ch);
                fmap.put(ch, oldFreq);
            }
        }
    }

    // cc-> current character, li-> last index
    public static void generateWords(int cc, String str, Character[] spots, HashMap<Character, Integer> li) {
        if (cc == str.length()) {
            for (char c : spots) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        char ch = str.charAt(cc);
        int lsi = li.get(ch); // last spot index

        for (int box = lsi + 1; box < spots.length; box++) {
            if (spots[box] == null) {
                spots[box] = ch;
                li.put(ch, box);
                generateWords(cc + 1, str, spots, li);
                spots[box] = null;
                li.put(ch, lsi);
            }
        }
    }

    // i->index, ustr -> unique string, ssf->selected so far, ts->total selection
    public static void combination(int i, String ustr, int ssf, int ts, String asf) {
        if (i == ustr.length()) {
            if (ssf == ts) {
                System.out.println(asf);
            }
            return;
        }

        char ch = ustr.charAt(i);
        // yes call
        combination(i + 1, ustr, ssf + 1, ts, asf + ch);
        // no call
        combination(i + 1, ustr, ssf, ts, asf);
    }

    // lc-> last character
    public static void combination(String ustr, int ssf, int ts, String asf, int lc) {
        if (ssf == ts) {
            System.out.println(asf);
            return;
        }
        for (int i = lc + 1; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            combination(ustr, ssf + 1, ts, asf + ch, i);
        }
    }

    public static void permutation(String ustr, int ssf, int i, Character[] slots) {
        if (i == ustr.length()) {
            // print
            if (ssf == slots.length) {
                for (char c : slots) {
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }

        char ch = ustr.charAt(i);

        // yes call
        for (int s = 0; s < slots.length; s++) {
            if (slots[s] == null) {
                // place
                slots[s] = ch;
                permutation(ustr, ssf + 1, i + 1, slots);
                // unplace
                slots[s] = null;
            }
        }
        // no call
        permutation(ustr, ssf, i + 1, slots);
    }

    // Words - K Length Words - 2
    public static void permutation(String ustr, HashSet<Character> vis, int cs, int ts, String asf) {
        if (cs == ts) {
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            if (vis.contains(ch) == false) {
                // place
                vis.add(ch);
                permutation(ustr, vis, cs + 1, ts, asf + ch);
                // unplace
                vis.remove(ch);
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

    // solve soduko
    public static void display(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSafeToPlaceNum(int[][] board, int row, int col, int num) {
        // check in row
        for (int c = 0; c < board[0].length; c++) {
            if (board[row][c] == num)
                return false;
        }
        // check in column
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num)
                return false;
        }
        // check in submatrix
        // rr and cc is starting point of submatrix
        int rr = row - (row % 3);
        int cc = col - (col % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + rr][j + cc] == num)
                    return false;
            }
        }
        return true;
    }

    public static void solveSudoku(int[][] board, int i, int j) {
        if (i == board.length) {
            display(board);
            return;
        }

        if (board[i][j] == 0) {
            for (int num = 1; num < 10; num++) {
                if (isSafeToPlaceNum(board, i, j, num)) {
                    // place
                    board[i][j] = num;
                    if (j == board[0].length - 1) {
                        solveSudoku(board, i + 1, 0);
                    } else {
                        solveSudoku(board, i, j + 1);
                    }
                    // unplace
                    board[i][j] = 0;
                }
            }
        } else {
            if (j == board[0].length - 1) {
                solveSudoku(board, i + 1, 0);
            } else {
                solveSudoku(board, i, j + 1);
            }
        }
    }

    // sudoku from 1D using box number
    public static void solveSudoku1D(int[][] board, ArrayList<Integer> list, int indx) {
        if (indx == list.size()) {
            display(board);
            return;
        }

        int bno = list.get(indx);
        int r = bno / board.length;
        int c = bno % board.length;

        for (int num = 1; num < 10; num++) {
            if (isSafeToPlaceNum(board, r, c, num)) {
                board[r][c] = num;
                solveSudoku1D(board, list, indx + 1);
                board[r][c] = 0;
            }
        }
    }

    public static void solveSudoku1(int[][] board) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    int bno = board.length * i + j; // bno -> box number
                    list.add(bno);
                }
            }
        }

        solveSudoku1D(board, list, 0);
    }

    // Cryptarithmatic
    // s1 -> SEND, s2 -> MORE, s3-> MONEY, unique -> SENDMORY
    public static int IntegerFromMap(String str, HashMap<Character, Integer> map) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int n = map.get(ch);

            num = (num * 10 + n);
        }
        return num;
    }

    public static void solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int n1 = IntegerFromMap(s1, charIntMap);
            int n2 = IntegerFromMap(s2, charIntMap);
            int n3 = IntegerFromMap(s3, charIntMap);

            if (n1 + n2 == n3) {
                // print mapping in sorted order
                for (int i = 0; i < 26; i++) {
                    char ch = (char) (i + 'a');
                    if (charIntMap.containsKey(ch) == true) {
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }
        char ch = unique.charAt(idx);

        for (int i = 0; i < 10; i++) {
            if (usedNumbers[i] == false) {
                usedNumbers[i] = true;
                charIntMap.put(ch, i);
                solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);
                charIntMap.remove(ch);
                usedNumbers[i] = false;
            }
        }
    }

    // crossword
    public static boolean canPlaceHorizontal(char[][] grid, int r, int c, String word) {
        // left check
        if (c > 0 && grid[r][c - 1] != '+') {
            return false;
        }
        // right check
        if (c - 1 + word.length() >= grid[0].length) {
            return false;
        }

        if ((c - 1 + word.length() < grid[0].length - 1) && (grid[r][c + word.length()] != '+')) {
            return false;
        }
        for (int j = 0; j < word.length(); j++) {
            if (grid[r][j + c] != '-' && grid[r][j + c] != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canPlaceVertical(char[][] grid, int r, int c, String word) {
        // top check
        if (r > 0 && grid[r - 1][c] != '+') {
            return false;
        }
        // bottom check
        if (r - 1 + word.length() >= grid.length) {
            return false;
        }

        if ((r - 1 + word.length() < grid.length - 1) && (grid[r + word.length()][c] != '+')) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (grid[i + r][c] != '-' && grid[i + r][c] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeHorizontal(char[][] grid, int r, int c, String word) {
        boolean[] status = new boolean[word.length()];
        for (int j = 0; j < word.length(); j++) {
            if (grid[r][c + j] == '-') {
                grid[r][c + j] = word.charAt(j);
                status[j] = true;
            }
        }
        return status;
    }

    public static boolean[] placeVertical(char[][] grid, int r, int c, String word) {
        boolean[] status = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (grid[r + i][c] == '-') {
                grid[r + i][c] = word.charAt(i);
                status[i] = true;
            }
        }
        return status;
    }

    public static void unplaceHorizontal(char[][] grid, int r, int c, boolean[] status) {
        for (int i = 0; i < status.length; i++) {
            if (status[i] == true) {
                grid[r][c + i] = '-';
            }
        }
    }

    public static void unplaceVertical(char[][] grid, int r, int c, boolean[] status) {
        for (int i = 0; i < status.length; i++) {
            if (status[i] == true) {
                grid[r + i][c] = '-';
            }
        }
    }

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void solution(char[][] grid, String[] words, int vidx) {
        if (vidx == words.length) {
            // print
            print(grid);
            return;
        }

        String word = words[vidx];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '-' || grid[i][j] == word.charAt(0)) {
                    // horizontal try
                    if (canPlaceHorizontal(grid, i, j, word)) {
                        // place
                        boolean[] status = placeHorizontal(grid, i, j, word);
                        solution(grid, words, vidx + 1);
                        // unplace
                        unplaceHorizontal(grid, i, j, status);
                    }
                    // vertical try
                    if (canPlaceVertical(grid, i, j, word)) {
                        // place
                        boolean[] status = placeVertical(grid, i, j, word);
                        solution(grid, words, vidx + 1);
                        // unplace
                        unplaceVertical(grid, i, j, status);
                    }
                }
            }
        }
    }

    // k-partition
    public static int count = 1;

    public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
        if (i > n) {
            if (ans.size() == k) {
                System.out.print(count + ". ");
                for (int j = 0; j < ans.size(); j++) {
                    ArrayList<Integer> list = ans.get(j);
                    System.out.print(list + " ");
                }
                System.out.println();
                count++;
            }
            return;
        }
        // n - 1, k work, add with previous options
        for (int j = 0; j < ans.size(); j++) {
            ArrayList<Integer> list = ans.get(j);
            list.add(i);
            solution(i + 1, n, k, rssf, ans);
            list.remove(list.size() - 1);
        }

        // n - 1, k - 1, start from myself if size + 1 <= k
        if (ans.size() + 1 <= k) {
            ArrayList<Integer> mres = new ArrayList<>();
            mres.add(i);
            ans.add(mres);
            solution(i + 1, n, k, rssf, ans);
            ans.remove(ans.size() - 1);
        }
    }

    // magnets
    public static int signCountInRow(char[][] ans, int row, char sign) {
        int count = 0;
        for (int c = 0; c < ans[0].length; c++) {
            if (ans[row][c] == sign) {
                count++;
            }
        }
        return count;
    }

    public static int signCountInCol(char[][] ans, int col, char sign) {
        int count = 0;
        for (int r = 0; r < ans.length; r++) {
            if (ans[r][col] == sign) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValid(char[][] ans, int[] top, int[] left, int[] right, int[] bottom, int r, int c,
            char sign) {
        // make a check for valid polarity
        int[] xdir = { -1, 0, 0 };
        int[] ydir = { 0, 1, -1 };
        for (int d = 0; d < 3; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            if (rr >= 0 && rr < ans.length && cc >= 0 && cc < ans[0].length && ans[rr][cc] == sign) {
                return false;
            }
        }
        // make a check for valid sign count in row and col
        int cir = signCountInRow(ans, r, sign); // cir -> count in row
        int cic = signCountInCol(ans, c, sign); // cic -> count in column

        // top and left -> +ve sign, bottom ans right -> -ve sign
        if (sign == '+') {
            if ((top[c] != -1 && cic >= top[c]) || (left[r] != -1 && cir >= left[r])) {
                return false;
            }
        } else {
            if ((bottom[c] != -1 && cic >= bottom[c]) || (right[r] != -1 && cir >= right[r])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCorrectResult(char[][] ans, int[] top, int[] left, int[] bottom, int[] right) {
        // check for row
        for (int r = 0; r < ans.length; r++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for (int c = 0; c < ans[0].length; c++) {
                if (ans[r][c] == '+')
                    pcount++;
                else if (ans[r][c] == '-')
                    ncount++;
            }
            if (left[r] != -1 && left[r] != pcount)
                return false;
            if (right[r] != -1 && right[r] != ncount)
                return false;
        }
        // check for col
        for (int c = 0; c < ans[0].length; c++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for (int r = 0; r < ans.length; r++) {
                if (ans[r][c] == '+')
                    pcount++;
                else if (ans[r][c] == '-')
                    ncount++;
            }
            if (top[c] != -1 && top[c] != pcount)
                return false;
            if (bottom[c] != -1 && bottom[c] != ncount)
                return false;
        }
        return true;
    }

    public static boolean solution(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans,
            int row, int col) {
        if (col == arr[0].length) {
            col = 0;
            row++;
        }
        if (row == ans.length) {
            if (isCorrectResult(ans, top, left, bottom, right))
                return true;
            else
                return false;
        }
        // yes call
        if (arr[row][col] == 'L') {
            // [L | R] -> + -
            if (isValid(ans, top, left, right, bottom, row, col, '+')
                    && isValid(ans, top, left, right, bottom, row, col + 1, '-')) {
                // place + -
                ans[row][col] = '+';
                ans[row][col + 1] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace + -
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
            // [L | R] -> - +
            if (isValid(ans, top, left, right, bottom, row, col, '-')
                    && isValid(ans, top, left, right, bottom, row, col + 1, '+')) {
                // place - +
                ans[row][col] = '-';
                ans[row][col + 1] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace - +
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
        } else if (arr[row][col] == 'T') {
            // [T | B] -> + -
            if (isValid(ans, top, left, right, bottom, row, col, '+')
                    && isValid(ans, top, left, right, bottom, row + 1, col, '-')) {
                // place + -
                ans[row][col] = '+';
                ans[row + 1][col] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace + -
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
            // [T | B] -> - +
            if (isValid(ans, top, left, right, bottom, row, col, '-')
                    && isValid(ans, top, left, right, bottom, row + 1, col, '+')) {
                // place - +
                ans[row][col] = '-';
                ans[row + 1][col] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace - +
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
        }
        // no call
        if (solution(arr, top, left, right, bottom, ans, row, col + 1)) {
            return true;
        }
        return false;
    }

    // Friends Pairing - 2
    static int counter = 1;

    public static void solution(int i, int n, boolean[] used, String asf) {
        if (i > n) {
            System.out.println(counter + "." + asf);
            counter++;
            return;
        }
        if (used[i] == true) {
            // already included in lower levels, so no option at current level
            solution(i + 1, n, used, asf);
        } else {
            // single call
            used[i] = true;
            solution(i + 1, n, used, asf + "(" + i + ") ");
            // pairup calls
            for (int j = i + 1; j <= n; j++) {
                if (used[j] == false) {
                    used[j] = true;
                    solution(i + 1, n, used, asf + "(" + i + "," + j + ") ");
                    used[j] = false;
                }
            }
            used[i] = false;
        }
    }

    // All Palindromic Permutations
    public static String reverse(String str) {
        String res = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            res += str.charAt(i);
        }
        return res;
    }

    public static void generatepw(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc, String asf) {
        if (cs == ts) {
            String rev = reverse(asf);
            if (oddc == null) {
                System.out.println(asf + rev);
            } else {
                System.out.println(asf + oddc + rev);
            }
            return;
        }

        for (char ch : fmap.keySet()) {
            int freq = fmap.get(ch);
            if (freq > 0) {
                // use current character, reduce freq
                fmap.put(ch, freq - 1);
                generatepw(cs + 1, ts, fmap, oddc, asf + ch);
                // regain freq.
                fmap.put(ch, freq);
            }
        }
    }

    public static void solve(HashMap<Character, Integer> fmap) {
        int oddCount = 0;
        Character ch = null; // blank character
        int len = 0;
        for (char key : fmap.keySet()) {
            len += fmap.get(key);
            if (fmap.get(key) % 2 == 1) {
                oddCount++;
                ch = key;
            }
            fmap.put(key, fmap.get(key) / 2);
        }
        if (oddCount > 1) {
            System.out.println(-1);
            return;
        }
        generatepw(0, len / 2, fmap, ch, "");
    }

    // All Palindromic Partitions
    public static boolean isPlaindromic(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void solution(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String option = str.substring(0, i + 1);
            String roq = str.substring(i + 1);
            if (isPlaindromic(option)) {
                solution(roq, asf + "(" + option + ") ");
            }
        }
    }

    // K Subsets With Equal Sum
    public static boolean isSumSame(int[] arr) {
        int val = arr[0];
        for(int ele : arr) {
            if(ele != val) return false;
        }
        return true;
    }

    public static void solution(int[] arr, int indx, int sum, int k, int[] subsetSum, int ssf, ArrayList<ArrayList<Integer>> ans) {
        if(indx == arr.length) {
            if(ssf == k) {
                if(isSumSame(subsetSum) == true) {
                    for(ArrayList<Integer> list : ans) {
                        System.out.print(list + " ");
                    }
                    System.out.println();
                }
            }
            return;
        }
        int val = arr[indx];
        int i = 0;
        // merging with existing set
        while(i < ans.size() && ans.get(i).size() > 0) {
            if(subsetSum[i] + val <= (sum / k)) {
                // increase subset sum
                subsetSum[i] += val;
                // add in answer so far
                ans.get(i).add(val);
                solution(arr, indx + 1, sum, k, subsetSum, ssf, ans);
                // decrease subset sum
                subsetSum[i] -= val;
                // remove from asf
                ans.get(i).remove(ans.get(i).size() - 1);
            }
            i++;
        }
        if(i < k) {
            // single call with new set
            // increase subset sum
            subsetSum[i] += val;
            // add in answer so far
            ans.get(i).add(val);
            solution(arr, indx + 1, sum, k, subsetSum, ssf + 1, ans);
            // decrease subset sum
            subsetSum[i] -= val;
            // remove from asf
            ans.get(i).remove(ans.get(i).size() - 1);
        }
    }

    // Tug Of War, soset1 (sum1) -> sum of set 1, soset 2 (sum2)-> sum of set 2
    static int mindiff = Integer.MAX_VALUE;
	static String ans = "";
	public static void solve(int[] arr, int indx, ArrayList<Integer> s1, ArrayList<Integer> s2, int sum1, int sum2) {
        if(indx == arr.length) {
            int diff = Math.abs(sum1 - sum2);
            if(diff < mindiff) {
                mindiff = diff;
                ans = "" + s1 + " " + s2;
            }
            return;
        }
        int val = arr[indx];
        // val in set1
        if(s1.size() < (arr.length + 1) / 2) {
            s1.add(val);
            solve(arr, indx + 1, s1, s2, sum1 + val, sum2);
            s1.remove(s1.size() - 1);
        }
        // val in set2
        if(s1.size() > 0 && s2.size() < (arr.length + 1) / 2) {
            s2.add(val);
            solve(arr, indx + 1, s1, s2, sum1, sum2 + val);
            s2.remove(s2.size() - 1);
        }
	}

    // pattern matching
    public static void solution(String str, String pattern, HashMap<Character,String> map, String asf, int indx){
		if(indx == pattern.length()) {
            if(str.length() == 0)
                System.out.println(asf + ".");
            return;
        }

        char ch = pattern.charAt(indx);
        String mapping = map.get(ch);

        for(int i = 0; i < str.length(); i++) {
            String substr = str.substring(0, i + 1);
            String roq = str.substring(i + 1);

            // mapping
            map.put(ch, substr);
            if(mapping.length() > 0) {
                if(substr.equals(mapping) == true) {
                    solution(roq, pattern, map, asf, indx + 1);
                }
            } else {
                solution(roq, pattern, map, asf + ch + " -> " + substr + ", ", indx + 1);
            }
            // reset mapping
            map.put(ch, mapping);
        }
	}

    // word break
    public static void wordBreak(String str, String ans, HashSet<String> set){
		if(str.length() == 0) {
            System.out.println(ans);
            return;
        }

        for(int i = 0; i < str.length(); i++) {
            String word = str.substring(0, i + 1);
            String roq = str.substring(i + 1);
            if(set.contains(word)) {
                wordBreak(roq, ans + word + " ", set);
            }
        }
	}

    // remove invalid parenthesis
    public static void solution(String str, int minRemoval, HashSet<String> ans) {
		if(minRemoval == 0) {
            if(getMin(str) == 0 && ans.contains(str) == false) {
                ans.add(str);
                System.out.println(str);
            }
            return;
        }

        for(int i = 0; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i + 1);
            solution(left + right, minRemoval - 1, ans);
        }
	}

	public static int getMin(String str){
		Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(') {
                st.push(ch);
            } else {
                if(st.size() > 0 && st.peek() == '(') {
                    st.pop();
                } else {
                    st.push(ch);
                }
            }
        }
		return st.size();
	}

    public static void fun() {

    }

    public static void main(String[] args) {
        fun();
    }
}
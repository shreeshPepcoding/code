import java.util.*;

public class recursion {

    // ci-> current item, ti-> total items
    public static void permutations(int[] boxes, int ci, int ti){
        if(ci > ti) {
            // print the result
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }

        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // place
                boxes[b] = ci;
                permutations(boxes, ci + 1, ti);
                // unplace
                boxes[b] = 0;
            }
        }
    }

    // cb -> current box, tb-> total box, ssf-> selected so far, ts->total selection, asf->answer so far
    public static void combinations(int cb, int tb, int ssf, int ts, String asf){
        if(cb > tb) {
            if(ssf == ts) {
                // print
                System.out.println(asf);
            }
            return;
        }
        // yes call
        if(ssf < ts)
            combinations(cb + 1, tb, ssf + 1, ts, asf + "i");
        // no call
        combinations(cb + 1, tb, ssf, ts, asf + "-");
    }

    public static void permutations(int cb, int tb, int[] items, int ssf, int ts, String asf){
        if(cb > tb) {
            // print
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }

        // yes call
        for(int i = 0; i < items.length; i++) {
            if(items[i] == 0) {
                // place
                items[i] = 1;
                permutations(cb + 1, tb, items, ssf + 1, ts, asf + (i + 1));
                // unplace
                items[i] = 0;
            }
        }
        // no call
        permutations(cb + 1, tb, items, ssf, ts, asf + "-");
    }

    // lb -> last box
    public static void combinations(int[] boxes, int ci, int ti, int lb){
        if(ci > ti) {
            for(int i = 0; i < boxes.length; i++) {
                if(boxes[i] == 0) {
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
            boxes[b] = 1;
            combinations(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    // Queens Combinations - 2d As 2d - Box Chooses
    // qpsf -> queen placed so far, tq -> total queen, asf -> answer so far
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        if(row == tq) {
            // print result
            if(qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }
        
        if(col + 1 < tq) {
            // in same row, column will increase
            // yes
            queensCombinations(qpsf + 1, tq, row, col + 1, asf + "q");
            // no
            queensCombinations(qpsf, tq, row, col + 1, asf + "-");
        } else {
            // row will increase and column begin from 0
            // yes
            queensCombinations(qpsf + 1, tq, row + 1, 0, asf + "q\n");
            // no
            queensCombinations(qpsf, tq, row + 1, 0, asf + "-\n");
        }
    }

    // Queens Permutations - 2d As 2d - Queen Chooses
    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        if(qpsf == tq) {
            // print
            for(int i = 0; i < tq; i++) {
                for(int j = 0; j < tq; j++) {
                    if(chess[i][j] == 0) {
                        // empty box
                        System.out.print("-\t");
                    } else {
                        // occupied box
                        System.out.print("q" + chess[i][j] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < chess.length; i++) {
            for(int j = 0; j < chess[0].length; j++) {
                if(chess[i][j] == 0) {
                    // place
                    chess[i][j] = qpsf + 1;
                    queensPermutations(qpsf + 1, tq, chess);
                    // unplace
                    chess[i][j] = 0;
                }
            }
        }
    }

    // Queens Permutations - 2d As 2d - Box Chooses
    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        if(row == tq) {
            if(qpsf == tq) {
                System.out.println(asf);
                System.out.println();
            }
            return;
        }
        if(col + 1 < tq) {
            // yes call -> with loop of items
            for(int q = 0; q < queens.length; q++) {
                if(queens[q] == false) {
                    // place
                    queens[q] = true;
                    queensPermutations(qpsf + 1, tq, row, col + 1, asf + "q" + (q  + 1) + "\t", queens);
                    // unplace
                    queens[q] = false;
                }
            }

            // no call
            queensPermutations(qpsf, tq, row, col + 1, asf + "-\t", queens);
        } else {
            // yes call -> with loop of items
            for(int q = 0; q < queens.length; q++) {
                if(queens[q] == false) {
                    // place
                    queens[q] = true;
                    queensPermutations(qpsf + 1, tq, row + 1, 0, asf + "q" + (q + 1) + "\n", queens);
                    // unplace
                    queens[q] = false;
                }
            }

            // no call
            queensPermutations(qpsf, tq, row + 1, 0, asf + "-\n", queens);
        }
    }

    // Queens Combinations - 2d As 2d - Queen Chooses
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int row, int col){
        if(qpsf == tq) {
            for(int i = 0; i < tq; i++) {
                for(int j = 0; j < tq; j++) {
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
        
        // part1
        for(int r = row, c = col + 1; c < tq; c++) {
            // mark
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, r, c);
            // unmark
            chess[r][c] = false;
        }

        // part2
        for(int r = row + 1; r < tq; r++) {
            for(int c = 0; c < tq; c++) {
                // mark
                chess[r][c] = true;
                queensCombinations(qpsf + 1, tq, chess, r, c);
                // unmark
                chess[r][c] = false;
            }
        }
    }

    // Queens Combinations - 2d As 1d - Queen Chooses
    // lcno -> last cell number used
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        if(qpsf == tq) {
            for(int i = 0; i < tq; i++) {
                for(int j = 0; j < tq; j++) {
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

        for(int box = lcno + 1; box < chess.length * chess[0].length; box++) {
            int r = box / chess[0].length;
            int c = box % chess[0].length;
            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, box);
            // unplace
            chess[r][c] = false;
        }
    }

    // Nqueens Combinations - 2d As 1d - Queen Chooses
    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        // direction 0, r--, c++
        for(int r = row, c = col; r >= 0 && c < chess.length; r--, c++) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 1, r--, c -> x
        for(int r = row, c = col; r >= 0; r--) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 2, r--, c--
        for(int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if(chess[r][c] == true) {
                return false;
            } 
        }
        // direction 3, r -> x, c--
        for(int r = row, c = col; c >= 0; c--) {
            if(chess[r][c] == true) {
                 return false;
            }
        }

        return true;
    }

    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (IsQueenSafe(chess, row, col)) {
                chess[row][col] = true;
                nqueens(qpsf + 1, tq, chess, row * chess.length + col);
                chess[row][col] = false;
            }
        }
    }

    // Nqueens Permutations - 2d As 1d - Queen Chooses
    public static boolean IsQueenSafe2(int[][] chess, int row, int col) {
        int[] xdir = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] ydir = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int rad = 0; rad <= chess.length; rad++) {
            for(int d = 0; d < 8; d++) {
                int r = row + (rad * xdir[d]);
                int c = col + (rad * ydir[d]);

                if(r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    if(chess[row][col] != 0) {
                        System.out.print("q" + chess[row][col] + "\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (IsQueenSafe2(chess, row, col)) {
                chess[row][col] = qpsf + 1;
                nqueens(qpsf + 1, tq, chess);
                chess[row][col] = 0;
            }
        }
    }

    // Permutations - Words - 1, cb-> current box, tb-> total box
    public static void generateWords(int cb, int tb, HashMap<Character, Integer> fmap, String asf) {
        if(cb > tb) {
            System.out.println(asf);
            return;
        }

        for(char ch : fmap.keySet()) {
            int freq = fmap.get(ch);
            if(freq > 0) {
                fmap.put(ch, freq - 1);
                generateWords(cb + 1, tb, fmap, asf + ch);
                fmap.put(ch, freq);
            }
        }
    }

    // Permutations - Words - 2, cci-> current character index
    public static void generateWords(int cci, String str, Character[] box, HashMap<Character, Integer> lastOccurence) {
        if(cci == str.length()) {
            for(char ch : box) {
                System.out.print(ch);
            }
            System.out.println();
            return;
        }

        char ch = str.charAt(cci);
        int li = lastOccurence.get(ch); // li -> last index
        
        for(int b = li + 1; b < box.length; b++) {
            if(box[b] == null) {
                // place character in box
                box[b] = ch;
                // set last box used in lastOccurrence
                lastOccurence.put(ch, b);

                generateWords(cci + 1, str, box, lastOccurence);

                // re-set last box used in lastOccurrence
                lastOccurence.put(ch, li);
                // unplace character in box
                box[b] = null;
            }
        }
    }

    // Words - K Selection - 1
    public static void combination(int i, String ustr, int ssf, int ts, String asf) {
        if(i == ustr.length()) {
            if(ssf == ts) {
                // print
                System.out.println(asf);
            }
            return;
        }

        char ch = ustr.charAt(i);
        // yes call
        if(ssf + 1 <= ts)
            combination(i + 1, ustr, ssf + 1, ts, asf + ch);
        // no call
        combination(i + 1, ustr, ssf, ts, asf);
    }

    // Words - K Selection - 2
    public static void combination(String ustr, int ssf, int ts, int li, String asf) {
        if(ssf == ts) {
            System.out.println(asf);
            return;
        }

        for(int i = li + 1; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            combination(ustr, ssf + 1, ts, i, asf + ch);
        }
    }

    // Words - K Length Words - 1
    public static void permutation(String ustr, int indx, int ssf, Character[] slots) {
        if(ssf == slots.length) {
            // print
            for(char ch : slots) {
                System.out.print(ch);
            }
            System.out.println();
            return;
        }
        if(indx == ustr.length()) return;
        
        char ch = ustr.charAt(indx);
        // yes call
        for(int s = 0; s < slots.length; s++) {
            if(slots[s] == null) {
                // place
                slots[s] = ch;
                permutation(ustr, indx + 1, ssf + 1, slots);
                // unplace
                slots[s] = null;
            }
        }
        // no call
        permutation(ustr, indx + 1, ssf, slots);
    }

    // Words - K Length Words - 2, cs-> current slot, ts-> total slot
    public static void permutation(String ustr, int cs, int ts, String asf, HashSet<Character> vis) {
        if(cs == ts) {
            System.out.println(asf);
            return;
        }
        for(int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            if(!vis.contains(ch)) {
                // add in visited
                vis.add(ch);
                permutation(ustr, cs + 1, ts, asf + ch, vis);
                // remove from visited
                vis.remove(ch);
            }
        }
    }

    // Words - K Selection - 3
    public static void words_K_Selection3(String ustr, int indx, HashMap<Character, Integer> fmap, String asf, int k) {
        if(asf.length() == k) {
            System.out.println(asf);
            return;
        }
        if(indx == ustr.length()) return;
        
        char ch = ustr.charAt(indx);
        int freq = fmap.get(ch);
        // yes call
        int len = asf.length();
        for(int i = freq; i > 0; i--) {
            if(len + i <= k) {
                String str = "";
                for(int j = 0; j < i; j++) {
                    str += ch;
                }
                words_K_Selection3(ustr, indx + 1, fmap, asf + str, k);
            }
        }
        // no call
        words_K_Selection3(ustr, indx + 1, fmap, asf, k);
    }

    // Words - K Selection - 4
    public static void words_K_Selection4(String ustr, int cs, int ts, String asf, int li,
        HashMap<Character, Integer> fmap) {
        if(cs == ts) {
            System.out.println(asf);
            return;
        }
        for(int i = li; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if(freq > 0) {
                // freq reduce
                fmap.put(ch, freq - 1);
                words_K_Selection4(ustr, cs + 1, ts, asf + ch, i, fmap);
                // freq regain
                fmap.put(ch, freq);
            }
        } 
    }

    // Words - K Length Words - 3
    public static void words_K_Length_Words3(String str, int indx, Character[] slots, 
        HashMap<Character, Integer> lastIndx, int ssf) {
        if(indx == str.length()) {
            if(ssf == slots.length) {
                for(char c : slots) {
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }
        
        char ch = str.charAt(indx);
        int li = lastIndx.get(ch);
        
        // yes call
        for(int s = li + 1; s < slots.length; s++) {
            if(slots[s] == null) {
                // place in slots
                slots[s] = ch;
                // update last index
                lastIndx.put(ch, s);
                words_K_Length_Words3(str, indx + 1, slots, lastIndx, ssf + 1);
                // reset l;;ast index 
                lastIndx.put(ch, li);
                // unplace in slot
                slots[s] = null;
            }
        }
        // no call
        if(li == -1) {
            words_K_Length_Words3(str, indx + 1, slots, lastIndx, ssf);
        }
    }

    // Words - K Length Words - 4
    public static void words_K_Length_Words4(String ustr, int cs, int ts, String asf, HashMap<Character, Integer> fmap) {
        if(cs == ts) {
            System.out.println(asf);
            return;
        }
        
        for(int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if(freq > 0) {
                // reduce freq
                fmap.put(ch, freq - 1);
                words_K_Length_Words4(ustr, cs + 1, ts, asf + ch, fmap);
                // gain freq
                fmap.put(ch, freq);
            }
        }
    }

    // Coin Change - Combinations - 1
    // amtsf -> amount so fat, tamt -> total amount
    public static void coinChange1(int indx, int[] coins, int amtsf, int tamt, String asf){
        if(indx == coins.length) {
            if(amtsf == tamt) {
                System.out.println(asf + ".");
            }
            return;
        }
        
        int coin = coins[indx];

        // yes call
        if(amtsf + coin <= tamt) {
            coinChange1(indx + 1, coins, amtsf + coin, tamt, asf + coin + "-");
        }
        // no call
        coinChange1(indx + 1, coins, amtsf, tamt, asf);
    }

    // Coin Change - Combinations - 2
    public static void coinChange2(int indx, int[] coins, int amtsf, int tamt, String asf) {
        if(amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }
        if(indx == coins.length) {
            return;
        }
        
        int coin = coins[indx];
        // yes call
        if(amtsf + coin <= tamt) {
            coinChange2(indx, coins, amtsf + coin, tamt, asf + coin + "-");
        }
        // no call
        coinChange2(indx + 1, coins, amtsf, tamt, asf);
    }

    // Coin Change - Permutations - 1
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf, boolean[] used){
        if(amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if(used[i] == false && coin + amtsf <= tamt) {
                used[i] = true;
                coinChange(coins, amtsf + coin, tamt, asf + coin + "-", used);
                used[i] = false;
            }
        }
    }

    // Coin Change - Permutations - 2
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf) {
        if(amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        for(int coin : coins) {
            if(coin + amtsf <= tamt) {
                coinChange(coins, amtsf + coin, tamt, asf + coin + "-");
            }
        }
    }

    // Abbreviation Using Backtracking
    public static void solution(String str, String asf,int count, int indx){
        if(indx == str.length()) {
            String res = asf + (count != 0 ? count : "");
            System.out.println(res);
            return;
        }

        char ch = str.charAt(indx);
        // yes call
        solution(str, asf + (count != 0 ? count : "") + ch , 0, indx + 1);
        // no call
        solution(str, asf, count + 1, indx + 1);
    }

    // max score
    public static int solution(String[] words, int[] farr, int[] score, int indx) {
        if(indx == words.length) return 0;
        String word = words[indx];
        // yes call
        // reduce freq and make sure that remaining freq greater than equal to 0 and also count score
        int myscore = 0;
        boolean flag = true;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int charIndx = (int)(ch - 'a');
            farr[charIndx]--;
            if(farr[charIndx] < 0) {
                flag = false; // we can't make yes call for current word
            }
            myscore += score[charIndx];
        }

        int yes_score = 0;
        if(flag == true) {
            yes_score = myscore + solution(words, farr, score, indx + 1);
        }

        // increase freq
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int charIndx = (int)(ch - 'a');
            farr[charIndx]++;
        }

        // no call
        int no_score = solution(words, farr, score, indx + 1);

        return Math.max(yes_score, no_score);
	}

    // N Queens Branch and Bound
    static boolean[] cols;
    static boolean[] normal_diagonal;
    static boolean[] reverse_diagonal;

    public static boolean isQueenSafeB_B(int r, int c) {
        if(cols[c] == true) return false;
        if(normal_diagonal[r + c] == true) return false;
        if(reverse_diagonal[r - c + cols.length - 1] == true) return false;
        return true;
    }

    public static void nQueensB_B(int n, int row, String asf) {
        if(row == n) {
            System.out.println(asf);
            return;
        }

        for(int col = 0; col < n; col++) {
            if(isQueenSafeB_B(row, col)) {
                // mark
                cols[col] = true;
                normal_diagonal[row + col] = true;
                reverse_diagonal[row - col + n - 1] = true;
                nQueensB_B(n, row + 1, asf + row + "-" + col + ", "); 
                // unmark
                cols[col] = false;
                normal_diagonal[row + col] = false;
                reverse_diagonal[row - col + n - 1] = false;
            }
        }
    }

    // josephus problem
    public static int solution(int n, int k){
        if(n == 1) return 0;
        return (solution(n - 1, k) + k) % n; // y = (x + k) % n, x => solution(n-1, k)
    }

    // lexicographical numbers
    public static void printOrder(int i, int n) {
        if(i > n) return;
        System.out.println(i);
        for(int j = 0; j < 10; j++) {
            printOrder(i * 10 + j, n);
        }
    }

    // goldmine 2
    static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public static int goldmine(int[][] arr, int i, int j) {
        // mark
        int profit = arr[i][j];
        arr[i][j]  *= -1;
        // travel on unvisited neighbour
        for(int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && r < arr.length && c >= 0 && c < arr[0].length && arr[r][c] > 0) {
                profit += goldmine(arr, r, c);
            }
        }
        return profit;
    }

    static int max = 0;
	public static void getMaxGold(int[][] arr){
		for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                if(arr[i][j] > 0) {
                    max = Math.max(max, goldmine(arr, i, j));
                }
            }
        }
	}

    // solve sudoku
    public static boolean isSafeInSudoku(int[][] board, int row, int col, int num) {
        // row check
        for(int r = row, c = 0; c < board[0].length; c++) {
            if(board[r][c] == num) return false;
        }
        // col check
        for(int r = 0, c = col; r < board.length; r++) {
            if(board[r][c] == num) return false;
        }
        // submatrix check
        // starting point of matrix
        int rr = row - row % 3;
        int cc = col - col % 3;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                if(board[r + rr][c + cc] == num) return false;
            }
        }
        return true;
    }
    
    public static void display(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
            System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solveSudoku(int[][] board, int i, int j) {
        if(j >= board[0].length) {
            j = 0;
            i++;
        }
        if(i == board.length) {
            display(board);
            return;
        }
        if(board[i][j] != 0) {
            // continue on next level
            solveSudoku(board, i, j + 1);
        } else {
            // provide option of every digit
            for(int n = 1; n < 10; n++) {
                if(isSafeInSudoku(board, i, j, n)) {
                    board[i][j] = n;
                    solveSudoku(board, i, j + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    // cryptArithmatic
    public static int stringIntoIntFromMapping(String s, HashMap<Character, Integer> map) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            ans = ans * 10 + map.get(s.charAt(i));
        }
        return ans;
    }

    public static void solution(String unique, int indx, HashMap<Character, Integer> charIntMap, 
        boolean[] usedNumbers, String s1, String s2, String s3) {

        if(indx == unique.length()) {
            int n1 = stringIntoIntFromMapping(s1, charIntMap);
            int n2 = stringIntoIntFromMapping(s2, charIntMap);
            int n3 = stringIntoIntFromMapping(s3, charIntMap);

            if(n1 + n2 == n3) {
                // valid mapping
                // print mapping in sorted order
                for(int i = 0; i < 26; i++) {
                    char ch = (char)(i + 'a');
                    if(charIntMap.containsKey(ch)) {
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }        

        char ch = unique.charAt(indx);
        for(int digit = 0; digit < 10; digit++) {
            if(usedNumbers[digit] == false) {
                // use 
                usedNumbers[digit] = true;
                charIntMap.put(ch, digit);

                solution(unique, indx + 1, charIntMap, usedNumbers, s1, s2, s3);

                // make it available
                usedNumbers[digit] = false;
                charIntMap.put(ch, -1);
            }
        }
    }

    // crossword puzzle
    public static boolean canPlaceHorizontally(char[][] arr, int i, int j, String word) {
        int len = word.length();
        // 1. management of starting point
        if(j > 0 && arr[i][j - 1] == '-') return false;
        // 2. Availability of space for word
        if(j + len > arr[0].length) return false;
        // 3. perfectly fitting 
        if(j + len < arr[0].length && arr[i][j + len] == '-') return false;
        // 4. In available space all the cells are valid or not
        for(int k = 0; k < len; k++) {
            if(arr[i][j + k] != '-' && arr[i][j + k] != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeHorizontally(char[][] arr, int i, int j, String word) {
        int len = word.length();
        boolean[] info = new boolean[len];
        for(int k = 0; k < len; k++) {
            if(arr[i][j + k] == '-') {
                arr[i][j + k] = word.charAt(k);
                info[k] = true;
            }
        }
        return info;
    }

    public static void unplaceWordHorizontally(char[][] arr, int i, int j, boolean[] info) {
        for(int k = 0; k < info.length; k++) {
            if(info[k] == true) {
                arr[i][j + k] = '-';
            }
        }
    }

    public static boolean canPlaceVertically(char[][] arr, int i, int j, String word) {
        int len = word.length();
        // 1. management of starting point
        if(i > 0 && arr[i - 1][j] == '-') return false;
        // 2. Availability of space for word
        if(i + len > arr.length) return false;
        // 3. perfectly fitting 
        if(i + len < arr.length && arr[i + len][j] == '-') return false;
        // 4. In available space all the cells are valid or not
        for(int k = 0; k < len; k++) {
            if(arr[i + k][j] != '-' && arr[i + k][j] != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeVertically(char[][] arr, int i, int j, String word) {
        int len = word.length();
        boolean[] info = new boolean[len];
        for(int k = 0; k < len; k++) {
            if(arr[i + k][j] == '-') {
                arr[i + k][j] = word.charAt(k);
                info[k] = true;
            }
        }
        return info;
    }

    public static void unplaceWordVertically(char[][] arr, int i, int j, boolean[] info) {
        for(int k = 0; k < info.length; k++) {
            if(info[k] == true) {
                arr[i + k][j] = '-';
            }
        }
    }

    public static void print(char[][] arr){
		for(int i = 0 ; i < arr.length; i++){
			for(int j = 0 ; j < arr.length; j++){
				System.out.print(arr[i][j]);
			}
                  System.out.println();
		}
		
	}

    public static void solution(char[][] arr, String[] words, int indx){
        if(indx == words.length) {
            print(arr);
            return;
        }

		String word = words[indx];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                if(arr[i][j] == '-' || arr[i][j] == word.charAt(0)) {
                    // horizontal
                    if(canPlaceHorizontally(arr, i, j, word)) {
                        // place
                        boolean[] info = placeHorizontally(arr, i, j, word);
                        // call
                        solution(arr, words, indx + 1);
                        // unplace
                        unplaceWordHorizontally(arr, i, j, info);
                    }
                    // vertical
                    if(canPlaceVertically(arr, i, j, word)) {
                        // place
                        boolean[] info = placeVertically(arr, i, j, word);
                        // call
                        solution(arr, words, indx + 1);
                        // unplace
                        unplaceWordVertically(arr, i, j, info);
                    }
                }
            }
        }
	}

    // magnets
    public static int signRowCount(char[][] arr, char sign, int r) {
        int count = 0;
        for(int c = 0; c < arr[0].length; c++) {
            if(arr[r][c] == sign) 
                count++;
        }
        return count;
    }

    public static int signColCount(char[][] arr, char sign, int c) {
        int count = 0;
        for(int r = 0; r < arr.length; r++) {
            if(arr[r][c] == sign) 
                count++;
        }
        return count;
    }

    public static boolean isSafeToPlace(char[][] ans, int i, int j, char sign, int[] top, int[] left, int[] right, int[] bottom) {
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // 1. polarity check
        for(int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && r < ans.length && c >= 0 && c < ans[0].length && ans[r][c] == sign) {
                return false;
            }
        }
        // 2. valid count of sign
        int rc = signRowCount(ans, sign, i);
        int cc = signColCount(ans, sign, j);

        if(sign == '+') {
            if((top[j] != -1 && cc >= top[j]) || (left[i] != -1 && rc >= left[i])) return false;
        } else {
            if((bottom[j] != -1 && cc >= bottom[j]) || (right[i] != -1 && rc >= right[i])) return false;
        }
        return true;
    }

    public static boolean isFinalSafe(char[][] ans, int[] top, int[] left, int[] right, int[] bottom) {
        // col
        for(int c = 0; c < ans[0].length; c++) {
            int pcc = signColCount(ans, '+', c); // positive column count
            int ncc = signColCount(ans, '-', c); // negative column count

            if((top[c] != -1 && top[c] != pcc )|| (bottom[c] != -1 && bottom[c] != ncc))
                return false;
        }
        // row
        for(int r = 0; r < ans.length; r++) {
            int prc = signRowCount(ans, '+', r); // positive Row count
            int nrc = signRowCount(ans, '-', r); // negative Row count

            if((left[r] != -1 && left[r] != prc) || (right[r] != -1 && right[r] != nrc))
                return false;
        }
        return true;
    }

    public static boolean solution(char[][] arr, int[] top, int[] left, int[] right, 
        int[] bottom, char[][] ans, int i, int j) {
        if(j == arr[0].length) {
            i++;
            j = 0;
        }
        if(i == arr.length) {
            if(isFinalSafe(ans, top, left, right, bottom))
                return true;
            return false;
        }
        // yes call
        if(arr[i][j] == 'L') {
            // L|R -> |+|-|
            if(isSafeToPlace(ans, i, j, '+', top, left, right, bottom) && 
            isSafeToPlace(ans, i, j + 1, '-', top, left, right, bottom)) {
                // place
                ans[i][j] = '+';
                ans[i][j + 1] = '-';
                if(solution(arr, top, left, right, bottom, ans, i, j + 2))
                    return true;
                // unplace
                ans[i][j] = 'X';
                ans[i][j + 1] = 'X';
            }
            // L|R -> |-|+|
            if(isSafeToPlace(ans, i, j, '-', top, left, right, bottom) && 
            isSafeToPlace(ans, i, j + 1, '+', top, left, right, bottom)) {
                // place
                ans[i][j] = '-';
                ans[i][j + 1] = '+';
                if(solution(arr, top, left, right, bottom, ans, i, j + 2))
                    return true;
                // unplace
                ans[i][j] = 'X';
                ans[i][j + 1] = 'X';
            }
        } else if(arr[i][j] == 'T') {
            // T|B -> |+|-|
            if(isSafeToPlace(ans, i, j, '+', top, left, right, bottom) && 
            isSafeToPlace(ans, i + 1, j, '-', top, left, right, bottom)) {
                // place
                ans[i][j] = '+';
                ans[i + 1][j] = '-';
                if(solution(arr, top, left, right, bottom, ans, i, j + 1))
                    return true;
                // unplace
                ans[i][j] = 'X';
                ans[i + 1][j] = 'X';
            }
            // T|B -> |-|+|
            if(isSafeToPlace(ans, i, j, '-', top, left, right, bottom) && 
            isSafeToPlace(ans, i + 1, j, '+', top, left, right, bottom)) {
                // place
                ans[i][j] = '-';
                ans[i + 1][j] = '+';
                if(solution(arr, top, left, right, bottom, ans, i, j + 1))
                    return true;
                // unplace
                ans[i][j] = 'X';
                ans[i + 1][j] = 'X';
            }
        }
        // no call
        if(solution(arr, top, left, right, bottom, ans, i, j + 1))
            return true;
		return false;
	}

    // friends pairing 2
    public static void solution(int i, int n, boolean[] used, String asf) {
        if(i > n) {
            System.out.println(counter + "." + asf);
            counter++;
            return;
        }
        
        if(used[i] == true) {
            // already engaged in previous level, then it have no choice
            solution(i + 1, n, used, asf);
        } else {
            // single choice
            used[i] = true;
            solution(i + 1, n, used, asf + "(" + i + ") ");
            // no unmark here -> i is also engaged in pairing
            // pair up choice
            for(int j = i + 1; j <= n; j++) {
                if(used[j] == false) {
                    used[j] = true;
                    solution(i + 1, n, used, asf + "(" + i + "," + j + ") ");
                    used[j] = false;
                }
            }
            used[i] = false;
        }
    }

    // K-Partition
    static int count = 1;
    public static void solution(int i, int n, int k, int ssf, ArrayList<ArrayList<Integer>> ans) {
        if(i > n) {
            if(ssf == k) {
                System.out.print(count + ". ");
                for(ArrayList<Integer> list : ans) {
                    System.out.print(list + " ");
                }
                System.out.println();
                count++;
            }
            return;
        }
        // add in existing set -> ssf help us to find already existed set
        int li = 0; // list index
        for(li = 0; li < ssf; li++) {
            ArrayList<Integer> list = ans.get(li);
            list.add(i);
            solution(i + 1, n, k, ssf, ans);
            list.remove(list.size() - 1);
        }
        // self starting
        if(li < k) {
            ArrayList<Integer> list = ans.get(li);
            list.add(i);
            solution(i + 1, n, k, ssf + 1, ans);
            list.remove(list.size() - 1);
        }
	}

    // print all palindromic permutation
    public static String reverseString(String asf) {
        String res = "";
        for(int i = asf.length() - 1; i >= 0; i--) {
            res += asf.charAt(i);
        }
        return res;
    }

    public static void generatepw(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc, String asf) {
        if(cs == ts) {
            String res = asf + (oddc == null ? "" : oddc) + reverseString(asf);
            System.out.println(res);
            return;
        }

        for(char ch : fmap.keySet()) {
            int freq = fmap.get(ch);
            if(freq > 0) {
                fmap.put(ch, freq - 1);
                generatepw(cs + 1, ts, fmap, oddc, asf + ch);
                fmap.put(ch, freq);
            }
        }
	}

    // all palindromic partition
    public static boolean isPalindromic(String str) {
        int left = 0;
        int right = str.length() - 1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) return false;

            left++;
            right--;
        }
        return true;
    }

    public static void solution(String str, String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }

        for(int i = 1; i <= str.length(); i++) {
            String substr = str.substring(0, i);
            String ros = str.substring(i);
            if(isPalindromic(substr)) {
                solution(ros, asf + "(" + substr + ") ");
            }
        }
	}

    // K Subset with equal sum
    public static void solution(int[] arr, int indx, int sum, int k, int[] subsetSum,int ssf,
        ArrayList<ArrayList<Integer>> ans) {
        
        if(indx == arr.length) {
            if(ssf == k) {
                boolean flag = true;
                for(int val : subsetSum) {
                    if(val != sum / k) {
                        flag = false;
                        break;
                    }
                }
                if(flag == true) {
                    for(ArrayList<Integer> list : ans) {
                        System.out.print(list + " ");
                    }
                    System.out.println();
                }
            }
            return;
        }
        int val = arr[indx];
        // merging with existing subset
        int li = 0;
        for(li = 0; li < ssf; li++) {
            ArrayList<Integer> list = ans.get(li);
            if(subsetSum[li] + val <= sum / k) {
                subsetSum[li] += val;
                list.add(val);
                solution(arr, indx + 1, sum, k, subsetSum, ssf, ans);
                list.remove(list.size() - 1);
                subsetSum[li] -= val;           
            }
        }
        if(li < k) {
            ArrayList<Integer> list = ans.get(li);
            subsetSum[li] += val;
            list.add(val);
            solution(arr, indx + 1, sum, k, subsetSum, ssf + 1, ans);
            list.remove(list.size() - 1);
            subsetSum[li] -= val; 
        }
	}

    // tug of war
    static int mindiff = Integer.MAX_VALUE;
	static String ans = "";

    public static void solve(int[] arr, int indx, ArrayList<Integer> set1, 
    ArrayList<Integer> set2, int sum1, int sum2) {
        if(indx == arr.length) {
            // valid candidate for minimum sum difference
            int diff = Math.abs(sum1 - sum2);
            if(diff < mindiff) {
                mindiff = diff;
                ans = set1 + " " + set2;
            }
            return;
        }

        int ele = arr[indx];
        // add in set1
        if(set1.size() + 1 <= (arr.length + 1) / 2) {
            set1.add(ele);
            solve(arr, indx + 1, set1, set2, sum1 + ele, sum2);
            set1.remove(set1.size() - 1);
        }

        // add in set2
        if(indx != 0 && set2.size() + 1 <= (arr.length + 1) / 2) {
            set2.add(ele);
            solve(arr, indx + 1, set1, set2, sum1, sum2 + ele);
            set2.remove(set2.size() - 1);
        }
	}

    // pattern matching
    public static void solution(String str, String pattern, int indx, HashMap<Character,String> map, String asf){
		if(indx == pattern.length()) {
            if(str.length() == 0)
                System.out.println(asf + ".");
            return;
        }
        char ch = pattern.charAt(indx);
        if(map.containsKey(ch) == true) {
            // already mapped with some string
            String mapping = map.get(ch);
            for(int i = 1; i <= str.length(); i++) {
                String fhalf = str.substring(0, i);
                String roq = str.substring(i);
                if(mapping.equals(fhalf) == true) {
                    solution(roq, pattern, indx + 1, map, asf);
                }
            }
        } else {
            // try all possibility
            for(int i = 1; i <= str.length(); i++) {
                String fhalf = str.substring(0, i);
                String roq = str.substring(i);

                map.put(ch, fhalf);
                solution(roq, pattern, indx + 1, map, asf + ch + " -> " + fhalf + ", ");
                map.remove(ch);
            }
        }
	}

    // word break
    public static void wordBreak(String str, String ans, HashSet<String> dict){
		if(str.length() == 0) {
            System.out.println(ans);
            return;
        }

        for(int i = 1; i <= str.length(); i++) {
            String half = str.substring(0, i);
            String roq = str.substring(i);
            if(dict.contains(half) == true) {
                wordBreak(roq, ans + half + " ", dict);
            }
        }
	}

    // remove invalid paremnmthesis
    public static void solution(String str, int minRemoval, HashSet<String> ans) {
        if(minRemoval == 0) {
            if(getMin(str) == 0 && ans.contains(str) == false) {
                ans.add(str);
                System.out.println(str);
            }
            return;
        }

        for(int i = 0; i < str.length(); i++) {
            String first = str.substring(0, i);
            String second = str.substring(i + 1);

            solution(first + second, minRemoval - 1, ans);
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
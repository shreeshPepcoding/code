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


    public static void fun() {
        combinations1(1, 4, 0, 2, "");
    }

    public static void main(String[] args) {
        fun();
    }

}

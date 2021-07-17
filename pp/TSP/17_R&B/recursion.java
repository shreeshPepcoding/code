
public class recursion {

    // ci-> current items. ti-> total items
    public static void permutations(int[] boxes, int ci, int ti){
        if(ci > ti) {
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }

        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // box is empty
                // place object
                boxes[b] = ci;
                permutations(boxes, ci + 1, ti);
                // unplace objects
                boxes[b] = 0;
            }
        }
    }

    // cb-> current box, tb-> total box, isf->item so far, ti->total item, asf-> answer so far
    public static void combinations(int cb, int tb, int isf, int ti, String asf){
        if(cb > tb) {
            if(isf == ti) {
                // print
                System.out.println(asf);
            }
            return;
        }
        
        // yes call
        if(isf + 1 <= ti)
            combinations(cb + 1, tb, isf + 1, ti, asf + "i");
        // no call
        combinations(cb + 1, tb, isf, ti, asf + "-");
    }

    // cb -> current box, tb-> total box, isf-> item so far, ti-> total item, asf-> asnwer so far
    public static void permutations(int cb, int tb, int[] items, int isf, int ti, String asf){
        if(cb > tb) {
            if(isf == ti) {
                // print
                System.out.println(asf);
            }
            return;
        }

        // yes call
        for(int i = 0; i < items.length && isf < ti; i++) {
            if(items[i] == 0) {
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
            // place ci item on bth box
            boxes[b] = ci;
            combinations(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    // qpsf->queen placed so far, tq -> total queen, asf-> answer so far
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        if(row == tq) {
            if(qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }
        if(col + 1 < tq) {
            // yes call
            if(qpsf < tq)
                queensCombinations(qpsf + 1, tq, row, col + 1, asf + "q");
            // no call
            queensCombinations(qpsf, tq, row, col + 1, asf + "-");
        } else {
            // yes call
            if(qpsf < tq)
                queensCombinations(qpsf + 1, tq, row + 1, 0, asf + "q\n");
            // no call
            queensCombinations(qpsf, tq, row + 1, 0, asf + "-\n");
        }
    }

    public static void queensPermutations(int qpsf, int tq, int[][] chess){
        if(qpsf == tq) {
            // print result
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

        for(int i = 0; i < chess.length; i++) {
            for(int j = 0; j < chess[0].length; j++) {
                if(chess[i][j] == 0) {
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
        if(row == tq) {
            if(qpsf == tq) {
                System.out.println(asf);
                System.out.println();
            }
            return;
        }
        
        // yes call
        for(int q = 0; q < queens.length; q++) {
            if(queens[q] == false) {
                // place the queen
                queens[q] = true;
                if(col + 1 < tq) {
                    queensPermutations(qpsf + 1, tq, row, col + 1, asf + "q" + (q + 1) + "\t", queens);
                } else {
                    queensPermutations(qpsf + 1, tq, row + 1, 0, asf + "q" + (q + 1) + "\n", queens);
                }
                // unplace queen
                queens[q] = false;
            }
        }
        // no call
        if(col + 1 < tq) {
            queensPermutations(qpsf, tq, row, col + 1, asf + "-\t", queens);
        } else {
            queensPermutations(qpsf, tq, row + 1, 0, asf + "-\n", queens);
        }
    }

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int i, int j){
        if(qpsf == tq) {
            for(int r = 0; r < chess.length; r++) {
                for(int c = 0; c < chess[0].length; c++) {
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

        // travel in remaining columns in current row
        for(int c = j + 1; c < chess[0].length; c++) {
            int r = i;
            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, r, c);
            // unplace
            chess[r][c] = false;
        }

        // travel in remaining rows and columns
        for(int r = i + 1; r < chess.length; r++) {
            for(int c = 0; c < chess[0].length; c++) {
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
        if(qpsf == tq) {
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess[0].length; j++) {
                    if(chess[i][j] == true) {
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

        for(int b = lcno + 1; b < chess.length * chess[0].length; b++) {
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
        for(int r = row, c = col; c >= 0; c--) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 1
        for(int r = row, c = col; c >= 0 && r >= 0; c--, r--) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 2
        for(int r = row, c = col; r >= 0; r--) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        // direction 3
        for(int r = row, c = col; c < chess[0].length && r >= 0; c++, r--) {
            if(chess[r][c] == true) {
                return false;
            }
        }
        return true;
    }

    // lcno -> last cell number
    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if(qpsf == tq) {
            for(int i = 0; i < chess.length; i++) {
                for(int j = 0; j < chess[0].length; j++) {
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

        for(int b = lcno + 1; b < chess.length * chess[0].length; b++) {
            int r = b / chess.length;
            int c = b % chess[0].length;
            if(IsQueenSafe(chess, r, c)) {
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
        for(int rad = 1; rad < chess.length; rad++) {
            for(int d = 0; d < 8; d++) {
                int r = row + rdir[d] * rad;
                int c = col + cdir[d] * rad;
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
        for(int b = 0; b < chess.length * chess[0].length; b++) {
            int r = b / chess.length;
            int c = b % chess.length;
            if(chess[r][c] == 0 && IsQueenSafe2(chess, r, c)) {
                // place
                chess[r][c] = qpsf + 1;
                nqueens(qpsf + 1, tq, chess);
                // unplace
                chess[r][c] = 0;
            }
        }
    }

    static int[] xdir = {-2, -1, -1, -2};
    static int[] ydir = {1, 2, -2, -1};
    public static boolean IsKnightSafe(boolean[][] chess, int i, int j) {
        for(int d = 0; d < 4; d++) {
            int r = i + xdir[d];
            int c = j + ydir[d];

            if(r >= 0 && r < chess.length && c >= 0 && c < chess[0].length && chess[r][c] == true) {
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



    public static void fun() {

    }

    public static void main(String[] args) {
        fun();
    }
}
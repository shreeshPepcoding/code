import java.util.*;

public class knightsTour {

    // public static int count = 1;

    public static void display(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static int[] rdir = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] cdir = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void knightTour(int[][] board, int r, int c, int step) {
        if(step == board.length * board.length) {
            // mark
            board[r][c] = step;
            // System.out.println("=====" + count + "=====") ;
            display(board);
            // System.out.println("=====" + count + "=====") ;
            // count++;
            System.out.println();
            // unmark
            board[r][c] = 0;
            return;
        }

        // mark step count
        board[r][c] = step;
        // make calls
        for(int d = 0; d < 8; d++) {
            int rr = r + rdir[d];
            int cc = c + cdir[d];

            if(rr >= 0 && rr < board.length && cc >= 0 && cc < board.length && board[rr][cc] == 0) {
                knightTour(board, rr, cc, step + 1);
            }
        }

        // unmark step count
        board[r][c] = 0;
    }

    public static void solve() {
        int n = 5;
        int r = 2;
        int c = 0;

        int[][] board = new int[n][n];

        knightTour(board, r , c, 1);
    }

    public static void main(String[] args) {
        solve();    
    }    
}

import java.util.*;

public class nqueens {
    
    public static boolean isSafePlace1(boolean[][] board, int r, int c) {
        // north

        // north east

        // east

        // south east

        // south

        // south west

        // west

        // north west

        return true;
    }

    public static boolean isSafePlace2(boolean[][] board, int r, int c) {
        // north
        for(int i = r; i >= 0; i--) {
            if(board[i][c] == true)
                return false;
        }
        // north east
        for(int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == true)
                return false;
        }

        // north west
        for(int i = r, j = c; i >= 0 &&  j < board[0].length; i--, j++) {
            if(board[i][j] == true)
                return false;
        }

        return true;
    }
    // level -> ow
    // options -> col
    public static void nqueen(boolean[][] board, int r, String asf) {
        if(r == board.length) {
            System.out.println(asf + ".");
            return;
        }

        for(int c = 0; c < board[0].length; c++) {
            if(isSafePlace2(board, r, c) == true) {
                // place it
                board[r][c] = true;
                nqueen(board, r + 1, asf + r + "_" + c + ", ");
                // unplace it
                board[r][c] = false;
            }
        }
    }

    public static void solve() {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        nqueen(board, 0, "");
    }

    public static void main(String[] args) {
        solve();
    }
}

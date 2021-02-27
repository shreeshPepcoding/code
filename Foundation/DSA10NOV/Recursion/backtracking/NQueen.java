public class NQueen {

    public static int[] xdir = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] ydir = {0, 1, 1, 1, 0, -1, -1, -1};

    public static boolean isValid(boolean[][] board, int x, int y) {
        for(int r = 1; r < board.length; r++) {
            for(int d = 0; d < 8; d++) {
                int rr = x + (xdir[d] * r);
                int cc = y + (ydir[d] * r);

                if(rr >= 0 && rr < board.length && cc >= 0 && cc < board.length && board[rr][cc] == true) {
                    return false; // unsafe
                }
            }
        }
        return true;
    }
    
    public static int nqueen(boolean[][] board, int qpsf, int tq, int lbo, String asf) {
        if(qpsf == tq) {
            System.out.println(asf + ".");
            return 1;
        }
        int count = 0;
        for(int b = lbo; b < board.length * board.length; b++) {
            int r = b / board[0].length;
            int c = b % board[0].length;
            
            if(board[r][c] == false && isValid(board, r, c) == true) {
                board[r][c] = true;
                count += nqueen(board, qpsf + 1, tq, b + 1, asf + r + "-" + c + ", ");
                board[r][c] = false;
            }
        }
        return count;
    }

    public static void solve() {
        int q = 4;
        boolean[][] chessBoard = new boolean[q][q];
        System.out.println(nqueen(chessBoard, 0, q, 0, ""));
    }

    public static void main(String[] args) {
        solve();        
    }
}

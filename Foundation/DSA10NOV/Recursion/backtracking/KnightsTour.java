public class KnightsTour {

    public static int[] xdir = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] ydir = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int knightTour(int[][] board, int r, int c, int kpsf) {
        if(kpsf == board.length * board.length) {
            board[r][c] = kpsf;
            displayBoard(board);
            board[r][c] = 0;
            return 1;
        }

        
        // marking
        board[r][c] = kpsf;

        int count = 0;
        for(int d = 0; d < 8; d++) {
            int x = r + xdir[d];
            int y = c + ydir[d];

            // valid && unvisited
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 0) {
                count += knightTour(board, x, y, kpsf + 1);
            }
        }


        // unmarking
        board[r][c] = 0;
        return count;
    }

    public static void solve() {
        int n = 5;
        int r = 2;
        int c = 0;

        int[][] board = new int[n][n];
        System.out.println(knightTour(board, r, c, 1));
    }   

    public static void main(String[] args) {
        solve();
    }
}

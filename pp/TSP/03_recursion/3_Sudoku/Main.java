import java.util.*;

public class Main {

    public static boolean isValid(char[][] board, int x, int y, int num) {
        // row
        for (int i = 0; i < board.length; i++)
            if (board[x][i] - '0' == num)
                return false;
        // col
        for (int j = 0; j < board.length; j++)
            if (board[j][y] - '0' == num)
                return false;
        // check in sub matrix

        x = (x / 3) * 3;
        y = (y / 3) * 3;
        for (int i = x; i < x + 3; i++)
            for (int j = y; j < y + 3; j++)
                if (board[i][j] - '0' == num)
                    return false;

        return true;
    }
    
    public static boolean solve(char[][] bd, int bn) {
        if(bn == bd.length * bd.length) {
            // print bd
            return true;
        }
        
        int r = bn / bd.length;
        int c = bn % bd.length;
        
        boolean res = false;
        
        if(bd[r][c] != '.') {
            res = solve(bd, bn + 1);
            if(res == true) return res;
            
            return false;
        }
        
        for(int i = 1; i <= 9; i++) {
            if(isValid(bd, r, c, i) == true) {
                bd[r][c] = (char)(i + '0');
                res = solve(bd, bn + 1);
                if(res == true) return res;
                bd[r][c] = '.';
            }
        }
        
        return false;
    }
    
    public static void solveSudoku(char[][] board) {
        solve(board, 0);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = 9;
        char[][] arr = new char[n][n];
        for(int i = 0; i < n; i++) {
            String str = scn.nextLine();
            for(int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        solveSudoku(arr);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
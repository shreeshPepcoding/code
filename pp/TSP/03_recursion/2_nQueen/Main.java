import java.util.*;

public class Main {

    public static List<List<String>> res;
    
    public static int[] rdir = {-1, -1, -1};
    public static int[] cdir = {-1, 0, 1};
    
    public static boolean isValidPoisition(char[][] bd, int x, int y) {
        int radius = bd.length;
        
        for(int r = 1; r < radius; r++) {
            for(int d = 0; d < 3; d++) {
                int rr = x + r * rdir[d];
                int cc = y + r * cdir[d];
                
                if(rr >= 0 && rr < bd.length && cc >= 0 && cc < bd.length && bd[rr][cc] == 'Q') {
                    return false;
                } 
            }
        }
        return true;
    }
    
    public static void solve(char[][] bd, int r) {
        if(r == bd.length) {
            List<String> bres = new ArrayList<>();
            for(int i = 0; i < bd.length; i++) {
                String str = "";
                for(int j = 0; j < bd.length; j++) {
                    str += bd[i][j];
                }
                bres.add(str);
            }
            res.add(bres);
            return;
        }
        
        for(int c = 0; c < bd.length; c++) {
            if(isValidPoisition(bd, r, c) == true) {
                bd[r][c] = 'Q';
                solve(bd, r + 1);
                bd[r][c] = '.';
            }
        }
    }
    
    public static List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] bd = new char[n][n];
        for(char[] arr : bd) {
            Arrays.fill(arr, '.');
        }
        solve(bd, 0);
        return res;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<List<String>> res = solveNQueens(n);
        System.out.println(res);
    }

}
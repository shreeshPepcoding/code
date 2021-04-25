import java.util.*;

public class recursion {

    public static int[] rdir = {-1, 0, 1, 0};
    public static int[] cdir = {0, -1, 0, 1};
    public static char[] chArr = {'t', 'l', 'd', 'r'};

    public static void floodfill(int[][] board, int sr, int sc, String asf) {
        if(sr == board.length - 1 && sc == board[0].length - 1) {
            System.out.println(asf);
            return;
        }

        // mark
        board[sr][sc] = 1;

        for(int d = 0; d < rdir.length; d++) {
            int rr = sr + rdir[d];
            int cc = sc + cdir[d];
            char dir = chArr[d];

            if(rr >= 0 && rr < board.length && cc >= 0 && cc < board[0].length && board[rr][cc] != 1) {
                floodfill(board, rr, cc, asf + dir);
            }
        }

        // unmarking
        board[sr][sc] = 0;
    }

    public static void printTargetSumSubset(int[] arr, int indx, int ssf, int targ, String asf) {
        if(indx == arr.length) {
            if(targ == ssf) {
                System.out.println(asf + ".");
            }
            return;
        }


        // yes call
        if(ssf + arr[indx] <= targ) {
            printTargetSumSubset(arr, indx + 1, ssf + arr[indx], targ, asf + arr[indx] + ", ");
        }
        // no call
        printTargetSumSubset(arr, indx + 1, ssf, targ, asf);
    }

    public static void ques() {
        int[] arr = {2, 1, 3, 0};
        int targ = 3;
        printTargetSumSubset(arr, 0, 0, targ, "");
 
        // int[][] board = {
        //     {0, 1, 0, 0, 0, 0},
        //     {0, 1, 0, 1, 1, 0},
        //     {0, 1, 0, 1, 1, 0},
        //     {0, 0, 0, 0, 0, 0},
        //     {1, 1, 0, 1, 1, 0},
        //     {1, 1, 0, 0, 0, 0}
        // };

        // floodfill(board, 0, 0, "");
        return;
    }

    public static void demoForNormalRadius() {
        // dummy matrix for figure out the radius concept
        int n = 5;
        int m = 4;


        int r = 0;
        int c = 0;

        int[] rdir = {-1, 0, 1, 0};
        int[] cdir = {0, 1, 0, -1};

        int radius = Math.max(n,m);
        // for radius 1
        for(int rad = 1; rad <= radius; rad++) {
            for(int dir = 0; dir < rdir.length; dir++) {
                int rr = r + (rad * rdir[dir]);
                int cc = c + (rad * cdir[dir]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + dir +" :->  row : " + rr + ", col : " + cc);
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for(int dir = 0; dir < rdir.length; dir++) {
            for(int rad = 1; rad <= radius; rad++) {
                int rr = r + (rad * rdir[dir]);
                int cc = c + (rad * cdir[dir]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + dir +" :->  row : " + rr + ", col : " + cc);
            }
        }
    }

    public static void demoForQueenTraversal() {
        int n = 10;
        int m = 10;

        int r = 0;
        int c = 0;

        int[][] dir = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
        };

        int radius = Math.max(n,m);
        // for radius 1
        for(int rad = 1; rad <= radius; rad++) {
            for(int d = 0; d < dir.length; d++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + d +" :->  row : " + rr + ", col : " + cc);
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for(int d = 0; d < dir.length; d++) {
            for(int rad = 1; rad <= radius; rad++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + d +" :->  row : " + rr + ", col : " + cc);
            }
        }
    }

    public static void demoForKnightTraversal() {
        int[] rdir = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] cdir = {1, 2, 2, 1, -1, -2, -2, -1};
        int r = 4;
        int c = 4;
        for(int d = 0; d < rdir.length; d++) {
            int rr = r + rdir[d];
            int cc = c + cdir[d];

            System.out.println("row : " + rr + ", col : " + cc);
        }
    }

    public static void demo() {
        // demoForNormalRadius();
        // demoForQueenTraversal();
        demoForKnightTraversal();
    }

    public static void main(String[] args) {
        ques();
        // demo();
    }

}
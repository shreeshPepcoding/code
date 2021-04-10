
import java.util.*;

public class revesionBack {

    public static void floodfill(int[][] maze, int sr, int sc, String asf) {
        if(sr == maze.length - 1 && sc == maze[0].length - 1 && maze[sr][sc] != 1) {
            // print answer
            // System.out.println(asf);
            return;
        }

        if(sr < 0 || sr >= maze.length || sc < 0 || sc >= maze[0].length || maze[sr][sc] == 1) {
            // invalid positions + hurdle
            return;
        }

        // mark
        maze[sr][sc] = 1;

        // calls (r, c)
        // top  (r - 1, c)
        // left (r, c - 1)
        // down (r + 1, c)
        // right (r, c + 1)
        
        floodfill(maze, sr - 1, sc, asf + "t");
        floodfill(maze, sr, sc - 1, asf + "l");
        floodfill(maze, sr + 1, sc, asf + "d");
        floodfill(maze, sr, sc + 1, asf + "r");

        // unmark
        maze[sr][sc] = 0;
    }


    public static int[] rdir = {-1, 0, 1, 0};
    public static int[] cdir = {0, -1, 0, 1};
    public static char[] chars = {'t', 'l', 'd', 'r'};

    public static void floodfill1(int[][] maze, int sr, int sc, String asf) {
        if(sr == maze.length - 1 && sc == maze[0].length - 1) {
            // print answer
            // System.out.println(asf);
            return;
        }

        // mark
        maze[sr][sc] = 1;

        for(int d = 0; d < 4; d++) {
            int r = sr + rdir[d];
            int c = sc + cdir[d];
            if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] != 1)
                floodfill1(maze, r, c, asf + chars[d]);
        }

        // unmark
        maze[sr][sc] = 0;
    }

    // asf -> answer so far, ssf -> sum so far, tar-> target
    public static void printTargetSumSubsets(int[] arr, int idx, String asf, int ssf, int tar) {
        if(idx == arr.length) {
            if(ssf == tar)
                System.out.println(asf + ".");
            return;
        }

        // yes call
        if(ssf + arr[idx] <= tar)
            printTargetSumSubsets(arr, idx + 1, asf + arr[idx] + ", ", ssf + arr[idx], tar);
        // no call
        printTargetSumSubsets(arr, idx + 1, asf, ssf, tar);
    }

    public static void backtrack() {
        int[] arr = {10, 20, 0, 30};
        int tar = 30;
        printTargetSumSubsets(arr, 0, "", 0, tar);



        // int[][] maze = {
        //     {0, 0, 0, 0, 0, 0, 1, 0},
        //     {0, 0, 0, 0, 0, 0, 1, 1},
        //     {0, 0, 0, 0, 0, 0, 0, 0},
        //     {0, 0, 1, 0, 1, 0, 0, 0},
        //     {0, 0, 0, 0, 0, 1, 0, 0},
        //     {0, 0, 1, 0, 0, 0, 0, 0},
        //     {0, 0, 0, 0, 0, 1, 0, 0},
        //     {0, 0, 1, 0, 0, 1, 0, 0}
        // };
        // long t1 = System.currentTimeMillis();
        // floodfill(maze, 0, 0, "");
        // long t2 = System.currentTimeMillis();

        // System.out.println("Method one required " + (t2 - t1) + " milli seconds to solve");

        // if(maze[0][0] == 1 || maze[maze.length - 1][maze[0].length - 1] == 1) {
        //     return;
        // }
        // t1 = System.currentTimeMillis();
        // floodfill1(maze, 0, 0, "");
        // t2 = System.currentTimeMillis();
        // System.out.println("Method two required " + (t2 - t1) + " milli seconds to solve");
    }


    public static void main(String[] args) {
        backtrack();
    }    
}

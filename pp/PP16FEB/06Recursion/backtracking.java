import java.util.*;

public class backtracking {

    public static void floodfill(int[][] maze, int sr, int sc, String asf) {
        // base case
        if(sr == maze.length - 1 && sc == maze[0].length - 1 && maze[sr][sc] != 1) {
            System.out.println(asf);
            return;
        }

        if(sr < 0 || sr >= maze.length || sc < 0 || sc >= maze[0].length || maze[sr][sc] == 1) {
            return;
        }

        // mark
        maze[sr][sc] = 1;

        // currently at (r,c)
        // top -> r - 1, c
        // left -> r, c - 1
        // down -> r + 1, c
        // right -> r, c + 1
        floodfill(maze, sr - 1, sc, asf + "t");
        floodfill(maze, sr, sc - 1, asf + "l");
        floodfill(maze, sr + 1, sc, asf + "d");
        floodfill(maze, sr, sc + 1, asf + "r");

        // unmark
        maze[sr][sc] = 0;
    }

    public static void backtrack() {
        int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 0, 0, 0, 0}
        };

        floodfill(maze, 0, 0, "");
    }

    public static void main(String[] args) {
        backtrack();
    }
}

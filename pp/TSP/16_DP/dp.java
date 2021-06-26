import java.util.*;

public class dp {

    // ~~~~~~~~~~~~~~~Fibbonacci~~~~~~~~~~~~
    public static int fib_rec(int n) {
        if (n == 0 || n == 1)
            return n;

        int fibnm1 = fib_rec(n - 1);
        int fibnm2 = fib_rec(n - 2);

        int fibn = fibnm1 + fibnm2;

        return fibn;
    }

    public static int fib_memo(int n, int[] dp) {
        if (n == 0 || n == 1) {
            return dp[n] = n;
        }
        // 1. if Problem is already solved, then return the answer
        if (dp[n] != 0) {
            return dp[n];
        }

        int fibnm1 = fib_memo(n - 1, dp);
        int fibnm2 = fib_memo(n - 2, dp);

        int fibn = fibnm1 + fibnm2;
        // 2. If not solved, then solve the problem and store it in the dp
        return dp[n] = fibn;
    }

    public static int fib_tab2(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0 || n == 1) {
                dp[n] = n;
                continue;
            }

            int fibnm1 = dp[n - 1];// fib_memo(n - 1, dp);
            int fibnm2 = dp[n - 2]; // fib_memo(n - 2, dp);

            int fibn = fibnm1 + fibnm2;
            // 2. If not solved, then solve the problem and store it in the dp
            dp[n] = fibn;
        }
        return dp[N];
    }

    public static int fib_tab(int n, int[] dp) {
        // 1. figure out repitition -> repitition on N
        // 2. make a storage of size -> n + 1
        // 3. assign meaning to cell -> dp[i] = fib[i] = fib[i - 1] + fib[i - 2]
        // 4. smallest problem is at n = 0
        // 5. pre requisite -> base case

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int fib_btr(int n) {
        int a = 0;
        int b = 1;

        while (n-- > 0) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void fib() {
        int n = 10;
        // int res = fib_rec(n);
        int[] dp = new int[n + 1];
        // int res = fib_memo(n, dp);
        // int res = fib_tab(n, dp);
        int res = fib_btr(n);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~Climb Stairs~~~~~~~~~~~~~
    public static int climbStair_rec(int n) {
        if (n == 0) {
            return 1;
        }

        int count = 0;
        for (int j = 1; j <= 3; j++) {
            if (n - j >= 0) {
                count += climbStair_rec(n - j);
            }
        }
        return count;
    }

    public static int climbStair_memo(int n, int[] dp) {
        if (n == 0) {
            return dp[0] = 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int count = 0;
        for (int j = 1; j <= 3; j++) {
            if (n - j >= 0) {
                count += climbStair_memo(n - j, dp);
            }
        }
        return dp[n] = count;
    }

    // purely tabulation
    public static int climbStair_tab1(int n, int[] dp) {
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (i >= 3) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            } else if (i >= 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }

    // convert code from memoisation to tabulation
    public static int climbStair_tab2(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[0] = 1;
                continue;
            }

            int count = 0;
            for (int j = 1; j <= 3; j++) {
                if (n - j >= 0) {
                    count += dp[n - j]; // climbStair_memo(n - j, dp);
                }
            }
            dp[n] = count;
        }
        return dp[N];
    }

    // i-> current stair, n-> total stair, jumps[] -> jumps allowed at ith stair
    public static int climbStair_VarJumps_rec(int i, int n, int[] jumps) {
        if (i == n) {
            return 1;
        }
        int count = 0;
        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbStair_VarJumps_rec(i + jump, n, jumps);
        }
        return count;
    }

    public static int climbStair_VarJumps_memo(int i, int n, int[] jumps, int[] dp) {
        if (i == n) {
            return dp[i] = 1;
        }

        if (dp[i] != 0) {
            return dp[i];
        }

        int count = 0;
        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbStair_VarJumps_memo(i + jump, n, jumps, dp);
        }
        return dp[i] = count;
    }

    public static int climbStair_VarJumps_tab(int i, int n, int[] dp, int[] jumps) {
        for (i = n; i >= 0; i--) {
            if (i == n) {
                dp[i] = 1;
                continue;
            }

            int count = 0;
            for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
                count += dp[i + jump]; // climbStair_VarJumps_memo(i + jump, n, jumps, dp);
            }
            dp[i] = count;
        }
        return dp[0];
    }

    public static int climbStair_minJumps_rec(int i, int n, int[] jumps) {
        if (i == n) {
            return 0;
        }

        int minJumps = (int) 1e9;

        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            minJumps = Math.min(minJumps, climbStair_minJumps_rec(i + jump, n, jumps));
        }

        return minJumps == 1e9 ? minJumps : minJumps + 1;
    }

    public static int climbStair_minJumps_memo(int i, int n, int[] jumps, int[] dp) {
        if (i == n) {
            return dp[i] = 0;
        }

        if (dp[i] != 0)
            return dp[i];

        int minJumps = (int) 1e9;

        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            minJumps = Math.min(minJumps, climbStair_minJumps_memo(i + jump, n, jumps, dp));
        }

        return minJumps == 1e9 ? (dp[i] = minJumps) : (dp[i] = minJumps + 1);
    }

    public static int climbStair_minJumps_tab(int i, int n, int[] jumps, int[] dp) {
        for (i = n; i >= 0; i--) {
            if (i == n) {
                dp[i] = 0;
                continue;
            }

            int minJumps = (int) 1e9;

            for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
                minJumps = Math.min(minJumps, dp[i + jump]); // climbStair_minJumps_memo(i + jump, n, jumps, dp));
            }

            if (minJumps == 1e9) {
                dp[i] = (int) 1e9;
            } else {
                dp[i] = minJumps + 1;
            }
        }
        return dp[0];
    }

    public static void climbStair() {
        int n = 4;
        // int res = climbStair_rec(n);
        int[] dp = new int[n + 1];
        int res = climbStair_memo(n, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~Min Cost Path~~~~~~~~~~~
    public static int minCostPath_rec(int[][] maze, int x, int y) {

        if (x == maze.length - 1 && y == maze[0].length - 1) {
            return maze[x][y];
        }

        int minCost = (int) 1e9;
        // right call
        if (y + 1 < maze[0].length) {
            minCost = Math.min(minCost, minCostPath_rec(maze, x, y + 1));
        }
        // down call
        if (x + 1 < maze.length) {
            minCost = Math.min(minCost, minCostPath_rec(maze, x + 1, y));
        }

        return minCost + maze[x][y];
    }

    public static int minCostPath_memo(int[][] maze, int x, int y, int[][] dp) {
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            return dp[x][y] = maze[x][y];
        }

        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        int minCost = (int) 1e9;
        // right call
        if (y + 1 < maze[0].length) {
            minCost = Math.min(minCost, minCostPath_memo(maze, x, y + 1, dp));
        }
        // down call
        if (x + 1 < maze.length) {
            minCost = Math.min(minCost, minCostPath_memo(maze, x + 1, y, dp));
        }

        return dp[x][y] = minCost + maze[x][y];
    }

    public static int minCostPath_tab1(int[][] maze, int x, int y, int[][] dp) {
        for (x = maze.length - 1; x >= 0; x--) {
            for (y = maze[0].length - 1; y >= 0; y--) {
                if (x == maze.length - 1 && y == maze[0].length - 1) {
                    // bottom right corner
                    dp[x][y] = maze[x][y];
                } else if (x == maze.length - 1) {
                    // last row
                    dp[x][y] = maze[x][y] + dp[x][y + 1];
                } else if (y == maze[0].length - 1) {
                    // last col
                    dp[x][y] = maze[x][y] + dp[x + 1][y];
                } else {
                    // middle section
                    dp[x][y] = maze[x][y] + Math.min(dp[x][y + 1], dp[x + 1][y]);
                }
            }
        }
        return dp[0][0];
    }

    public static int minCostPath_tab2(int[][] maze, int x, int y, int[][] dp) {
        for(x = maze.length - 1; x >= 0; x--) {
            for(y = maze[0].length - 1; y >= 0; y--) {
                if(x == maze.length - 1 && y == maze[0].length - 1) {
                    dp[x][y] = maze[x][y];
                    continue;
                }
        
                int minCost = (int)1e9;
                // right call
                if(y + 1 < maze[0].length) {
                    minCost = Math.min(minCost, dp[x][y + 1]);
                }
                // down call
                if(x + 1 < maze.length) {
                    minCost = Math.min(minCost, dp[x + 1][y]);
                }
        
                dp[x][y] = minCost + maze[x][y];
            }
        } 
        return dp[0][0];
    }

    public static void minCostPath(int[][] dp, int x, int y, String psf) {
        if(x == dp.length -1 && y == dp[0].length - 1) {
            System.out.println(psf);
        } else if(x == dp.length - 1) {
            minCostPath(dp, x, y + 1, psf + "R ");
        } else if(y == dp[0].length - 1) {
            minCostPath(dp, x + 1, y, psf + "D ");
        } else {
            if(dp[x][y + 1] == dp[x + 1][y]) {
                // both side
                minCostPath(dp, x + 1, y, psf + "D ");
                minCostPath(dp, x, y + 1, psf + "R ");
            } else if(dp[x][y + 1] < dp[x + 1][y]) {
                // right side
                minCostPath(dp, x, y + 1, psf + "R ");
            } else {
                // down side
                minCostPath(dp, x + 1, y, psf + "D ");
            }
        }
    }

    public static void mazePath() {
        int[][] maze = {
            {0, 1, 4, 2, 8, 2},
            {4, 3, 6, 5, 0, 4},
            {1, 2, 4, 1, 4, 6},
            {2, 0, 7, 3, 2, 2},
            {3, 1, 5, 9, 2, 4},
            {2, 7, 0, 8, 5, 1}
        };

        int[][] dp = new int[6][6];
        minCostPath_tab1(maze, 0, 0, dp);

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        minCostPath(dp, 0, 0, "");
    }

    // ~~~~~~~~~~~~~~~Goldmine~~~~~~~~~~~~~~
    


    public static void goldmine() {

    }

    public static void ques() {
        goldmine();
        // mazePath();
        // climbStair();
        // fib();
    }

    public static void main(String[] args) {
        ques();
    }
}
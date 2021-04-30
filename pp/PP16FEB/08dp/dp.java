import java.util.*;

public class dp {

    // ~~~~~~~~~~~~~~~~~~~~~Fibonacci Problem~~~~~~~~~~~~~~~~~~~~~~~~
    public static int fib_rec(int n) {
        if(n == 0 || n == 1) return n;

        int fnm1 = fib_rec(n - 1);
        int fnm2 = fib_rec(n - 2);
        int fibn = fnm1 + fnm2;

        return fibn;
    }

    public static int fib_memo(int n, int[] dp) {
        if(n == 0 || n == 1) {
            dp[n] = n;
            return dp[n];
        }
        // if problem is already solved, then return the solution
        if(dp[n] != 0) {
            return dp[n];
        }

        int fnm1 = fib_memo(n - 1, dp);
        int fnm2 = fib_memo(n - 2, dp);
        int fibn = fnm1 + fnm2;
        // solve and store the problem in dp    
        dp[n] = fibn;
        return fibn;
    }

    public static int fib_tab(int n, int[] dp) {
        // 1. make storage
        // 2. Meaning of cell -> dp[i] have fib(i) i.e. dp[i] = dp[i - 1] + dp[i - 2]
        // 3. Identify traversal -> smallest problem is on 0 abnd 1
        // 4. prerequisite
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void fib() {
        int n = 5;
        // int res = fib_rec(n);
        int[] dp = new int[n + 1];
        // int res = fib_memo(n, dp);
        int res = fib_tab(n, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~~~~Climb Stair Path~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static int climbStair_rec(int i, int n) {
        if(i == n) {
            return 1;
        }

        int count = 0;
        for(int jump = 1; jump <= 3 && i + jump <= n; jump++) {
            count += climbStair_rec(i + jump, n);
        }
        return count;
    }

    public static int climbStair_memo(int i, int n, int[] dp) {
        if(i == n) return dp[i] = 1;

        if(dp[i] != 0) return dp[i];

        int count = 0;
        for(int jump = 1; jump <= 3 && i + jump <= n; jump++) {
            count += climbStair_memo(i + jump, n, dp);
        }

        return dp[i] = count;
    }

    // write tabulation code
    public static int climbStair_tab1(int i, int n, int[] dp) {
        // prerequisite
        dp[n] = 1;

        for(i = n - 1; i >= 0; i--) {
            if(i + 3 <= n) {
                // for three jumps
                dp[i] = dp[i + 1] + dp[i + 2] + dp[i + 3];
            } else if(i + 2 <= n) {
                // for two jumps
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                // for single jump
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static int climbStair_tab2(int i, int n, int[] dp) {
        for(i = n; i >= 0; i--) {
            if(i == n) {
                dp[i] = 1;
                continue;
            }
            int count = 0;
            for(int jump = 1; jump <= 3 && i + jump <= n; jump++) {
                count += dp[i + jump];
            }
            dp[i] = count;
        }
        return dp[0];
    }

    // variable jumps -> climb stair===================
    public static int climbStairJump_rec(int[] jumps, int i, int n) {
        if(i == n) {
            return 1;
        }

        int count = 0;
        for(int jump = 1; jump <= jumps[i] && i + jump <= n; jump++) {
            count += climbStairJump_rec(jumps, i + jump, n);
        }
        return count;
    }

    public static int climbStairJump_memo(int[] jumps, int i, int n, int[] dp) {
        if(i == n) {
            dp[i] = 1;
            return 1;
        }
        if(dp[i] != 0) 
            return dp[i];

        int count = 0;
        for(int jump = 1; jump <= jumps[i] && i + jump <= n; jump++) {
            count += climbStairJump_memo(jumps, i + jump, n, dp);
        }

        dp[i] = count;
        return count;
    }

    public static int climbStairJumps_tab(int[] jumps, int i, int n, int[] dp) {
        for(i = n; i >= 0; i--) {
            if(i == n) {
                dp[i] = 1;
                continue;
            }
            int count = 0;
            for(int jump = 1; jump <= jumps[i] && i + jump <= n; jump++) {
                count += dp[i + jump];
            }
            dp[i] = count;
        }
        return dp[0];
    }

    // min jumps/ move -> climb stair===================

    public static int climbStairMinJumps_rec(int[] jumps, int i, int n){
        if(i == n) {
            return 0;
        }

        int minJump = Integer.MAX_VALUE - 1;
        for(int jump = 1; jump <= jumps[i] && i + jump <= n; jump++) {
            minJump= Math.min(minJump, climbStairMinJumps_rec(jumps, i + jump, n));
        }

        return minJump + 1;
    }

    public static int climbStairMinJumps_memo(int[] jumps, int i, int n, int[] dp){
        if(i == n) {
            return dp[i] = 0;
        }
        if(dp[i] != 0)
            return dp[i];

        int minJump = Integer.MAX_VALUE - 1;
        for(int jump = 1; jump <= jumps[i] && i + jump <= n; jump++) {
            minJump= Math.min(minJump, climbStairMinJumps_memo(jumps, i + jump, n, dp));
        }

        return dp[i] = minJump + 1;
    }

    public static int climbStairMinJumps_tab(int[] jumps, int i, int n, int[] dp){
        for(i = n; i >= 0; i--) {
            if(i == n) {
                dp[i] = 0;
                continue;
            }

            int minJump = Integer.MAX_VALUE - 1;
            for(int jump = 1; jump <= jumps[i] && i + jump <= n; jump++) {
                minJump= Math.min(minJump, dp[i + jump]);
            }
    
            dp[i] = minJump + 1;
        }
        return dp[0];
    }

    public static void climbStairs() {
        int n = 7;
        int[] jumps = {2, 1, 3, 0, 2, 3, 1};
        // int res = climbStairMinJumps_rec(jumps, 0, n);
        int[] dp = new int[n + 1];
        // int res = climbStairMinJumps_memo(jumps, 0, n, dp);
        int res = climbStairMinJumps_tab(jumps, 0, n, dp);
        
        // int res = climbStair_rec(0, n);
        // int[] dp = new int[n + 1];
        // int res = climbStair_memo(0, n, dp);
        // int res = climbStair_tab1(0, n, dp);
        // int res = climbStair_tab2(0, n, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~~~~Min Cost in Maze Traversal~~~~~~~~~~~~~~~~~~~~~~

    public static int minCostPath_rec(int[][] maze, int sr, int sc) {
        if(sr == maze.length - 1 && sc == maze[0].length - 1) {
            return maze[sr][sc];
        }
        int minCost = Integer.MAX_VALUE;
        // horizontal
        if(sc + 1 < maze[0].length) {
            minCost = Math.min(minCost, minCostPath_rec(maze, sr, sc + 1));
        }

        // vertical
        if(sr + 1 < maze.length) {
            minCost = Math.min(minCost, minCostPath_rec(maze, sr + 1, sc));
        }
        return maze[sr][sc] + minCost;
    }

    public static int minCostPath_memo(int[][] maze, int sr, int sc, int[][] dp) {
        if(sr == maze.length - 1 && sc == maze[0].length - 1) {
            return dp[sr][sc] = maze[sr][sc];
        }

        if(dp[sr][sc] != 0) {
            return dp[sr][sc];
        }

        int minCost = Integer.MAX_VALUE;
        // horizontal
        if(sc + 1 < maze[0].length) {
            minCost = Math.min(minCost, minCostPath_memo(maze, sr, sc + 1, dp));
        }

        // vertical
        if(sr + 1 < maze.length) {
            minCost = Math.min(minCost, minCostPath_memo(maze, sr + 1, sc, dp));
        }
        dp[sr][sc] = maze[sr][sc] + minCost;
        return dp[sr][sc]; 
    }

    public static int minCostPath_tab(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        int[][] dp = new int[n][m];


        for(int r = n - 1; r >= 0; r--) {
            for(int c = m - 1; c >= 0; c--) {
                if(r == n - 1 && c == m - 1) {
                    dp[r][c] = maze[r][c];
                } else if(r == n - 1) {
                    dp[r][c] = maze[r][c] + dp[r][c + 1];
                } else if(c == m - 1) {
                    dp[r][c] = maze[r][c] + dp[r + 1][c];
                } else {
                    dp[r][c] = maze[r][c] + Math.min(dp[r][c + 1], dp[r + 1][c]);
                }
            }
        }

        return dp[0][0];
    }

    public static int minCostPath_tab2(int[][] maze, int sr, int sc, int[][] dp) {
        for(sc = maze[0].length - 1; sc >= 0; sc--) {
            for(sr = maze.length - 1; sr >= 0; sr--) {
                if(sr == maze.length - 1 && sc == maze[0].length - 1) {
                    dp[sr][sc] = maze[sr][sc];
                    continue;
                }
        
                int minCost = Integer.MAX_VALUE;
                // horizontal
                if(sc + 1 < maze[0].length) {
                    minCost = Math.min(minCost, dp[sr][sc + 1]);
                }
        
                // vertical
                if(sr + 1 < maze.length) {
                    minCost = Math.min(minCost, dp[sr + 1][sc]);
                }
                dp[sr][sc] = maze[sr][sc] + minCost;
            }
        }
        return dp[0][0];
    }

    public static void minCostPath() {
        int[][] maze = {
            {0, 1, 4, 2, 8, 2},
            {4, 3, 6, 5, 0, 4},
            {1, 2, 4, 1, 4, 6},
            {2, 0, 7, 3, 2, 2},
            {3, 1, 5, 9, 2, 4},
            {2, 7, 0, 8, 5, 1}
        };
        // int res = minCostPath_rec(maze, 0, 0);
        int[][] dp = new int[maze.length][maze[0].length];
        // int res = minCostPath_memo(maze, 0, 0, dp);
        // int res = minCostPath_tab(maze);
        int res = minCostPath_tab2(maze, 0, 0, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~Goldmine~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static int[] rdir = {-1, 0, 1};
    public static int[] cdir = {1, 1, 1};

    public static int goldmine_rec(int[][] mine, int r, int c) {
        if(c == mine[0].length - 1) {
            return mine[r][c];
        }

        int maxGold = 0;
        for(int d = 0; d <= 2; d++) {
            int rr = r + rdir[d];
            int cc = c + cdir[d];
            if(rr >= 0 && rr < mine.length) {
                maxGold = Math.max(maxGold, goldmine_rec(mine, rr, cc));
            }
        }
        return maxGold + mine[r][c];
    }

    public static int goldmine_recHelp(int[][] mine) {
        int maxGold = 0;
        for(int r = 0; r < mine.length; r++) {
            maxGold = Math.max(maxGold, goldmine_rec(mine, r, 0));
        }
        return maxGold;
    }

    public static int goldmine_memo(int[][] mine, int r, int c, int[][] dp) {
        if(c == mine[0].length - 1) {
            return dp[r][c] = mine[r][c];
        }

        if(dp[r][c] != 0) return dp[r][c];

        int maxGold = 0;
        for(int d = 0; d <= 2; d++) {
            int rr = r + rdir[d];
            int cc = c + cdir[d];
            if(rr >= 0 && rr < mine.length) {
                maxGold = Math.max(maxGold, goldmine_memo(mine, rr, cc, dp));
            }
        }
        return dp[r][c] = maxGold + mine[r][c];
    }

    public static int goldmine_memoHelp(int[][] mine) {

        int[][] dp = new int[mine.length][mine[0].length];

        int maxGold = 0;
        for(int r = 0; r < mine.length; r++) {
            maxGold = Math.max(maxGold, goldmine_memo(mine, r, 0, dp));
        }
        return maxGold;
    }

    public static int goldmine_tab(int[][] mine) {
        int[][] dp = new int[mine.length][mine[0].length];

        for(int c = mine[0].length - 1; c >= 0; c--) {
            for(int r = 0; r < mine.length; r++) {
                if(c == mine[0].length - 1) {
                    // last caolumn
                    dp[r][c] = mine[r][c];
                } else if(r == 0) {
                    // first row
                    dp[r][c] = mine[r][c] + Math.max(dp[r][c + 1], dp[r + 1][c + 1]);
                } else if(r == mine.length - 1) {
                    // last row
                    dp[r][c] = mine[r][c] + Math.max(dp[r - 1][c + 1], dp[r][c + 1]);
                } else {
                    // middle section
                    dp[r][c] = mine[r][c] + Math.max(dp[r - 1][c + 1], Math.max(dp[r][c + 1], dp[r + 1][c + 1]));
                }
            }
        }
        // loop and find answer
        int maxGold = 0;
        for(int r = 0; r < dp.length; r++) {
            maxGold = Math.max(maxGold, dp[r][0]);
        }
        return maxGold;
    }

    public static int goldmine_tab2(int[][] mine, int r, int c, int[][] dp) {
        for(c = mine[0].length - 1; c >= 0; c--) {
            for(r = 0; r < mine.length; r++) {
                if(c == mine[0].length - 1) {
                    dp[r][c] = mine[r][c];
                    continue;
                }

                int maxGold = 0;
                for(int d = 0; d <= 2; d++) {
                    int rr = r + rdir[d];
                    int cc = c + cdir[d];
                    if(rr >= 0 && rr < mine.length) {
                        maxGold = Math.max(maxGold, dp[rr][cc]);
                    }
                }
                dp[r][c] = maxGold + mine[r][c];
            }
        }

        // loop and find answer
        int gold = 0;
        for(r = 0; r < dp.length; r++) {
            gold = Math.max(gold, dp[r][0]);
        }
        return gold;
    }

    public static void goldmine() {
        int[][] mine = {
            {0, 1, 4, 2, 8, 2},
            {4, 3, 6, 5, 0, 4},
            {1, 2, 4, 1, 4, 6},
            {2, 0, 7, 3, 2, 2},
            {3, 1, 5, 9, 2, 4},
            {2, 7, 0, 8, 5, 1}
        };

        // int res = goldmine_recHelp(mine);
        // int res = goldmine_memoHelp(mine);
        // int res = goldmine_tab(mine);

        int[][] dp = new int[mine.length][mine[0].length];
        int res = goldmine_tab2(mine, 0, 0, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~Coin Change~~~~~~~~~~~~~~~~~~~~~~    

    public static int coinChangePerm_rec(int[] coins, int target) {
        if(target == 0) {
            return 1;
        }


        int count = 0;
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if(target - coin >= 0) {
                count += coinChangePerm_rec(coins, target - coin);
            }
        }
        return count;
    }

    public static int coinChangePerm_memo(int[] coins, int target, int[] dp) {
        if(target == 0) {
            return dp[target] = 1;
        }

        if(dp[target] != 0) {
            return dp[target];
        }

        int count = 0;
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if(target - coin >= 0) {
                count += coinChangePerm_memo(coins, target - coin, dp);
            }
        }
        return dp[target] = count;
    }

    public static int coinChangePerm_tab1(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int t = 1; t <= target; t++) {
            // find solution for target = t;
            int count = 0;
            // provide opportunity to every coins
            for(int i = 0; i < coins.length; i++) {
                int coin = coins[i];
                if(t - coin >= 0) {
                    count += dp[t - coin];
                }
            }
            dp[t] = count;
        }
        return dp[target];
    }

    public static void coinChangePermutation(int[] coins, int target) {
        // int res = coinChangePerm_rec(coins, target);
        // int[] dp = new int[target + 1]; 
        // int res = coinChangePerm_memo(coins, target, dp);

        int res = coinChangePerm_tab1(coins, target);
        System.out.println(res);
    }

    // li-> last index
    public static int coinChangeComb_rec(int[] coins, int target, int li) {
        if(target == 0) return 1;

        int count = 0;
        for(int i = li; i < coins.length; i++) {
            int coin = coins[i];

            if(target - coin >= 0) {
                count += coinChangeComb_rec(coins, target - coin, i);
            }
        }

        return count;
    }

    public static int coinChangeComb_tab(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for(int t = coin; t <= target; t++) {
                dp[t] += dp[t - coin];
            }
        }

        return dp[target];
    }

    public static void  coinChangeComb(int[] coins, int target) {
        int res = coinChangeComb_rec(coins, target, 0);
        System.out.println(res);
    }

    public static void coinChange() {
        int[] coins = {2, 3, 5, 6};
        int target = 7;
        // coinChangePermutation(coins, target);
        coinChangeComb(coins, target);
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~Target Sum Subset~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static boolean targetSumSubset_rec(int[] arr, int indx, int target) {
        if(target == 0) {
            return true;
        }
        if(indx == arr.length) return false;

        boolean res = false;
        // yes call
        if(target - arr[indx] >= 0) {
            res = targetSumSubset_rec(arr, indx + 1, target - arr[indx]);
        }
        // no call
        res = res || targetSumSubset_rec(arr, indx + 1, target);
        return res;
    }

    public static boolean targetSumSubset_memo(int[] arr, int indx, int target, boolean[][] dp) {
        if(target == 0) {
            return dp[indx][target] = true;
        }
        if(indx == arr.length) return dp[indx][target] = false;

        if(dp[indx][target] == true) {
            return true;
        }

        boolean res = false;
        // yes call
        if(target - arr[indx] >= 0) {
            res = targetSumSubset_rec(arr, indx + 1, target - arr[indx]);
        }
        // no call
        res = res || targetSumSubset_rec(arr, indx + 1, target);
        return dp[indx][target] = res;
    }

    public static int targetSumSubset_noOfWays(int[] arr, int indx, int target, int[][] dp, String res) {
        if(target == 0) {
            System.out.println(res);
            return dp[indx][target] = 1;
        }
        if(indx == arr.length) return dp[indx][target] = 0;

        // if(dp[indx][target] != 0) return dp[indx][target];

        int count = 0;
        if(target - arr[indx] >= 0) {
            count += targetSumSubset_noOfWays(arr, indx + 1, target - arr[indx], dp, res + arr[indx] + ", ");
        }
        count += targetSumSubset_noOfWays(arr, indx + 1, target, dp, res);

        return dp[indx][target] = count;
    }

    public static void targetSumSubset() {
        int[] arr = {3, 1, 2, 5, 5, 6, 2, 7, 1, 3, 1};
        int target = 22;
        // boolean[][] dp = new boolean[arr.length + 1][target + 1];
        int[][] dp = new int[arr.length + 1][target + 1];
        System.out.println(targetSumSubset_noOfWays(arr, 0, target, dp, ""));

        for(int[] a : dp){
            System.out.println(Arrays.toString(a));
        }
        // System.out.println(targetSumSubset_memo(arr, 0, target, dp));
        // System.out.println(targetSumSubset_rec(arr, 0, target));
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~Knapsack Problem~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static void ques() {
        targetSumSubset();
        // coinChange();
        // goldmine();
        // minCostPath();
        // climbStairs();
        // fib();
    }

    public static void main(String[] args) {
        ques();
    }
}
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

    public static int fib_optimise(int n) {
        int a = 0;
        int b = 1;

        while(n > 0) {
            int c = a + b;
            a = b;
            b = c;
            n--;
        }
        return a;
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

        if(dp[indx][target] != 0) return dp[indx][target];

        int count = 0;
        if(target - arr[indx] >= 0) {
            count += targetSumSubset_noOfWays(arr, indx + 1, target - arr[indx], dp, res + arr[indx] + ", ");
        }
        count += targetSumSubset_noOfWays(arr, indx + 1, target, dp, res);

        return dp[indx][target] = count;
    }

    public static int targetSumSubset_tab(int[] arr, int target) {
        int[][] dp = new int[arr.length + 1][target + 1];

        for(int i = 0; i < dp.length; i++) {
            int val = i > 0 ? arr[i - 1] : 0;
            for(int t = 0; t <= target; t++) {
                if(i == 0 && t == 0) {
                    dp[i][t] = 1;
                } else if(i == 0) {
                    dp[i][t] = 0;
                } else if(t == 0) {
                    dp[i][t] = 1;
                } else {
                    int count = 0;
                    if(t - val < 0) {
                        // if target is less than current value, only no call possible
                        count += dp[i - 1][t];
                    } else {
                        // yes  
                        count += dp[i - 1][t - val];
                        // no 
                        count += dp[i - 1][t];
                    }
                    dp[i][t] = count;
                }
            }
        }

        // for(int[] a : dp){
        //     System.out.println(Arrays.toString(a));
        // }

        return dp[arr.length][target];
    }

    public static void targetSumSubset() {
        // int[] arr = {3, 1, 2, 5, 5, 6, 2, 7, 1, 3, 1};
        // int target = 22;

        int[] arr = {4, 2, 7, 1, 3};
        int target = 10;
        // boolean[][] dp = new boolean[arr.length + 1][target + 1];
        // int[][] dp = new int[arr.length + 1][target + 1];
        // System.out.println(targetSumSubset_noOfWays(arr, 0, target, dp, ""));
        System.out.println(targetSumSubset_tab(arr, target));

        // for(int[] a : dp){
        //     System.out.println(Arrays.toString(a));
        // }
        // System.out.println(targetSumSubset_memo(arr, 0, target, dp));
        // System.out.println(targetSumSubset_rec(arr, 0, target));
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~Knapsack Problem~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static int knapsack01_rec(int[] wts, int[] vals, int indx, int cap) {
        if(indx == vals.length) {
            return 0;
        }

        System.out.println(indx + " " + cap);

        // yes call
        int v1 = Integer.MIN_VALUE;
        if(cap - wts[indx] >= 0)
            v1 = knapsack01_rec(wts, vals, indx + 1, cap - wts[indx]) + vals[indx];
        // no call
        int v2 = knapsack01_rec(wts, vals, indx + 1, cap);

        return v1 > v2 ? v1 : v2;
    }

    public static int knapsack01_memo(int[] wts, int[] vals, int indx, int cap, int[][] dp) {
        if(indx == vals.length) {
            return dp[indx][cap] = 0;
        }

        if(dp[indx][cap] != 0) 
            return dp[indx][cap];
        
        System.out.println(indx + " " + cap);

        // yes call
        int v1 = Integer.MIN_VALUE;
        if(cap - wts[indx] >= 0)
            v1 = knapsack01_memo(wts, vals, indx + 1, cap - wts[indx], dp) + vals[indx];
        // no call
        int v2 = knapsack01_memo(wts, vals, indx + 1, cap, dp);

        return dp[indx][cap] = v1 > v2 ? v1 : v2;
    }

    public static int knapsack01_tab(int[] wts, int[] val, int cap) {
        int[][] dp = new int[wts.length + 1][cap + 1];

        for(int i = 1; i < dp.length; i++) {
            int wt = wts[i - 1];
            int vl = val[i - 1];
            for(int c = 1; c < dp[0].length; c++) {
                if(c < wt) {
                    dp[i][c] = dp[i - 1][c];
                } else {
                    dp[i][c] = Math.max(dp[i - 1][c - wt] + vl, dp[i - 1][c]);
                }
            }
        }

        for(int[] a : dp){
            System.out.println(Arrays.toString(a));
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static int unboundedKnapsack_tab(int[] wts, int[] vals, int cap) {
        int[] dp = new int[cap + 1];

        for(int i = 0; i < wts.length; i++) {
            int wt = wts[i];
            int val = vals[i];

            for(int c = wt; c <= cap; c++) {
                // yes call
                int v1 = dp[c - wt] + val;
                // no call
                int v2 = dp[c];
                dp[c] = Math.max(v1, v2);
            }
        }

        System.out.println(Arrays.toString(dp));

        return dp[cap];
    }

    public static void knapsack() {
        int[] wts = {2, 5, 1, 3, 4};
        int[] val = {15, 14, 10, 45, 30};
        int cap = 7;

        System.out.println(unboundedKnapsack_tab(wts, val, cap));
        // int[][] dp = new int[wts.length + 1][cap + 1];
        // System.out.println(knapsack01_memo(wts, val, 0, cap, dp));
        // for(int[] a : dp){
        //     System.out.println(Arrays.toString(a));
        // }
        // System.out.println(knapsack01_tab(wts, val, cap));
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~Count Binary Strings~~~~~~~~~~~~~~~~~~~~~~~~~~
    // clen -> current length
    // n -> total size of string
    // le -> last element
    public static int countBinaryStrings_rec(int clen, int n, int le, String str) {
        if(clen == n) {
            System.out.println(str);
            return 1;
        }
        
        int count = 0;

        // System.out.println(clen + " " + le);

        if(le == 0) {
            count += countBinaryStrings_rec(clen + 1, n, 1, str + "1");
        } else {
            count += countBinaryStrings_rec(clen + 1, n, 0, str + "0");
            count += countBinaryStrings_rec(clen + 1, n, 1, str + "1");
        }
        return count;
    }

    public static int countBinaryStrings_memo(int clen, int n, int le, String str, int[][] dp) {
        if(clen == n) {
            return dp[le][clen] = 1;
        }
        
        if(dp[le][clen] != 0) {
            return dp[le][clen];
        }
        int count = 0;
        System.out.println(clen + " " + le);
        if(le == 0) {
            count += countBinaryStrings_memo(clen + 1, n, 1, str + "1", dp);
        } else {
            count += countBinaryStrings_memo(clen + 1, n, 0, str + "0", dp);
            count += countBinaryStrings_memo(clen + 1, n, 1, str + "1", dp);
        }
        return dp[le][clen] = count;
    }

    public static int countBinaryString_opt(int n) {
        int oo = 1; // old one
        int oz = 1; // ld zero

        for(int i = 2; i <= n; i++) {
            int no = oo + oz; // new one
            int nz = oo; // new zero

            oo = no;
            oz = nz;
        }

        return oo + oz;
    }

    public static void countBinaryString() {
        int n = 4;

        // System.out.println(countBinaryString_opt(n));

        int count = countBinaryStrings_rec(1, n, 0, "0");
        count += countBinaryStrings_rec(1, n, 1, "1");

        // int[][] dp = new int[2][n + 1];

        // int count = countBinaryStrings_memo(1, n, 0, "0", dp);
        // count += countBinaryStrings_memo(1, n, 1, "1", dp);

        // System.out.println(count);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Arrange Buildings~~~~~~~~~~~~~~~~~~~~~~~~~~
    // question is similar to count binary string
    public static void ArrangeBuildings(int n) {
        long ob = 1;
        long os = 1;
        
        for(int i = 2; i <= n; i++) {
            long nb = os;
            long ns = ob + os;
            
            os = ns;
            ob = nb;
        }
        
        long res = ob + os;
        res *= res;
        System.out.println(res);
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Count Encodings~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int countEncoding_rec(String str, String asf) {
        if(str.length() == 0) {
            // System.out.println(asf);
            return 1;
        }

        System.out.println(str);

        if(str.charAt(0) == '0') return 0;


        int count = 0;
        int n1 = (int)(str.charAt(0) - '0');
        char ch1 = (char)(n1 + 'a' - 1);
        String roq1 = str.substring(1);
        count+= countEncoding_rec(roq1, asf + ch1);

        if(str.length() > 1) {
            int n2 = (int)(str.charAt(1) - '0');
            int n = n1 * 10 + n2;
            if(n <= 26) {
                String roq2 = str.substring(2);
                char ch2 = (char)(n + 'a' - 1);
                count += countEncoding_rec(roq2, asf + ch2);
            }
        }
        return count;
    }

    public static int countEncoding_memo(String str, int indx, int[] dp) {
        if(indx == str.length()) {
            return dp[indx] = 1;
        }

        if(str.charAt(indx) == '0') return dp[indx] = 0;

        if(dp[indx] != 0) return dp[indx];


        int count = 0;
        int n1 = (int)(str.charAt(indx) - '0');
        count+= countEncoding_memo(str, indx + 1, dp);

        if(indx + 1 < str.length()) {
            int n2 = (int)(str.charAt(indx + 1) - '0');
            int n = n1 * 10 + n2;
            if(n <= 26) {
                count += countEncoding_memo(str, indx + 2, dp);
            }
        }
        return dp[indx] = count;
    }

    public static int countEncoding_tab(String str) {
        int[] dp = new int[str.length() + 1];
        for(int indx = dp.length - 1; indx >= 0; indx--) {
            if(indx == str.length()) {
                dp[indx] = 1;
                continue;
            }
    
            if(str.charAt(indx) == '0')  {
                dp[indx] = 0;
                continue;
            }

            int count = 0;
            int n1 = (int)(str.charAt(indx) - '0');
            count+= dp[indx + 1]; // countEncoding_memo(str, indx + 1, dp);
    
            if(indx + 1 < str.length()) {
                int n2 = (int)(str.charAt(indx + 1) - '0');
                int n = n1 * 10 + n2;
                if(n <= 26) {
                    count += dp[indx + 2]; // countEncoding_memo(str, indx + 2, dp);
                }
            }
            dp[indx] = count;
        }

        return dp[0];
    }

    public static void countEncoding() {
        // String str = "231011";
        String str = "123";
        int count = countEncoding_tab(str);
        // int[] dp = new int[str.length() + 1];
        // int count = countEncoding_memo(str, 0, dp);
        // int count = countEncoding_rec(str, "");
        System.out.println(count);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Count A+B+C~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int countAiBjCk(String str) {
        int a_count = 0;
        int b_count = 0;
        int c_count = 0;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == 'a') {
                a_count = 2 * a_count + 1;
            } else if(ch == 'b') {
                b_count = 2 * b_count + a_count;
            } else {
                c_count = 2 * c_count + b_count;
            }
        }
        return c_count;
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~Max. Sum Non Adjacent Element~~~~~~~~~~~~~~~~~~~~~~
    public static long maxSumNonAdjacent(int[] arr) {
        long exc = 0; // exclude
        long inc = arr[0]; // include

        for(int i = 1; i < arr.length; i++) {
            long n_exc = Math.max(exc, inc); // new exclude
            long n_inc = exc + arr[i]; // new include

            exc= n_exc;
            inc = n_inc;
        }

        return Math.max(inc, exc);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Paint House~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int paintHouse(int[][] arr) {
        int red = arr[0][0];
        int green = arr[0][1];
        int blue = arr[0][2];

        for(int i = 1; i < arr.length; i++) {
            int n_red = Math.min(green, blue) + arr[i][0];
            int n_green = Math.min(red, blue) + arr[i][1];
            int n_blue = Math.min(green, red) + arr[i][2];

            red = n_red;
            green = n_green;
            blue = n_blue;
        }

        // min from all three colours
        if(red > green && blue > gren) {
            return green;
        } else if(blue > red) {
            return red;
        } else {
            return blue;
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Paint House with Many Colors~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int paintHouseIIOn3(int[][] arr) {
        int n = arr.length;
        int k = arr[0].length;

        int[] dp = new int[k]; // no. of colors

        // fill for first row
        for(int j = 0; j < k; j++)
            dp[j] = arr[0][j];

        // dp code
        for(int i = 1; i < n; i++) {
            int[] n_dp = new int[k];
            for(int j = 0; j < k; j++) {
                // fill n_dp[j], find min from old dp except jth index
                int min = Integer.MAX_VALUE;
                for(int indx = 0; indx < k; indx++) {
                    if(indx == j) continue;
                    if(dp[indx] < min)
                        min = dp[indx];
                }

                n_dp[j] = min + arr[i][j];
            }
            dp = n_dp;
        }

        // return min from dp
        int ans = Integer.MAX_VALUE;
        for(int indx = 0; indx < k; indx++) {
            if(dp[indx] < ans)
                ans = dp[indx];
        }
        return ans;
    }

    public static int paintHouseII(int[][] arr) {
        int n = arr.length;
        int k = arr[0].length;

        int[] dp = new int[k]; // no. of colors

        int min = Integer.MAX_VALUE; // min
        int smin = Integer.MAX_VALUE; // second min

        // fill for first row
        for(int j = 0; j < k; j++) {
            dp[j] = arr[0][j];
            if(min > dp[j]) {
                smin = min;
                min = dp[j];
            } else if(smin > dp[j]) {
                smin = dp[j];
            }
        }

        // dp code
        for(int i = 1; i < n; i++) {
            int[] n_dp = new int[k];
            int min2 = Integer.MAX_VALUE;
            int smin2 = Integer.MAX_VALUE;
            for(int j = 0; j < k; j++) {
                // fill n_dp[j], find min from old dp except jth index
                if(dp[j] == min)
                    n_dp[j] = smin + arr[i][j];
                else
                    n_dp[j] = min + arr[i][j];

                if(min2 > n_dp[j]) {
                    smin2 = min2;
                    min2= n_dp[j];
                } else if(smin2 > n_dp[j]) {
                    smin2 = n_dp[j];
                }
            }
            dp = n_dp;
            min = min2;
            smin = smin2;
        }

        return min;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Paint Fence~~~~~~~~~~~~~~~~~~~~~~
    public static long paintFence(int n, int k) {
        long same = k;
        long diff = k * (k - 1);

        for(int i = 3; i <= n; i++) {
            long nsame = diff;
            long ndiff = (same + diff) * (k - 1);

            same = nsame;
            diff = ndiff;
        }
        return same + diff;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~tillin 2 * 1~~~~~~~~~~~~~~~~~~~~~~~

    public static int tiling2x1(int n) {
        // fibbonacci after analysing
        int a = 1;
        int b = 2;

        for(int i = 1; i < n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~tillin M * 1~~~~~~~~~~~~~~~~~~~~~~~
    public static int tilingMx1(int n, int m) {
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            if(i < m) {
                dp[i] = 1;
            } else if(i == m) {
                dp[i] = 2;
            } else {
                dp[i] = dp[i - 1] + dp[i - m];
            }
        }
        return dp[n];
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~Friends Pairing~~~~~~~~~~~~~~~~~~
    public static int friendsPair(int n) {
        if(n == 1) return 1;
        int a = 1; // single 
        int b = 2; // when no. of friend is 2;

        for(int i = 3; i <= n; i++) {
            // f(n) = f(n - 1) + (n - 1) * f(n - 2);
            int c = b + (i - 1) * a;

            // shifting
            a = b;
            b = c;
        }
        return b;
    }

    // ~~~~~~~~~~~~~~~~~~~~Partition into k subset~~~~~~~~~~~~~~~~
    public static int partitionKSubset(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];

        for(int i = 1; i <= k; i++) {
            for(int j = i; j <= n; j++) {
                if(j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = i * dp[i][j - 1] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[k][n];
    }

    // ~~~~~~~~~~~~~~~~~~Stock Buy sell One transaction~~~~~~~~~~
    public static int stockBuySell1(int[] price) {
        int bd = 0; // buy date

        int profit = 0;
        for(int day = 1; day < price.length; day++) {
            if(price[day] >= price[day - 1]) {
                // local maxima
                profit = Math.max(profit, price[day] - price[bd]);
            } else {
                // local minima
                if(price[day] < price[bd]) {
                    bd = day;
                }
            }
        }
        return profit;
    }

    // ~~~~~~~~~~~~~~~Stock Buy Sell Infinite transactions~~~~~~~~~
    public static int stackBuySell2(int[] price) {
        int bd = 0;
        int sd = 0;
        int profit = 0;

        for(int day = 1; day < price.length; day++){
            if(price[day] > price[day - 1]) {
                // increasing
                sd = day;
            } else {
                // add profit from previous incrementation
                profit += price[sd] - price[bd];
                // update buying selling day
                sd = day;
                bd = day;
            }
        }
        profit += price[sd] - price[bd];
        return profit;
    }

    // ~~~~~~~~~~~Longest Increasing Subsequence~~~~~~~~~~~~~~~~~
    public static int lis(int[] arr) {
        int omax = 0;
        int[] dp = new int[arr.length];
        dp[0] = 1;

        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            // find max from previous valid points
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] <= arr[i] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
        }

        return omax;
    }

    public static void ques() {
        countEncoding();
        // countBinaryString();
        // knapsack();
        // targetSumSubset();
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
import java.util.*;

public class dp {

    // ~~~~~~~~~~~~~~~Fibbonacci~~~~~~~~~~~~
    public static int fib_rec(int n) {
        if(n == 0 || n == 1) return n;

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
    public static int goldmineHelper_rec(int[][] mine, int x, int y) {
        if(y == mine[0].length - 1) {
            return mine[x][y];
        }

        int cost = 0;
        // top-right
        if(x - 1 >= 0) {
            cost = Math.max(cost, goldmineHelper_rec(mine, x - 1, y + 1));
        }
        // right -> no need for check of y
        cost = Math.max(cost, goldmineHelper_rec(mine, x, y + 1));
        // down-right
        if(x + 1 < mine.length) {
            cost = Math.max(cost, goldmineHelper_rec(mine, x + 1, y + 1));
        }
        return cost + mine[x][y];
    }

    public static int goldmineHelper_memo(int[][] mine, int x, int y, Integer[][] dp) {
        if(y == mine[0].length - 1) {
            return dp[x][y] = mine[x][y];
        }

        if(dp[x][y] != null) {
            return dp[x][y];
        }

        int cost = 0;
        // top-right
        if(x - 1 >= 0) {
            cost = Math.max(cost, goldmineHelper_memo(mine, x - 1, y + 1, dp));
        }
        // right -> no need for check of y
        cost = Math.max(cost, goldmineHelper_memo(mine, x, y + 1, dp));
        // down-right
        if(x + 1 < mine.length) {
            cost = Math.max(cost, goldmineHelper_memo(mine, x + 1, y + 1, dp));
        }
        return dp[x][y] = cost + mine[x][y];
    }

    public static int goldmine_rec(int[][] mine) {
        int profit = 0;

        Integer[][] dp = new Integer[mine.length][mine[0].length];
        for(int x = 0; x < mine.length; x++) {
            profit = Math.max(profit, goldmineHelper_memo(mine, x, 0, dp));
        }
        return profit;
    }

    public static int goldmine_tab1(int[][] mine, int x, int y, int[][] dp) {
        int res = 0;
        for(y = mine[0].length - 1; y >= 0; y--) {
            for(x = 0; x < mine.length; x++) {
                if(y == mine[0].length - 1) {
                    dp[x][y] = mine[x][y];
                } else if(x == 0){
                    dp[x][y] = Math.max(dp[x][y + 1], dp[x + 1][y + 1]) + mine[x][y];
                } else if(x == mine.length - 1) {
                    dp[x][y] = Math.max(dp[x][y + 1], dp[x - 1][y + 1]) + mine[x][y];
                } else {
                    dp[x][y] = Math.max(dp[x - 1][ y + 1], Math.max(dp[x][y + 1], dp[x + 1][y + 1])) + mine[x][y];
                }
                res = Math.max(res, dp[x][y]);
            }
        }
        return res;
    }

    public static int goldmine_tab2(int[][] mine, int x, int y, int[][] dp) {
        int profit = 0;
        for(y = mine[0].length - 1; y >= 0; y--) {
            for(x = 0; x < mine.length; x++) {
                if(y == mine[0].length - 1) {
                    dp[x][y] = mine[x][y];
                    profit = Math.max(profit, dp[x][y]);
                    continue;
                }
                int cost = 0;
                // top-right
                if(x - 1 >= 0) {
                    cost = Math.max(cost, dp[x - 1][y + 1]);
                }
                // right -> no need for check of y
                cost = Math.max(cost, dp[x][y + 1]);
                // down-right
                if(x + 1 < mine.length) {
                    cost = Math.max(cost, dp[x + 1][y + 1]);
                }
                dp[x][y] = cost + mine[x][y];
                profit = Math.max(profit, dp[x][y]);
            }
        }
        return profit;
    }

    public static void goldmine() {
        int[][] mine = {};
        int res = goldmine_rec(mine);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~Target Sum Subset~~~~~~~~~~
    public static boolean targetSumSubset_rec(int[] arr, int indx, int target) {
        if(target == 0) return true; 
        if(indx == arr.length) {
            return false;
        }
        boolean res = false;
        
        // no call
        res = targetSumSubset_rec(arr, indx + 1, target);
        
        // yes call
        if(target - arr[indx] >= 0) {
            res = res || targetSumSubset_rec(arr, indx + 1, target - arr[indx]);
        }
        
        return res;
    }

    public static boolean targetSumSubset_memo(int[] arr, int indx, int target, Boolean[][] dp) {
        if(target == 0) return dp[indx][target] = true; 
        if(indx == arr.length) {
            return dp[indx][target] = false;
        }

        if(dp[indx][target] != null) {
            return dp[indx][target];
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

    public static boolean targetSumSubset_tab1(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        
        for(int indx = 0; indx < dp.length; indx++) {
            for(int targ = 0; targ < dp[0].length; targ++) {
                if(targ == 0) {
                    dp[indx][targ] = true;
                } else if(indx == 0) {
                    dp[indx][targ] = false;
                } else {
                    int val = arr[indx - 1];
                    if(targ < val) {
                        // only no call
                        dp[indx][targ] = dp[indx - 1][targ];
                    } else {
                        // no call OR(||) yes call
                        dp[indx][targ] = dp[indx - 1][targ] || dp[indx - 1][targ - val];
                    }
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static boolean targetSumSubset_tab2(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        for(int indx = arr.length; indx >= 0; indx--) {
            for(int targ = 0; targ <= target; targ++) {
                if(targ == 0) {
                    dp[indx][targ] = true; 
                    continue;
                }
                    
                if(indx == arr.length) {
                    dp[indx][targ] = false;
                    continue;
                }

                boolean res = false;
                // yes call
                if(targ - arr[indx] >= 0) {
                    res = dp[indx + 1][targ - arr[indx]];
                }
                // no call
                res = res || dp[indx + 1][targ];
                dp[indx][targ] = res;
            }
        }
        return dp[0][target];
    }

    public static void targetSumSubset() {
        int[] arr = {4, 2, 7, 1, 3};
        int target = 10;
        targetSumSubset_rec(arr, 0, target);
    }

    // ~~~~~~~~~~~~~~~~~~~Coin Change~~~~~~~~~~~~~~
    public static int coinChange_perm_rec(int[] coins, int target, String psf) {
        if(target == 0) {
            System.out.println(psf);
            return 1;
        } 

        int count = 0;
        for(int coin : coins) {
            if(target - coin >= 0) 
                count += coinChange_perm_rec(coins, target - coin, psf + coin + " ");
        }
        return count;
    }

    public static int coinChange_perm_memo(int[] coins, int target, int[] dp) {
        if(target == 0) {
            return dp[target] = 1;
        } 

        if(dp[target] != 0) return dp[target];

        int count = 0;
        for(int coin : coins) {
            if(target - coin >= 0) 
                count += coinChange_perm_memo(coins, target - coin, dp);
        }
        return dp[target] = count;
    }

    public static int coinChange_perm_tab1(int[] coins, int target, int[] dp) {
        dp[0] = 1;

        for(int i = 1; i <= target; i++) {
            for(int coin : coins) {
                if(i - coin >= 0)
                    dp[i] += dp[i - coin];
            }
        }
        return dp[target];
    }
    public static HashMap<String, Integer> map = null;
    public static int coinChange_comb_rec_SubseqStyle(int[] coins, int indx, int target,
                 String psf, Integer[][] dp) {
        // System.out.println(indx + " " + target);
        if(target == 0) {
            // System.out.println(psf);
            return dp[indx][target] = 1;
        }

        if(indx == coins.length) {
            return dp[indx][target] = 0;
        }

        if(dp[indx][target] != null) return dp[indx][target];

        String str = "" + indx + " " + target;
        if(map.containsKey(str) == true) {
            map.put(str, map.get(str) + 1);
        } else {
            map.put(str, 1);
        }

        int count = 0;
        if(target - coins[indx] >= 0) {
            count += coinChange_comb_rec_SubseqStyle(coins, indx, target - coins[indx], psf + coins[indx] + " ", dp);
        }
        count += coinChange_comb_rec_SubseqStyle(coins, indx + 1, target, psf, dp);
        return dp[indx][target] = count;
    }

    public static int coinChange_comb_tab(int[] coins, int target) {
        int[] dp = new int[target + 1];

        dp[0] = 1;

        for(int coin : coins) {
            for(int i = coin; i <= target; i++) {
                if(i - coin >= 0)
                    dp[i] += dp[i - coin];
            }
        }
        return dp[target];
    }  

    public static int coinChange_tab2(int[] coins, int targ, Integer[][] dp) {
        for(int indx = coins.length; indx >= 0; indx--) {
            for(int target = 0; target <= targ; target++) {
                if(target == 0) {
                    dp[indx][target] = 1;
                    continue;
                }
        
                if(indx == coins.length) {
                    dp[indx][target] = 0;
                    continue;
                }
        
                int count = 0;
                if(target - coins[indx] >= 0) {
                    count += dp[indx][target - coins[indx]];
                }
                count += dp[indx + 1][target];
                dp[indx][target] = count;
            }
        }
        return dp[0][targ];
    }

    public static void coinChange() {
        map = new HashMap<>();
        int[] coins = {2, 3, 5, 6, 10};
        int target = 90;

        Integer[][] dp = new Integer[coins.length + 1][target + 1];
        // int[] dp = new  int[target + 1];
        // int res = coinChange_perm_memo(coins, target, dp);
        // System.out.println(res);

        coinChange_comb_rec_SubseqStyle(coins, 0, target, "", dp);
        
        for(String key : map.keySet()) {
            // if(map.get(key) > 1)
            System.out.println(key + " -> " + map.get(key));
        }

    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~Knapsack~~~~~~~~~~~~~~~
    public static int knapsack01_rec(int[] wts, int[] vals, int indx, int cap) {
        if(indx == -1) {
            return 0;
        }

        int v1 = 0;
        // yes call
        if(cap - wts[indx] >= 0) {
            v1 = knapsack01_rec(wts, vals, indx - 1, cap - wts[indx]) + vals[indx];
        }
        // no call
        int v2 = knapsack01_rec(wts, vals, indx - 1, cap);
        return Math.max(v1, v2);
    }

    public static int knapsack01_memo(int[] wts, int[] vals, int indx, int cap, int[][] dp) {
        if(indx == -1) {
            return dp[indx + 1][cap] = 0;
        }

        if(dp[indx + 1][cap] != 0) {
            return dp[indx + 1][cap];
        }

        int v1 = 0;
        // yes call
        if(cap - wts[indx] >= 0) {
            v1 = knapsack01_memo(wts, vals, indx - 1, cap - wts[indx], dp) + vals[indx];
        }
        // no call
        int v2 = knapsack01_memo(wts, vals, indx - 1, cap, dp);
        return dp[indx + 1][cap] = Math.max(v1, v2);
    }

    public static int knapsack01_tab(int[] wts, int[] vals, int indx, int Cap, int[][] dp) {
        
        for(indx = 1; indx <= vals.length; indx++) {
            for(int cap = 1; cap <= Cap; cap++) {
                if(cap < wts[indx - 1]) {
                    dp[indx][cap] = dp[indx - 1][cap];
                } else {
                    // yes call
                    int v1 = dp[indx - 1][cap - wts[indx - 1]] + vals[indx - 1];
                    // no call
                    int v2 = dp[indx - 1][cap];

                    dp[indx][cap] = Math.max(v1, v2);
                }
            }
        }
        return dp[wts.length][Cap];
    }

    public static int unboundedKnapsack_rec(int[] wts, int[] vals, int indx, int cap) {
        if(cap == 0 || indx == -1) {
            return 0;
        }

        int v1 = 0;
        // yes call
        if(cap - wts[indx] >= 0) {
            v1 = unboundedKnapsack_rec(wts, vals, indx, cap - wts[indx]) + vals[indx];
        }
        // no call
        int v2 = unboundedKnapsack_rec(wts, vals, indx - 1, cap);

        return Math.max(v1, v2);
    }

    public static int unboundedKnapsack_memo(int[] wts, int[] vals, int indx, int cap, int[][] dp) {
        if(cap == 0 || indx == -1) {
            return dp[indx + 1][cap] = 0;
        }

        if(dp[indx + 1][cap] != 0) return dp[indx + 1][cap];

        int v1 = 0;
        // yes call
        if(cap - wts[indx] >= 0) {
            v1 = unboundedKnapsack_memo(wts, vals, indx, cap - wts[indx], dp) + vals[indx];
        }
        // no call
        int v2 = unboundedKnapsack_memo(wts, vals, indx - 1, cap, dp);

        return dp[indx + 1][cap] = Math.max(v1, v2);
    }

    public static int unoundedKnapsack_tab(int[] wts, int[] vals, int cap) {
        int[] dp = new int[cap + 1];

        // outer loop for box
        // inner loop for cap
        for(int i = 0; i < wts.length; i++) {
            for(int c = wts[i]; c <= cap; c++) {
                // no call
                int v1 = dp[c];
                // yes call
                int v2 = dp[c - wts[i]] + vals[i];

                dp[c] = Math.max(v1, v2);
            }
        }
        return dp[cap];
    }

    public static class Fpair implements Comparable<Fpair> {
        int val;
        int wt;
        Double frac;

        public Fpair(int val, int wt) {
            this.val = val;
            this.wt = wt;
            this.frac = val * 1.0 / wt;
        }

        @Override
        public int compareTo(Fpair other) {

            // this is wrong consition because we are running our pq on a double variable
            // return this.frac - other.frac;
            if(this.frac > other.frac) {
                return 1;
            } else if(this.frac < other.frac) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void printFractionalKnapsack(int[] wts, int[] vals, int cap) {
        PriorityQueue<Fpair> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < wts.length; i++) {
            pq.add(new Fpair(vals[i], wts[i]));
        }

        double profit = 0;
        while(pq.size() > 0 && cap > 0){
            Fpair rem = pq.remove();

            if(rem.wt <= cap) {
                profit += rem.val;
                cap -= rem.wt;
            } else {
                profit += rem.frac * cap;
                cap = 0;
            }
        } 

        System.out.println(profit);
    }

    public static void knapsack() {
    
    }

    // ~~~~~~~~~~~~~~~~~~~Count Binary Strings~~~~~~~~~~
    public static int countBinaryStrings_rec(int n, int le, String asf) {
        if(n == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        // check is to prevent repition of consecutive zero's 
        if(le == 1) {
            count += countBinaryStrings_rec(n - 1, 0, asf + " 0");
        }
        count += countBinaryStrings_rec(n - 1, 1, asf + " 1");
        return count;
    }

    public static int countBinaryStrings_memo(int n, int le, int[][] dp) {
        if(n == 0) {
            return dp[n][le] = 1;
        }

        if(dp[n][le] != 0) return dp[n][le];

        int count = 0;
        // check is to prevent repition of consecutive zero's 
        if(le == 1) {
            count += countBinaryStrings_memo(n - 1, 0, dp);
        }
        count += countBinaryStrings_memo(n - 1, 1, dp);
        return dp[n][le] = count;
    }

    public static int countBinaryString_tab(int N) {
        int[][] dp = new int[2][N];

        dp[0][0] = 1;
        dp[1][0] = 1;

        for(int n = 1; n < N; n++){
            dp[n][0] = dp[n - 1][1];
            dp[n][1] = dp[n - 1][0] + dp[n - 1][1];
        } 

        return dp[N - 1][0] + dp[N - 1][1];
    }

    public static int countBinaryString_optimise(int n) {
        int zero = 1;
        int one = 1;

        for(int i = 2; i <= n; i++) {
            int n_zero = one;
            int n_one = one + zero;

            zero = n_zero;
            one = n_one;
        }
        return one + zero;
    }


    public static void countBinaryString() {
        // int res = countBinaryStrings_rec(4, 1, "");
        int[][] dp = new int[5][2];
        int res = countBinaryStrings_memo(4, 1, dp);
        System.out.println(res);
    }  

    // ~~~~~~~~~~~~Arrange Buildings~~~~~~~~~~~
    public static long arrangeBuildings(int n) {
        long building = 1;
        long space = 1;

        for(int i = 2; i <= n; i++) {
            long n_building = space;
            long n_space = building + space;

            space = n_space;
            building = n_building;
        }
        long res = building + space;
        return res * res;
    }

    // ~~~~~~~~~~~~~Count Encoding~~~~~~~~~~~
    public static int countEncoding_rec(String str, int indx) {
        if(indx == str.length()) {
            return 1;
        }
        if(str.charAt(indx) == '0') {
            return 0;
        }

        int count = 0;
        int n1 = str.charAt(indx) - '0';
        count += countEncoding_rec(str, indx + 1);
        if(indx + 1 < str.length()) {
            int n = str.charAt(indx + 1) - '0';
            int n2 = n1 * 10 + n;
            if(n2 <= 26) {
                count += countEncoding_rec(str, indx + 2);
            }
        }
        return count;
    }

    public static int countEncoding_memo(String str, int indx, int[] dp) {
        if(indx == str.length()) {
            return dp[indx] = 1;
        }
        if(str.charAt(indx) == '0') {
            return dp[indx] = 0;
        }

        if(dp[indx] != 0) {
            return dp[indx];
        }

        int count = 0;
        int n1 = str.charAt(indx) - '0';
        count += countEncoding_memo(str, indx + 1, dp);
        if(indx + 1 < str.length()) {
            int n = str.charAt(indx + 1) - '0';
            int n2 = n1 * 10 + n;
            if(n2 <= 26) {
                count += countEncoding_memo(str, indx + 2, dp);
            }
        }
        return dp[indx] = count;
    }

    public static int countEncoding_tab1(String str) {
        int[] dp = new int[str.length() + 1];
        for(int indx = str.length(); indx >= 0; indx--) {
            if(indx == str.length()) {
                dp[indx] = 1;
                continue;
            }
            if(str.charAt(indx) == '0') {
                dp[indx] = 0;
                continue;
            }

            int count = 0;
            int n1 = str.charAt(indx) - '0';
            count += dp[indx + 1]; // countEncoding_memo(str, indx + 1, dp);
            if(indx + 1 < str.length()) {
                int n = str.charAt(indx + 1) - '0';
                int n2 = n1 * 10 + n;
                if(n2 <= 26) {
                    count += dp[indx + 2]; // countEncoding_memo(str, indx + 2, dp);
                }
            }
            dp[indx] = count;
        }
        return dp[0];
    }

    public static void countEncoding() {
        // int res = countEncoding_rec("12323", 0);
        int[] dp = new int[6];
        int res = countEncoding_memo("12323", 0, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~Count A^i B^j C^k ~~~~~~~~~~~~~~~
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
                c_count = 2 * b_count + c_count;
            }
        }

        return c_count;
    }

    // ~~~~~~~~~~~~~Max Sum Non Adjacent Elements~~~~~~~~~~~~
    public static int maxSumNonAdjacentEle_rec(int[] arr, int indx, int status) {
        if(indx == -1) {
            return 0;
        }
        int maxSum = (int)-1e9;
        if(status == 0) {
            // this is call of include
            maxSum = maxSumNonAdjacentEle_rec(arr, indx - 1, 1) + arr[indx];
        }
        // exclude call
        maxSum = Math.max(maxSum, maxSumNonAdjacentEle_rec(arr, indx - 1, 0));
        return maxSum;
    }

    public static int maxSumNonAdjacentEle_memo(int[] arr, int indx, int status, int[][] dp) {
        if(indx == -1) {
            return dp[status][indx + 1] = 0;
        }

        if(dp[status][indx + 1] != 0) return dp[status][indx + 1];

        int maxSum = (int)-1e9;
        if(status == 0) {
            // this is call of include
            maxSum = maxSumNonAdjacentEle_memo(arr, indx - 1, 1, dp) + arr[indx];
        }
        // exclude call
        maxSum = Math.max(maxSum, maxSumNonAdjacentEle_memo(arr, indx - 1, 0, dp));
        return dp[status][indx + 1] =  maxSum;
    }

    public static int maxSumNonAdjacentEle_tab_optimise(int[] arr) {
        int include = 0;
        int exclude = 0;

        for(int i = 0; i < arr.length; i++) {
            int n_include = exclude + arr[i];
            int n_exclude = Math.max(include, exclude);

            include = n_include;
            exclude = n_exclude;
        }

        return Math.max(include, exclude);
    }
    
    public static void MaxSumNonAdjacentEle() {
        int[] arr = {5, 10, 10, 100, 5, 6};
        // int res = maxSumNonAdjacentEle_rec(arr, arr.length - 1, 0);
        int n = arr.length;
        int[][] dp = new int[2][n + 1];
        int res = maxSumNonAdjacentEle_memo(arr, n - 1, 0, dp);
        System.out.println(res);
    }

    // ~~~~~~~~~~~~~~~~~Paint House~~~~~~~~~~~~~~
    public static int paintHouse(int[][] cost) {

        int red = 0;
        int green = 0;
        int blue = 0;

        for(int i = 0; i < cost.length; i++) {
            // red -> index = 0
            int n_red = Math.min(green, blue) + cost[i][0];
            // green -> index = 1
            int n_green = Math.min(blue, red) + cost[i][1];
            // blue -> index = 2
            int n_blue = Math.min(green, red) + cost[i][2];

            red = n_red;
            green = n_green;
            blue = n_blue;
        }
        return Math.min(red, Math.min(blue, green));
    }

    public static int paintHouseManyColors(int[][] cost) {
        int n = cost.length;
        int k = cost[0].length;
        int[][] dp = new int[n][k];

        int min1 = (int)1e9; // min value
        int min2 = (int)1e9; // second min value

        for(int i = 0; i < n; i++) {
            int min11 = (int)1e9; // new min value
            int min22 = (int)1e9; // new second min value
            for(int j = 0; j < k; j++) {
                if(i == 0) {
                    dp[i][j] = cost[i][j];
                } else {
                    if(dp[i - 1][j] != min1) {
                        dp[i][j] = cost[i][j] + min1;
                    } else {
                        dp[i][j] = cost[i][j] + min2;
                    }
                }
                if(dp[i][j] <= min11) {
                    min22 = min11;
                    min11 = dp[i][j];
                } else if(dp[i][j] < min22) {
                    min22 = dp[i][j];
                }
            }
            min1 = min11;
            min2 = min22;
        }
        return min1;
    }

    public static long paintFence(int n, int k) {
        long same = 0;
        long distinct = k;

        for(int i = 1; i < n; i++) {
            long n_same = distinct;
            long n_distinct = (same + distinct) * (k - 1);

            same = n_same;
            distinct = n_distinct;
        }

        return same + distinct;
    }

    public static void paintHouse() {

    }

    // ~~~~~~~~~Tiling Questions~~~~~~~~~~~
    public static long tiling2X1(int n) {
        long a = 1;
        long b = 2;
        // long is logically correct but for portal we have to use int
        for(int i = 1; i < n; i++) {
            long c = a + b;
            a = b;  
            b = c;
        }

        return a;
    }

    public static long tilingMX1(int n, int m) {
        long[] dp = new long[n + 1];

        for(int i = 0; i <= n; i++) {
            if(i < m) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + dp[i - m];
            }
        }
        return dp[n];
    }

    // ~~~~~~~~~~~~~~~~Friends Pairing~~~~~~~~~~~~~~~
    public static int friendsPair(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            //         single + pairing 
            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        }
        return dp[n];
    }

    // ~~~~~~~~~~~~~~~Partition into K Subset~~~~~~~~
    public static long partitionKSubset(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= i && j <= k; j++) {
                if(j == 0) {
                    dp[i][j] = 0;
                } else if(i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + j * dp[i - 1][j];
                }
            }
        }
        return dp[n][k];
    }
    
    // ~~~~~~~~~~~~~~~~Stock Buy Sell~~~~~~~~~~~~~~~~
    public static int stockBuySellOneTransaction(int[] price) {
        int profit = 0;
        int minPrice = price[0];
        for(int day = 1; day < price.length; day++) {
            profit = Math.max(profit, price[day] - minPrice);
            minPrice = Math.min(minPrice, price[day]);
        }
        return profit;
    }

    public static int stockBuySellInfiniteTransaction(int[] price) {
        int profit = 0;
        int bd = 0; // buying day
        int sd = 0; // selling day
        for(int i = 1; i < price.length; i++) {
            if(price[i - 1] > price[i]) {
                profit += price[sd] - price[bd];
                sd = bd = i;
            } else {
                sd = i;
            }
        }
        profit += price[sd] - price[bd];
        return profit;
    }

    public static int stockBuySellInfiniteTransactionWithFee(int[] price, int fees) {
        int pwb = -price[0];    // profit with buy
        int pws = 0;            // profit with sell    

        for(int i = 1; i < price.length; i++) {
            int npwb = Math.max(pwb, pws - price[i]); // new price with buy
            int npws = Math.max(pws, pwb + price[i] - fees); // new profit with sell

            pwb = npwb;
            pws = npws;
        }
        return pws;
    }

    public static void ques() {
        MaxSumNonAdjacentEle();

        // countEncoding();
        // countBinaryString();
        // coinChange();
        // targetSumSubset();
        // goldmine();
        // mazePath();
        // climbStair();
        // fib();
    }

    public static void main(String[] args) {
        ques();
    }
}
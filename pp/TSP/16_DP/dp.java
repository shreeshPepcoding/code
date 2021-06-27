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
    


    public static void ques() {
        coinChange();
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
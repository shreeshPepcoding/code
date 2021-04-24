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


    public static void ques() {
        
        climbStairs();
        // fib();
    }

    public static void main(String[] args) {
        ques();
    }
}
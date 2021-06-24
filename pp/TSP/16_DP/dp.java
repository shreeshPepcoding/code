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
        if(n == 0 || n == 1) {
            return dp[n] = n;
        }
        // 1. if Problem is already solved, then return the answer        
        if(dp[n] != 0) {
            return dp[n];
        }

        int fibnm1 = fib_memo(n - 1, dp);
        int fibnm2 = fib_memo(n - 2, dp);
        
        int fibn = fibnm1 + fibnm2;
        // 2. If not solved, then solve the problem and store it in the dp
        return dp[n] = fibn;
    }   

    public static int fib_tab2(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0 || n == 1) {
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

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int fib_btr(int n) {
        int a = 0;
        int b = 1;

        while(n-- > 0) {
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
        if(n == 0) {
            return 1;
        }

        int count = 0;
        for(int j = 1; j <= 3; j++) {
            if(n - j >= 0) {
                count += climbStair_rec(n - j);
            }
        }
        return count;
    }

    public static int climbStair_memo(int n, int[] dp) {
        if(n == 0) {
            return dp[0] = 1;
        }
 
        if(dp[n] != 0) {
            return dp[n];
        }

        int count = 0;
        for(int j = 1; j <= 3; j++) {
            if(n - j >= 0) {
                count += climbStair_memo(n - j, dp);
            }
        }
        return dp[n] = count;
    }

    // purely tabulation
    public static int climbStair_tab1(int n, int[] dp) {
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            if(i >= 3) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            } else if(i >= 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }

    // convert code from memoisation to tabulation
    public static int climbStair_tab2(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0) {
                dp[0] = 1;
                continue;
            }
    
            int count = 0;
            for(int j = 1; j <= 3; j++) {
                if(n - j >= 0) {
                    count += dp[n - j]; // climbStair_memo(n - j, dp);
                }
            }
            dp[n] = count;
        }
        return dp[N];
    }

    // i-> current stair, n-> total stair, jumps[] -> jumps allowed at ith stair
    public static int climbStair_VarJumps_rec(int i, int n, int[] jumps) {
        if(i == n) {
            return 1;
        }
        int count = 0;
        for(int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbStair_VarJumps_rec(i + jump, n, jumps);
        }
        return count;
    }

    public static int climbStair_VarJumps_memo(int i, int n, int[] jumps, int[] dp) {
        if(i == n) {
            return dp[i] = 1;
        }

        if(dp[i] != 0) {
            return dp[i];
        }

        int count = 0;
        for(int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbStair_VarJumps_memo(i + jump, n, jumps, dp);
        }
        return dp[i] = count;
    }

    public static int climbStair_VarJumps_tab(int i, int n, int[] dp, int[] jumps) {
        for(i = n; i >= 0; i--) {
            if(i == n) {
                dp[i] = 1;
                continue;
            }
    
            int count = 0;
            for(int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
                count += dp[i + jump]; //climbStair_VarJumps_memo(i + jump, n, jumps, dp);
            }
            dp[i] = count;
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

    public static void ques() {
        climbStair();
        // fib();
    }

    public static void main(String[] args) {
        ques();
    }
}
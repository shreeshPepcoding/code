import java.util.*;

public class dp {

    // stock buy and sell, one transaction
    private static void stockBuySell1(int[] stocks) {
        int oprofit = 0; // overall profit
        int min = Integer.MAX_VALUE;
        for(int d = 0; d < stocks.length; d++) {
            int price = stocks[d];
            min = Math.min(min, price);
            oprofit = Math.max(oprofit, price - min);
        }
        System.out.println(oprofit);
    }

    // stock buy and sell, infinite transaction
    private static void stockBuySell2(int[] price) {
        int bd = 0;
        int sd = 0;
        int profit = 0;

        for(int i = 1; i < price.length; i++) {
            if(price[i] > price[i - 1]) {
                sd++;
            } else {
                profit += price[sd] - price[bd];
                // reset bd and sd
                bd = i;
                sd = i;
            }
        }
        // is peak is present in price int end of days
        profit += price[sd] - price[bd];
        System.out.println(profit);
    }

    // stock buy and sell with transaction fees, infinite transaction
    private static void stockBuySell3(int[] price, int fees) {
        int pibt = -price[0]; // pibt -> profit if buy today
        int pist = 0; // initially 0, pist-> profit if sell today
        for(int i = 1; i < price.length; i++) {
            int new_pibt = Math.max(pibt, pist - price[i]);
            int new_pist = Math.max(pist, price[i] + pibt - fees);
            pibt = new_pibt;
            pist = new_pist;
        }
        System.out.println(pist);
    }

    // stock buy and sell with cooldown, infinite transaction
    private static void stockBuySell4(int[] price) {
        int pibt = -price[0]; // pibt -> profit if buy today
        int pist = 0; // initially 0, pist-> profit if sell today
        int pwcd = 0; // pwcd -> profit with cooldown
        for(int i = 1; i < price.length; i++) {
            int new_pibt = Math.max(pibt, pwcd - price[i]);
            pwcd = pist;
            int new_pist = Math.max(pist, price[i] + pibt);
            pibt = new_pibt;
            pist = new_pist;
        }
        System.out.println(pist);
    }

    // stock buy and sell, two transaction
    private static void stockBuySell5(int[] price) {
        int[] profitFromSellToday = new int[price.length];
        int profit = 0;

        // fill from left to right -> profit if we sell stock today
        int min = price[0];
        for(int i = 1; i < price.length; i++) {
            min = Math.min(min, price[i]);
            profitFromSellToday[i] = Math.max(profitFromSellToday[i - 1], price[i] - min);
        }

        // fill from right to lef in same array
        int max = Integer.MIN_VALUE;
        for(int i = price.length - 1; i >= 0; i--) {
            max = Math.max(max, price[i]);
            int profitFromBuyToday = max - price[i];
            profit = Math.max(profit, profitFromSellToday[i] + profitFromBuyToday);
        }
        System.out.println(profit);
    }

    // stock buy and sell, K transaction
    private static void stockBuySell6(int[] price, int K) {
        int[][] dp = new int[K + 1][price.length];

        for(int i = 1; i < dp.length; i++) {
            int max = -price[0];
            for(int j = 1; j < dp[0].length; j++) {
                // int pitt = 0; // pitt -> profit if transaction today
                int pitt = max + price[j];
                int pintt = dp[i][j - 1]; // pintt -> profit if no transaction today
                // for(int k = j - 1; k >= 0; k--) {
                //     pitt = Math.max(pitt, price[i] - price[k] + dp[i - 1][k]);
                // }
                dp[i][j] = Math.max(pitt, pintt);
                // prepare max for next iteration
                max = Math.max(max, dp[i - 1][j] - price[j]);
            }
        }
        System.out.println(dp[K][price.length - 1]);
    }

    // longest increasing subsequence
    private static int lis(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int ans = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // maximum sum increasing subsequence
    private static int maxSumLIS(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int ans = arr[0];
        for(int i = 1; i < arr.length; i++) {
            // int max = 0;
            int max = Integer.MIN_VALUE;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] <= arr[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            // dp[i] = max + arr[i];
            if(max == Integer.MIN_VALUE) {
                dp[i] = arr[i];
            } else {
                dp[i] = max + arr[i];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // longest decreasing subsequence
    private static int lds(int[] arr) {
        int[] dp = new int[arr.length];
        dp[arr.length - 1] = 1;
        int ans = 1;
        for(int i = arr.length - 2; i >= 0; i--) {
            int max = 0;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    public static void main(String[] args) {

    }
}
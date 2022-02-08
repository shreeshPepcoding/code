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

    // lbs -> longest bitonic subsequence
    private static int lbs(int[] arr) {
        int[] lis_ = lis(arr); // make a function of LIS and LDS which return DP rather than answer
        int[] lds_ = lds(arr);

        int ans = 0;
        for(int i = 0; i < arr.length; i++) {
            Math.max(ans, lis_[i] + lds_[i] - 1);
        }
        return ans;
    }

    // maximm non overlapping bridges
    private static class Bridges implements Comparable<Bridges>{
        int n;
        int s;

        public Bridges(int n, int s) {
            this.n = n;
            this.s = s;
        }

        public int compareTo(Bridges o) {
            if(this.n != o.n) {
                return this.n - o.n;
            } else {
                return this.s - o.s;
            }
        }
    }

    private static int nonOverlappingBridges(int[][] bridges) {
        Bridges[] arr = new Bridges[bridges.length];
        for(int i = 0; i < bridges.length; i++) {
            arr[i] = new Bridges(bridges[i][0], bridges[i][1]);
        }

        // ort north pole
        Arrays.sort(arr);
        // LIS on south pole
        int[] dp = new int[arr.length];
        int ans = 1;
        dp[0] = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j].s < arr[i].s) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // russian doll envelope
    // code is exactle same as above one, i.e. Non overlapping bridges

    // box stacking, link : https://practice.geeksforgeeks.org/problems/box-stacking/1/
    private static class Box implements Comparable<Box> {
        int l;
        int b;
        int h;
        int area;

        public Box(int l, int b, int h) {
            this.l = l;
            this.b = b;
            this.h = h;
            this.area = this.l * this.b;
        }

        public int compareTo(Box o) {
            if(o.area != this.area)
                return o.area - this.area;
            else
                return o.h - this.h;
        }
    }

    public static int maxHeight(int[] height, int[] width, int[] length, int n) {
        Box[] arr = new Box[3 * n];
        int j = 0;
        for(int i = 0; i < n; i++) {
            // LBH
            if(length[i] > width[i]) {
                arr[j++] = new Box(length[i], width[i], height[i]);
            } else {
                arr[j++] = new Box(width[i], length[i], height[i]);
            }
            // HBL
            if(height[i] > width[i]) {
                arr[j++] = new Box(height[i], width[i], length[i]);
            } else {
                arr[j++] = new Box(width[i], height[i], length[i]);
            }
            // LHB
            if(length[i] > height[i]) {
                arr[j++] = new Box(length[i], height[i], width[i]);
            } else {
                arr[j++] = new Box(height[i], length[i], width[i]);
            }
        }
        Arrays.sort(arr);
        int[] dp = new int[3 * n];
        dp[0] = arr[0].h;
        int maxHeight = dp[0];
        for(int i = 1; i < 3 * n; i++) {
            int max = 0;
            for(int k = i - 1; k >= 0; k--) {
                if(arr[k].l > arr[i].l && arr[k].b > arr[i].b) {
                    max = Math.max(max, dp[k]);
                }
            }
            dp[i] = max + arr[i].h;
            maxHeight = Math.max(maxHeight, dp[i]);
        }
        return maxHeight;
    }

    // leetcode 1691, https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
    public int maxHeight(int[][] cuboids) {
        Box[] arr = new Box[cuboids.length];
        int j = 0;
        for(int[] box : cuboids) {
            Arrays.sort(box);
            arr[j++] = new Box(box[0], box[1], box[2]);
        }
        Arrays.sort(arr);
        int[] dp = new int[arr.length];
        dp[0] = arr[0].h;
        int maxHeight = dp[0];
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int k = i - 1; k >= 0; k--) {
                if(arr[k].l >= arr[i].l && arr[k].b >= arr[i].b && arr[k].h >= arr[i].h) {
                    max = Math.max(max, dp[k]);
                }
            }
            dp[i] = max + arr[i].h;
            maxHeight = Math.max(maxHeight, dp[i]);
        }
        return maxHeight;
    }

    // leetcode 646, https://leetcode.com/problems/maximum-length-of-pair-chain/
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b)->{
            return a[1] - b[1];
        });

        int[] dp = new int[pairs.length];
        dp[0] = 1;
        int maxLength = 1;
        for(int i = 1; i < pairs.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(pairs[j][1] < pairs[i][0]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    // leetcode 647, https://leetcode.com/problems/palindromic-substrings/
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0) {
                    dp[i][j] = true;
                } else if(gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if(dp[i][j] == true) count++;
            }
        }
        return count;
    }

    // leetcode 5, https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int x = 0;
        int y = 0;
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0) {
                    dp[i][j] = true;
                } else if(gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if(dp[i][j] == true) {
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y + 1);
    }

    public static void main(String[] args) {

    }
}
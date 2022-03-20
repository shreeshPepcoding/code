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

    // print path of min jumps
    private static class MJHelper {
        int indx;
        int jumps;
        int minJumps;
        String psf;
        public MJHelper(int indx, int jumps, int minJumps, String psf) {
            this.indx = indx;
            this.jumps = jumps;
            this.minJumps = minJumps;
            this.psf = psf;
        }
    }

    public static void printPathMinJumps(int arr[]){
        int n = arr.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        
        for(int i = n - 2; i >= 0; i--) {
            int minJumps = Integer.MAX_VALUE;
            for(int jump = 1; jump <= arr[i] && i + jump < n; jump++) {
                minJumps = Math.min(minJumps, dp[i + jump]);
            }
            dp[i] = minJumps != Integer.MAX_VALUE? minJumps + 1: minJumps;
        }

        System.out.println(dp[0]);
        Queue<MJHelper> que = new LinkedList<>();
        que.add(new MJHelper(0, arr[0], dp[0], "0"));

        while(que.size() > 0) {
            MJHelper rem = que.remove();
            if(rem.indx == n - 1) {
                System.out.println(rem.psf + " .");
                continue;
            }
            for(int jump = 1; jump < rem.jumps && jump + rem.indx < n; jump++) {
                if(dp[rem.indx + jump] == rem.minJumps - 1) {
                    int nindx = rem.indx + jump;
                    que.add(new MJHelper(nindx, arr[nindx], rem.minJumps - 1, rem.psf + " -> "+ nindx));
                }
            }
        }
    }

    // print all path for minimum cost in grid
    private static class Pair {
        String psf;
        int i;
        int j;
        
        public Pair(String psf, int i, int j) {
            this.psf = psf;
            this.i = i;
            this.j = j;
        }
    }
    
    private static void printAllMinCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        
        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                if(i == n - 1 && j == m - 1) {
                    dp[i][j] = grid[i][j];
                } else if(i == n -1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if(j == m - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        System.out.println(dp[0][0]);

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair("", 0, 0));
        while(que.size() > 0) {
            Pair rem = que.remove();
            int i = rem.i;
            int j = rem.j;

            if(i == n - 1 && j == m - 1) {
                System.out.println(rem.psf);
                continue;
            } else if(i == n - 1) {
                que.add(new Pair(rem.psf + "H", i, j + 1));
            } else if(j == m - 1) {
                que.add(new Pair(rem.psf + "V", i + 1, j));
            } else {
                if(dp[i + 1][j] == dp[i][j + 1]) {
                    // add H
                    que.add(new Pair(rem.psf + "H", i, j + 1));
                    // add V
                    que.add(new Pair(rem.psf + "V", i + 1, j));
                } else if(dp[i + 1][j] < dp[i][ j + 1]) {
                    que.add(new Pair(rem.psf + "V", i + 1, j));
                } else {
                    que.add(new Pair(rem.psf + "H", i, j + 1));
                }
            }
        }
    } 
    
    // print all path in goldmine
    private static int[] xarr = {-1, 0, 1};
    private static int[] yarr = {1, 1, 1};

    private static int maxInGoldmine(int x, int y, int[][] dp) {
        int max = Integer.MIN_VALUE;
        for(int d = 1; d <= 3; d++) {
            int r = x + xarr[d - 1];
            int c = y + yarr[d - 1];

            if(r >= 0 && r < dp.length && c >= 0 && c < dp[0].length) {
                max = Math.max(max, dp[r][c]);
            }
        }
        return max;
    }

    private static void printPathGoldmine(int[][] mine) {
        int n = mine.length;
        int m = mine[0].length;

        int[][] dp = new int[n][m];
        int max = Integer.MIN_VALUE;
        for(int c = m - 1; c >= 0; c--) {
            for(int r = 0; r < n; r++) {
                if(c == m - 1) {
                    dp[r][c] = mine[r][c];
                } else if(r == 0) { // compare D2 and D3
                    dp[r][c] = Math.max(dp[r][c + 1], dp[r + 1][c + 1]) + mine[r][c];
                } else if(r == n - 1) { // compare D1 and D2
                    dp[r][c] = Math.max(dp[r - 1][c + 1], dp[r][c + 1]) + mine[r][c];
                } else { // compare D1, D2, and D3
                    dp[r][c] = Math.max(dp[r - 1][c + 1], Math.max(dp[r][c + 1], dp[r + 1][c + 1])) + mine[r][c];
                }
                max = Math.max(max, dp[r][c]);
            }
        }
        System.out.println(max);

        Queue<Pair> que = new LinkedList<>();
        for(int r = 0; r < n; r++) {
            if(dp[r][0] == max) {
                que.add(new Pair(""  + r + " ", r, 0));
            }
        }

        while(que.size() > 0) {
            Pair rem = que.remove();
            int i = rem.i;
            int j = rem.j;

            if(j == m - 1) {
                System.out.println(rem.psf);
                continue;
            } else {
                int maxInDiagonal = maxInGoldmine(i, j, dp);
                for(int d = 1; d <= 3; d++) {
                    int r = i + xarr[d - 1];
                    int c = j + yarr[d - 1];
                    if(r >= 0 && r < dp.length && c >= 0 && 
                        c < dp[0].length && dp[r][c] == maxInDiagonal) {
                        que.add(new Pair(rem.psf + "d" + d + " ", r, c));
                    }
                }
            }
        }
    }

    // print all path with Target Sum Subset
    private static void printPathtargetSumSubset(int[] arr, int tar) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][tar + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= tar; j++) {
                if(j == 0) {
                    dp[i][j] = true;
                } else if(i == 0) {
                    dp[i][j] = false;
                } else {
                    if(j < arr[i - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                    }
                }
            }
        }
        System.out.println(dp[n][tar]);
        Queue<Pair> que = new LinkedList<>();
        if(dp[n][tar] == true) {
            que.add(new Pair("", n, tar));
        }
        while(que.size() > 0) {
            Pair rem = que.remove();
            int i = rem.i;
            int j = rem.j;
            if(j == 0) {
                System.out.println(rem.psf);
                continue;
            } else if(i == 0) {
                continue;
            }
            // yes call
            if(j - arr[i - 1] >= 0 && dp[i - 1][j - arr[i - 1]] == true) {
                que.add(new Pair((i - 1) + " " +rem.psf, i - 1, j - arr[i - 1]));
            }
            // no call
            if(dp[i - 1][j] == true) {
                que.add(new Pair(rem.psf, i - 1, j));
            }
        }
    }
    
    // print all possible result in 0-1 Knapsacks
    private static void print01Knapsack(int[] value, int[] wts, int cap) {
        int n = value.length;
        int[][] dp = new int[n + 1][cap + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= cap; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if(j < wts[i - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {    
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wts[i - 1]] + value[i - 1]);
                    }
                }
            }
        }
        System.out.println(dp[n][cap]);

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair("", n, cap));
        while(que.size() > 0) {
            Pair rem = que.remove();
            int i = rem.i;
            int j = rem.j;
            if(j == 0) {
                System.out.println(rem.psf);
                continue;
            } else if(i == 0) {
                continue;
            }
            // yes call
            if(j - wts[i - 1] >= 0 && dp[i - 1][j - wts[i - 1]] == dp[i][j] - value[i - 1]) {
                que.add(new Pair(wts[i - 1] + " " + rem.psf, i - 1, j - wts[i - 1]));
            }
            // no call
            if(dp[i - 1][j] == dp[i][j]) {
                que.add(new Pair(rem.psf, i - 1, j));
            }
        }
    }

    // fractional Knapsack
    private static class KnapHelper implements Comparable<KnapHelper> {
        int val;
        int wt;
        double val_by_wt;
        KnapHelper(int val, int wt) {
            this.val = val;
            this.wt = wt;
            this.val_by_wt = (val * 1.0) / wt;
        }
        public int compareTo(KnapHelper o) {
            if(this.val_by_wt > o.val_by_wt) {
                return 1;
            } else if(this.val_by_wt < o.val_by_wt) {
                return -1;
            } else {
                return 0;
            }
            // return Double.compare(this.val_by_wt, o.val_by_wt);
        }
    }

    private static double fractionalKnapsack(int[] val, int[] wts, int cap) {
        PriorityQueue<KnapHelper> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < val.length; i++) {
            pq.add(new KnapHelper(val[i], wts[i]));
        }
        double profit = 0;
        while(pq.size() > 0) {
            KnapHelper rem = pq.remove();
            if(rem.wt <= cap) {
                // consume all wt
                cap -= rem.wt;
                profit += rem.val;
            } else {
                // consume according to fraction, we have to exahuast capacity to 0
                profit += cap * rem.val_by_wt;
                cap = 0;
            }
            if(cap == 0) break;
        }
        return profit;
    }

    // leetcode 279, https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; 
        for(int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int count = 1;
            while(i - (count * count) >= 0) {
                min = Math.min(min, dp[i - count * count]);
                count++;
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    // print all Longest Increasing Subsequence
    public static class LISHelper {
        int l;
        int i;
        int v;
        String psf;
        
        LISHelper(int l, int i, int v, String psf){
            this.l = l;
            this.i = i;
            this.v = v;
            this.psf = psf;
        }
    }

    public static void printAllLIS(int []arr){
        int n = arr.length;
        int[] dp = new int[n];

        dp[0] = 1;
        int maxIndx = 0;
        for(int i = 1; i < n; i++) {
            int maxLen = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) {
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
            dp[i] = maxLen + 1;
            if(dp[maxIndx] < dp[i]) {
                maxIndx = i;
            }
        }

        Queue<LISHelper> qu = new LinkedList<>();
        for(int i = n - 1; i >= 0; i--) {
            if(dp[i] == dp[maxIndx]) {
                qu.add(new LISHelper(dp[maxIndx], i, arr[i], arr[i] + ""));
            }
        }

        while(qu.size() > 0) {
            LISHelper rem = qu.remove();
            if(rem.l == 1) {
                System.out.println(rem.psf);
                continue;
            }

            for(int j = rem.i - 1; j >= 0; j--) {
                if(dp[j] == rem.l - 1 && arr[j] < arr[rem.i]) {
                    qu.add(new LISHelper(dp[j], j, arr[j], arr[j] + " -> " + rem.psf));
                }
            }
        }
    }

    // largest square from 1s, leetcode 221(Maximal Rectangle, return area rather than length)
    public static int largestSquarefrom1s(int[][] arr) {
		int max = 0;
        int r = arr.length;
        int c = arr[0].length;
        int[][] dp = new int[r][c];
        for(int i = r - 1; i >= 0; i--) {
            for(int j = c - 1; j >= 0; j--) {
                if(i == r - 1 || j == c - 1 || arr[i][j] == 0) {
                    dp[i][j] = arr[i][j];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
	}

    // catalan number
    private static int catalanNumber(int n) {
        if(n == 0 || n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            int i1 = 0;
            int i2 = i - 1;
            int sum = 0;
            for(; i1 < i; i1++, i2--) {
                sum += dp[i1] * dp[i2];
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    // possible number of BST from n nodes
    private static int numberOfBSTs(int n) {
        return catalanNumber(n - 2);
    }

    // possible number of mountain and valleys
    private static int countOfMountainAndValleys(int n) {
        return catalanNumber(n);
    }

    // count brackets
    private static int countBrackets(int n) {
        return catalanNumber(n);
    }

    // chords and circle
    public static int NumberOfChords(int n){
        return catalanNumber(n);
    }

    // number of ways of triangulation
    public static int numberOfWaysOfTriangulation(int n){
		return catalanNumber(n);
	}

    // highway billboard, method-1 using LIS technique

    // highway billboard, method-2 using distance
    public static int highwayBillboard(int m , int[] x, int[] rev, int t) {
        int[] dp = new int[m + 1];
        dp[0] = 0;
        int indx = 0;
        for(int i = 1; i <= m; i++) {
            if(indx < x.length && x[indx] == i) {
                int opt1 = dp[i - 1];
                int opt2 = (i - t - 1) >= 0 ? dp[i - t - 1] + rev[indx] : rev[indx];
                dp[i] = Math.max(opt1, opt2);
                indx++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[m];
    }

    // temple offerings
    public static int totaloffering(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        // left to right
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            if(height[i] > height[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }
        int sum = 0;
        // right to left
        int count = 1;
        dp[n - 1] = Math.max(count, dp[n - 1]);
        sum = dp[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            if(height[i] > height[i + 1]) {
                count++;
            } else {
                count = 1;
            }
            sum += Math.max(count, dp[i]);
        }
        return sum;
	}

    // longest palindromic subsequence
    private static int lps(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        
        for(int gap = 0; gap < n; gap++) {
            for(int r = 0, c = gap; c < n; r++, c++) {
                if(gap == 0) {
                    dp[r][c] = 1;
                } else if(gap == 1) {
                    dp[r][c] = str.charAt(r) == str.charAt(c) ? 2 : 1;
                } else {
                    if(str.charAt(r) == str.charAt(c)) {
                        dp[r][c] = dp[r + 1][c - 1] + 2;
                    } else {
                        dp[r][c] = Math.max(dp[r][c - 1], dp[r + 1][c]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    // count all palindromic subseq.
    private static int countLPS(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        
        for(int gap = 0; gap < n; gap++) {
            for(int r = 0, c = gap; c < n; r++, c++) {
                if(gap == 0) {
                    dp[r][c] = 1;
                } else if(gap == 1) {
                    dp[r][c] = str.charAt(r) == str.charAt(c) ? 3 : 2;
                } else {
                    if(str.charAt(r) == str.charAt(c)) {
                        dp[r][c] = dp[r][c - 1] + dp[r + 1][c] + 1;
                    } else {
                        dp[r][c] = dp[r][c - 1] + dp[r + 1][c] - dp[r + 1][c - 1];
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    // ugly number
    public static int uglyNumber(int n) {
        if(n == 1) return 1;

        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;    

        int indx = 1;
        while(indx < n) {
            int n1 = dp[p2] * 2;
            int n2 = dp[p3] * 3;
            int n3 = dp[p5] * 5;
            int min = Math.min(n1, Math.min(n2, n3));
            dp[indx] = min;
            if(n1 == min) {
                p2++;
            }
            if(n2 == min) {
                p3++;
            }
            if(n3 == min) {
                p5++;
            }
            indx++;
        }
        return dp[n - 1];
    }

    // longest common subseq.
    private static int lcs(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <= n2; j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]  + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    // count distinct subseq.
    private static int countDistinctSubseq(String str) {
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(str.charAt(0), 0);
        for(int i = 2; i <= n; i++) {
            char ch = str.charAt(i - 1);
            int prevVal = map.containsKey(ch) == true ? dp[map.get(ch)] : 0;
            dp[i] = 2 * dp[i - 1] - prevVal;
        }
        // subtract 1 for all no's
        return dp[n] - 1;
    }

    // count distinct palindrome subseq
    public static int countDistintcPalindromeSubseq(String str) {
		int n = str.length();
        int[] next = new int[n];
        int[] prev = new int[n];

        HashMap<Character, Integer> map = new HashMap<>();
        // set next,
        for(int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if(map.containsKey(ch) == true) {
                prev[i] = map.get(ch);
            } else {
                prev[i] = i;
            }
            map.put(ch, i);
        }

        map.clear();
        for(int i = n - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if(map.containsKey(ch) == true) {
                next[i] = map.get(ch);
            } else {
                next[i] = i;
            }
            map.put(ch, i);
        }
        int[][] dp = new int[n][n];
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0) {
                    dp[i][j] = 1;
                } else if(gap == 1) {
                    dp[i][j] = 2;
                } else {
                    if(str.charAt(i) == str.charAt(j)) {
                        int nextCharIndx = next[i];
                        int prevCharIndx = prev[j];
                        if(nextCharIndx == j && prevCharIndx == i) {
                            // no ch available in middle part
                            dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                        } else if(nextCharIndx == prevCharIndx) {
                            // single ch available in middle part
                            dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                        } else {
                            // more than one ch available
                            dp[i][j] = 2 * dp[i + 1][j - 1] - dp[nextCharIndx + 1][prevCharIndx - 1];
                        }
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[0][n - 1];
	}

    // longest repeating subseq.
    public static int longestRepeatingSubseq(String str){
        // code and logic is same as longest common subseq. 
        // but in equality check, make sure that i and j index is not equal

        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1]  + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
	}

    // longest common substring
    public static int longestCommonSubstring(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();

        int[][] dp = new int[n1 + 1][n2 + 1];
        int ans = 0;
        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <= n2; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
	}

    // arithmatic slices
    public static int arithmaticSlices1(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
               dp[i] = dp[i - 1] + 1;
               count += dp[i];
            }
        }
        return count;
	}

    // arithmatic slices 2
    public static int arithmaticSlices2(int[] arr) {
        int n = arr.length;
        HashMap<Long, Integer>[] dp = new HashMap[n];
        for(int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int count = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                long cd = (long)arr[i] - (long)arr[j];

                int countInI = dp[i].getOrDefault(cd, 0);
                int countInJ = dp[j].getOrDefault(cd, 0);
                
                count += countInJ;
                dp[i].put(cd, countInI + countInJ + 1);
            }
        }
        return count;
    }

    // burst ballons, leetcode 312, https://leetcode.com/problems/burst-balloons/
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        
        for(int g = 0; g < n; g++) {
            for(int x = 0, y = g; y < n; y++, x++)  {
                int nm1 = x == 0 ? 1 : nums[x - 1];
                int np1 = y == n - 1 ? 1 : nums[y + 1];
                int max = 0;
                for(int z = x; z <= y; z++) {
                    int firstHalf = z == 0 ? 0 : dp[x][z - 1];
                    int num = nums[z];
                    int secondHalf = z == n - 1 ? 0 : dp[z + 1][y];
                    
                    max = Math.max(max, firstHalf + (nm1 * num * np1) + secondHalf);
                }
                dp[x][y] = max;
            }
        } 
        return dp[0][n - 1];
    }
    

    public static void main(String[] args) {

    }
}
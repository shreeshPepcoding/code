import java.util.*;

public class dp {
    //~~~~~~~~~~~~~~~~~~~~~~Longest Increasing Subsequen    ce~~~~~~~~~~~~~~
    public static int lis(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int omax = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(dp[i] > omax)
                omax = dp[i];

        }
        return omax;
    }

    //~~~~~~~~~~~~~~~~~~Maximum Sum Increasing Subsequence~~~~~~~~~~~~~~
    public static int maxSumLIS(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int omax = arr[0];
        for(int i = 1; i < arr.length; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] <= arr[i] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + arr[i];
            if(dp[i] > omax)
                omax = dp[i];

        }
        return omax;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~Longest Bitonic Subsequence~~~~~~~~~~~~~~~~
    public static int lbs(int[] arr) {
        // find lis
        int[] dp1 = new int[arr.length];
        dp1[0] = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] <= arr[i] && dp1[j] > max) {
                    max = dp1[j];
                }
            }
            dp1[i] = max + 1;
        }

        // find lis from right to left -> overall it is LDS  but order wise not correct
        int[] dp2 = new int[arr.length];
        dp2[arr.length - 1] = 1;
        for(int i = arr.length - 2; i >= 0; i--) {
            int max = 0;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] <= arr[i] && dp2[j] > max) {
                    max = dp2[j];
                }
            }
            dp2[i] = max + 1;
        }

        // find LBS using loop
        int omax = 0;
        for(int i = 0; i < dp1.length; i++) {
            omax = Math.max(omax, dp1[i] + dp2[i] - 1);
        }
        return omax;
    }

    //~~~~~~~~~~~~~~~~~~~~~Maximum Non-Overlapping Bridges~~~~~~~~~~~~~~
    public static class bridges implements Comparable<bridges>{
        int st = 0;
        int end = 0;

        public bridges(int st, int end) {
            this.st = st;
            this.end = end;
        }

        public int compareTo(bridges o) {
            if(this.st == o.st) {
                return this.end - o.end;
            }
            return this.st - o.st;
        }
    }

    public static int nonOverlappingBridges(int[] start, int[] end) {
        bridges[] arr = new bridges[start.length];

        for(int i = 0; i < start.length; i++) {
            arr[i] = new bridges(start[i], end[i]);
        }

        Arrays.sort(arr);

        // lis on the basis of end coordinate of bridges
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int omax = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j].end <= arr[i].end && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(dp[i] > omax)
                omax = dp[i];

        }
        return omax;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~Russian Dolls Envelope~~~~~~~~~~~~~~~~~~~~
    public static class envelopes implements Comparable<envelopes>{
        int width = 0;
        int height = 0;

        public envelopes(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int compareTo(envelopes o) {
            if(this.width == o.width) {
                return this.height - o.height;
            }
            return this.width - o.width;
        }
    }

    public static int russianDollsEnvelope(int[] width, int[] height) {
        envelopes[] arr = new envelopes[width.length];

        for(int i = 0; i < width.length; i++) {
            arr[i] = new envelopes(width[i], height[i]);
        }

        Arrays.sort(arr);

        // lis on the basis of end coordinate of bridges
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int omax = 1;
        for(int i = 1; i < arr.length; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j].height <= arr[i].height && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(dp[i] > omax)
                omax = dp[i];

        }
        return omax;
    }


    public static void ques() {

    }
    
    public static void main(String[] args) {
        ques();
    }
}
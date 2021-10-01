import java.util.*;

public class arrays {

    // leetcode 925. https://leetcode.com/problems/long-pressed-name/
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()) return false;

        int i = 0; // i is for name
        int j = 0; // j is for typed
        while(i < name.length() && j < typed.length()) {
            if(name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if(i - 1 >= 0 && name.charAt(i - 1) == typed.charAt(j)) {
                    j++;
                } else {
                    return false;
                }
            }
        }

        while(j < typed.length()) {
            if(typed.charAt(j) != name.charAt(i - 1)) 
                return false;
            j++;
        }
        
        return i == name.length();
    }

    // leetcode 11. https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int maxWater = 0;

        int i = 0;
        int j = height.length - 1;

        while(i < j) {
            // with i, j pair, water holding capacity
            int l = j - i;
            int h = Math.min(height[i], height[j]);
            int water = l * h;

            maxWater = Math.max(maxWater, water);

            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxWater;
    }

    // leetcode 977 https://leetcode.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        // o(n) time, O(n) space
        int n = nums.length;
        int[] res = new int[n];
        int i = 0;
        int j = n - 1;
        
        for(int k = n - 1; k >= 0; k--) {
            int val1 = nums[i] * nums[i];
            int val2 = nums[j] * nums[j];

            if(val1 > val2) {
                res[k] = val1;
                i++;
            } else {
                res[k] = val2;
                j--;
            }
        }
        return res;
    }

    // leetcode 169. https://leetcode.com/problems/majority-element/ 
    public int majorityElement1(int[] nums) {
        int val = nums[0];
        int count = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == val) {
                count++;
            } else {
                if(count > 0) {
                    // pairing for distinct elements
                    count--;
                } else {
                    // reset
                    val = nums[i];
                    count = 1;
                }
            }
        }
        return val;
    }

    // leetcode 229. https://leetcode.com/problems/majority-element-ii/
    private boolean isMajority(int[] arr, int val) {
        int count = 0;
        for(int ele : arr) {
            if(val == ele)
                count++;
        }
        return count > arr.length / 3;
    }

    public List<Integer> majorityElement(int[] nums) {
        int val1 = nums[0];
        int count1 = 1;
        int val2 = nums[0];
        int count2 = 0;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == val1) {
                count1++;
            }  else if(nums[i] == val2) {
                count2++;
            } else {
                // may be priplet, or a fresh start
                if(count1 == 0) {
                    val1 = nums[i];
                    count1 = 1;
                } else if(count2 == 0) {
                    val2 = nums[i];
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        List<Integer> res = new ArrayList<>();

        // check if val1 and val2 present in majority or not
        if(isMajority(nums, val1)) {
            res.add(val1);
        } 

        if(val1 != val2 && isMajority(nums, val2)) {
            res.add(val2);
        }
        return res;
    }

    // leetcode 556. https://leetcode.com/problems/next-greater-element-iii/
    public int nextGreaterElement(int n) {
        if(n < 10) return -1;
        String nextNum = nextGreaterElement_("" + n);
        long num = Long.parseLong(nextNum);
        if(num <= Integer.MAX_VALUE) {
            return (int)num;
        } else {
            return -1;
        }
    }

    // next greater for portal
    private static int dipIndex(char[] arr) {
        int indx = -1;
        for(int i = arr.length - 1; i > 0; i--) {
            if(arr[i - 1] < arr[i]) {
                indx = i - 1;
                break;
            }
        }
        return indx;
    }

    private static int ceilIndex(char[] arr, int indx) {
        // indx -> dip indx
        int dipVal = arr[indx];
        int i = arr.length - 1;
        while(dipVal >= arr[i]) {
            i--;
        }
        return i;
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(char[] arr, int left, int right) {
        while(left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static String nextGreaterElement_(String str) {
        char[] arr = str.toCharArray();
        int dipIndx = dipIndex(arr);
        if(dipIndx == -1) {
            return "-1";
        }
        int ceilIndx = ceilIndex(arr, dipIndx);
        swap(arr, dipIndx, ceilIndx);
        reverse(arr, dipIndx + 1, arr.length - 1);
        return String.valueOf(arr);
    }

    // leetcode 905. https://leetcode.com/problems/sort-array-by-parity/
    public int[] sortArrayByParity(int[] nums) {
        int i = 0; // first unsolved
        int j = 0; // first odd

        while(i < nums.length) {
            if(nums[i] % 2 != 0) {
                // odd
                i++;
            } else {
                // even
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
                j++;
            }
        }
        return nums;
    }

    // leetcode 628. https://leetcode.com/problems/maximum-product-of-three-numbers/
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int val : nums) {
            if(val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if(val > max2) {
                max3 = max2;
                max2 = val;
            } else if(val > max3) {
                max3 = val;
            }

            if(val < min1) {
                min2 = min1;
                min1 = val;
            } else if(val < min2) {
                min2 = val;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    // leetcode 769. https://leetcode.com/problems/max-chunks-to-make-sorted/
    public int maxChunksToSorted1(int[] arr) {
        int chunks = 0;
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if(max == i) {
                chunks++;
            }
        }
        return chunks;
    }

    // leetcode 768. https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        // prepare right min and manage left max while running with loop
        int[] rightMin = new int[n];
        rightMin[n - 1] = arr[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }
        // count chunks 
        int count = 1;
        int leftmax = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; i++) {
            leftmax = Math.max(leftmax, arr[i]);
            if(leftmax <= rightMin[i + 1]) {
                count++;
            }
        }
        return count;
    }

    // leetcode 747. https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    public int dominantIndex(int[] nums) {
        if(nums.length == 1) return 0;
        int indx = 0;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                indx = i;
            } else if(nums[i] > max2) {
                max2 = nums[i];
            }
        }
        return max1 >= 2 * max2 ? indx : -1;
    }

    // leetcode 345. https://leetcode.com/problems/reverse-vowels-of-a-string/
    private boolean isVowel(char ch) {
        String str = "AEIOUaeiou";
        return str.contains(ch + "");
    } 

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();

        int left = 0;
        int right = s.length() - 1;

        while(left < right) {
            while(left < right && isVowel(arr[left]) == false) {
                left++;
            } 

            while(left < right && isVowel(arr[right]) == false) {
                right--;
            }

            swap(arr, left, right);
            left++;
            right--;
        }
        return String.valueOf(arr);
    }

    // leetcode 795. https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int i = 0;
        int j = 0;
        int prev_count = 0;
        int count = 0;

        while(i < nums.length) {
            if(left <= nums[i] && nums[i] <= right) {
                count += i - j + 1;
                prev_count = i - j + 1;
            } else if(nums[i] < left) {
                count += prev_count;
            } else {
                prev_count = 0;
                j = i + 1;
            }
            i++;
        }
        return count;
    }

    // wiggle sort -> https://practice.geeksforgeeks.org/problems/wave-array-1587115621/1
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void wiggleSort1(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            if(i % 2 == 0) {
                // even
                if(arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            } else {
                // odd
                if(arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    // leetcode 324. https://leetcode.com/problems/wiggle-sort-ii/
    public void wiggleSort2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr);
        
        int i = 1;
        int j = n - 1;
        while(i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }

        i = 0;
        while(i < n) {
            nums[i] = arr[j];
            i += 2;
            j--;
        }
    }

    // lintcode 903, https://www.lintcode.com/problem/range-addition/description
    public int[] getModifiedArray(int n, int[][] query) {
        int[] arr = new int[n];

        // travel on query and mark impact
        for(int i = 0; i < query.length; i++) {
            int st = query[i][0];
            int end = query[i][1];
            int val = query[i][2];

            arr[st] += val;
            if(end != n - 1) {
                arr[end + 1] -= val;
            }
        }

        // travel on array and make impact visible using prefix sum
        for(int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }

        return arr;
    }

    // leetcode 238. https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] rightProduct = new int[n + 1];
        int[] res = new int[n];

        // make right product array
        rightProduct[n] = 1;
        for(int i = n - 1; i >= 0; i--) {
            rightProduct[i] = nums[i] * rightProduct[i + 1];
        }

        // maintain left product in running time and solve 
        int leftprod = 1;
        for(int i = 0; i < n; i++) {
            res[i] = leftprod * rightProduct[i + 1];
            leftprod *= nums[i];
        }
        return res;
    }

    // leetcode 119. https://leetcode.com/problems/pascals-triangle-ii/
    public List<Integer> getRow(int rowIndex) {
        
    }

    // leetcode 849. https://leetcode.com/problems/maximize-distance-to-closest-person/
    // O(1) space
    public int maxDistToClosest(int[] seats) {
        // step 1. travel on arr and make left person distance and empty seats, and -1 at occupied seat
        int indx = Integer.MAX_VALUE;
        for(int i = 0; i < seats.length; i++) {
            if(seats[i] == 1) {
                indx = i;
                seats[i] = -1;
            } else {
                seats[i] = Math.abs(indx - i);
            }
        }

        /* step 2. travel from right to left and make arr as closest person 
            distance and manage max of closest person distnace*/
        int max = 0;
        indx = Integer.MAX_VALUE;
        for(int i = seats.length - 1; i >= 0; i--) {
            if(seats[i] == -1) {
                indx = i;
            } else {
                // min distance between closest left and right
                seats[i] = Math.min(seats[i], indx - i);
                // maximise closest distance
                max = Math.max(max, seats[i]);
            }
        }
        return max;
    }

    // leetcode 41. https://leetcode.com/problems/first-missing-positive/
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // step 1. mark number out of range and check presence of one
        boolean one = false;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) 
                one = true;
            
            if(nums[i] < 1 || n < nums[i]) {
                nums[i] = 1;
            } 
        }
        if(one == false) return 1;
        // step 2. travel and mark present index in array
        for(int i = 0; i < n; i++) {
            int indx = Math.abs(nums[i]) - 1; 
            nums[indx] = -Math.abs(nums[indx]);
        }
        // step 3. travel and check unmarked index, if found return indx + 1, otherwise n + 1
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    // lintcode 912. https://www.lintcode.com/problem/912/
    public int minTotalDistance(int[][] grid) {
        ArrayList<Integer> xcord = new ArrayList<>();
        // for sorted x, travel row wise
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1) {
                    xcord.add(r);
                }
            }
        }

        ArrayList<Integer> ycord = new ArrayList<>();
        // for sorted y, travel column wise
        for(int c = 0; c < grid[0].length; c++) {
            for(int r = 0; r < grid.length; r++) {
                if(grid[r][c] == 1) {
                    ycord.add(c);
                }
            }
        }

        // find meeting point from median
        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);

        int dist = 0;
        for(int i = 0; i < xcord.size(); i++) {
            // distance between xcord
            dist += Math.abs(x - xcord.get(i));
            // distance between ycord
            dist += Math.abs(y - ycord.get(i));
        }

        return dist;
    }

    // leetcode 670. https://leetcode.com/problems/maximum-swap/
    // portal function
    public static String maximumSwap(String num) {
        char[] arr = num.toCharArray();
        int[] lastIndex = new int[10];
        for(int i = 0; i < arr.length; i++) {
            lastIndex[arr[i] - '0'] = i;
        }
        for(int i = 0; i < arr.length; i++) {
            int digit = arr[i] - '0';
            boolean flag = true;
            for(int j = 9; j > digit; j--) {
                if(lastIndex[j] > i) {
                    swap(arr, i, lastIndex[j]);
                    flag = false;
                    break;
                }
            }
            if(flag == false) {
                break;
            }
        }
        return String.valueOf(arr);
    }

    // two Sum : Target Sum Unique Pair
    public static List<List<Integer>> twoSum(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        List<List<Integer>> res = new ArrayList<>();

        while(left < right) {
            // how to prevent repitition
            if(left != 0 && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if(sum == target) {
                List<Integer> subres = new ArrayList<>();
                subres.add(arr[left]);
                subres.add(arr[right]);
                res.add(subres);
                left++;
                right--;
            } else if(sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    // 3 sum : Unique Triplet with target sum
    private static List<List<Integer>> twoSum_(int[] arr, int target, int st) {
        int left = st;
        int right = arr.length - 1;
        List<List<Integer>> res = new ArrayList<>();

        while(left < right) {
            // how to prevent repitition
            if(left != st && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if(sum == target) {
                List<Integer> subres = new ArrayList<>();
                subres.add(arr[left]);
                subres.add(arr[right]);
                res.add(subres);
                left++;
                right--;
            } else if(sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums, int targ) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i <= n - 3; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int val1 = nums[i];
            List<List<Integer>> subres = twoSum_(nums, targ - val1, i + 1);
            for(List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    // four sum : Unique Quadruple
    private static List<List<Integer>> threeSum_(int[] nums, int targ, int st) {
        // Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for(int i = st; i <= n - 3; i++) {
            if(i != st && nums[i] == nums[i - 1]) {
                continue;
            }

            int val1 = nums[i];
            List<List<Integer>> subres = twoSum_(nums, targ - val1, i + 1);
            for(List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i <= n - 4; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int val1 = nums[i];
            List<List<Integer>> subres = threeSum_(nums, target - val1, i + 1);
            for(List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    // K Sum, target sum unique Set
    private static List<List<Integer>> kSum_(int[] nums, int target, int st, int k) {
        if(k == 2) {
            return twoSum_(nums, target, st);
        }

        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for(int i = st; i <= n - k; i++) {
            if(i != st && nums[i] == nums[i - 1]) {
                continue;
            }
            int val1 = nums[i];
            List<List<Integer>> subres = kSum_(nums, target - val1, i + 1, k - 1);
            for(List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }     
    
    public static List<List<Integer>> kSum(int[] arr, int target, int k) {
        // main recursive function
        Arrays.sort(arr);
        return kSum_(arr, target, 0, k);
    }

    // leetcode 537. https://leetcode.com/problems/complex-number-multiplication/
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0, num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+") + 1, num1.length() - 1));

        int a2 = Integer.parseInt(num2.substring(0, num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+") + 1, num2.length() - 1));

        int real = a1 * a2 - b1 * b2;
        int imaginary = a1 * b2 + a2 * b1;

        return real + "+" + imaginary + "i";
    }

    // minimum Platform, https://practice.geeksforgeeks.org/problems/minimum-platforms/1
    static int findPlatform(int arr[], int dep[], int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int omax = 0;
        int platforms = 0;
        int i = 0; // for arrival
        int j = 0; // for departure
        while(i < arr.length) {
            if(arr[i] <= dep[j]) {
                platforms++;
                i++;
            } else {
                platforms--;
                j++;
            }
            omax = Math.max(omax, platforms);
        }
        return omax;
    }

    // pair with given differencem, https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
    public boolean findPair(int arr[], int size, int n) {
        //code here.
    }

    public static void printPrimeUsingSieve(int n) {
        // pre calculation
        boolean[] isprime = new boolean[n + 1];
        
        // fill all as prime, i.e. as true -> this is optional step
        Arrays.fill(isprime, true);

        // begin from 2 to root(n)
        for(int i = 2; i * i <= n; i++) {
            if(isprime[i] == false) {
                continue; // because if it is marked as not prime then its multiple are also marked
            }
            for(int j = i + i; j <= n; j += i) {
                isprime[j] = false;
            }
        }

        // run a loop and print if prime
        for(int i = 2; i <= n; i++) {
            if(isprime[i] == true) {
                // i is prime number
                System.out.print(i + " ");
            }
        }
    }  

    // segmented sieve
    private static ArrayList<Integer> sieve(int n) {
        // pre calculation
        boolean[] isprime = new boolean[n + 1];

        // begin from 2 to root(n)
        for(int i = 2; i * i <= n; i++) {
            if(isprime[i] == true) {
                continue; // because if it is marked as not prime then its multiple are also marked
            }
            for(int j = i + i; j <= n; j += i) {
                isprime[j] = true;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 2; i < isprime.length; i++) {
            if(isprime[i] == false) {
                res.add(i);
            }
        }
        return res;
    }

    public static void segmentedSieveAlgo(int a, int b) {
        int rootb = (int)Math.sqrt(b);
        ArrayList<Integer> primes = sieve(rootb);

        int n = b - a;
        boolean[] isprime = new boolean[n + 1];
        // isprime[i] == true -> associated value is not prime
        // isprime[i] == false -> associated value is prime

        for(int prime : primes) {
            int multiple = (int)Math.ceil(a * 1.0 / prime);

            if(multiple == 1) multiple++;

            int si = multiple * prime - a;
            for(int i = si; i < isprime.length; i += prime) {
                isprime[i] = true; // mark it as not prime
            }
        }

        // travel and print prime
        for(int i = 0; i < isprime.length; i++) {
            if(isprime[i] == false && i + a != 1) {
                System.out.println(i + a);
            }
        }
    }

    // Given Pair Difference, https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
    public boolean findPair(int arr[], int size, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = 1;

        while(right < arr.length) {
            int diff = arr[right] - arr[left];
            if(diff == target) {
                return true;
            } else if(diff > target) {
                left++;
            } else {
                right++;
            }
        }
        return false;
    }

    // leetcode 881. https://leetcode.com/problems/boats-to-save-people/
    public int numRescueBoats(int[] wts, int capacity) {
        Arrays.sort(wts);
        int boats = 0;
        int left = 0;
        int right = wts.length - 1;
        while(left <= right) {
            int sum = wts[left] + wts[right];

            if(sum > capacity) {
                right--;
            } else {
                left++;
                right--;
            }
            boats++;
        }
        return boats;
    }

    // leetcode 754. https://leetcode.com/problems/reach-a-number/
    public int reachNumber(int target) {
        target = Math.abs(target);
        int sum = 0;
        int jump = 0;

        while(sum < target) {
            jump++;
            sum += jump;
        }

        if(sum == target) {
            // reached at destination
            return jump;
        } else if((sum - target) % 2 == 0) {
            return jump;
        } else if((sum + jump + 1 - target) % 2 == 0) {
            return jump + 1;
        } else {
            return jump + 2;
        }
    }

    // leetcode 763. https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++)
            map.put(s.charAt(i), i);
        
        int max = 0;
        int prev = 0;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            max = Math.max(max, map.get(ch));

            if(max == i) {
                res.add(i - prev + 1);
                prev = i + 1;
            }
        }
        return res;
    }

    // leetcode 867. https://leetcode.com/problems/transpose-matrix/
    public int[][] transpose1(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] res = new int[col][row];

        for(int r = 0; r < col; r++) {
            for(int c = 0; c < row; c++) {
                res[r][c] = matrix[c][r];
            }
        }
        return res;
    }

    // portal
    public static void transpose2(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // leetcode 48. https://leetcode.com/problems/rotate-image/
    public void rotate(int[][] matrix) {
        transpose2(matrix);
        // reverse col
        for(int r = 0; r < matrix.length; r++) {
            int left = 0;
            int right = matrix[0].length - 1;

            while(left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
                left++;
                right--;
            }
        }
    }
    
    // leetcode 838. https://leetcode.com/problems/push-dominoes/
    private void solveConfig(char[] arr, int i, int j) {
        if(arr[i] == 'L' && arr[j] == 'L') {
            for(int k = i + 1; k < j; k++)
                arr[k] = 'L';
        } else if(arr[i] == 'R' && arr[j] == 'R') {
            for(int k = i + 1; k < j; k++)
                arr[k] = 'R';
        } else if(arr[i] == 'L' && arr[j] == 'R') {
            // nothing to do
        } else {
            int left = i + 1;
            int right = j - 1;
            while(left < right) {
                arr[left] = 'R';
                arr[right] = 'L';

                left++;
                right--;
            }
        }
    }

    public String pushDominoes(String s) {
        int n = s.length();
        char[] arr = new char[n + 2];
        arr[0] = 'L';
        arr[n + 1] = 'R';
        for(int i = 0; i < n; i++)
            arr[i + 1] = s.charAt(i);

        int i = 0;
        int j = 1;
        while(j < arr.length) {
            while(arr[j] == '.')
                j++;
            
            if(j - i > 1) {
                solveConfig(arr, i, j);
            }
            i = j;
            j++;
        }

        // String res = "";
        // for(int k = 1; k < arr.length - 1; k++) {
        //     res += arr[k];
        // }
        // return res;

        StringBuilder str = new StringBuilder();
        for(int k = 1; k < arr.length - 1; k++) {
            str.append(arr[k]);
        }
        return str.toString();
    }

    // leetcode 829. https://leetcode.com/problems/consecutive-numbers-sum/
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        for(int k = 1; k * (k - 1) < 2 * n ; k++) {
            // we have to find if it is possible to make sum with k numbers
            int numerator = n - ((k - 1) * k / 2);
            if(numerator % k == 0)
                count++;
        }
        return count;
    }

    // add strings -> portal
    public static String addStrings(String num1, String num2) {
        
    }

    // multiply strings -> portal
    public static String multiplication(String num1, String num2) {

    }

    // leetcode 42. https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        int flow = 0;
        int water = 0;
        int max = height[0];
        int maxIndx = 0;

        for(int i = 1; i < height.length; i++) {
            int ht = height[i];
            if(max <= ht) {
                water += flow;
                flow = 0;
                max = ht;
                maxIndx = i;
            } else {
                flow += (max - ht);
            }
        }

        // solve the overflow of flow value
        flow = 0;
        max = height[height.length - 1];
        for(int i = height.length - 2; i >= maxIndx; i--) {
            int ht = height[i];
            if(max <= ht) {
                water += flow;
                flow = 0;
                max = ht;
            } else {
                flow += (max - ht);
            }
        }
        return water;
    }

    // leetcode 239. https://leetcode.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        
    }


    public static void main(String[] args) {
        
    }
}
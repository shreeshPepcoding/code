import java.util.*;

public class arrays {

    // leetcode 925 https://leetcode.com/problems/long-pressed-name/
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()) return false;
        int i = 0; // name string
        int j = 0; // typed string
        while(i < name.length() && j < typed.length()) {
            if(name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if(0 <= i - 1 && name.charAt(i - 1) == typed.charAt(j)) {
                j++;
            } else {
                return false;
            }
        }
        while(j < typed.length()) {
            if(typed.charAt(j) != name.charAt(i - 1)) {
                return false;
            }
            j++;
        }
        return i < name.length() ? false : true;
    }
    
    // leetcode 11 https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int maxWater = 0;
        int i = 0;
        int j = height.length - 1;
        while(i < j) {
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
        int[] res = new int[nums.length];

        int i = 0;
        int j = nums.length - 1;
        
        for(int k = nums.length - 1; k >= 0; k--) {
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

    // leetcode 169 https://leetcode.com/problems/majority-element/
    public int majorityElement_(int[] nums) {
        int val = nums[0];
        int count = 1;

        int i = 1;
        while(i < nums.length) {
            if(val == nums[i]) {
                // increase the count of same element
                count++;
            } else {
                if(count > 0) {
                    // pair distinct pair
                    count--;
                } else {
                    val = nums[i];
                    count = 1;
                }
            }
            i++;
        }
        // majority always exist, so val is result
        return val;
    }

    // leetcode 229 https://leetcode.com/problems/majority-element-ii/
    private boolean checkFreqM2(int[] arr, int val) {
        int count = 0;
        for(int e : arr) {
            if(e == val) count++;
        }
        return count > arr.length / 3;
    }

    public List<Integer> majorityElement(int[] arr) {
        // find valid candidates fore majority
        int val1 = arr[0];
        int count1 = 1; // count1 -> count of val1 for current window to make triplets

        int val2 = arr[0];
        int count2 = 0; // count2 -> count of val2 for current window to make triplets

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == val1) {
                count1++;
            } else if(arr[i] == val2) {
                count2++;
            } else {
                if(count1 == 0) {
                    val1 = arr[i];
                    count1 = 1;
                } else if(count2 == 0) {
                    val2 = arr[i];
                    count2 = 1;
                } else {
                    // triplet is found, reduce freq of val1 & val2 from count1 and count2
                    count1--;
                    count2--;
                }
            }
        }

        // check if it have freq more than n/3 using val1 and val2
        List<Integer> list = new ArrayList<>();
        boolean check1 = checkFreqM2(arr, val1);
        if(check1 == true)  list.add(val1);

        if(val1 == val2) return list;

        boolean check2 = checkFreqM2(arr, val2);
        if(check2 == true)  list.add(val2);
        
        return list;
    }

    // next greater element 3
    private static int dipIndex(char[] arr) {
        int indx = arr.length - 2;
        while(indx >= 0 && (arr[indx] >= arr[indx + 1])) {
            indx--;
        }
        return indx;
    }

    private static int ceilIndex(char[] arr, int indx) {
        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] > arr[indx]) {
                return i;
            }
        }
        return -1;
    } 

    private static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void reverse(char[] arr, int indx) {
        int left = indx;
        int right = arr.length - 1;
        while(left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static String nextGreaterElement_(String str) {
        if(str.length() == 1) return "-1";
        char[] arr = str.toCharArray();
        int dipIndx = dipIndex(arr);
        if(dipIndx == -1) {
            return "-1";
        }
        int ceilIndx = ceilIndex(arr, dipIndx);
        swap(arr, dipIndx, ceilIndx);
        reverse(arr, dipIndx + 1);
        return String.valueOf(arr);
    }

    // for leetcode, above problem
    public int nextGreaterElement(int n) {
        String str = n + "";
        String res_ = nextGreaterElement_(str);
        long res = Long.parseLong(res_);
        if(res <= Integer.MAX_VALUE) {
            return (int)res;
        } else {
            return -1;
        }
    }

    // leetcode 628 https://leetcode.com/problems/maximum-product-of-three-numbers/
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

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
        return Math.max(max1 * min1 * min2, max1 * max2 * max3);
    }

    // leetcode 747 https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    public int dominantIndex(int[] nums) {
        if(nums.length == 1) return 0;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int indx = 0;
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

    // leetcode 769. https://leetcode.com/problems/max-chunks-to-make-sorted/
    public int maxChunksToSorted1(int[] arr) {
        int chunks = 0;
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if(max == i)
                chunks++;
        }
        return chunks;
    }

    // leetcode 768 https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] rightMin = new int[n + 1];
        rightMin[n] = Integer.MAX_VALUE;
        int chunks = 0;
        // prepare right min
        for(int i = n - 1; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }
        // solve count of using using a leftmax variable
        int leftmax = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            leftmax = Math.max(leftmax, arr[i]);

            if(leftmax <= rightMin[i + 1])
                chunks++;
        }
        return chunks;
    }

    // leetcode 345 https://leetcode.com/problems/reverse-vowels-of-a-string/
    private boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(ch + "");
    }
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
            // make left pointer at vowel
            while(left < right && isVowel(arr[left]) == false) {
                left++;
            }

            // make right pointer at vowel
            while(left < right && isVowel(arr[right]) == false) {
                right--;
            }

            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return String.valueOf(arr);
    }

    // leetcode 238 https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] leftProd = new int[n];
        int[] rightProd = new int[n];

        // left
        leftProd[0] = nums[0];
        for(int i = 1; i < n; i++) {
            leftProd[i] = leftProd[i - 1] * nums[i];
        }

        // right
        rightProd[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightProd[i] = rightProd[i + 1] * nums[i];
        }

        // res
        int[] res = new int[n];
        res[0] = rightProd[1];
        res[n - 1] = leftProd[n - 2];

        for(int i = 1; i < n - 1; i++) {
            res[i] = leftProd[i - 1] * rightProd[i + 1];
        }
        return res;
    }

    // wiggle sort 1
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void wiggleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            if(i % 2 == 0) {
                // even index
                if(arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            } else {
                // odd index
                if(arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        } 
    }

    // leetcode 324. https://leetcode.com/problems/wiggle-sort-ii/
    public void wiggleSort2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n]; // dupliicate array

        for(int i = 0; i < n; i++)
            arr[i] = nums[i];

        Arrays.sort(arr);

        int j = n - 1;
        int i = 1;

        // fill odd index
        while(i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }

        // fill even index
        i = 0;
        while(i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }
    }
    
    // leetcode 795 https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int prev_count = 0;
        int overall_count = 0;

        int i = 0;
        int j = 0;

        while(j < nums.length) {
            if(left <= nums[j] && nums[j] <= right) {
                prev_count = j - i + 1;
                overall_count += prev_count;
            } else if(nums[j] < left) {
                overall_count += prev_count;
            } else {
                prev_count = 0;
                i = j + 1;
            }
            j++;
        }
        return overall_count;
    }

    // leetcode 849 https://leetcode.com/problems/maximize-distance-to-closest-person/
    public int maxDistToClosest(int[] seats) {
        int dist = 0;
        int zeros = 0;
        int indx = 0;
        // left sub part
        while(seats[indx] != 1) {
            indx++;
            zeros++;
        }
        indx++;
        dist = zeros;
        zeros = 0;
        // segements calculations
        while(indx < seats.length) {
            while(indx < seats.length && seats[indx] != 1) {
                zeros++;
                indx++;
            }
            if(indx == seats.length)
                break;

            indx++;
            dist = Math.max(dist, (zeros + 1) / 2);
            zeros = 0;
        }

        // right sub part
        return Math.max(zeros, dist);
    }

    // leetcode 41. https://leetcode.com/problems/first-missing-positive/
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // step 1. find occurence of one and mark out of range element
        boolean one = false;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) one = true;

            if(nums[i] < 1 || n < nums[i]) {
                nums[i] = 1;
            }
        }
        if(one == false) return 1;
        // step 2. mark element in array
        for(int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            int indx = val - 1;
            nums[indx] = -Math.abs(nums[indx]);
        }

        // step 3. check first missing positive
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    // leetcode 905. https://leetcode.com/problems/sort-array-by-parity/
    public int[] sortArrayByParity(int[] nums) {
        int i = 0; // first unsolved
        int j = 0; // first odd

        while(i < nums.length) {
            if(nums[i] % 2 == 0) {
                // even
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
                j++;
            } else {
                // odd
                i++;
            }
        }
        return nums;
    }

    // lintcode 903. https://www.lintcode.com/problem/903/
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];

        // make impact of query on res
        for(int i = 0; i < updates.length; i++) {
            int st = updates[i][0];
            int end = updates[i][1];
            int inc = updates[i][2];

            res[st] += inc;

            if(end + 1 < length) {
                res[end + 1] -= inc;
            }
        }

        // apply impact on res using prefix sum
        int sum = 0;
        for(int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }

    // lintcode 912 https://www.lintcode.com/problem/912/
    public int minTotalDistance(int[][] grid) {
        ArrayList<Integer> xcord = new ArrayList<>();
        // fill xcordinate in sorted manner -> row wise traversal
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1)
                    xcord.add(r);
            }
        }
        ArrayList<Integer> ycord = new ArrayList<>();
        // fill ycordinate in sorted manner -> column wise traversal
        for(int c = 0; c < grid[0].length; c++) {
            for(int r = 0; r < grid.length; r++) {
                if(grid[r][c] == 1)
                    ycord.add(c);
            }
        }
        // find meeting point
        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);
        int dist = 0;
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1) {
                    dist += Math.abs(x - r) + Math.abs(y - c);
                }  
            }
        }
        return dist;
    }

    // leetcode 670. https://leetcode.com/problems/maximum-swap/
    public int maximumSwap(int n) {
        String num = n + "";
        // convert number into string
        char[] arr = num.toCharArray();

        int[] lastIndxOfDigit = new int[10];
        // travel and fill last index of digits
        for(int i = 0; i < arr.length; i++) {
            lastIndxOfDigit[arr[i] - '0'] = i;
        }

        // travel and find swapping point
        for(int i = 0; i < arr.length; i++) {
            int digit = arr[i] - '0';
            int indx = i;
            for(int j = 9; j > digit; j--) {
                // greater digit have max index then set it in indx and break
                if(lastIndxOfDigit[j] > i) {
                    indx = lastIndxOfDigit[j];
                    break;
                }
            }
            if(indx != i) {
                swap(arr, i, indx);
                break;
            }
        }
        String res = String.valueOf(arr);
        // convert string into number
        return Integer.parseInt(res);
    }

    // leetcode 119 https://leetcode.com/problems/pascals-triangle-ii/
    public List<Integer> getRow(int n) {
        List<Integer> res = new ArrayList<>();
        long val = 1;
        for(int r = 0; r <= n; r++) {
            res.add((int)val);
            val = val * (n - r) / (r + 1); 
        }
        return res;
    }

    // leetcode 537. https://leetcode.com/problems/complex-number-multiplication/
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0, num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+") + 1, num1.length() - 1));

        int a2 = Integer.parseInt(num2.substring(0, num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+") + 1, num2.length() - 1));

        int a = a1 * a2 - b1 * b2;
        int b = a1 * b2 + a2 * b1;
        return a + "+" + b + "i";
    }

    // 2 Sum - target Sum Unique Pair
    public static List<List<Integer>> twoSum_(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
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

    private static List<List<Integer>> twoSum(int[] arr, int st, int end, int target) {
        int left = st;
        int right = end;
        List<List<Integer>> res = new ArrayList<>();
        while(left < right) {
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

    public static List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < nums.length - 2; i++) {
            if(i != 0 && nums[i] == nums[i - 1])
                continue;

            int val1 = nums[i];
            int smallTarget = target - val1;

            List<List<Integer>> subres = twoSum(nums, i + 1, nums.length - 1, smallTarget);
            for(List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
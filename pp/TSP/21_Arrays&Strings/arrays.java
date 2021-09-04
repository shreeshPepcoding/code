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

    public static void main(String[] args) {

    }
}
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



    public static void main(String[] args) {
        
    }
}
import java.util.*;

public class SnS {
    

    public static class Students implements Comparable<Students> {
        int phy;
        int chem;
        int maths;


        public Students(int phy, int chem, int maths) {
            this.phy = phy;
            this.chem = chem;
            this.maths = maths;
        }

        public int compareTo(Students o) {
            if(this.phy == o.phy) {
                // decision depends on chemistry
                if(this.chem == o.chem) {
                    // decision depends on maths, default behaviour
                    return this.maths - o.maths;
                } else {
                    // reverse of default behaviour
                    return o.chem - this.chem;
                }
            } else {
                // default behaviour, increasing order requirement
                return this.phy - o.phy;
            }
        }
    }

    // marks of PCM https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1
    public void customSort (int phy[], int chem[], int math[], int N) {
        Students[] sarr = new Students[phy.length];

        for(int i = 0; i < phy.length; i++) {
            sarr[i] = new Students(phy[i], chem[i], math[i]);
        }

        Arrays.sort(sarr);
        for(int i = 0; i < phy.length; i++) {
            phy[i] = sarr[i].phy;
            chem[i] = sarr[i].chem;
            math[i] = sarr[i].maths;
        }
    }
    
    // union of two sorted arrays, https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        ArrayList<Integer> res = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < n && j < m) {
            if(arr1[i] == arr2[j]) {
                if(res.size() == 0 || res.get(res.size() - 1) != arr1[i]) {
                    res.add(arr1[i]);
                }
                i++;
                j++;
            } else if(arr1[i] < arr2[j]) {
                if(res.size() == 0 || res.get(res.size() - 1) != arr1[i]) {
                    res.add(arr1[i]);
                }
                i++;
            } else {
                if(res.size() == 0 || res.get(res.size() - 1) != arr2[j]) {
                    res.add(arr2[j]);
                }
                j++;
            }
        }

        // either i will remain or j will remain
        while(i < n) {
            if(res.size() == 0 || res.get(res.size() - 1) != arr1[i]) {
                res.add(arr1[i]);
            } 
            i++;
        }

        while(j < m) {
            if(res.size() == 0 || res.get(res.size() - 1) != arr2[j]) {
                res.add(arr2[j]);
            } 
            j++;
        }
        return res;
    }

    // search in 2D matrix, portal
    private static int findRowIndx(int[][] matrix, int target) {
        int indx = -1;
        int lo = 0;
        int hi = matrix.length - 1;

        while(lo <= hi) {
            int midRow = lo + (hi - lo) / 2;

            if(matrix[midRow][0] <= target && target <= matrix[midRow][matrix[0].length - 1]) {
                indx = midRow;
                break;
            } else if(target < matrix[midRow][0]) {
                hi = midRow - 1;
            } else {
                lo = midRow + 1;
            }
        }
        return indx;
    }

    private static int findInIthRow(int[][] matrix, int target, int row) {
        int indx = -1;
        int lo = 0;
        int hi = matrix[0].length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(matrix[row][mid] == target) {
                indx = mid;
                break;
            } else if(matrix[row][mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return indx;
    }

    public static boolean search(int[][]matrix, int target) {
        int rowIndx = findRowIndx(matrix, target);
        if(rowIndx == -1) return false;
        int colIndx = findInIthRow(matrix, target, rowIndx);
        return colIndx != -1;
    }

    // search in 2D matrix 2, portal
    public static boolean search2(int[][]matrix,int target) {
        int r = 0;
        int c = matrix[0].length - 1;

        while(r < matrix.length && c >= 0) {
            if(matrix[r][c] == target) {
                return true;
            } else if(matrix[r][c] < target) {
                // discard current row
                r++;
            } else {
                // discard column
                c--;
            }
        }
        return false;
    }

    // leetcode 724, https://leetcode.com/problems/find-pivot-index/
    public int pivotIndex(int[] nums) {
        int tsum = 0;
        for(int val : nums) 
            tsum += val;
        
        int lsum = 0;
        for(int i = 0; i < nums.length; i++) {
            tsum -= nums[i];
            if(lsum == tsum) {
                return i;
            }
            lsum += nums[i];
        }
        return -1;
    }

    // find k closest element using priority queue
    public static class Pair implements Comparable<Pair> {
        int val;
        int dist;

        public Pair(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }

        public int compareTo(Pair o) {
            if(this.dist == o.dist) {
                return this.val - o.val;
            } else {
                return this.dist - o.dist;
            }
        }
    }

    public static ArrayList<Integer> findClosest(int[]arr,int k,int x) {
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < arr.length; i++) {
            if(i < k) {
                pq.add(new Pair(arr[i], Math.abs(arr[i] - x)));
            } else {
                if(pq.peek().dist > Math.abs(arr[i] - x)) {
                    pq.remove();
                    pq.add(new Pair(arr[i], Math.abs(x - arr[i])));
                }
            }
        }

        while(pq.size() > 0) {
            res.add(pq.remove().val);
        }
        Collections.sort(res);
        return res;
    }

    // find pair with given difference, portal
    public static void findPair(int[]arr,int target) {
        int lo = 0;
        int hi = 1;
        Arrays.sort(arr);
        while(hi < arr.length && lo < hi) {
            int diff = arr[hi] - arr[lo];
            if(diff == target) {
                System.out.println(arr[lo] + " " + arr[hi]);
                return;
            } else if(diff > target) {
                lo++;
            } else {
                hi++;
            }
        }
        System.out.println(-1);
    }

    // roof top, portal
    public static int findMaxSteps(int[]arr) {
        int count = 0;
        int res = 0;

        for(int i = 1; i < arr.length; i++) {
            if(arr[i - 1] < arr[i]) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 0;
            }
        }
        res = Math.max(res, count);
        return res;
    }

    // maximize sum of arr[i] * i, portal
    public static int maximise(int[]arr) {
        long sum = 0;
        int mod = (int)(1e9) + 7;
        Arrays.sort(arr);
        for(long i = 0; i < arr.length; i++) {
            sum = sum + ((long)(arr[(int)i]) * i);
        }
        return (int)(sum % mod);
    }

    // maximise config. arr[i] * i, portal
    public static int maximise1(int[]arr) {
        int sum  = 0;
        int sIm1 = 0; // s[i - 1]
        for(int i = 0; i < arr.length; i++) {
            sIm1 += arr[i] * i;
            sum += arr[i];
        }
        int res = sIm1;
        for(int i = 1; i < arr.length; i++) {
            int sI = sIm1 + sum - n * arr[n - i];
            res = Math.max(sI, res);
            sIm1 = sI;
        }
        return res;
    }

    // =================Binary Search======================
    // 1. Normal Binary Search
    public int binarySearch(int[] arr, int dtf) {
        int lo = 0;
        int hi = arr.length - 1;
        int indx = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == dtf) {
                indx = mid;
                break;
            } else if(arr[mid] > dtf) {
                // discard right side
                hi = mid - 1;
            } else {
                // discard left side
                lo = mid + 1;
            }
        }
        return indx;
    }

    // 2. Binary Search First Index
    public int binarySearchFirstIndex(int[] arr, int dtf) {
        int lo = 0;
        int hi = arr.length - 1;
        int indx = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == dtf) {
                indx = mid;
                hi = mid - 1;
            } else if(arr[mid] > dtf) {
                // discard right side
                hi = mid - 1;
            } else {
                // discard left side
                lo = mid + 1;
            }
        }
        return indx;
    }

    // 3. Binary Search Last Index
    public int binarySearchLastIndex(int[] arr, int dtf) {
        int lo = 0;
        int hi = arr.length - 1;
        int indx = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == dtf) {
                indx = mid;
                lo = mid + 1;
            } else if(arr[mid] > dtf) {
                // discard right side
                hi = mid - 1;
            } else {
                // discard left side
                lo = mid + 1;
            }
        }
        return indx;
    }

    // first Transition Point, portal
    public static int findTransition(int[] arr) {
        int indx = -1;
        int lo = 0;
        int hi = arr.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == 1) {
                indx = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return indx;
    }
    
    // leetcode 658, https://leetcode.com/problems/find-k-closest-elements/
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        int lo = 0;
        int hi = arr.length - 1;
        int indx = 0;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            indx = Math.abs(arr[indx] - x) > Math.abs(arr[mid] - x) ? mid: indx;
            if(arr[mid] == x) {
                break;
            } else if(arr[mid] > x) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        lo = indx - 1;
        hi = indx;
        while(res.size() < k && lo >= 0 && hi < arr.length) {
            if(Math.abs(x - arr[lo]) <= Math.abs(arr[hi] - x)) {
                res.addFirst(arr[lo]);
                lo--;
            } else {
                res.addLast(arr[hi]);
                hi++;
            }
        }

        while(res.size() < k && lo >= 0) {
            res.addFirst(arr[lo]);
            lo--;
        }
        
        while(res.size() < k && hi < arr.length) {
            res.addLast(arr[hi]);
            hi++;
        }
        return res;
    }

    // leetcode 33, https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int indx = -1;
        
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == target) {
                // data found
                indx = mid;
                break;
            } else if(arr[mid] < arr[hi]) {
                // right side is sorted
                if(arr[mid] < target && target <= arr[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                // left side is sorted
                if(arr[lo] <= target && target < arr[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }
        
        return indx;
    }

    // minimum in sorted rotated array + rotation count
    public static int findMinimum(int[]arr) {
        int lo = 0;
        int hi = arr.length - 1;

        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] < arr[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return arr[hi];
    }

    static int c_inv = 0;

    private static int[] mergeSortedArray(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] res = new int[n1 + n2];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < n1 && j < n2) {
            if(arr1[i] > arr2[j]) {
                res[k] = arr2[j];
                j++;
                c_inv += n1 - i;
            } else {
                res[k] = arr1[i];
                i++;
            }
            k++;
        }

        while(i < n1) {
            res[k] = arr1[i];
            i++;
            k++;
        }

        while(j < n2) {
            res[k] = arr2[j];
            j++;
            k++;
        }

        return res;
    }

    private static int[] mergeSort(int[] arr, int lo, int hi) {
        if(lo == hi) {
            int[] bres = {arr[lo]};
            return bres;
        }

        int mid = lo + (hi - lo) / 2;
        int[] lres = mergeSort(arr, lo, mid);
        int[] rres = mergeSort(arr, mid + 1, hi);
        int[] res = mergeSortedArray(lres, rres);
        return res;
    }

    private static int countInversion(int[] arr) {
        c_inv = 0;
        int[] res = mergeSort(arr, 0, arr.length - 1);
        return c_inv;
    }

    // median of two sorted arrays
    public static double find(int[] a, int[] b) {
        int n1 = a.length;
        int n2 = b.length;

        if(n1 > n2) {
            int[] temp = a;
            a = b;
            b = temp;

            int temp2 = n1;
            n1 = n2;
            n2 = temp2;
        }

        int lo = 0;
        int hi = n1;
        int te = n1 + n2;
        while(lo <= hi) {
            int ali = lo + (hi - lo) / 2; // aleft is equivalent to mid, ali-> a left index
            int bli = (te + 1) / 2 - ali; // why te + 1, because we have to manage odd and even both, bli-> b left index

            int alm1 = ali == 0 ? Integer.MIN_VALUE : a[ali - 1];
            int al = ali == n1 ? Integer.MAX_VALUE : a[ali];
            int blm1 = bli == 0 ? Integer.MIN_VALUE : b[bli - 1];
            int bl = bli == n2 ? Integer.MAX_VALUE : b[bli];

            // make sure that splitting is valid or not
            if(alm1 <= bl && blm1 <= al) {
                // after is here in this block
                double median = 0.0;
                if(te % 2 == 0) {
                    // even elements
                    median = (Math.max(blm1, alm1) + Math.min(al, bl)) / 2.0;
                } else {
                    // odd elements
                    median = Math.max(alm1, blm1);
                }
                return median;
            } else if(blm1 > al) {
                lo = ali + 1;
            } else {
                hi = ali -1;
            }
        }
        return 0.0;
    }

    // leetcode 875. https://leetcode.com/problems/koko-eating-bananas/
    private boolean isPossible(int[] piles, int h, int speed) {
        int time = 0;
        for(int i = 0; i < piles.length; i++) {
            time += (int)Math.ceil(piles[i] * 1.0 / speed);
        }
        return time <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for(int val : piles) 
            max = Math.max(max, val);
            
        int lo = 1;
        int hi = max;
        int k = Integer.MAX_VALUE;

        while(lo <= hi) {
            int speed = lo + (hi - lo) / 2;
            if(isPossible(piles, h, speed) == true) {
                k = speed;
                hi = speed - 1;
            } else {
                lo = speed + 1;
            }
        }
        return k;
    }

    // leetcode 1283. https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
    public int smallestDivisor(int[] nums, int threshold) {
        // same code as above
    }

    // Allocate minimum number of pages, https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
    private static boolean isBurdenPossible(int[] pages, int burden, int m) {
        int student = 1;
        int sum = 0;

        for(int i = 0; i < pages.length; i++) {
            sum += pages[i];
            if(sum > burden) {
                student++;
                sum = pages[i];
            }
        }
        return student <= m;
    }
    
    public static int findPages(int[] pages, int N, int M) {
        if(M > N) {
            return -1;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int val : pages) {
            sum += val;
            max = Math.max(max, val);
        }

        int lo = max;
        int hi = sum;

        int burden = Integer.MAX_VALUE;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(isBurdenPossible(pages, mid, M) == true) {
                burden = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return burden;
    }

    // count zeros in sorted matrix, portal
    public static int countZeros(int[][] mat) {
        int count = 0;

        int i = 0;
        int j = mat[0].length - 1;
        while(i < mat.length && j >= 0) {
            if(mat[i][j] == 1) {
                j--;
            } else {
                count += (j + 1);
                i++;
            }
        }
        return count;
    }

    // count pairs, portal
    public static int countPairs(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int val : arr) {
            int fq = map.containsKey(val) == true ? map.get(val) : 0;
            map.put(val, fq + 1);
        }

        int pairs = 0;
        for(int val : map.keySet()) {
            int freq = map.get(val);
            pairs += (freq) * (freq - 1) / 2;
        }
        return pairs;
    }

    // facing the sun, portal
    public static int countBuildings(int[]ht) {
        int max = ht[0];
        int count = 1;
        
        for(int i = 1; i < ht.length; i++) {
            if(ht[i] > max) {
                count++;
                max = ht[i];
            }
        }
        return count;
    }

    // distinct element count
    public static int count(int[]arr) {
        int cnt = 0;
        
        int left = 0;
        int right = arr.length - 1;
        int prev = Integer.MIN_VALUE;
        int next = Integer.MAX_VALUE;

        while(left <= right) {
            int lval = Math.abs(arr[left]);
            int rval = Math.abs(arr[right]);

            if(lval == rval) {
                if(lval != prev && rval != next) cnt++;

                prev = lval;
                next = rval;
                left++;
                right--;
            } else if(lval > rval) {
                if(lval != prev) cnt++;

                prev = lval;
                left++;
            } else {
                // rval > lval 
                if(rval != next) cnt++;

                next = rval;
                right--;
            }
        }
        return cnt; 
    }

    // leetcode 540. https://leetcode.com/problems/single-element-in-a-sorted-array/
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int indx = -1;
        
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if((mid == 0 || mid == nums.length - 1) || 
                (nums[mid - 1] != nums[mid] && nums[mid] != nums[mid + 1])) {
                indx = mid;
                break;
            } else if(nums[mid] == nums[mid + 1]){
                if((hi - mid + 1) % 2 == 0) {
                    hi = mid - 1;
                } else {
                    lo = mid;
                }
            } else {
                if((mid - lo + 1) % 2 == 0) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }
        
        return nums[indx];
    }

    // largest perimeter triangle
    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int a = 0;
        int b = 0;
        int c = 0;
        int indx = nums.length - 3;
        while(indx >= 0) {
            a = nums[indx];
            b = nums[indx + 1];
            c = nums[indx + 2];

            if(a + b  > c) {
                return a + b + c;
            }
            indx--;
        }
        return 0;
    }

    // largest number, portal
    public static String largestNumber(int[] arr) {
        String[] starr = new String[arr.length];
        for(int i = 0; i < arr.length; i++) {
            starr[i] = "" + arr[i];
        }

        Arrays.sort(starr, (a, b) -> {
            long d1 = Long.parseLong(a + b);
            long d2 = Long.parseLong(b + a);

            if(d1 > d2) {
                return -1;
            } else if(d1 < d2) {
                return 1;
            } else {
                return 0;
            }
        });

        StringBuilder res = new StringBuilder();
        for(int i = starr.length - 1; i >= 0; i--) {
            res.append(starr[i]);
        }
        String ans = res.toString();
        return ans.charAt(0) == '0' ? "0" : ans;
    }

    // topper of the class, https://practice.geeksforgeeks.org/problems/toppers-of-class3826/1
    
    public class node {
        int marks;
        int index;
        public void setMarks(int a)
        {
            this.marks=a;
        }
        public void setIndex(int b)
        {
            this.index=b;
        }
    }

    public 
    void kTop(node[] a,int n) {
        
    }


    public static void main(String[] args) {
        
    }
}
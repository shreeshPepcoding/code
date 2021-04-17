import java.util.*;

public class tns {
    public static void display(int[] arr) {
        for(int val : arr) 
            System.out.print(val + " ");
        
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int linearSearch(int[] arr, int data) {
        int idx = -1;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == data) {
                idx = i;
                break;
            }
        } 
        return idx;
    } 

    public static boolean binarySearch1(int[] arr, int lo, int hi, int data) {
        if(lo > hi) {
            return false;
        }
        
        int mid = lo + (hi - lo) / 2;

        boolean isPresent = false;
        if(arr[mid] == data) {
            // data found
            isPresent = true;
        } else if(arr[mid] > data) {
            // left side
            isPresent = binarySearch1(arr, lo, mid - 1, data);
        } else {
            // right side
            isPresent = binarySearch1(arr, mid + 1, hi, data);
        }
        return isPresent;
    }

    public static int binarySearchIndx(int[] arr, int lo, int hi, int data) {
        if(lo > hi) {
            return -1;
        }
        
        int mid = lo + (hi - lo) / 2;

        int isPresent = -1;
        if(arr[mid] == data) {
            // data found
            isPresent = mid;
        } else if(arr[mid] > data) {
            // left side
            isPresent = binarySearchIndx(arr, lo, mid - 1, data);
        } else {
            // right side
            isPresent = binarySearchIndx(arr, mid + 1, hi, data);
        }
        return isPresent;
    }

    public static void searching() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int data = 55;
        // boolean res = binarySearch1(arr, 0, arr.length - 1, data);
        // int res = linearSearch(arr, data);
        int res = binarySearchIndx(arr, 0, arr.length - 1, data);
        
        
        display(arr);
        System.out.println(data + " situation : " + res);
    }


    public static void sort01(int[] arr) {
        int i = 0;
        int j = 0;

        while(j < arr.length) {
            if(arr[j] == 1) {
                // expand the region of 1s
                j++;
            } else {
                swap(arr, j, i);
                // expand the region of 0s
                i++;
                // expand the region of 1s
                j++;
            }
        }
    }

    public static void sort012(int[] arr) {
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while(j <= k) {
            if(arr[j] == 0) {
                swap(arr, j, i); 
                i++;
                j++;
            } else if(arr[j] == 1) {
                j++;
            } else {
                swap(arr, j, k);
                k--;
            }
        }
    }

    // fun, f(x, n) = 1.x^(n) + 2.x^(n - 1) + 3.x^(n - 2) + .... + n.x^1
    public static int fun(int x, int N) {
        int sum = 0;
        int n = N;
        int xpower = x;
        for(int i = 1; i <= N; i++) {
            sum += n * xpower;
            n--;
            xpower *= x;
        }
        return sum;
    }

    public static void ques() {
        // int[] arr = {1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0};
        // int[] arr = {0, 1, 0, 2, 2, 0, 1, 1, 0, 2, 2, 0, 2, 0, 1, 0, 1, 0, 2, 0, 1, 2, 2};
        // display(arr);
        // sort01(arr);
        // sort012(arr);
        // display(arr);

        int x = 2;
        int n = 3;
        System.out.println(fun(x, n));
    }

    public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        int l1 = arr1.length;
        int l2 = arr2.length;

        int[] res = new int[l1 + l2];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < l1 && j < l2) {
            if(arr1[i] < arr2[j]) {
                res[k] = arr1[i];
                i++;
            } else {
                res[k] = arr2[j];
                j++;
            }
            k++;
        }

        while(i < l1) {
            res[k] = arr1[i];
            i++;
            k++;
        }

        while(j < l2) {
            res[k] = arr2[j];
            j++;
            k++;
        }

        return res;
    }

    public static int[] mergeSort(int[] arr, int lo, int hi) {
        if(lo == hi) {
            int[] bres = new int[1];
            bres[0] = arr[lo];
            return bres;
        }

        int mid = lo + (hi - lo) / 2;

        // faith
        int[] arr1 = mergeSort(arr, lo, mid);
        int[] arr2 = mergeSort(arr, mid + 1, hi);

        int[] res = mergeTwoSortedArrays(arr1, arr2);
        return res;
    }

    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minIndx = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[minIndx]) {
                    minIndx = j;
                }
            }
            // swapping i, minIndx;
            swap(arr, i, minIndx);
        }
    }

    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int j = i;
            while(j > 0 && arr[j - 1] > arr[j]) {
                swap(arr, j - 1, j);
                j--;
            }
        }
    }

    public static void countSort(int[] arr, int maxRange) {
        int[] fmap = new int[maxRange + 1];

        for(int i = 0; i < arr.length; i++) {
            // int indx = arr[i];
            // fmap[indx]++;

            int val = arr[i];
            fmap[val] += 1;
        }

        System.out.println("fmap : " + Arrays.toString(fmap));
        
        int indx = 0;
        
        for(int i = 0; i < fmap.length; i++) {
            int freq = fmap[i];
            for(int j = 0; j < freq; j++) {
                arr[indx] = i;
                indx++;
            }
        }
    }

    public static void countSort2(int[] arr, int min, int max) {
        int[] fmap = new int[max - min + 1];

        for(int i = 0; i < arr.length; i++) {
            int indx = arr[i] - min; // val - base value
            fmap[indx]++;
        }

        int indx = 0;
        for(int i = 0; i < fmap.length; i++) {
            int val = i + min; // indx + base value
            int freq = fmap[i];

            for(int j = 0; j < freq; j++) {
                arr[indx] = val;
                indx++;
            }
        }
    }

    public static void partition(int[] arr, int pivot) {
        int i = 0;
        int j = 0;

        while(j < arr.length) {
            if(arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
    }

    public static int partitionIndx(int[] arr, int lo, int hi, int pivot) {
        int i = lo;
        int j = lo;

        while(j <= hi) {
            if(arr[j] <= pivot) {
                swap(arr, j, i);
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i - 1;

    }

    public static void quickSort(int[] arr, int lo, int hi) {
        if(lo > hi) 
            return;

        int pivot = arr[hi];

        int pi = partitionIndx(arr, lo, hi, pivot);

        // String str = Arrays.toString(arr);
        // System.out.println("lo : " + lo + " hi : " + hi + " pi : " + pi + " Array : " + str);

        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi + 1, hi);
    }

    public static int quickSelect(int[] arr, int lo, int hi, int k) {
        // if(lo > hi) return -1;
        int pivot = arr[hi];
        int pi = partitionIndx(arr, lo, hi, pivot);

        if(pi > k) {
            // left side
            return quickSelect(arr, lo, pi - 1, k);
        } else if(pi < k) {
            // right side
            return quickSelect(arr, pi + 1, hi, k);
        } else {
            // found
            return pivot;
        }   
    }

    public static void sorting() {
        int[] arr = {10, 15, 19, 57, 25, 36, 14, 12, 11, 24, 27};
        // int[] arr = {4, 2, 2, 1, 0, 1, 2, 1, 1, 3, 3, 4, 7, 0, 9, 0, 9, 9, 7, 7, 6, 6, 5, 8};
        // int[] arr = {7, -2, 4, -1, 3, -2, 1, 7, 6, -1};

        Arrays.sort(arr);

        System.out.println("Array Before sort : " + Arrays.toString(arr));
        // int[] res = mergeSort(arr, 0, arr.length - 1);
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // countSort(arr, 9);
        // countSort2(arr, -2, 7);

        quickSort(arr, 0, arr.length - 1);

        // System.out.println("Res after sort : " + Arrays.toString(res));
        System.out.println("Array after sort : " + Arrays.toString(arr));

        // int[] arr1 = {8, 10, 15, 16, 18, 21, 25};
        // int[] arr2 = {9, 12, 14, 17, 19};
        // int[] res = mergeTwoSortedArrays(arr1, arr2);
        // String str = Arrays.toString(res);
        // System.out.println(str);
    }

    public static void testing() {
        int[] arr = new int[10000];
        int len = 10000;
        for(int i = 0; i < 10000; i++) {
            arr[i] = len;
            len--;
        }

        long start = System.currentTimeMillis();
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
    
    public static void solve() {
        // searching();
        // ques();
        sorting();
        // testing();
    }

    public static void main(String[] args) {
        solve();
    }
}
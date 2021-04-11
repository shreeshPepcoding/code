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


    
    public static void solve() {
        // searching();
        ques();
    }

    public static void main(String[] args) {
        solve();
    }
}
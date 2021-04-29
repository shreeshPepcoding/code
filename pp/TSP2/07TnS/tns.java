import java.util.*;

public class tns {
    // ~~~~~~~~~~~~~~~~~~~~~~Mis~~~~~~~~~~~~~~~~~~~~~~~~~~ 
    public static void display(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }  
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~Searching Algos~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int linearSearch(int[] arr, int data) {
        // O(n)
        for(int i = 0; i < arr.length; i++) {
            if(data == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean binarySearchRec(int[] arr, int lo, int hi, int data) {
        // O(logn)
        if(lo > hi) {
            return false;
        }
        
        int mid = lo + (hi - lo) / 2;
        boolean res = false;
        if(arr[mid] == data) {
            res = true;
        } else if(arr[mid] < data) {
            // right part
            res = binarySearchRec(arr, mid + 1, hi, data);
        } else {
            // left part
            res = binarySearchRec(arr, lo, mid - 1, data);
        }
        return res;
    }

    public static int binarySearchRecIndx(int[] arr, int lo, int hi, int data) {
        // O(logn)
        if(lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;

        int res = -1;
        if(arr[mid] == data) {
            res = mid;
        } else if(arr[mid] < data) {
            // right part
            res = binarySearchRecIndx(arr, mid + 1, hi, data);
        } else {
            // left part
            res = binarySearchRecIndx(arr, lo, mid - 1, data);
        }
        return res;
    }

    public static void searching() {
        int[] arr = {1, 2, 5, 6, 7, 8, 9, 10, 15, 16};
        int data = 11;
        // int indx = linearSearch(arr, data);
        // boolean indx = binarySearchRec(arr, 0, arr.length - 1, data);
        int indx = binarySearchRecIndx(arr, 0, arr.length - 1, data);


        System.out.println(Arrays.toString(arr));
        System.out.println(data + " found at " + indx + " index");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~Questions~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static void sort01(int[] arr) {
        int i = 0; // first unsolved
        int j = 0; // first 1

        while(i < arr.length) {
            if(arr[i] == 1) { // 1
                i++;
            } else { // 0
                swap(arr, i, j);
                i++;
                j++;
            }
        }
    }

    public static void sort012(int[] arr) {
        int i = 0; // first unsolved
        int j = 0; // first 1
        int k = arr.length - 1; // last unsolved

        while(i <= k) {
            if(arr[i] == 0) {
                swap(arr, i, j);
                i++;
                j++;
            } else if(arr[i] == 1) {
                i++;
            } else {
                swap(arr, i, k);
                k--;
            }
        }
    }

    // f(x, n) = 1.x^n + 2.x^(n-1) + 3.x^(n-2) + - - - - - - - - + n.x
    public static int polynomial(int x, int N) {
        if(x == 0) 
            return 0;
        
        int xval = x;
        int sum = 0;

        for(int n = N; n >= 1; n--) {
            sum += n * xval;
            xval *= x;
        }
        return sum;
    }

    public static void ques() {
        // int[] arr = {1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0};

        int res = polynomial(2, 5);
        System.out.println(res);

        // display(arr);
        // sort01(arr);
        // display(arr);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~Sorting Algos~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        int s1 = arr1.length;
        int s2 = arr2.length;

        int[] res = new int[s1 + s2];

        int i = 0; // itr for arr1
        int j = 0; // itr for arr2
        int k = 0; // help to fill the res

        while(i < s1 || j < s2) {
            int ival = i < s1 ? arr1[i] : Integer.MAX_VALUE;
            int jval = j < s2 ? arr2[j] : Integer.MAX_VALUE;

            if(ival > jval) {
                res[k] = jval;
                j++;
            } else {
                res[k] = ival;
                i++;
            }
            k++;
        }

        return res;
    }

    public static void sorting() {
        return;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~Testing~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int[] mergeTwoSortedArrays2(int[] arr1, int[] arr2){

        // System.out.println(Arrays.toString(arr1));
        // System.out.println(Arrays.toString(arr2));
        int[] arr = new int[arr1.length + arr2.length];
        int length = arr1.length > arr2.length ? arr2.length : arr1.length;
        // System.out.println(length);
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < length && j < length) {
            
            // System.out.println(i + " " + j + " " + k);
            if(arr1[i] > arr2[j]) {
                arr[k] = arr2[j];
                j++;
            } else {
                arr[k] = arr1[i];
                i++;
            }
            k++;
        }
        
        while (i < arr1.length) {
            arr[k] = arr1[i];
            i++;
            k++;
        }
    
        while (j < arr2.length) {
            arr[k] = arr2[j];
            j++;
            k++;
        }
    
        return arr;
  }

    public static void testing() {
        int[] arr = {10, 20, 30, 40, 50};
        int[] arr2 = {7, 9, 15, 21};

        int[] res = mergeTwoSortedArrays2(arr, arr2);
        System.out.println(Arrays.toString(res));

        return;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~Main Sections~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void fun() {
        // searching();
        // sorting();
        // ques();
        testing();
    }

    public static void main(String[] args) {
        fun();
    }
}
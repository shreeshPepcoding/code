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

    public static boolean isPrime(int num) {
        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static void printPrime(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if(num == 0 || num == 1) continue;
            if(isPrime(num) == true) {
                System.out.println(num + " is prime");
            } else {
                System.out.println(num + " is not prime");
            }
        }
    }

    public static void sieve(int[] queries, int hi) {
        boolean[] isPrime = new boolean[hi + 1];
        // marks element element as true, just for conviniency
        Arrays.fill(isPrime, true);

        // pre calculate prime
        for(int i = 2; i * i <= hi; i++) {
            if(isPrime[i] == true) {
                for(int j = i + i; j <= hi; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // solve for every queries
        for(int i = 0; i < queries.length; i++) {
            int num = queries[i];
            if(num == 0 || num == 1) continue;

            if(isPrime[num] == true) {
                System.out.println(num + " is prime");
            } else {
                System.out.println(num + " is not prime");
            }
        }
    }

    public static void ques() {
        int[] arr = arrayFiller(50, 1);
        // display(arr);
        // printPrime(arr);
        sieve(arr, 9);
        // int[] arr = {1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0};

        // int res = polynomial(2, 5);
        // System.out.println(res);

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

    public static int[] mergeSort(int[] arr, int lo, int hi) {
        // base case
        if(lo == hi) {
            int[] bres = new int[1];
            bres[0] = arr[lo];
            return bres;
        }

        int mid = lo + (hi - lo) / 2;
        int[] arr1 = mergeSort(arr, lo, mid);
        int[] arr2 = mergeSort(arr, mid + 1, hi);

        int[] res = mergeTwoSortedArrays(arr1, arr2);

        return res;
    }

    public static void partitionAnArray(int[] arr, int pivot) {
        int i = 0;
        int j = 0;

        while(i < arr.length) {
            if(arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
    }

    public static int partitionIndex(int[] arr, int lo, int hi, int pivot) {
        int i = lo;
        int j = lo;

        while(i <= hi) {
            if(arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j - 1;
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        if(lo > hi) return;

        int pivot = arr[hi];
        int pi = partitionIndex(arr, lo, hi, pivot);    
        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi + 1, hi);
    }

    public static void sorting() {
        int[] arr = {90, 70, 20, 60, 50, 30, 80, 40, 10, 45};
        
        display(arr);
        // partitionAnArray(arr, 45);
        quickSort(arr, 0, arr.length - 1);
        // int[] res = mergeSort(arr, 0, arr.length - 1);
        // System.out.println(partitionIndex(arr, 0, arr.length - 1, 45));
        display(arr);
        // display(res);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~Testing~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static int[] arrayFiller(int size, int digits) {
        int power = (int)Math.pow(10, digits);

        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * power);
        }
        return arr;
    }

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
        int[] arr = arrayFiller(10000, 3);
        quickSort(arr, 0, arr.length - 1);

        Arrays.sort(arr);

        long st = System.currentTimeMillis();
        // int[] res = mergeSort(arr, 0, arr.length - 1);
        // quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();

        System.out.println(end - st + " mili seconds");

        // int[] arr = {10, 20, 30, 40, 50};
        // int[] arr2 = {7, 9, 15, 21};

        // int[] res = mergeTwoSortedArrays2(arr, arr2);
        // System.out.println(Arrays.toString(res));
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
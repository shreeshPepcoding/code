import java.util.Arrays;

public class tns {
    public static boolean linearSearch(int[] arr, int data) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                return true;
            }
        }
        return false;
    }

    // dtf => data to find
    public static boolean BinarySearchRec(int[] arr, int si, int ei, int dtf) {
        if (si > ei) {
            return false;
        }

        int mid = (si + ei) / 2;
        boolean res = false;
        if (arr[mid] == dtf) {
            res = true;
        } else if (arr[mid] < dtf) { // data is present in the right section
            res = BinarySearchRec(arr, mid + 1, ei, dtf);
        } else { // data is present in left
            res = BinarySearchRec(arr, si, mid - 1, dtf);
        }
        return res;
    }

    public static int BinarySearchIndx(int[] arr, int si, int ei, int dtf) {
        if (si > ei) {
            return -1;
        }

        int mid = (si + ei) / 2;
        int res = -1;
        if (arr[mid] == dtf) {
            res = mid;
        } else if (arr[mid] < dtf) { // data is present in the right section
            res = BinarySearchIndx(arr, mid + 1, ei, dtf);
        } else { // data is present in left
            res = BinarySearchIndx(arr, si, mid - 1, dtf);
        }
        return res;
    }

    public static void searching() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        int dtf = 90;
        // System.out.println(linearSearch(arr, dtf));
        System.out.println(BinarySearchIndx(arr, 0, arr.length - 1, dtf));

    }

    // display to print data
    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort01(int[] arr) {
        int i = 0;
        int j = 0;

        while (j < arr.length) {
            if (arr[j] == 1) {
                j++;
            } else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }

    }

    // Dutch national flag
    public static void sort012(int[] arr) {
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while (j <= k) {
            if (arr[j] == 0) {
                swap(arr, j, i);
                i++;
                j++;
            } else if (arr[j] == 1) {
                j++;
            } else {
                swap(arr, j, k);
                k--;
            }
        }
    }

    // Bonus Question => 1.x^n + 2.x^(n-1) + 3.x^(n-2) + ---------- + n.X^1
    public static void fun(int X, int N) {
        int xval = X;
        int sum = 0;
        for(int n = N; n >= 1; n--) {
            sum += xval * n;
            xval *= X;
        }
        System.out.println(sum);
    }

    public static boolean isPrime(int n) {
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }

    public static void PrimeCheck1(int[] query, int hi) {
        for(int i = 0; i < query.length; i++) {
            int num = query[i];
            if(isPrime(num)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            // System.out.println(isPrime(num));
        }
    }

    public static void Sieve(int[] query, int hi) {
        boolean[] primes = new boolean[hi + 1];
        // initialise with same value
        Arrays.fill(primes, true);

        // preprocessing (n.root())
        for(int i = 2; i * i <= hi; i++) {
            if(primes[i] == true) {
                // unmark your factor
                for(int j = 2 * i; j<= hi; j += i) {
                    primes[j] = false;
                }
            } else {
                // if you are markes, that means your factors are also marked
                continue;
            }
        }

        // solve queries in o(1) 
        for(int q = 0; q < query.length; q++) {
            int num = query[q];
            System.out.println(primes[num]);
        }

    }



    public static int[] mergeTwoSortedArray(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;

        int[] res = new int[n + m];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < n && j < m) {
            if (A[i] < B[j]) {
                res[k] = A[i];
                i++;
            } else {
                res[k] = B[j];
                j++;
            }
            k++;
        }

        while (i < n) {
            res[k] = A[i];
            i++;
            k++;
        }

        while (j < m) {
            res[k] = B[j];
            j++;
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
        
        int mid = (lo + hi) / 2;
        int[] arr1 = mergeSort(arr, lo, mid);
        int[] arr2 = mergeSort(arr, mid + 1, hi);

        int[] res = mergeTwoSortedArray(arr1, arr2);

        return res;
    }

    public static void bubbleSort(int[] arr) {
        for(int itr = 1; itr <= arr.length - 1; itr++) {
            for(int j = 0; j < arr.length - itr; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for(int itr = 0; itr < arr.length - 1; itr++) {
            int minIndx = itr;
            for(int j = itr + 1; j < arr.length; j++) {
                if(arr[j] < arr[minIndx]) {
                    minIndx = j;    
                }
            }
            // minIndx have index of min value
            swap(arr, itr, minIndx);
        }
    }

    public static void insertionSort(int[] arr) {
        for(int itr = 1; itr < arr.length; itr++) {
            for(int j = itr; j > 0; j--) {
                if(arr[j-1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void CountSort(int[] arr, int min, int max) {
        int[] fmap = new int[max - min + 1]; // frequency map

        // mark the frequency
        for(int val : arr) {
            int indx = val - min;
            fmap[indx]++;
        }

        int k = 0;
        // fill the actual array in sorted manner using fmap
        for(int i = 0; i < fmap.length; i++) {
            int val = i + min;
            int freq = fmap[i];
            for(int j = 0; j < freq; j++) {
                arr[k] = val;
                k++;
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
    
    public static int PartitionIndex(int[] arr, int pivot, int lo, int hi) {
        int i = lo;
        int j = lo;

        while(j <= hi) {
            if(arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i - 1;
    }

    // kth Smallest
    public static int quickSelect(int[] arr, int lo, int hi, int k) {
        int pivot = arr[hi];
        int pi = PartitionIndex(arr, pivot, lo, hi);

        if(pi < k) {
            return quickSelect(arr, pi + 1, hi, k);
        } else if(pi > k) {
            return quickSelect(arr, lo, pi - 1, k);
        } else {
            return pivot; // arr[pi]
        }
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        // base case
        if(lo > hi) {
            return;
        }

        // segregate array on the basis of pivot element
        int pivot = arr[hi];
        int pi = PartitionIndex(arr, pivot, lo, hi); // partition index
        
        quickSort(arr, lo, pi - 1); // solve for left side
        quickSort(arr, pi + 1, hi); // solve for right side
    }

    public static int findPivot(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;

        while(lo < hi) {
            int mid = (lo + hi) / 2;
            if(arr[mid] < arr[hi]) { // move toward left
                hi = mid;
            } else { // move toward right
                lo = mid + 1;
            }
        }
        return arr[lo];
    }


    public static void problem() {
        // int[] arr = {0, 0, 0,2, 2, 1, 1, 0, 1, 2, 2, 0, 1, 1, 2, 2, 1, 0, 0, 1, 0};
        // display(arr);
        // sort01(arr);
        // sort012(arr);
        // display(arr);

        // int[] A = { 0, 1, 10, 11, 12, 13 };
        // int[] B = { 2, 3, 4, 5, 6 };

        // display(A);
        // display(B);
        // int[] res = mergeTwoSortedArray(A, B);
        // display(res);
        // fun(2, 3);
        // int hi = 10;
        // int[] query = {3, 6, 9, 8, 7, 4, 5, 9, 7, 5, 3,6, 6, 5, 4, 8, 9, 3, 2, 5, 8, 7, 4, 9};
        // PrimeCheck1(query, hi);
        // Sieve(query, hi);

        int[] arr = {9, 6, 3, 2, 5, 8, 7, 4, 1, 2, 3, 9, 8, 7, 4, 5, 6};
        int pivot = 5;
        display(arr);
        partition(arr, pivot);
        display(arr);
    }


    public static void sorting() {
        // int[] arr = {5, 4, 7, 8, 6, 1, 3, 2, 9};
        int[] arr = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        // int[] arr2 = {3, 6, 9, 8, 7, 4, 5, 9, 7, 5, 3,6, 1, 1, 1, 6, 5, 4, 8, 9, 3, 2, 5, 8, 7, 4, 9};
        // int[] res = mergeSort(arr, 0, arr.length - 1);
        // display(res);
        // display(arr);
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // CountSort(arr2, 1, 9);
        quickSort(arr, 0, arr.length - 1);
        display(arr);

    }

    public static void solve() {
        // searching();
        sorting();
        // problem();
    }

    public static void main(String[] args) {
        solve();
    }
}
import java.util.Random;

public class test {

    public static void fillArray(int[] arr, int sz) {
        for (int i = 0; i < sz; i++) {
            arr[i] = (int) (Math.random() * 10000) % 1000;
        }
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int PartitionIndex(int[] arr, int pivot, int lo, int hi) {
        int i = lo;
        int j = lo;

        while (j <= hi) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i - 1;
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        // base case
        if (lo > hi) {
            return;
        }

        // segregate array on the basis of pivot element
        int pivot = arr[hi];
        int pi = PartitionIndex(arr, pivot, lo, hi); // partition index

        quickSort(arr, lo, pi - 1); // solve for left side
        quickSort(arr, pi + 1, hi); // solve for right side
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

    public static void main(String[] args) {
        int sz = 100000;
        int[] arr = new int[sz];
        fillArray(arr, sz);

        quickSort(arr, 0, sz - 1);

        long start = System.currentTimeMillis();
        quickSort(arr, 0, sz - 1);
        // mergeSort(arr, 0, sz - 1);
        // bubbleSort(arr);   
        // insertionSort(arr);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        // display(arr);
    }
}

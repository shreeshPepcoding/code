import java.util.*;

public class binarySearchWithCeilFloor {
    
    private static int floorIndex(int[] arr, int data) {
        int lo = 0;
        int hi = arr.length - 1;
        int indx = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] < data) {
                indx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return indx;
    }
    
    private static int ceilIndex(int[] arr, int data) {
        int lo = 0;
        int hi = arr.length - 1;
        int indx = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(data < arr[mid]) {
                indx = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return indx;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 5, 6, 7,  7, 8,  9,  10, 10};
                    {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}
        System.out.println("Ceil Indexes");
        for(int val : arr) {
            int indx = ceilIndex(arr, val);
            System.out.println(val + " --> " + indx);
        }
        System.out.println("\nFloor Indexes");
        for(int val : arr) {
            int indx = floorIndex(arr, val);
            System.out.println(val + " --> " + indx);
        }
    }
}

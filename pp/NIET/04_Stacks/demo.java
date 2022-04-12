import java.util.*;

public class demo {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                int v1 = arr[j];
                int v2 = arr[j + 1];
                if(v1 > v2) {
                    swap(arr, j, j+1);
                }
            } 
        }
    }

    public static class Num implements Comparable<Num>{
        int val;

        Num(int val) {
            this.val = val;
        }

        public int compareTo(Num other) {
            return this.val - other.val;
        }
    }

    public static void displayNum(Num[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].val + " ");
        }
        System.out.println();
    }

    public static void sort2(Num[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                Num v1 = arr[j];
                Num v2 = arr[j + 1];
                if(v1.compareTo(v2) < 0) {
                    // v1 is smaller than v2
                    // nothing to do

                } else if(v1.compareTo(v2) > 0) {
                    // v1 is greater than v2
                    // swap
                    Num temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else {
                    // v1 is equal to v2
                    // nothing to do
                }
            } 
        }
    }

    public static void fun() {
        int[] arr = {10, 8, 7, 9, 6, 5, 9, 1, 3, 14, 25, 3, 1};
        // System.out.println(Arrays.toString(arr));
        // sort(arr);
        // System.out.println(Arrays.toString(arr));  

        Num[] narr = new Num[arr.length];
        for(int i = 0; i < arr.length; i++) {
            narr[i] = new Num(arr[i]);
        }
        displayNum(narr);
        sort2(narr);
        displayNum(narr);
    }

    public static void main(String[] args) {
        fun();
    }
}

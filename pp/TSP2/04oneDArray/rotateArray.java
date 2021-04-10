import java.util.*;

public class rotateArray {

    public static void reverse(int[] arr, int left, int right) {
        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void rotate(int[] arr, int k){
        int size = arr.length;
        // make a valid k
        k = k  % size;
        if(k < 0)
            k = k + size;

        // reverse
        reverse(arr, 0, size - k - 1); // first half
        reverse(arr, size - k, size - 1); // second half
        reverse(arr, 0, size - 1); // complete
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int k = -4;
        rotate(arr, k);

        for(int val : arr)
            System.out.print(val + " ");
        
        System.out.println();
    }
}

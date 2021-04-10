import java.util.*;

public class reverse {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for(int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();

        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
            // swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        for(int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

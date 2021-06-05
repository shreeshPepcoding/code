import java.util.*;

public class kRotate {

    public static void reverse(int[] arr, int left, int right) {
        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void rotate(int[] arr, int k) {
        int n = arr.length;
        // k management
        k = k % n;
        if(k < 0) {
            k += n;
        }

        reverse(arr, 0, n - k - 1); // first half reversal
        reverse(arr, n - k, n - 1); // second half reversal
        reverse(arr, 0, n - 1); // complete reversal
    }

    public static void main(String[] args) {
        return;
    }
}

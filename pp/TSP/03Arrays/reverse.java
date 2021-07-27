import java.util.*;

public class reverse {

    public static void reverseArr(int[] arr) {
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
    }

    public static void solve() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        reverseArr(arr);
    }

    public static void main(String[] args) {
        solve();
    }
}

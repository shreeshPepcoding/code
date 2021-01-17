import java.util.Arrays;

public class quickTest {

    public static int partion(int[] arr, int lo, int hi, int pivot) {
        int i = lo;
        int j = lo;


        while(j <= hi) {
            if(arr[j] <= pivot) {

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j++;
            } else {
                j++;
            }
        }
        return i - 1;
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        if(lo > hi) return;
        int pivot = arr[hi];
        int pi = partion(arr, lo, hi, pivot);

        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi + 1, hi);
    }

    public static void main(String[] args) {
        int[] arr = {10, 50, 0, 80, 30, 20, 40, 60, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

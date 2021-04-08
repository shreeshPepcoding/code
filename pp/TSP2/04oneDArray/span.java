import java.util.*;

public class span {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr, int n) {
        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static int min(int[] arr) {
        int mn = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] < mn) {
                mn = arr[i];
            }
        }
        return mn;
    }

    public static int max(int[] arr) {
        int mx = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }


    public static void main(String[] args) {
        // take size from user
        int n = scn.nextInt();
        // create array of size n
        int[] arr = new int[n];

        takeInput(arr, n);

        int min = min(arr);
        int max = max(arr);

        int span = max - min;
        System.out.println(span);
    }    
}

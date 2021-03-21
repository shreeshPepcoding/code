import java.util.*;

public class array {

    public static Scanner scn = new Scanner(System.in);

    public static void input(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void display(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int maxArray(int[] arr) {
        int max = Integer.MIN_VALUE; // -infinity

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
               max = arr[i];
            }
        }

        return max;
    }

    public static int minArray(int[] arr) {
        int min = Integer.MAX_VALUE; // +infinity

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] < min) {
               min = arr[i];
            }
        }

        return min;
    }

    public static void main(String[] args) {
        // 1. create an array
        // int[] arr = new int[0];
        // 2. take input from user and fill the array
        input(arr);
        // 3. try to find min value from array
        int min = minArray(arr);
        // 4. try to find maximum value from array
        int max = maxArray(arr);
        // 5. print maximum and min value
        System.out.println("Max is : " + max);
        System.out.println("Min is : " + min);
        // 6. display array
        display(arr);

    }
}

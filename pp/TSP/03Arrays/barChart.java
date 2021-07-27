import java.util.*;

public class barChart {

    public static Scanner scn = new Scanner(System.in);
    
    public static void takeInput(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
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

    public static void printBarChart(int[] arr) {
        // find max from array
        int max = max(arr);

        for(int ht = max; ht >= 1; ht--) {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] >= ht) {
                    // print star
                    System.out.print("*\t");
                } else {
                    // print space
                    System.out.print("\t");
                }
            }
            // hit enter
            System.out.println();
        }

        // print array
        // for(int i = 0; i < arr.length; i++) {
        //     System.out.print(arr[i] + "\t");
        // }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];        
        takeInput(arr);
        printBarChart(arr);
    }
}
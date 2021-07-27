import java.util.*;

public class demo {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static void display(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void displayArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void testing(int[] arr) {
        // new array creation
        int[] arr2 = new int[arr.length];

        // new array prepare
        for(int i = 0; i < arr.length; i++) {
            arr2[i] = 2 * arr[i];
        }

        // make connection of array to array2
        arr = arr2;
        displayArr(arr);
    }

    public static void main(String[] args) {

        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};
        testing(arr);
        displayArr(arr);

        // int r = scn.nextInt();
        // int c = scn.nextInt();

        // int[][] arr = new int[r][c];
        // takeInput(arr);
        // display(arr);
    }

}
import java.util.*;

public class demo {

    public static Scanner scn = new Scanner(System.in);

    public static void display(int[][] arr) {
        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c < arr[0].length; c++) {
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void takeInput(int[][] arr) {
        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c < arr[0].length; c++) {
                arr[r][c] = scn.nextInt();
            }
        }
    }

    public static void fun() {
        // create 2D array
        int r = 3;
        int c = 4;
        int[][] arr = new int[r][c];

        // initialise
        // int[][] arr = {
        //     {11, 12, 13, 14},
        //     {21, 22, 23, 24},
        //     {31, 32, 33, 34}
        // };

        // how to calculate rows and column
        int row = arr.length;
        int col = arr[0].length;

        // input
        takeInput(arr);
        // ouput
        display(arr);
    }

    public static void main(String[] args) {
        fun();
    }
}
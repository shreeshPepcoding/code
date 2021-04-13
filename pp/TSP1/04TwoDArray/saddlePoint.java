import java.util.*;

public class saddlePoint {
    
    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static int getMinColIndxFromR(int[][] arr, int r) {
        // fix - > row
        // vary -> col
        int c = 0;
        for(int j = 1; j < arr[0].length; j++) {
            if(arr[r][j] < arr[r][c]) {
                c = j;
            }
        }
        return c;
    }

    public static int getMaxRowIndxFromC(int[][] arr, int c) {
        // fix = > col
        // vary => row
        int r = 0;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i][c] > arr[r][c]) {
                r = i;
            }
        }
        return r;
    }

    public static void printSaddlePoint(int[][] arr) {
        for(int r = 0; r < arr.length; r++) {
            // find column index of min element from rth row
            int mnCI = getMinColIndxFromR(arr, r); // min column Index
            int mxRI = getMaxRowIndxFromC(arr, mnCI); // max row Index
            if(mxRI == r) {
                // System.out.println("Saddle point is  : " + arr[r][mnCI] + " at (" + r + ", " + mnCI + ")");
                System.out.println(arr[r][mxRI]);
                return;
            }
        }
        System.out.println("Input invalid");
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        // int m = scn.nextInt();
        int[][] arr = new int[n][n];
        takeInput(arr);

        // int[][] arr = {
        //     {14, 12, 11, 13, 15},
        //     {23, 22, 20, 24, 25},
        //     {31, 32, 33, 34, 35},
        //     {43, 42, 10, 44, 45}
        // };

        printSaddlePoint(arr);
    }
}

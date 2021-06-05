import java.util.*;

public class transpose {

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
    
    public static void transposeMatrix(int[][] arr) {
        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c <= r; c++) {
                int temp  = arr[r][c];
                arr[r][c] = arr[c][r];
                arr[c][r] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        // take r and c
        int r = scn.nextInt();
        int c = scn.nextInt();

        // make 2 D array
        int[][] arr = new int[r][c];
        
        // take input
        takeInput(arr);
        
        // display
        display(arr);
        System.out.println("==============");
        // call to transpose
        transposeMatrix(arr);
        // display matrix to fig. the output
        display(arr);

    }
}

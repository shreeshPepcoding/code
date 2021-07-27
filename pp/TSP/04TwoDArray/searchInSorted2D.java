import java.util.*;

public class searchInSorted2D {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }


    public static void searchInMatrix(int[][] arr, int data) {
        int r = 0;
        int c = arr[0].length - 1;

        // while(r >= 0 && r < arr.length && c >= 0 && c < arr[0].length) {
        
        while(r < arr.length && c >= 0) {
            if(arr[r][c] == data) {
                System.out.println(r + "\n" + c);
                return;
            } else if(arr[r][c] < data) {
                r++;
            } else {
                c--;
            }
        }
        System.out.println("Not Found");
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        // int m = scn.nextInt();
        int[][] arr = new int[n][n];
        takeInput(arr);
        int data = scn.nextInt();
        searchInMatrix(arr, data);
    }    
}

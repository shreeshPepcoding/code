import java.io.*;
import java.util.*;

public class saddlePoint {
    
    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }
    
    public static void saddlePoint(int[][] arr, int n) {
        for(int r = 0; r < n; r++) {
            int minCI = 0;
            for(int c = 1; c < n; c++) {
                if(arr[r][c] < arr[r][minCI]) {
                    minCI = c;
                }
            }
            // travel in minCI and check is greater is present or not
            int val = arr[r][minCI];
            boolean flag = true;
            for(int i = 0; i < n; i++) {
                if(arr[i][minCI] > val) {
                    flag = false;
                    break;
                }
            }
            
            if(flag == true) {
                // saddle point is encounter
                System.out.println(arr[r][minCI]);
                return;
            }
        }
        System.out.println("Invalid input");
    }
    
    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        takeInput(arr, n);
        saddlePoint(arr, n);
    }

}

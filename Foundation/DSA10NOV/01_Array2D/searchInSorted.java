import java.io.*;
import java.util.*;

public class searchInSorted {
    
    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }
    
    public static void SearchInSortedMatrix(int[][] arr, int x) {
        int r = 0;
        int c = arr[0].length - 1;
        
        while(r < arr.length && c >= 0) {
            if(arr[r][c] == x) {
                System.out.println(r);
                System.out.println(c);
                return;
            } else if(arr[r][c] < x) {
                r++;
            } else {
                c--;
            }
        }
        System.out.println("Not Found");
    }
    
    
    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        takeInput(arr, n);
        int x = scn.nextInt();
        
        SearchInSortedMatrix(arr, x);
    }

}

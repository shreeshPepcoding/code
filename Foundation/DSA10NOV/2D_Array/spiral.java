import java.io.*;
import java.util.*;

public class spiral {
    
    public static Scanner scn = new Scanner(System.in);
    
    public static void takeInput(int[][] arr, int n, int m) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }
    
    public static void spiralDisplay(int[][] arr) {
        int minr = 0;
        int maxr = arr.length - 1;
        int minc = 0;
        int maxc = arr[0].length - 1;
        
        int tele = arr.length * arr[0].length;
        int cnt = 0;
        
        while(cnt < tele) {
            // left wall
            for(int i = minr, j = minc; i <= maxr && cnt < tele; i++) {
                System.out.println(arr[i][j]);
                cnt++;
            }
            minc++;
            
            // bottom wall
            for(int i = maxr, j = minc; j <= maxc && cnt < tele; j++) {
                System.out.println(arr[i][j]);
                cnt++;
            }
            maxr--;
            
            // right wall
            for(int i = maxr, j = maxc; i >= minr && cnt < tele; i--) {
                System.out.println(arr[i][j]);
                cnt++;
            }
            maxc--;
            
            // top wall
            for(int i = minr, j = maxc; j >= minc && cnt < tele; j--) {
                System.out.println(arr[i][j]);
                cnt++;
            }
            minr++;
        }
    }
    
    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int m = scn.nextInt();
        
        int[][] arr = new int[n][m];
        takeInput(arr, n, m);
        spiralDisplay(arr);
    }

}

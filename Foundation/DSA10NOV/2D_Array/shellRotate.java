import java.io.*;
import java.util.*;

public class shellRotate {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }
    
    public static int[] getOneDArray(int[][] arr, int s) {
        int minr = s - 1;
        int minc = s - 1;
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;
                // lw + bw + rw + tw - 4
        int sz = 2 * (maxr - minr + maxc - minc);
        
        int[] oneD = new int[sz];
        int indx = 0;
        
        // left wall
        for(int i = minr, j = minc; i <= maxr; i++) {
            oneD[indx] = arr[i][j];
            indx++;
        }
        minc++;
        // bottom wall
        for(int i = maxr, j = minc; j <= maxc; j++) {
            oneD[indx] = arr[i][j];
            indx++;
        }
        maxr--;
        // right wall
        for(int i = maxr, j = maxc; i >= minr; i--) {
            oneD[indx] = arr[i][j];
            indx++;
        }
        maxc--;
        // top wall
        for(int i = minr, j = maxc; j >= minc; j--) {
            oneD[indx] = arr[i][j];
            indx++;
        }
        minr++;
        return oneD;
    }
    
    public static void reverse(int[] oneD, int si, int ei) {
        while(si < ei) {
            int temp = oneD[si];
            oneD[si] = oneD[ei];
            oneD[ei] = temp;
            
            si++;
            ei--;
        }
    } 
    
    public static void rotate(int[] oneD, int r) {
        r = r % oneD.length;
        if(r < 0) {
            r = r + oneD.length;
        }
        
        reverse(oneD, 0 , oneD.length - r - 1);
        reverse(oneD, oneD.length - r , oneD.length - 1);
        reverse(oneD, 0 , oneD.length - 1);
    }
    
    public static void placeOneDArray(int[][] arr, int[] oneD, int s) {
        int minr = s - 1;
        int minc = s - 1;
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;
        
        int indx = 0;
        
        // left wall
        for(int i = minr, j = minc; i <= maxr; i++) {
            arr[i][j] = oneD[indx];
            indx++;
        }
        minc++;
        // bottom wall
        for(int i = maxr, j = minc; j <= maxc; j++) {
            arr[i][j] = oneD[indx];
            indx++;
        }
        maxr--;
        // right wall
        for(int i = maxr, j = maxc; i >= minr; i--) {
            arr[i][j] = oneD[indx];
            indx++;
        }
        maxc--;
        // top wall
        for(int i = minr, j = maxc; j >= minc; j--) {
            arr[i][j] = oneD[indx];
            indx++;
        }
        minr++;
    }
    
    public static void rotateShell(int[][] arr, int s, int r) {
        // steps
        // 1. get oneD array having elemnets of s shell
        int[] oneD = getOneDArray(arr, s);
        // 2. rotate oneD array from rth rotations
        rotate(oneD, r);
        // 3. place oneD array in actual array 
        placeOneDArray(arr, oneD, s);
    }

    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] arr = new int[n][m];
        takeInput(arr, n, m);
        int s = scn.nextInt();
        int r = scn.nextInt();
        
        rotateShell(arr, s, r);
        
        display(arr);
    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}

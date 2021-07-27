import java.util.*;

public class shellRotate {
    
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

    public static int[] getOneDArrayFromMatrix(int[][] matrix, int s) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rmin = s - 1;
        int cmin = s - 1;
        int rmax = rows - s;
        int cmax = cols - s;

        int size = 2 * (rmax - rmin + cmax - cmin);

        int[] arr = new int[size];
        int idx = 0;
        // fill one D Array
        // left wall, fix -> min col
        for(int r = rmin; r <= rmax && idx < size; r++) {
            arr[idx] = matrix[r][cmin];
            idx++;
        }
        cmin++;

        // bottom wall, fix -> max row
        for(int c = cmin; c <= cmax && idx < size; c++) {
            arr[idx] = matrix[rmax][c];
            idx++;
        }
        rmax--;

        // right wall, fix -> max col
        for(int r = rmax; r >= rmin && idx < size; r--) {
            arr[idx] = matrix[r][cmax];
            idx++;
        }
        cmax--;

        // top wall, fix->rmin
        for(int c = cmax; c >= cmin && idx < size; c--) {
            arr[idx] = matrix[rmin][c];
            idx++;
        }
        rmin++;
        
        return arr;
    }

    public static void reverse(int[] arr, int left, int right) {
        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void rotate(int[] arr, int k) {
        int n = arr.length;
        // k management
        k = k % n;
        if(k < 0) {
            k += n;
        }

        reverse(arr, 0, n - k - 1); // first half reversal
        reverse(arr, n - k, n - 1); // second half reversal
        reverse(arr, 0, n - 1); // complete reversal
    }

    public static void setOneDArrayInMatrix(int[][] matrix, int s, int[] arr) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rmin = s - 1;
        int cmin = s - 1;
        int rmax = rows - s;
        int cmax = cols - s;

        int idx = 0;
        int size = arr.length;
        // fill one D Array
        // left wall, fix -> min col
        for(int r = rmin; r <= rmax && idx < size; r++) {
            matrix[r][cmin] = arr[idx];
            idx++;
        }
        cmin++;

        // bottom wall, fix -> max row
        for(int c = cmin; c <= cmax && idx < size; c++) {
            matrix[rmax][c] = arr[idx];
            idx++;
        }
        rmax--;

        // right wall, fix -> max col
        for(int r = rmax; r >= rmin && idx < size; r--) {
            matrix[r][cmax] = arr[idx];
            idx++;
        }
        cmax--;

        // top wall, fix->rmin
        for(int c = cmax; c >= cmin && idx < size; c--) {
            matrix[rmin][c] = arr[idx];
            idx++;
        }
        rmin++;
    }

    public static void rotateShell(int[][] matrix, int s, int k) {
        int[] sthArr = getOneDArrayFromMatrix(matrix, s); // array from sth shell
        rotate(sthArr, k);
        setOneDArrayInMatrix(matrix, s, sthArr);
    }
    

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        takeInput(arr);
        int s = scn.nextInt();
        int k = scn.nextInt();

        rotateShell(arr, s, k);
        display(arr);
    }
}

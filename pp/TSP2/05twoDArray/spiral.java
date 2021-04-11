import java.util.*;

public class spiral {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static void spiralDisplay(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int rmin = 0;
        int rmax = n - 1;
        int cmin = 0;
        int cmax = m - 1;

        int tele = n * m;
        while(tele > 0) {
            // left wall, fix-> minCol
            for(int r = rmin; r <= rmax && tele > 0; r++) {
                System.out.println(arr[r][cmin]);
                tele--;
            }
            cmin++;

            // bottom wall, fix-> maxRow
            for(int c = cmin; c <= cmax && tele > 0; c++) {
                System.out.println(arr[rmax][c]);
                tele--;
            }
            rmax--;

            // right wall, fix-> maxCol
            for(int r = rmax; r >= rmin && tele > 0; r--) {
                System.out.println(arr[r][cmax]);
                tele--;
            }
            cmax--;

            // top wall, fix-> minRow
            for(int c = cmax; c >= cmin && tele > 0; c--) {
                System.out.println(arr[rmin][c]);
                tele--;
            }
            rmin++;
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        takeInput(arr);
        spiralDisplay(arr);
    }
}

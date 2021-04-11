import java.util.*;

public class waveTraversal {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static void waveDislpay(int[][] arr) {
        for(int c = 0; c < arr[0].length; c++) {
            if(c % 2 == 0) {
                // even column -> display row top -> bottom
                for(int r = 0; r < arr.length; r++) {
                    System.out.println(arr[r][c]);
                }
            } else {
                // odd column -> display row bottom -> top
                for(int r = arr.length - 1; r >= 0; r--) {
                    System.out.println(arr[r][c]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int r = scn.nextInt();
        int c = scn.nextInt();
        int[][] arr = new int[r][c];
        takeInput(arr);
        waveDislpay(arr);
    }
}

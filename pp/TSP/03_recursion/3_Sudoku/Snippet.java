import java.util.*;

public class Main {

    public static void solveSudoku(char[][] board) {
        // write your code here
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = 9;
        char[][] arr = new char[n][n];
        for(int i = 0; i < n; i++) {
            String str = scn.nextLine();
            for(int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        solveSudoku(arr);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
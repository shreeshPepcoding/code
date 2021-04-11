import java.util.*;

public class matrixMult {

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

    public static void MatrixMultiplication(int[][] mat1, int[][] mat2) {
        int r1 = mat1.length;
        int c1 = mat1[0].length;

        int r2 = mat2.length;
        int c2 = mat2[0].length;

        if(c1 != r2) {
            // multiplication not possible
            System.out.println("Invalid input");
            return;
        }

        int[][] res = new int[r1][c2];

        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                // here i have to fill i, j
                // ith row for mat1, jth col for mat is fixed
                int mul = 0;
                for(int k = 0; k < c1; k++) {
                    mul += mat1[i][k] * mat2[k][j];
                }
                res[i][j] = mul;
            }
        }

        display(res);
    }

    public static void main(String[] args) {
        int r1 = scn.nextInt();
        int c1 = scn.nextInt();
        int[][] mat1 = new int[r1][c1];
        takeInput(mat1);

        int r2 = scn.nextInt();
        int c2 = scn.nextInt();
        int[][] mat2 = new int[r2][c2];
        takeInput(mat2);
        
        MatrixMultiplication(mat1, mat2);
    }
}

import java.util.*;

public class arr {

    public static Scanner scn = new Scanner(System.in);



    public static void arrSolve() {
        int sz = 5;
        // reference on stack
        int[] arr;
        // memory allocate on heap
        arr = new int[sz];
        
        // set array
        // arr[0] = 10;
        // arr[1] = 20;
        // arr[2] = 30;
        // arr[3] = 40;
        // arr[4] = 50;

        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        // print array
        // System.out.println(arr[0]); 
        // System.out.println(arr[1]);
        // System.out.println(arr[2]);
        // System.out.println(arr[3]);
        // System.out.println(arr[4]);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        arrSolve();
        ques();
    }
}
import java.util.*;

public class recursion {

    public static void display(int[] arr, int indx) {
        if(indx == arr.length) {
            System.out.println();
            return;
        }
        
        System.out.print(arr[indx] + " ");
        display(arr, indx + 1);
    }

    public static void displayRev(int[] arr, int indx) {
        if(indx == arr.length) return;

        displayRev(arr, indx + 1);
        System.out.println(arr[indx]);
    }

    public static int maxOfArray(int[] arr, int indx) {
        if(indx == arr.length - 1) 
            return arr[indx];
            // return Integer.MIN_VALUE;

        int rmax = maxOfArray(arr, indx + 1);
        // comparsion between rmax vs arr[indx]
        
        return Math.max(rmax, arr[indx]);
        
        // if(rmax > arr[indx]) {
        //     return rmax;
        // } else {
        //     return arr[indx];
        // }
    }

    public static int firstIndex(int[] arr, int indx, int data) {
        if(indx == arr.length) 
            return -1;
        
        if(arr[indx] == data) 
            return indx;

        int rindx = firstIndex(arr, indx + 1, data);
        return rindx;
    }

    public static int lastIndex(int[] arr, int indx, int data) {
        if(indx == arr.length) {
            return -1;
        }

        int rindx = lastIndex(arr, indx + 1, data);

        if(rindx == -1) {
            if(arr[indx] == data)
                rindx = indx;
        }

        return rindx;
    }

    public static void ques() {
        // int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int[] arr = {20, 14, 12, 11, 19, 15, 15, 1, 7};

        // int ans = maxOfArray(arr, 0);
        // int ans = firstIndex(arr, 0, 15);
        int ans = lastIndex(arr, 0, 15);
        System.out.println(ans);
        // display(arr, 0);
        // displayRev(arr, 0);



        // System.out.println("Recursion with array");
    }

    public static void main(String[] args) {
        ques();
    }
}

// Scanner scn = new Scanner(System.in);
// int n = scn.nextInt();
// int[] arr = new int[n];
// for(int i = 0; i < n; i++) 
//     arr[i] = scn.nextInt();

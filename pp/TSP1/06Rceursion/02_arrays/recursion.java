import java.util.*;

public class recursion {

    public static void display(int[] arr, int i) {
        if(i == arr.length) 
            return;
        // my work
        System.out.println(arr[i]);
        // faith
        display(arr, i + 1);
    }

    public static void displayReverse(int[] arr, int i) {
        // base case
        if(i == arr.length) {
            return;
        }

        // faith
        displayReverse(arr, i + 1);
        // my work
        System.out.println(arr[i]);
    }

    public static int maxFromArray(int[] arr, int indx)  {
        if(indx == arr.length) {
            return Integer.MIN_VALUE;
        }

        int rres = maxFromArray(arr, indx + 1);
        return Math.max(arr[indx], rres);
    }

    // dtf => data to find
    public static boolean find(int[] arr, int indx, int dtf) {
        // if(indx == arr.length) 
        //     return false;

        // // check yourself
        // if(arr[indx] == dtf)
        //     return true;
        
        // // otherwise, rres is ans
        // return find(arr, indx + 1, dtf);

        return indx != arr.length ? arr[indx] == dtf || find(arr, indx + 1, dtf) : false;
    }

    public static int firstIndx(int[] arr, int indx, int dtf) {
        if(indx == arr.length) 
            return -1;

        // check yourself
        if(arr[indx] == dtf)
            return indx;
        
        // otherwise, rres is ans
        return firstIndx(arr, indx + 1, dtf);
    }

    public static void ques() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        // display(arr, 0);

        System.out.println(find(arr, 0, 55));
    }

    public static void extra() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        // call to recursion function
    }

    public static void main(String[] args) {
        ques();
    }
}
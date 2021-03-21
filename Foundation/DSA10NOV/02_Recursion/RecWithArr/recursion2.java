import java.util.*;

public class recursion2 {
    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void main(String[] args) {

        // int n = scn.nextInt();
        // int[] arr = new int[n];
        // takeInput(arr, n);

        // diasplayArr(arr, 0);
        int data = 52;
        int[] arr = { 10, 20, 30, 40, 50, 50, 50, 60, 70, 80, 90, 100 };
        boolean res = find2(arr, 0, data);
        if (res == true) {
            System.out.println("Present");
        } else {
            System.out.println("Not Present");
        }
    }

    public static int[] allIndices(int[] arr, int data, int indx, int countSoFar) {
        if(indx == arr.length) {
            // make an array to store the indexes of data
            int[] bres = new int[countSoFar];
            return bres;
        }

        if(arr[indx] == data)
            countSoFar++;

        int[] rres = allIndices(arr, data, indx + 1, countSoFar);
        if(arr[indx] == data) {
            rres[countSoFar - 1] = indx;
        }
        return rres;
    }

    // bottom up approach - helps in first occurrence
    public static int first(int[] arr, int indx, int data) {
        if (indx == arr.length) {
            return -1;
        }
        // check yourself
        if (arr[indx] == data) {
            return indx;
        }

        return first(arr, indx + 1, data);
    }

    // top down approach - help in last occurence
    public static int last(int[] arr, int indx, int data) {
        if (indx == arr.length) {
            return -1;
        }
        int recRes = last(arr, indx + 1, data);
        if (recRes != -1) {
            return recRes;
        }

        if (arr[indx] == data) {
            return indx;
        }
        return -1;
    }

    // bottom up approach - helps in first occurrence
    public static boolean find1(int[] arr, int indx, int data) {
        if (indx == arr.length) {
            return false;
        }
        // check yourself
        if (arr[indx] == data) {
            return true;
        }

        // boolean recRes = find1(arr, indx + 1, data);
        // return recRes;
        return find1(arr, indx + 1, data);
    }

    // top down approach - help in last occurence
    public static boolean find2(int[] arr, int indx, int data) {
        if (indx == arr.length) {
            return false;
        }
        boolean recRes = find2(arr, indx + 1, data);
        if (recRes == true) {
            return recRes;
        }

        return arr[indx] == data;
    }

    // max in array
    public static int maxEle(int[] arr, int idx) {
        if (idx == arr.length)
            return Integer.MIN_VALUE;

        int recMax = maxEle(arr, idx + 1);
        int omax = Math.max(recMax, arr[idx]);
        return omax;
    }

    // Display Reverse
    public static void diasplayRev(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }
        diasplayRev(arr, idx + 1); // faith
        System.out.println(arr[idx]); // done by myself
    }

    // display array
    public static void diasplayArr(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }
        System.out.println(arr[idx]); // done by myself
        diasplayArr(arr, idx + 1); // faith
    }

}

import java.util.*;

public class sumOfArray {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void printSumOfArray(int[] arr1, int[] arr2) {
        int s1 = arr1.length;
        int s2 = arr2.length;

        int rsize = s1 > s2 ? s1 + 1 : s2 + 1;
        int[] res = new int[rsize];

        int i = s1 - 1;
        int j = s2 - 1;
        int carry = 0;
        for(int k = rsize - 1; k >= 0; k--) {
            int val1 = i >= 0 ? arr1[i] : 0;
            int val2 = j >= 0 ? arr2[j] : 0;

            int sum = val1 + val2 + carry;
            res[k] = sum % 10;
            carry = sum / 10;
            i--;
            j--;
        }

        // print array
        if(res[0] != 0) {
            System.out.println(res[0]);
        }
        for(i = 1; i < rsize; i++) {
            System.out.println(res[i]);
        }
    }


    public static void main(String[] args) {
        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        takeInput(arr1);

        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        takeInput(arr2);

        printSumOfArray(arr1, arr2);
    }
}

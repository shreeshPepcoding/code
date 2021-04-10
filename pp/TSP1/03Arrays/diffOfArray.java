import java.util.*;

public class diffOfArray {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void printDifffArray(int[] arr1, int[] arr2) {
        // to solve : arr2 - arr1
        int s1 = arr1.length;
        int s2 = arr2.length;
        int[] res = new int[s2];

        int i = s2 - 1;
        int j = s1 - 1;
        int carry = 0;
        for(int k = s2 - 1; k >= 0; k--) {
            int val2 = i >= 0 ? arr2[i] : 0;
            int val1 = j >= 0 ? arr1[j] : 0;

            int sub = (val2 + carry) - val1;

            if(sub < 0) {
                sub += 10;
                carry = -1;
            } else {
                // reset the carry
                carry = 0;
            }

            res[k] = sub;

            i--;
            j--;
        }

        // print res
        boolean isleading0 = true;
        for(i = 0; i < s2; i++) {
            if(res[i] == 0) {
                // zero
                if(isleading0 == true) {
                    // nothing to do
                } else {
                    // print middle 0s
                    System.out.println(res[i]);
                }
            } else {
                // non zero
                System.out.println(res[i]);
                isleading0 = false;
            }
        }

    }


    public static void main(String[] args) {
        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        takeInput(arr1);

        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        takeInput(arr2);
        printDifffArray(arr1, arr2);
    }
}

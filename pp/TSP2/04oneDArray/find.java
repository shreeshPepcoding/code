import java.util.*;

public class find {

    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void find(int[] arr, int dtf) {
        // boolean isPresent = false;
        int di = -1; // data index
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == dtf) {
                // isPresent = true;
                di = i;
                break;
            }
        }
        System.out.println(di);
        // System.out.println(isPresent);
    }

    public static int find2(int[] arr, int dtf) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == dtf) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        takeInput(arr);
        int dtf = scn.nextInt(); // data to find
        
        // expectatiopn is to print true for presence otherwise false
        find(arr, dtf);
    }    
}

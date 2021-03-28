import java.util.*;

public class recArray {

    public static void display(int[] arr, int indx) {
        if(indx == arr.length) {
            System.out.println(); // hit enter
            return;
        }

        System.out.print(arr[indx] + " ");
        display(arr, indx + 1);
    }

    public static void revDisplay(int[] arr, int indx) {
        revDisplay(arr, indx  + 1);
        System.out.println(arr[indx]);
    }
    
    public static int maxInArray(int[] arr, int indx) {
        if(indx == arr.length) return Integer.MIN_VALUE;

        int rmax = maxInArray(arr, indx + 1);
        int res = Math.max(rmax, arr[indx]);
        return res;
    }

    public static boolean find(int[] arr, int indx, int dtf) {
        if(indx == arr.length) return false;

        if(arr[indx] == dtf) 
            return true;

        boolean rres = find(arr, indx + 1, dtf);
        return rres;
    }

    public static int firstIndex(int[] arr, int indx, int data){
        if(indx == arr.length) return -1;
        
        
        if(arr[indx] == data) return indx;
        
        int rres = firstIndex(arr, indx + 1, data);
        return rres;
    }

    public static int lastIndex(int[] arr, int indx, int dtf) {
        if(indx == arr.length) return -1;

        int rres = lastIndex(arr, indx + 1, dtf);

        if(rres != -1) {
            return rres;
        }

        return arr[indx] == dtf ? indx : -1;
    }

    public static int[] allIndices(int[] arr, int indx, int dtf, int count) {
        if(indx == arr.length) {
            int[] bres = new int[count];
            return bres;
        }        

        if(arr[indx] == dtf) count++;
        
        int[] rres = allIndices(arr, indx + 1, dtf, count);

        if(arr[indx] == dtf) rres[count - 1] = indx;

        return rres;
    }

    public static void starting() {
        int[] arr = {10, 40, 30, 40, 50, 40, 40, 40, 60, 70, 80, 90};

        // display(arr, 0);
        // System.out.println("Welcome in recursion with array");

        // int rmax = maxInArray(arr, 0);

        // System.out.println(rmax);

        // boolean res = find(arr, 0, 100);
        // System.out.println(res);
        int[] res = allIndices(arr, 0, 40, 0);

        System.out.println(Arrays.toString(res));

    }

    public static void demo() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = scn.nextInt();    
    }

    public static void main(String[] args) {
        starting();
    }
}


// 9555680106
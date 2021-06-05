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

    public static int lastIndx(int[] arr, int indx, int dtf) {
        if(indx == arr.length) return -1;

        int li = lastIndx(arr, indx + 1, dtf);

        if(li == -1 && arr[indx] == dtf) {
            li = indx;
        }
        return li;
    }
    
    public static int[] allIndices(int[] arr, int data, int indx, int count) {
        if(indx == arr.length) {
            int[] bres = new int[count];
            return bres;
        }


        if(arr[indx] == data){
            count++;
        }
        int[] res = allIndices(arr, data, indx + 1, count);
        if(arr[indx] == data) {
            res[count - 1] = indx;
        }

        return res;
    }

    public static void ques() {
        int[] arr = {10, 20, 20, 40, 20, 30, 20, 60, 20, 30, 20, 90};

        int[] res = allIndices(arr, 20, 0, 0);

        System.out.println(Arrays.toString(res));

        // display(arr, 0); 
        // System.out.println(find(arr, 0, 55));
        // System.out.println(lastIndx(arr, 0, 30));
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
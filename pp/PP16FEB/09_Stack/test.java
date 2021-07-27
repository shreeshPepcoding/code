import java.io.*;
import java.util.*;

public class test {
public static void main(String[] args) throws Exception {
   
    // Scanner sc = new Scanner(System.in);
    // int n = sc.nextInt();
    int[] a = {73, 74, 75, 71, 69, 72, 76, 73};
    int n = a.length;
    // for(int i = 0; i < n; i++){

    //    a[i] = sc.nextInt();
    // }

    int[] nge = solve(a);
    for(int i = 0 ; i < a.length; i++){
        nge[i] = nge[i] - i;
    //  System.out.print(nge[i] +" ");
    }
    // nge = new int[0];
    System.out.print("[");
    for(int i = 0; i < nge.length - 1; i++) {
        System.out.print(nge[i] + ",");
    }
    if(nge.length > 0) {
        System.out.print(nge[nge.length - 1]);
    }
    System.out.println("]");
    
 }

 public static int[] solve(int[] arr){
     Stack<Integer> st = new Stack<>();
     st.push(0); 
     int[] res = new int[arr.length];
     for(int i = 1 ; i < arr.length ; i++){
         while(st.size() > 0 && arr[st.peek()] < arr[i]){
             res[st.pop()] = i; 
        }
        st.push(i);
     }
     while(st.size() > 0){
         int idx = st.peek();
         res[st.pop()] = idx;
     }
   return res;
 }

}











// import java.util.*;

// public class test {

//     public static void display(int[][] arr) {
//         // write your code here
//         int r = arr.length;
//         int c = arr[0].length;

//         for(int i = 0; i < r; i++) {
//             for(int j = 0; j < c; j++) {
//                 System.out.print(arr[i][j] + " ");
//             }
//             System.out.println();
//         }
//     }

//     public static void main(String[] args) {
//         Scanner scn = new Scanner(System.in);
//         int r = scn.nextInt();
//         int c = scn.nextInt();

//         int[][] arr = new int[r][c];

//         for(int i = 0; i < r; i++) {
//             for(int j = 0; j < c; j++) {
//                 arr[i][j] = scn.nextInt();
//             }
//         }


//         display(arr);
//     }
// }

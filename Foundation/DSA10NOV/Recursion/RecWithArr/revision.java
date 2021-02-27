import java.util.*;

public class revision {

    public static Scanner scn = new Scanner(System.in);

    public static void pd(int n) {
        // base case
        if (n == 0) {
            return;
        }

        System.out.println(n);

        pd(n - 1);
    }

    // print decreasing increasing
    public static void pdi(int n) {
        // base case
        if (n == 0) {
            // System.out.println("You are in BASE CASE");
            return;
        }

        System.out.println("pre: " + n);
        pdi(n - 1);
        System.out.println("post: " + n);
    }

    // print zigzag
    public static void pzz(int n) {
        if (n == 0) {
            return;
        }

        System.out.println("pre : " + n);
        pzz(n - 1);
        System.out.println("in : " + n);
        pzz(n - 1);
        System.out.println("post : " + n);
    }

    // tower of hanoi n, source destination helper
    public static void toh(int n, int src, int dst, int hlp) {
        if (n == 0)
            return;

        toh(n - 1, src, hlp, dst);
        System.out.println("Move disk " + n + " from rod " + src + " to rod " + dst);
        toh(n - 1, hlp, dst, src);
    }

    public static void main(String[] args) {
        // expectatiion
        // int n = scn.nextInt();
        // pd(n); // => n = 5, 5 4 3 2 1
        int n = 3;
        // pdi(n);
        // pzz(n);
        toh(n, 1, 3, 2);
    }
}
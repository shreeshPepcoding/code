import java.util.*;

public class recursion {

    // expectation pd(5) -> 5, 4, 3, 2, 1
    public static void pd(int n) {
        // base case -> from low level analysis
        if(n == 0) {
            return;
        }

        // done by myself
        System.out.print(n + " ");
        // faith from recursion
        pd(n -1);
    }

    // expectation pi(5) -> 1 2 3 4 5
    public static void pi(int n) {
        if(n == 0) return;
        // faith
        pi(n - 1);
        // done by myself
        System.out.println(n);
    }

    // expectation pdi(5) -> 5 4 3 2 1 1 2 3 4 5
    public static void pdi(int n) {
        if(n == 0) 
            return;

        // done by myself
        System.out.println(n);
        // faith
        pdi(n - 1);
        // done by myself
        System.out.println(n);
    }

    // expectation pid(5) -> 1 2 3 4 5 5 4 3 2 1
    public static void pid(int i, int n) {
        if(i > n) {
            return;
        }

        System.out.println(i);
        pid(i + 1, n);
        System.out.println(i);
    }

    // expectation fact(5) -> 5 x 4 x 3 x 2 x 1
    public static int fact(int n) {
        if(n == 0) {
            return 1;
        }
        // faith of factorial i.e. fact(n - 1);
        int fnm1 = fact(n - 1);
        int fn = n * fnm1;
        return fn;
    }

    // expectation power(2, 5) -> 2 * 2 * 2 * 2 * 2
    public static int power(int x, int n) {
        if(n == 0) {
            return 1;
        }
        
        // faith
        int pxnm1 = power(x, n -1 );
        int pxn = pxnm1 *x;
        
        return pxn;
    }

    public static void demo() {
        int n = 5;
        // pd(n);
        // pi(n);

        // pdi(n);
        // pid(1, n);

        System.out.println(n + "! = " + fact(n));
    }

    public static void main(String[] args) {
        demo();
    }
}
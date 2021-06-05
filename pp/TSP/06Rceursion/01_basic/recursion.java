import java.util.*;

public class recursion {

    public static void printDecreasing(int n) {
        // base case
        if(n == 0) {
            System.out.println();
            return;
        }
    
        // done by myself
        System.out.print(n + " ");
        // faith
        printDecreasing(n - 1);
    }

    public static void printIncreasing(int n) {
        // base case
        if(n == 0) {
            return;
        }

        // faith
        printIncreasing(n - 1);
        // my work
        System.out.println(n);
    }

    public static void pdi(int n) {
        if(n == 0) {
            return;
        }
        // done by myself
        System.out.println(n);
        // faith on recursion
        pdi(n - 1);
        // done by myself
        System.out.println(n);
    }

    public static void pid(int n, int i) {
        // base case
        if(n == i) {
            System.out.println(n);
            return;
        }

        // my work
        System.out.println(i);
        // faith
        pid(n, i + 1);
        // my work
        System.out.println(i);
    }

    public static int factorial(int n) {
        // base case
        if(n == 0) {
            return 1;
        }
        // faith
        // int fnm1 = factorial(n - 1); // (n - 1)!
        // merging
        // int fn = n * fnm1; // n * (n - 1)!
        // return fn; // n!

        return n * factorial(n - 1);
    }

    public static int power(int x, int n) {
        // base case
        if(n == 0) {
            return 1;
        }

        int xnm1 = power(x, n - 1); // faith -> done by recursion
        int xn = xnm1 * x;
        return xn;
    }

    public static int powerFakeBtr(int x, int n) {
        if(n == 0) return 1;

        int xn = powerFakeBtr(x, n / 2) * powerFakeBtr(x, n / 2);

        if(n % 2 != 0) {
            xn *= x;
        }
        return xn;
    }

    public static int powerBtr(int x, int n) {
        if(n == 0) return 1;

        int halfPower = powerBtr(x, n / 2);
        int xn = halfPower * halfPower;
        if(n % 2 != 0) {
            xn *= x;
        }
        return xn;
    }

    public static void pzz(int n) {
        // base case
        if(n == 0) 
            return;

        // pre area
        System.out.println("Pre : " + n);
        pzz(n - 1);
        // In area
        System.out.println("In : " + n);
        pzz(n - 1);
        // post area
        System.out.println("Post : " + n);
    }

    public static int count = 1;
    
    public static void toh(int n, char src, char dst, char hlp) {
        if(n == 0) return;
        // faith
        toh(n - 1, src, hlp, dst);
        // my work
        System.out.println(count + ". move " + n + "th disc from " + src + " to " + dst);
        // faith
        count++;
        toh(n - 1, hlp, dst, src);
    }

    public static void ques() {
        int n = 4;
        toh(n, 'A', 'B', 'C');
        // pzz(n);

        // expectation
        // int res = factorial(n);
        // System.out.println(n + "! = " + res);
        // pid(n, 1);
        // pdi(n);  
        // printIncreasing(n);
        // printDecreasing(n);
        // System.out.println("Hello World");
    }

    public static void main(String[] args) {
        ques();
    }
}
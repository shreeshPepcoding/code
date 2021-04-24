import java.util.*;

public class factorial {

    public static int fact(int n) {
        // base case
        if(n == 0) {
            return 1;
        }
        // 2. faith
        // int fnm1 = fact(n - 1); // fnm1 => factorial of n - 1
        // int fn = fnm1 * n;  // fn => factorial of n

        // return fn;

        return n * fact(n - 1);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        // 1. expectations
        int res = fact(n);
        
        System.out.println(res);
        // System.out.println(n + "! = " + res);
    }
}
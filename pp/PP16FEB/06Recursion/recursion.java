import java.util.*;

public class recursion {

    public static int powerLogarithmic(int x, int n) {
        if(n == 0) return 1;

        int halfPower = powerLogarithmic(x, n / 2);

        int power = halfPower * halfPower;
        if(n % 2 != 0) {
            // odd power
            power *= x;
        }
        return power;
    }

    public static int powerLogarithmicFakeBetter(int x, int n) {
        if(n == 0) return 1;

        // int power = powerLogarithmicFakeBetter(x, n / 2) * powerLogarithmicFakeBetter(x, n / 2);

        int h1 = powerLogarithmicFakeBetter(x, n / 2);
        int h2 = powerLogarithmicFakeBetter(x, n / 2);
        int power = h1 * h2;

        if(n % 2 != 0) {
            // odd power
            power *= x;
        }
        return power;
    }

    public static void printZigZag(int n) {
        if(n == 0) return;

        System.out.println("Pre Area : " + n);
        printZigZag(n - 1);
        System.out.println("In Area : " + n);
        printZigZag(n - 1);
        System.out.println("Post Area : " + n);
    }

    public static void toh(int n, int src, int dst, int hlp) {
        if(n == 0) return;

        // faith1 -> no. of disc, source tower, destination tower, helper tower
        toh(n - 1, src, hlp, dst);
        System.out.println("Move disc-" + n + " from tower-" + src + " to tower-" + dst);
        // faith2 -> no. of disc, source tower, destination tower, helper tower
        toh(n -1, hlp, dst, src);
    }

    public static void basic() {
        // int res = powerLogarithmic(2, 22);
        // System.out.println(res);
        // int n = 3;
        // printZigZag(n);

        toh(3, 1, 2, 3);
    }

    public static void main(String[] args) {
        basic();
    }
} 
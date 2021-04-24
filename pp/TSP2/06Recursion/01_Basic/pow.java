import java.util.*;

public class pow {

    public static int power(int x, int n) {
        if(n == 0) 
            return 1;
        
            // faith
        int xnm1 = power(x, n - 1); // xnm1 = x ^ (n - 1)
        int xn = xnm1 * x;

        return xn;
    }
    
    public static int powerFakeBtr(int x, int n) {
        if(n == 0) return 1;

        int firstHalf = powerFakeBtr(x, n / 2);
        int secondHalf = powerFakeBtr(x, n / 2);

        int xn = firstHalf * secondHalf;

        return n % 2 == 0 ? xn : xn * x;
    } 


    public static int powerBtr(int x, int n) {
        int halfPower = powerBtr(x, n / 2);
        int xn = halfPower * halfPower;

        return n % 2 == 0 ? xn : xn * x;
    }

    public static void main(String[] args) {
        int x = 2;
        int n = 5;

        int res = power(x, n);
        System.out.println(res);
    }
}

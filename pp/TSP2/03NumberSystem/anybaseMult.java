import java.util.*;

public class anybaseMult {

    public static int anybaseAddition(int num1, int num2, int base) {
        int res = 0;

        int carry = 0;
        int power = 1; // 10 ^ 0
        while(num1 != 0 || num2 != 0 || carry != 0) {
            int val1 = num1 % 10;
            num1 /= 10;
            int val2 = num2 % 10;
            num2 /= 10;
            int sum = val1 + val2 + carry;

            int val = sum % base;
            carry = sum / base;
            res += val * power;
            power *= 10;
        }
        return res;
    }

    public static int anybaseMultiplication(int num1, int num2, int base) {

        int ans = 0;
        int powerOuter = 1; // 10 ^ 0
        while(num2 != 0) {
            int ival = num2 % 10;
            // make a copy from original num1 so that original will prevent
            int num = num1;
            int carry = 0;
            int res = 0;
            int powerInner = 1; // 10 ^ 0
            while(num != 0 || carry != 0) {
                int jval = num % 10;
                int mult = ival * jval + carry;

                int val = mult % base;
                carry = mult / base;

                // make res every time
                res += val * powerInner;
                powerInner *= 10;

                num /= 10;
            }
            res *= powerOuter;
            ans = anybaseAddition(ans, res, base); // ans = ans + res;
            powerOuter *= 10;
            num2 /= 10;
        }
        return ans;
    }

    public static void solve() {
        int n1 = 739;
        int n2 = 25;
        int base = 10;

        int res = anybaseMultiplication(n1, n2, base);
        System.out.println(n1 + " + " + n2 + " with base : " + base + " is " + res);
    }

    public static void main(String[] args) {
        solve();
    }

}
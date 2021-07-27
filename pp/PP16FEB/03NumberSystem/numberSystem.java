import java.util.*;

public class numberSystem {

    public static int decimalToBinary(int n) {
        // write your code
        int res = 0;
        int power = 1; // 10 ^ 0;
        while(n != 0) {
            int rem = n % 2;
            n = n / 2;

            res += rem * power;
            power *= 10; // power = power * 10;
        }

        return res;
    }

    public static int decimalToAnyBase(int n, int b) {
        // write your code
        int res = 0;
        int power = 1; // 10 ^ 0;
        while(n != 0) {
            int rem = n % b;
            n = n / b;

            res += rem * power;
            power *= 10; // power = power * 10;
        }

        return res;
    }

    public static int binaryToDecimal(int n) {
        int res = 0;
        int power = 1; // 2 ^ 0;

        while(n != 0) {
            int ld = n % 10;
            n = n / 10;

            res += ld * power;

            power *= 2;
        }
        return res;
    }

    public static int anybaseToDecimal(int n, int b) {
        int res = 0;
        int power = 1; // base ^ 0;

        while(n != 0) {
            int ld = n % 10;
            n = n / 10;

            res += ld * power;

            power *= b;
        }
        return res;
    }

    public static int anybaseAddition(int n1, int n2, int b) {
        int res = 0;
        int pow = 1; // 10 ^ 0;
        int carry = 0;

        while(n1 != 0 || n2 != 0 || carry != 0) {
            int val1 = n1 % 10;
            n1 = n1 / 10;

            int val2 = n2 % 10;
            n2 = n2 / 10;

            int sum = val1 + val2 + carry;
            int val = sum % b;
            carry = sum / b;

            res += val * pow;
            pow *= 10;            
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        // int res = decimalToBinary(n);
        // int res = decimalToAnyBase(n, 8);

        // int res = binaryToDecimal(n);
        int res = anybaseToDecimal(n, 2);
        System.out.println(res);

        // System.out.println(Integer.toBinaryString(n));
    }
}

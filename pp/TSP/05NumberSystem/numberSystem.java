import java.util.*;

public class numberSystem {
    
    public static int binaryToDecimal(int num) {
        int power2 = 1; // 2 ^ 0
        int res = 0;
        
        while(num != 0) {
            int digit = num % 10;
            num /= 10;

            res += digit * power2;
            power2 *= 2;
        }

        return res;
    }

    public static int AnybaseToDecimal(int num, int b) {
        int power = 1; // base ^ 0
        int res = 0;
        
        while(num != 0) {
            int digit = num % 10;
            num /= 10;
            res += digit * power;
            power *= b;
        }

        return res;
    }

    public static int DecimalToAnybase(int num, int b) {
        int res = 0;
        int power = 1; // 10 ^ 0

        while(num != 0) {
            int rem = num % b;
            num /= b;
            res += rem * power;
            power *= 10;
        }

        return res;
    }

    public static int AnybaseToAnybase(int num, int sb, int db) {
        // sb => source base
        // db => destination base

        int deciNum = AnybaseToDecimal(num, sb);
        int res = DecimalToAnybase(deciNum, db);
        return res;
    }
    
    public static int anybaseAddition(int num1, int num2, int b) {
        int carry = 0;
        int power = 1; // 10 ^ 0;
        int res = 0;
        while(num1 != 0 || num2 != 0 || carry != 0) {
            int val1 = num1 % 10;
            num1 /= 10;
            int val2 = num2 % 10;
            num2 /= 10;
            int sum = val1 + val2 + carry;
            int val = sum % b;
            carry = sum / b;

            res += val * power;
            power *= 10;
        }
        return res;
    }

    public static int anybaseSubtraction(int n1, int n2, int b) {
        // solve n2 - n1
        int carry = 0;
        int res = 0;
        int power = 1; // 10 ^ 0

        while(n2 != 0) {
            int val2 = n2 % 10;
            n2 /= 10;
            int val1 = n1 % 10;
            n1 /= 10;

            int val = (val2 + carry) - val1;
            if(val < 0) {
                val += b;
                carry = -1;
            } else {
                // reset carry
                carry = 0;
            }

            res += val * power;
            power *= 10;
        }

        return res;
    }

    public static int anybaseMultiplication(int n1, int n2, int b) {
        int res = 0;
        int opower = 1; // outer power to calculate overall res
        while(n1 != 0) {
            int ival = n1 % 10;
            n1 /= 10;

            int n22 = n2; // copy of n2, so that we can overcome loss of n2
            int carry = 0;

            int pi = 0; // roduct on every level of multiplication
            int ipower = 1; // 10 ^ 0, inner power to make product result of every level
            while(n22 != 0 || carry != 0) {
                int jval = n22 % 10;
                n22 /= 10;

                int mult = ival * jval + carry;
                int val = mult % b;
                carry = mult / b;

                pi += val * ipower;
                ipower *= 10;
            }
            pi *= opower;
            opower *= 10;
            res = anybaseAddition(res, pi, b); // res = (res + pi) with base b;
        }
        return res;
    }

    public static void ques() {
        int num = 110101;
        // int dec = binaryToDecimal(num);
        // int dec = AnybaseToDecimal(num, 2); 
        // System.out.println(num + " : " + dec);
    }

    public static void main(String[] args) {
        ques();
    }
}
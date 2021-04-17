import java.util.*;

public class subset {

    public static int DecimalToBinary(int num) {
        int res = 0;
        int power = 1; // 10 ^ 0

        while(num != 0) {
            int rem = num % 2;
            num /= 2;
            res += rem * power;
            power *= 10;
        }

        return res;
    }
    
    public static void printSubset(int[] arr) {
        int size = arr.length;
        int len = (int)Math.pow(2, size);

        for(int i = 0; i < len; i++) {
            int binary = DecimalToBinary(i);
            
            String subset = "";
            for(int j = size - 1; j >= 0; j--) {
                int digit = binary % 10;
                binary /= 10;

                if(digit == 0) {
                    subset = "-\t" + subset;
                } else {
                    subset = arr[j] + "\t" + subset;
                }
            }
            System.out.println(subset);
            // String str = Integer.toBinaryString(i);
            // System.out.println(str);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        printSubset(arr);    
    }
}

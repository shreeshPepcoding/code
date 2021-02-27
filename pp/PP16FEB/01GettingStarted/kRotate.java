import java.util.*;

public class kRotate {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int k = scn.nextInt();

        // generate count digit
        int cd = 0;
        int n = num;
        while(n != 0) {
            cd++;
            n /= 10;
        }

        // make a valid k
        k = k % cd;
        if(k < 0) { 
            k = k + cd;
        }
        // solve for algo

        int pow1 = (int)Math.pow(10, k);
        int pow2 = (int)Math.pow(10, cd - k);

        int sec_half = num % pow1;
        int f_half = num / pow1;
        sec_half *= pow2;
        int res = f_half + sec_half;

        System.out.println(res);
    }
}

import java.util.*;

public class factorial {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int n = scn.nextInt();
        int res = fact(n);
        System.out.println(res);
    }

    public static int fact(int n) {
        if (n == 0) {
            return 1;
        }
        int factnm1 = fact(n - 1);
        int factn = n * factnm1;
        return factn;
    }
}

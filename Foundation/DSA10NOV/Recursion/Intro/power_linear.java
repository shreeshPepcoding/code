import java.util.*;

public class power_linear {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int x = scn.nextInt();
        int n = scn.nextInt();
        int res = power(x, n);
        System.out.println(res);
    }

    public static int power(int x, int n) {
        if (n == 0)
            return 1;
        int pnm1 = power(x, n - 1);
        int pn = x * pnm1;
        return pn;
    }
}

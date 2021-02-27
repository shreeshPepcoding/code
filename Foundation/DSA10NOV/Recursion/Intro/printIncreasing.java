import java.util.*;

public class printIncreasing {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        pi(n);
    }

    public static void pi(int n) {
        if (n == 0)
            return;
        pi(n - 1);
        System.out.println(n);
    }
}

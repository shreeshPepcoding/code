import java.util.*;

public class pattern13 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        for(int i = 0; i < n; i++) {
            int val = 1;
            for(int j = 0; j <= i; j++) {
                System.out.print(val + "\t");
                // int factor = (i - j) / (j + 1);
                // val = val * factor;
                val = val * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }
}

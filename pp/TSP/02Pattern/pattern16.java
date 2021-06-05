import java.util.*;

public class pattern16 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int star = 1;
        int space = 2 * n - 3;

        for(int r =0; r < n; r++) {
            int val = 1;

            for(int st = 0; st < star; st++) {
                System.out.print(val + "\t");
                val++;
            }

            for(int sp = 0; sp < space; sp++) {
                System.out.print("\t");
            }

            if(r == n - 1) {
                star--;
                val--;
            }
            for(int st = 0; st < star; st++) {
                val--;
                System.out.print(val + "\t");
            }

            // hit enter
            System.out.println();

            star++;
            space -= 2;
        }

    }    
}

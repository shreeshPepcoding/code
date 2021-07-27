import java.util.*;

public class pattern17 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int star = 1;
        int space = n / 2;

        for(int r = 0; r < n; r++) {
            // print space
            for(int sp = 0; sp < space; sp++) {
                if(r == n / 2) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }

            // print star
            for(int st = 0; st < star; st++) {
                System.out.print("*\t");
            }

            // hit enter
            System.out.println();
            
            // count manage
            if(r < n / 2) {
                star++;
            } else {
                star--;
            }
        }
    }
}

import java.util.*;

public class pattern6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // initilisation
        int star = n / 2 + 1;
        int space = 1;
        for(int r = 0; r < n; r++) {
            // print star
            for(int st = 0; st < star; st++) {
                System.out.print("*\t");
            }
            // print space
            for(int sp = 0; sp < space; sp++) {
                System.out.print("\t");
            }
            // print star
            for(int st = 0; st < star; st++) {
                System.out.print("*\t");
            }
            // hit enter
            System.out.println();
            // manage count
            if(r < n / 2) {
                space += 2;
                star--;
            } else {
                space -= 2;
                star++;
            }
        }
    }

}

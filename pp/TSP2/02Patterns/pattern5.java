import java.util.*;

public class pattern5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // initilisation
        int star = 1;
        int space = n / 2;
        for(int r = 0; r < n; r++) {
            // System.out.println("Space : " + space + " Star : " + star);

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
                star += 2;
                space--;
            } else {
                star -= 2;
                space++;
            }
        }
    }    
}

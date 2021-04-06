import java.util.*;

public class pattern1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        

        // initilisation
        int star = 1;
        // loop for row
        for(int r = 1; r <= n; r++) {
            // System.out.println(star);

            // print star
            for(int st = 1; st <= star; st++) {
                System.out.print("*\t");
            }
            // hit enter
            System.out.println();
            // manage count
            star++;
        }
    }
}
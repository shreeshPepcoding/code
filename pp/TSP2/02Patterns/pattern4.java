import java.util.*;

public class pattern4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // initilisation
        int star = n;
        int space = 0;
        for(int r = 1; r <= n; r++) {
            // 1. print space
            for(int sp = 1; sp <= space; sp++) {
                System.out.print("\t");
            }
            // 2. print star
            for(int st = 1; st <= star; st++) {
                System.out.print("*\t");
            }
            // 3. enter hit
            System.out.println();
            // 4. manage count
            star--;
            space++;
        }
    }    
}

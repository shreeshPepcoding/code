import java.util.*;

public class pattern2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // initilisation
        int star = n;

        for(int r = 1; r <= n; r++) {
            // 1. print star
            for(int st = 1; st <= star; st++) {
                System.out.print("*\t");
            }
            // 2. hit enter
            System.out.println();
            // 3. manage count
            star--;
        }
    }    
}

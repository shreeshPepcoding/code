import java.util.*;

public class pattern6 {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int n = scn.nextInt();

        // initialise
        int star = n / 2 + 1;
        int space = 1;

        for(int r = 0; r < n; r++) {
            // 1. print star
            for(int st = 0; st < star; st++) {
                System.out.print("*\t");
            }
            // 2. print space
            for(int sp = 0; sp < space; sp++) {
                System.out.print("\t");
            }
            // 3. print star
            for(int st = 0; st < star; st++) {
                System.out.print("*\t");
            }
            // 4. hit enter
            System.out.println();
            // 5. count management
            if(r < n / 2) {
                star--;
                space += 2;
            } else {
                star++;
                space -= 2;
            }
        }
    }
}
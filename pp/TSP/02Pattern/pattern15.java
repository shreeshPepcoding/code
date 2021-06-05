import java.util.*;

public class pattern15 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int star = 1;
        int space = n / 2;
        
        int var = 1;
        for(int r = 0; r < n; r++) {
            for(int sp = 0; sp < space; sp++) {
                System.out.print("\t");
            }
            
            int var2 = var;
            for(int st = 0; st < star; st++) {
                System.out.print(var2 + "\t");
                if(st < star / 2) {
                    var2++;
                } else {
                    var2--;
                }
            }

            System.out.println();

            if(r < n / 2) {
                var++;
                star += 2;
                space--;
            } else {
                var--;
                star -= 2;
                space++;
            }
        }
    }
}

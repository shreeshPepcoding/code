import java.util.*;

public class pattern11 {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int n = scn.nextInt();
        int var = 1;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c <= r; c++) {
                System.out.print(var + "\t");
                // increment in var
                var++;
            }
            // hit enter
            System.out.println();
        }
    }
}
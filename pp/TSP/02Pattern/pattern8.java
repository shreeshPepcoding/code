import java.util.*;

public class pattern8 {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int n = scn.nextInt();

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(r + c >= n - 1) {
                    // print star
                    System.out.print("*\t");
                    // break;
                } else {
                    // print space
                    System.out.print("\t");
                }
            }
            // hit enter
            System.out.println();
        }
    }
}
import java.util.*;

public class pattern7 {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int n = scn.nextInt();

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(r <= c) {
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
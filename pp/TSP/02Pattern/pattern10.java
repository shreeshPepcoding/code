import java.util.*;

public class pattern10 {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int n = scn.nextInt();
        int row = n;
        n--;
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < row; c++) {
                if(r + c == n / 2 || c - r == n / 2 || r - c == n / 2 || r + c == 3 * n / 2) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }
            // hit enter
            System.out.println();
        }
    }
}
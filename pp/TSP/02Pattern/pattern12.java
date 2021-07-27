import java.util.*;

public class pattern12 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int a = 0;
        int b = 1;


        // fibonaci 
        // for(int i = 0; i < n; i++) {
        //     System.out.println(a);
        //     int c = a + b;
        //     a = b;
        //     b = c;
        // }

        for(int r = 0; r < n; r++) {
            for(int c = 0; c <= r; c++) {
                System.out.print(a + "\t");
                int sum = a + b;
                a = b;
                b = sum;
            }
            System.out.println();
        }
    }    
}

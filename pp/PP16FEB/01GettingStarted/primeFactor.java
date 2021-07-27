import java.util.*;

public class primeFactor {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int div = 2;
        while(n != 1) {
            if(n % div == 0) {
                System.out.print(div + " ");
                n /= div;
            } else {
                div++;
            }
        }
        System.out.println();
    }
}

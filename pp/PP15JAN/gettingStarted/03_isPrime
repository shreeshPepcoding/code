import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();

        for (int i = 1; i <= t; i++) {

            int num = scn.nextInt();
            // logic for check of prime => num
            boolean isPrime = true;

            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    // number is completely divide by j
                    isPrime = false;
                    break;
                }
            }

            if (isPrime == true) {
                System.out.println("prime");
            } else {
                System.out.println("not prime");
            }
        }

    }
}

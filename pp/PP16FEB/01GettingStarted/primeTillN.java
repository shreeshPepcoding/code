import java.util.*;

public class primeTillN {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int lo = scn.nextInt();
        int hi = scn.nextInt();

        for(int num = lo; num <= hi; num++) {
            // put logic for prime number on num

            boolean isPrime = true;
            for(int j = 2; j * j <= num; j++) {
                if(num % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if(isPrime == true) {
                System.out.println(num);
            }
        }
    }
}
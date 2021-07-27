import java.util.*;

public class isPrime {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);


        int t = scn.nextInt(); // no. of test cases

        for(int i = 0; i < t; i++) {
            int num = scn.nextInt(); // i will get t different numbers

            // find if num is prime or not

            // method 1
            // boolean isprime = true;
            // for(int j = 2; j < num; j++) {
            //     if(num % j == 0) {
            //         // num is definietly not prime
            //         isprime = false;
            //         break;
            //     }
            // }

            // if(isprime == true) {
            //     System.out.println("prime");
            // } else {
            //     System.out.println("not prime");
            // }


            // method 2
            // boolean isprime = true;
            // for(int j = 2; j <= num / 2; j++) {
            //     if(num % j == 0) {
            //         isprime = false;
            //         break;
            //     }
            // }
            // if(isprime == true) {
            //     System.out.println("prime");
            // } else {
            //     System.out.println("not prime");
            // }


            // method 3
            boolean isprime = true;
            for(int j = 2; j * j <= num; j++) {
                if(num % j == 0) {
                    isprime = false;
                    break;
                }
            }

            if(isprime == true) {
                System.out.println("prime");
            } else {
                System.out.println("not prime");
            }
        }
    }
}
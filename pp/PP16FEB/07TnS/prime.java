import java.util.*;

public class prime {

    public static boolean isPrime(int n) {
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // range of queries[i] is from 2 to 100, length of queries, 10^4
    public static void printPrime(int[] queries) {
        for(int q = 0; q < queries.length; q++) {
            if(isPrime(queries[q]) == true) {
                System.out.println(queries[q] + " is Prime");
            } else {
                System.out.println(queries[q] + " is Not Prime");
            }
        }
    }

    // sieve
    public static void sieve(int[] queries) {
        int range = 20;
        boolean[] primes = new boolean[range + 1];

        // make every element as true
        Arrays.fill(primes, true);

        // primes[i] == true, i is prime
        // primes[i] == false i is not prime

        for(int i = 2; i * i <= range; i++) {
            if(primes[i] == false) continue;

            for(int j = i + i; j <= range; j += i) {
                primes[j] = false;
            }
        }

        for(int q = 0; q < queries.length; q++) {
            int num = queries[q];
            if(primes[num] == true) {
                System.out.println(num + " is Prime");
            } else {
                System.out.println(num + " is Not Prime");
            }
        }
    }

    public static void main(String[] args) {
        int[] queries = {2, 7, 19, 15, 19, 3, 9, 4, 17, 20, 2, 18, 20, 19, 16, 2, 11, 7, 13, 7, 11, 16, 15, 13, 13};
        printPrime(queries);
        // sieve(queries);
    }
}
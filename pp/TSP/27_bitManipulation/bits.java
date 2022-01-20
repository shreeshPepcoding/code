import java.util.*;

public class bits {

    // basic of bits
    private static void basicBit(int n, int i, int j, int k, int m) {
        // 1. on ith bit
        int bm1 = (1 << i);
        System.out.println(n | bm1);
        // 2. off jth bit
        int bm2 = (1 << j);
        bm2 = (~bm2); // 1's compliment
        System.out.println(n & bm2);
        // 3. toggle kth bit
        int bm3 = (1 << k);
        System.out.println(n ^ bm3);
        // 4. check if mth bit is on or off
        int bm4 = (i << m);
        System.out.println((bm4 & n) == bm4);
    }

    // print rightmost set bit mask
    private static void printRSBM(int n) {
        int rsbm = (n & (-n));
        System.out.println(Integer.toBinaryString(rsbm));
    }

    // kernighan's algorithm
    private static int countSetBit(int n) {
        int count = 0;
        while(n != 0) {
            int rsbm = (n & (-n));
            n = n - rsbm;
            count++;
        }
        return count;
    }

    // josephus from bit mathematics
    public static int josephusBit(int n){
        // find 2^x
        int i = 1;
        while(i * 2 <= n) {
            i = i * 2;
        }

        // we know that n = 2^x + l, l = n - 2^x
        int l = n - i; // i is 2^ x
        // result is 2 * l + 1
        return 2 * l + 1;
    }

    // gray code
    public static List<Integer> grayCode(int n) {
        if(n <= 1) {
            List<Integer> bres = new ArrayList<>();
            bres.add(0);
            if(n == 1)
                bres.add(1);
            return bres;
        }

        List<Integer> rres = grayCode(n - 1);
        List<Integer> mres = new ArrayList<>();
        // add normal sequence of rres in mres
        for(int val : rres) 
            mres.add(val);

        // on (n - 1)th bit and add it in mres (make loop iteration in reverse order)
        int bm = (1 << (n - 1));
        for(int i = rres.size() - 1; i >= 0; i--) {
            int val = rres.get(i);
            mres.add(val | bm);
        }
        return mres;
    }


    public static void main(String[] args) {
        System.out.println();
    }
}

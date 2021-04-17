import java.util.*;

public class towerOfHanoi {

    public static void toh(int n, char src, char dst, char hlp) {
        if(n == 0) return;

        toh(n - 1, src, hlp, dst);
        System.out.println("move " + n + "th disk from " + src + " to " + dst);
        toh(n - 1, hlp, dst, src);
    }
    
    public static void main(String[] args) {
        int n = 5;
        toh(n, 'A', 'B', 'C');   
    }
}

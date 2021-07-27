import java.util.*;

public class funDemo {

    public static void sayHi() {
        System.out.println("Welcome in introduction of Functions");
        System.out.println("Hi! to everyone");
    }

    public static int maxOfThree(int a, int b, int c) {
        int ans = 0;
        if(a > b && a > c) {
            ans = a;
        } else if(b > c) {
            ans = b;
        } else {
            ans = c;
        }

        return ans;
    }

    public static int countDigits(int num) {

    }

    java,.  ut 

    public static void main(String[] args) {
        sayHi();
        sayHi();

        int res = maxOfThree(10, 35, 200);
        System.out.println("max is : " + res);
        int d1 = countDigits(123454); // 6
        int d2 = countDigits(1054); // 4
        int d3 = countDigits(134); // 3

        System.out.println(d1 + " " + d2 + " " + d3);
    }
}
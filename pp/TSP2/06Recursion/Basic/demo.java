import java.util.*;

public class demo {

    public static void fun(int n) {
        System.out.println(n);
        fun(n + 1);
    }

    public static void main(String[] args) {
        int n = 1;
        fun(n);
    }
}

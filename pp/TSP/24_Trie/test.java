import java.util.*;

public class test {

    private static void fun(int[] val) {
        System.out.println("In fun : " + val[0]);
        val[0] = 15;
        System.out.println("In fun : " + val[0]);
    }

    static int val;
    public static void main(String[] args) {
        int[] val = {10};
        System.out.println("In main : " + val[0]);
        fun(val);
        System.out.println("In main : " + val[0]);
    }
}

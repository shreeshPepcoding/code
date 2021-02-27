public class test {

    public static void rec(int n) {
        if(n == 11000) return;
        System.out.println(n);
        rec(n + 1);
    }

    public static void main(String[] args) {
        rec(1);
        // write your code here.
    }
}

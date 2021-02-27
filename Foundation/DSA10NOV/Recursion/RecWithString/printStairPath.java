public class printStairPath {

    public static void printSP1(int n, String asf) {
        // Smart Base case, why? because it can manage valid stair as well as basement
        System.out.println(n);
        if (n <= 0) {
            // if (n == 0)
            //     System.out.println(asf);
            return;
        }
        printSP1(n - 1, asf + "1");
        printSP1(n - 2, asf + "2");
        printSP1(n - 3, asf + "3");
    }

    public static void printSP2(int n, String asf) {
        System.out.println(n);
        // fool base case
        if (n == 0) {
            // System.out.println(asf);
            return;
        }

        // smart calls
        if (n - 1 >= 0)
            printSP2(n - 1, asf + "1");
        if (n - 2 >= 0)
            printSP2(n - 2, asf + "2");
        if (n - 3 >= 0)
            printSP2(n - 3, asf + "3");
    }

    public static void main(String[] args) {
        int n = 3;
        printSP1(n, "");
        System.out.println("============================");
        printSP2(n, "");
    }
}
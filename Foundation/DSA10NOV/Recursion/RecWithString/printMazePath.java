import java.util.Scanner;

public class printMazePath {

    public static void printMP1(int x, int y, int dr, int dc, String psf) {
        if (x > dr || y > dc) {
            return;
        }
        if (x == dr && y == dc) {
            System.out.println(psf);
            return;
        }

        // horizonatal call
        printMP1(x, y + 1, dr, dc, psf + "h");
        // vertical calls
        printMP1(x + 1, y, dr, dc, psf + "v");
    }

    public static void printMP2(int x, int y, int dr, int dc, String psf) {
        if (x == dr && y == dc) {
            System.out.println(psf);
            return;
        }
        System.out.println(x + " " + y);
        // horizonatal call
        if (y <= dc)
            printMP2(x, y + 1, dr, dc, psf + "h");
        // vertical calls
        if (x <= dr)
            printMP2(x + 1, y, dr, dc, psf + "v");
    }

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();

        // printMP1(0, 0, n - 1, m - 1, "");
        // System.out.println("===========");
        printMP2(0, 0, n - 1, m - 1, "");
    }
}

import java.util.Scanner;

public class printMazeJumps {

    public static void printMJP(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        }

        // explore the option of horizontal
        for (int jump = 1; jump + sc <= dc; jump++) {
            printMJP(sr, sc + jump, dr, dc, psf + "h" + jump);
        }

        // explore the option of vertical
        for (int jump = 1; jump + sr <= dr; jump++) {
            printMJP(sr + jump, sc, dr, dc, psf + "v" + jump);
        }

        // explore the option of diagonal
        for (int jump = 1; jump + sr <= dr && jump + sc <= dc; jump++) {
            printMJP(sr + jump, sc + jump, dr, dc, psf + "d" + jump);
        }
    }

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();

        printMJP(0, 0, n - 1, m - 1, "");
    }
}

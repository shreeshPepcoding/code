import java.util.*;

public class recursion {

    public static void printSubseq(String str, String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }

        char ch = str.charAt(0);
        String roq = str.substring(1);

        // yes call
        printSubseq(roq, asf + ch);
        // no call
        printSubseq(roq, asf + "-");
    }

    public static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static void printKPC(String str, String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }

        int indx = str.charAt(0) - '0';
        String code = codes[indx];
        String roq = str.substring(1);

        for(int i =0; i < code.length(); i++) {
            char option = code.charAt(i);
            printKPC(roq, asf + option);
        }
    }

    public static void printStair(int n, String asf) {
        if(n == 0) {
            System.out.println(asf);
            return;
        }

        for(int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            printStair(n - jump, asf + jump);
        }
    }

    // calls -> stupid, basecase -> smart
    public static int printStair2(int n, String asf) {
        if(n == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        if(n - 1 >= 0)
            count += printStair2(n - 1, asf + "1");
        if(n - 2 >= 0)        
            count += printStair2(n - 2, asf + "2");
        if(n - 3 >= 0)
            count += printStair2(n - 3, asf + "3");

        return count;
    }

    public static void printMazePath(int sr, int sc, int dr, int dc, String asf) {
        if(sr == dr && sc == dc) {
            System.out.println(asf);
            return;
        }

        // horizontal calls
        if(sc + 1 <= dc) {
            printMazePath(sr, sc + 1, dr, dc, asf + "h");
        }
        // vertical calls
        if(sr + 1 <= dr) {
            printMazePath(sr + 1, sc, dr, dc, asf + "v");
        }
    } 

    public static void printMazePathJumps(int sr, int sc, int dr, int dc, String asf) {
        if(sr == dr && sc == dc) {
            System.out.println(asf);
            return;
        }
        
        // horizontals
        for(int jump = 1; jump + sc <= dc; jump++) {
            printMazePathJumps(sr, sc + jump, dr, dc, asf + "h" + jump);
        }
        // verticals
        for(int jump = 1; jump + sr <= dr; jump++) {
            printMazePathJumps(sr + jump, sc, dr, dc, asf + "v" + jump);
        }
        // diagonals
        for(int jump = 1; jump + sr <= dr && jump + sc <= dc; jump++) {
            printMazePathJumps(sr + jump, sc + jump, dr, dc, asf + "d" + jump);
        }
    }

    public static void ques() {
        // printMazePath(0, 0, 2, 2, "");
        printMazePathJumps(0, 0, 2, 2, "");
        // String str = "179";
        // printStair(4, "");
        // int res = printStair2(4, "");
        // System.out.println(res);
        // printKPC(str, "");
        // printSubseq(str, "");
    }

    public static void main(String[] args) {
        ques();
    }
}
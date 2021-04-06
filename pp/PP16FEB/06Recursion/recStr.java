import java.util.*;

public class recStr {

    // qsf -> question so far, asf -> answer so far
    public static void printSubseq(String qsf, String asf) {
        if(qsf.length() == 0) {
            System.out.println(asf);
            return;
        }

        char ch = qsf.charAt(0);
        String roq = qsf.substring(1); // roq -> rest of question

        // yes call
        printSubseq(roq, asf + ch);
        // no call
        printSubseq(roq, asf + "-");
    }

    public static String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static void printKPC(String qsf, String asf) {
        if(qsf.length() == 0) {
            System.out.println(asf);
            return;
        }

        int indx = qsf.charAt(0) - '0';
        String roq = qsf.substring(1);

        String code = codes[indx];

        for(int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            printKPC(roq, asf + ch);
        }
    }

    public static void printStairPath(int n, String asf) {
        if(n == 0) {
            System.out.println(asf);
            return;
        }

        for(int jump = 1; jump <= 3; jump++) {
            if(n - jump >= 0) {
                printStairPath(n - jump, asf + jump);
            }
        }
    }
    
    public static void printStairWithJumps(int n, String asf, int[] jumps) {
        if(n == 0) {
            System.out.println(asf);            
            return;
        }
                                                                                                                                                                                                                                                        
        for(int j = 1; j <= jumps[n - 1]; j++) {
            if(n - j >= 0) {
                printStairWithJumps(n - j, asf + j, jumps);
            }
        }
    }

    public static void printMazePath(int sr, int sc, int dr, int dc, String asf) {
        if(sr == dr && sc == dc) {
            System.out.println(asf);
            return;
        }

        // horizontal move -> toward right
        if(sc + 1 <= dc) {
            printMazePath(sr, sc + 1, dr, dc, asf +"h");
        }
        // vertical move -> toward down
        if(sr + 1 <= dr) {
            printMazePath(sr + 1, sc, dr, dc, asf +"v");
        }
    }

    public static void mazePathWithJumps(int sr, int sc, int dr, int dc, String asf, ArrayList<String> res) {
        if(sr == dr && sc == dc) {
            // base case
            System.out.println(asf);
            res.add(asf);
            return;
        }
        // horizontal calls
        for(int jump = 1; jump + sc <= dc; jump++) {
            mazePathWithJumps(sr, sc + jump, dr, dc, asf + "h" + jump, res);
        }
        // vertical calls
        for(int jump = 1; jump + sr <= dr; jump++) {
            mazePathWithJumps(sr + jump, sc, dr, dc, asf + "v" + jump, res);
        }
        // diagonal calls
        for(int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++) {
            mazePathWithJumps(sr + jump, sc + jump, dr, dc, asf + "d" + jump, res);
        }
    }

    public static List<String> res; // reference

    public static void mazePathWithJumpsWithOutPass(int sr, int sc, int dr, int dc, String asf) {
        if(sr == dr && sc == dc) {
            // base case
            System.out.println(asf);
            res.add(asf);
            return;
        }

        // horizontal calls
        for(int jump = 1; jump + sc <= dc; jump++) {
            mazePathWithJumpsWithOutPass(sr, sc + jump, dr, dc, asf + "h" + jump);
        }
        // vertical calls
        for(int jump = 1; jump + sr <= dr; jump++) {
            mazePathWithJumpsWithOutPass(sr + jump, sc, dr, dc, asf + "v" + jump);
        }
        // diagonal calls
        for(int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++) {
            mazePathWithJumpsWithOutPass(sr + jump, sc + jump, dr, dc, asf + "d" + jump);
        }
    }

    public static List<String> getPath(int sr, int sc, int dr, int dc) {
        // code here
        // ArrayList<String> res = new ArrayList<>();
        // mazePathWithJumps(sr, sc, dr, dc, "", res);
        // return res;

        res = new ArrayList<>();
        mazePathWithJumpsWithOutPass(sr, sc, dr, dc, "");
        return res;
    }

    public static void printPermutation(String str, String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            String first = str.substring(0, i);
            String second = str.substring(i + 1);

            printPermutation(first + second, asf + ch);
        }
    }

    public static void printEncoding(String qsf, String asf) {
        if(qsf.length() == 0) {
            System.out.println(asf);
            return;
        }

        if(qsf.charAt(0) == '0') {
            return;
        }

        int num1 = qsf.charAt(0) - '0';
        printEncoding(qsf.substring(1), asf + (char)('a' + num1 - 1));
        if(qsf.length() > 1) {
            int num2 = qsf.charAt(1) - '0';
            int num = num1 * 10 + num2;
            if(num <= 26) {
                printEncoding(qsf.substring(2), asf + (char)('a' + num - 1));
            }
        }
    }

    public static void recQues() {
        String str = "1003";
        printEncoding(str, "");
        // printPermutation(str, "");

        // mazePathWithJumps(0, 0, 2, 2, "", res);
        // System.out.println(res);

        // printMazePath(1, 1, 3, 3, "");


        // int[] jumps = {4, 0, 3, 2, 2, 4, 3};
        // printStairWithJumps(4, "", jumps);

        // printStairPath(4, "");
        // printKPC(str, "");  
        // printSubseq(str, "");

    }
    
    public static void main(String[] args) {
        recQues();
    }
}
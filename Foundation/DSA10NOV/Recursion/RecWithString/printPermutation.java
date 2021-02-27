import java.util.Scanner;

public class printPermutation {

    public static void printPerm(String qsf, String asf) {
        if(qsf.length() == 0) {
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < qsf.length(); i++) {
            char ch = qsf.charAt(i);
            String left = qsf.substring(0, i);
            String right = qsf.substring(i + 1);
            String roq = left + right;
            printPerm(roq, asf + ch);
        }
    }

    // struggling code = for strugglers
    public static void printPerm(String qsf, String asf, boolean[] vis) {
        if(qsf.length() == asf.length()) {
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < qsf.length(); i++) {
            if(vis[i] == false) {
                vis[i] = true;
                char ch = qsf.charAt(i);
                printPerm(qsf, asf + ch, vis);    
                vis[i] = false;
            }
            public static Scanner scn = new Scanner(System.in);
        }
    }

    public static void main(String[] args) {
        String qsf = scn.next();
        printPerm(qsf, "");
    }
}

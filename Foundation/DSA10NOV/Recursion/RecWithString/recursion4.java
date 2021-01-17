import java.util.*;

public class recursion4 {

    public static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static void printKPC(String qsf, String asf) {
        if (qsf.length() == 0) {
            // print answer
            System.out.println(asf);
            return;
        }

        int indx = qsf.charAt(0) - '0';
        String options = codes[indx];
        String roq = qsf.substring(1);
        // make loop on options and manage answer so far
        for (int i = 0; i < options.length(); i++) {
            printKPC(roq, asf + options.charAt(i));
        }
    }

    // qstr -> question string, asf -> answer so far
    public static void printSS(String qstr, String asf) {
        if (qstr.length() == 0) {
            System.out.println(asf);
            return;
        }
        String roq = qstr.substring(1);
        printSS(roq, asf);
        printSS(roq, asf + qstr.charAt(0));
    }

    public static void solve() {
        printKPC("", "");
        // printSS("yvTA", "");
    }

    public static void main(String[] args) {
        solve();
    }
}
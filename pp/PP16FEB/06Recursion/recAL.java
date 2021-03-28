import java.util.*;

public class recAL {

    public static ArrayList<String> gss(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);

        ArrayList<String> rres = gss(ros);
        ArrayList<String> mres = new ArrayList<>();
        // add with dash (-)
        for(String s : rres) {
            mres.add(s);
        }

        // add with character c
        for(String s : rres) {
            mres.add("" + ch + s);
        }

        return mres;
    }

    public static String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static ArrayList<String> gkpc(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        int indx = str.charAt(0) - '0';
        String code = codes[indx];
        String ros = str.substring(1);

        ArrayList<String> rres = gkpc(ros);
        ArrayList<String> mres = new ArrayList<>();

        for(int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);

            for(String s : rres) {
                mres.add(ch + s);
            }            
        }
        return mres;
    }

    public static void rec() {
        // String str = "abc";
        // ArrayList<String> res = gss(str);
        // System.out.println(res);

        String str = "723";
        ArrayList<String> res = gkpc(str);
        // System.out.println(res);
        for(String s : res)
            System.out.println(s);
    }
    
    public static void main(String[] args) {
        rec();
    }
}

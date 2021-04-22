import java.util.*;

public class recursion {
    
    public static ArrayList<String> getSubseq(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        // roq => rest of question
        String roq = str.substring(1);
        ArrayList<String> rres = getSubseq(roq);
        ArrayList<String> mres = new ArrayList<>();
        // character add with "no" option
        for(String s : rres) {
            mres.add("-" + s);
        }
        // character add with "yes" option
        for(String s : rres) {
            mres.add(ch + s);
        }

        return mres;
    }

    public static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        int indx = str.charAt(0) - '0';
        String code = codes[indx];
        String roq = str.substring(1);

        ArrayList<String> rres = getKPC(roq);

        ArrayList<String> mres = new ArrayList<>();
        for(int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);

            for(String s : rres) {
                mres.add(ch + s);
            }
        }
        return mres;
    }

    public static ArrayList<String> getStairPaths(int n) {
        if(n <= 0) {
            ArrayList<String> bres = new ArrayList<>();
            if(n == 0)
                bres.add("");
            
            return bres;
        }

        // jump allowed = 3
        ArrayList<String> rres1 = getStairPaths(n - 1);
        ArrayList<String> rres2 = getStairPaths(n - 2);
        ArrayList<String> rres3 = getStairPaths(n - 3);

        ArrayList<String> mres = new ArrayList<>();
        for(String s : rres1) {
            mres.add("1" + s);
        }

        for(String s : rres2) {
            mres.add("2" + s);
        }

        for(String s : rres3) {
            mres.add("3" + s);
        }
        return mres;
    }

    public static ArrayList<String> getStairPaths2(int n) {
        // stupid
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres; 
        }
        // smart calls
        ArrayList<String> mres = new ArrayList<>();
        // jump allowed = 3
        if(n - 1 >= 0) {
            ArrayList<String> rres1 = getStairPaths2(n - 1);
            for(String s : rres1) {
                mres.add("1" + s);
            }
        }
        if(n - 2 >= 0) {
            ArrayList<String> rres2 = getStairPaths2(n - 2);
            for(String s : rres2) {
                mres.add("2" + s);
            }
        }
        if(n - 3 >= 0) {
            ArrayList<String> rres3 = getStairPaths2(n - 3);
            for(String s : rres3) {
                mres.add("3" + s);
            }
        }
        return mres;
    }

    public static ArrayList<String> getStairPaths3(int n) {
        // stupid
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres; 
        }
        // smart calls
        ArrayList<String> mres = new ArrayList<>();
        for(int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            ArrayList<String> rres = getStairPaths3(n - jump);
            for(String s : rres) {
                mres.add(jump + s);
            }
        }
        return mres;
    }

    public static void ques() {

        System.out.println(getStairPaths3(7));

        // String str = "179";
        // ArrayList<String> res = getSubseq(str);
        // ArrayList<String> res = getKPC(str);
        // for(String s : res) 
        //     System.out.println(s);

        // System.out.println(res);
    }
    
    public static void main(String[] args) {
        ques();    
    }
}
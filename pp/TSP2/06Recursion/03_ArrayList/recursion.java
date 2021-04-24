import java.util.*;

public class recursion {

    public static ArrayList<String> getSubSeq(String str) {
        if (str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String roq = str.substring(1); // roq => rest of question

        ArrayList<String> rres = getSubSeq(roq);
        ArrayList<String> mres = new ArrayList<>();
        // add with -
        for (String s : rres) {
            mres.add(s);
        }
        // add with char
        for (String s : rres) {
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
        String roq = str.substring(1);
        String code = codes[indx];
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

    public static ArrayList<String> getStairPath(int n) {
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't move
            return bres;
        }

        ArrayList<String> mres = new ArrayList<>();
        if(n - 1 >= 0) {
            // jump for 1
            ArrayList<String> rres = getStairPath(n - 1);
            for(String s : rres) {
                mres.add("1" + s);
            }
        }

        if(n - 2 >= 0) {
            // jump for 2
            ArrayList<String> rres = getStairPath(n - 2);
            for(String s : rres) {
                mres.add("2" + s);
            }
        }

        if(n - 3 >= 0) {
            // jump for 3
            ArrayList<String> rres = getStairPath(n - 3);
            for(String s : rres) {
                mres.add("3" + s);
            }
        }
        return mres;
    }

    public static ArrayList<String> getStairPath1(int n) {
        // smart base case
        if(n <= 0) {
            ArrayList<String> bres = new ArrayList<>();
            if(n == 0)
                bres.add(""); // don't move
            
            return bres;
        }

        ArrayList<String> mres = new ArrayList<>();
        ArrayList<String> rres = null;

        // stupid calls
        rres = getStairPath1(n - 1);
        for(String s : rres) {
            mres.add("1" + s);
        }

        rres = getStairPath1(n - 2);
        for(String s : rres) {
            mres.add("2" + s);
        }

        rres = getStairPath1(n - 3);
        for(String s : rres) {
            mres.add("3" + s);
        }

        return mres;
    }

    public static ArrayList<String> getStairPath2(int n) {
        // smart base case
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't move
            
            return bres;
        }

        ArrayList<String> mres = new ArrayList<>();
        
        for(int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            ArrayList<String> rres = getStairPath2(n - jump);
            for(String s : rres) {
                mres.add(jump + s);
            }
        }

        return mres;
    }

    public static ArrayList<String> getMazePath(int sr, int sc, int dr, int dc) {
        // System.out.println(sr + " " + sc);
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        } else if(sr > dr || sc > dc) {
            ArrayList<String> bres = new ArrayList<>();
            return bres;
        }


        // stupd call
        // horizontal call
        ArrayList<String> hres = getMazePath(sr, sc + 1, dr, dc);
        // vertical call
        ArrayList<String> vres = getMazePath(sr + 1, sc, dr, dc);

        ArrayList<String> mres = new ArrayList<>();
        for(String s : hres) {
            mres.add("h" + s);
        }

        for(String s : vres) {
            mres.add("v" + s);
        }
        return mres;
    }

    public static ArrayList<String> getMazePathSmartCalls(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        
        ArrayList<String> mres = new ArrayList<>();
        // horizontal call
        if(sc + 1 <= dc) {
            ArrayList<String> hres = getMazePathSmartCalls(sr, sc + 1, dr, dc);
            for(String s : hres) {
                mres.add("h" + s);
            }
        }
        // vertical call
        if(sr + 1 <= dr) {
            ArrayList<String> vres = getMazePathSmartCalls(sr + 1, sc, dr, dc);
            for(String s : vres) {
                mres.add("v" + s);
            }
        }  
        return mres;
    }

    public static void ques() {
        String str = "abc";
        // ArrayList<String> res = getSubSeq(str);
        // ArrayList<String> res = getKPC("479");
        // ArrayList<String> res = getStairPath2(3);

        ArrayList<String> res = getMazePathSmartCalls(0, 0, 2, 2);
        System.out.println(res);
    }

    public static void ASCIIDemo() {
        // char ch = '4' - 48;
        // int n = 52 + ch;

        char ch = '4';
        // int indx = ch - 48;
        int indx = ch - '0';
        System.out.println(indx);
        // System.out.println((int)ch + " " + (char)n);
    }

    public static void main(String[] args) {
        ques();
        // ASCIIDemo();
    }
}
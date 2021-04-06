import java.lang.reflect.Array;
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

    public static ArrayList<String> getStairPaths(int n) {
        // System.out.println("I am at : " + n + "th stair");
        // base case -> stupid
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // i.e. don't move
            return bres;
        }


        ArrayList<String> mres = new ArrayList<>();

        // calls -> smart
        for(int jump = 1; jump <= 3; jump++) {
            if(n - jump >= 0) {
                ArrayList<String> rres = getStairPaths(n - jump);

                for(String path : rres) {
                    mres.add("" + jump + path);
                }
            }
        }
        return mres;
    }

    public static ArrayList<String> getStairPaths2(int n) {

        // System.out.println("I am at : " + n + "th stair");

        // smart base case
        if(n <= 0) {
            // basement
            ArrayList<String> bres = new ArrayList<>();
            if(n == 0)
                bres.add("");
            
            return bres;
        }

        // stupid base case
        ArrayList<String> nm1path = getStairPaths2(n - 1);
        ArrayList<String> nm2path = getStairPaths2(n - 2);
        ArrayList<String> nm3path = getStairPaths2(n - 3);

        ArrayList<String> mres = new ArrayList<>();

        for(String path : nm1path) {
            mres.add("1" + path);
        }
        for(String path : nm2path) {
            mres.add("2" + path);
        }
        for(String path : nm3path) {
            mres.add("3" + path);
        }

        return mres;
    }

    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        // horizontal calls -> column change
        ArrayList<String> mres = new ArrayList<>();
        if(sc + 1 <= dc) {
            ArrayList<String> hres = getMazePaths(sr, sc + 1, dr, dc);
            // add horizontal
            for(int i = 0; i < hres.size(); i++) {
                String path = hres.get(i);
                mres.add("h" + path);
            }
        }

        // vertical calls -> row change
        if(sr + 1 <= dr) {
            ArrayList<String> vres = getMazePaths(sr + 1, sc, dr, dc);
            // add vertical
            for(int i = 0; i < vres.size(); i++) {
                String path = vres.get(i);
                mres.add("v" + path);
            }
        }
        return mres;
    }

    public static ArrayList<String> getMazePathsWithJumps(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }


        ArrayList<String> mres = new ArrayList<>();
        // horizontal
        for(int jump = 1; sc + jump <= dc; jump++) {
            ArrayList<String> hres = getMazePathsWithJumps(sr, sc + jump, dr, dc);

            for(int i = 0; i < hres.size(); i++) {
                String path = hres.get(i);
                mres.add("h" + jump + path);
            }
        }
        // vertical
        for(int jump = 1; sr + jump <= dr; jump++) {
            ArrayList<String> vres = getMazePathsWithJumps(sr + jump, sc, dr, dc);

            for(int i = 0; i < vres.size(); i++) {
                String path = vres.get(i);
                mres.add("v" + jump + path);
            }
        }
        // diagonal
        for(int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++) {
            ArrayList<String> dres = getMazePathsWithJumps(sr + jump, sc + jump, dr, dc);

            for(int i = 0; i < dres.size(); i++) {
                String path = dres.get(i);
                mres.add("d" + jump + path);
            }
        }
        return mres;
    }


    public static void rec() {
        int sr = 0;
        int sc = 0;
        int dr = 4;
        int dc = 0;

        // System.out.println(getMazePaths(sr, sc, dr, dc));
        System.out.println(getMazePathsWithJumps(sr, sc, dr, dc));


        // int n = 4;
        // System.out.println(getStairPaths(n));
        // System.out.println(getStairPaths2(n));


        // String str = "abc";
        // ArrayList<String> res = gss(str);
        // System.out.println(res);

        // String str = "723";
        // ArrayList<String> res = gkpc(str);
        // System.out.println(res);
        // for(String s : res)
        //     System.out.println(s);
    }
    
    public static void main(String[] args) {
        rec();
    }
}

import java.util.*;

public class recursion3 {

    public static ArrayList<String> getSubSeq(String str) {
        if (str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1); // bc
        ArrayList<String> rres = getSubSeq(ros);

        // str = abc => rres = [-, b, c, bc];
        ArrayList<String> mres = new ArrayList<>();
        for (String s : rres) {
            mres.add("-" + s);
        }
        for (String s : rres) {
            mres.add(ch + s);
        }
        return mres;
    }

    public static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String num) {
        if (num.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        int codeIndx = num.charAt(0) - '0';
        String ron = num.substring(1);
        String code = codes[codeIndx];

        ArrayList<String> rres = getKPC(ron);
        ArrayList<String> mres = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            for (String str : rres) {
                mres.add(ch + str);
            }
        }
        return mres;
    }

    public static void solve(String str) {
        ArrayList<String> res = getSubSeq(str);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> getStairPaths(int n) {
        // basement
        if (n < 0) {
            ArrayList<String> bres = new ArrayList<>();
            return bres; // here size is 0 which is useless for jump addition in parths
        }

        // at destinatiopn
        if (n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> nm1Path = getStairPaths(n - 1);
        ArrayList<String> nm2Path = getStairPaths(n - 2);
        ArrayList<String> nm3Path = getStairPaths(n - 3);

        ArrayList<String> mres = new ArrayList<>();

        // make path for jump = 1
        for (String path : nm1Path) {
            mres.add("1" + path);
        }
        // make path for jump = 2
        for (String path : nm2Path) {
            mres.add("2" + path);
        }
        // make path for jump = 3
        for (String path : nm3Path) {
            mres.add("3" + path);
        }

        return mres;
    }

    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        // answer
        if (sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't move path
            return bres;
        }
        // invalid
        if (sr > dr || sc > dc) {
            ArrayList<String> bres = new ArrayList<>();
            return bres;
        }

        // get path after horizontal jump
        ArrayList<String> hres = getMazePaths(sr, sc + 1, dr, dc);
        ArrayList<String> vres = getMazePaths(sr + 1, sc, dr, dc);

        ArrayList<String> mres = new ArrayList<>();
        // add path of horizontal jump
        for (String path : hres) {
            mres.add("h" + path);
        }
        // add path of vertical jump
        for (String path : vres) {
            mres.add("v" + path);
        }

        return mres;
    }

    public static ArrayList<String> getMazePathsWithJump(int x, int y, int dr, int dc) {
        // result
        if(x == dr && y == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        // invalid
        if(x > dr || y > dc) {
            return new ArrayList<String>();
        }
        ArrayList<String> mres = new ArrayList<>();
        // Horizontal Jumps
        for (int jump = 1; jump + y <= dc; jump++) {
            ArrayList<String> hres = getMazePathsWithJump(x, y + jump, dr, dc);
            for (String path : hres) {
                mres.add("h" + jump + path);
            }
        }
        // Vertical
        for (int jump = 1; jump + x <= dr; jump++) {
            ArrayList<String> vres = getMazePathsWithJump(x + jump, y, dr, dc);
            for (String path : vres) {
                mres.add("v" + jump + path);
            }
        }

        // Diagonal
        for (int jump = 1; jump + x <= dr && jump + y <= dc; jump++) {
            ArrayList<String> dres = getMazePathsWithJump(x + jump, y + jump, dr, dc);
            for (String path : dres) {
                mres.add("d" + jump + path);
            }
        }

        return mres;
    }

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        String str = scn.next();
        solve(str);
    }
}

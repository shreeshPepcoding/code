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

    public static ArrayList<String> getMazePath(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't move
            return bres;
        }

        ArrayList<String> mres = new ArrayList<>();
        // horizontal calls
        if(sc + 1 <= dc) {
            ArrayList<String> hres = getMazePath(sr, sc + 1, dr, dc);
            for(String s : hres) {
                mres.add("h" + s);
            }
        }
        // vertical calls
        if(sr + 1 <= dr) {
            ArrayList<String> vres = getMazePath(sr + 1, sc, dr, dc);
            for(String s : vres) {
                mres.add("v" + s);
            }
        }
        return mres;
    }

    public static ArrayList<String> getMazePath2(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        } else if(sr > dr || sc > dc) {
            return new ArrayList<>();
            // ArrayList<String> bres = new ArrayList<>();
            // return bres;
        }

        ArrayList<String> mres = new ArrayList<>();
        ArrayList<String> hres = getMazePath2(sr, sc + 1, dr, dc);
        ArrayList<String> vres = getMazePath2(sr + 1, sc, dr, dc);

        for(String s : hres)
            mres.add("h" + s);

        for(String s : vres)
            mres.add("v" + s);

        return mres;
    }

    public static ArrayList<String> getMazePathWithJumps(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> mres = new ArrayList<>();
        // horizontal
        for(int jump = 1; jump + sc <= dc; jump++) {
            ArrayList<String> res = getMazePathWithJumps(sr, sc + jump, dr, dc);

            for(String s : res) {
                mres.add("h" + jump + s);
            }
        }
        // vertical
        for(int jump = 1; jump + sr <= dr; jump++) {
            ArrayList<String> res = getMazePathWithJumps(sr + jump, sc, dr, dc);

            for(String s : res) {
                mres.add("v" + jump + s);
            }
        }
        // diagonal
        for(int jump = 1; sr + jump <= dr && jump + sc <= dc; jump++) {
            ArrayList<String> res = getMazePathWithJumps(sr + jump, sc + jump, dr, dc);

            for(String s : res) {
                mres.add("d" + jump + s);
            }
        }
        return mres;
    }

    // testing of code
    public static ArrayList<String> getMazePathWithJumps2(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }


        ArrayList<String> mres = new ArrayList<>();
        for(int jump = 1; jump + sr <= dr || jump + sc <= dc; jump++) {
            if(jump + sc <= dc) {
                ArrayList<String> res = getMazePathWithJumps2(sr, sc + jump, dr, dc);
                for(String s : res) {
                    mres.add("h" + jump + s);
                }
            }

            if(jump + sr <= dr) {
                ArrayList<String> res = getMazePathWithJumps2(sr + jump, sc, dr, dc);
                for(String s : res) {
                    mres.add("v" + jump + s);
                }
            }

            if(jump + sr <= dr && jump + sc <= dc) {
                ArrayList<String> res = getMazePathWithJumps2(sr + jump, sc + jump, dr, dc);
                for(String s : res) {
                    mres.add("d" + jump + s);
                }
            }
        }
        return mres;
    }

    // os -> original stair
    public static ArrayList<String> getStairPathWithJumps(int[] jumps, int n, int os) {
        // stupid
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres; 
        }
        // smart calls
        ArrayList<String> mres = new ArrayList<>();
        for(int jump = 1; jump <= jumps[n] && n - jump >= 0; jump++) {
            ArrayList<String> rres = getStairPathWithJumps(jumps, n - jump, os);
            for(String s : rres) {
                mres.add(jump + s);
            }
        }

        // if(n == os && mres.size() == 0) {
        //     System.out.println("No Possible Paths");
        // }
        
        return mres;
    }

    // testin with codes
    public static ArrayList<String> testingMaze(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }

        ArrayList<String> myresult = new ArrayList<>();
        System.out.println(sr + " " + sc);

        for (int jump = 1; jump + sc <= dc; jump++) {
            ArrayList<String> ans = testingMaze(sr, sc + jump, dr, dc);
            for (String s : ans) {
                myresult.add("h" + jump + s);
            }
        }

        for (int jump = 1; jump + sr <= dr; jump++) {
            ArrayList<String> ans = testingMaze(sr + jump, sc, dr, dc);
            for (String s : ans) {
                myresult.add("v" + jump + s);
            }
        }

        for (int jump = 1; jump + sc <= dc && jump + sr <= dr; jump++) {
            ArrayList<String> ans = testingMaze(sr + jump, sc + jump, dr, dc);
            for (String s : ans) {
                myresult.add("d" + jump + s);
            }
        }
        return myresult;
    }

    public static void ques() {

        // ArrayList<String> res = getMazePath2(0, 0, 3, 3);
        // ArrayList<String> res = getMazePathWithJumps(0, 0, 3, 3);
        // ArrayList<String> res1 = getMazePathWithJumps2(0, 0, 3, 3);
        int[] jumps = {1, 0, 2, 1, 1, 0, 2, 3};
        ArrayList<String> res = getStairPathWithJumps(jumps, 7, 7);
        for(String s : res)
            System.out.println(s);

        // for(int i = 0; i < res.size(); i++) {
        //     String s1 = res.get(i);
        //     String s2 = res1.get(i);

        //     System.out.println(s1 + "\t \t" + s2);
        // }


        // System.out.println(getStairPaths3(7));
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
import java.util.*;

public class recursion {


    public static void ques() {
        return;
    }

    public static void demoForNormalRadius() {
        // dummy matrix for figure out the radius concept
        int n = 5;
        int m = 4;


        int r = 0;
        int c = 0;

        int[] rdir = {-1, 0, 1, 0};
        int[] cdir = {0, 1, 0, -1};

        int radius = Math.max(n,m);
        // for radius 1
        for(int rad = 1; rad <= radius; rad++) {
            for(int dir = 0; dir < rdir.length; dir++) {
                int rr = r + (rad * rdir[dir]);
                int cc = c + (rad * cdir[dir]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + dir +" :->  row : " + rr + ", col : " + cc);
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for(int dir = 0; dir < rdir.length; dir++) {
            for(int rad = 1; rad <= radius; rad++) {
                int rr = r + (rad * rdir[dir]);
                int cc = c + (rad * cdir[dir]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + dir +" :->  row : " + rr + ", col : " + cc);
            }
        }
    }

    public static void demoForQueenTraversal() {
        int n = 10;
        int m = 10;

        int r = 0;
        int c = 0;

        int[][] dir = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
        };

        int radius = Math.max(n,m);
        // for radius 1
        for(int rad = 1; rad <= radius; rad++) {
            for(int d = 0; d < dir.length; d++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + d +" :->  row : " + rr + ", col : " + cc);
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for(int d = 0; d < dir.length; d++) {
            for(int rad = 1; rad <= radius; rad++) {
                int rr = r + (rad * dir[d][0]);
                int cc = c + (rad * dir[d][1]);

                // calls
                if(rr >= 0 && rr < n && cc >= 0 && cc < m)
                    System.out.println("Radius : " + rad + ", dir : " + d +" :->  row : " + rr + ", col : " + cc);
            }
        }
    }

    public static void demoForKnightTraversal() {
        int[] rdir = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] cdir = {1, 2, 2, 1, -1, -2, -2, -1};
        int r = 4;
        int c = 4;
        for(int d = 0; d < rdir.length; d++) {
            int rr = r + rdir[d];
            int cc = c + cdir[d];

            System.out.println("row : " + rr + ", col : " + cc);
        }
    }

    public static void demo() {
        // demoForNormalRadius();
        // demoForQueenTraversal();
        demoForKnightTraversal();
    }

    public static void main(String[] args) {
        // ques();
        demo();
    }

}
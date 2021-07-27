import java.util.*;

public class exitPoint {
    
    public static void printExitPoint(int[][] arr) {
        int dir = 0;
        // source point
        int r = 0;
        int c = 0;

        while(r >= 0 && r < arr.length && c >= 0 && c < arr[0].length) {
            // direction management
            dir = (dir + arr[r][c]) % 4;

            // moves management
            if(dir == 0) {
                c++;
            } else if(dir == 1) {
                r++;
            } else if(dir == 2) {
                c--;
            } else {
                r--;
            }
        }
        // management of invalid coordinate
        if(dir == 0) {
            c--;
        } else if(dir == 1) {
            r--;
        } else if(dir == 2) {
            c++;
        } else {
            r++;
        }

        System.out.println(r + ", " + c);
    }

    public static void main(String[] args) {
        int[][] arr = {
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 0, 1, 0}
        };

        printExitPoint(arr);
    }    
}

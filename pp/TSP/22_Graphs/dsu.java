import java.util.*;

public class dsu {
    
    public static int find(int[] setleaders, int x) {
        if(setleaders[x] == x) {
            return x;
        }

        int temp = find(setleaders, setleaders[x]);
        setleaders[x] = temp; // for path compression
        return temp;
    }

    public static int union(int[][] pairs, int n) {
        // n-> no. of vertex
        int[] setleaders = new int[n];
        int[] rankArray = new int[n]; 
        for(int i = 0; i < n; i++) {
            setleaders[i] = i;
            rankArray[i] = 0;
        }

        for(int[] pair : pairs) {
            int v1 = pair[0];
            int v2 = pair[1];

            int slofv1 = find(setleaders, v1); // slofv1 -> set leader of val1
            int slofv2 = find(setleaders, v2); // slofv2 -> set leader of val2

            // set leader of val1 point to set leader of val2 -> logically correct by not optimised
            // setleaders[slofv1] = slofv2;

            // to optimise union, we required a rank array
            int rank1 = rankArray[slofv1];
            int rank2 = rankArray[slofv2];
            // max rank's set leader will pointed with min rank's set leader
            if(rank1 > rank2) {
                // set leader 2 will point to set leader 1
                setleaders[slofv2] = slofv1;
            } else if(rank1 < rank2) {
                // set leader 1 will point to set leader 2
                setleaders[slofv1] = slofv2;
            } else {
                // according to choice point anyone, but remember to increment in its rank
                // // set leader 2 will point to set leader 1
                setleaders[slofv2] = slofv1;
                rankArray[slofv2]++;
            }
        }
        // find number of sets
        int count = 0; 
        for(int i = 0; i < n; i++) {
            if(setleaders[i] == i)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] pairs = {
            {1, 2},
            {3, 4},
            {2, 3},
            {1, 4},
            {5, 6},
            {7, 8},
            {5, 7},
            {9, 10},
            {1, 3}, 
            {1, 5}
        };
        int n = 11;
        int count = union(pairs, n);
        System.out.println(count);
    }
}

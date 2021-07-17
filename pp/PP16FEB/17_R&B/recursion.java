import java.util.*;

public class recursion {

    // ci-> current item, ti-> total items
    public static void permutations(int[] boxes, int ci, int ti){
        if(ci > ti) {
            // print the result
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }

        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // place
                boxes[b] = ci;
                permutations(boxes, ci + 1, ti);
                // unplace
                boxes[b] = 0;
            }
        }
    }

    // cb -> current box, tb-> total box, ssf-> selected so far, ts->total selection, asf->answer so far
    public static void combinations(int cb, int tb, int ssf, int ts, String asf){
        if(cb > tb) {
            if(ssf == ts) {
                // print
                System.out.println(asf);
            }
            return;
        }
        // yes call
        if(ssf < ts)
            combinations(cb + 1, tb, ssf + 1, ts, asf + "i");
        // no call
        combinations(cb + 1, tb, ssf, ts, asf + "-");
    }

    public static void permutations(int cb, int tb, int[] items, int ssf, int ts, String asf){
        if(cb > tb) {
            // print
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }

        // yes call
        for(int i = 0; i < items.length; i++) {
            if(items[i] == 0) {
                // place
                items[i] = 1;
                permutations(cb + 1, tb, items, ssf + 1, ts, asf + (i + 1));
                // unplace
                items[i] = 0;
            }
        }
        // no call
        permutations(cb + 1, tb, items, ssf, ts, asf + "-");
    }

    // lb -> last box
    public static void combinations(int[] boxes, int ci, int ti, int lb){
        if(ci > ti) {
            for(int i = 0; i < boxes.length; i++) {
                if(boxes[i] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }
        for(int b = lb + 1; b < boxes.length; b++) {
            // place
            boxes[b] = 1;
            combinations(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    public static void fun() {

    }

    public static void main(String[] args) {
        fun();    
    }
}
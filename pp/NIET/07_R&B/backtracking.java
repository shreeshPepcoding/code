import java.util.*;

public class backtracking {


    // ci-> current item, ti-> total items
    public static void permutations1(int[] boxes, int ci, int ti){
        if(ci > ti) {
            // print arrange of items in box and return
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }
        // loop of options
        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // place current item at bth box
                boxes[b] = ci;
                // recursive call
                permutations1(boxes, ci + 1, ti);
                // unplace current item at bth box
                boxes[b] = 0;
            }
        }
    }

    // cb-> current box, tb-> total box, ssf -> selected so far, ts-> total selection, asf-> answer so far
    public static void combinations1(int cb, int tb, int ssf, int ts, String asf){
        if(cb > tb) {
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }
        // yes call
        if(ssf < ts) {
            combinations1(cb + 1, tb, ssf + 1, ts, asf + "i");
        }
        // no call
        combinations1(cb + 1, tb, ssf, ts, asf + "-");
    }

    public static void permutations2(int cb, int tb, int[] items, int ssf, int ts, String asf){
        // base case
        if(cb > tb) {
            if(ssf == ts) {
                System.out.println(asf);
            }
            return;
        }
        // yes call
        for(int i = 0; i < items.length; i++) {
            if(items[i] == 0 && ssf < ts) {
                // use ith item -> mark
                items[i] = i + 1;
                permutations2(cb + 1, tb, items, ssf + 1, ts, asf + (i + 1));
                // make it available -> unmark
                items[i] = 0;
            }
        }
        // no call
        permutations2(cb + 1, tb, items, ssf, ts, asf + "0");
    }

    // ci -> current item, ti-> total item, lb -> last box
    public static void combinations2(int[] boxes, int ci, int ti, int lb){
        if(ci > ti) {
            for(int val : boxes) {
                if(val == 0) {
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
            boxes[b] = ci;
            // call
            combinations2(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;
        }
    }

    public static void fun() {
        combinations1(1, 4, 0, 2, "");
    }

    public static void main(String[] args) {
        fun();
    }

}

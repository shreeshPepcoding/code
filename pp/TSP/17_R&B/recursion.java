
public class recursion {

    // ci-> current items. ti-> total items
    public static void permutations(int[] boxes, int ci, int ti){
        if(ci > ti) {
            for(int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }

        for(int b = 0; b < boxes.length; b++) {
            if(boxes[b] == 0) {
                // box is empty
                // place object
                boxes[b] = ci;
                permutations(boxes, ci + 1, ti);
                // unplace objects
                boxes[b] = 0;
            }
        }
    }

    // cb-> current box, tb-> total box, isf->item so far, ti->total item, asf-> answer so far
    public static void combinations(int cb, int tb, int isf, int ti, String asf){
        if(cb > tb) {
            if(isf == ti) {
                // print
                System.out.println(asf);
            }
            return;
        }
        
        // yes call
        if(isf + 1 <= ti)
            combinations(cb + 1, tb, isf + 1, ti, asf + "i");
        // no call
        combinations(cb + 1, tb, isf, ti, asf + "-");
    }

    // cb -> current box, tb-> total box, isf-> item so far, ti-> total item, asf-> asnwer so far
    public static void permutations(int cb, int tb, int[] items, int isf, int ti, String asf){
        if(cb > tb) {
            if(isf == ti) {
                // print
                System.out.println(asf);
            }
            return;
        }

        // yes call
        for(int i = 0; i < items.length && isf < ti; i++) {
            if(items[i] == 0) {
                // select item
                items[i] = 1;
                permutations(cb + 1, tb, items, isf + 1, ti, asf + (i + 1));
                // deselect item
                items[i] = 0;
            }
        }
        // no call
        permutations(cb + 1, tb, items, isf, ti, asf + "0");
    }
    
    // ci-> current item, ti-> total item, lb-> last box used
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
            // place ci item on bth box
            boxes[b] = ci;
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